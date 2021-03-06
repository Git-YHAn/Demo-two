package com.bee.devops.admin.component.git.file;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.bee.devops.admin.component.git.helper.RepoHelper;

/**
 * A subclass of RepoFile that contains a file that Git is ignoring.
 */
public class StagedAndModifiedRepoFile extends RepoFile {

    private StagedAndModifiedRepoFile(Path filePath, RepoHelper repo) {
        super(filePath, repo);
    }

    public StagedAndModifiedRepoFile(String filePathString, RepoHelper repo) {
        this(Paths.get(filePathString), repo);
    }

    @Override public boolean canAdd() {
        return true;
    }

    @Override public boolean canRemove() {
        return true;
    }
}
