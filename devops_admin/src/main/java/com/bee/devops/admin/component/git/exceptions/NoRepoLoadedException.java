package com.bee.devops.admin.component.git.exceptions;

/**
 * Exception for when there isn't a repository loaded and the user
 * tries to perform an operation on the current repo
 */
public class NoRepoLoadedException extends Exception{
}
