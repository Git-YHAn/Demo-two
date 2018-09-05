package com.bee.devops.admin.core.vo.response;

import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseServer;
import com.bee.devops.admin.core.common.entity.ops.OpsVersionAppDep;

/**
 * @author Roc created on 2018/8/17
 */
public class OpsDepAppOrderInfoAppEnvVo {

    private OpsBaseServer server;

    private OpsAssembleApp appEnv;

    private OpsVersionAppDep depAppVer;

    public OpsBaseServer getServer() {
        return server;
    }

    public void setServer(OpsBaseServer server) {
        this.server = server;
    }

    public OpsAssembleApp getAppEnv() {
        return appEnv;
    }

    public void setAppEnv(OpsAssembleApp appEnv) {
        this.appEnv = appEnv;
    }

    public OpsVersionAppDep getDepAppVer() {
        return depAppVer;
    }

    public void setDepAppVer(OpsVersionAppDep depAppVer) {
        this.depAppVer = depAppVer;
    }
}
