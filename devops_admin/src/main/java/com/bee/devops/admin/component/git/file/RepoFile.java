package com.bee.devops.admin.component.git.file;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.jgit.api.errors.GitAPIException;

import com.bee.devops.admin.component.git.helper.RepoHelper;

/**
 *
 * The RepoFile contains a file in the repo and stores any children the file may have
 * (i.e. if it's a directory).
 *
 * For now, each RepoFile instance also contains a copy of the Repository that the file belongs
 * to, because the *currently open* repo may change when we support multiple repositories.
 *
 * This broad class is extended by more specific types of files you may find in a repository, such as:
 *      - DirectoryRepoFile
 *      - ModifiedRepoFile
 *      - UntrackedRepoFile
 *      - MissingRepoFile.
 *
 * The plain RepoFile class represents an untouched file in the repository that is thus
 * unaffected by commits.
 *
 */
public class RepoFile implements Comparable<RepoFile> {

    public Path filePath;
    protected RepoHelper repo;
    protected static final Logger logger = LogManager.getLogger(RepoFile.class);
    protected ArrayList<RepoFile> children; // Only directories will use this!

    public RepoFile(Path filePath, RepoHelper repo) {
        this.repo = repo;

        if(filePath.isAbsolute()){
            this.filePath = repo.getLocalPath().relativize(filePath);
        } else {
            this.filePath = filePath;
        }

    }

    public RepoFile(String filePathString, RepoHelper repo) {
        this(Paths.get(filePathString), repo);
    }

    /**
     *
     * @return whether or not this file can be added (staged)
     */
    public boolean canAdd() throws GitAPIException, IOException {
        return false;
    }

    public boolean canRemove() {
        return true;
    }

    /**
     * The files are *displayed* by only their location relative
     * to the repo, not the whole path.
     *
     * This is particularly helpful in the WorkingTreePanelView, where
     * the TreeView's leafs contain RepoFiles and presents them by their
     * string representation. Instead of flooding the user with a long directory
     * string, this displays the only info the user really cares about: the file name
     * and parent directories.
     *
     * @return the RepoFile's file name.
     */
    @Override
    public String toString() {
        return this.filePath.getFileName().toString();
    }

    public Path getFilePath() {
        return this.filePath;
    }

    public RepoHelper getRepo() {
        return this.repo;
    }

    public int getLevelInRepository(){
        int depth = 0;
        Path p = this.filePath.getParent();
        while(p!=null){
            depth++;
            p = p.getParent();
        }
        return depth;
    }

    public ArrayList<RepoFile> getChildren() {
        // Files with no children will return null, since this ArrayList was never instantiated.
        return this.children;
    }

    public void addChild(RepoFile repoFile) {
        // Files with no children can't have children added to them!
        System.err.println("Can't add children to this type of RepoFile.");
    }

    public boolean equals(Object o){
        if(o != null && o.getClass().equals(this.getClass())){
            RepoFile other = (RepoFile) o;
            return this.filePath.equals(other.filePath) && other.getRepo().getLocalPath().equals(getRepo().getLocalPath());
        }
        return false;
    }

    @Override
    public int compareTo(RepoFile other) {
        return this.toString().compareToIgnoreCase(other.toString());
    }
}
