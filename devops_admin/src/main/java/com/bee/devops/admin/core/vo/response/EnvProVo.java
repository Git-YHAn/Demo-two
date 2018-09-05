package com.bee.devops.admin.core.vo.response;

import java.util.List;

import com.bee.devops.admin.core.common.entity.ops.OpsBaseEnv;
import com.bee.devops.admin.core.common.entity.ops.OpsBasePro;

public class EnvProVo {
	private List<OpsBasePro> listPros;
	private List<OpsBaseEnv> listEnvs;
	public EnvProVo() {}
	public EnvProVo(List<OpsBasePro> listPros, List<OpsBaseEnv> listEnvs) {
		this.listPros = listPros;
		this.listEnvs = listEnvs;
	}
	public List<OpsBasePro> getListPros() {
		return listPros;
	}
	public void setListPros(List<OpsBasePro> listPros) {
		this.listPros = listPros;
	}
	public List<OpsBaseEnv> getListEnvs() {
		return listEnvs;
	}
	public void setListEnvs(List<OpsBaseEnv> listEnvs) {
		this.listEnvs = listEnvs;
	}
	
	
}
