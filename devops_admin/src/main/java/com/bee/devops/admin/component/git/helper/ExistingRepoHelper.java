package com.bee.devops.admin.component.git.helper;

import java.io.IOException;
import java.nio.file.Path;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import com.bee.devops.admin.component.git.exceptions.CancelledAuthorizationException;

/**
 * A RepoHelper implementation for pre-existing repositories.
 */
public class ExistingRepoHelper extends RepoHelper {

    public ExistingRepoHelper(Path directoryPath, UsernamePasswordCredentialsProvider ownerAuth)
	        throws GitAPIException, IOException, CancelledAuthorizationException {
		super(directoryPath, ownerAuth);
        repo = obtainRepository();
        setup();
	}

	/**
     * Builds a repository by searching the directory for .git files
     * and then returns the JGit Repository object.
     *
     * @return the RepoHelper's associated Repository object.
     * @throws IOException if building the repository fails.
     */
    protected Repository obtainRepository() throws IOException {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        return builder.findGitDir(this.localPath.toFile())
                .readEnvironment()
                .build();
    }
}
