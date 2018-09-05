create table  `OPS_BASE_ENV`
(
       `ENV_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment '环境ID',
       `ENV_NAME`            VARCHAR(50) not null comment '环境名称',
       `ENV_CODE`            VARCHAR(10) not null comment '环境编码',
       `PRIORITY`        INT comment '优先级',
       `DESCRIPTION`     VARCHAR(200) comment '描述信息',
       `CREATE_TIME`     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
       `UPDATE_TIME`     timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
	   PRIMARY KEY (`ENV_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='发布环境类型表';

create table  `OPS_BASE_PRO`
(
       `PRO_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment '项目ID',
       `PRO_NAME`            VARCHAR(50) not null comment '项目名称',
       `PRO_CODE`            VARCHAR(50) not null comment '项目编码',
       `DESCRIPTION`     VARCHAR(255) comment '描述信息',
       `CREATE_TIME`     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
       `UPDATE_TIME`     timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
	   PRIMARY KEY (`PRO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='发布项目表';

create table  `OPS_BASE_APP`
(
       `APP_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment '应用ID',
       `PRO_ID`          INT comment '项目编码',
       `APP_NAME`            VARCHAR(50) not null comment '应用名称',
       `APP_CODE`            VARCHAR(10) not null comment '应用编码',
       `APP_TYPE`        INT comment '应用类型',
       `DESCRIPTION`     VARCHAR(255) comment '描述信息',
       `CREATE_TIME`     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
       `UPDATE_TIME`     timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
	   PRIMARY KEY (`APP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='发布应用表';

create table  `OPS_BASE_COMPONENT`
(
       `SERVICE_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment '组件ID',
       `SERVICE_NAME`            VARCHAR(50) not null comment '服务组件名称',
       `SERVICE_TYPE`    INT default 0 not null comment '类型 0为公共组件，1为私有组件',
       `IDENTIFY`        VARCHAR(50) not null comment '标识',
       `SERVICE_URL`     VARCHAR(255) not null comment '服务地址',
       `SERVICE_PORT`    VARCHAR(50) comment '服务端口',
       `SERVICE_USERNAME` VARCHAR(50) comment '用户名',
       `SERVICE_PASSWORD` VARCHAR(50) comment '密码',
       `DESCRIPTION`     VARCHAR(255) comment '描述信息',
	   PRIMARY KEY (`SERVICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='服务组件表';

create table  `DEPLOY_CONFIG_FILE`
(
       `CONFIG_FILE_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment '文件ID',
       `CONFIG_FILE_NAME`            VARCHAR(50) not null comment '文件名称',
       `FILE_URL`        VARCHAR(255) not null comment '文件路径',
       `FILE_TYPE`       VARCHAR(50) comment '文件类型',
       `DESCRIPTION`     VARCHAR(255) comment '描述信息',
       `CREATE_TIME`     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
       `APP_ENV_ID`      INT comment '应用环境表',
	   PRIMARY KEY (`CONFIG_FILE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用配置';

/*
警告: 字段名可能非法 - STATUS
*/
create table  `DEPLOY_CONFIG_FILE_ADUIT`
(
       `FILE_ADUIT_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment 'ID',
       `FILE_ID`         INT comment '文件ID',
       `OPERATE_TYPE`    INT comment '操作类型 0为新增，1为修改，2为删除',
       `OPERATE_USER_ID` INT comment '操作人ID',
       `ADUIT_USER_ID`   INT comment '审核人ID',
       `UPDATE_CONTENT`  VARCHAR(512) comment '更新内容',
       `FILE_ADUIT_STATUS`          INT comment '状态 0为未更改，1为待审核，2已审核',
       `DESCRIPTION`     VARCHAR(255) comment '描述信息',
       `ADUIT_TIME`      DATETIME(6) comment '审核时间',
       `UPDATE_TIME`     DATETIME(6) comment '更新时间',
	   PRIMARY KEY (`FILE_ADUIT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用配置审核信息表';

/*
警告: 字段名可能非法 - PASSWORD
*/
create table  `ADMIN_USER`
(
       `ADMIN_USER_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment '用户ID',
       `REAL_NAME`            VARCHAR(50) not null comment '用户姓名',
       `USERNAME`        VARCHAR(255) not null comment '登录用户名',
       `PASSWORD`        VARCHAR(255) not null comment '用户密码',
       `EMAIL`           VARCHAR(100) comment '邮箱地址',
       `MOBILE`          VARCHAR(50) comment '手机号码',
       `IS_SUPPER`       INT comment '是否为超级管理员 0为普通用户，1为超级用户',
       `IS_ACTIVE`       INT comment '用户状态 1为正常用户，0为禁用',
       `CREATE_TIME`     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
       `UPDATE_TIME`     timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
	   PRIMARY KEY (`ADMIN_USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

/*
警告: 字段名可能非法 - STATUS
*/
create table  `OPS_BASE_SERVER`
(
       `ASSETS_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment '资源ID',
       `ASSETS_NAME`            VARCHAR(50) not null comment '资源名称',
       `OPS_BASE_SERVER_TYPE`     INT not null comment '资源类型 0为物理机，1为云服务器，2为容器',
       `SSH_ADDRESS`     VARCHAR(50) comment 'SSH地址',
       `SSH_PORT`        VARCHAR(50) comment 'SSH端口',
       `STATUS`          INT comment '服务器状态',
       `CREATE_TIME`     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
       `UPDATE_TIME`     timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
	   PRIMARY KEY (`ASSETS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='服务器信息表';

create table  `OPS_BASE_SERVER_TEMP`
(
       `TPL_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment 'ID',
       `TPL_NAME`        VARCHAR(50) comment '模板名称',
       `TPL_TYPE`        INT comment '模板类型',
       `TPL_CONTENT`     VARCHAR(2048) comment '模板内容',
       `TPL_DESCRIPTION` VARCHAR(255) comment '描述信息',
	   PRIMARY KEY (`TPL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='脚本执行模板';

create table  `OPS_BASE_SERVER_EXTEND`
(
       `EXT_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment 'ID',
       `HOST_ID`         INT comment '资源ID',
       `OPERATE_SYSTEM`  VARCHAR(50) comment '操作系统',
       `MEMORY`          VARCHAR(50) comment '内存',
       `CPU`             VARCHAR(50) comment '系统CPU',
       `DISK`            VARCHAR(50) comment '磁盘大小',
       `OUTER_IP`        VARCHAR(50) comment '外网IP',
       `INNER_IP`        VARCHAR(50) comment '内网IP',
	   PRIMARY KEY (`EXT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源扩展表';

/*
警告: 字段名可能非法 - STATUS
*/
create table  `OPS_VERSION_APP`
(
       `APP_VER_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment '版本ID',
       `APP_CODE`        INT not null comment '应用编码',
       `APP_ENV_ID`      INT comment '应用ID',
       `APP_VER_NAME`            VARCHAR(50) not null comment '版本名称',
       `VERSION_CODE`    VARCHAR(10) not null comment '版本编号',
       `COMMIT_ID`       VARCHAR(10) not null comment '提交记录ID',
       `STATUS`          INT default 0 not null comment '版本状态 0为可用，1为不可用',
       `DESCRIPTION`     VARCHAR(1024) comment '描述新',
       `CREATE_TIME`     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
       `UPDATE_TIME`     timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
       `OPERATE_USER_ID` INT comment '操作员',
	   PRIMARY KEY (`APP_VER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用版本';


/*
警告: 字段名可能非法 - STATUS
*/
create table  `OPS_VERSION_CONFIG`
(
       `CONFIG_VER_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment '版本ID',
       `APP_ENV_ID`      INT comment '应用ID',
       `VERSION_NAME`            VARCHAR(50) not null comment '版本名称',
       `VERSION_CODE`    VARCHAR(10) not null comment '版本编号',
       `COMMIT_ID`       VARCHAR(10) not null comment '提交记录ID',
       `STATUS`          INT default 0 not null comment '版本状态 0为可用，1为不可用',
       `DESCRIPTION`     VARCHAR(1024) comment '描述新',
       `CREATE_TIME`     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
       `UPDATE_TIME`     timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
       `OPERATE_USER_ID` INT comment '操作员',
	   PRIMARY KEY (`CONFIG_VER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='配置版本';


/*
警告: 字段名可能非法 - STATUS
*/
create table  `OPS_VERSION_APP_DEP`
(
       `DEP_APP_VER_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment '版本ID',
       `APP_ENV_ID`      INT comment '应用编码',
       `APP_VERSION_ID`  INT not null comment '应用版本ID',
       `OPS_VERSION_CONFIG_ID` INT not null comment '配置版本ID',
       `VERSION_CODE`    VARCHAR(50) not null comment '发布版本CODE',
       `COMMIT_ID`       VARCHAR(10) not null comment '提交记录ID',
       `STATUS`          INT default 0 not null comment '版本状态 0为可用，1为不可用',
       `DESCRIPTION`     VARCHAR(1024) comment '描述新',
       `CREATE_TIME`     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
       `UPDATE_TIME`     timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
       `OPERATE_USER_ID` INT comment '操作员',
	   PRIMARY KEY (`DEP_APP_VER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用发布版本';


/*
警告: 字段名可能非法 - STATUS
*/
create table  `OPS_BASE_APP_HIS`
(
       `APP_HIS_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment 'ID',
       `APP_ENV_ID`      INT comment '应用环境ID',
       `OPS_VERSION_APP_DEP_CODE` VARCHAR(50) not null comment '发布包版本号',
       `APP_VERSION_CODE` VARCHAR(50) not null comment '应用包版本号',
       `OPS_VERSION_CONFIG_CODE` VARCHAR(50) not null comment '配置包版本号',
       `DEPLOY_TIME`     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
       `OPERATE_TYPE`    INT not null comment '操作类型 0新发布，1回滚，2重启',
       `STATUS`          INT not null comment '发布状态 0失败，1成功',
       `DESCRIPTION`     VARCHAR(1024) comment '描述信息',
       `OPERATE_USER_ID` INT comment '操作员',
	   PRIMARY KEY (`APP_HIS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用发布历史信息表';


create table  `DEPLOY_JOBS`
(
       `DEP_JOB_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment '任务ID',
       `DEP_JOB_NAME`            VARCHAR(50) comment '任务名称',
       `OPERATE_TYPE`    INT comment '操作类型',
       `ENABLED`         INT comment '是否可用',
       `DESCRIPTION`     VARCHAR(255) comment '描述信息',
	   PRIMARY KEY (`DEP_JOB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='执行任务表';

/*
警告: 字段名可能非法 - STATUS
*/
create table  `DEPLOY_JOBS_HISTORY`
(
       `DEP_JOB_HIS_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment 'ID',
       `JOB_ID`          INT comment '任务ID',
       `START_TIME`      timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
       `END_TIME`        timestamp NULL COMMENT '结束时间',
       `EXEC_TIME`       VARCHAR(50) comment '执行时间',
       `STATUS`          INT not null comment '执行结果',
       `DESCRIPTION`     VARCHAR(255) comment '描述信息',
       `OPERATE_USER_ID` INT comment '操作员',
	   PRIMARY KEY (`DEP_JOB_HIS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务执行历史信息';


create table  `OPS_ASSEMBLE_APP`
(
       `APP_ENV_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment '编号',
       `APP_ID`          INT comment '应用ID',
       `ENV_ID`          INT comment '环境ID',
       `CREATE_TIME`     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
       `UPDATE_TIME`     timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
       `CURRENT_VERSION` VARCHAR(50) comment '当前版本',
       `CONFIG_GIT_URL`  VARCHAR(255) comment '配置版本库地址',
       `APP_GIT_URL`     VARCHAR(255) comment '应用版本库地址',
       `DEPLOY_APP_GIT_URL` VARCHAR(255) comment '发布版本库地址',
	   PRIMARY KEY (`APP_ENV_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用环境表';


create table  `OPS_BASE_ENV_SERVER`
(
       `HOST_APPENV_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment '编号ID',
       `HOST_ID`         INT comment '服务器ID',
       `APP_ENV_ID`      INT comment '应用环境ID',
	   PRIMARY KEY (`HOST_APPENV_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='服务器与应用环境关系表';


create table  `DEPLOY_JOB_APPENV_REL`
(
       `JOB_APPENV_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment '编号',
       `JOB_ID`          INT comment '任务ID',
       `APP_ENV_ID`      INT comment '名称',
	   PRIMARY KEY (`JOB_APPENV_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务与应用环境关系表';


create table  `DEPLOY_SERVICE_APPENV_REL`
(
       `SER_APPENV_ID`              int(10) unsigned NOT NULL AUTO_INCREMENT comment '编号',
       `SERVICE_ID`      INT comment '服务ID',
       `APP_ENV_ID`      INT comment '应用环境ID',
	   PRIMARY KEY (`SER_APPENV_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='服务与应用环境关系表';

/**
 * 资源类型表
 */
create table OPS_BASE_SERVER_TYPE
(
	OPS_BASE_SERVER_TYPE_ID int primary key auto_increment,
	OPS_BASE_SERVER_TYPE_NAME varchar(30) not null
)

/**
 * 服务器id以及对应的密码表
 */
create table ASSETS_HOST_ACCOUNT
(
	ID INT PRIMARY KEY auto_increment,
	HOST_ID INT NOT NULL,
	HOST_ACCOUNT INT NOT NULL,
	HOST_PASSWORD INT NOT NULL
)


create table HOST_APP_ENV
(
	HOST_APP_ENV_ID int primary key auto_increment,
	HOST_ID int not null,
	APP_ID int not null,
	ENV_ID int not null
)