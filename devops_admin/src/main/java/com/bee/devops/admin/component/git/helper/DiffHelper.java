package com.bee.devops.admin.component.git.helper;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.FileTreeIterator;
import org.eclipse.jgit.treewalk.filter.PathFilter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 *
 * A DiffHelper helps interact with the diff for a given file in a repository.
 * This class reads, formats, and returns the string in a ScrollPane.
 *
 *  ---
 *
 * Some code is from the JGit-Cookbook, with modification.
 *  (https://github.com/centic9/jgit-cookbook)
 *    - https://github.com/centic9/jgit-cookbook/blob/master/src/main/java/org/dstadler/jgit/porcelain/ShowChangedFilesBetweenCommits.java
 *    and
 *    - https://github.com/centic9/jgit-cookbook/blob/master/src/main/java/org/dstadler/jgit/porcelain/ShowFileDiff.java
 *
 * This is considered a derivative work under the Apache 2.0 license, under which jgit-cookbook is licensed.
 *
 */
public class DiffHelper {

    Repository repo;
    String pathFilter;

    public DiffHelper(Path relativeFilePath, RepoHelper repo) throws IOException {
        this.repo = repo.getRepo();
        this.pathFilter = relativeFilePath.toString();
    }

    public String getDiffString() throws GitAPIException, IOException {
        ObjectId head = this.repo.resolve("HEAD");
        if(head == null) {
        	return "";
        } 

        ByteArrayOutputStream diffOutputStream = new ByteArrayOutputStream();
        DiffFormatter formatter = new DiffFormatter(diffOutputStream);
        formatter.setRepository(this.repo);
        formatter.setPathFilter(PathFilter.create(this.pathFilter.replaceAll("\\\\","/")));

        AbstractTreeIterator commitTreeIterator = prepareTreeParser(this.repo, head.getName());
        FileTreeIterator workTreeIterator = new FileTreeIterator(this.repo);

        // Scan gets difference between the two iterators.
        formatter.format(commitTreeIterator, workTreeIterator);

        return diffOutputStream.toString();
    }


    public static AbstractTreeIterator prepareTreeParser(Repository repository, String objectId) throws IOException {
        // from the commit we can build the tree which allows us to construct the TreeParser
        try (RevWalk walk = new RevWalk(repository)){
            RevCommit commit = walk.parseCommit(ObjectId.fromString(objectId));
            RevTree tree = walk.parseTree(commit.getTree().getId());

            CanonicalTreeParser oldTreeParser = new CanonicalTreeParser();
            try (ObjectReader oldReader = repository.newObjectReader()){
                oldTreeParser.reset(oldReader, tree.getId());
            }

            walk.dispose();
            return oldTreeParser;
        }
    }
}
