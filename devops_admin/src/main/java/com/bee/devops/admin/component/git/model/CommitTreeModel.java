package com.bee.devops.admin.component.git.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.jgit.api.errors.GitAPIException;

import com.bee.devops.admin.component.git.helper.BranchHelper;
import com.bee.devops.admin.component.git.helper.CommitHelper;
import com.bee.devops.admin.component.git.helper.RepoHelper;
import com.bee.devops.admin.component.git.helper.TagHelper;

/**
 * Handles the conversion/creation of a list of commit helpers into a nice tree
 * structure. It also takes care of updating the view its given to display the
 * new tree whenever the graph is updated.
 */
public class CommitTreeModel {

	// The model from which this class pulls its commits
	SessionModel sessionModel;

	// A list of commits in this model
	private List<CommitHelper> commitsInModel;
	private List<CommitHelper> localCommitsInModel;
	private List<CommitHelper> remoteCommitsInModel;
	private List<BranchHelper> branchesInModel;
	private List<TagHelper> tagsInModel;

	// A list of tags that haven't been pushed yet
	public List<TagHelper> tagsToBePushed;

	static final Logger logger = LogManager.getLogger(CommitTreeModel.class);

	/**
	 * Constructs a new commit tree model that supplies the data for the given view
	 * 
	 * @param model
	 *            the model with which this class accesses the commits
	 * @param view
	 *            the view that will be updated with the new graph
	 */
	public CommitTreeModel(SessionModel model) {
		this.sessionModel = model;
		this.commitsInModel = new ArrayList<>();
		this.localCommitsInModel = new ArrayList<>();
		this.remoteCommitsInModel = new ArrayList<>();
		this.branchesInModel = new ArrayList<>();
	}

	/**
	 * @param repoHelper
	 *            the repository to get the branches from
	 * @return a list of all branches tracked by this model
	 */
	protected List<BranchHelper> getAllBranches(RepoHelper repoHelper) {
		return repoHelper.getBranchModel().getBranchListUntyped(BranchModel.BranchType.LOCAL);
	}

	/**
	 * @param repoHelper
	 *            the repository to get the commits from
	 * @return a list of all commits tracked by this model
	 */
	protected List<CommitHelper> getAllCommits(RepoHelper repoHelper) {
		return repoHelper.getAllCommits();
	}

	/**
	 * Initializes the treeGraph, unselects any previously selected commit, and then
	 * adds all commits tracked by this model to the tree
	 */
	public synchronized void init() {

		if (this.sessionModel.getCurrentRepoHelper() != null) {
			this.branchesInModel = this.sessionModel.getCurrentRepoHelper().getBranchModel().getAllBranches();
		}
	}

	public synchronized void update() throws GitAPIException, IOException {
		// Handles rare edge case with the RepositoryMonitor and removing repos
		if (this.sessionModel.getCurrentRepoHelper() != null) {
			// Get the changes between this model and the repo after updating the repo
			this.sessionModel.getCurrentRepoHelper().updateModel();
			UpdateModel updates = this.getChanges();

			if (!updates.hasChanges()) {
				return;
			}
			this.sessionModel.getCurrentRepoHelper().getBranchModel().updateAllBranches();
		}
	}

	/**
	 * Helper method that checks for differences between a commit tree model and a
	 * repo model
	 *
	 * @return an update model that has all the differences between these
	 */
	public UpdateModel getChanges() throws IOException {
		UpdateModel updateModel = new UpdateModel();
		RepoHelper repo = this.sessionModel.getCurrentRepoHelper();

		// Added commits are all commits in the current repo helper that aren't in the
		// model's list
		List<CommitHelper> commitsToAdd = new ArrayList<>(this.getAllCommits(this.sessionModel.getCurrentRepoHelper()));
		commitsToAdd.removeAll(this.getCommitsInModel());
		updateModel.setCommitsToAdd(commitsToAdd);

		// Removed commits are those in the model, but not in the current repo helper
		List<CommitHelper> commitsToRemove = new ArrayList<>(this.commitsInModel);
		commitsToRemove.removeAll(this.getAllCommits(this.sessionModel.getCurrentRepoHelper()));
		updateModel.setCommitsToRemove(commitsToRemove);

		// Updated commits are ones that have changed whether they are tracked locally
		// or uploaded to the server.
		// (remote-model's remote)+(model's remote-remote)+(local-model's
		// local)+(model's local-local)
		List<CommitHelper> commitsToUpdate = new ArrayList<>(this.localCommitsInModel);
		commitsToUpdate.removeAll(repo.getLocalCommits());
		updateModel.updateCommits(commitsToUpdate);
		commitsToUpdate = new ArrayList<>(repo.getLocalCommits());
		commitsToUpdate.removeAll(this.localCommitsInModel);
		updateModel.updateCommits(commitsToUpdate);
		commitsToUpdate = new ArrayList<>(this.remoteCommitsInModel);
		commitsToUpdate.removeAll(repo.getRemoteCommits());
		updateModel.updateCommits(commitsToUpdate);
		commitsToUpdate = new ArrayList<>(repo.getRemoteCommits());
		commitsToUpdate.removeAll(this.remoteCommitsInModel);
		updateModel.updateCommits(commitsToUpdate);

		commitsToUpdate = updateModel.getCommitsToUpdate();
		commitsToUpdate.removeAll(commitsToRemove);
		updateModel.setCommitsToUpdate(commitsToUpdate);

		/* ************************ BRANCHES ************************ */

		List<BranchHelper> branchesToUpdate = new ArrayList<>(this.sessionModel.getCurrentRepoHelper().getBranchModel().getAllBranches());
		Map<String, BranchHelper> currentBranchMap = new HashMap<>();
		Map<String, BranchHelper> updateBranchMap = new HashMap<>();

		for (BranchHelper branch : this.branchesInModel) {
			currentBranchMap.put(branch.getRefName(), branch);
		}
		for (BranchHelper branch : branchesToUpdate) {
			updateBranchMap.put(branch.getRefName(), branch);
		}

		// Check for added and changed branches
		for (BranchHelper branch : branchesToUpdate) {
			if (currentBranchMap.containsKey(branch.getRefName())) {
				if (currentBranchMap.get(branch.getRefName()).getCommit().getId().equals(branch.getHeadId().getName())) {
					continue;
				}
			}
			updateModel.addBranch(branch);
		}
		// Check if there are removed branches
		for (BranchHelper branch : this.branchesInModel) {
			if (!updateBranchMap.containsKey(branch.getRefName())) {
				updateModel.addBranch(branch);
			}
		}

		/* ************************ TAGS ************************ */
		List<TagHelper> tagsInRepo = new ArrayList<>(this.sessionModel.getCurrentRepoHelper().getTagModel().getAllTags());

		// Check for added tags
		for (TagHelper tag : tagsInRepo) {
			if (!this.tagsInModel.contains(tag)) {
				updateModel.addTag(tag);
			}
		}


		// Check for removed tags
		for (TagHelper tag : this.tagsInModel) {
			if (!tagsInRepo.contains(tag)) {
				updateModel.addTag(tag);
			}
		}

		return updateModel;
	}

	/**
	 * Removes a single commit from the tree
	 *
	 * @param commitHelper
	 *            the commit to remove
	 */
	public void removeCommitFromTree(CommitHelper commitHelper) {
		this.commitsInModel.remove(commitHelper);
		this.localCommitsInModel.remove(commitHelper);
		this.remoteCommitsInModel.remove(commitHelper);
	}

	public List<TagHelper> getTagsToBePushed() {
		return tagsToBePushed;
	}

	public List<CommitHelper> getCommitsInModel() {
		return this.commitsInModel;
	}

	public List<BranchHelper> getBranchesInModel() {
		return this.branchesInModel;
	}

	public List<TagHelper> getTagsInModel() {
		return this.tagsInModel;
	}
}
