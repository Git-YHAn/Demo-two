package com.bee.devops.admin.component.git.exceptions;

/**
 * An exception thrown when a pushed tag points to a commit
 * that has not been pushed.
 */
public class TagPointsToUnpushedCommitException extends Exception {
}
