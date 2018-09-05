package com.bee.devops.admin.component.git.file;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.jgit.api.errors.GitAPIException;

import com.bee.devops.admin.component.git.helper.RepoHelper;

/**
 * A subclass of the RepoFile class that holds a reference to
 * and interacts with a file in the repository that has conflicts
 * in git.
 */
public class ConflictingRepoFile extends RepoFile {

    static final Logger logger = LogManager.getLogger(ConflictingRepoFile.class);

    public ConflictingRepoFile(Path filePath, RepoHelper repo) {
        super(filePath, repo);
    }

    public ConflictingRepoFile(String filePathString, RepoHelper repo) {
        this(Paths.get(filePathString), repo);
    }

    @Override public boolean canAdd() throws GitAPIException, IOException{
        return true;
    }

    @Override public boolean canRemove() {
        return true;
    }
}
