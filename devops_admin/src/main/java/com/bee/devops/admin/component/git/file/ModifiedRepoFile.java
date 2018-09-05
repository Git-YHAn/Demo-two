package com.bee.devops.admin.component.git.file;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.bee.devops.admin.component.git.helper.RepoHelper;

/**
 * A subclass of RepoFile that contains a file that Git reports as modified.
 */
public class ModifiedRepoFile extends RepoFile {

    public ModifiedRepoFile(Path filePath, RepoHelper repo) {
        super(filePath, repo);
    }

    public ModifiedRepoFile(String filePathString, RepoHelper repo) {
        this(Paths.get(filePathString), repo);
    }

    @Override public boolean canAdd() { return true; }

    @Override public boolean canRemove() { return false; }
}
