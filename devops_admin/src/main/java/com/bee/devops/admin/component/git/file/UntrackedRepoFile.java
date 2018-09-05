package com.bee.devops.admin.component.git.file;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.bee.devops.admin.component.git.helper.RepoHelper;

/**
 * A subclass of RepoFile that contains a file that Git reports as untracked.
 */
public class UntrackedRepoFile extends RepoFile {

    public UntrackedRepoFile(Path filePath, RepoHelper repo) {
        super(filePath, repo);
    }

    public UntrackedRepoFile(String filePathString, RepoHelper repo) {
        this(Paths.get(filePathString), repo);
    }

    @Override public boolean canAdd() { return true; }

    @Override public boolean canRemove() { return false; }
}
