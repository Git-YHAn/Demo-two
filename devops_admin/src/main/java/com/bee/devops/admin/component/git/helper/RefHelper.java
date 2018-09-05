package com.bee.devops.admin.component.git.helper;

/**
 * Parent class for branch and tag helpers
 */
public class RefHelper {
    // THe name of this ref, e.g. 'master' or 'tag1'
    protected String refName;
    // The commit that this ref points to
    protected CommitHelper commit;

    /**
     * @return the name of the ref
     */
    public String getRefName() { return this.refName; }


    /**
     * @return the commit that this ref points to, or null if it hasn't been set
     */
    public CommitHelper getCommit(){
        return commit;
    }


    /**
     * @return the name of the ref, or an abbreviated version if it's too long
     */
    public String getAbbrevName() {
        return this.refName;
    }
}
