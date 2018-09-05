package com.bee.devops.admin.component.git.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.stream.Stream;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;

import com.bee.devops.admin.component.git.common.AuthMethod;
import com.bee.devops.admin.component.git.common.PrefObj;
import com.bee.devops.admin.component.git.exceptions.MissingRepoException;
import com.bee.devops.admin.component.git.file.ConflictingRepoFile;
import com.bee.devops.admin.component.git.file.DirectoryRepoFile;
import com.bee.devops.admin.component.git.file.IgnoredRepoFile;
import com.bee.devops.admin.component.git.file.MissingRepoFile;
import com.bee.devops.admin.component.git.file.ModifiedRepoFile;
import com.bee.devops.admin.component.git.file.RepoFile;
import com.bee.devops.admin.component.git.file.StagedAndModifiedRepoFile;
import com.bee.devops.admin.component.git.file.StagedRepoFile;
import com.bee.devops.admin.component.git.file.UntrackedRepoFile;
import com.bee.devops.admin.component.git.helper.RepoHelper;

/**
 * The singleton SessionModel stores all the Repos (contained in RepoHelper objects)
 * in the session and lets the user switch between repos.
 */
public class SessionModel {

    // Keys for preferences recall
    private static final String RECENT_REPOS_LIST_KEY = "RECENT_REPOS_LIST";
    private static final String LAST_OPENED_REPO_PATH_KEY = "LAST_OPENED_REPO_PATH";
    private static final String LAST_UUID_KEY="LAST_UUID";

    private RepoHelper currentRepoHelper;

    private List<RepoHelper> allRepoHelpers;
    private static SessionModel sessionModel;

    private Preferences preferences;

    static final Logger logger = LogManager.getLogger(SessionModel.class);

    /**
     * @return the SessionModel object
     */
    public static SessionModel getSessionModel() {
        if (sessionModel == null) {
            sessionModel = new SessionModel();
        }
        return sessionModel;
    }

    /**
     * Private constructor for the SessionModel singleton
     */
    private SessionModel() {
        this.allRepoHelpers = new ArrayList<>();
        this.preferences = Preferences.userNodeForPackage(this.getClass());
    }

    /**
     * Opens the given repository
     *
     * @param repoHelper the repository to open
     */
    private void openRepo(RepoHelper repoHelper) throws BackingStoreException, IOException, ClassNotFoundException {
        if(!this.allRepoHelpers.contains(repoHelper)) {
            this.allRepoHelpers.add(repoHelper);
        }
        this.currentRepoHelper = repoHelper;
        this.saveListOfRepoPathStrings();
        this.saveMostRecentRepoPathString();
    }

    /**
     * Loads a RepoHelper by checking to see if that RepoHelper's directory is already
     * loaded into the Model. If it is already loaded, this method will load that RepoHelper.
     * If not, this method will add the new RepoHelper and then load it.
     *
     * @param repoHelperToLoad the RepoHelper to be loaded.
     */
    public void openRepoFromHelper(RepoHelper repoHelperToLoad) throws BackingStoreException, IOException, ClassNotFoundException, MissingRepoException {
        RepoHelper matchedRepoHelper = this.matchRepoWithAlreadyLoadedRepo(repoHelperToLoad);
        if (matchedRepoHelper == null) {
            // So, this repo isn't loaded into the model yet
            this.allRepoHelpers.add(repoHelperToLoad);
            this.openRepo(repoHelperToLoad);
        } else {
            // So, this repo is already loaded into the model
            if(matchedRepoHelper.exists()){
                this.openRepo(matchedRepoHelper);
            }else{
                this.allRepoHelpers.remove(matchedRepoHelper);
                throw new MissingRepoException();
            }
        }
    }

    /**
     * Checks if a repoHelper is already loaded in the model by comparing repository directories.
     *
     * @param repoHelperCandidate the repoHelper being checked
     * @return the repo helper that points to the same repository as the candidate
     *          (by directory), or null if there is no such RepoHelper already in the model.
     */
    private RepoHelper matchRepoWithAlreadyLoadedRepo(RepoHelper repoHelperCandidate) {
        if(repoHelperCandidate != null) {
            for (RepoHelper repoHelper : this.allRepoHelpers) {
                if (repoHelper.getLocalPath().equals(repoHelperCandidate.getLocalPath())) {
                    return repoHelper;
                }
            }
        }
        return null;
    }

    public RepoHelper matchRepoWithAlreadyLoadedRepo(String repoPath) {
        if(StringUtils.isNotBlank(repoPath)) {
            for (RepoHelper repoHelper : this.allRepoHelpers) {
                if (repoHelper.getLocalPath().toString().equals(repoPath)) {
                    return repoHelper;
                }
            }
        }
        return null;
    }
    
    /**
     * @return the current repository
     */
    public RepoHelper getCurrentRepoHelper(){
        return currentRepoHelper;
    }

    /**
     * @return the current JGit repository associated with the current RepoHelper
     */
    private Repository getCurrentRepo() {
        return this.currentRepoHelper.getRepo();
    }

    /**
     * Gets a list of all repositories held in this session. Repositories
     * that no longer exist are removed (and not returned)
     * @return a list of all existing repositories held in the session
     */
    public List<RepoHelper> getAllRepoHelpers() {
        List<RepoHelper> tempList = new ArrayList<>(allRepoHelpers);
        for(RepoHelper r : tempList){
            if(!r.exists()){
                allRepoHelpers.remove(r);
            }
        }
        return allRepoHelpers;
    }

    /**
     * Calls `git status` and returns the set of untracked files that Git reports.
     *
     * @return a set of untracked filenames in the working directory.
     * @throws GitAPIException if the `git status` call fails.
     */
    private Set<String> getUntrackedFiles(Status status) throws GitAPIException {
        if(status == null) {
            status = new Git(this.getCurrentRepo()).status().call();
        }

        return status.getUntracked();
    }

    /**
     * Calls `git status` and returns the set of untracked files that Git reports.
     *
     * @return a set of untracked filenames in the working directory.
     * @throws GitAPIException if the `git status` call fails.
     */
    private Set<String> getIgnoredFiles(Status status) throws GitAPIException {
        if(status == null) {
            status = new Git(this.getCurrentRepo()).status().call();
        }

        return status.getIgnoredNotInIndex();
    }

    /**
     * Calls `git status` and returns the set of conflicting files that Git reports.
     *
     * @return a set of conflicting filenames in the working directory.
     * @throws GitAPIException
     */
    private Set<String> getConflictingFiles(Status status) throws GitAPIException {
        if (status == null) {
            status = new Git(this.getCurrentRepo()).status().call();
        }

        return status.getConflicting();
    }

    /**
     * Calls `git status` and returns the set of missing files that Git reports.
     *
     * @return a set of missing filenames in the working directory.
     * @throws GitAPIException if the `git status` call fails.
     */
    private Set<String> getMissingFiles(Status status) throws GitAPIException {
        if(status == null) {
            status = new Git(this.getCurrentRepo()).status().call();
        }

        return status.getMissing();
    }

    /**
     * Calls `git status` and returns the set of modified files that Git reports.
     *
     * Modified files differ between the disk and the index
     *
     * @return a set of modified filenames in the working directory.
     * @throws GitAPIException if the `git status` call fails.
     */
    private Set<String> getModifiedFiles(Status status) throws GitAPIException {
        if(status == null) {
            status = new Git(this.getCurrentRepo()).status().call();
        }

        return status.getModified();
    }


    /**
     * Calls `git status` and returns the set of staged files that Git reports.
     *
     * @return a set of modified filenames in the working directory.
     * @throws GitAPIException if the `git status` call fails.
     */
    private Set<String> getStagedFiles(Status status) throws GitAPIException {
        if(status == null) {
            status = new Git(this.getCurrentRepo()).status().call();
        }
        HashSet<String> stagedFiles = new HashSet<>();
        stagedFiles.addAll(status.getChanged());
        stagedFiles.addAll(status.getAdded());
        return stagedFiles;
    }

    /**
     * Get (construct) the current repo's working directory DirectoryRepoFile
     * by creating and populating a new DirectoryRepoFile from the repository's
     * parent directory.
     *
     * @return the populated DirectoryRepoFile for the current repository's parent directory.
     * @throws GitAPIException if the call to `populateDirectoryRepoFile(...)` fails.
     * @throws IOException if the call to `populateDirectoryRepoFile(...)` fails.
     */
    public DirectoryRepoFile getParentDirectoryRepoFile() throws GitAPIException, IOException {
        Path fullPath = this.currentRepoHelper.getLocalPath();

        DirectoryRepoFile parentDirectoryRepoFile = new DirectoryRepoFile(fullPath, this.getCurrentRepoHelper());
        parentDirectoryRepoFile = this.populateDirectoryRepoFile(parentDirectoryRepoFile);

        return parentDirectoryRepoFile;
    }

    /**
     * Adds all the children files contained within a directory to that directory's DirectoryRepoFile.
     *
     * @param superDirectory the RepoFile of the directory to be populated.
     * @return the populated RepoFile of the initially passed-in directory.
     * @throws GitAPIException if the `git status` methods' calls to Git fail (for getting
     * @throws IOException if the DirectoryStream fails.
     */
    private DirectoryRepoFile populateDirectoryRepoFile(DirectoryRepoFile superDirectory) throws GitAPIException, IOException {
        try {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(superDirectory.getFilePath());
            for (Path path : directoryStream) {
                if (path.equals(this.getCurrentRepo().getDirectory().toPath())) {
                    // If the path is the Repository's .git folder, don't populate it.
                } else if (Files.isDirectory(path)) {
                    // Recurse! Populate the directory.
                    DirectoryRepoFile subdirectory = new DirectoryRepoFile(path, superDirectory.getRepo());
                    populateDirectoryRepoFile(subdirectory);
                    superDirectory.addChild(subdirectory);

                } else {
                    // So, this is a file and not a directory.

                    Status status = new Git(this.getCurrentRepo()).status().call();

                    Set<String> modifiedFiles = getModifiedFiles(status);
                    Set<String> missingFiles = getMissingFiles(status);
                    Set<String> untrackedFiles = getUntrackedFiles(status);
                    Set<String> conflictingFiles = getConflictingFiles(status);
                    Set<String> stagedFiles = getStagedFiles(status);

                    // Relativize the path to the repository, because that's the file structure JGit
                    //  looks for in an 'add' command
                    Path repoDirectory = this.getCurrentRepo().getWorkTree().toPath();
                    Path relativizedPath = repoDirectory.relativize(path);

                    // Determine what type of RepoFile we're dealing with.
                    //  Is it modified? Untracked/new? Missing? Just a plain file?
                    //  Construct the appropriate RepoFile and add it to the parent directory.
                    if (conflictingFiles.contains(relativizedPath.toString())) {
                        ConflictingRepoFile conflictingFile = new ConflictingRepoFile(path, this.getCurrentRepoHelper());
                        superDirectory.addChild(conflictingFile);
                    } else if (stagedFiles.contains(relativizedPath.toString())) {
                        StagedRepoFile stagedFile = new StagedRepoFile(path, this.getCurrentRepoHelper());
                        superDirectory.addChild(stagedFile);
                    }else if (modifiedFiles.contains(relativizedPath.toString())) {
                        ModifiedRepoFile modifiedFile = new ModifiedRepoFile(path, this.getCurrentRepoHelper());
                        superDirectory.addChild(modifiedFile);
                    } else if (missingFiles.contains(relativizedPath.toString())) {
                        MissingRepoFile missingFile = new MissingRepoFile(path, this.getCurrentRepoHelper());
                        superDirectory.addChild(missingFile);
                    } else if (untrackedFiles.contains(relativizedPath.toString())) {
                        UntrackedRepoFile untrackedFile = new UntrackedRepoFile(path, this.getCurrentRepoHelper());
                        superDirectory.addChild(untrackedFile);
                    } else {
                        RepoFile plainRepoFile = new RepoFile(path, this.getCurrentRepoHelper());
                        superDirectory.addChild(plainRepoFile);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Exception trying to populate directory repo file");
            logger.debug(e.getStackTrace());
            e.printStackTrace();
        }
        return superDirectory;
    }

    /**
     * Assembles all the changed files (modified, missing, untracked) into RepoFiles
     * and returns a list of them.
     *
     * @return a list of changed files, contained in RepoFile objects.
     * @throws GitAPIException if the `git status` calls fail.
     */
    @SuppressWarnings("resource")
	List<RepoFile> getAllChangedRepoFiles() throws GitAPIException {
        Status status = new Git(this.getCurrentRepo()).status().call();
        Set<String> modifiedFiles = getModifiedFiles(status);
        Set<String> missingFiles = getMissingFiles(status);
        Set<String> untrackedFiles = getUntrackedFiles(status);
        Set<String> conflictingFiles = getConflictingFiles(status);
        Set<String> stagedFiles = getStagedFiles(status);
//        ArrayList<String> conflictingThenModifiedFiles = ConflictingFileWatcher.getConflictingThenModifiedFiles();

        List<RepoFile> changedRepoFiles = new ArrayList<>();

        ArrayList<String> conflictingRepoFileStrings = new ArrayList<>();

        for (String conflictingFileString : conflictingFiles) {
            // If a file is conflicting but was also recently modified, make it a ConflictingThenModifiedRepoFile instead
            ConflictingRepoFile conflictingRepoFile = new ConflictingRepoFile(conflictingFileString, this.getCurrentRepoHelper());
            changedRepoFiles.add(conflictingRepoFile);
            // Store these paths to make sure this file isn't registered as a modified file or something.
            //  If it's conflicting, the app should focus only on the conflicting state of the
            //  file first.
            //
            // e.g. If a modification causes a conflict, that file should have its conflicts resolved
            //      before it gets added.
            conflictingRepoFileStrings.add(conflictingFileString);
        }

        // Remove files from conflictingThenModifedFiles when they are no longer conflicting

        for (String stagedFileString : stagedFiles) {
            if (!conflictingRepoFileStrings.contains(stagedFileString) && !modifiedFiles.contains(stagedFileString)) {
                StagedRepoFile stagedRepoFile = new StagedRepoFile(stagedFileString, this.getCurrentRepoHelper());
                changedRepoFiles.add(stagedRepoFile);
            } else if (!conflictingRepoFileStrings.contains(stagedFileString)) {
                StagedAndModifiedRepoFile stagedAndModifiedRepoFile = new StagedAndModifiedRepoFile(stagedFileString, this.getCurrentRepoHelper());
                changedRepoFiles.add(stagedAndModifiedRepoFile);
            }
        }
        for (String modifiedFileString : modifiedFiles) {
            if (!conflictingRepoFileStrings.contains(modifiedFileString) && !stagedFiles.contains(modifiedFileString)) {
                ModifiedRepoFile modifiedRepoFile = new ModifiedRepoFile(modifiedFileString, this.getCurrentRepoHelper());
                changedRepoFiles.add(modifiedRepoFile);
            }
        }

        for (String missingFileString : missingFiles) {
            if (!conflictingRepoFileStrings.contains(missingFileString)) {
                MissingRepoFile missingRepoFile = new MissingRepoFile(missingFileString, this.getCurrentRepoHelper());
                changedRepoFiles.add(missingRepoFile);
            }
        }

        for (String untrackedFileString : untrackedFiles) {
            if (!conflictingRepoFileStrings.contains(untrackedFileString)) {
                UntrackedRepoFile untrackedRepoFile = new UntrackedRepoFile(untrackedFileString, this.getCurrentRepoHelper());
                changedRepoFiles.add(untrackedRepoFile);
            }
        }

        Collections.sort(changedRepoFiles);
        return changedRepoFiles;
    }
    /**
     * Assembles all files in the repository's folder into RepoFiles
     * and returns a list of them.
     *
     * @return a list of changed files, contained in RepoFile objects.
     * @throws GitAPIException if the `git status` calls fail.
     */
    List<RepoFile> getAllRepoFiles() throws GitAPIException, IOException {
        List<RepoFile> allFiles = getAllChangedRepoFiles();

        Status status = new Git(this.getCurrentRepo()).status().call();

        for(String ignoredFileString : getIgnoredFiles(status)){
            IgnoredRepoFile ignoredRepoFile = new IgnoredRepoFile(ignoredFileString, this.getCurrentRepoHelper());
            allFiles.add(ignoredRepoFile);
        }

        List<Path> allPaths = new LinkedList<>();

        try (Stream<Path> paths = Files.walk(currentRepoHelper.getLocalPath())
                .filter(path -> !path.toString().contains(File.separator + ".git" + File.separator)
                        && !path.equals(currentRepoHelper.getLocalPath())
                        && !path.endsWith(".git"))) {
            paths.forEach(allPaths::add);
        }

        Set<Path> addedPaths = new HashSet<>();
        for(RepoFile file : allFiles){
            addedPaths.add(file.getFilePath());
        }

        for(Path path : allPaths){
            path = currentRepoHelper.getLocalPath().relativize(path);
            if(!addedPaths.contains(path)){
                RepoFile temp;
                if(path.toFile().isDirectory()){
                    temp = new DirectoryRepoFile(path, currentRepoHelper);
                }else{
                    temp = new RepoFile(path, currentRepoHelper);
                }
                allFiles.add(temp);
                addedPaths.add(path);
            }
        }

        Collections.sort(allFiles);
        return allFiles;
    }

    /**
     * Saves the model's list of RepoHelpers using the Preferences API (and the PrefObj wrapper
     *  from IBM).
     *
     * We store these as a list of file strings instead of Paths
     *  because Paths aren't serializable.
     *
     * @throws BackingStoreException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void saveListOfRepoPathStrings() throws BackingStoreException, IOException, ClassNotFoundException {
        ArrayList<String> repoPathStrings = new ArrayList<>();
        for (RepoHelper repoHelper : this.allRepoHelpers) {
            Path path = repoHelper.getLocalPath();
            repoPathStrings.add(path.toString());
        }

        // Store the list object using IBM's PrefObj helper class:
        PrefObj.putObject(this.preferences, RECENT_REPOS_LIST_KEY, repoPathStrings);
    }

    /**
     * Saves the most recently opened repository to the Preferences API (to be
     *  re-opened next time the app is opened).
     *
     * @throws BackingStoreException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void saveMostRecentRepoPathString() throws BackingStoreException, IOException, ClassNotFoundException {
        String pathString = this.currentRepoHelper.getLocalPath().toString();

        PrefObj.putObject(this.preferences, LAST_OPENED_REPO_PATH_KEY, pathString);
    }

    /**
     * Clears the information stored by the Preferences API:
     *  recent repos and the last opened repo.
     *
     * @throws BackingStoreException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void clearStoredPreferences() throws BackingStoreException, IOException, ClassNotFoundException {
        PrefObj.putObject(this.preferences, RECENT_REPOS_LIST_KEY, null);
        PrefObj.putObject(this.preferences, LAST_OPENED_REPO_PATH_KEY, null);
        PrefObj.putObject(this.preferences, LAST_UUID_KEY, null);
    }

    void setAuthPref(String pathname, AuthMethod authTechnique) {
        Preferences authPrefs = preferences.node("authentication");
        authPrefs.putInt(hashPathname(pathname), authTechnique.getEnumValue());
    }

    AuthMethod getAuthPref(String pathname)  {
        Preferences authPrefs = preferences.node("authentication");
        int enumValue = authPrefs.getInt(hashPathname(pathname), -1);
        if (enumValue == -1) {
            throw new NoSuchElementException("AuthPref not present");
        }

        return AuthMethod.getEnumFromValue(enumValue);
    }

    void removeAuthPref(String pathname) {
        Preferences authPrefs = preferences.node("authentication");
        authPrefs.remove(hashPathname(pathname));
    }

    String[] listAuthPaths() {
        Preferences authPrefs = preferences.node("authentication");
        try {
            return authPrefs.keys();
        } catch (BackingStoreException e) {
            throw new RuntimeException(e);
        }
    }

    // Preferences API has a limit of 80 characters max, and some pathnames
    // may be longer than that. Hashing it will solve that problem.
    String hashPathname(String pathname) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(pathname.getBytes());
        String prefKey;
        //try {
            //prefKey = new String(md.digest(), "US-ASCII");
            prefKey = DatatypeConverter.printHexBinary(md.digest());
            //prefKey = "hello";
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
        return prefKey;
    }

    public void removeRepoHelpers(List<RepoHelper> checkedItems) {
        for (RepoHelper item : checkedItems) {
            this.allRepoHelpers.remove(item);
        }
    }

    /**
     * After the last RepoHelper is closed by user, sessionModel needs to be
     * updated and reflect the new view.
     */
    public void resetSessionModel() {
        try {
            clearStoredPreferences();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        sessionModel = new SessionModel();
    }

    /**
     * Once files are uploaded to the server, we have a UUID to remember for the next
     * upload of files.
     */
    public void setLastUUID(String uuid) throws BackingStoreException, ClassNotFoundException, IOException {
        PrefObj.putObject(this.preferences, LAST_UUID_KEY, uuid);
    }

    /**
     * To upload a file to the server, we need to find the last uuid
     */
    public String getLastUUID() throws BackingStoreException, ClassNotFoundException, IOException {
        return (String) PrefObj.getObject(this.preferences, LAST_UUID_KEY);
    }
}
