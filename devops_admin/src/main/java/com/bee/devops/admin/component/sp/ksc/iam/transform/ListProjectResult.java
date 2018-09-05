package com.bee.devops.admin.component.sp.ksc.iam.transform;

import java.util.ArrayList;
import java.util.List;

import com.bee.devops.admin.component.sp.model.iam.Project;

public class ListProjectResult {
	Integer total;
	List<Project> projectList = new ArrayList<>();

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

}
