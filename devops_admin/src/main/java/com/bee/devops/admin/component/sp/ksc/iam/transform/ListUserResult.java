package com.bee.devops.admin.component.sp.ksc.iam.transform;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.bee.devops.admin.component.sp.model.iam.User;

public class ListUserResult {
	List<User> users = new ArrayList<>();
	String Marker;
	boolean IsTruncated;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getMarker() {
		return Marker;
	}

	public void setMarker(String marker) {
		Marker = marker;
	}

	public boolean isIsTruncated() {
		return IsTruncated;
	}

	public void setIsTruncated(boolean isTruncated) {
		IsTruncated = isTruncated;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
