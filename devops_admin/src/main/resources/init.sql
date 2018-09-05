/*
Navicat MySQL Data Transfer

Source Server         : G0-G1
Source Server Version : 50630
Source Host           : 192.168.100.130:32000
Source Database       : E08P00_G0_ADMIN

Target Server Type    : MYSQL
Target Server Version : 50630
File Encoding         : 65001

Date: 2018-09-05 17:17:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ADMIN_INTERFACE`
-- ----------------------------
DROP TABLE IF EXISTS `ADMIN_INTERFACE`;
CREATE TABLE `ADMIN_INTERFACE` (
  `INTERFACE_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '接口ID',
  `INTERFACE_NAME` varchar(255) DEFAULT NULL COMMENT '接口名',
  `INTERFACE_URL` varchar(255) DEFAULT NULL COMMENT '接口地址',
  PRIMARY KEY (`INTERFACE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ADMIN_INTERFACE
-- ----------------------------
INSERT INTO `ADMIN_INTERFACE` VALUES ('1', '带分页模糊查询应用环境', '/appenv/search/appenvs/\\d+/\\d+/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('2', '根据应用环境ID查询应用环境', '/appenv/query/byid/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('3', '查询所有可用服务器', '/assets/query/all');
INSERT INTO `ADMIN_INTERFACE` VALUES ('4', '查询可选的微服务类型', '/appenv/query/ms/types');
INSERT INTO `ADMIN_INTERFACE` VALUES ('5', '修改应用环境', '/appenv/update');
INSERT INTO `ADMIN_INTERFACE` VALUES ('6', '查询应用环境配置的服务器', '/assets/query/servers/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('7', '应用环境配置服务器', '/assets/set/appenv');
INSERT INTO `ADMIN_INTERFACE` VALUES ('8', '应用上传', '/appenv/zip/upload');
INSERT INTO `ADMIN_INTERFACE` VALUES ('10', '查询需要审核的应用,即状态为已经提交的应用', '/appconfig/record/search/commit/\\d+/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('11', '查询项目下的所有应用', '/app/query/pro/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('12', '应用管理查看应用被动', '/appconfig/record/query/diffs/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('13', '应用审核是否通过', '/appconfig/record/commit/audit');
INSERT INTO `ADMIN_INTERFACE` VALUES ('14', '查询应用审核的历史', '/appconfig/record/search/user/\\d+/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('15', '查询当前登录用户信息', '/user/info');
INSERT INTO `ADMIN_INTERFACE` VALUES ('16', '分页查询项目列表', '/pro/query/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('17', '查询所有环境', '/env/query/all');
INSERT INTO `ADMIN_INTERFACE` VALUES ('18', '查询所有项目', '/pro/query/all');
INSERT INTO `ADMIN_INTERFACE` VALUES ('19', '查询启用及禁用用户数量', '/user/count/info');
INSERT INTO `ADMIN_INTERFACE` VALUES ('20', '获取发布应用数量', '/order/publish/search/status');
INSERT INTO `ADMIN_INTERFACE` VALUES ('21', '查询服务器的使用数量', '/assets/search/use/status');
INSERT INTO `ADMIN_INTERFACE` VALUES ('22', '获取服务器应用状态', '/assets/status/search/\\d+/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('23', '获取首页面板统计', '/pro/select/dashboard/\\d+/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('24', '获取应用发布数据', '/order/publish/app/status/.*/\\d+/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('25', 'Dashboard', '/app/instance/search/\\d+/\\d+/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('38', '按类型查询服务器', '/assets/query/bytype/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('41', '应用重启', '/appenv/restart');
INSERT INTO `ADMIN_INTERFACE` VALUES ('42', '停止应用', '/appenv/stop');
INSERT INTO `ADMIN_INTERFACE` VALUES ('43', '查询应用', '/appenv/query/envent/\\d+/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('44', '根据名称或者SSH地址查询服务器信息', '/assets/query/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('46', '添加服务器', '/assets/add');
INSERT INTO `ADMIN_INTERFACE` VALUES ('47', '验证服务器连接', '/assets/verify');
INSERT INTO `ADMIN_INTERFACE` VALUES ('48', '修改服务器信息', '/assets/update');
INSERT INTO `ADMIN_INTERFACE` VALUES ('49', '根据id查询服务器信息', '/assets/select/byid/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('50', '删除服务器', '/assets/delete/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('51', '查询可以初始化的服务器', '/assets/query/uninitialized');
INSERT INTO `ADMIN_INTERFACE` VALUES ('52', '初始化服务器', '/assets/init');
INSERT INTO `ADMIN_INTERFACE` VALUES ('53', '执行脚本命令', '/assets/execute');
INSERT INTO `ADMIN_INTERFACE` VALUES ('54', '根据key获取服务器返回结果', '/assets/getResult.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('55', '根据key取消获取结果', '/assets/cancel.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('56', '查询服务器模板', '/temp/query');
INSERT INTO `ADMIN_INTERFACE` VALUES ('57', '添加服务器模板', '/temp/add');
INSERT INTO `ADMIN_INTERFACE` VALUES ('58', '更新服务器模板', '/temp/update');
INSERT INTO `ADMIN_INTERFACE` VALUES ('59', '删除服务器模板', '/temp/delete/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('60', '查询服务器执行脚本历史', '/exec/his/query');
INSERT INTO `ADMIN_INTERFACE` VALUES ('62', '添加项目', '/pro/add');
INSERT INTO `ADMIN_INTERFACE` VALUES ('63', '通过ID查询项目', '/pro/query/byid/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('64', '更新项目信息', '/pro/update');
INSERT INTO `ADMIN_INTERFACE` VALUES ('65', '添加应用', '/app/add');
INSERT INTO `ADMIN_INTERFACE` VALUES ('66', '查询发布应用', '/app/query/apptypes');
INSERT INTO `ADMIN_INTERFACE` VALUES ('67', '通过ID删除项目', '/pro/delete/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('68', '修改应用', '/app/update');
INSERT INTO `ADMIN_INTERFACE` VALUES ('69', '根据ID查询应用', '/app/query/byid/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('70', '根据ID删除应用', '/app/delete/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('71', '通过名称分页查询环境', '/env/query/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('72', '根据环境ID查询该环境下的项目', '/pro/query/appenv/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('74', '添加环境', '/environ/add');
INSERT INTO `ADMIN_INTERFACE` VALUES ('75', '通过ID查询环境', '/env/query/byid/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('76', '更新环境信息', '/environ/update');
INSERT INTO `ADMIN_INTERFACE` VALUES ('77', '通过ID删除环境', '/env/delete/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('78', '查询该环境没有配置的应用', '/app/query/noset/\\d+/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('79', '应用环境保存', '/environ/setapp');
INSERT INTO `ADMIN_INTERFACE` VALUES ('80', '查询项目环境下的应用', '/app/query/appenv/\\d+/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('81', '查询分支', '/deployconfig_version/query/app/branches/\\d+/\\d+/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('82', '创建分支', '/deployconfig_version/checkout/app/branch/\\d+/\\d+/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('83', '查询分支文件列表', '/deployconfig_version/query/branch/files/\\d+/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('84', '获取配置文件内容', '/deployconfig_version/file/content');
INSERT INTO `ADMIN_INTERFACE` VALUES ('85', '删除本地分支', '/deployconfig_version/drop/branch/\\d+/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('86', '获取主分支配置文件内容', '/deployconfig_version/file/content/origin');
INSERT INTO `ADMIN_INTERFACE` VALUES ('87', '新增配置文件', '/deployconfig_version/file/add');
INSERT INTO `ADMIN_INTERFACE` VALUES ('88', '修改配置文件', '/deployconfig_version/file/content/save');
INSERT INTO `ADMIN_INTERFACE` VALUES ('89', '删除配置文件', '/deployconfig_version/file/delete');
INSERT INTO `ADMIN_INTERFACE` VALUES ('90', '查询当前分支未推送文件变动', '/deployconfig_version/show/staged/diff/\\d+/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('91', '推送并删除本地分支', '/deployconfig_version/push/branch');
INSERT INTO `ADMIN_INTERFACE` VALUES ('92', '获取已提交的记录', '/config/record/search/commit/\\d+/\\d+/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('93', '审核配置提交记录', '/config/record/commit/audit');
INSERT INTO `ADMIN_INTERFACE` VALUES ('94', '据用户查询配置文件修改记录', '/config/record/search/user/\\d+/\\d+/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('96', '查询变更文件', '/config/record/query/diffs/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('97', '撤回已提交审核的分支', '/config/record/commit/withdrawn');
INSERT INTO `ADMIN_INTERFACE` VALUES ('102', '查询指定环境的应用版本', '/app_version/search/\\d+/\\d+/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('103', '创建应用版本', '/app_version/save');
INSERT INTO `ADMIN_INTERFACE` VALUES ('104', '创建配置版本', '/config_version/create');
INSERT INTO `ADMIN_INTERFACE` VALUES ('105', '查询指定环境的配置版本', '/config_version/search/\\d+/\\d+/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('106', '查询发布版本列表', '/release/search/\\d+/\\d+/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('107', '通过版本ID获取应用版本', '/app_version/query/appver/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('108', '通过版本ID获取配置版本', '/config_version/query/configver/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('109', '查询应用版本', '/app_version/search/byid/\\d+/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('110', '通过Id查询配置版本', '/config_version/search/byid/\\d+/\\d+/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('111', '创建发布版本', '/release/create');
INSERT INTO `ADMIN_INTERFACE` VALUES ('112', '查询待制作的版本', '/release/apps/wait/\\d+/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('113', '查询正在制作的发布版本', '/release/query/\\d+/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('114', '更改制作状态为完成', '/release/change/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('115', '查询发布应用历史', '/publish/history/search/\\d+/\\d+/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('116', '根据项目ID和环境ID查询所有待发布的应用', '/appenv/publish/apps/wait/\\d+/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('117', '根据项目ID和环境ID查询所有待发布的应用', '/appenv/publish/apps/waits/\\d+/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('118', '根据项目ID和环境ID查询正在发布的应用', '/appenv/publish/apps/\\d+/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('119', '根据项目ID和环境ID查询常规发布的应用', '/appenv/publish/apps/phy/\\d+/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('120', '根据项目ID和环境ID查询容器发布的应用', '/appenv/publish/apps/con/\\d+/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('121', '保存发布应用', '/appenv/publish/save');
INSERT INTO `ADMIN_INTERFACE` VALUES ('122', '重新发布应用', '/appenv/publish/republish');
INSERT INTO `ADMIN_INTERFACE` VALUES ('123', '应用归档', '/appenv/publish/archive');
INSERT INTO `ADMIN_INTERFACE` VALUES ('124', '取消应用发布', '/appenv/publish/cancel');
INSERT INTO `ADMIN_INTERFACE` VALUES ('125', '获取用户列表', '/user/search/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('126', '通过用户ID查询用户', '/user/get/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('127', '添加用户', '/user/add');
INSERT INTO `ADMIN_INTERFACE` VALUES ('128', '更新用户信息', '/user/update');
INSERT INTO `ADMIN_INTERFACE` VALUES ('129', '通过用户ID删除用户', '/user/delete/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('130', '查询用户角色', '/user/query/role/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('131', '保存用户角色', '/user/save/role');
INSERT INTO `ADMIN_INTERFACE` VALUES ('132', '查询所有角色', '/role/all');
INSERT INTO `ADMIN_INTERFACE` VALUES ('133', '查询角色列表', '/role/list/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('134', '添加角色', '/role/addRole');
INSERT INTO `ADMIN_INTERFACE` VALUES ('135', '删除角色', '/role/delete/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('136', '修改角色', '/role/update');
INSERT INTO `ADMIN_INTERFACE` VALUES ('137', '角色添加资源', '/role/update/resource');
INSERT INTO `ADMIN_INTERFACE` VALUES ('138', '根据角色ID获取资源ID', '/sys/resource/get/role/resource/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('139', '获取资源树', '/sys/resource/get/tree');
INSERT INTO `ADMIN_INTERFACE` VALUES ('140', '添加资源', '/sys/resource/add/resource');
INSERT INTO `ADMIN_INTERFACE` VALUES ('141', '编辑资源', '/sys/resource/edit/resource');
INSERT INTO `ADMIN_INTERFACE` VALUES ('142', '删除资源', '/sys/resource/delete/resource');
INSERT INTO `ADMIN_INTERFACE` VALUES ('144', '查询所有可配置的系统参数', '/sys/param/search');
INSERT INTO `ADMIN_INTERFACE` VALUES ('146', '根据页面返回的系统参数项进行更新', '/sys/param/update');
INSERT INTO `ADMIN_INTERFACE` VALUES ('148', '根据项目ID和环境ID查询工单列表', '/order/publish/search/orders/\\d+/\\d+/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('149', '根据工单ID查询工单详细信息', '/order/publish/search/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('150', '根据工单详细信息ID查询工单详细信息', '/order/publish/search/orderinfo/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('151', '根据项目ID和环境ID查询所有应用', '/order/publish/search/apps/\\d+/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('152', '制定发布工单', '/order/publish/insert');
INSERT INTO `ADMIN_INTERFACE` VALUES ('153', '根据工单ID删除工单', '/order/publish/delete/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('155', '发布工单中自动发布功能', '/order/publish/save');
INSERT INTO `ADMIN_INTERFACE` VALUES ('156', '发布工单中部署代码功能', '/order/publish/deploy/code');
INSERT INTO `ADMIN_INTERFACE` VALUES ('157', '发布工单中重启服务功能', '/order/publish/restart/service');
INSERT INTO `ADMIN_INTERFACE` VALUES ('158', '查询日志类型列表', '/sys/log/search/type');
INSERT INTO `ADMIN_INTERFACE` VALUES ('159', '修改日志是否启用', '/sys/log/change/type');
INSERT INTO `ADMIN_INTERFACE` VALUES ('160', '查询日志列表', '/sys/log/search/record/\\d+/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('161', '查询实例', '/ksyun/kec/search/instance/\\d+/\\d+.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('162', '查询机型配置信息', '/ksyun/kec/search//instance/familys');
INSERT INTO `ADMIN_INTERFACE` VALUES ('163', '开启实例', '/ksyun/kec/start/instance');
INSERT INTO `ADMIN_INTERFACE` VALUES ('164', '关闭实例', '/ksyun/kec/stop/instance');
INSERT INTO `ADMIN_INTERFACE` VALUES ('165', '重启实例', '/ksyun/kec/reboot/instance');
INSERT INTO `ADMIN_INTERFACE` VALUES ('166', '重装系统', '/ksyun/kec/modify/instance/image');
INSERT INTO `ADMIN_INTERFACE` VALUES ('167', '镜像字典', '/ksyun/kec/search/image/dic');
INSERT INTO `ADMIN_INTERFACE` VALUES ('168', '查询镜像', '/ksyun/kec/search/image.*');
INSERT INTO `ADMIN_INTERFACE` VALUES ('169', '添加模块', '/module/add');
INSERT INTO `ADMIN_INTERFACE` VALUES ('170', '编辑模块', '/module/edit');
INSERT INTO `ADMIN_INTERFACE` VALUES ('171', '根据ID删除模块', '/module/delete');
INSERT INTO `ADMIN_INTERFACE` VALUES ('172', '获取接口列表', '/uri/list/\\d+/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('173', '新增接口', '/uri/add');
INSERT INTO `ADMIN_INTERFACE` VALUES ('174', '编辑接口', '/uri/edit');
INSERT INTO `ADMIN_INTERFACE` VALUES ('175', '根据ID删除接口', '/uri/delete');
INSERT INTO `ADMIN_INTERFACE` VALUES ('176', '根据ID获取接口详情', '/uri/get/\\d+');
INSERT INTO `ADMIN_INTERFACE` VALUES ('177', '添加资源与接口的关联关系', '/sys/resource/rel/uri');

-- ----------------------------
-- Table structure for `ADMIN_LOGIN_LIMIT`
-- ----------------------------
DROP TABLE IF EXISTS `ADMIN_LOGIN_LIMIT`;
CREATE TABLE `ADMIN_LOGIN_LIMIT` (
  `BAN_ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `LOGIN_IP` varchar(255) DEFAULT NULL COMMENT '登陆IP',
  `LOGIN_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次登录时间',
  `LOGIN_USERNAME` varchar(255) DEFAULT NULL COMMENT '最后一次登陆错误的用户名',
  `LOGIN_NUM` int(10) DEFAULT '0' COMMENT '登陆次数 5:禁用此IP 登录成功则归0',
  PRIMARY KEY (`BAN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ADMIN_LOGIN_LIMIT
-- ----------------------------
INSERT INTO `ADMIN_LOGIN_LIMIT` VALUES ('1', '192.168.50.37', '2018-08-31 20:15:38', 'yh2386', '0');
INSERT INTO `ADMIN_LOGIN_LIMIT` VALUES ('2', '172.30.73.0', '2018-09-05 15:21:03', 'admin', '0');
INSERT INTO `ADMIN_LOGIN_LIMIT` VALUES ('3', '192.168.50.33', '2018-09-05 16:31:51', 'admin', '0');
INSERT INTO `ADMIN_LOGIN_LIMIT` VALUES ('4', '172.30.73.1', '2018-09-05 15:55:18', 'admin', '0');
INSERT INTO `ADMIN_LOGIN_LIMIT` VALUES ('5', '192.168.50.35', '2018-09-05 15:30:38', 'admin', '0');
INSERT INTO `ADMIN_LOGIN_LIMIT` VALUES ('6', '192.168.50.31', '2018-09-05 16:42:42', 'admin', '0');

-- ----------------------------
-- Table structure for `ADMIN_MODULE`
-- ----------------------------
DROP TABLE IF EXISTS `ADMIN_MODULE`;
CREATE TABLE `ADMIN_MODULE` (
  `MODULE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MODULE_NAME` varchar(50) NOT NULL COMMENT '模块名称',
  `MODULE_CODE` varchar(50) NOT NULL COMMENT '模块编码，录入后不可修改，字段值唯一',
  `MODULE_URL` varchar(500) DEFAULT NULL COMMENT '模块首页地址',
  `PARENT_ID` bigint(20) DEFAULT NULL COMMENT '父模块ID',
  `IS_ENABLE` int(11) DEFAULT NULL COMMENT '模块是否可用 1:是 0:否\r\n接口权限校验时校验该模块是否可用\r\n前端查询模块资源时若不可用则不返回该模块及模块下所有资源',
  `IS_SHOW` int(11) DEFAULT NULL COMMENT '模块是否显示 1:是 0:否\r\n前端是否显示该模块\r\n接口返回模块资源时返回改字段',
  `ADD_CHILD` int(11) DEFAULT NULL COMMENT '是否可以新增/删除子模块 1:是 0:否',
  PRIMARY KEY (`MODULE_ID`),
  UNIQUE KEY `MODULE_CODE` (`MODULE_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='本表不可新增不可删除，模块编码不可修改';

-- ----------------------------
-- Records of ADMIN_MODULE
-- ----------------------------
INSERT INTO `ADMIN_MODULE` VALUES ('1', '项目', 'MODULE_PROJECT', '', '0', '1', '1', '1');
INSERT INTO `ADMIN_MODULE` VALUES ('2', '乐米彩票', 'MODULE_LEMI_DEV', '/LEMI/DEV', '1', '1', '1', '0');
INSERT INTO `ADMIN_MODULE` VALUES ('3', '乐米彩票MST环境', 'MODULE_LEMI_MST', '/LEMI/MST', '1', '1', '0', '0');
INSERT INTO `ADMIN_MODULE` VALUES ('4', '乐仑炫彩', 'MODULE_LELUN_DEV', '/LELUN/DEV', '1', '1', '1', '0');
INSERT INTO `ADMIN_MODULE` VALUES ('5', '乐仑炫彩MST环境', 'MODULE_LELUN_MST', '/LELUN/MST', '1', '1', '0', '0');
INSERT INTO `ADMIN_MODULE` VALUES ('6', '资源', 'MODULE_ASSET', '/asset', '0', '1', '1', '1');
INSERT INTO `ADMIN_MODULE` VALUES ('7', '系统管理', 'MODULE_SYSTEM', '', '0', '1', '1', '1');
INSERT INTO `ADMIN_MODULE` VALUES ('8', '项目管理', 'MODULE_SYSTEM_PRO', '/system/project', '7', '1', '1', null);
INSERT INTO `ADMIN_MODULE` VALUES ('9', '环境管理', 'MODULE_SYSTEM_ENV', '/system/environment', '7', '1', '1', null);
INSERT INTO `ADMIN_MODULE` VALUES ('11', '系统设置', 'MODULE_SYSTEM_PARAM', '/system/setting', '7', '1', '1', null);
INSERT INTO `ADMIN_MODULE` VALUES ('12', '日志管理', 'MODULE_SYSTEM_LOG', '/system/log', '7', '1', '1', null);
INSERT INTO `ADMIN_MODULE` VALUES ('13', '', '', null, null, null, null, null);

-- ----------------------------
-- Table structure for `ADMIN_RESOURCE`
-- ----------------------------
DROP TABLE IF EXISTS `ADMIN_RESOURCE`;
CREATE TABLE `ADMIN_RESOURCE` (
  `RESOURCE_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `RESOURCE_NAME` varchar(200) DEFAULT NULL COMMENT '资源名称（显示用）',
  `RESOURCE_CODE` varchar(200) DEFAULT NULL COMMENT '资源编码（前端页面元素权限控制）,字段值唯一,命名规则:模块_页面_元素',
  `RESOURCE_URL` varchar(500) DEFAULT NULL COMMENT '资源地址（页面地址/接口地址）',
  `RESOURCE_ICON` varchar(50) DEFAULT NULL COMMENT 'fontawesome图标类名',
  `PARENT_ID` bigint(20) DEFAULT NULL COMMENT '父级资源ID',
  `MODULE_ID` bigint(20) DEFAULT NULL COMMENT '模块ID',
  `IS_ENABLE` int(11) DEFAULT NULL COMMENT '资源是否可用 1:是 0:否\r\n模块资源接口中若该资源不可用则不返回该资源\r\n后端接口权限校验时，若该资源不可用则权限验证不通过',
  `IS_SHOW` int(11) DEFAULT NULL COMMENT '资源是否显示 1:是 0:否\r\n前端是否显示该模块\r\n接口返回模块资源时返回改字段',
  `RESOURCE_ORDER` int(11) DEFAULT NULL COMMENT '资源排序 ASC',
  `RESOURCE_TYPE` int(11) NOT NULL COMMENT '资源类型 1:目录 2:页面 3:页面元素 4:接口\r\n其中接口资源类型URL使用正则表达式',
  PRIMARY KEY (`RESOURCE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ADMIN_RESOURCE
-- ----------------------------
INSERT INTO `ADMIN_RESOURCE` VALUES ('1', '应用管理', null, null, 'fa-th-large', '0', '2', '1', '1', '1', '1');
INSERT INTO `ADMIN_RESOURCE` VALUES ('2', '应用列表', '', 'E00P00/DEV/assemble/manage', 'fa-remove', '1', '2', '1', '1', '1', '2');
INSERT INTO `ADMIN_RESOURCE` VALUES ('3', '查询', 'ASSMBLE_MANAGE_SEARCH', '', null, '2', '2', '1', '1', '1', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('4', '详情', 'ASSMBLE_MANAGE_DETAIL', null, null, '2', '2', '1', '1', '2', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('5', '编辑', 'ASSMBLE_MANAGE_EDIT', null, null, '2', '2', '1', '1', '3', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('6', '服务器配置', 'ASSMBLE_MANAGE_CONFIG', null, null, '2', '2', '1', '1', '4', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('7', '应用上传', 'ASSMBLE_MANAGE_UPLOAD', null, null, '2', '2', '1', '1', '5', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('17', '应用审核', null, '/E00P00/DEV/assemble/audit', 'fa-remove', '1', '2', '1', '1', '2', '2');
INSERT INTO `ADMIN_RESOURCE` VALUES ('18', '通过', 'ASSMBLE_AUDIT_PASS', null, null, '17', '2', '1', '1', '1', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('19', '不通过', 'ASSMBLE_AUDIT_NOPASS', null, null, '17', '2', '1', '1', '2', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('20', '审核历史', null, '/E00P00/DEV/assemble/record', 'fa-remove', '1', '2', '1', '1', '3', '2');
INSERT INTO `ADMIN_RESOURCE` VALUES ('21', '配置管理', null, null, 'fa-th-large', '0', '2', '1', '1', '1', '1');
INSERT INTO `ADMIN_RESOURCE` VALUES ('22', '查看配置', null, '/E00P00/DEV/config/select', null, '21', '2', '1', '1', '1', '2');
INSERT INTO `ADMIN_RESOURCE` VALUES ('23', '修改配置', null, '/E00P00/DEV/config/edit', null, '21', '2', '1', '1', '1', '2');
INSERT INTO `ADMIN_RESOURCE` VALUES ('24', '审核配置', null, '/E00P00/DEV/config/audit', null, '21', '2', '1', '1', '1', '2');
INSERT INTO `ADMIN_RESOURCE` VALUES ('25', '审核历史', null, '/E00P00/DEV/config/record', null, '21', '2', '1', '1', '1', '2');
INSERT INTO `ADMIN_RESOURCE` VALUES ('26', '版本管理', null, null, 'fa-th-large', '0', '2', '1', '1', '1', '1');
INSERT INTO `ADMIN_RESOURCE` VALUES ('27', '应用版本', null, '/E00P00/DEV/version/application', null, '26', '2', '1', '1', '1', '2');
INSERT INTO `ADMIN_RESOURCE` VALUES ('28', '配置版本', null, '/E00P00/DEV/version/config', null, '26', '2', '1', '1', '1', '2');
INSERT INTO `ADMIN_RESOURCE` VALUES ('29', '发布版本', null, '/E00P00/DEV/version/release', null, '26', '2', '1', '1', '1', '2');
INSERT INTO `ADMIN_RESOURCE` VALUES ('30', '首页', null, null, null, '0', '2', '1', '1', '1', '2');
INSERT INTO `ADMIN_RESOURCE` VALUES ('31', 'Dashboard', null, '/E00P00/DEV/', null, '0', '2', '1', '1', '1', '2');
INSERT INTO `ADMIN_RESOURCE` VALUES ('32', '新建分支', 'CONFIG_EDIT_NEW_BRANCH', null, null, '23', '2', '1', '1', '1', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('33', '删除分支', 'CONFIG_EDIT_DELETE_BRANCH', null, null, '23', '2', '1', '1', '2', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('34', '变更记录', 'CONFIG_EDIT_CHANGE_INFO', null, null, '23', '2', '1', '1', '3', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('35', '对比', 'CONFIG_EDIT_SHOW_DIFF', null, null, '23', '2', '1', '1', '3', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('36', '新增', 'CONFIG_EDIT_ADD', null, null, '23', '2', '1', '1', '4', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('37', '保存', 'CONFIG_EDIT_SAVE', null, null, '23', '2', '1', '1', '5', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('38', '删除', 'CONFIG_EDIT_DELETE', null, null, '23', '2', '1', '1', '6', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('39', '提交审核', 'CONFIG_EDIT_COMMIT_AUDIT', null, null, '23', '2', '1', '1', '7', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('40', '查看变动', 'CONFIG_AUDIT_CHANGE_INFO', null, null, '24', '2', '1', '1', '1', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('41', '通过', 'CONFIG_AUDIT_PASS', null, null, '24', '2', '1', '1', '2', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('42', '不通过', 'CONFIG_AUDIT_NOPASS', null, null, '24', '2', '1', '1', '3', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('43', '查看变动', 'CONFIG_RECORD_CHANGE_INFO', null, null, '25', '2', '1', '1', '1', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('44', '撤回', 'CONFIG_RECORD_WITHDRAW', null, null, '25', '2', '1', '1', '2', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('45', '查询', 'VERSION_APPLICATION_SEARCH', null, null, '27', '2', '1', '1', '1', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('46', '新增', 'VERSION_APPLICATION_ADD', null, null, '27', '2', '1', '1', '2', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('47', '版本信息', 'VERSION_APPLICATION_INFO', null, null, '27', '2', '1', '1', '3', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('48', '更改记录', 'VERSION_APPLICATION_CHANGE_INFO', null, null, '27', '2', '1', '1', '4', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('49', '查询', 'VERSION_CONFIGURE_SEARCH', null, null, '28', '2', '1', '1', '1', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('50', '新增', 'VERSION_CONFIGURE_ADD', null, null, '28', '2', '1', '1', '2', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('51', '版本信息', 'VERSION_CONFIGURE_INFO', null, null, '28', '2', '1', '1', '3', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('52', '更改记录', 'VERSION_CONFIGURE_CHANGE_INFO', null, null, '28', '2', '1', '1', '4', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('53', '查询', 'VERSION_RELEASE_SEARCH', null, null, '29', '2', '1', '1', '1', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('54', '新增', 'VERSION_RELEASE_ADD', null, null, '29', '2', '1', '1', '2', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('55', '版本信息', 'VERSION_RELEASE_INFO', null, null, '29', '2', '1', '1', '3', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('56', '更改记录', 'VERSION_RELEASE_CHANGE_INFO', null, null, '29', '2', '1', '1', '4', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('57', '查询', 'DASHBOARD_SEARCH', null, null, '31', '2', '1', '1', '1', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('58', '运行日志', 'DASHBOARD_RUNNING_LOG', null, null, '31', '2', '1', '1', '2', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('59', '并行重启', 'DASHBOARD_PARALLEL_RESTART', null, null, '31', '2', '1', '1', '3', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('60', '顺序重启', 'DASHBOARD_ORDER_RESTART', null, null, '31', '2', '1', '1', '4', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('61', '停止', 'DASHBOARD_STOP', null, null, '31', '2', '1', '1', '5', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('62', '日志下载', 'DASHBOARD_LOG_DOWNLOAD', null, null, '31', '2', '1', '1', '6', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('63', '创建发布工单', 'DASHBOARD_CREATE_ORDER', null, null, '31', '2', '1', '1', '7', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('64', '发布管理', null, null, 'fa-th-large', '0', '2', '1', '1', '1', '1');
INSERT INTO `ADMIN_RESOURCE` VALUES ('65', '常规', null, null, null, '64', '2', '1', '1', '1', '1');
INSERT INTO `ADMIN_RESOURCE` VALUES ('66', '发布工单', null, '/E00P00/DEV/order', null, '65', '2', '1', '1', '1', '2');
INSERT INTO `ADMIN_RESOURCE` VALUES ('67', '查询', 'ORDER_SEARCH', null, null, '66', '2', '1', '1', '1', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('68', '查看详情', 'ORDER_DETAIL', null, null, '66', '2', '1', '1', '2', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('69', '复制工单', 'ORDER_COPY', null, null, '66', '2', '1', '1', '3', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('70', '删除工单', 'ORDER_DELETE', null, null, '66', '2', '1', '1', '4', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('71', '创建发布工单', 'ORDER_CREATE', null, null, '66', '2', '1', '1', '5', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('72', '容器', null, null, null, '64', '2', '1', '1', '1', '1');
INSERT INTO `ADMIN_RESOURCE` VALUES ('73', '应用发布', null, '/E00P00/DEV/release/container', null, '72', '2', '1', '1', '1', '2');
INSERT INTO `ADMIN_RESOURCE` VALUES ('74', '发布历史', null, '/E00P00/DEV/release/history', null, '72', '2', '1', '1', '2', '2');
INSERT INTO `ADMIN_RESOURCE` VALUES ('75', '确认发布', 'RELEASE_COMFIRM_PUBLISH', null, null, '73', '2', '1', '1', '1', '3');
INSERT INTO `ADMIN_RESOURCE` VALUES ('76', '查询', 'RELEASE_HISTORY_SEARCH', null, null, '74', '2', '1', '1', '1', '3');

-- ----------------------------
-- Table structure for `ADMIN_RESOURCE_INTERFACE`
-- ----------------------------
DROP TABLE IF EXISTS `ADMIN_RESOURCE_INTERFACE`;
CREATE TABLE `ADMIN_RESOURCE_INTERFACE` (
  `RELATION_ID` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '关系ID',
  `RESOURCE_ID` bigint(20) DEFAULT NULL COMMENT '资源ID',
  `INTERFACE_ID` bigint(20) DEFAULT NULL COMMENT '接口ID',
  `IS_ENABLED` int(11) DEFAULT NULL COMMENT '是否启用 0: 禁用 1:启用',
  PRIMARY KEY (`RELATION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ADMIN_RESOURCE_INTERFACE
-- ----------------------------
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('1', '2', '1', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('2', '2', '2', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('3', '2', '3', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('4', '2', '4', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('5', '2', '5', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('6', '2', '6', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('7', '2', '7', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('8', '2', '8', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('9', '17', '10', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('10', '17', '11', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('11', '17', '12', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('12', '17', '13', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('13', '20', '11', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('14', '20', '14', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('15', '20', '12', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('16', '30', '15', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('17', '30', '16', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('18', '30', '17', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('19', '30', '18', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('20', '30', '19', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('21', '30', '20', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('22', '30', '21', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('23', '30', '22', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('24', '30', '23', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('25', '30', '24', '1');
INSERT INTO `ADMIN_RESOURCE_INTERFACE` VALUES ('26', '31', '25', '1');

-- ----------------------------
-- Table structure for `ADMIN_ROLE`
-- ----------------------------
DROP TABLE IF EXISTS `ADMIN_ROLE`;
CREATE TABLE `ADMIN_ROLE` (
  `ROLE_ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(50) NOT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  `CREATE_USERNAME` varchar(50) DEFAULT NULL,
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATE_USERNAME` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of ADMIN_ROLE
-- ----------------------------
INSERT INTO `ADMIN_ROLE` VALUES ('13', '超级管理员', '2018-04-23 15:01:00', '超级管理员', null, '2018-07-11 11:28:04', 'admin');
INSERT INTO `ADMIN_ROLE` VALUES ('23', '管理员【赢球帝项目】', '2018-05-17 15:05:00', '赢球帝项目 管理者角色', 'admin', '2018-07-11 16:00:54', 'admin');
INSERT INTO `ADMIN_ROLE` VALUES ('24', '运维工程师', '2018-05-18 17:49:36', '运维工程师', 'admin', '2018-07-13 15:45:16', 'admin');
INSERT INTO `ADMIN_ROLE` VALUES ('57', '管理员【彩票项目】', '2018-06-14 15:03:19', '彩票项目 管理者角色', 'admin', '2018-07-11 16:00:07', 'admin');
INSERT INTO `ADMIN_ROLE` VALUES ('58', '测试工程师【彩票项目】', '2018-06-14 17:28:32', '彩票项目 测试工程师角色', 'admin', '2018-07-11 15:55:32', 'admin');
INSERT INTO `ADMIN_ROLE` VALUES ('59', '测试工程师【赢球帝项目】', '2018-06-14 17:29:00', '赢球帝项目 测试工程师角色', 'admin', '2018-08-14 15:57:55', 'ytest1');

-- ----------------------------
-- Table structure for `ADMIN_ROLE_RESOURCE`
-- ----------------------------
DROP TABLE IF EXISTS `ADMIN_ROLE_RESOURCE`;
CREATE TABLE `ADMIN_ROLE_RESOURCE` (
  `ROLE_ID` bigint(10) unsigned NOT NULL,
  `RESOURCE_ID` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`RESOURCE_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of ADMIN_ROLE_RESOURCE
-- ----------------------------
INSERT INTO `ADMIN_ROLE_RESOURCE` VALUES ('13', '30');
INSERT INTO `ADMIN_ROLE_RESOURCE` VALUES ('13', '31');
INSERT INTO `ADMIN_ROLE_RESOURCE` VALUES ('59', '1');
INSERT INTO `ADMIN_ROLE_RESOURCE` VALUES ('59', '2');
INSERT INTO `ADMIN_ROLE_RESOURCE` VALUES ('59', '3');
INSERT INTO `ADMIN_ROLE_RESOURCE` VALUES ('59', '4');
INSERT INTO `ADMIN_ROLE_RESOURCE` VALUES ('59', '5');
INSERT INTO `ADMIN_ROLE_RESOURCE` VALUES ('59', '6');
INSERT INTO `ADMIN_ROLE_RESOURCE` VALUES ('59', '7');
INSERT INTO `ADMIN_ROLE_RESOURCE` VALUES ('59', '17');
INSERT INTO `ADMIN_ROLE_RESOURCE` VALUES ('59', '18');
INSERT INTO `ADMIN_ROLE_RESOURCE` VALUES ('59', '19');
INSERT INTO `ADMIN_ROLE_RESOURCE` VALUES ('59', '20');
INSERT INTO `ADMIN_ROLE_RESOURCE` VALUES ('59', '30');
INSERT INTO `ADMIN_ROLE_RESOURCE` VALUES ('59', '31');

-- ----------------------------
-- Table structure for `ADMIN_USER`
-- ----------------------------
DROP TABLE IF EXISTS `ADMIN_USER`;
CREATE TABLE `ADMIN_USER` (
  `ADMIN_USER_ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `REAL_NAME` varchar(50) NOT NULL COMMENT '用户姓名',
  `USERNAME` varchar(255) NOT NULL COMMENT '登录用户名',
  `PASSWORD` varchar(255) NOT NULL COMMENT '用户密码',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '邮箱地址',
  `MOBILE` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `IS_SUPPER` int(11) DEFAULT NULL COMMENT '是否为超级管理员 0为普通用户，1为超级用户',
  `IS_ACTIVE` int(11) DEFAULT NULL COMMENT '用户状态 1为正常用户，0为禁用',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `LOGIN_NUM` int(10) DEFAULT '0' COMMENT '登录次数 3: 账号禁用 登录成功此账号次数归0',
  PRIMARY KEY (`ADMIN_USER_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='用户信息表';

-- ----------------------------
-- Records of ADMIN_USER
-- ----------------------------
INSERT INTO `ADMIN_USER` VALUES ('1', '超管用户', 'admin', '8e19b13fd4eab13229c5bb7ee7c1b7b7', 'devops_admin@163.com', '13880730655', '1', '1', '2018-04-09 04:57:38', '2018-08-30 15:33:42', '0');
INSERT INTO `ADMIN_USER` VALUES ('87', '彩票普通用户一', 'ctest1', '93a8387d62d53b263daafac4edb3bd84', '', '', null, '1', '2018-07-11 15:53:51', '2018-08-30 15:33:44', '0');
INSERT INTO `ADMIN_USER` VALUES ('88', '彩票普通用户二', 'ctest2', 'b4d70232157ab8ea98cc5deff0e95ebc', '', '', null, '1', '2018-07-11 15:54:25', '2018-08-30 15:33:45', '0');
INSERT INTO `ADMIN_USER` VALUES ('89', '彩票普通用户三', 'ctest3', 'a733a737764eb0e3fcd0a0515132cd0a', '', '', null, '1', '2018-07-11 15:54:46', '2018-08-30 15:33:45', '0');
INSERT INTO `ADMIN_USER` VALUES ('90', '赢球帝普通用户一', 'ytest1', '6b7cb4fc7f4a5c2637e25e9dc1dc262a', '', '', null, '1', '2018-07-11 15:57:21', '2018-08-30 15:33:45', '0');
INSERT INTO `ADMIN_USER` VALUES ('91', '赢球帝普通用户二', 'ytest2', '35d5bdcc4c2cb45dd34634b7f58ba056', '', '', null, '1', '2018-07-11 15:57:38', '2018-08-30 15:33:46', '0');
INSERT INTO `ADMIN_USER` VALUES ('92', '赢球帝普通用户三', 'ytest3', 'b41a0804a418b29a4515bb879337fc31', '', '', null, '1', '2018-07-11 15:57:53', '2018-08-30 15:33:46', '0');
INSERT INTO `ADMIN_USER` VALUES ('93', '彩票管理用户', 'cagent', '859f6ab2bd433399021115d59bb8df2a', '', '', null, '1', '2018-07-11 16:02:12', '2018-08-30 15:33:47', '0');
INSERT INTO `ADMIN_USER` VALUES ('94', '赢球帝管理用户', 'yagent', '4d0fe89012978db605b8fd102cdb9448', '', '', null, '0', '2018-07-11 16:02:42', '2018-08-30 15:33:47', '0');
INSERT INTO `ADMIN_USER` VALUES ('95', '运维工程师账号', 'test1', '195d862596828ab53e81e505d52de043', 'Devops.test@163.c', '13584688647', null, '0', '2018-07-13 15:43:59', '2018-08-30 15:33:48', '0');
INSERT INTO `ADMIN_USER` VALUES ('99', '测试账号', 'test2', '8898513661c5ca973017040511825a67', 'devops_test@163.com', '18628454552', null, '0', '2018-08-14 16:16:17', '2018-08-30 15:33:48', '0');
INSERT INTO `ADMIN_USER` VALUES ('100', 'Xxxxx', 'yh2386', 'be50f52f8079cef1c652f27a46b16d2a', '158421152@qq.com', '15847269876', null, '1', '2018-08-29 09:49:45', '2018-08-30 17:45:54', '0');

-- ----------------------------
-- Table structure for `ADMIN_USER_ROLE`
-- ----------------------------
DROP TABLE IF EXISTS `ADMIN_USER_ROLE`;
CREATE TABLE `ADMIN_USER_ROLE` (
  `USER_ID` bigint(10) unsigned NOT NULL,
  `ROLE_ID` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of ADMIN_USER_ROLE
-- ----------------------------
INSERT INTO `ADMIN_USER_ROLE` VALUES ('1', '13');
INSERT INTO `ADMIN_USER_ROLE` VALUES ('87', '23');
INSERT INTO `ADMIN_USER_ROLE` VALUES ('87', '24');
INSERT INTO `ADMIN_USER_ROLE` VALUES ('87', '57');
INSERT INTO `ADMIN_USER_ROLE` VALUES ('87', '58');
INSERT INTO `ADMIN_USER_ROLE` VALUES ('87', '59');
INSERT INTO `ADMIN_USER_ROLE` VALUES ('88', '58');
INSERT INTO `ADMIN_USER_ROLE` VALUES ('89', '58');
INSERT INTO `ADMIN_USER_ROLE` VALUES ('90', '59');
INSERT INTO `ADMIN_USER_ROLE` VALUES ('91', '59');
INSERT INTO `ADMIN_USER_ROLE` VALUES ('92', '59');
INSERT INTO `ADMIN_USER_ROLE` VALUES ('93', '57');
INSERT INTO `ADMIN_USER_ROLE` VALUES ('94', '23');
INSERT INTO `ADMIN_USER_ROLE` VALUES ('95', '24');
INSERT INTO `ADMIN_USER_ROLE` VALUES ('100', '24');
INSERT INTO `ADMIN_USER_ROLE` VALUES ('100', '59');

-- ----------------------------
-- Table structure for `OPS_APP_CONFIG`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_APP_CONFIG`;
CREATE TABLE `OPS_APP_CONFIG` (
  `RECORD_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `RECORD_STATUS` int(4) DEFAULT NULL COMMENT '配置文件审核状态，0：未提交，1：已提交，2：审核通过，-1：审核失败',
  `APP_ID` bigint(20) unsigned DEFAULT NULL COMMENT '应用ID',
  `PRO_ID` bigint(20) DEFAULT NULL,
  `APP_NAME` varchar(100) DEFAULT NULL,
  `PRO_NAME` varchar(100) DEFAULT NULL,
  `BRANCH_NAME` varchar(100) DEFAULT NULL COMMENT '分支名称',
  `EDITOR_ID` bigint(20) unsigned DEFAULT NULL COMMENT '配置修改人员',
  `COMMIT_DATE` datetime DEFAULT NULL COMMENT '配置提交时间',
  `AUDITOR_ID` bigint(20) unsigned DEFAULT NULL COMMENT '配置审核人员',
  `AUDIT_DATE` datetime DEFAULT NULL COMMENT '配置审核时间',
  `BEGIN_SHA` varchar(100) DEFAULT NULL COMMENT '分支开始提交记录SHA',
  `END_SHA` varchar(100) DEFAULT NULL COMMENT '分支结束提交记录SHA',
  `EDITOR_NAME` varchar(50) DEFAULT NULL COMMENT '配置修改人员名称',
  `AUDITOR_NAME` varchar(50) DEFAULT NULL COMMENT '配置审核人员名称',
  `COMMIT_MESSAGE` varchar(1024) DEFAULT NULL,
  `AUDIT_STATUS` int(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`RECORD_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=219 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of OPS_APP_CONFIG
-- ----------------------------
INSERT INTO `OPS_APP_CONFIG` VALUES ('200', '2', '128', '49', 'admin', '彩票', 'admin20180828112902', '1', '2018-08-28 11:29:30', '1', '2018-08-28 11:37:28', 'a5b652801af2dcb900c602628d58814b0453e2f0', '7f329c5a49568380194aa5f9edabe6ab2568154c', 'admin', 'admin', 'admin 111111111....', '0');
INSERT INTO `OPS_APP_CONFIG` VALUES ('201', '-1', '128', '49', 'admin', '彩票', 'admin20180828113829', '1', '2018-08-28 11:39:13', '1', '2018-08-28 11:45:35', '7f329c5a49568380194aa5f9edabe6ab2568154c', '16872c90c697f6ca2aa94026eb3ed023e6ce3630', 'admin', 'admin', 'admin 更新应用包\r\n8/28 11:38', '0');
INSERT INTO `OPS_APP_CONFIG` VALUES ('202', '2', '128', '49', 'admin', '彩票', 'admin20180828114420', '1', '2018-08-28 11:44:36', '1', '2018-08-28 11:45:13', '7f329c5a49568380194aa5f9edabe6ab2568154c', 'dceec83318636217e5dab4ca2efe62daa6abed6d', 'admin', 'admin', 'admin 123', '0');
INSERT INTO `OPS_APP_CONFIG` VALUES ('203', '-1', '128', '49', 'admin', '彩票', 'admin20180828115004', '1', '2018-08-28 11:50:18', '1', '2018-08-28 11:50:53', 'dceec83318636217e5dab4ca2efe62daa6abed6d', '7d4c10551d76191c479fd2565115200d529f4d79', 'admin', 'admin', 'admin 12377', '0');
INSERT INTO `OPS_APP_CONFIG` VALUES ('204', '2', '148', '49', 'pay', '彩票', 'admin20180828160327', '1', '2018-08-28 16:03:40', '1', '2018-08-28 16:07:40', '95589a3f5e10967150b40a0d1a4afe1da7349df6', '70e9e53673f4d880650a2d32927f610150defafb', 'admin', 'admin', 'admin 更新彩票Pay应用包\r\n8/27 16:03', '0');
INSERT INTO `OPS_APP_CONFIG` VALUES ('205', '2', '128', '49', 'admin', '彩票', 'admin20180828160418', '1', '2018-08-28 16:04:33', '1', '2018-08-28 16:07:35', 'dceec83318636217e5dab4ca2efe62daa6abed6d', '451dd427889aab0a1d106e7d76fa9f43155c584a', 'admin', 'admin', 'admin 更新彩票admin应用包\r\n添加Today is Tuesday\r\n8/28 16:04', '0');
INSERT INTO `OPS_APP_CONFIG` VALUES ('206', '2', '128', '49', 'admin', '彩票', 'admin20180828170002', '1', '2018-08-28 17:00:16', '1', '2018-08-28 17:02:01', '451dd427889aab0a1d106e7d76fa9f43155c584a', 'bbd68065e12959250c18c12cb8173350df564a43', 'admin', 'admin', 'admin 更新admin应用包\r\nC_amdin_0828_00.zip\r\n8/28 17:00', '0');
INSERT INTO `OPS_APP_CONFIG` VALUES ('207', '2', '128', '49', 'admin', '彩票', 'admin20180828195113', '1', '2018-08-28 19:51:44', '1', '2018-08-28 19:54:40', 'c1d50b8c6af1044656d4ab98c70c0050be290aeb', '957f4e0400d5e8b5faab1463c4b8f888b4243e34', 'admin', 'admin', 'admin 更新应用版本\r\n8/28 19:51', '0');
INSERT INTO `OPS_APP_CONFIG` VALUES ('208', '2', '128', '49', 'admin', '彩票', 'admin20180828200942', '1', '2018-08-28 20:10:48', '1', '2018-08-28 20:11:46', '957f4e0400d5e8b5faab1463c4b8f888b4243e34', '68b536a25b2733c645e081c6f4ad9b35cfd4042d', 'admin', 'admin', 'admin 上传更新应用包\r\n文件结构不合法\r\n8/28 20:10', '0');
INSERT INTO `OPS_APP_CONFIG` VALUES ('209', '-1', '128', '49', 'admin', '彩票', 'admin20180828204846', '1', '2018-08-28 20:49:56', '1', '2018-08-28 21:12:25', '68b536a25b2733c645e081c6f4ad9b35cfd4042d', 'e3e32b56ed3691d232211b0afd41cd1c4a78971a', 'admin', 'admin', 'admin 审核状态新增。。。', '1');
INSERT INTO `OPS_APP_CONFIG` VALUES ('210', '2', '128', '49', 'admin', '彩票', 'admin20180828205124', '1', '2018-08-28 20:52:13', '1', '2018-08-28 20:53:57', '68b536a25b2733c645e081c6f4ad9b35cfd4042d', '6909031a15472b11cfa3bed13719ebd483a6a2cf', 'admin', 'admin', 'admin 新增审核状态。。。。', '1');
INSERT INTO `OPS_APP_CONFIG` VALUES ('211', '2', '128', '49', 'admin', '彩票', 'admin20180828205830', '1', '2018-08-28 20:58:53', '1', '2018-08-28 20:59:57', 'd868d7294d7ae651748343ad0e20f31ea8c15e62', 'b412d74eb7a7ed5723f149e24117b6f72b9941e6', 'admin', 'admin', 'admin liping  lalalalala.....这是审核状态。。。。', '1');
INSERT INTO `OPS_APP_CONFIG` VALUES ('212', '2', '128', '49', 'admin', '彩票', 'admin20180828210213', '1', '2018-08-28 21:02:36', '1', '2018-08-28 21:10:50', 'b412d74eb7a7ed5723f149e24117b6f72b9941e6', 'd91154740987e73bdda97b914c0e0347e509b9af', 'admin', 'admin', 'admin 123456...', '1');
INSERT INTO `OPS_APP_CONFIG` VALUES ('213', '2', '128', '49', 'admin', '彩票', 'admin20180828212437', '1', '2018-08-28 21:24:54', '1', '2018-08-28 21:25:12', '2c8fb7216a6e0f98f4b431db841c07542e847832', 'a8c57ae4f83ec88fbb0ad357ecc26d50c8cbdca5', 'admin', 'admin', 'admin 李萍。。。这是审核状态。。。', '1');
INSERT INTO `OPS_APP_CONFIG` VALUES ('214', '2', '128', '49', 'admin', '彩票', 'admin20180828212626', '1', '2018-08-28 21:26:42', '1', '2018-08-28 21:29:23', 'a8c57ae4f83ec88fbb0ad357ecc26d50c8cbdca5', '6a14da1fbc722a658d2ac5729d699671130d7286', 'admin', 'admin', 'admin 111111', '1');
INSERT INTO `OPS_APP_CONFIG` VALUES ('215', '-1', '128', '49', 'admin', '彩票', 'admin20180828213001', '1', '2018-08-28 21:30:42', '1', '2018-08-28 21:31:06', '6a14da1fbc722a658d2ac5729d699671130d7286', 'c12cd44f972e66d9eb1c131764efb7f88ec56207', 'admin', 'admin', 'admin 8/28 21:30', '1');
INSERT INTO `OPS_APP_CONFIG` VALUES ('216', '2', '128', '49', 'admin', '彩票', 'admin20180830163905', '1', '2018-08-30 16:39:33', '1', '2018-08-30 16:44:48', '6a14da1fbc722a658d2ac5729d699671130d7286', 'ec88f62b3e6a4be66499878c33e4ecc66528f5ca', 'admin', 'admin', 'admin 8/30 16:39\r\n更新应用发布包', '1');
INSERT INTO `OPS_APP_CONFIG` VALUES ('217', '2', '128', '49', 'admin', '彩票', 'admin20180830185944', '1', '2018-08-30 19:00:11', '1', '2018-08-30 19:03:41', 'ec88f62b3e6a4be66499878c33e4ecc66528f5ca', 'b9426be0993f4066bb41fa74661b77b275216d7e', 'admin', 'admin', 'admin 8/30 19:00', '1');
INSERT INTO `OPS_APP_CONFIG` VALUES ('218', '2', '129', '52', 'admin', '赢球帝', 'admin20180830195515', '1', '2018-08-30 19:55:45', '1', '2018-08-30 20:00:41', '4b8a5005feb5d2819fa81056e047d168fcaa1d6b', '5282729b69ba5c7253426d7e2a2e87617755b73a', 'admin', 'admin', 'admin 更新应用包\r\nHello，This is Devops！\r\n.<8/30 19:55>', '1');

-- ----------------------------
-- Table structure for `OPS_ASSEMBLE_APP`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_ASSEMBLE_APP`;
CREATE TABLE `OPS_ASSEMBLE_APP` (
  `APP_ENV_ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `APP_ID` bigint(11) unsigned DEFAULT NULL COMMENT '应用ID',
  `ENV_ID` bigint(11) unsigned DEFAULT NULL COMMENT '环境ID',
  `LABELS` varchar(50) DEFAULT NULL COMMENT '标签',
  `NAMESPACE` varchar(50) DEFAULT NULL COMMENT '名称空间',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `CURRENT_CONTAIN` varchar(50) DEFAULT NULL,
  `CURRENT_VERSION` varchar(50) DEFAULT NULL COMMENT '当前常规版本',
  `CONFIG_GIT_URL` varchar(255) DEFAULT NULL COMMENT '配置版本库地址',
  `APP_GIT_URL` varchar(255) DEFAULT NULL COMMENT '应用版本库地址',
  `DEPLOY_APP_GIT_URL` varchar(255) DEFAULT NULL COMMENT '发布版本库地址',
  `DEPLOY_APP_IMAGES_GIT_URL` varchar(250) DEFAULT NULL,
  `PRO_ID` bigint(11) unsigned DEFAULT NULL COMMENT '项目ID',
  `APP_NAME` varchar(50) DEFAULT NULL COMMENT '应用名称',
  `ENV_NAME` varchar(50) DEFAULT NULL COMMENT '环境名称',
  `PRO_NAME` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `MS_PORT` int(50) DEFAULT '8080' COMMENT '微服务或WEB端口',
  `MS_EUREKA_ADDR` varchar(50) DEFAULT NULL COMMENT '注册中心地址',
  `MS_PROFILE_TYPE_ID` bigint(50) DEFAULT NULL,
  `MS_ZONE_TYPE_ID` bigint(50) DEFAULT NULL,
  `MS_REGION_TYPE_ID` bigint(50) DEFAULT NULL,
  `MS_CONFIG_URL` varchar(50) DEFAULT NULL COMMENT '微服务配置地址',
  `UPLOAD_STATUS` int(50) DEFAULT '0',
  PRIMARY KEY (`APP_ENV_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=399 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='应用环境表';

-- ----------------------------
-- Records of OPS_ASSEMBLE_APP
-- ----------------------------
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('272', '124', '134', 'app=lbl-e00p00g0-10020-taskserver', 'e00p00g0', '2018-07-11 11:38:50', '2018-07-25 15:56:33', null, 'V_20180725_00', 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/taskserver/config.git', null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/taskserver/war.git', null, '49', 'taskserver', 'DEV', '彩票', '8091', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('273', '127', '134', 'app=lbl-e00p00g0-10004-api', 'e00p00g0', '2018-07-11 11:38:50', '2018-08-22 16:52:41', null, null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/api/config.git', null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/api/war.git', null, '49', 'api', 'DEV', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('274', '128', '134', 'app=lbl-e00p00g0-10002-admin', 'e00p00g0', '2018-07-11 11:38:50', '2018-08-27 20:40:32', null, null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/admin/config.git', null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/admin/war.git', null, '49', 'admin', 'DEV', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '2');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('275', '131', '134', 'app=lbl-e00p00g0-10005-app', 'e00p00g0', '2018-07-11 11:38:50', '2018-08-22 16:52:35', null, null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/app/config.git', null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/app/war.git', null, '49', 'app', 'DEV', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('276', '141', '134', 'app=lbl-e00p00g0-10011-shop', 'e00p00g0', '2018-07-11 11:38:50', '2018-08-22 16:52:31', null, null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/shop/config.git', null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/shop/war.git', null, '49', 'shop', 'DEV', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('277', '142', '134', 'app=lbl-e00p00g0-10001-www', 'e00p00g0', '2018-07-11 11:38:50', '2018-08-22 16:52:25', null, null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/www/config.git', null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/www/war.git', null, '49', 'www', 'DEV', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('278', '143', '134', 'app=lbl-e00p00g0-10003-distributor', 'e00p00g0', '2018-07-11 11:38:50', '2018-08-22 16:53:02', null, null, null, null, null, null, '49', 'distributor', 'DEV', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('279', '144', '134', 'app=lbl-e00p00g0-10006-agent', 'e00p00g0', '2018-07-11 11:38:50', '2018-08-22 16:51:43', null, null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/agent/config.git', null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/agent/war.git', null, '49', 'agent', 'DEV', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('280', '145', '134', 'app=lbl-e00p00g0-10013-leyou', 'e00p00g0', '2018-07-11 11:38:50', '2018-08-22 16:53:07', null, null, null, null, null, null, '49', 'leyou', 'DEV', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('281', '146', '134', 'app=lbl-e00p00g0-10012-lexun', 'e00p00g0', '2018-07-11 11:38:50', '2018-08-22 16:53:12', null, null, null, null, null, null, '49', 'lexun', 'DEV', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('282', '147', '134', 'app=lbl-e00p00g0-10014-lelun', 'e00p00g0', '2018-07-11 11:38:50', '2018-08-22 16:53:17', null, null, null, null, null, null, '49', 'lelun', 'DEV', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('283', '148', '134', 'app=lbl-e00p00g0-10007-pay', 'e00p00g0', '2018-07-11 11:38:50', '2018-08-22 16:53:22', null, null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/pay/config.git', null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/pay/war.git', null, '49', 'pay', 'DEV', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '2');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('284', '149', '134', 'app=lbl-e00p00g0-10009-data', 'e00p00g0', '2018-07-11 11:38:50', '2018-08-22 16:52:01', null, null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/data/config.git', null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/data/war.git', null, '49', 'data', 'DEV', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('285', '150', '134', 'app=lbl-e00p00g0-10008-login', 'e00p00g0', '2018-07-11 11:38:50', '2018-08-22 16:53:26', null, null, null, null, null, null, '49', 'login', 'DEV', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('286', '151', '134', 'app=lbl-e00p00g0-10021-message', 'e00p00g0', '2018-07-11 11:38:50', '2018-07-26 13:54:48', null, null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/message/config.git', null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/message/war.git', null, '49', 'message', 'DEV', '彩票', '8091', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('287', '152', '134', 'app=lbl-e00p00g0-10010-mshop', 'e00p00g0', '2018-07-11 11:38:50', '2018-08-22 16:53:30', null, null, null, null, null, null, '49', 'mshop', 'DEV', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('288', '129', '134', 'app=lbl-e05p00g0-15002-admin', 'e05p00g0', '2018-07-11 11:39:10', '2018-08-22 17:02:29', null, 'V_20180731_01', 'http://192.168.100.110:31000/E09/P00/DEVELOP/E05P00/admin/config.git', null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E05P00/admin/war.git', null, '52', 'admin', 'DEV', '赢球帝', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '2');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('289', '135', '134', 'app=lbl-e05p00g0-15005-app', 'e05p00g0', '2018-07-11 11:39:10', '2018-08-28 16:21:14', null, null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E05P00/app/config.git', null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E05P00/app/war.git', null, '52', 'app', 'DEV', '赢球帝', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('290', '136', '134', 'app=lbl-e05p00g0-15020-taskserver', 'e05p00g0', '2018-07-11 11:39:10', '2018-08-01 14:03:23', null, 'V_20180727_00', 'http://192.168.100.110:31000/E09/P00/DEVELOP/E05P00/taskserver/config.git', null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E05P00/taskserver/war.git', null, '52', 'taskserver', 'DEV', '赢球帝', '8091', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('291', '137', '134', 'app=lbl-e05p00g0-15004-api', 'e05p00g0', '2018-07-11 11:39:10', '2018-08-22 17:02:36', null, null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E05P00/api/config.git', null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E05P00/api/war.git', null, '52', 'api', 'DEV', '赢球帝', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('292', '153', '134', 'app=lbl-e05p00g0-15009-data', 'e05p00g0', '2018-07-11 11:39:10', '2018-08-22 20:03:21', null, null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E05P00/data/config.git', null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E05P00/data/war.git', null, '52', 'data', 'DEV', '赢球帝', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('293', '154', '134', 'app=lbl-e05p00g0-15021-message', 'e05p00g0', '2018-07-11 11:39:10', '2018-08-01 21:11:33', null, 'V_20180730_00', 'http://192.168.100.110:31000/E09/P00/DEVELOP/E05P00/message/config.git', null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E05P00/message/war.git', null, '52', 'message', 'DEV', '赢球帝', '8091', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('338', '124', '135', 'app=lbl-e00p00g1-10120-taskserver', 'e00p00g1', '2018-07-11 14:44:55', '2018-07-13 17:10:40', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/taskserver/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/taskserver/war.git', null, '49', 'taskserver', 'MASTER', '彩票', '8091', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('339', '127', '135', 'app=lbl-e00p00g1-10104-api', 'e00p00g1', '2018-07-11 14:44:55', '2018-08-22 16:54:48', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/api/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/api/war.git', null, '49', 'api', 'MASTER', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('340', '128', '135', 'app=lbl-e00p00g1-10102-admin', 'e00p00g1', '2018-07-11 14:44:55', '2018-08-22 16:54:09', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/admin/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/admin/war.git', null, '49', 'admin', 'MASTER', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('341', '131', '135', 'app=lbl-e00p00g1-10105-app', 'e00p00g1', '2018-07-11 14:44:55', '2018-08-22 17:01:39', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/app/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/app/war.git', null, '49', 'app', 'MASTER', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('342', '141', '135', 'app=lbl-e00p00g1-10111-shop', 'e00p00g1', '2018-07-11 14:44:55', '2018-08-22 17:01:44', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/shop/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/shop/war.git', null, '49', 'shop', 'MASTER', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('343', '142', '135', 'app=lbl-e00p00g1-10101-www', 'e00p00g1', '2018-07-11 14:44:55', '2018-08-22 17:01:48', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/www/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/www/war.git', null, '49', 'www', 'MASTER', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('344', '143', '135', 'app=lbl-e00p00g1-10103-distributor', 'e00p00g1', '2018-07-11 14:44:55', '2018-08-22 17:01:52', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/distributor/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/distributor/war.git', null, '49', 'distributor', 'MASTER', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('345', '144', '135', 'app=lbl-e00p00g1-10106-agent', 'e00p00g1', '2018-07-11 14:44:55', '2018-08-22 17:01:57', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/agent/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/agent/war.git', null, '49', 'agent', 'MASTER', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('346', '145', '135', 'app=lbl-e00p00g1-10113-leyou', 'e00p00g1', '2018-07-11 14:44:55', '2018-08-22 17:02:00', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/leyou/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/leyou/war.git', null, '49', 'leyou', 'MASTER', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('347', '146', '135', 'app=lbl-e00p00g1-10112-lexun', 'e00p00g1', '2018-07-11 14:44:55', '2018-08-22 17:02:04', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/lexun/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/lexun/war.git', null, '49', 'lexun', 'MASTER', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('348', '147', '135', 'app=lbl-e00p00g1-10114-lelun', 'e00p00g1', '2018-07-11 14:44:55', '2018-08-22 17:02:08', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/lelun/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/lelun/war.git', null, '49', 'lelun', 'MASTER', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('349', '148', '135', 'app=lbl-e00p00g1-10107-pay', 'e00p00g1', '2018-07-11 14:44:55', '2018-08-22 16:54:05', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/pay/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/pay/war.git', null, '49', 'pay', 'MASTER', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('350', '149', '135', 'app=lbl-e00p00g1-10109-data', 'e00p00g1', '2018-07-11 14:44:55', '2018-08-22 16:54:00', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/data/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/data/war.git', null, '49', 'data', 'MASTER', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('351', '150', '135', 'app=lbl-e00p00g1-10108-login', 'e00p00g1', '2018-07-11 14:44:55', '2018-08-22 16:54:15', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/login/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/login/war.git', null, '49', 'login', 'MASTER', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('352', '151', '135', 'app=lbl-e00p00g1-10121-message', 'e00p00g1', '2018-07-11 14:44:55', '2018-07-13 17:10:41', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/message/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/message/war.git', null, '49', 'message', 'MASTER', '彩票', '8091', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('353', '152', '135', 'app=lbl-e00p00g1-10110-mshop', 'e00p00g1', '2018-07-11 14:44:55', '2018-08-22 16:54:53', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/mshop/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/mshop/war.git', null, '49', 'mshop', 'MASTER', '彩票', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('354', '129', '135', 'app=lbl-e05p00g1-15102-admin', 'e05p00g1', '2018-07-11 15:15:26', '2018-08-22 17:03:10', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E05P00/admin/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E05P00/admin/war.git', null, '52', 'admin', 'MASTER', '赢球帝', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('355', '135', '135', 'app=lbl-e05p00g1-15105-app', 'e05p00g1', '2018-07-11 15:15:27', '2018-08-22 17:02:58', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E05P00/app/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E05P00/app/war.git', null, '52', 'app', 'MASTER', '赢球帝', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('356', '136', '135', 'app=lbl-e05p00g1-15120-taskserver', 'e05p00g1', '2018-07-11 15:15:27', '2018-07-13 17:10:41', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E05P00/taskserver/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E05P00/taskserver/war.git', null, '52', 'taskserver', 'MASTER', '赢球帝', '8091', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('357', '137', '135', 'app=lbl-e05p00g1-15104-api', 'e05p00g1', '2018-07-11 15:15:27', '2018-08-22 17:03:02', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E05P00/api/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E05P00/api/war.git', null, '52', 'api', 'MASTER', '赢球帝', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('358', '153', '135', 'app=lbl-e05p00g1-15109-data', 'e05p00g1', '2018-07-11 15:15:27', '2018-08-22 17:03:06', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E05P00/data/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E05P00/data/war.git', null, '52', 'data', 'MASTER', '赢球帝', '8080', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('359', '154', '135', 'app=lbl-e05p00g1-15121-message', 'e05p00g1', '2018-07-11 15:15:27', '2018-07-13 17:10:41', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E05P00/message/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E05P00/message/war.git', null, '52', 'message', 'MASTER', '赢球帝', '8091', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('360', '163', '134', null, null, '2018-07-13 16:36:12', '2018-07-13 20:51:32', null, null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/micro-config-center/config.git', null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/micro-config-center/war.git', null, '49', 'ms-config-center', 'DEV', '彩票', '8091', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('361', '169', '134', 'app=lbl-e00p00g0-10079-micro-tj-api', 'e00p00g0', '2018-07-26 14:31:51', '2018-08-31 14:19:28', null, null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/micro-tj-api/config.git', null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/micro-tj-api/war.git', null, '49', 'micro-tj-api', 'DEV', '彩票', '8095', 'http://192.168.100.80:10098/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('362', '170', '134', 'app=lbl-e00p00g0-10078-micro-act-api', 'e00p00g0', '2018-07-26 14:31:51', '2018-08-06 11:19:34', null, 'V_20180727_00', 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/micro-act-api/config.git', null, 'http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/micro-act-api/war.git', null, '49', 'micro-act-api', 'DEV', '彩票', '8094', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('363', '169', '135', 'app=lbl-e00p00g1-10179-micro-tj-api', 'e00p00g0', '2018-07-26 14:32:01', '2018-08-06 11:19:36', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/micro-tj-api/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/micro-tj-api/war.git', null, '49', 'micro-tj-api', 'MASTER', '彩票', '8096', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');
INSERT INTO `OPS_ASSEMBLE_APP` VALUES ('364', '170', '135', 'app=lbl-e00p00g1-10178-micro-act-api', 'e00p00g0', '2018-07-26 14:32:01', '2018-08-06 11:19:37', null, null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/micro-act-api/config.git', null, 'http://192.168.100.110:31000/E09/P00/MASTER/E00P00/micro-act-api/war.git', null, '49', 'micro-act-api', 'MASTER', '彩票', '8097', 'http://192.168.100.1:8090/eureka/', '100', '100', '100', 'http://192.168.100.110:31000/E00/P00/E00P00M05.git', '0');

-- ----------------------------
-- Table structure for `OPS_BASE_APP`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_BASE_APP`;
CREATE TABLE `OPS_BASE_APP` (
  `APP_ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '应用ID',
  `PRO_ID` bigint(11) unsigned DEFAULT NULL COMMENT '项目ID',
  `APP_NAME` varchar(50) NOT NULL COMMENT '应用名称',
  `APP_CODE` varchar(10) NOT NULL COMMENT '应用编码',
  `APP_TYPE` int(11) DEFAULT NULL COMMENT '应用类型0 代表tomcat 1 代表jdk7 2代表jdk8',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `APP_GIT_URL` varchar(255) NOT NULL,
  `LOG_PATH` varchar(255) DEFAULT NULL COMMENT '日志存放路径',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '描述信息',
  `APP_TYPE_ID` bigint(20) NOT NULL COMMENT '应用类型ID',
  `APP_TYPE_NAME` varchar(255) DEFAULT NULL COMMENT '应用类型名称',
  PRIMARY KEY (`APP_ID`) USING BTREE,
  KEY `k1` (`PRO_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='发布应用表';

-- ----------------------------
-- Records of OPS_BASE_APP
-- ----------------------------
INSERT INTO `OPS_BASE_APP` VALUES ('124', '49', 'taskserver', 'P006', '1', '2018-05-30 14:17:41', '2018-07-13 14:37:11', 'http://192.168.100.110:31000/E09/P00/E00P00/taskserver/class.git', '/data/logs/app_taskserver_log', '彩票taskserver应用', '100', 'JAVA应用');
INSERT INTO `OPS_BASE_APP` VALUES ('127', '49', 'api', 'P009', '0', '2018-05-30 14:18:35', '2018-07-13 14:36:48', 'http://192.168.100.110:31000/E09/P00/E00P00/api/class.git', '/data/logs/tomcat_api_log', '彩票api应用', '200', 'WEB应用');
INSERT INTO `OPS_BASE_APP` VALUES ('128', '49', 'admin', 'P010', '0', '2018-05-30 14:19:58', '2018-07-13 14:36:48', 'http://192.168.100.110:31000/E09/P00/E00P00/admin/class.git', '/data/logs/tomcat_admin_log', '彩票admin应用', '200', 'WEB应用');
INSERT INTO `OPS_BASE_APP` VALUES ('129', '52', 'admin', 'P001', '0', '2018-05-31 14:17:37', '2018-07-13 14:36:48', 'http://192.168.100.110:31000/E09/P00/E05P00/admin/class.git', '/data/logs/tomcat_admin_log', '赢球帝admin应用', '200', 'WEB应用');
INSERT INTO `OPS_BASE_APP` VALUES ('131', '49', 'app', 'P008', '0', '2018-06-05 10:50:18', '2018-07-13 14:36:48', 'http://192.168.100.110:31000/E09/P00/E00P00/app/class.git', '/data/logs/tomcat_app_log', '彩票app应用', '200', 'WEB应用');
INSERT INTO `OPS_BASE_APP` VALUES ('135', '52', 'app', 'P015', '0', '2018-06-05 11:01:05', '2018-08-28 11:02:47', 'http://192.168.100.110:31000/E09/P00/E05P00/app/class.git', '/data/nginx/nginx_static/app', '赢球帝app应用', '400', 'STATIC应用');
INSERT INTO `OPS_BASE_APP` VALUES ('136', '52', 'taskserver', 'P016', '1', '2018-06-05 11:02:38', '2018-07-13 14:37:11', 'http://192.168.100.110:31000/E09/P00/E05P00/taskserver/class.git', '/data/logs/app_taskserver_log', '赢球帝taskserver应用', '100', 'JAVA应用');
INSERT INTO `OPS_BASE_APP` VALUES ('137', '52', 'api', 'P017', '0', '2018-06-05 11:03:24', '2018-08-24 16:13:49', 'http://192.168.100.110:31000/E09/P00/E05P00/api/class.git', '/data/logs/tomcat_api_log', '赢球帝api应用', '200', 'WEB应用');
INSERT INTO `OPS_BASE_APP` VALUES ('141', '49', 'shop', 'P021', '0', '2018-06-05 15:45:16', '2018-07-13 14:36:49', 'http://192.168.100.110:31000/E09/P00/E00P00/shop/class.git', '/data/logs/tomcat_shop_log', '彩票shop应用', '200', 'WEB应用');
INSERT INTO `OPS_BASE_APP` VALUES ('142', '49', 'www', 'P022', '0', '2018-06-05 15:45:26', '2018-07-13 14:36:49', 'http://192.168.100.110:31000/E09/P00/E00P00/www/class.git', 'data/logs/tomcat_www_log', '彩票www应用', '200', 'WEB应用');
INSERT INTO `OPS_BASE_APP` VALUES ('143', '49', 'distributor', 'P023', '0', '2018-06-05 15:45:37', '2018-07-13 14:36:49', 'http://192.168.100.110:31000/E09/P00/E00P00/distributor/class.git', '/data/logs/tomcat_distributor_log', '彩票distributor应用', '200', 'WEB应用');
INSERT INTO `OPS_BASE_APP` VALUES ('144', '49', 'agent', 'P024', '0', '2018-06-05 15:45:44', '2018-07-13 14:36:49', 'http://192.168.100.110:31000/E09/P00/E00P00/agent/class.git', '/data/logs/tomcat_agent_log', '彩票agent应用', '200', 'WEB应用');
INSERT INTO `OPS_BASE_APP` VALUES ('145', '49', 'leyou', 'P025', '0', '2018-06-05 15:45:53', '2018-07-13 14:36:49', 'http://192.168.100.110:31000/E09/P00/E00P00/leyou/class.git', '/data/logs/tomcat_leyou_log', '彩票leyou应用', '200', 'WEB应用');
INSERT INTO `OPS_BASE_APP` VALUES ('146', '49', 'lexun', 'P026', '0', '2018-06-05 15:46:01', '2018-07-13 14:36:49', 'http://192.168.100.110:31000/E09/P00/E00P00/lexun/class.git', '/data/logs/tomcat_lexun_log', '彩票lexun应用', '200', 'WEB应用');
INSERT INTO `OPS_BASE_APP` VALUES ('147', '49', 'lelun', 'P027', '0', '2018-06-05 15:46:09', '2018-07-13 14:36:49', 'http://192.168.100.110:31000/E09/P00/E00P00/lelun/class.git', '/data/logs/tomcat_lelun_log', '彩票lelun应用', '200', 'WEB应用');
INSERT INTO `OPS_BASE_APP` VALUES ('148', '49', 'pay', 'P028', '0', '2018-06-05 15:46:19', '2018-07-13 14:36:49', 'http://192.168.100.110:31000/E09/P00/E00P00/pay/class.git', '/data/logs/tomcat_pay_log', '彩票pay应用', '200', 'WEB应用');
INSERT INTO `OPS_BASE_APP` VALUES ('149', '49', 'data', 'P029', '0', '2018-06-05 15:46:33', '2018-07-13 14:36:49', 'http://192.168.100.110:31000/E09/P00/E00P00/data/class.git', '/data/logs/tomcat_data_log', '彩票data应用', '200', 'WEB应用');
INSERT INTO `OPS_BASE_APP` VALUES ('150', '49', 'login', 'P030', '0', '2018-06-05 15:46:50', '2018-07-13 14:36:49', 'http://192.168.100.110:31000/E09/P00/E00P00/login/class.git', '/data/logs/tomcat_login_log', '彩票login应用', '200', 'WEB应用');
INSERT INTO `OPS_BASE_APP` VALUES ('151', '49', 'message', 'P031', '1', '2018-06-05 15:47:06', '2018-07-13 14:37:08', 'http://192.168.100.110:31000/E09/P00/E00P00/message/class.git', '/data/logs/app_message_log', '彩票message应用', '100', 'JAVA应用');
INSERT INTO `OPS_BASE_APP` VALUES ('152', '49', 'mshop', 'P032', '0', '2018-06-05 16:25:37', '2018-07-13 14:36:49', 'http://192.168.100.110:31000/E09/P00/E00P00/mshop/class.git', '/data/logs/tomcat_mshop_log', '彩票mshop应用', '200', 'WEB应用');
INSERT INTO `OPS_BASE_APP` VALUES ('153', '52', 'data', 'P033', '0', '2018-06-06 11:08:08', '2018-07-13 14:36:49', 'http://192.168.100.110:31000/E09/P00/E05P00/data/class.git', '/data/logs/tomcat_data_log', '赢球帝data应用', '200', 'WEB应用');
INSERT INTO `OPS_BASE_APP` VALUES ('154', '52', 'message', 'P034', '1', '2018-06-06 11:08:35', '2018-07-13 14:37:08', 'http://192.168.100.110:31000/E09/P00/E05P00/message/class.git', '/data/logs/app_message_log', '赢球帝message应用', '100', 'JAVA应用');
INSERT INTO `OPS_BASE_APP` VALUES ('163', '49', 'ms-config-center', 'E33', '2', '2018-07-13 16:32:59', '2018-07-13 16:32:53', 'http://192.168.100.110:31000/E09/P00/E00P00/micro-config-center/class.git', '/data/log/ms-config-center', '微服务配置中心', '300', '微服务应用');
INSERT INTO `OPS_BASE_APP` VALUES ('169', '49', 'micro-tj-api', 'P036', '2', '2018-07-26 14:29:29', '2018-07-26 14:31:23', 'http://192.168.100.110:31000/E09/P00/E00P00/micro-tj-api/class.git', '/data/log/micro-tj-api', 'micro-tj-api微服务', '300', '微服务应用');
INSERT INTO `OPS_BASE_APP` VALUES ('170', '49', 'micro-act-api', 'P037', '2', '2018-07-26 14:30:56', '2018-07-26 14:31:29', 'http://192.168.100.110:31000/E09/P00/E00P00/micro-act-api/class.git', '/data/log/micro-act-api', 'micro-act-api微服务', '300', '微服务应用');

-- ----------------------------
-- Table structure for `OPS_BASE_APP_HIS`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_BASE_APP_HIS`;
CREATE TABLE `OPS_BASE_APP_HIS` (
  `APP_HIS_ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `APP_ENV_ID` bigint(11) unsigned DEFAULT NULL COMMENT '应用环境ID',
  `SSH_ADDRESS` varchar(256) DEFAULT NULL COMMENT '服务器IP地址',
  `DEPLOY_APP_VERSION_CODE` varchar(50) DEFAULT NULL COMMENT '发布包版本号',
  `DEPLOY_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `DEPLOY_TYPE` varchar(2) NOT NULL,
  `OPERATE_TYPE` int(11) NOT NULL COMMENT '操作类型 0新发布，1回滚，2重启',
  `STATUS` int(11) NOT NULL COMMENT '发布状态 0失败，1成功',
  `DESCRIPTION` varchar(1024) DEFAULT NULL COMMENT '描述信息',
  `OPERATE_USER_ID` bigint(11) unsigned DEFAULT NULL COMMENT '操作员',
  `JENKINS_JOB_ID` int(11) unsigned DEFAULT NULL,
  `FOLDER_NAME` varchar(50) DEFAULT NULL,
  `DEP_JOB_NAME` varchar(50) DEFAULT NULL,
  `LOGINFO` blob,
  PRIMARY KEY (`APP_HIS_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=622 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='应用发布历史信息表';

-- ----------------------------
-- Records of OPS_BASE_APP_HIS
-- ----------------------------

-- ----------------------------
-- Table structure for `OPS_BASE_APP_TYPE`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_BASE_APP_TYPE`;
CREATE TABLE `OPS_BASE_APP_TYPE` (
  `APP_TYPE_ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '应用类型ID',
  `APP_TYPE_NAME` varchar(50) NOT NULL COMMENT '应用类型名称',
  `SCRIPT_PATH` varchar(100) NOT NULL COMMENT '脚本路径',
  `DEPLOY_PATH` varchar(100) NOT NULL COMMENT '部署路径',
  PRIMARY KEY (`APP_TYPE_ID`) USING BTREE,
  KEY `k1` (`APP_TYPE_NAME`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3301 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='发布应用表';

-- ----------------------------
-- Records of OPS_BASE_APP_TYPE
-- ----------------------------
INSERT INTO `OPS_BASE_APP_TYPE` VALUES ('100', 'JAVA应用', '/usr/local/action_#{NAME}/', '/usr/local/app_#{NAME}/');
INSERT INTO `OPS_BASE_APP_TYPE` VALUES ('200', 'WEB应用', '/usr/local/action_#{NAME}/', '/usr/local/tomcat_#{NAME}/webapps/ROOT/');
INSERT INTO `OPS_BASE_APP_TYPE` VALUES ('300', '微服务应用', '/usr/local/wf/#{NAME}/', '/usr/local/wf/#{NAME}/app/');
INSERT INTO `OPS_BASE_APP_TYPE` VALUES ('400', 'STATIC应用', '/usr/local/action_#{NAME}/', '/date/nginx/nginx_static/#{NAME}/');

-- ----------------------------
-- Table structure for `OPS_BASE_ENV`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_BASE_ENV`;
CREATE TABLE `OPS_BASE_ENV` (
  `ENV_ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '环境ID',
  `ENV_NAME` varchar(50) NOT NULL COMMENT '环境名称',
  `ENV_CODE` varchar(10) NOT NULL COMMENT '环境编码',
  `PRIORITY` int(11) DEFAULT NULL COMMENT '优先级',
  `DESCRIPTION` varchar(200) DEFAULT NULL COMMENT '描述信息',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`ENV_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='发布环境类型表';

-- ----------------------------
-- Records of OPS_BASE_ENV
-- ----------------------------
INSERT INTO `OPS_BASE_ENV` VALUES ('134', 'DEV', 'E001', '1', '第一套发布环境', '2018-05-30 11:54:49', '2018-06-23 15:16:31');
INSERT INTO `OPS_BASE_ENV` VALUES ('135', 'MASTER', 'E002', '2', '第二套发布环境', '2018-05-30 14:03:38', '2018-06-13 18:11:19');

-- ----------------------------
-- Table structure for `OPS_BASE_ENV_SERVER`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_BASE_ENV_SERVER`;
CREATE TABLE `OPS_BASE_ENV_SERVER` (
  `HOST_APPENV_ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号ID',
  `HOST_ID` bigint(11) unsigned DEFAULT NULL COMMENT '服务器ID',
  `APP_ENV_ID` bigint(11) unsigned DEFAULT NULL COMMENT '应用环境ID',
  `VERSION_CODE` varchar(50) DEFAULT NULL COMMENT '上一次成功发布版本',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `OPERATE_USER_ID` bigint(11) unsigned DEFAULT NULL COMMENT '操作员',
  `APP_STATUS` smallint(6) DEFAULT '-1' COMMENT '应用发布状态 -1表示已停止，0表示运行中，1表示发布中',
  PRIMARY KEY (`HOST_APPENV_ID`) USING BTREE,
  KEY `index_hostId` (`HOST_ID`) USING BTREE,
  KEY `index_appenv` (`APP_ENV_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1275 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='服务器与应用环境关系表';

-- ----------------------------
-- Records of OPS_BASE_ENV_SERVER
-- ----------------------------
INSERT INTO `OPS_BASE_ENV_SERVER` VALUES ('1177', '496', '283', null, '2018-08-24 10:24:58', null, '-1');
INSERT INTO `OPS_BASE_ENV_SERVER` VALUES ('1178', '494', '283', null, '2018-08-31 10:42:05', null, '-1');
INSERT INTO `OPS_BASE_ENV_SERVER` VALUES ('1179', '495', '283', null, '2018-08-31 10:40:38', null, '-1');
INSERT INTO `OPS_BASE_ENV_SERVER` VALUES ('1194', '500', '289', 'V_20180828_02', '2018-09-04 17:32:25', null, '0');
INSERT INTO `OPS_BASE_ENV_SERVER` VALUES ('1195', '501', '289', null, '2018-08-31 11:28:05', null, '0');
INSERT INTO `OPS_BASE_ENV_SERVER` VALUES ('1196', '502', '289', null, '2018-08-31 11:28:05', null, '0');
INSERT INTO `OPS_BASE_ENV_SERVER` VALUES ('1197', '491', '285', null, '2018-09-05 10:11:55', null, '-1');
INSERT INTO `OPS_BASE_ENV_SERVER` VALUES ('1235', '491', '274', 'V_20180828_16', '2018-09-05 10:11:55', null, '-1');
INSERT INTO `OPS_BASE_ENV_SERVER` VALUES ('1236', '492', '274', null, '2018-09-04 19:49:53', null, '-1');
INSERT INTO `OPS_BASE_ENV_SERVER` VALUES ('1237', '497', '274', null, '2018-09-05 10:11:56', null, '-1');

-- ----------------------------
-- Table structure for `OPS_BASE_PRO`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_BASE_PRO`;
CREATE TABLE `OPS_BASE_PRO` (
  `PRO_ID` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '项目ID',
  `PRO_NAME` varchar(50) NOT NULL COMMENT '项目名称',
  `PRO_CODE` varchar(50) NOT NULL COMMENT '项目编码',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '描述信息',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `WEB_HOOK` varchar(255) DEFAULT NULL COMMENT '钉钉hook',
  `RESOURCE_ID` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`PRO_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='发布项目表';

-- ----------------------------
-- Records of OPS_BASE_PRO
-- ----------------------------
INSERT INTO `OPS_BASE_PRO` VALUES ('49', '彩票', 'E00P00', '彩票Web项目', '2018-05-30 14:14:29', '2018-08-03 19:03:21', 'https://oapi.dingtalk.com/robot/send?access_token=21d1dca5e820ceb5ef99884177f2608c3277726e8f09c7d904f6c17a20028bce', null);
INSERT INTO `OPS_BASE_PRO` VALUES ('52', '赢球帝', 'E05P00', '赢球帝项目', '2018-05-31 14:16:57', '2018-08-03 19:05:47', 'https://oapi.dingtalk.com/robot/send?access_token=fd3c9a7e75cdb9daa243bd40e1bdb3e812a8796e432c435d4c1296a1a029c342', null);

-- ----------------------------
-- Table structure for `OPS_BASE_PROFILE_TYPE`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_BASE_PROFILE_TYPE`;
CREATE TABLE `OPS_BASE_PROFILE_TYPE` (
  `PROFILE_TYPE_ID` bigint(10) DEFAULT NULL,
  `PROFILE_TYPE_NAME` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OPS_BASE_PROFILE_TYPE
-- ----------------------------
INSERT INTO `OPS_BASE_PROFILE_TYPE` VALUES ('100', 'dev');

-- ----------------------------
-- Table structure for `OPS_BASE_REGION_TYPE`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_BASE_REGION_TYPE`;
CREATE TABLE `OPS_BASE_REGION_TYPE` (
  `REGION_TYPE_ID` bigint(10) DEFAULT NULL,
  `REGION_TYPE_NAME` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OPS_BASE_REGION_TYPE
-- ----------------------------
INSERT INTO `OPS_BASE_REGION_TYPE` VALUES ('100', 'dev_region');

-- ----------------------------
-- Table structure for `OPS_BASE_SERVER`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_BASE_SERVER`;
CREATE TABLE `OPS_BASE_SERVER` (
  `ASSETS_ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '资源ID',
  `ASSETS_NAME` varchar(50) NOT NULL COMMENT '资源名称',
  `ASSETS_TYPE` int(11) NOT NULL COMMENT '资源类型 0为物理机，1为云服务器，2为容器',
  `SSH_ADDRESS` varchar(50) DEFAULT NULL COMMENT 'SSH地址',
  `SSH_PORT` int(10) DEFAULT NULL COMMENT 'SSH端口',
  `ASSETS_STATUS` int(11) DEFAULT NULL COMMENT '服务器状态',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `OPERATE_SYSTEM` varchar(50) DEFAULT NULL COMMENT '操作系统',
  `MEMORY` varchar(50) DEFAULT NULL COMMENT '内存',
  `CPU` varchar(50) DEFAULT NULL COMMENT '系统CPU',
  `DISK` varchar(50) DEFAULT NULL COMMENT '磁盘大小',
  `OUTER_IP` varchar(50) DEFAULT NULL COMMENT '外网IP',
  `INNER_IP` varchar(50) DEFAULT NULL COMMENT '内网IP',
  `OPERATE_USER_ID` bigint(10) unsigned DEFAULT NULL,
  `HOST_ACCOUNT` varchar(30) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '该服务器的账号',
  `HOST_PASSWORD` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '该服务器的密码',
  `INITIAL_STATUS` int(11) DEFAULT NULL COMMENT '服务器初始化状态 1 已初始化 0 未初始化 2 正在初始化',
  PRIMARY KEY (`ASSETS_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=510 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='服务器信息表';

-- ----------------------------
-- Records of OPS_BASE_SERVER
-- ----------------------------
INSERT INTO `OPS_BASE_SERVER` VALUES ('491', '彩D-admin-100.1', '0', '192.168.100.1', '22', '0', '2018-08-24 09:57:41', '2018-08-28 15:53:27', '', '', '', '', '192.168.100.1', '192.168.100.1', '1', 'root', '1q2w3e4r..', '1');
INSERT INTO `OPS_BASE_SERVER` VALUES ('492', '彩D-admin-100.2', '0', '192.168.100.2', '22', '0', '2018-08-24 10:03:48', '2018-09-04 15:24:45', null, null, null, null, '192.168.100.2', '192.168.100.2', '1', 'root', '1q2w3e4r..', '1');
INSERT INTO `OPS_BASE_SERVER` VALUES ('494', '赢D-admin-100.75', '0', '192.168.100.75', '22', '0', '2018-08-24 10:06:18', '2018-08-30 20:01:56', '', '', '', '', '192.168.100.75', '192.168.100.75', '1', 'root', '1q2w3e4r..', '1');
INSERT INTO `OPS_BASE_SERVER` VALUES ('495', '彩D-pay-100.84', '0', '192.168.100.84', '22', '0', '2018-08-24 10:06:37', '2018-08-28 15:53:32', null, null, null, null, '192.168.100.84', '192.168.100.84', '1', 'root', '1q2w3e4r..', '1');
INSERT INTO `OPS_BASE_SERVER` VALUES ('496', '彩D-pay-100.85', '1', '192.168.100.85', '22', '0', '2018-08-24 10:08:36', '2018-09-03 17:46:41', null, null, null, null, '192.168.100.85', '192.168.100.85', '1', 'root', '1q2w3e4r..', '0');
INSERT INTO `OPS_BASE_SERVER` VALUES ('497', '彩D-admin-100.86', '0', '192.168.100.86', '22', '0', '2018-08-24 10:09:53', '2018-08-28 15:53:30', null, null, null, null, '192.168.100.86', '192.168.100.86', '1', 'root', '1q2w3e4r..', '1');
INSERT INTO `OPS_BASE_SERVER` VALUES ('498', '赢D-admin-100.76', '0', '192.168.100.76', '22', '0', '2018-08-24 10:10:59', '2018-08-30 20:01:56', null, null, null, null, '192.168.100.76', '192.168.100.76', '1', 'root', '1q2w3e4r..', '1');
INSERT INTO `OPS_BASE_SERVER` VALUES ('499', '赢D-admin-100.77', '0', '192.168.100.77', '22', '0', '2018-08-24 10:10:59', '2018-08-31 10:55:33', null, null, null, null, '192.168.100.77', '192.168.100.77', '1', 'root', '1q2w3e4r..', '1');
INSERT INTO `OPS_BASE_SERVER` VALUES ('500', '赢D-app-100.81', '0', '192.168.100.81', '22', '0', '2018-08-24 10:10:59', '2018-08-24 10:27:58', null, null, null, null, '192.168.100.81', '192.168.100.81', '1', 'root', '1q2w3e4r..', '0');
INSERT INTO `OPS_BASE_SERVER` VALUES ('501', '赢D-app-100.82', '0', '192.168.100.82', '22', '0', '2018-08-24 10:10:59', '2018-08-24 10:28:04', null, null, null, null, '192.168.100.82', '192.168.100.82', '1', 'root', '1q2w3e4r..', '0');
INSERT INTO `OPS_BASE_SERVER` VALUES ('502', '赢D-app-100.83', '0', '192.168.100.83', '22', '0', '2018-08-24 10:10:59', '2018-09-04 19:34:48', null, null, null, null, '192.168.100.83', '192.168.100.83', '1', 'root', '1q2w3e4r..', '0');
INSERT INTO `OPS_BASE_SERVER` VALUES ('506', '彩D-admin-100.78', '0', '192.168.100.78', '22', '1', '2018-08-30 18:46:52', '2018-08-31 11:03:12', 'Linux', '8G', '512HZ', '500G', '192.168.100.78', '192.168.100.78', '1', 'root', '1q2w3e4r..', '0');
INSERT INTO `OPS_BASE_SERVER` VALUES ('508', '彩D-task-50.74', '0', '192.168.50.74', '10001', '0', '2018-09-04 10:20:59', '2018-09-04 18:53:08', '', '', '', '', '192.168.50.74', '192.168.50.74', '1', 'root', 'Roc@123', '1');

-- ----------------------------
-- Table structure for `OPS_BASE_SERVER_EXEC_HIS`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_BASE_SERVER_EXEC_HIS`;
CREATE TABLE `OPS_BASE_SERVER_EXEC_HIS` (
  `EXEC_HIS_ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `EXEC_CONTENT` text COMMENT '模板内容',
  `EXEC_RESULT` varchar(255) DEFAULT NULL COMMENT '执行结果状态',
  `TPL_ID` bigint(10) DEFAULT NULL COMMENT '执行模板ID',
  `EXEC_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '执行时间',
  `ASSETS_ID` varchar(50) DEFAULT NULL COMMENT '服务器ID',
  `ADMIN_USER_ID` bigint(10) DEFAULT NULL COMMENT '操作员ID',
  PRIMARY KEY (`EXEC_HIS_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=908 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='脚本执行模板';

-- ----------------------------
-- Records of OPS_BASE_SERVER_EXEC_HIS
-- ----------------------------
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('774', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:26', '498', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('775', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:27', '499', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('776', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:42', '498', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('777', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:43', '499', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('778', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:42', '501', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('779', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:45', '497', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('780', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:43', '502', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('781', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:45', '496', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('782', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:46', '492', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('783', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:47', '495', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('784', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:47', '494', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('785', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:27', '500', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('786', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:43', '500', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('787', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:52', '501', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('788', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:53', '502', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('789', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:53', '497', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('790', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:54', '496', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('791', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:54', '492', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('792', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:55', '495', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('793', 'ls -la /usr/local', '成功', null, '2018-08-24 10:12:56', '494', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('794', 'ls -la /usr/local', '成功', null, '2018-08-24 10:13:10', '498', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('795', 'ls -la /usr/local', '成功', null, '2018-08-24 10:13:10', '499', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('796', 'ls -la /usr/local', '成功', null, '2018-08-24 10:13:10', '502', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('797', 'ls -la /usr/local', '成功', null, '2018-08-24 10:13:11', '501', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('798', 'pwd', '成功', null, '2018-08-24 10:13:24', '498', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('799', 'pwd', '成功', null, '2018-08-24 10:13:24', '499', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('800', 'pwd', '成功', null, '2018-08-24 10:13:24', '502', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('801', 'pwd', '成功', null, '2018-08-24 10:13:25', '501', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('802', 'ls -la /usr/local', '成功', null, '2018-08-24 10:13:33', '494', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('803', 'pwd', '成功', null, '2018-08-24 10:13:33', '494', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('804', 'pwd', '成功', null, '2018-08-24 10:13:25', '500', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('805', 'ls -la /usr/local', '成功', null, '2018-08-24 10:13:11', '500', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('806', 'ls -l /usr/local', '成功', null, '2018-08-24 10:13:50', '498', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('807', 'ls -l /usr/local', '成功', null, '2018-08-24 10:13:50', '499', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('808', 'ls -l /usr/local', '成功', null, '2018-08-24 10:13:51', '502', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('809', 'ls -l /usr/local', '成功', null, '2018-08-24 10:13:51', '501', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('810', 'ls -l /usr/local', '成功', null, '2018-08-24 10:14:02', '494', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('811', 'ls -l /usr/local', '成功', null, '2018-08-24 10:13:51', '500', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('812', 'ls -l /tmp', '成功', null, '2018-08-24 10:15:06', '498', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('813', 'ls -l /tmp', '成功', null, '2018-08-24 10:15:06', '499', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('814', 'ls -l /tmp', '成功', null, '2018-08-24 10:15:06', '502', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('815', 'ls -l /tmp', '成功', null, '2018-08-24 10:15:07', '501', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('816', 'ls -l /tmp', '成功', null, '2018-08-24 10:15:17', '494', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('817', 'ls -l /tmp', '成功', null, '2018-08-24 10:15:07', '500', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('818', 'ls -l /data', '失败【 Incorrect Usage】', null, '2018-08-24 10:18:22', '502', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('819', 'ls -l /data', '成功', null, '2018-08-24 10:18:40', '494', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('820', 'cat /etc/profile', '成功', null, '2018-08-24 10:19:09', '498', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('821', 'cat /etc/profile', '成功', null, '2018-08-24 10:19:09', '499', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('822', 'cat /etc/profile', '成功', null, '2018-08-24 10:19:10', '502', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('823', 'cat /etc/profile', '成功', null, '2018-08-24 10:19:10', '501', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('824', 'cat /etc/profile', '成功', null, '2018-08-24 10:19:33', '494', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('825', 'cat /etc/profile', '成功', null, '2018-08-24 10:19:10', '500', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('826', 'cat /etc/profile;ls -l /tmp;ls -l /usr/local', '成功', null, '2018-08-24 10:20:27', '496', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('827', 'cat /etc/profile;ls -l /tmp;ls -l /usr/local', '成功', null, '2018-08-24 10:20:27', '497', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('828', 'cat /etc/profile;ls -l /tmp;ls -l /usr/local', '成功', null, '2018-08-24 10:20:28', '495', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('829', null, '成功', '231', '2018-08-25 14:35:03', '495', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('830', null, '成功', '231', '2018-08-25 14:35:03', '492', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('831', null, '成功', '231', '2018-08-25 14:35:04', '497', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('832', null, '成功', '231', '2018-08-25 14:35:04', '496', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('833', null, '成功', '231', '2018-08-25 14:35:04', '493', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('834', null, '成功', '231', '2018-08-25 14:35:05', '491', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('835', null, '成功', '231', '2018-08-25 17:36:25', '502', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('836', null, '成功', '231', '2018-08-25 17:36:25', '501', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('837', null, '成功', '231', '2018-08-25 17:36:25', '500', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('838', null, '成功', '231', '2018-08-25 17:36:25', '498', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('839', null, '成功', '231', '2018-08-25 17:36:26', '499', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('840', null, '成功', '231', '2018-08-25 17:36:26', '494', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('841', null, '成功', '231', '2018-08-27 13:40:46', '492', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('842', null, '成功', '231', '2018-08-27 13:40:46', '491', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('843', null, '成功', '231', '2018-08-28 15:30:43', '493', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('844', null, '成功', '231', '2018-08-28 15:30:43', '495', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('845', null, '成功', '231', '2018-08-28 15:30:44', '492', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('846', null, '成功', '231', '2018-08-28 15:30:44', '491', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('847', null, '成功', '231', '2018-08-28 15:30:44', '497', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('848', null, '成功', '231', '2018-08-28 15:30:45', '496', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('849', 'pwd', '成功', null, '2018-08-29 10:08:28', '500', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('850', null, '成功', '231', '2018-08-30 19:56:26', '494', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('851', null, '成功', '231', '2018-08-30 19:56:26', '498', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('852', null, '成功', '231', '2018-08-30 19:56:27', '499', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('853', null, '成功', '231', '2018-08-30 19:57:46', '494', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('854', null, '成功', '231', '2018-08-30 19:57:47', '498', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('855', null, '成功', '231', '2018-08-30 19:57:47', '499', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('856', null, '失败【 Authentication failed.】', '270', '2018-09-04 17:44:52', '508', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('857', null, '成功', '270', '2018-09-04 17:46:10', '508', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('858', null, '成功', '270', '2018-09-04 18:56:37', '508', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('859', null, '成功', '231', '2018-09-05 14:50:06', '502', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('860', null, '成功', '231', '2018-09-05 14:50:07', '508', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('861', null, '成功', '231', '2018-09-05 14:50:07', '492', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('862', null, '成功', '231', '2018-09-05 14:50:08', '496', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('863', null, '成功', '231', '2018-09-05 14:50:08', '506', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('864', null, '成功', '231', '2018-09-05 14:50:09', '499', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('865', null, '成功', '231', '2018-09-05 14:50:09', '498', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('866', null, '成功', '231', '2018-09-05 14:50:09', '494', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('867', null, '成功', '231', '2018-09-05 14:50:10', '495', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('868', null, '成功', '231', '2018-09-05 14:50:10', '497', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('869', null, '成功', '231', '2018-09-05 14:50:10', '491', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('870', null, '成功', '231', '2018-09-05 14:50:11', '501', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('871', null, '成功', '231', '2018-09-05 14:50:12', '500', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('872', null, '成功', '231', '2018-09-05 15:33:41', '502', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('873', null, '成功', '231', '2018-09-05 15:33:41', '508', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('874', null, '成功', '231', '2018-09-05 15:33:42', '492', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('875', null, '成功', '231', '2018-09-05 15:33:43', '496', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('876', null, '成功', '231', '2018-09-05 15:33:43', '506', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('877', null, '成功', '231', '2018-09-05 15:33:44', '499', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('878', null, '成功', '231', '2018-09-05 15:33:45', '498', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('879', null, '成功', '231', '2018-09-05 15:33:45', '494', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('880', null, '成功', '231', '2018-09-05 15:33:45', '495', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('881', null, '成功', '231', '2018-09-05 15:33:46', '497', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('882', null, '成功', '231', '2018-09-05 15:33:46', '491', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('883', null, '成功', '231', '2018-09-05 15:33:46', '501', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('884', null, '成功', '231', '2018-09-05 15:33:47', '500', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('885', 'ls -l /usr/local', '成功', null, '2018-09-05 15:36:55', '502', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('886', null, '成功', '231', '2018-09-05 15:37:50', '502', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('887', 'ls -l /usr/local', '成功', null, '2018-09-05 15:38:12', '502', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('888', null, '成功', '231', '2018-09-05 15:43:53', '502', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('889', null, '成功', '231', '2018-09-05 15:43:53', '492', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('890', null, '成功', '231', '2018-09-05 15:43:54', '496', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('891', null, '成功', '231', '2018-09-05 15:43:54', '495', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('892', null, '成功', '231', '2018-09-05 15:43:55', '497', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('893', null, '成功', '231', '2018-09-05 15:43:55', '491', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('894', null, '成功', '231', '2018-09-05 15:43:55', '501', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('895', null, '成功', '231', '2018-09-05 15:43:56', '500', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('896', null, '成功', '231', '2018-09-05 15:43:56', '494', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('897', null, '成功', '231', '2018-09-05 15:55:49', '508', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('898', null, '成功', '231', '2018-09-05 15:56:28', '508', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('899', 'ls -l /tmp/*.sh', '失败【 Incorrect Usage】', null, '2018-09-05 16:18:19', '502', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('900', 'ls -l /tmp/*.sh', '成功', null, '2018-09-05 16:18:20', '508', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('901', 'ls -l /tmp/*.sh', '失败【 Incorrect Usage】', null, '2018-09-05 16:18:20', '496', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('902', 'ls -l /tmp/*.sh', '失败【 Incorrect Usage】', null, '2018-09-05 16:18:21', '506', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('903', 'ls -l /tmp/*.sh', '失败【 Incorrect Usage】', null, '2018-09-05 16:18:23', '499', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('904', 'ls -l /tmp/*.sh', '失败【 Incorrect Usage】', null, '2018-09-05 16:18:23', '498', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('905', 'ls -l /tmp/*.sh', '失败【 Incorrect Usage】', null, '2018-09-05 16:18:23', '494', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('906', 'ls -l /tmp/*.sh', '失败【 Incorrect Usage】', null, '2018-09-05 16:18:24', '495', '1');
INSERT INTO `OPS_BASE_SERVER_EXEC_HIS` VALUES ('907', 'ls -l /tmp/*.sh', '失败【 Incorrect Usage】', null, '2018-09-05 16:18:24', '497', '1');

-- ----------------------------
-- Table structure for `OPS_BASE_SERVER_TEMP`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_BASE_SERVER_TEMP`;
CREATE TABLE `OPS_BASE_SERVER_TEMP` (
  `TPL_ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `TPL_NAME` varchar(50) DEFAULT NULL COMMENT '模板名称',
  `TPL_TYPE` varchar(50) DEFAULT NULL COMMENT '模板类型',
  `TPL_CONTENT` text COMMENT '模板内容',
  `TPL_DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`TPL_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=272 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='脚本执行模板';

-- ----------------------------
-- Records of OPS_BASE_SERVER_TEMP
-- ----------------------------
INSERT INTO `OPS_BASE_SERVER_TEMP` VALUES ('231', '清理发布环境', 'test', '#!/bin/bash\necho \"Start clear files ...\"\ncd /usr/local\nrm -rf action_* app_* tomcat_* server wf jdk*\n\nrm -rf /tmp/tomcat*\n\nrm -rf /data/logs/*\n\necho \"Clear completed.\"\nexit 0', '');
INSERT INTO `OPS_BASE_SERVER_TEMP` VALUES ('255', 'JDK1.8初始化', 'jdk1.8', '#!/bin/bash\n# $1 为服务名:[api,agent,admin,data,dist,login,mshop,pay,shop,web,message,taskserver]\n\nif [ ! -d \"/usr/local/jdk1.8\" ]; then\n	cd /tmp/\n    echo \"wget --ftp-user=virtualuser --ftp-password=12345678 ftp://192.168.100.2/init/jdk1.8.tar.gz\"\n	wget --ftp-user=virtualuser --ftp-password=12345678 ftp://192.168.100.2/init/jdk1.8.tar.gz\n    echo \"tar zxvf /tmp/jdk1.8.tar.gz  -C /usr/local/\"\n	tar zxvf /tmp/jdk1.8.tar.gz  -C /usr/local/\n\n	con1=\"export JAVA_HOME=/usr/local/jdk1.8\"\n	con2=\"export CLASSPATH=\\$JAVA_HOME/lib:\\$JAVA_HOME/jre/lib\"\n	con3=\"export PATH=$PATH:\\$JAVA_HOME/bin:\\$JAVA_HOME/jre/bin\"\n\n	echo $con1 >> /etc/profile\n	echo $con2 >> /etc/profile\n	echo $con3 >> /etc/profile\n	source  /etc/profile\nfi\necho \"success\"\nexit 0', null);
INSERT INTO `OPS_BASE_SERVER_TEMP` VALUES ('267', '添加日志目录', '添加日志目录', 'mkdir -p /data/logs/;\nmkdir -p /data/logs/;\nmkdir -p /data/logs/tomcat_admin_log;', null);
INSERT INTO `OPS_BASE_SERVER_TEMP` VALUES ('270', '彩票_admin_环境初始化', 'script', '#!/bin/bash\n# $1 为服务名:[api,agent,admin,data,dist,login,mshop,pay,shop,web,message,taskserver]\nAPP_NAME=\"admin\"\n\n#check and install wget\nfunction checkWget(){\n    which \"wget\" > /dev/null\n    if [[ $? -ne 0 ]]; then\n        cd /tmp/\n        yum install -y wget\n    fi\n}\n\n#check and install jdk1.7\nfunction checkJdk(){\n    if [ ! -d \"/usr/local/jdk1.7\" ]; then\n        cd /tmp/\n		if [ -f /tmp/jdk1.7.tar.gz ];then\n				echo \"Delete the old /tmp/jdk1.7.tar.gz\"\n				rm -rf /tmp/jdk1.7.tar.gz\n			fi\n        wget --ftp-user=virtualuser --ftp-password=12345678 ftp://192.168.100.2/init/jdk1.7.tar.gz\n        tar zxvf /tmp/jdk1.7.tar.gz  -C /usr/local/\n\n        con1=\"export JAVA_HOME=/usr/local/jdk1.7\"\n        con2=\"export CLASSPATH=\\$JAVA_HOME/lib:\\$JAVA_HOME/jre/lib\"\n        con3=\"export PATH=$PATH:\\$JAVA_HOME/bin:\\$JAVA_HOME/jre/bin\"\n\n        echo $con1 >> /etc/profile\n        echo $con2 >> /etc/profile\n        echo $con3 >> /etc/profile\n        source  /etc/profile\n    fi\n}\n\nfunction juadge(){\n    # 应用名称为message 或名称以task开头的应用仅创建目录\n	if [ ${APP_NAME} == \"message\" ]||[[ ${APP_NAME} =~ ^task.* ]];then\n	    echo \"mkdir -p /usr/local/app_${APP_NAME}\"\n	    mkdir -p /usr/local/app_${APP_NAME}\n	# tomcat应用下载 tomcat_${APP_NAME}.tar.gz 并解压\n	elif [ ! -d \"/usr/local/tomcat_${APP_NAME}\" ]; then\n        echo \"download /usr/local/tomcat_${APP_NAME}\"\n        cd /tmp/\n	    if [ -f /tmp/tomcat_${APP_NAME}.tar.gz ];then\n		    echo \"Delete the old /tmp/tomcat_${APP_NAME}.tar.gz\"\n		    rm -rf /tmp/tomcat_${APP_NAME}.tar.gz\n	    fi\n        wget --ftp-user=virtualuser --ftp-password=12345678 ftp://192.168.100.2/init/tomcat_package/tomcat_${APP_NAME}.tar.gz\n        wget --ftp-user=virtualuser --ftp-password=12345678 ftp://192.168.100.2/init/tomcat_exec_script/tomcat\n        tar zxvf /tmp/tomcat_${APP_NAME}.tar.gz  -C /usr/local/\n	fi\n}\n\n#check and download /usr/local/sbin/crononlog\ncheck_cronolog(){\n    file_name=\"cronolog\"\n    cronolog_path=\"/usr/local/sbin/${file_name}\"\n\n    if [ ! -f ${cronolog_path} ]; then\n        echo \"Download the file: ${cronolog_path}\"\n        cd /tmp/\n        wget --ftp-user=virtualuser --ftp-password=12345678 ftp://192.168.100.2/init/cronolog\n        mv /tmp/${file_name} ${cronolog_path}\n        chmod 755 ${cronolog_path}\n    fi\n}\n\necho \"=========================== Start  Initialing ${APP_NAME} ===========================\"\ncheckWget\ncheckJdk\njuadge\ncheck_cronolog\n\nmkdir -p /data/logs/\necho \"=========================== Finish Initialing ${APP_NAME} ===========================\"\nexit 0', '适用于彩票项目中admin应用的环境初始化过程');
INSERT INTO `OPS_BASE_SERVER_TEMP` VALUES ('271', '彩票_taskserver_环境初始化', 'script', '#!/bin/bash\n# $1 为服务名:[api,agent,admin,data,dist,login,mshop,pay,shop,web,message,taskserver]\nAPP_NAME=\"taskserver\"\n\n#check and install wget\nfunction checkWget(){\n    which \"wget\" > /dev/null\n    if [[ $? -ne 0 ]]; then\n        cd /tmp/\n        yum install -y wget\n    fi\n}\n\n#check and install jdk1.7\nfunction checkJdk(){\n    if [ ! -d \"/usr/local/jdk1.7\" ]; then\n        cd /tmp/\n		if [ -f /tmp/jdk1.7.tar.gz ];then\n				echo \"Delete the old /tmp/jdk1.7.tar.gz\"\n				rm -rf /tmp/jdk1.7.tar.gz\n			fi\n        wget --ftp-user=virtualuser --ftp-password=12345678 ftp://192.168.100.2/init/jdk1.7.tar.gz\n        tar zxvf /tmp/jdk1.7.tar.gz  -C /usr/local/\n\n        con1=\"export JAVA_HOME=/usr/local/jdk1.7\"\n        con2=\"export CLASSPATH=\\$JAVA_HOME/lib:\\$JAVA_HOME/jre/lib\"\n        con3=\"export PATH=$PATH:\\$JAVA_HOME/bin:\\$JAVA_HOME/jre/bin\"\n\n        echo $con1 >> /etc/profile\n        echo $con2 >> /etc/profile\n        echo $con3 >> /etc/profile\n        source  /etc/profile\n    fi\n}\n\nfunction juadge(){\n    # 应用名称为message 或名称以task开头的应用仅创建目录\n	if [ ${APP_NAME} == \"message\" ]||[[ ${APP_NAME} =~ ^task.* ]];then\n	    echo \"mkdir -p /usr/local/app_${APP_NAME}\"\n	    mkdir -p /usr/local/app_${APP_NAME}\n	# tomcat应用下载 tomcat_${APP_NAME}.tar.gz 并解压\n	elif [ ! -d \"/usr/local/tomcat_${APP_NAME}\" ]; then\n        echo \"download /usr/local/tomcat_${APP_NAME}\"\n        cd /tmp/\n	    if [ -f /tmp/tomcat_${APP_NAME}.tar.gz ];then\n		    echo \"Delete the old /tmp/tomcat_${APP_NAME}.tar.gz\"\n		    rm -rf /tmp/tomcat_${APP_NAME}.tar.gz\n	    fi\n        wget --ftp-user=virtualuser --ftp-password=12345678 ftp://192.168.100.2/init/tomcat_package/tomcat_${APP_NAME}.tar.gz\n        wget --ftp-user=virtualuser --ftp-password=12345678 ftp://192.168.100.2/init/tomcat_exec_script/tomcat\n        tar zxvf /tmp/tomcat_${APP_NAME}.tar.gz  -C /usr/local/\n	fi\n}\n\n#check and download /usr/local/sbin/crononlog\ncheck_cronolog(){\n    file_name=\"cronolog\"\n    cronolog_path=\"/usr/local/sbin/${file_name}\"\n\n    if [ ! -f ${cronolog_path} ]; then\n        echo \"Download the file: ${cronolog_path}\"\n        cd /tmp/\n        wget --ftp-user=virtualuser --ftp-password=12345678 ftp://192.168.100.2/init/cronolog\n        mv /tmp/${file_name} ${cronolog_path}\n        chmod 755 ${cronolog_path}\n    fi\n}\n\necho \"=========================== Start  Initialing ${APP_NAME} ===========================\"\ncheckWget\ncheckJdk\njuadge\ncheck_cronolog\n\nmkdir -p /data/logs/\necho \"=========================== Finish Initialing ${APP_NAME} ===========================\"\nexit 0', '适用于彩票项目中taskserver应用的环境初始化过程');

-- ----------------------------
-- Table structure for `OPS_BASE_SERVER_TYPE`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_BASE_SERVER_TYPE`;
CREATE TABLE `OPS_BASE_SERVER_TYPE` (
  `ASSETS_TYPE_ID` bigint(11) unsigned NOT NULL COMMENT '服务器类型编号',
  `ASSETS_TYPE_NAME` varchar(30) NOT NULL COMMENT '服务器类型名称',
  PRIMARY KEY (`ASSETS_TYPE_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of OPS_BASE_SERVER_TYPE
-- ----------------------------
INSERT INTO `OPS_BASE_SERVER_TYPE` VALUES ('0', '物理机');
INSERT INTO `OPS_BASE_SERVER_TYPE` VALUES ('1', '云服务器');
INSERT INTO `OPS_BASE_SERVER_TYPE` VALUES ('2', '容器');

-- ----------------------------
-- Table structure for `OPS_BASE_ZONE_TYPE`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_BASE_ZONE_TYPE`;
CREATE TABLE `OPS_BASE_ZONE_TYPE` (
  `ZONE_TYPE_ID` bigint(10) DEFAULT NULL,
  `ZONE_TYPE_NAME` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OPS_BASE_ZONE_TYPE
-- ----------------------------
INSERT INTO `OPS_BASE_ZONE_TYPE` VALUES ('100', 'dev_zone');

-- ----------------------------
-- Table structure for `OPS_COM_GIT_HOOK`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_COM_GIT_HOOK`;
CREATE TABLE `OPS_COM_GIT_HOOK` (
  `GIT_MSG_ID` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `APP_ID` bigint(11) unsigned DEFAULT NULL COMMENT '应用ID',
  `ENV_ID` bigint(11) unsigned DEFAULT NULL COMMENT '环境ID',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `MSG_TYPE` varchar(50) DEFAULT NULL,
  `MSG_STATUS` int(11) DEFAULT NULL COMMENT '消息状态',
  `COMMITS_COUNT` int(11) DEFAULT NULL COMMENT '提交次数',
  `COMMITS_CONTENT` blob COMMENT '提交内容',
  PRIMARY KEY (`GIT_MSG_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='GITLAB推送消息表';

-- ----------------------------
-- Records of OPS_COM_GIT_HOOK
-- ----------------------------

-- ----------------------------
-- Table structure for `OPS_CONFIG`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_CONFIG`;
CREATE TABLE `OPS_CONFIG` (
  `RECORD_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `RECORD_STATUS` int(4) DEFAULT NULL COMMENT '配置文件审核状态，0：未提交，1：已提交，2：审核通过，-1：审核失败',
  `APP_ENV_ID` bigint(20) unsigned DEFAULT NULL COMMENT '应用环境ID',
  `BRANCH_NAME` varchar(100) DEFAULT NULL COMMENT '分支名称',
  `EDITOR_ID` bigint(20) unsigned DEFAULT NULL COMMENT '配置修改人员',
  `COMMIT_DATE` datetime DEFAULT NULL COMMENT '配置提交时间',
  `AUDITOR_ID` bigint(20) unsigned DEFAULT NULL COMMENT '配置审核人员',
  `AUDIT_DATE` datetime DEFAULT NULL COMMENT '配置审核时间',
  `BEGIN_SHA` varchar(100) DEFAULT NULL COMMENT '分支开始提交记录SHA',
  `END_SHA` varchar(100) DEFAULT NULL COMMENT '分支结束提交记录SHA',
  `EDITOR_NAME` varchar(50) DEFAULT NULL COMMENT '配置修改人员名称',
  `AUDITOR_NAME` varchar(50) DEFAULT NULL COMMENT '配置审核人员名称',
  `COMMIT_MESSAGE` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`RECORD_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=245 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of OPS_CONFIG
-- ----------------------------
INSERT INTO `OPS_CONFIG` VALUES ('178', '-1', '283', 'admin20180825113959', '1', '2018-08-25 11:45:50', '1', '2018-08-28 16:13:29', '6ccf5fec337bebec346663c281df32d1c2bbb907', 'd11fe3bfe4234fef08e466be0449ecefb1d141f4', 'admin', 'admin', '第一次提交失败，再次提价1. 添加备注信息2. 删除1个新增的配置文件。');
INSERT INTO `OPS_CONFIG` VALUES ('179', '0', '274', 'admin20180825141827', '1', '2018-08-25 14:19:19', null, null, 'a59cadb65cb86087c73e1740669608fc67d5c8f4', 'a59cadb65cb86087c73e1740669608fc67d5c8f4', 'admin', null, null);
INSERT INTO `OPS_CONFIG` VALUES ('180', '2', '274', 'admin20180825142122', '1', '2018-08-25 14:22:00', '1', '2018-08-25 14:22:58', 'f069b8075fe38fba5e1caedd05c828c27991e754', 'f069b8075fe38fba5e1caedd05c828c27991e754', 'admin', 'admin', '14:22');
INSERT INTO `OPS_CONFIG` VALUES ('181', '0', '288', 'admin20180825172608', '1', '2018-08-25 17:27:12', null, null, '44cbb57ce0c778ae561fe1d5b876797bb973cc07', '44cbb57ce0c778ae561fe1d5b876797bb973cc07', 'admin', null, null);
INSERT INTO `OPS_CONFIG` VALUES ('182', '2', '288', 'admin20180825172857', '1', '2018-08-25 17:29:19', '1', '2018-08-25 17:30:34', 'b21ae825adaa849b59c49f9aff738559f27ecf6c', 'b21ae825adaa849b59c49f9aff738559f27ecf6c', 'admin', 'admin', '17:29');
INSERT INTO `OPS_CONFIG` VALUES ('183', '0', '361', 'admin20180827110032', '1', '2018-08-27 11:01:05', null, null, 'bd1c673216e641312cadbc98f4db7a1521f19a92', 'bd1c673216e641312cadbc98f4db7a1521f19a92', 'admin', null, null);
INSERT INTO `OPS_CONFIG` VALUES ('184', '0', '286', 'admin20180827110425', '1', '2018-08-27 11:08:53', null, null, 'a700681bf8a084b99ea4c52337120b141707dae0', '2e061b35aebe803ea0c04b76ce69cfcb0201ba2c', 'admin', null, null);
INSERT INTO `OPS_CONFIG` VALUES ('185', '-1', '274', 'admin20180827111203', '1', '2018-08-27 11:12:14', '1', '2018-08-27 11:13:03', '4453c76d05d090844d3b61934b18ec0c8b106462', '4453c76d05d090844d3b61934b18ec0c8b106462', 'admin', 'admin', 'admin');
INSERT INTO `OPS_CONFIG` VALUES ('186', '-1', '284', 'admin20180827111322', '1', '2018-08-27 11:13:40', '1', '2018-08-27 11:14:21', '44cbc859e7261c596c63615c121d12fc0ed438c9', '44cbc859e7261c596c63615c121d12fc0ed438c9', 'admin', 'admin', 'data');
INSERT INTO `OPS_CONFIG` VALUES ('187', '-1', '277', 'admin20180827111443', '1', '2018-08-27 11:18:50', '1', '2018-08-27 11:18:32', 'c6c943a75682eb90be46973d4ea1074d006cfef7', 'ad3a973dc79a19deb979918027fa8cefa8c5cad9', 'admin', 'admin', 'www');
INSERT INTO `OPS_CONFIG` VALUES ('188', '-1', '286', 'admin20180827111550', '1', '2018-08-27 16:16:03', '1', '2018-08-28 16:13:26', '32d9f0ec41e2a6f4e88b9b6e416bd9f5b5795c5b', 'e9ac7a86ccee9627287892783e84f93fe4891ed2', 'admin', 'admin', '8/27\n<16:15>');
INSERT INTO `OPS_CONFIG` VALUES ('189', '-1', '277', 'admin20180827111755', '1', '2018-08-27 11:18:02', '1', '2018-08-27 11:18:29', '3c19d1f8ce495d11baad294756ad04127aa718f8', '3c19d1f8ce495d11baad294756ad04127aa718f8', 'admin', 'admin', 'wwww');
INSERT INTO `OPS_CONFIG` VALUES ('190', '0', '286', 'admin20180827112050', '1', '2018-08-27 11:21:25', null, null, 'ede65cd821d0419a58d376f045304f27424a18db', 'ede65cd821d0419a58d376f045304f27424a18db', 'admin', null, null);
INSERT INTO `OPS_CONFIG` VALUES ('191', '-1', '274', 'admin20180827112412', '1', '2018-08-27 11:25:51', '1', '2018-08-27 13:42:23', 'f74e715a0f7bb63d19bbe9773a0cb4f0af2163aa', '46b64f025f1c6b760c274c31128f67e807e65fd8', 'admin', 'admin', '123');
INSERT INTO `OPS_CONFIG` VALUES ('192', '-1', '274', 'admin20180827120147', '1', '2018-08-27 13:42:58', '1', '2018-08-27 13:43:35', '4d2572de2d0144dcbcc3ab6cf68303c120c9b401', '73b54667f75c1abe0120f4df17229d6e51bde691', 'admin', 'admin', '这是一个字符串测试信息\n测试一下换行\n再测试一下特殊字符~！@#WE￥%……&*（）——\nem.....还有顶格 \\n \\n      \\n     \\r\\n ?\n  这是一个字符串测试信息\n测试一下换行\n再测试一下特殊字符~！@#WE￥%……&*（）——\nem.....还有顶格 \\n \\n      \\n     \\r\\n/');
INSERT INTO `OPS_CONFIG` VALUES ('194', '2', '274', 'admin20180828160842', '1', '2018-08-28 16:09:45', '1', '2018-08-28 16:12:42', '176f5e6a0baa75a376df4999be0157876ffef980', '67c18fad484cf170545fc0a2180f24a9e9165668', 'admin', 'admin', '制定配置版本\n8/28 16:11');
INSERT INTO `OPS_CONFIG` VALUES ('195', '2', '283', 'admin20180828161439', '1', '2018-08-28 16:15:57', '1', '2018-08-28 16:17:04', 'a148830656a0791bb01114b5f034f9db5981e181', '74ab5b5b13373d24969797e575a9ec54772bb00a', 'admin', 'admin', '8/28 16:16\n制定彩票pay配置版本');
INSERT INTO `OPS_CONFIG` VALUES ('196', '2', '274', 'admin20180828195121', '1', '2018-08-28 19:52:49', '1', '2018-08-28 19:54:30', 'a86bd13da40f46ab4cbbf4cf5ed994bb2c981958', 'db6707ae72534f76bb678df57ecb7ffee30a8ebe', 'admin', 'admin', '8/28 19:53\n制定配置版本\n删除配置文件\n修改配置文件');
INSERT INTO `OPS_CONFIG` VALUES ('197', '-1', '286', 'admin20180828202103', '1', '2018-08-28 20:27:47', '1', '2018-08-28 20:28:52', '37bceb6a6f05cd507e65b5f059d0567b7c842743', 'a5a63ca704cb76bc68243c4c8f3968ee5790b85c', 'admin', 'admin', '8/28\n<20:28>');
INSERT INTO `OPS_CONFIG` VALUES ('198', '0', '274', 'admin20180828204054', '1', '2018-08-28 20:51:44', null, null, '7e0b4dae80948c5f32705153034c88fe12ae14ad', '44023386f61458f2c2ec1b8935f7797c8204b605', 'admin', null, null);
INSERT INTO `OPS_CONFIG` VALUES ('200', '0', '274', 'admin20180828211953', '1', '2018-08-28 21:27:59', null, null, '5dd64e071272b1986a7518909f0102bf1aaf4640', '08ad3d1dc7e904aa83d441af615fc2a1b3297866', 'admin', null, null);
INSERT INTO `OPS_CONFIG` VALUES ('201', '0', '293', 'admin20180829094156', '1', '2018-08-29 09:42:05', null, null, '30d843867b7d04b8d9954012974be9fcc3f704ae', '30d843867b7d04b8d9954012974be9fcc3f704ae', 'admin', null, null);
INSERT INTO `OPS_CONFIG` VALUES ('202', '2', '293', 'admin20180829094540', '1', '2018-08-29 09:45:47', '1', '2018-08-29 09:46:33', '845f198e95007d806d5a554829ec4d910df6e8d9', '845f198e95007d806d5a554829ec4d910df6e8d9', 'admin', 'admin', '测试');
INSERT INTO `OPS_CONFIG` VALUES ('203', '2', '293', 'admin20180829094748', '1', '2018-08-29 09:47:58', '1', '2018-08-29 09:48:17', 'b7bb16da72cd5f1d0d732cb5fca974fb432bef66', 'b7bb16da72cd5f1d0d732cb5fca974fb432bef66', 'admin', 'admin', '测试');
INSERT INTO `OPS_CONFIG` VALUES ('204', '2', '293', 'admin20180829094844', '1', '2018-08-29 09:48:54', '1', '2018-08-29 09:49:18', '251b8e5f5c30104387cf5a0c362e75d3dd7a1175', '251b8e5f5c30104387cf5a0c362e75d3dd7a1175', 'admin', 'admin', '11');
INSERT INTO `OPS_CONFIG` VALUES ('205', '2', '293', 'admin20180829095110', '1', '2018-08-29 09:51:31', '1', '2018-08-29 09:52:36', '185b3709350194503b32980859af7acd9a7ac614', 'b36b6a146a2325d81575fec90369b584f884938f', 'admin', 'admin', '删除测试增加的文件');
INSERT INTO `OPS_CONFIG` VALUES ('208', '0', '275', 'admin20180829165851', '1', '2018-08-29 17:06:41', null, null, 'bf731ce174402d63333f6268429edf4ca38fef3d', 'bf731ce174402d63333f6268429edf4ca38fef3d', 'admin', null, null);
INSERT INTO `OPS_CONFIG` VALUES ('209', '1', '275', 'admin20180829172647', '1', '2018-08-29 17:26:54', null, null, '36dd092c0071829c3674b319408fe7d046c99ab0', '36dd092c0071829c3674b319408fe7d046c99ab0', 'admin', null, '8/27 ');
INSERT INTO `OPS_CONFIG` VALUES ('210', '2', '274', 'admin20180830163942', '1', '2018-08-30 16:55:42', '1', '2018-08-30 16:56:41', 'd58c797a82098e7298ca4d01c729de23756a4ff9', 'e3356c56445ff6ca3e488f2909f00d46fd9566d0', 'admin', 'admin', '8/30\n./<16:56>');
INSERT INTO `OPS_CONFIG` VALUES ('211', '2', '274', 'admin20180830185957', '1', '2018-08-30 19:00:25', '1', '2018-08-30 19:01:12', '7bba65a93561d2b5fbb73e0a05755b851db11f75', '7bba65a93561d2b5fbb73e0a05755b851db11f75', 'admin', 'admin', '19:00');
INSERT INTO `OPS_CONFIG` VALUES ('212', '2', '288', 'admin20180830200119', '1', '2018-08-30 20:02:30', '1', '2018-08-30 20:05:17', '02e59c91361e7f13d556cccedc85b42f038be098', 'e0135c69e6b782221ee53aa76a8878b3d59cc951', 'admin', 'admin', '8/30 20:03');
INSERT INTO `OPS_CONFIG` VALUES ('213', '1', '288', 'admin20180831144335', '1', '2018-08-31 14:45:29', null, null, '0350ab10f74e1961081e14a6f3c2de1a5a1973c2', 'bffcdbf97d0e11224d5f50e51a63a0d2a43e2227', 'admin', null, '修改配置文件\n1.新增配置文件\n2.删除配置文件\n3.修改配置文件\n2018-08-31 14:46');
INSERT INTO `OPS_CONFIG` VALUES ('214', '2', '288', 'admin20180831144717', '1', '2018-08-31 14:51:08', '1', '2018-08-31 14:52:59', '0533c786e362496e5f781fe707c55601aedcb76a', '7e7c90b2334db5d892f2ee03e2a66964ed7fe0c7', 'admin', 'admin', '修改配置文件\n1. 修改配置文件，与上次修改文档不同\n2. 修改配置文件，与上次修改文档相同，当修改地方不一样\n# 2018-08-30 14:52');
INSERT INTO `OPS_CONFIG` VALUES ('215', '2', '289', 'admin20180831145530', '1', '2018-08-31 14:56:50', '1', '2018-08-31 14:58:21', 'f10c6c4643c371f5fa403cce58fb915f0d2adf00', 'ad87bffa4d67339dc65c6015664b3ce6c6268dff', 'admin', 'admin', '修改配置文档\n1. 增加配置文档\n2. 编辑配置文档\n2018-08-31 14:58');
INSERT INTO `OPS_CONFIG` VALUES ('216', '-1', '289', 'admin20180831150058', '1', '2018-08-31 15:25:51', '1', '2018-08-31 19:27:55', '28a329c0f63e03c85c7ade82eef54a1b223dcd14', '15972db2af97b623657a551d7fc6115bd91e205e', 'admin', 'admin', '2018-08-31 15:26');
INSERT INTO `OPS_CONFIG` VALUES ('217', '2', '289', 'admin20180831151220', '1', '2018-08-31 15:12:43', '1', '2018-08-31 15:23:04', '4ca135da34066fce4438a5249ea28020c1fea879', '4ca135da34066fce4438a5249ea28020c1fea879', 'admin', 'admin', '修改配置文档\n2018-08-31 15:13');
INSERT INTO `OPS_CONFIG` VALUES ('218', '2', '289', 'admin20180831152751', '1', '2018-08-31 15:29:23', '1', '2018-08-31 15:29:59', '0b8dbdd75c3a8259680df719a55dba1dc85abe76', '8f9a66bffa33961f15bf5f19c81465ed1c61d4a5', 'admin', 'admin', '08-31 15:31');
INSERT INTO `OPS_CONFIG` VALUES ('219', '2', '289', 'admin20180831153056', '1', '2018-08-31 15:31:06', '1', '2018-08-31 15:32:32', '2c9451b8c5b1dde56ee868464224a341ab15669a', '2c9451b8c5b1dde56ee868464224a341ab15669a', 'admin', 'admin', '08-31 15:31 ');
INSERT INTO `OPS_CONFIG` VALUES ('220', '2', '289', 'admin20180831153129', '1', '2018-08-31 15:31:58', '1', '2018-08-31 15:32:31', '4cb276e6eaf89b661668609ad1e1de8497ae640b', '4cb276e6eaf89b661668609ad1e1de8497ae640b', 'admin', 'admin', '08-31 15:32');
INSERT INTO `OPS_CONFIG` VALUES ('221', '2', '290', 'admin20180831154053', '1', '2018-08-31 15:41:22', '1', '2018-08-31 15:43:44', '6ed0afeab8baf928ee8e65df0707ca61b1dc8266', '6ed0afeab8baf928ee8e65df0707ca61b1dc8266', 'admin', 'admin', '08-31 15:42');
INSERT INTO `OPS_CONFIG` VALUES ('222', '2', '290', 'admin20180831154215', '1', '2018-08-31 15:43:09', '1', '2018-08-31 15:43:43', '3ed2cdffa97ac0888a40696952c1e3b3b7a21c4b', '3ed2cdffa97ac0888a40696952c1e3b3b7a21c4b', 'admin', 'admin', '修改配置文档，分支号：1154215 0831-15:43');
INSERT INTO `OPS_CONFIG` VALUES ('223', '2', '290', 'admin20180831154448', '1', '2018-08-31 15:45:47', '1', '2018-08-31 15:49:31', '6e956571765b4eb248d14b6ec3a9d34f3e627c18', '6e956571765b4eb248d14b6ec3a9d34f3e627c18', 'admin', 'admin', '修改配置文件，分支号154448 08-31 15:45');
INSERT INTO `OPS_CONFIG` VALUES ('224', '2', '290', 'admin20180831154611', '1', '2018-08-31 15:48:26', '1', '2018-08-31 15:49:29', '242e21d58a552db1cdf47de2e5840870f52b9da5', '58b4aa53b443045c1be65fcd8fe8793e23e5a143', 'admin', 'admin', '修改配置文件 08-31 15:47 分支号：154611\n新增文档\n修改文档');
INSERT INTO `OPS_CONFIG` VALUES ('225', '-1', '289', 'admin20180831192859', '1', '2018-08-31 19:29:22', '1', '2018-08-31 19:38:31', '00627511a7c699ac3850aba51f2852a8f3e3b10d', '3f99588be65843d036e5b1cc9cbdfac7bd124bb2', 'admin', 'admin', '修改配置文件\n1.删除文件add_file-001\n2.修改文件add_file-003\n分支号为：192859\n08-31 19:30');
INSERT INTO `OPS_CONFIG` VALUES ('226', '2', '289', 'admin20180831193046', '1', '2018-08-31 19:31:17', '1', '2018-08-31 19:37:41', 'c828061c8d870168bed532ca5424f29fec7419a0', 'c828061c8d870168bed532ca5424f29fec7419a0', 'admin', 'admin', '修改配置文件\n1.修改配置文件，add_file-003 修改的地方与上个分支不相同\n分支号：193046\n08-31 19:32');
INSERT INTO `OPS_CONFIG` VALUES ('227', '2', '290', 'admin20180903144651', '1', '2018-09-03 14:47:33', '1', '2018-09-03 14:48:19', '90a3c3a345c1986dabdb88c1c847e9b4f858681b', '90a3c3a345c1986dabdb88c1c847e9b4f858681b', 'admin', 'admin', '09-03 14:47 修改文档一次');
INSERT INTO `OPS_CONFIG` VALUES ('228', '2', '290', 'admin20180903144845', '1', '2018-09-03 14:49:11', '1', '2018-09-03 14:51:14', '8304ee928bd5d2e311f85c8e03edd96b5ef6f00a', '8304ee928bd5d2e311f85c8e03edd96b5ef6f00a', 'admin', 'admin', '09-03 14:49 修改文档 第二次');
INSERT INTO `OPS_CONFIG` VALUES ('229', '2', '290', 'admin20180903144940', '1', '2018-09-03 14:50:17', '1', '2018-09-03 14:51:15', '574d22a7c63e9d9ce0d50bf944b3528e62fa8622', '574d22a7c63e9d9ce0d50bf944b3528e62fa8622', 'admin', 'admin', '09-03 14:50 修改文档 第二次 2');
INSERT INTO `OPS_CONFIG` VALUES ('230', '2', '290', 'admin20180903145558', '1', '2018-09-03 14:56:17', '1', '2018-09-03 15:07:25', 'e5e7ad2970db32115ea75eda52de86dc2e17accd', 'e5e7ad2970db32115ea75eda52de86dc2e17accd', 'admin', 'admin', '09-03 14:56 修改文档 第3次 1');
INSERT INTO `OPS_CONFIG` VALUES ('231', '2', '290', 'admin20180903145634', '1', '2018-09-03 14:56:45', '1', '2018-09-03 15:06:06', '0511bd5709957304a686d77ed9e448139fbd85e8', '0511bd5709957304a686d77ed9e448139fbd85e8', 'admin', 'admin', '09-03 14:56 修改文档 第3次 2');
INSERT INTO `OPS_CONFIG` VALUES ('232', '2', '290', 'admin20180903154219', '1', '2018-09-03 15:42:37', '1', '2018-09-03 15:43:25', '14fb8a8477dd0bc1fa5a6d98ff000de12926f728', '14fb8a8477dd0bc1fa5a6d98ff000de12926f728', 'admin', 'admin', '09-03 14:56 修改文档 第4次 1');
INSERT INTO `OPS_CONFIG` VALUES ('233', '2', '290', 'admin20180903154252', '1', '2018-09-03 15:43:02', '1', '2018-09-03 15:43:20', '503b6146a51bc8859c053dd77676e8f736e2d2ed', '503b6146a51bc8859c053dd77676e8f736e2d2ed', 'admin', 'admin', '09-03 14:56 修改文档 第4次 2');
INSERT INTO `OPS_CONFIG` VALUES ('234', '1', '274', 'admin20180903155330', '1', '2018-09-03 17:05:40', null, null, '671b95bde4ccf1b32518439071cb7c604fcfeae0', '8036f0c24114df052e4c1a3c273a0767b9615348', 'admin', null, '\"12\"3123\"');
INSERT INTO `OPS_CONFIG` VALUES ('235', '2', '290', 'admin20180903163323', '1', '2018-09-03 16:33:45', '1', '2018-09-03 16:37:00', '342d37acd1c8014e9ba17808f7e6766fa8fe9427', '342d37acd1c8014e9ba17808f7e6766fa8fe9427', 'admin', 'admin', '09-03 16:33 修改文档 第5次 1');
INSERT INTO `OPS_CONFIG` VALUES ('236', '2', '290', 'admin20180903163357', '1', '2018-09-03 16:34:07', '1', '2018-09-03 16:34:41', '85a0c229022d477f7b9482d1550a279b8f728107', '85a0c229022d477f7b9482d1550a279b8f728107', 'admin', 'admin', '09-03 16:33 修改文档 第5次 2');
INSERT INTO `OPS_CONFIG` VALUES ('237', '2', '290', 'admin20180903163544', '1', '2018-09-03 16:36:31', '1', '2018-09-03 16:36:59', 'e2a1e6afc06d8ea9d6fc2a47c6e2b73e9652c8a2', 'e2a1e6afc06d8ea9d6fc2a47c6e2b73e9652c8a2', 'admin', 'admin', '09-03 16:36 修改文档 第5次 3');
INSERT INTO `OPS_CONFIG` VALUES ('238', '-1', '290', 'admin20180903163857', '1', '2018-09-03 16:43:17', '1', '2018-09-03 16:48:26', '4ae15b48986037b22b8743233eb9f3b8a5b73447', '5e4b273db4f24c57597205e8173d643c60056210', 'admin', 'admin', '09-03 16:43 修改文档 第6次 2');
INSERT INTO `OPS_CONFIG` VALUES ('239', '2', '290', 'admin20180903164350', '1', '2018-09-03 16:44:01', '1', '2018-09-03 16:44:17', '49292e681e39133bd7e30a7e2972f2e07f73375d', '49292e681e39133bd7e30a7e2972f2e07f73375d', 'admin', 'admin', '09-03 16:43 修改文档 第6次 33');
INSERT INTO `OPS_CONFIG` VALUES ('240', '2', '290', 'admin20180903164857', '1', '2018-09-03 16:49:21', '1', '2018-09-03 16:50:16', 'c2ba0496aa611f464afc11f501815ff754fc8aa7', 'c2ba0496aa611f464afc11f501815ff754fc8aa7', 'admin', 'admin', '09-03 16:49 修改文档 第7次 1');
INSERT INTO `OPS_CONFIG` VALUES ('241', '2', '290', 'admin20180903164936', '1', '2018-09-03 16:49:50', '1', '2018-09-03 16:50:11', '6f6654a72b6685f1109672ca09488a6aedb58d61', '6f6654a72b6685f1109672ca09488a6aedb58d61', 'admin', 'admin', '09-03 16:50 修改文档 第7次 2');
INSERT INTO `OPS_CONFIG` VALUES ('242', '0', '273', 'admin20180903171802', '1', '2018-09-03 17:18:16', null, null, 'f740af8175fcc096dea3e77e95ebf8f9b86045fd', 'f740af8175fcc096dea3e77e95ebf8f9b86045fd', 'admin', null, null);
INSERT INTO `OPS_CONFIG` VALUES ('243', '2', '272', 'admin20180904103507', '1', '2018-09-04 10:35:24', '1', '2018-09-04 10:35:47', 'aaf5fc5be4263eaf00ad495b7354dab24c961b57', 'aaf5fc5be4263eaf00ad495b7354dab24c961b57', 'admin', 'admin', '生成配置版本');
INSERT INTO `OPS_CONFIG` VALUES ('244', '1', '290', 'admin20180904200011', '1', '2018-09-04 20:00:41', null, null, '7c98772ceafb0c437375a3bbb05a6cf65eb5f8a6', '7c98772ceafb0c437375a3bbb05a6cf65eb5f8a6', 'admin', null, '\"提交审核\"');

-- ----------------------------
-- Table structure for `OPS_DEP_APP`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_DEP_APP`;
CREATE TABLE `OPS_DEP_APP` (
  `PUBLISH_ID` bigint(10) NOT NULL AUTO_INCREMENT,
  `DEPLOY_VERSION_ID` bigint(10) DEFAULT NULL,
  `DEPLOY_VERSION_CODE` varchar(255) DEFAULT NULL,
  `DEPLOY_TYPE` int(4) DEFAULT NULL,
  `PUBLISH_STATUS` int(4) DEFAULT NULL,
  `AUTO_RESTART` int(4) DEFAULT NULL,
  `SERVER_ID` bigint(10) DEFAULT NULL,
  `APP_ENV_ID` bigint(10) DEFAULT NULL,
  `PUBLISH_DATE` datetime DEFAULT NULL,
  `PUBLISH_INFO` varchar(1024) DEFAULT NULL COMMENT '发布信息',
  `OPERATE_USER_ID` bigint(10) DEFAULT NULL COMMENT '操作用户ID',
  PRIMARY KEY (`PUBLISH_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=793 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of OPS_DEP_APP
-- ----------------------------

-- ----------------------------
-- Table structure for `OPS_DEP_APP_HIS`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_DEP_APP_HIS`;
CREATE TABLE `OPS_DEP_APP_HIS` (
  `PUBLISH_ID` bigint(10) NOT NULL AUTO_INCREMENT,
  `DEPLOY_VERSION_ID` bigint(10) DEFAULT NULL,
  `DEPLOY_VERSION_CODE` varchar(255) DEFAULT NULL,
  `DEPLOY_TYPE` int(4) DEFAULT NULL COMMENT '发布方式',
  `PUBLISH_STATUS` int(4) DEFAULT NULL,
  `AUTO_RESTART` int(4) DEFAULT NULL,
  `SERVER_ID` bigint(10) DEFAULT NULL,
  `APP_ENV_ID` bigint(10) DEFAULT NULL,
  `PUBLISH_CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP,
  `OPERATE_USER_ID` bigint(10) DEFAULT NULL COMMENT '操作用户ID',
  `PUBLISH_INFO` varchar(1024) DEFAULT NULL COMMENT '发布信息',
  PRIMARY KEY (`PUBLISH_ID`) USING BTREE,
  KEY `index_publishtime` (`PUBLISH_CREATE_TIME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of OPS_DEP_APP_HIS
-- ----------------------------

-- ----------------------------
-- Table structure for `OPS_DEP_APP_ORDER`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_DEP_APP_ORDER`;
CREATE TABLE `OPS_DEP_APP_ORDER` (
  `ORDER_ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '发布工单ID',
  `ENV_ID` bigint(10) NOT NULL COMMENT '环境ID',
  `PRO_ID` bigint(10) NOT NULL COMMENT '项目ID',
  `ORDER_TITLE` varchar(255) NOT NULL COMMENT '发布工单标题',
  `OPERATE_USER_ID` bigint(10) NOT NULL COMMENT '工单创建者ID',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ORDER_STATUS` int(11) NOT NULL COMMENT '工单状态 0为未处理，100为发布中，200为全部成功，-100发布中有失败，-200发布结束全部失败',
  `ORDER_DETAILS` text NOT NULL COMMENT '工单详细信息',
  PRIMARY KEY (`ORDER_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=579 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='发布工单信息表';

-- ----------------------------
-- Records of OPS_DEP_APP_ORDER
-- ----------------------------
INSERT INTO `OPS_DEP_APP_ORDER` VALUES ('549', '134', '49', '发布pay应用  单台服务器', '1', '2018-08-25 14:26:26', '-200', '<p>版本：2500</p>\n<p>单台服务器</p>');
INSERT INTO `OPS_DEP_APP_ORDER` VALUES ('550', '134', '49', '发布admin应用  单台服务器', '1', '2018-08-25 14:27:02', '200', '<p>版本：2500</p>\n<p>单台服务器</p>');
INSERT INTO `OPS_DEP_APP_ORDER` VALUES ('556', '134', '49', '发布admin应用  三台服务器并行', '1', '2018-08-25 14:31:42', '200', '<p>版本：2500</p>\n<p>三台服务器 并行发布</p>');
INSERT INTO `OPS_DEP_APP_ORDER` VALUES ('557', '134', '49', '发布admin应用  三台服务器串行', '1', '2018-08-25 14:32:13', '200', '<p>版本：2500</p>\n<p>三台服务器 串行发布</p>');
INSERT INTO `OPS_DEP_APP_ORDER` VALUES ('558', '134', '52', '发布admin应用  单台服务器', '1', '2018-08-25 17:33:48', '-200', '<p>发布admin应用 单台服务器</p>');
INSERT INTO `OPS_DEP_APP_ORDER` VALUES ('559', '134', '52', '发布admin应用  三台服务器 并行', '1', '2018-08-25 17:34:19', '-200', '<p>发布admin应用 三台服务器 并行</p>');
INSERT INTO `OPS_DEP_APP_ORDER` VALUES ('560', '134', '52', '发布admin应用  三台服务器 串行', '1', '2018-08-25 17:34:44', '0', '<p>发布admin应用 三台服务器 串行</p>');
INSERT INTO `OPS_DEP_APP_ORDER` VALUES ('561', '134', '52', 'static应用测试 赢D-app-100.81', '1', '2018-08-28 14:21:37', '200', '<p>static应用测试 赢D-app-100.81</p>');
INSERT INTO `OPS_DEP_APP_ORDER` VALUES ('562', '134', '49', 'v_20180828_00 发布admin应用  单台服务器', '1', '2018-08-28 16:27:17', '200', '<p>版本：v_20180828_00</p>\n<p>单台服务器</p>');
INSERT INTO `OPS_DEP_APP_ORDER` VALUES ('563', '134', '49', 'v_20180828_00 发布pay应用  单台服务器', '1', '2018-08-28 16:28:36', '-200', '<p>版本：v_20180828_00</p>\n<p>单台服务器</p>');
INSERT INTO `OPS_DEP_APP_ORDER` VALUES ('564', '134', '49', 'v_20180828_00 发布admin应用  三台服务器并行', '1', '2018-08-28 16:43:04', '200', '<p>版本：v_20180828_00</p>\n<p>发布三台服务器&nbsp; 并行</p>');
INSERT INTO `OPS_DEP_APP_ORDER` VALUES ('566', '134', '49', 'v_20180828_16 发布admin应用  三台服务器串行', '1', '2018-08-28 20:01:19', '200', '<p>版本号：v_20180828_16</p>\n<p>发布admin应用</p>\n<p>三台服务器串行发布</p>');
INSERT INTO `OPS_DEP_APP_ORDER` VALUES ('567', '134', '49', 'v_20180829_00  发布admin应用  单台服务器', '1', '2018-08-29 10:28:30', '-200', '<p>版本：v_20180829_00</p>\n<p>单台服务器</p>\n<p>版本异常，检测回滚功能</p>');
INSERT INTO `OPS_DEP_APP_ORDER` VALUES ('569', '134', '49', '123', '1', '2018-08-29 17:51:40', '0', '<p>123</p>');
INSERT INTO `OPS_DEP_APP_ORDER` VALUES ('570', '134', '49', 'V_20180830_00 发布admin应用 单台192.168.100.86', '1', '2018-08-30 17:48:49', '200', '<p>V_20180830_00 发布admin应用 单台192.168.100.86</p>');
INSERT INTO `OPS_DEP_APP_ORDER` VALUES ('571', '134', '52', '发布admin应用  单台服务器', '1', '2018-08-30 20:07:51', '200', '<p>发布admin应用 单台服务器</p>');
INSERT INTO `OPS_DEP_APP_ORDER` VALUES ('576', '134', '49', 'taskserver发布测试', '1', '2018-09-04 10:37:27', '200', '<p>taskserver发布测试</p>');
INSERT INTO `OPS_DEP_APP_ORDER` VALUES ('577', '134', '49', 'admin发布 测试 50.74', '1', '2018-09-04 17:53:32', '200', '<p>admin发布 测试 50.74</p>');

-- ----------------------------
-- Table structure for `OPS_DEP_APP_ORDER_INFO`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_DEP_APP_ORDER_INFO`;
CREATE TABLE `OPS_DEP_APP_ORDER_INFO` (
  `ORDER_INFO_ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '发布工单详细信息ID',
  `ORDER_ID` bigint(10) NOT NULL COMMENT '工单ID',
  `APP_ENV_ID` bigint(10) NOT NULL COMMENT '应用实例ID',
  `APP_NAME` varchar(255) NOT NULL COMMENT '应用名称',
  `SERVER_ID` bigint(10) NOT NULL COMMENT '资源ID',
  `SERVER_IP` varchar(50) NOT NULL COMMENT '资源IP',
  `SERVER_NAME` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `DEP_APP_VER_ID` bigint(10) NOT NULL COMMENT '发布版本',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DEPLOY_STATUS` int(11) NOT NULL COMMENT '发布状态',
  `DEPLOY_TYPE` int(11) NOT NULL DEFAULT '0' COMMENT '0，顺序执行，1，并发执行',
  `DEPLOY_LOG` text CHARACTER SET utf8 COMMENT '发布日志信息',
  `DEPLOY_START_DATE` timestamp NULL DEFAULT NULL COMMENT '发布起始时间',
  `DEPLOY_END_DATE` timestamp NULL DEFAULT NULL COMMENT '发布结束时间',
  PRIMARY KEY (`ORDER_INFO_ID`) USING BTREE,
  KEY `index_status` (`DEPLOY_STATUS`) USING BTREE,
  KEY `index_appenv` (`APP_ENV_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=698 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='发布工单详细信息表';

-- ----------------------------
-- Records of OPS_DEP_APP_ORDER_INFO
-- ----------------------------
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('646', '549', '283', 'pay', '493', '192.168.100.9', '彩D-pay-100.9', '337', '2018-08-25 14:51:07', '2018-08-25 14:26:26', '-250', '0', '2018-08-25 14:47:51 pay on 192.168.100.9 准备发布\n\r\n2018-08-25 14:47:51 pay on 192.168.100.9 目录检测中\r\n/usr/local/action_pay/\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_pay exists: Success!\r\n\r\n2018-08-25 14:47:51 pay on 192.168.100.9 代码检出中\r\nInitialized empty Git repository in /usr/local/tomcat_pay/webapps/ROOT/.git/\r\nNote: checking out \'V_20180825_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD is now at c41a5cc... 制定发布版本 8/25 14:14\r\n\r\n2018-08-25 14:47:58 pay on 192.168.100.9 版本检测中\r\nTest Version V_20180825_00 success!\r\n\r\n2018-08-25 14:47:59 pay on 192.168.100.9 服务启动中\r\n[00;31mTomcat is not running[00m\r\n[00;32mStarting tomcat[00m\r\n[00;32mTomcat is running with pid: 28444[00m\r\n\r\n2018-08-25 14:47:59 pay on 192.168.100.9 应用状态检测中\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\n\r\n2018-08-25 14:51:01 pay on 192.168.100.9 应用状态检测失败\r\n\r\n2018-08-25 14:51:07 pay on 192.168.100.9 没有找到可回退版本！', '2018-08-25 14:47:51', '2018-08-25 14:51:07');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('647', '550', '274', 'admin', '497', '192.168.100.86', '彩D-admin-100.86', '338', '2018-08-27 12:38:17', '2018-08-25 14:27:02', '300', '0', '2018-08-27 12:36:39 admin on 192.168.100.86 准备发布\n\r\n2018-08-27 12:36:39 admin on 192.168.100.86 目录检测中\r\n/usr/local/action_admin/\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-08-27 12:36:40 admin on 192.168.100.86 代码检出中\r\n正克隆到 \'.\'...\r\nNote: checking out \'V_20180825_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 b006bef... 制定admin发布版本 8/25 14:24\r\n\r\n2018-08-27 12:36:49 admin on 192.168.100.86 版本检测中\r\nTest Version V_20180825_00 success!\r\n\r\n2018-08-27 12:36:49 admin on 192.168.100.86 服务启动中\r\n[00;31mTomcat is not running[00m\r\n[00;32mStarting tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;32mTomcat is running with pid: 26606[00m\r\n\r\n2018-08-27 12:36:49 admin on 192.168.100.86 应用状态检测中\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is running!\r\n\r\n2018-08-27 12:38:17 admin on 192.168.100.86 发布成功', '2018-08-27 12:36:39', '2018-08-27 12:38:18');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('663', '556', '274', 'admin', '491', '192.168.100.1', '彩D-admin-100.1', '338', '2018-08-29 20:36:36', '2018-08-25 14:31:42', '300', '1', '2018-08-29 20:35:46 admin on 192.168.100.1 准备发布\n\r\n2018-08-29 20:35:46 admin on 192.168.100.1 目录检测中\r\n/usr/local/action_admin/\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-08-29 20:35:46 admin on 192.168.100.1 代码检出中\r\nPrevious HEAD position was b006bef... 制定admin发布版本 8/25 14:24\r\nSwitched to branch \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180825_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD is now at b006bef... 制定admin发布版本 8/25 14:24\r\n\r\n2018-08-29 20:35:50 admin on 192.168.100.1 版本检测中\r\nTest Version V_20180825_00 success!\r\n\r\n2018-08-29 20:35:50 admin on 192.168.100.1 服务启动中\r\n[00;31mStoping Tomcat[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n\r\n[00;31mkilling processes which didn\'t stop after 5 seconds[00m[00;32mStarting tomcat[00m\r\n[00;32mTomcat is running with pid: 25450[00m\r\n\r\n2018-08-29 20:35:56 admin on 192.168.100.1 应用状态检测中\r\n2018-08-29 20:35:57 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 20:36:17 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 20:36:27 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 20:36:36 Tomcat启动成功, 页面返回码为302\r\n\r\n2018-08-29 20:36:34 admin on 192.168.100.1 发布成功\r\n\r\n2018-08-29 20:36:34 admin on 192.168.100.1 发布完成！', '2018-08-29 20:35:46', '2018-08-29 20:36:35');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('664', '556', '274', 'admin', '492', '192.168.100.2', '彩D-admin-100.2', '338', '2018-08-29 20:17:02', '2018-08-25 14:31:42', '300', '1', '2018-08-29 20:15:26 admin on 192.168.100.2 准备发布\n\r\n2018-08-29 20:15:27 admin on 192.168.100.2 目录检测中\r\n/usr/local/action_admin/\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-08-29 20:15:27 admin on 192.168.100.2 代码检出中\r\n之前的 HEAD 位置是 b006bef... 制定admin发布版本 8/25 14:24\r\n切换到分支 \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180825_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 b006bef... 制定admin发布版本 8/25 14:24\r\n\r\n2018-08-29 20:15:32 admin on 192.168.100.2 版本检测中\r\nTest Version V_20180825_00 success!\r\n\r\n2018-08-29 20:15:32 admin on 192.168.100.2 服务启动中\r\n[00;31mStoping Tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n\r\n[00;31mkilling processes which didn\'t stop after 5 seconds[00m[00;32mStarting tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;32mTomcat is running with pid: 10942[00m\r\n\r\n2018-08-29 20:15:38 admin on 192.168.100.2 应用状态检测中\r\n2018-08-29 20:15:40 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 20:16:00 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 20:16:10 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 20:16:20 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 20:16:30 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 20:16:40 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 20:16:50 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 20:17:00 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 20:17:02 Tomcat启动成功, 页面返回码为302\r\n\r\n2018-08-29 20:17:00 admin on 192.168.100.2 发布成功\r\n\r\n2018-08-29 20:17:01 admin on 192.168.100.2 发布完成！', '2018-08-29 20:15:26', '2018-08-29 20:17:01');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('665', '556', '274', 'admin', '497', '192.168.100.86', '彩D-admin-100.86', '338', '2018-08-29 20:17:09', '2018-08-25 14:31:42', '300', '1', '2018-08-29 20:15:26 admin on 192.168.100.86 准备发布\n\r\n2018-08-29 20:15:27 admin on 192.168.100.86 目录检测中\r\n/usr/local/action_admin/\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-08-29 20:15:27 admin on 192.168.100.86 代码检出中\r\n之前的 HEAD 位置是 b006bef... 制定admin发布版本 8/25 14:24\r\n切换到分支 \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180825_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 b006bef... 制定admin发布版本 8/25 14:24\r\n\r\n2018-08-29 20:15:31 admin on 192.168.100.86 版本检测中\r\nTest Version V_20180825_00 success!\r\n\r\n2018-08-29 20:15:31 admin on 192.168.100.86 服务启动中\r\n[00;31mStoping Tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n\r\n[00;31mkilling processes which didn\'t stop after 5 seconds[00m[00;32mStarting tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;32mTomcat is running with pid: 29142[00m\r\n\r\n2018-08-29 20:15:38 admin on 192.168.100.86 应用状态检测中\r\n2018-08-29 20:15:40 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 20:16:00 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 20:16:10 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 20:16:20 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 20:16:30 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 20:16:40 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 20:16:50 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 20:17:00 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 20:17:09 Tomcat启动成功, 页面返回码为302\r\n\r\n2018-08-29 20:17:07 admin on 192.168.100.86 发布成功\r\n\r\n2018-08-29 20:17:08 admin on 192.168.100.86 发布完成！', '2018-08-29 20:15:26', '2018-08-29 20:17:08');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('666', '557', '274', 'admin', '491', '192.168.100.1', '彩D-admin-100.1', '338', '2018-08-29 19:14:53', '2018-08-25 14:32:13', '300', '0', '2018-08-29 19:14:01 admin on 192.168.100.1 准备发布\n\r\n2018-08-29 19:14:02 admin on 192.168.100.1 目录检测中\r\n/usr/local/action_admin/\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-08-29 19:14:02 admin on 192.168.100.1 代码检出中\r\nPrevious HEAD position was b006bef... 制定admin发布版本 8/25 14:24\r\nSwitched to branch \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180825_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD is now at b006bef... 制定admin发布版本 8/25 14:24\r\n\r\n2018-08-29 19:14:05 admin on 192.168.100.1 版本检测中\r\nTest Version V_20180825_00 success!\r\n\r\n2018-08-29 19:14:05 admin on 192.168.100.1 服务启动中\r\n[00;31mStoping Tomcat[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n\r\n[00;31mkilling processes which didn\'t stop after 5 seconds[00m[00;32mStarting tomcat[00m\r\n[00;32mTomcat is running with pid: 5970[00m\r\n\r\n2018-08-29 19:14:11 admin on 192.168.100.1 应用状态检测中\r\n2018-08-29 19:14:13 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 19:14:33 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 19:14:43 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 19:14:53 Tomcat启动成功, 页面返回码为302\r\n\r\n2018-08-29 19:14:51 admin on 192.168.100.1 发布成功\r\n\r\n2018-08-29 19:14:51 admin on 192.168.100.1 发布完成！', '2018-08-29 19:14:02', '2018-08-29 19:14:52');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('667', '557', '274', 'admin', '492', '192.168.100.2', '彩D-admin-100.2', '338', '2018-08-29 19:16:24', '2018-08-25 14:32:13', '300', '0', '2018-08-29 19:14:01 admin on 192.168.100.2 准备发布\n\r\n2018-08-29 19:14:52 admin on 192.168.100.2 目录检测中\r\n/usr/local/action_admin/\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-08-29 19:14:52 admin on 192.168.100.2 代码检出中\r\n之前的 HEAD 位置是 b006bef... 制定admin发布版本 8/25 14:24\r\n切换到分支 \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180825_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 b006bef... 制定admin发布版本 8/25 14:24\r\n\r\n2018-08-29 19:14:56 admin on 192.168.100.2 版本检测中\r\nTest Version V_20180825_00 success!\r\n\r\n2018-08-29 19:14:57 admin on 192.168.100.2 服务启动中\r\n[00;31mStoping Tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n\r\n[00;31mkilling processes which didn\'t stop after 5 seconds[00m[00;32mStarting tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;32mTomcat is running with pid: 169664[00m\r\n\r\n2018-08-29 19:15:03 admin on 192.168.100.2 应用状态检测中\r\n2018-08-29 19:15:05 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 19:15:25 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 19:15:35 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 19:15:45 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 19:15:55 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 19:16:05 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 19:16:15 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 19:16:24 Tomcat启动成功, 页面返回码为302\r\n\r\n2018-08-29 19:16:23 admin on 192.168.100.2 发布成功\r\n\r\n2018-08-29 19:16:23 admin on 192.168.100.2 发布完成！', '2018-08-29 19:14:02', '2018-08-29 19:16:24');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('668', '557', '274', 'admin', '497', '192.168.100.86', '彩D-admin-100.86', '338', '2018-08-29 19:18:05', '2018-08-25 14:32:13', '300', '0', '2018-08-29 19:14:01 admin on 192.168.100.86 准备发布\n\r\n2018-08-29 19:16:24 admin on 192.168.100.86 目录检测中\r\n/usr/local/action_admin/\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-08-29 19:16:24 admin on 192.168.100.86 代码检出中\r\n之前的 HEAD 位置是 b006bef... 制定admin发布版本 8/25 14:24\r\n切换到分支 \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180825_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 b006bef... 制定admin发布版本 8/25 14:24\r\n\r\n2018-08-29 19:16:28 admin on 192.168.100.86 版本检测中\r\nTest Version V_20180825_00 success!\r\n\r\n2018-08-29 19:16:28 admin on 192.168.100.86 服务启动中\r\n[00;31mStoping Tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n\r\n[00;31mkilling processes which didn\'t stop after 5 seconds[00m[00;32mStarting tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;32mTomcat is running with pid: 2042[00m\r\n\r\n2018-08-29 19:16:35 admin on 192.168.100.86 应用状态检测中\r\n2018-08-29 19:16:36 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 19:16:57 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 19:17:07 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 19:17:17 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 19:17:27 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 19:17:37 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 19:17:47 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 19:17:57 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 19:18:05 Tomcat启动成功, 页面返回码为302\r\n\r\n2018-08-29 19:18:04 admin on 192.168.100.86 发布成功\r\n\r\n2018-08-29 19:18:04 admin on 192.168.100.86 发布完成！', '2018-08-29 19:14:02', '2018-08-29 19:18:04');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('669', '558', '288', 'admin', '499', '192.168.100.77', '赢D-admin-100.77', '339', '2018-08-25 17:51:54', '2018-08-25 17:33:48', '-250', '0', '2018-08-25 17:48:46 admin on 192.168.100.77 准备发布\n\r\n2018-08-25 17:48:47 admin on 192.168.100.77 目录检测中\r\n/usr/local/action_admin/\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-08-25 17:48:47 admin on 192.168.100.77 代码检出中\r\n切换到分支 \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180825_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 a6bc070... 制定发布版本 8/25 17:31\r\n\r\n2018-08-25 17:48:48 admin on 192.168.100.77 版本检测中\r\nTest Version V_20180825_00 success!\r\n\r\n2018-08-25 17:48:48 admin on 192.168.100.77 服务启动中\r\n[00;31mStoping Tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;32mStarting tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;32mTomcat is running with pid: 14049[00m\r\n\r\n2018-08-25 17:48:50 admin on 192.168.100.77 应用状态检测中\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\n\r\n2018-08-25 17:51:52 admin on 192.168.100.77 应用状态检测失败\r\n\r\n2018-08-25 17:51:54 admin on 192.168.100.77 没有找到可回退版本！', '2018-08-25 17:48:47', '2018-08-25 17:51:55');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('670', '559', '288', 'admin', '499', '192.168.100.77', '赢D-admin-100.77', '339', '2018-08-25 17:55:16', '2018-08-25 17:34:19', '-250', '1', '2018-08-25 17:52:05 admin on 192.168.100.77 准备发布\n\r\n2018-08-25 17:52:06 admin on 192.168.100.77 目录检测中\r\n/usr/local/action_admin/\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-08-25 17:52:06 admin on 192.168.100.77 代码检出中\r\n切换到分支 \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180825_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 a6bc070... 制定发布版本 8/25 17:31\r\n\r\n2018-08-25 17:52:06 admin on 192.168.100.77 版本检测中\r\nTest Version V_20180825_00 success!\r\n\r\n2018-08-25 17:52:06 admin on 192.168.100.77 服务启动中\r\n[00;31mStoping Tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;32mStarting tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;32mTomcat is running with pid: 17569[00m\r\n\r\n2018-08-25 17:52:09 admin on 192.168.100.77 应用状态检测中\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\n\r\n2018-08-25 17:55:10 admin on 192.168.100.77 应用状态检测失败\r\n\r\n2018-08-25 17:55:16 admin on 192.168.100.77 没有找到可回退版本！', '2018-08-25 17:52:05', '2018-08-25 17:55:16');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('671', '559', '288', 'admin', '498', '192.168.100.76', '赢D-admin-100.76', '339', '2018-08-25 17:55:19', '2018-08-25 17:34:19', '-250', '1', '2018-08-25 17:52:05 admin on 192.168.100.76 准备发布\n\r\n2018-08-25 17:52:05 admin on 192.168.100.76 目录检测中\r\n/usr/local/action_admin/\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-08-25 17:52:06 admin on 192.168.100.76 代码检出中\r\n正克隆到 \'.\'...\r\nNote: checking out \'V_20180825_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 a6bc070... 制定发布版本 8/25 17:31\r\n\r\n2018-08-25 17:52:14 admin on 192.168.100.76 版本检测中\r\nTest Version V_20180825_00 success!\r\n\r\n2018-08-25 17:52:15 admin on 192.168.100.76 服务启动中\r\n[00;31mTomcat is not running[00m\r\n[00;32mStarting tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;32mTomcat is running with pid: 24078[00m\r\n\r\n2018-08-25 17:52:15 admin on 192.168.100.76 应用状态检测中\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\n\r\n2018-08-25 17:55:19 admin on 192.168.100.76 应用状态检测失败\r\n\r\n2018-08-25 17:55:19 admin on 192.168.100.76 没有找到可回退版本！', '2018-08-25 17:52:05', '2018-08-25 17:55:20');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('672', '559', '288', 'admin', '494', '192.168.100.75', '赢D-admin-100.75', '339', '2018-08-25 17:55:16', '2018-08-25 17:34:19', '-250', '1', '2018-08-25 17:52:05 admin on 192.168.100.75 准备发布\n\r\n2018-08-25 17:52:05 admin on 192.168.100.75 目录检测中\r\n/usr/local/action_admin/\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-08-25 17:52:06 admin on 192.168.100.75 代码检出中\r\n正克隆到 \'.\'...\r\nNote: checking out \'V_20180825_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 a6bc070... 制定发布版本 8/25 17:31\r\n\r\n2018-08-25 17:52:14 admin on 192.168.100.75 版本检测中\r\nTest Version V_20180825_00 success!\r\n\r\n2018-08-25 17:52:14 admin on 192.168.100.75 服务启动中\r\n[00;31mTomcat is not running[00m\r\n[00;32mStarting tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;32mTomcat is running with pid: 27026[00m\r\n\r\n2018-08-25 17:52:15 admin on 192.168.100.75 应用状态检测中\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\nTomcat is stopped!\r\n\r\n2018-08-25 17:55:16 admin on 192.168.100.75 应用状态检测失败\r\n\r\n2018-08-25 17:55:16 admin on 192.168.100.75 没有找到可回退版本！', '2018-08-25 17:52:05', '2018-08-25 17:55:17');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('673', '560', '288', 'admin', '499', '192.168.100.77', '赢D-admin-100.77', '339', '2018-08-25 17:34:44', '2018-08-25 17:34:44', '0', '0', null, null, null);
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('674', '560', '288', 'admin', '498', '192.168.100.76', '赢D-admin-100.76', '339', '2018-08-25 17:34:44', '2018-08-25 17:34:44', '0', '0', null, null, null);
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('675', '560', '288', 'admin', '494', '192.168.100.75', '赢D-admin-100.75', '339', '2018-08-25 17:34:44', '2018-08-25 17:34:44', '0', '0', null, null, null);
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('676', '561', '289', 'app', '500', '192.168.100.81', '赢D-app-100.81', '341', '2018-09-04 14:34:27', '2018-08-28 14:21:37', '300', '0', '2018-09-04 14:34:21 app on 192.168.100.81 准备发布\n\r\n2018-09-04 14:34:22 app on 192.168.100.81 目录检测中\r\nTest /usr/local/jdk1.7 exists: Not Found!\r\nTest /date/nginx/nginx_static/app exists: Success!\r\n\r\n2018-09-04 14:34:22 app on 192.168.100.81 代码检出中\r\n切换到分支 \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180828_02\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 d97c083... 制作发布版本 static\r\n\r\n2018-09-04 14:34:26 app on 192.168.100.81 版本检测中\r\nTest Version V_20180828_02 success!\r\n\r\n2018-09-04 14:34:26 app on 192.168.100.81 服务启动中\r\n\r\n2018-09-04 14:34:26 app on 192.168.100.81 应用状态检测中\r\n2018-09-04 14:34:26 nginx is running with pid 2710 \r\n\r\n2018-09-04 14:34:26 app on 192.168.100.81 发布成功\r\n\r\n2018-09-04 14:34:27 app on 192.168.100.81 发布完成！', '2018-09-04 14:34:21', '2018-09-04 14:34:28');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('677', '562', '274', 'admin', '497', '192.168.100.86', '彩D-admin-100.86', '343', '2018-09-04 15:35:22', '2018-08-28 16:27:17', '300', '0', '2018-09-04 14:34:21 app on 192.168.100.81 准备发布\r\n\r\n2018-09-04 14:34:22 app on 192.168.100.81 目录检测中\r\nTest /usr/local/jdk1.7 exists: Not Found!\r\nTest /date/nginx/nginx_static/app exists: Success!\r\n\r\n2018-09-04 14:34:22 app on 192.168.100.81 代码检出中\r\n切换到分支 \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180828_02\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 d97c083... 制作发布版本 static\r\n\r\n2018-09-04 14:34:26 app on 192.168.100.81 版本检测中\r\nTest Version V_20180828_02 success!\r\n\r\n2018-09-04 14:34:26 app on 192.168.100.81 服务启动中\r\n\r\n2018-09-04 14:34:26 app on 192.168.100.81 应用状态检测中\r\n2018-09-04 14:34:26 nginx is running with pid 2710 \r\n\r\n2018-09-04 14:34:26 app on 192.168.100.81 发布成功\r\n\r\n2018-09-04 14:34:27 app on 192.168.100.81 发布完成！', '2018-08-29 17:36:57', '2018-08-29 17:42:05');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('678', '563', '283', 'pay', '495', '192.168.100.84', '彩D-pay-100.84', '342', '2018-08-30 17:17:55', '2018-08-28 16:28:36', '-250', '0', '2018-08-30 17:14:40 pay on 192.168.100.84 准备发布\n\r\n2018-08-30 17:14:41 pay on 192.168.100.84 目录检测中\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_pay exists: Success!\r\n\r\n2018-08-30 17:14:41 pay on 192.168.100.84 代码检出中\r\n切换到分支 \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180828_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 56db7ce... 1. 制定彩票admin发布版本 2. 制定彩票pay发布版本\r\n\r\n2018-08-30 17:14:42 pay on 192.168.100.84 版本检测中\r\nTest Version V_20180828_00 success!\r\n\r\n2018-08-30 17:14:42 pay on 192.168.100.84 服务启动中\r\n[00;31mStoping Tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_pay\r\nUsing CATALINA_HOME:   /usr/local/tomcat_pay\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_pay/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_pay/bin/bootstrap.jar\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;32mStarting tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_pay\r\nUsing CATALINA_HOME:   /usr/local/tomcat_pay\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_pay/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_pay/bin/bootstrap.jar\r\n[00;32mTomcat is running with pid: 31634[00m\r\n\r\n2018-08-30 17:14:44 pay on 192.168.100.84 应用状态检测中\r\n2018-08-30 17:14:47 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:14:57 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:15:07 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:15:17 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:15:27 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:15:37 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:15:47 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:15:58 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:16:08 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:16:18 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:16:28 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:16:38 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:16:48 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:16:58 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:17:08 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:17:18 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:17:28 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:17:38 Tomcat页面出错, 页面返回码为000\r\n\r\n2018-08-30 17:17:46 pay on 192.168.100.84 应用状态检测失败\r\n\r\n2018-08-30 17:17:53 pay on 192.168.100.84 没有找到可回退版本！\r\n\r\n2018-08-30 17:17:53 pay on 192.168.100.84 发布完成！', '2018-08-30 17:14:40', '2018-08-30 17:17:54');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('679', '564', '274', 'admin', '491', '192.168.100.1', '彩D-admin-100.1', '343', '2018-09-04 14:23:35', '2018-08-28 16:43:04', '300', '1', '2018-09-04 14:22:37 admin on 192.168.100.1 准备发布\n\r\n2018-09-04 14:22:42 admin on 192.168.100.1 目录检测中\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-09-04 14:22:42 admin on 192.168.100.1 代码检出中\r\nPrevious HEAD position was 9e2b494... 1. 制定彩票admin发布版本 2. 制定彩票pay发布版本\r\nSwitched to branch \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180828_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD is now at 9e2b494... 1. 制定彩票admin发布版本 2. 制定彩票pay发布版本\r\n\r\n2018-09-04 14:22:43 admin on 192.168.100.1 版本检测中\r\nTest Version V_20180828_00 success!\r\n\r\n2018-09-04 14:22:43 admin on 192.168.100.1 服务启动中\r\n[00;31mStoping Tomcat[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n\r\n[00;31mkilling processes which didn\'t stop after 5 seconds[00m[00;32mStarting tomcat[00m\r\n[00;32mTomcat is running with pid: 1153[00m\r\n\r\n2018-09-04 14:22:49 admin on 192.168.100.1 应用状态检测中\r\n2018-09-04 14:23:35 Tomcat启动成功, 页面返回码为302\r\n\r\n2018-09-04 14:23:34 admin on 192.168.100.1 发布成功\r\n\r\n2018-09-04 14:23:35 admin on 192.168.100.1 发布完成！', '2018-09-04 14:22:37', '2018-09-04 14:23:35');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('680', '564', '274', 'admin', '492', '192.168.100.2', '彩D-admin-100.2', '343', '2018-09-04 14:27:50', '2018-08-28 16:43:04', '300', '1', '2018-09-04 14:26:21 admin on 192.168.100.2 准备发布\n\r\n2018-09-04 14:26:22 admin on 192.168.100.2 目录检测中\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-09-04 14:26:23 admin on 192.168.100.2 代码检出中\r\n之前的 HEAD 位置是 9e2b494... 1. 制定彩票admin发布版本 2. 制定彩票pay发布版本\r\n切换到分支 \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180828_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 9e2b494... 1. 制定彩票admin发布版本 2. 制定彩票pay发布版本\r\n\r\n2018-09-04 14:26:23 admin on 192.168.100.2 版本检测中\r\nTest Version V_20180828_00 success!\r\n\r\n2018-09-04 14:26:23 admin on 192.168.100.2 服务启动中\r\n[00;31mStoping Tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n\r\n[00;31mkilling processes which didn\'t stop after 5 seconds[00m[00;32mStarting tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;32mTomcat is running with pid: 144767[00m\r\n\r\n2018-09-04 14:26:30 admin on 192.168.100.2 应用状态检测中\r\n2018-09-04 14:26:32 Tomcat页面出错, 页面返回码为000\r\n2018-09-04 14:27:14 Tomcat页面出错, 页面返回码为000\r\n2018-09-04 14:27:24 Tomcat页面出错, 页面返回码为000\r\n2018-09-04 14:27:34 Tomcat页面出错, 页面返回码为000\r\n2018-09-04 14:27:44 Tomcat启动成功, 页面返回码为302\r\n\r\n2018-09-04 14:27:50 admin on 192.168.100.2 发布成功\r\n\r\n2018-09-04 14:27:50 admin on 192.168.100.2 发布完成！', '2018-09-04 14:26:22', '2018-09-04 14:27:51');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('681', '564', '274', 'admin', '497', '192.168.100.86', '彩D-admin-100.86', '343', '2018-08-28 16:44:46', '2018-08-28 16:43:04', '300', '1', '2018-08-28 16:43:19 admin on 192.168.100.86 准备发布\n\r\n2018-08-28 16:43:19 admin on 192.168.100.86 目录检测中\r\n/usr/local/action_admin/\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-08-28 16:43:19 admin on 192.168.100.86 代码检出中\r\n切换到分支 \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180828_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 9e2b494... 1. 制定彩票admin发布版本 2. 制定彩票pay发布版本\r\n\r\n2018-08-28 16:43:20 admin on 192.168.100.86 版本检测中\r\nTest Version V_20180828_00 success!\r\n\r\n2018-08-28 16:43:20 admin on 192.168.100.86 服务启动中\r\n[00;31mStoping Tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n\r\n[00;31mkilling processes which didn\'t stop after 5 seconds[00m[00;32mStarting tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;32mTomcat is running with pid: 23737[00m\r\n\r\n2018-08-28 16:43:27 admin on 192.168.100.86 应用状态检测中\r\n\r\n2018-08-28 16:44:45 admin on 192.168.100.86 发布成功', '2018-08-28 16:43:19', '2018-08-28 16:44:46');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('685', '566', '274', 'admin', '491', '192.168.100.1', '彩D-admin-100.1', '347', '2018-09-04 19:51:14', '2018-08-28 20:01:19', '300', '0', '2018-09-04 19:50:22 admin on 192.168.100.1 准备发布\n\r\n2018-09-04 19:50:25 admin on 192.168.100.1 目录检测中\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-09-04 19:50:25 admin on 192.168.100.1 代码检出中\r\nPrevious HEAD position was 9e2b494... 1. 制定彩票admin发布版本 2. 制定彩票pay发布版本\r\nSwitched to branch \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180828_16\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD is now at 4c12a2a... 发布admin版本 8/28 19:55\r\n\r\n2018-09-04 19:50:25 admin on 192.168.100.1 版本检测中\r\nTest Version V_20180828_16 success!\r\n\r\n2018-09-04 19:50:26 admin on 192.168.100.1 服务启动中\r\n[00;31mStoping Tomcat[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n\r\n[00;31mkilling processes which didn\'t stop after 5 seconds[00m[00;32mStarting tomcat[00m\r\n[00;32mTomcat is running with pid: 18117[00m\r\n\r\n2018-09-04 19:50:32 admin on 192.168.100.1 应用状态检测中\r\n2018-09-04 19:50:32 Tomcat页面出错, 页面返回码为000\r\n2018-09-04 19:50:42 Tomcat页面出错, 页面返回码为000\r\n2018-09-04 19:50:53 Tomcat页面出错, 页面返回码为000\r\n2018-09-04 19:51:03 Tomcat启动成功, 页面返回码为302\r\n\r\n2018-09-04 19:51:13 admin on 192.168.100.1 发布成功\r\n\r\n2018-09-04 19:51:14 admin on 192.168.100.1 发布完成！', '2018-09-04 19:50:23', '2018-09-04 19:51:15');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('686', '566', '274', 'admin', '492', '192.168.100.2', '彩D-admin-100.2', '347', '2018-08-29 18:37:35', '2018-08-28 20:01:19', '300', '0', '2018-08-29 18:34:19 admin on 192.168.100.2 准备发布\n\r\n2018-08-29 18:36:01 admin on 192.168.100.2 目录检测中\r\n/usr/local/action_admin/\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-08-29 18:36:01 admin on 192.168.100.2 代码检出中\r\n之前的 HEAD 位置是 4c12a2a... 发布admin版本 8/28 19:55\r\n切换到分支 \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180828_16\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 4c12a2a... 发布admin版本 8/28 19:55\r\n\r\n2018-08-29 18:36:06 admin on 192.168.100.2 版本检测中\r\nTest Version V_20180828_16 success!\r\n\r\n2018-08-29 18:36:06 admin on 192.168.100.2 服务启动中\r\n[00;31mStoping Tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n\r\n[00;31mkilling processes which didn\'t stop after 5 seconds[00m[00;32mStarting tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;32mTomcat is running with pid: 146859[00m\r\n\r\n2018-08-29 18:36:13 admin on 192.168.100.2 应用状态检测中\r\n2018-08-29 18:36:14 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 18:36:34 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 18:36:44 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 18:36:54 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 18:37:04 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 18:37:14 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 18:37:24 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 18:37:34 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 18:37:35 Tomcat启动成功, 页面返回码为302\r\n\r\n2018-08-29 18:37:33 admin on 192.168.100.2 发布成功\r\n\r\n2018-08-29 18:37:34 admin on 192.168.100.2 发布完成！', '2018-08-29 18:34:19', '2018-08-29 18:37:34');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('687', '566', '274', 'admin', '497', '192.168.100.86', '彩D-admin-100.86', '347', '2018-08-29 18:36:02', '2018-08-28 20:01:19', '300', '0', '2018-08-29 18:34:19 admin on 192.168.100.86 准备发布\n\r\n2018-08-29 18:34:19 admin on 192.168.100.86 目录检测中\r\n/usr/local/action_admin/\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-08-29 18:34:20 admin on 192.168.100.86 代码检出中\r\n之前的 HEAD 位置是 4c12a2a... 发布admin版本 8/28 19:55\r\n切换到分支 \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180828_16\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 4c12a2a... 发布admin版本 8/28 19:55\r\n\r\n2018-08-29 18:34:24 admin on 192.168.100.86 版本检测中\r\nTest Version V_20180828_16 success!\r\n\r\n2018-08-29 18:34:24 admin on 192.168.100.86 服务启动中\r\n[00;31mStoping Tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n\r\n[00;31mkilling processes which didn\'t stop after 5 seconds[00m[00;32mStarting tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;32mTomcat is running with pid: 16554[00m\r\n\r\n2018-08-29 18:34:31 admin on 192.168.100.86 应用状态检测中\r\n2018-08-29 18:34:32 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 18:34:52 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 18:35:02 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 18:35:12 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 18:35:22 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 18:35:33 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 18:35:43 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 18:35:53 Tomcat页面出错, 页面返回码为000\r\n2018-08-29 18:36:01 Tomcat启动成功, 页面返回码为302\r\n\r\n2018-08-29 18:36:00 admin on 192.168.100.86 发布成功\r\n\r\n2018-08-29 18:36:00 admin on 192.168.100.86 发布完成！', '2018-08-29 18:34:19', '2018-08-29 18:36:01');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('688', '567', '274', 'admin', '497', '192.168.100.86', '彩D-admin-100.86', '356', '2018-08-30 17:41:44', '2018-08-29 10:28:30', '-250', '0', '2018-08-30 17:32:57 admin on 192.168.100.86 准备发布\n\r\n2018-08-30 17:32:58 admin on 192.168.100.86 目录检测中\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-08-30 17:32:58 admin on 192.168.100.86 代码检出中\r\n之前的 HEAD 位置是 4c12a2a... 发布admin版本 8/28 19:55\r\n切换到分支 \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180829_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 3c95de7... 制定admin发布版本 8/29\r\n\r\n2018-08-30 17:34:30 admin on 192.168.100.86 版本检测中\r\nTest Version V_20180829_00 success!\r\n\r\n2018-08-30 17:34:47 admin on 192.168.100.86 服务启动中\r\n[00;31mStoping Tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n\r\n[00;31mkilling processes which didn\'t stop after 5 seconds[00m[00;32mStarting tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;32mTomcat is running with pid: 30225[00m\r\n\r\n2018-08-30 17:35:05 admin on 192.168.100.86 应用状态检测中\r\n2018-08-30 17:35:34 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:35:44 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:35:54 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:36:04 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:36:14 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:36:24 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:36:34 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:36:44 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:36:54 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:37:04 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:37:14 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:37:24 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:37:34 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:37:45 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:37:55 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:38:05 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:38:15 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:38:25 Tomcat页面出错, 页面返回码为404\r\n\r\n2018-08-30 17:38:32 admin on 192.168.100.86 应用状态检测失败\r\n\r\n2018-08-30 17:38:33 admin on 192.168.100.86  回退版本 --> V_20180829_00\r\ncd /usr/local/tomcat_admin/webapps/ROOT/\r\nTest /.git exists:   Success!\r\n之前的 HEAD 位置是 3c95de7... 制定admin发布版本 8/29\r\n切换到分支 \'master\'\r\nAlready up-to-date.\r\ngit checkout V_20180829_00\r\nNote: checking out \'V_20180829_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 3c95de7... 制定admin发布版本 8/29\r\ngit log --graph --decorate --oneline -n 1\r\n* 3c95de7 (HEAD, tag: V_20180829_00) 制定admin发布版本 8/29\r\n\r\n2018-08-30 17:38:37 admin on 192.168.100.86  回退版本成功！\r\n\r\n2018-08-30 17:38:37 admin on 192.168.100.86 版本检测中\r\nTest Version V_20180829_00 success!\r\n\r\n2018-08-30 17:38:37 admin on 192.168.100.86 服务启动中\r\n[00;31mStoping Tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;32mStarting tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;32mTomcat is running with pid: 13819[00m\r\n\r\n2018-08-30 17:38:40 admin on 192.168.100.86 应用状态检测中\r\n2018-08-30 17:38:43 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:38:53 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:39:03 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:39:13 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:39:23 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:39:33 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:39:43 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:39:53 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:40:03 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:40:13 Tomcat页面出错, 页面返回码为404\r\n2018-08-30 17:40:23 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:40:33 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:40:43 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:40:53 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:41:04 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:41:14 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:41:24 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 17:41:34 Tomcat页面出错, 页面返回码为000\r\n\r\n2018-08-30 17:41:42 admin on 192.168.100.86 应用状态检测失败\r\n\r\n2018-08-30 17:41:42 admin on 192.168.100.86 发布完成！', '2018-08-30 17:32:58', '2018-08-30 17:41:42');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('689', '570', '274', 'admin', '497', '192.168.100.86', '彩D-admin-100.86', '357', '2018-09-03 14:34:50', '2018-08-30 17:48:49', '300', '0', '2018-09-03 14:32:57 admin on 192.168.100.86 准备发布\n\r\n2018-09-03 14:32:58 admin on 192.168.100.86 目录检测中\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-09-03 14:32:58 admin on 192.168.100.86 代码检出中\r\n切换到分支 \'master\'\r\n来自 http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/admin/war\r\n   01922ac..a2a1cef  master     -> origin/master\r\n * [新tag]           V_20180830_41 -> V_20180830_41\r\n来自 http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/admin/war\r\n * [新tag]           V_20180830_14 -> V_20180830_14\r\n * [新tag]           V_20180830_15 -> V_20180830_15\r\n * [新tag]           V_20180830_16 -> V_20180830_16\r\n * [新tag]           V_20180830_17 -> V_20180830_17\r\n * [新tag]           V_20180830_18 -> V_20180830_18\r\n * [新tag]           V_20180830_19 -> V_20180830_19\r\n * [新tag]           V_20180830_25 -> V_20180830_25\r\n * [新tag]           V_20180830_27 -> V_20180830_27\r\n * [新tag]           V_20180830_29 -> V_20180830_29\r\n * [新tag]           V_20180830_31 -> V_20180830_31\r\n * [新tag]           V_20180830_32 -> V_20180830_32\r\n * [新tag]           V_20180830_34 -> V_20180830_34\r\n * [新tag]           V_20180830_40 -> V_20180830_40\r\n更新 01922ac..a2a1cef\r\nFast-forward\r\n 11.png                                             |  Bin 7475 -> 0 bytes\r\n 111.png                                            |  Bin 48717 -> 0 bytes\r\n META-INF/MANIFEST.MF                               |    4 +-\r\n WEB-INF/classes/mpx.xml                            |    1 +\r\n .../controller/cache/OpenCodeCacheController.class |  Bin 4407 -> 3916 bytes\r\n .../web/controller/tj/TjRankTypeController.class   |  Bin 10122 -> 10077 bytes\r\n WEB-INF/classes/rebel.xml                          |    1 +\r\n WEB-INF/classes/spring-mvc.xml                     |    2 +-\r\n WEB-INF/content/admin/web-help-add.jsp             |  436 ++---\r\n .../content/advice/tj-ranking-config-manager.jsp   |  188 +-\r\n WEB-INF/content/product/flash-act-merge-form.jsp   | 1834 ++++++++++----------\r\n WEB-INF/content/product/flash-banner-add.jsp       |  552 +++---\r\n WEB-INF/content/system/inflow-type-edit.jsp        |  736 ++++----\r\n WEB-INF/content/system/main-top.jsp                |    2 +-\r\n WEB-INF/content/tj/tj-rank-issue-auditbox.jsp      |   76 +-\r\n WEB-INF/content/tj/tj-rank-issue-prizebox.jsp      |   74 +-\r\n WEB-INF/content/tj/tj-rank-issue-rankbox.jsp       |   76 +-\r\n WEB-INF/content/tj/tj-rank-type-auditbox.jsp       |   68 +-\r\n WEB-INF/content/tj/tj-rank-type-enablebox.jsp      |   70 +-\r\n WEB-INF/content/tj/tj-rank-type-prizebox.jsp       |   70 +-\r\n WEB-INF/content/tj/tj-rank-type-rankbox.jsp        |   70 +-\r\n WEB-INF/lib/lot-common.jar                         |  Bin 1820437 -> 1820437 bytes\r\n WEB-INF/lib/lot-dao-pri.jar                        |  Bin 301630 -> 301630 bytes\r\n WEB-INF/lib/lot-dao-pub.jar                        |  Bin 4611474 -> 4616376 bytes\r\n WEB-INF/lib/lot-service-pri.jar                    |  Bin 1898972 -> 1898987 bytes\r\n WEB-INF/lib/lot-service-pub.jar                    |  Bin 6303880 -> 6304945 bytes\r\n WEB-INF/lotfilterTag.tld                           |    1 +\r\n add_file-003                                       |    1 +\r\n add_file01.txt                                     |    7 +\r\n add_file_002                                       |    1 +\r\n article3_image3.png                                |  Bin 199137 -> 0 bytes\r\n article3_image4.png                                |  Bin 153019 -> 0 bytes\r\n template/issue/pk_zong_rank.ftl                    |   22 +-\r\n 33 files changed, 2152 insertions(+), 2140 deletions(-)\r\n delete mode 100644 11.png\r\n delete mode 100644 111.png\r\n create mode 100644 add_file-003\r\n create mode 100644 add_file01.txt\r\n create mode 100644 add_file_002\r\n delete mode 100644 article3_image3.png\r\n delete mode 100644 article3_image4.png\r\nNote: checking out \'V_20180830_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 01922ac... 8/30\r\n\r\n2018-09-03 14:33:03 admin on 192.168.100.86 版本检测中\r\ndiff --git a/11.png b/11.png\r\ndeleted file mode 100644\r\nindex 7a8226c..0000000\r\nBinary files a/11.png and /dev/null differ\r\ndiff --git a/111.png b/111.png\r\ntoo more...\r\n\r\n2018-09-03 14:33:03 admin on 192.168.100.86 服务启动中\r\n[00;31mStoping Tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n\r\n[00;31mkilling processes which didn\'t stop after 5 seconds[00m[00;32mStarting tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;32mTomcat is running with pid: 2395[00m\r\n\r\n2018-09-03 14:33:11 admin on 192.168.100.86 应用状态检测中\r\n2018-09-03 14:33:11 Tomcat页面出错, 页面返回码为000\r\n2018-09-03 14:33:21 Tomcat页面出错, 页面返回码为000\r\n2018-09-03 14:33:31 Tomcat页面出错, 页面返回码为000\r\n2018-09-03 14:33:41 Tomcat页面出错, 页面返回码为000\r\n2018-09-03 14:33:51 Tomcat页面出错, 页面返回码为000\r\n2018-09-03 14:34:01 Tomcat页面出错, 页面返回码为000\r\n2018-09-03 14:34:11 Tomcat页面出错, 页面返回码为000\r\n2018-09-03 14:34:22 Tomcat页面出错, 页面返回码为000\r\n2018-09-03 14:34:32 Tomcat页面出错, 页面返回码为000\r\n2018-09-03 14:34:42 Tomcat启动成功, 页面返回码为302\r\n\r\n2018-09-03 14:34:50 admin on 192.168.100.86 发布成功\r\n\r\n2018-09-03 14:34:51 admin on 192.168.100.86 发布完成！', '2018-09-03 14:32:58', '2018-09-03 14:34:51');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('690', '571', '288', 'admin', '499', '192.168.100.77', '赢D-admin-100.77', '359', '2018-08-30 20:09:28', '2018-08-30 20:07:51', '300', '0', '2018-08-30 20:08:00 admin on 192.168.100.77 准备发布\n\r\n2018-08-30 20:08:01 admin on 192.168.100.77 目录检测中\r\n/usr/local/action_admin/\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-08-30 20:08:01 admin on 192.168.100.77 代码检出中\r\n正克隆到 \'.\'...\r\nChecking out files:  48% (2365/4837)   \r\nChecking out files:  49% (2371/4837)   \r\nChecking out files:  50% (2419/4837)   \r\nChecking out files:  51% (2467/4837)   \r\nChecking out files:  52% (2516/4837)   \r\nChecking out files:  53% (2564/4837)   \r\nChecking out files:  54% (2612/4837)   \r\nChecking out files:  55% (2661/4837)   \r\nChecking out files:  56% (2709/4837)   \r\nChecking out files:  57% (2758/4837)   \r\nChecking out files:  58% (2806/4837)   \r\nChecking out files:  59% (2854/4837)   \r\nChecking out files:  60% (2903/4837)   \r\nChecking out files:  61% (2951/4837)   \r\nChecking out files:  62% (2999/4837)   \r\nChecking out files:  63% (3048/4837)   \r\nChecking out files:  64% (3096/4837)   \r\nChecking out files:  65% (3145/4837)   \r\nChecking out files:  66% (3193/4837)   \r\nChecking out files:  67% (3241/4837)   \r\nChecking out files:  68% (3290/4837)   \r\nChecking out files:  69% (3338/4837)   \r\nChecking out files:  70% (3386/4837)   \r\nChecking out files:  71% (3435/4837)   \r\nChecking out files:  72% (3483/4837)   \r\nChecking out files:  73% (3532/4837)   \r\nChecking out files:  74% (3580/4837)   \r\nChecking out files:  75% (3628/4837)   \r\nChecking out files:  76% (3677/4837)   \r\nChecking out files:  77% (3725/4837)   \r\nChecking out files:  78% (3773/4837)   \r\nChecking out files:  79% (3822/4837)   \r\nChecking out files:  80% (3870/4837)   \r\nChecking out files:  81% (3918/4837)   \r\nChecking out files:  82% (3967/4837)   \r\nChecking out files:  83% (4015/4837)   \r\nChecking out files:  84% (4064/4837)   \r\nChecking out files:  85% (4112/4837)   \r\nChecking out files:  86% (4160/4837)   \r\nChecking out files:  87% (4209/4837)   \r\nChecking out files:  88% (4257/4837)   \r\nChecking out files:  89% (4305/4837)   \r\nChecking out files:  90% (4354/4837)   \r\nChecking out files:  91% (4402/4837)   \r\nChecking out files:  92% (4451/4837)   \r\nChecking out files:  93% (4499/4837)   \r\nChecking out files:  94% (4547/4837)   \r\nChecking out files:  95% (4596/4837)   \r\nChecking out files:  96% (4644/4837)   \r\nChecking out files:  97% (4692/4837)   \r\nChecking out files:  98% (4741/4837)   \r\nChecking out files:  99% (4789/4837)   \r\nChecking out files: 100% (4837/4837)   \r\nChecking out files: 100% (4837/4837), done.\r\nNote: checking out \'V_20180830_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD 目前位于 45ffcfb... 8/30-20:06\r\n\r\n2018-08-30 20:08:13 admin on 192.168.100.77 版本检测中\r\nTest Version V_20180830_00 success!\r\n\r\n2018-08-30 20:08:13 admin on 192.168.100.77 服务启动中\r\n[00;31mTomcat is not running[00m\r\n[00;32mStarting tomcat[00m\r\nUsing CATALINA_BASE:   /usr/local/tomcat_admin\r\nUsing CATALINA_HOME:   /usr/local/tomcat_admin\r\nUsing CATALINA_TMPDIR: /usr/local/tomcat_admin/temp\r\nUsing JRE_HOME:        /usr/local/jdk1.7\r\nUsing CLASSPATH:       /usr/local/tomcat_admin/bin/bootstrap.jar\r\n[00;32mTomcat is running with pid: 16315[00m\r\n\r\n2018-08-30 20:08:13 admin on 192.168.100.77 应用状态检测中\r\n2018-08-30 20:08:13 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 20:08:34 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 20:08:44 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 20:08:54 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 20:09:04 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 20:09:14 Tomcat页面出错, 页面返回码为000\r\n2018-08-30 20:09:22 Tomcat启动成功, 页面返回码为200\r\n\r\n2018-08-30 20:09:22 admin on 192.168.100.77 发布成功\r\n\r\n2018-08-30 20:09:28 admin on 192.168.100.77 发布完成！', '2018-08-30 20:08:01', '2018-08-30 20:09:28');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('695', '576', '272', 'taskserver', '508', '192.168.50.74', '彩D-task-50.74', '368', '2018-09-04 11:59:19', '2018-09-04 10:37:27', '300', '0', '2018-09-04 11:59:01 taskserver on 192.168.50.74 准备发布\n\r\n2018-09-04 11:59:03 taskserver on 192.168.50.74 目录检测中\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /data/logs/ exists: Success!\r\nTest /usr/local/app_taskserver exists: Success!\r\n\r\n2018-09-04 11:59:05 taskserver on 192.168.50.74 代码检出中\r\nSwitched to branch \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180904_00\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD is now at 18fb028... 指定taskserver发布版本\r\n\r\n2018-09-04 11:59:08 taskserver on 192.168.50.74 版本检测中\r\nTest Version V_20180904_00 success!\r\n\r\n2018-09-04 11:59:09 taskserver on 192.168.50.74 服务启动中\r\n====================\r\nwarn: org.lot.taskserver.ServerStart is not running\r\n====================\r\nJAVA_HOME:/usr/local/jdk1.7\r\n====================\r\norg.lot.taskserver.ServerStart already started(PID=3108)\r\n====================\r\n\r\n2018-09-04 11:59:19 taskserver on 192.168.50.74 应用状态检测中\r\n\r\n2018-09-04 11:59:19 taskserver on 192.168.50.74 发布成功\r\n\r\n2018-09-04 11:59:19 taskserver on 192.168.50.74 发布完成！', '2018-09-04 11:59:01', '2018-09-04 11:59:20');
INSERT INTO `OPS_DEP_APP_ORDER_INFO` VALUES ('696', '577', '274', 'admin', '508', '192.168.50.74', '彩D-task-50.74', '367', '2018-09-04 18:58:24', '2018-09-04 17:53:32', '300', '0', '2018-09-04 18:57:01 admin on 192.168.50.74 准备发布\n\r\n2018-09-04 18:57:02 admin on 192.168.50.74 目录检测中\r\nTest /usr/local/jdk1.7 exists: Success!\r\nTest /usr/local/tomcat_admin exists: Success!\r\n\r\n2018-09-04 18:57:02 admin on 192.168.50.74 代码检出中\r\nSwitched to branch \'master\'\r\nAlready up-to-date.\r\nNote: checking out \'V_20180830_41\'.\r\n\r\nYou are in \'detached HEAD\' state. You can look around, make experimental\r\nchanges and commit them, and you can discard any commits you make in this\r\nstate without impacting any branches by performing another checkout.\r\n\r\nIf you want to create a new branch to retain commits you create, you may\r\ndo so (now or later) by using -b with the checkout command again. Example:\r\n\r\n  git checkout -b new_branch_name\r\n\r\nHEAD is now at a2a1cef... admin发布版本\r\n\r\n2018-09-04 18:57:03 admin on 192.168.50.74 版本检测中\r\nTest Version V_20180830_41 success!\r\n\r\n2018-09-04 18:57:03 admin on 192.168.50.74 服务启动中\r\n[00;31mStoping Tomcat[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n[00;31mwaiting for processes to exit[00m\r\n\r\n[00;31mkilling processes which didn\'t stop after 5 seconds[00m[00;32mStarting tomcat[00m\r\n[00;32mTomcat is running with pid: 93905[00m\r\n\r\n2018-09-04 18:57:10 admin on 192.168.50.74 应用状态检测中\r\n2018-09-04 18:57:08 Tomcat页面出错, 页面返回码为000\r\n2018-09-04 18:57:18 Tomcat页面出错, 页面返回码为000\r\n2018-09-04 18:57:28 Tomcat页面出错, 页面返回码为000\r\n2018-09-04 18:57:39 Tomcat页面出错, 页面返回码为000\r\n2018-09-04 18:57:49 Tomcat页面出错, 页面返回码为000\r\n2018-09-04 18:58:00 Tomcat页面出错, 页面返回码为000\r\n2018-09-04 18:58:10 Tomcat启动成功, 页面返回码为302\r\n\r\n2018-09-04 18:58:23 admin on 192.168.50.74 发布成功\r\n\r\n2018-09-04 18:58:23 admin on 192.168.50.74 发布完成！', '2018-09-04 18:57:01', '2018-09-04 18:58:24');

-- ----------------------------
-- Table structure for `OPS_SYS_LOG_RECORD`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_SYS_LOG_RECORD`;
CREATE TABLE `OPS_SYS_LOG_RECORD` (
  `RECORD_ID` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `USER_ID` bigint(11) DEFAULT NULL COMMENT '用户ID',
  `USER_NAME` varchar(255) DEFAULT NULL,
  `TYPE_ID` bigint(11) DEFAULT NULL COMMENT '类型ID',
  `TYPE_NAME` varchar(255) DEFAULT NULL COMMENT '类型名',
  `RECORD_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录时间',
  `OPERATION` varchar(255) DEFAULT NULL,
  `DETAILS` varchar(255) DEFAULT NULL COMMENT '详情',
  `IS_SUCCESS` int(5) DEFAULT NULL COMMENT '0成功 1失败',
  PRIMARY KEY (`RECORD_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=484 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OPS_SYS_LOG_RECORD
-- ----------------------------
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('1', '1', '超管用户', '1', '配置管理', '2018-08-10 15:48:49', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180810154550】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('2', '1', '超管用户', '1', '配置管理', '2018-08-13 11:38:18', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180813113756】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('3', '1', '超管用户', '1', '配置管理', '2018-08-13 11:38:59', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180813113756】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('4', '1', '超管用户', '1', '配置管理', '2018-08-13 11:39:18', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180813113756】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('5', '1', '超管用户', '1', '配置管理', '2018-08-13 11:39:38', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180813113756】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('6', '1', '超管用户', '2', '版本管理', '2018-08-13 11:41:11', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[154]版本[V_20180808_14]提交记录ID[c82fd339441f7434548e075cb0f4509976aad32f]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('7', '1', '超管用户', '2', '版本管理', '2018-08-13 11:43:29', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[151]message】应用的【V_20180813_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('8', '1', '超管用户', '2', '版本管理', '2018-08-13 11:45:31', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:应用[message]检出本地分支[null]错误：Ref master cannot be resolved', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('9', '1', '超管用户', '2', '版本管理', '2018-08-13 11:48:39', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【MASTER】环境下制作了【[151]message】应用的【V_20180813_01】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('10', '1', '超管用户', '3', '应用发布', '2018-08-13 13:59:02', '用户【超管用户】操作了【应用发布】模块,进行了【重新发布】操作', '在【null】环境下将【[null]null】应用的【V_20180810_00】版本【常规发布】到服务器【null】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('11', '1', '超管用户', '3', '应用发布', '2018-08-13 14:03:42', '用户【超管用户】操作了【应用发布】模块,进行了【重新发布】操作', '在【MASTER】环境下将【[170]micro-act-api】应用的【V_20180810_00】版本【常规发布】到服务器【测试服务器#3】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('12', '1', '超管用户', '1', '配置管理', '2018-08-13 15:45:33', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180810154550】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('13', '1', '超管用户', '2', '版本管理', '2018-08-14 15:32:07', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', 'Ex:应用[taskserver]检出本地分支[null]错误：http://192.168.100.110:31000/E09/P00/MASTER/E05P00/taskserver/config.git: not authorized', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('14', '1', '超管用户', '2', '版本管理', '2018-08-14 15:33:15', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：http://192.168.100.110:31000/E09/P00/MASTER/E00P00/admin/config.git: not authorized', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('15', '1', '超管用户', '3', '应用发布', '2018-08-15 10:18:56', '用户【超管用户】操作了【应用发布】模块,进行了【发布】操作', '在【MASTER】环境下将【[135]app】应用的【V_20180731_00】版本【常规发布】到服务器【虚拟服务器#14】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('16', '1', '超管用户', '3', '应用发布', '2018-08-15 10:19:11', '用户【超管用户】操作了【应用发布】模块,进行了【发布】操作', '在【MASTER】环境下将【[129]admin】应用的【V_20180809_01】版本【容器发布】到服务器【K8S容器管理节点】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('17', '1', '超管用户', '1', '配置管理', '2018-08-15 10:20:34', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【MASTER】环境下【[128]admin】应用的【admin20180815101957】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('18', '1', '超管用户', '2', '版本管理', '2018-08-15 11:10:01', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180815_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('19', '1', '超管用户', '2', '版本管理', '2018-08-15 11:10:38', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[128]版本[V_20180815_00]提交记录ID[e6ad512aabb315dcdcd44d3b1d407699a4be395e]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('20', '1', '超管用户', '2', '版本管理', '2018-08-15 11:48:29', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[128]版本[V_20180815_00]提交记录ID[e6ad512aabb315dcdcd44d3b1d407699a4be395e]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('21', '1', '超管用户', '1', '配置管理', '2018-08-15 15:15:18', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【MASTER】环境下【[128]admin】应用的【admin20180815104406】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('22', '1', '超管用户', '2', '版本管理', '2018-08-15 15:37:32', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('23', '1', '超管用户', '2', '版本管理', '2018-08-15 15:37:35', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('24', '1', '超管用户', '1', '配置管理', '2018-08-15 15:38:55', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【MASTER】环境下【[135]app】应用的【admin20180815110028】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('25', '1', '超管用户', '1', '配置管理', '2018-08-15 15:43:05', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【MASTER】环境下【[135]app】应用的【admin20180815110028】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('26', '1', '超管用户', '1', '配置管理', '2018-08-15 15:43:37', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【MASTER】环境下【[135]app】应用的【admin20180815110028】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('27', '1', '超管用户', '1', '配置管理', '2018-08-15 15:43:51', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【MASTER】环境下【[135]app】应用的【admin20180815110028】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('28', '1', '超管用户', '2', '版本管理', '2018-08-15 16:10:05', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【MASTER】环境下制作了【[154]message】应用的【V_20180815_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('29', '1', '超管用户', '2', '版本管理', '2018-08-15 16:39:39', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('30', '1', '超管用户', '2', '版本管理', '2018-08-15 17:06:20', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('31', '1', '超管用户', '2', '版本管理', '2018-08-15 17:08:38', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('32', '1', '超管用户', '2', '版本管理', '2018-08-15 17:08:44', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('33', '1', '超管用户', '2', '版本管理', '2018-08-15 17:20:37', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('34', '1', '超管用户', '3', '应用发布', '2018-08-15 19:13:19', '用户【超管用户】操作了【应用发布】模块,进行了【发布】操作', '将【MASTER】环境下【[135]app】应用的【admin20180815110028】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('35', '1', '超管用户', '3', '应用发布', '2018-08-15 19:13:19', '用户【超管用户】操作了【应用发布】模块,进行了【发布】操作', '在【MASTER】环境下制作了【[154]message】应用的【V_20180815_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('36', '1', '超管用户', '2', '版本管理', '2018-08-15 19:25:20', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('37', '1', '超管用户', '2', '版本管理', '2018-08-15 19:26:08', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('38', '1', '超管用户', '2', '版本管理', '2018-08-15 19:54:15', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('39', '1', '超管用户', '2', '版本管理', '2018-08-15 19:56:46', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('40', '1', '超管用户', '2', '版本管理', '2018-08-15 19:58:00', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('41', '1', '超管用户', '2', '版本管理', '2018-08-15 20:08:58', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('42', '1', '超管用户', '2', '版本管理', '2018-08-15 21:10:57', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('43', '1', '超管用户', '2', '版本管理', '2018-08-15 21:20:33', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('44', '1', '超管用户', '2', '版本管理', '2018-08-16 10:01:16', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('45', '1', '超管用户', '2', '版本管理', '2018-08-16 10:02:55', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('46', '1', '超管用户', '2', '版本管理', '2018-08-16 10:02:58', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('47', '1', '超管用户', '2', '版本管理', '2018-08-16 10:03:00', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('48', '1', '超管用户', '2', '版本管理', '2018-08-16 10:05:45', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('49', '1', '超管用户', '2', '版本管理', '2018-08-16 10:08:22', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('50', '1', '超管用户', '2', '版本管理', '2018-08-16 10:09:20', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('51', '1', '超管用户', '3', '应用发布', '2018-08-16 11:16:06', '用户【超管用户】操作了【应用发布】模块,进行了【发布】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('52', '1', '超管用户', '3', '应用发布', '2018-08-16 11:16:14', '用户【超管用户】操作了【应用发布】模块,进行了【发布】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('53', '1', '超管用户', '3', '应用发布', '2018-08-16 14:32:14', '用户【超管用户】操作了【应用发布】模块,进行了【发布】操作', '在【MASTER】环境下将【[129]admin】应用的【V_20180809_01】版本【容器发布】到服务器【K8S容器管理节点】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('54', '1', '超管用户', '2', '版本管理', '2018-08-16 14:40:12', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('55', '1', '超管用户', '3', '应用发布', '2018-08-16 14:45:34', '用户【超管用户】操作了【应用发布】模块,进行了【发布】操作', '在【MASTER】环境下将【[129]admin】应用的【V_20180809_01】版本【容器发布】到服务器【K8S容器管理节点】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('56', '1', '超管用户', '3', '应用发布', '2018-08-16 14:51:11', '用户【超管用户】操作了【应用发布】模块,进行了【发布】操作', '在【MASTER】环境下将【[129]admin】应用的【V_20180809_01】版本【容器发布】到服务器【K8S容器管理节点】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('57', '1', '超管用户', '3', '应用发布', '2018-08-16 14:51:28', '用户【超管用户】操作了【应用发布】模块,进行了【发布】操作', '在【MASTER】环境下将【[135]app】应用的【V_20180731_00】版本【常规发布】到服务器【虚拟服务器#14】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('58', '1', '超管用户', '3', '应用发布', '2018-08-16 14:52:00', '用户【超管用户】操作了【应用发布】模块,进行了【重新发布】操作', '在【MASTER】环境下将【[135]app】应用的【V_20180731_00】版本【常规发布】到服务器【专用33:10003】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('59', '1', '超管用户', '2', '版本管理', '2018-08-16 15:02:31', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('60', '1', '超管用户', '3', '应用发布', '2018-08-16 15:10:08', '用户【超管用户】操作了【应用发布】模块,进行了【发布】操作', '在【MASTER】环境下将【[129]admin】应用的【V_20180809_01】版本【容器发布】到服务器【K8S容器管理节点】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('61', '1', '超管用户', '2', '版本管理', '2018-08-16 15:10:47', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('62', '1', '超管用户', '2', '版本管理', '2018-08-16 15:10:49', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('63', '1', '超管用户', '2', '版本管理', '2018-08-16 16:14:02', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('64', '1', '超管用户', '3', '应用发布', '2018-08-16 16:25:04', '用户【超管用户】操作了【应用发布】模块,进行了【发布】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('65', '1', '超管用户', '3', '应用发布', '2018-08-16 16:25:07', '用户【超管用户】操作了【应用发布】模块,进行了【发布】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('66', '1', '超管用户', '3', '应用发布', '2018-08-16 16:26:42', '用户【超管用户】操作了【应用发布】模块,进行了【发布】操作', '在【MASTER】环境下将【[135]app】应用的【V_20180731_00】版本【常规发布】到服务器【虚拟服务器#14】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('67', '1', '超管用户', '2', '版本管理', '2018-08-16 16:46:23', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('68', '1', '超管用户', '2', '版本管理', '2018-08-16 16:50:32', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('69', '1', '超管用户', '2', '版本管理', '2018-08-16 16:54:38', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('70', '1', '超管用户', '2', '版本管理', '2018-08-16 17:00:39', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('71', '1', '超管用户', '2', '版本管理', '2018-08-16 17:02:41', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('72', '1', '超管用户', '1', '配置管理', '2018-08-16 17:59:04', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【MASTER】环境下【[128]admin】应用的【admin20180816175828】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('73', '1', '超管用户', '2', '版本管理', '2018-08-16 19:10:20', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('74', '1', '超管用户', '2', '版本管理', '2018-08-16 19:31:42', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('75', '1', '超管用户', '2', '版本管理', '2018-08-16 19:31:45', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('76', '1', '超管用户', '2', '版本管理', '2018-08-16 19:33:46', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('77', '1', '超管用户', '2', '版本管理', '2018-08-16 19:38:11', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【MASTER】环境下制作了【[151]message】应用的【V_20180816_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('78', '1', '超管用户', '2', '版本管理', '2018-08-16 19:38:34', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【MASTER】环境下制作了【[151]message】应用的【V_20180816_01】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('79', '1', '超管用户', '2', '版本管理', '2018-08-16 20:06:58', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：Ref master cannot be resolved', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('80', '1', '超管用户', '2', '版本管理', '2018-08-16 20:07:00', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：Ref master cannot be resolved', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('81', '1', '超管用户', '2', '版本管理', '2018-08-16 20:07:02', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：Ref master cannot be resolved', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('82', '1', '超管用户', '2', '版本管理', '2018-08-16 20:07:04', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：Ref master cannot be resolved', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('83', '1', '超管用户', '2', '版本管理', '2018-08-16 20:07:06', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：Cannot lock /home/app/E00P00/null/P010/.git/index. Ensure that no other process has an open file handle on the lock file /home/app/E00P00/null/P010/.git/index.lock, then you may delete the lock file and retry.', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('84', '1', '超管用户', '2', '版本管理', '2018-08-16 20:07:09', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('85', '1', '超管用户', '2', '版本管理', '2018-08-16 20:07:42', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('86', '1', '超管用户', '2', '版本管理', '2018-08-16 20:25:32', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[128]版本[V_20180816_00]提交记录ID[5e229817923e4b0c34e6f417baf2edb77a6d3339]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('87', '1', '超管用户', '2', '版本管理', '2018-08-16 20:26:21', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[128]版本[V_20180816_00]提交记录ID[5e229817923e4b0c34e6f417baf2edb77a6d3339]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('88', '1', '超管用户', '2', '版本管理', '2018-08-16 20:26:55', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[128]版本[V_20180816_00]提交记录ID[5e229817923e4b0c34e6f417baf2edb77a6d3339]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('89', '1', '超管用户', '2', '版本管理', '2018-08-16 20:27:03', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[128]版本[V_20180816_00]提交记录ID[5e229817923e4b0c34e6f417baf2edb77a6d3339]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('90', '1', '超管用户', '2', '版本管理', '2018-08-16 20:35:28', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]创建标签[V_20180816_01]失败：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('91', '1', '超管用户', '2', '版本管理', '2018-08-16 20:35:32', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[128]版本[V_20180816_01]提交记录ID[5e229817923e4b0c34e6f417baf2edb77a6d3339]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('92', '1', '超管用户', '2', '版本管理', '2018-08-16 20:35:35', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[128]版本[V_20180816_01]提交记录ID[5e229817923e4b0c34e6f417baf2edb77a6d3339]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('93', '1', '超管用户', '2', '版本管理', '2018-08-16 20:38:09', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[128]版本[V_20180816_01]提交记录ID[5e229817923e4b0c34e6f417baf2edb77a6d3339]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('94', '1', '超管用户', '1', '配置管理', '2018-08-17 10:10:34', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180817101007】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('95', '1', '超管用户', '1', '配置管理', '2018-08-17 10:11:11', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180817101042】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('96', '1', '超管用户', '1', '配置管理', '2018-08-17 10:11:21', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180817101042】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('97', '1', '超管用户', '1', '配置管理', '2018-08-17 10:11:34', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180817101007】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('98', '1', '超管用户', '1', '配置管理', '2018-08-17 10:13:13', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180817101253】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('99', '1', '超管用户', '1', '配置管理', '2018-08-17 10:13:28', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180817101315】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('100', '1', '超管用户', '1', '配置管理', '2018-08-17 10:13:37', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', 'Ex:应用[admin]查询推送本地分支错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('101', '1', '超管用户', '1', '配置管理', '2018-08-17 10:13:38', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180817101315】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('102', '1', '超管用户', '1', '配置管理', '2018-08-17 11:49:40', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180817114910】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('103', '1', '超管用户', '1', '配置管理', '2018-08-17 11:50:12', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180817114948】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('104', '1', '超管用户', '1', '配置管理', '2018-08-17 11:51:19', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', 'Ex:应用[admin]查询推送本地分支错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('105', '1', '超管用户', '1', '配置管理', '2018-08-17 11:51:19', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180817114948】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('106', '1', '超管用户', '1', '配置管理', '2018-08-17 14:17:34', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180817141707】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('107', '1', '超管用户', '1', '配置管理', '2018-08-17 14:18:16', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180817141740】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('108', '1', '超管用户', '1', '配置管理', '2018-08-17 14:20:02', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180817141937】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('109', '1', '超管用户', '2', '版本管理', '2018-08-17 14:20:58', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[148]版本[V_20180727_00]提交记录ID[48c931a02a7adcc792dc6b23e417afc9597f09b4]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('110', '1', '超管用户', '1', '配置管理', '2018-08-17 14:22:59', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180817142218】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('111', '1', '超管用户', '1', '配置管理', '2018-08-17 14:23:17', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180817142218】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('112', '1', '超管用户', '1', '配置管理', '2018-08-17 14:23:21', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180817141707】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('113', '1', '超管用户', '1', '配置管理', '2018-08-17 14:23:26', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180817141740】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('114', '1', '超管用户', '1', '配置管理', '2018-08-17 14:23:31', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【MASTER】环境下【[129]admin】应用的【admin20180817141937】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('115', '1', '超管用户', '2', '版本管理', '2018-08-17 16:24:36', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[154]版本[V_20180808_14]提交记录ID[c82fd339441f7434548e075cb0f4509976aad32f]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('116', '1', '超管用户', '2', '版本管理', '2018-08-17 16:25:19', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[154]message】应用的【V_20180817_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('117', '1', '超管用户', '2', '版本管理', '2018-08-17 16:29:15', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[154]message】应用的【V_20180817_01】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('118', '1', '超管用户', '2', '版本管理', '2018-08-17 16:29:15', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[154]版本[V_20180817_01]提交记录ID[16f325ba0bcfee21364f9d9c8d2ecc7a1079f399]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('119', '1', '超管用户', '2', '版本管理', '2018-08-17 16:29:29', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[154]版本[V_20180817_01]提交记录ID[16f325ba0bcfee21364f9d9c8d2ecc7a1079f399]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('120', '1', '超管用户', '2', '版本管理', '2018-08-17 16:29:39', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[154]message】应用的【V_20180817_02】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('121', '1', '超管用户', '2', '版本管理', '2018-08-17 16:30:01', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[154]message】应用的【V_20180817_03】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('122', '1', '超管用户', '2', '版本管理', '2018-08-17 16:30:02', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[154]版本[V_20180817_03]提交记录ID[56e8fee130279bf2a8afd79028b230446922a0d0]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('123', '1', '超管用户', '2', '版本管理', '2018-08-17 16:30:06', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[154]版本[V_20180817_03]提交记录ID[56e8fee130279bf2a8afd79028b230446922a0d0]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('124', '1', '超管用户', '2', '版本管理', '2018-08-17 16:30:52', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【MASTER】环境下制作了【[154]message】应用的【V_20180817_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('125', '1', '超管用户', '2', '版本管理', '2018-08-17 16:30:53', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【MASTER】环境下制作了【[154]message】应用的【V_20180817_01】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('126', '1', '超管用户', '2', '版本管理', '2018-08-17 16:31:15', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【MASTER】环境下制作了【[154]message】应用的【V_20180817_02】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('127', '1', '超管用户', '2', '版本管理', '2018-08-17 16:31:16', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【MASTER】环境下制作了【[154]message】应用的【V_20180817_03】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('128', '1', '超管用户', '2', '版本管理', '2018-08-17 17:50:42', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【MASTER】环境下制作了【[154]message】应用的【V_20180817_04】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('129', '1', '超管用户', '2', '版本管理', '2018-08-17 17:50:43', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【MASTER】环境下制作了【[154]message】应用的【V_20180817_05】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('130', '1', '超管用户', '2', '版本管理', '2018-08-18 17:25:39', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180818_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('131', '1', '超管用户', '2', '版本管理', '2018-08-18 17:25:54', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180818_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('132', '1', '超管用户', '2', '版本管理', '2018-08-18 17:26:47', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:应用[admin]发布版本创建失败, 错误原因：应用[admin]本地修改提交失败：没有提交信息！', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('133', '1', '超管用户', '2', '版本管理', '2018-08-18 17:27:28', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:应用[admin]发布版本创建失败, 错误原因：应用[admin]本地修改提交失败：没有提交信息！', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('134', '1', '超管用户', '2', '版本管理', '2018-08-18 17:33:09', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180818_02】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('135', '1', '超管用户', '2', '版本管理', '2018-08-20 15:43:00', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【DEV】环境下制作了【[129]admin】应用的【V_20180820_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('136', '1', '超管用户', '2', '版本管理', '2018-08-20 15:44:17', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[129]admin】应用的【V_20180820_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('137', '1', '超管用户', '2', '版本管理', '2018-08-21 15:26:36', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180821_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('138', '1', '超管用户', '2', '版本管理', '2018-08-21 15:36:20', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180821_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('139', '1', '超管用户', '1', '配置管理', '2018-08-21 17:50:43', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180821174749】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('140', '1', '超管用户', '1', '配置管理', '2018-08-21 17:51:02', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180809202441】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('141', '1', '超管用户', '1', '配置管理', '2018-08-21 17:51:39', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180821174749】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('142', '1', '超管用户', '2', '版本管理', '2018-08-21 20:04:53', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【DEV】环境下制作了【[129]admin】应用的【V_20180821_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('143', '1', '超管用户', '2', '版本管理', '2018-08-21 20:05:10', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180821_02】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('144', '1', '超管用户', '2', '版本管理', '2018-08-21 20:06:34', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180821_01】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('145', '1', '超管用户', '2', '版本管理', '2018-08-21 20:07:29', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[129]admin】应用的【V_20180821_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('146', '1', '超管用户', '2', '版本管理', '2018-08-21 21:06:56', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[129]admin】应用的【V_20180821_01】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('147', '1', '超管用户', '2', '版本管理', '2018-08-21 21:39:24', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[129]admin】应用的【V_20180821_02】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('148', '1', '超管用户', '2', '版本管理', '2018-08-22 09:32:51', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:标签[V_20180821_00]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('149', '1', '超管用户', '2', '版本管理', '2018-08-22 09:36:12', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:应用[admin]发布版本创建失败, 错误原因：应用[admin]本地修改提交失败：没有提交信息！', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('150', '1', '超管用户', '2', '版本管理', '2018-08-22 09:41:22', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:应用[admin]发布版本创建失败, 错误原因：应用[admin]本地修改提交失败：没有提交信息！', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('151', '1', '超管用户', '2', '版本管理', '2018-08-22 09:44:22', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[129]admin】应用的【V_20180822_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('152', '1', '超管用户', '2', '版本管理', '2018-08-22 10:16:37', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:标签[V_20180821_00]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('153', '1', '超管用户', '2', '版本管理', '2018-08-22 10:22:19', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[129]admin】应用的【V_20180822_02】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('154', '1', '超管用户', '2', '版本管理', '2018-08-22 10:56:55', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[129]admin】应用的【V_20180822_05】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('155', '1', '超管用户', '2', '版本管理', '2018-08-22 10:59:35', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[129]admin】应用的【V_20180822_06】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('156', '1', '超管用户', '2', '版本管理', '2018-08-22 11:13:41', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【DEV】环境下制作了【[154]message】应用的【V_20180822_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('157', '1', '超管用户', '2', '版本管理', '2018-08-22 11:35:59', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[154]版本[V_20180817_03]提交记录ID[56e8fee130279bf2a8afd79028b230446922a0d0]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('158', '1', '超管用户', '2', '版本管理', '2018-08-22 11:36:12', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[154]版本[V_20180817_03]提交记录ID[56e8fee130279bf2a8afd79028b230446922a0d0]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('159', '1', '超管用户', '1', '配置管理', '2018-08-22 13:53:57', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[148]pay】应用的【admin20180822135300】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('160', '1', '超管用户', '1', '配置管理', '2018-08-22 14:10:48', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[148]pay】应用的【admin20180822135300】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('161', '1', '超管用户', '1', '配置管理', '2018-08-22 14:10:48', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[148]pay】应用的【admin20180822141006】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('162', '1', '超管用户', '1', '配置管理', '2018-08-22 14:11:49', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[148]pay】应用的【admin20180822141006】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('163', '1', '超管用户', '2', '版本管理', '2018-08-22 14:50:32', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[148]版本[V_20180727_00]提交记录ID[48c931a02a7adcc792dc6b23e417afc9597f09b4]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('164', '1', '超管用户', '2', '版本管理', '2018-08-22 15:29:20', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【DEV】环境下制作了【[129]admin】应用的【V_20180822_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('165', '1', '超管用户', '2', '版本管理', '2018-08-22 15:39:37', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:应用[admin]发布版本创建失败, 错误原因：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('166', '1', '超管用户', '2', '版本管理', '2018-08-22 15:40:56', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:应用[admin]发布版本创建失败, 错误原因：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('167', '1', '超管用户', '2', '版本管理', '2018-08-22 16:54:58', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[129]admin】应用的【V_20180822_09】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('168', '1', '超管用户', '1', '配置管理', '2018-08-22 18:13:15', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180822181235】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('169', '1', '超管用户', '1', '配置管理', '2018-08-22 19:22:28', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180822192211】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('170', '1', '超管用户', '2', '版本管理', '2018-08-22 20:12:14', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[129]admin】应用的【V_20180822_10】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('171', '1', '超管用户', '2', '版本管理', '2018-08-22 20:19:43', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:标签[V_20180822_00]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('172', '1', '超管用户', '2', '版本管理', '2018-08-22 20:41:59', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[129]admin】应用的【V_20180822_12】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('173', '1', '超管用户', '1', '配置管理', '2018-08-22 21:01:38', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180822210145】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('174', '1', '超管用户', '1', '配置管理', '2018-08-22 21:01:45', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180822210145】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('175', '1', '超管用户', '1', '配置管理', '2018-08-22 21:07:29', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[148]pay】应用的【admin20180822210613】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('176', '1', '超管用户', '1', '配置管理', '2018-08-22 21:08:08', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[148]pay】应用的【admin20180822210613】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('177', '1', '超管用户', '1', '配置管理', '2018-08-22 21:20:31', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[151]message】应用的【admin20180822211742】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('178', '1', '超管用户', '1', '配置管理', '2018-08-22 21:22:38', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[151]message】应用的【admin20180822211742】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('179', '1', '超管用户', '1', '配置管理', '2018-08-22 21:25:00', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[151]message】应用的【admin20180822211742】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('180', '1', '超管用户', '1', '配置管理', '2018-08-22 21:25:43', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[151]message】应用的【admin20180822211742】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('181', '1', '超管用户', '2', '版本管理', '2018-08-23 11:11:41', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180823_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('182', '1', '超管用户', '2', '版本管理', '2018-08-23 11:13:44', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180823_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('183', '1', '超管用户', '2', '版本管理', '2018-08-23 15:11:49', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180823_01】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('184', '1', '超管用户', '2', '版本管理', '2018-08-23 15:13:44', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180823_01】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('185', '1', '超管用户', '1', '配置管理', '2018-08-23 17:40:50', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180823174022】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('186', '1', '超管用户', '1', '配置管理', '2018-08-23 17:42:56', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[154]message】应用的【admin20180823174200】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('187', '1', '超管用户', '1', '配置管理', '2018-08-23 17:45:39', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[151]message】应用的【admin20180823174449】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('188', '1', '超管用户', '1', '配置管理', '2018-08-23 17:46:40', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[151]message】应用的【admin20180823174622】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('189', '1', '超管用户', '1', '配置管理', '2018-08-23 17:48:12', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[151]message】应用的【admin20180823174721】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('190', '1', '超管用户', '1', '配置管理', '2018-08-23 17:48:31', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180823174726】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('191', '1', '超管用户', '1', '配置管理', '2018-08-23 17:54:32', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180823175048】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('192', '1', '超管用户', '1', '配置管理', '2018-08-23 17:58:36', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[170]micro-act-api】应用的【admin20180823175801】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('193', '1', '超管用户', '1', '配置管理', '2018-08-23 18:31:01', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180823183041】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('194', '1', '超管用户', '1', '配置管理', '2018-08-23 18:39:21', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180823183347】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('195', '1', '超管用户', '1', '配置管理', '2018-08-23 19:09:55', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180823190918】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('196', '1', '超管用户', '2', '版本管理', '2018-08-23 19:23:31', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[128]版本[V_20180823_01]提交记录ID[9d757b9b5af6e7be63bc81056ec0d3d3a93423b8]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('197', '1', '超管用户', '2', '版本管理', '2018-08-23 19:39:46', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180823_02】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('198', '1', '超管用户', '2', '版本管理', '2018-08-23 19:41:43', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[128]版本[V_20180823_02]提交记录ID[73b5d076224196931c179e8e889d5bd36e5ebbd5]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('199', '1', '超管用户', '2', '版本管理', '2018-08-23 20:06:42', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('200', '1', '超管用户', '2', '版本管理', '2018-08-23 20:07:49', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('201', '1', '超管用户', '2', '版本管理', '2018-08-23 20:08:08', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('202', '1', '超管用户', '2', '版本管理', '2018-08-23 20:08:58', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('203', '1', '超管用户', '2', '版本管理', '2018-08-23 20:09:01', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('204', '1', '超管用户', '2', '版本管理', '2018-08-23 20:09:23', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('205', '1', '超管用户', '2', '版本管理', '2018-08-23 20:16:48', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('206', '1', '超管用户', '2', '版本管理', '2018-08-23 20:28:08', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('207', '1', '超管用户', '2', '版本管理', '2018-08-23 20:32:23', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('208', '1', '超管用户', '1', '配置管理', '2018-08-23 20:33:30', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[151]message】应用的【admin20180823174622】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('209', '1', '超管用户', '1', '配置管理', '2018-08-23 20:33:34', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[151]message】应用的【admin20180823174449】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('210', '1', '超管用户', '1', '配置管理', '2018-08-23 20:43:07', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[170]micro-act-api】应用的【admin20180823204219】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('211', '1', '超管用户', '1', '配置管理', '2018-08-23 20:43:50', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[170]micro-act-api】应用的【admin20180823204219】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('212', '1', '超管用户', '1', '配置管理', '2018-08-23 20:44:05', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[170]micro-act-api】应用的【admin20180823204219】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('213', '1', '超管用户', '2', '版本管理', '2018-08-23 20:48:22', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180823_03】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('214', '1', '超管用户', '2', '版本管理', '2018-08-23 20:49:15', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180823_04】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('215', '1', '超管用户', '2', '版本管理', '2018-08-23 20:49:45', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('216', '1', '超管用户', '2', '版本管理', '2018-08-23 20:50:12', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('217', '1', '超管用户', '2', '版本管理', '2018-08-23 20:50:25', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180823_05】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('218', '1', '超管用户', '2', '版本管理', '2018-08-23 20:56:00', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('219', '1', '超管用户', '2', '版本管理', '2018-08-23 20:56:21', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180823_06】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('220', '1', '超管用户', '2', '版本管理', '2018-08-23 20:59:57', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[128]版本[V_20180823_07]提交记录ID[d27447e93f3874e6a9c8ca389ba0cf32a88ec04c]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('221', '1', '超管用户', '2', '版本管理', '2018-08-23 21:01:23', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180823_08】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('222', '1', '超管用户', '2', '版本管理', '2018-08-23 21:01:47', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180823_09】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('223', '1', '超管用户', '1', '配置管理', '2018-08-24 11:21:50', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[148]pay】应用的【admin20180824112152】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('224', '1', '超管用户', '1', '配置管理', '2018-08-24 11:22:45', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[148]pay】应用的【admin20180824112152】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('225', '1', '超管用户', '1', '配置管理', '2018-08-24 11:22:54', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[148]pay】应用的【admin20180824112152】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('226', '1', '超管用户', '1', '配置管理', '2018-08-25 11:47:18', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[148]pay】应用的【admin20180825113959】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('227', '1', '超管用户', '2', '版本管理', '2018-08-25 14:13:26', '用户【超管用户】操作了【版本管理】模块,进行了【制作配置版本】操作', '在【DEV】环境下制作了【[148]pay】应用的【V_20180825_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('228', '1', '超管用户', '2', '版本管理', '2018-08-25 14:14:55', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[148]pay】应用的【V_20180825_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('229', '1', '超管用户', '1', '配置管理', '2018-08-25 14:22:14', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180825142122】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('230', '1', '超管用户', '1', '配置管理', '2018-08-25 14:23:00', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180825142122】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('231', '1', '超管用户', '2', '版本管理', '2018-08-25 14:25:30', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180825_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('232', '1', '超管用户', '1', '配置管理', '2018-08-25 17:29:33', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180825172857】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('233', '1', '超管用户', '1', '配置管理', '2018-08-25 17:30:37', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180825172857】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('234', '1', '超管用户', '2', '版本管理', '2018-08-25 17:31:41', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[129]admin】应用的【V_20180825_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('235', '1', '超管用户', '2', '版本管理', '2018-08-27 10:18:57', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[128]版本[V_20180825_00]提交记录ID[b378f5b30696801e10c721c271275ccd430a1e89]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('236', '1', '超管用户', '2', '版本管理', '2018-08-27 10:23:10', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180827_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('237', '1', '超管用户', '1', '配置管理', '2018-08-27 11:12:34', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180827111203】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('238', '1', '超管用户', '1', '配置管理', '2018-08-27 11:13:03', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180827111203】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('239', '1', '超管用户', '1', '配置管理', '2018-08-27 11:13:49', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[149]data】应用的【admin20180827111322】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('240', '1', '超管用户', '1', '配置管理', '2018-08-27 11:14:21', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[149]data】应用的【admin20180827111322】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('241', '1', '超管用户', '1', '配置管理', '2018-08-27 11:15:06', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[142]www】应用的【admin20180827111443】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('242', '1', '超管用户', '1', '配置管理', '2018-08-27 11:15:16', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[142]www】应用的【admin20180827111443】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('243', '1', '超管用户', '1', '配置管理', '2018-08-27 11:15:56', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[142]www】应用的【admin20180827111443】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('244', '1', '超管用户', '1', '配置管理', '2018-08-27 11:18:10', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[142]www】应用的【admin20180827111755】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('245', '1', '超管用户', '1', '配置管理', '2018-08-27 11:18:29', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[142]www】应用的【admin20180827111755】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('246', '1', '超管用户', '1', '配置管理', '2018-08-27 11:18:32', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[142]www】应用的【admin20180827111443】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('247', '1', '超管用户', '1', '配置管理', '2018-08-27 11:20:34', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[151]message】应用的【admin20180827111550】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('248', '1', '超管用户', '1', '配置管理', '2018-08-27 11:28:13', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180827112412】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('249', '1', '超管用户', '2', '版本管理', '2018-08-27 11:33:22', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[128]版本[V_20180827_00]提交记录ID[b378f5b30696801e10c721c271275ccd430a1e89]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('250', '1', '超管用户', '2', '版本管理', '2018-08-27 11:33:29', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[128]版本[V_20180827_00]提交记录ID[b378f5b30696801e10c721c271275ccd430a1e89]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('251', '1', '超管用户', '2', '版本管理', '2018-08-27 11:34:20', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[128]版本[V_20180827_00]提交记录ID[b378f5b30696801e10c721c271275ccd430a1e89]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('252', '1', '超管用户', '2', '版本管理', '2018-08-27 11:35:56', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[128]版本[V_20180827_00]提交记录ID[b378f5b30696801e10c721c271275ccd430a1e89]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('253', '1', '超管用户', '2', '版本管理', '2018-08-27 11:36:23', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[128]版本[V_20180827_00]提交记录ID[b378f5b30696801e10c721c271275ccd430a1e89]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('254', '1', '超管用户', '1', '配置管理', '2018-08-27 12:03:23', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180827120147】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('255', '1', '超管用户', '1', '配置管理', '2018-08-27 13:42:32', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180827120147】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('256', '1', '超管用户', '1', '配置管理', '2018-08-27 13:42:32', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180827112412】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('257', '1', '超管用户', '1', '配置管理', '2018-08-27 13:42:32', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[151]message】应用的【admin20180827111550】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('258', '1', '超管用户', '1', '配置管理', '2018-08-27 13:43:28', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180827120147】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('259', '1', '超管用户', '1', '配置管理', '2018-08-27 13:43:34', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180827120147】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('260', '1', '超管用户', '2', '版本管理', '2018-08-27 14:24:40', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180827_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('261', '1', '超管用户', '1', '配置管理', '2018-08-27 16:16:10', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[151]message】应用的【admin20180827111550】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('262', '1', '超管用户', '2', '版本管理', '2018-08-28 11:05:29', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[135]app】应用的【V_20180828_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('263', '1', '超管用户', '2', '版本管理', '2018-08-28 11:29:28', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用ID[135]版本[V_20180828_00]提交记录ID[a92e41233c17e880cc07433996b4113fd2be4c62]已存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('264', '1', '超管用户', '2', '版本管理', '2018-08-28 11:30:30', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[135]app】应用的【V_20180828_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('265', '1', '超管用户', '2', '版本管理', '2018-08-28 11:53:19', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[135]app】应用的【V_20180828_02】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('266', '1', '超管用户', '1', '配置管理', '2018-08-28 14:27:43', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[124]taskserver】应用的【admin20180828142429】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('267', '1', '超管用户', '1', '配置管理', '2018-08-28 16:10:51', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180828160842】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('268', '1', '超管用户', '1', '配置管理', '2018-08-28 16:12:45', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180828160842】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('269', '1', '超管用户', '1', '配置管理', '2018-08-28 16:13:26', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[151]message】应用的【admin20180827111550】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('270', '1', '超管用户', '1', '配置管理', '2018-08-28 16:13:28', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[148]pay】应用的【admin20180825113959】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('271', '1', '超管用户', '1', '配置管理', '2018-08-28 16:16:41', '用户【超管用户】操作了【配置管理】模块,进行了【修改配置】操作', '将【DEV】环境下【[148]pay】应用的【admin20180828161439】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('272', '1', '超管用户', '1', '配置管理', '2018-08-28 16:17:11', '用户【超管用户】操作了【配置管理】模块,进行了【审核配置】操作', '将【DEV】环境下【[148]pay】应用的【admin20180828161439】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('273', '1', '超管用户', '2', '版本管理', '2018-08-28 16:21:07', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[148]pay】应用的【V_20180828_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('274', '1', '超管用户', '2', '版本管理', '2018-08-28 16:21:23', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180828_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('275', '1', '超管用户', '2', '版本管理', '2018-08-28 17:02:44', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:标签[V_20180828_03]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('276', '1', '超管用户', '2', '版本管理', '2018-08-28 17:03:22', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:标签[V_20180828_03]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('277', '1', '超管用户', '2', '版本管理', '2018-08-28 17:04:53', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:标签[V_20180828_03]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('278', '1', '超管用户', '2', '版本管理', '2018-08-28 17:05:29', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:标签[V_20180828_03]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('279', '1', '超管用户', '2', '版本管理', '2018-08-28 17:06:29', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:标签[V_20180828_03]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('280', '1', '超管用户', '2', '版本管理', '2018-08-28 17:19:47', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:标签[V_20180828_03]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('281', '1', '超管用户', '2', '版本管理', '2018-08-28 17:19:47', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:标签[V_20180828_03]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('282', '1', '超管用户', '2', '版本管理', '2018-08-28 17:26:41', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:标签[V_20180828_03]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('283', '1', '超管用户', '2', '版本管理', '2018-08-28 17:30:22', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180828_09】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('284', '1', '超管用户', '2', '版本管理', '2018-08-28 17:32:46', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180828_04】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('285', '1', '超管用户', '2', '版本管理', '2018-08-28 17:33:02', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:标签[V_20180828_04]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('286', '1', '超管用户', '2', '版本管理', '2018-08-28 17:34:37', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:标签[V_20180828_04]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('287', '1', '超管用户', '2', '版本管理', '2018-08-28 17:50:32', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180828_12】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('288', '1', '超管用户', '2', '版本管理', '2018-08-28 17:54:56', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180828_13】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('289', '1', '超管用户', '2', '版本管理', '2018-08-28 17:58:29', '用户【超管用户】在【版本管理】模块进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180828_05】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('290', '1', '超管用户', '2', '版本管理', '2018-08-28 17:59:14', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:标签[V_20180828_05]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('291', '1', '超管用户', '2', '版本管理', '2018-08-28 18:01:14', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:应用[admin]发布版本创建失败, 错误原因：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('292', '1', '超管用户', '1', '配置管理', '2018-08-28 19:53:39', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180828195121】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('293', '1', '超管用户', '1', '配置管理', '2018-08-28 19:54:33', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180828195121】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('294', '1', '超管用户', '2', '版本管理', '2018-08-28 19:56:20', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180828_16】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('295', '1', '超管用户', '2', '版本管理', '2018-08-28 20:12:45', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180828_07]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('296', '1', '超管用户', '2', '版本管理', '2018-08-28 20:13:02', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180828_07]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('297', '1', '超管用户', '2', '版本管理', '2018-08-28 20:23:15', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:标签[V_20180828_07]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('298', '1', '超管用户', '2', '版本管理', '2018-08-28 20:24:25', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('299', '1', '超管用户', '1', '配置管理', '2018-08-28 20:24:26', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[151]message】应用的【admin20180828202103】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('300', '1', '超管用户', '1', '配置管理', '2018-08-28 20:26:45', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[151]message】应用的【admin20180828202103】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('301', '1', '超管用户', '1', '配置管理', '2018-08-28 20:27:23', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[151]message】应用的【admin20180828202103】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('302', '1', '超管用户', '1', '配置管理', '2018-08-28 20:28:04', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[151]message】应用的【admin20180828202103】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('303', '1', '超管用户', '1', '配置管理', '2018-08-28 20:28:51', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[151]message】应用的【admin20180828202103】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('304', '1', '超管用户', '2', '版本管理', '2018-08-28 20:30:52', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：Ref master cannot be resolved', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('305', '1', '超管用户', '2', '版本管理', '2018-08-28 20:33:20', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：Ref master cannot be resolved', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('306', '1', '超管用户', '2', '版本管理', '2018-08-28 20:38:17', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：Ref master cannot be resolved', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('307', '1', '超管用户', '2', '版本管理', '2018-08-28 20:49:01', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：Cannot lock \\home\\deployapp\\E00P00\\E001\\P010\\.git\\index. Ensure that no other process has an open file handle on the lock file \\home\\deployapp\\E00P00\\E001\\P010\\.git\\index.lock, then you may delete the lock file and retry.', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('308', '1', '超管用户', '2', '版本管理', '2018-08-28 20:53:42', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180828_27】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('309', '1', '超管用户', '2', '版本管理', '2018-08-28 20:55:32', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180828_09】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('310', '1', '超管用户', '2', '版本管理', '2018-08-28 20:55:47', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('311', '1', '超管用户', '2', '版本管理', '2018-08-28 20:59:12', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180828_29】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('312', '1', '超管用户', '2', '版本管理', '2018-08-28 20:59:57', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180828_30】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('313', '1', '超管用户', '2', '版本管理', '2018-08-28 21:03:47', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180828_11】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('314', '1', '超管用户', '2', '版本管理', '2018-08-28 21:04:06', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('315', '1', '超管用户', '2', '版本管理', '2018-08-28 21:04:33', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180828_32】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('316', '1', '超管用户', '2', '版本管理', '2018-08-28 21:16:02', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('317', '1', '超管用户', '2', '版本管理', '2018-08-28 21:16:28', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180828_34】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('318', '1', '超管用户', '2', '版本管理', '2018-08-28 21:17:32', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('319', '1', '超管用户', '2', '版本管理', '2018-08-28 21:17:57', '用户【超管用户】操作了【版本管理】模块,进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180828_13】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('320', '1', '超管用户', '2', '版本管理', '2018-08-28 21:19:45', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:标签[V_20180828_13]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('321', '1', '超管用户', '2', '版本管理', '2018-08-28 21:22:43', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180828_36】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('322', '1', '超管用户', '2', '版本管理', '2018-08-28 21:23:37', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180828_37】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('323', '1', '超管用户', '2', '版本管理', '2018-08-28 21:25:40', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', 'Ex:标签[V_20180828_14]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('324', '1', '超管用户', '2', '版本管理', '2018-08-28 21:26:00', '用户【超管用户】操作了【版本管理】模块,进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180828_39】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('325', '1', '超管用户', '1', '配置管理', '2018-08-29 09:46:02', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[154]message】应用的【admin20180829094540】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('326', '1', '超管用户', '1', '配置管理', '2018-08-29 09:46:37', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[154]message】应用的【admin20180829094540】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('327', '1', '超管用户', '1', '配置管理', '2018-08-29 09:48:09', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[154]message】应用的【admin20180829094748】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('328', '1', '超管用户', '1', '配置管理', '2018-08-29 09:48:21', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[154]message】应用的【admin20180829094748】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('329', '1', '超管用户', '1', '配置管理', '2018-08-29 09:49:03', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[154]message】应用的【admin20180829094844】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('330', '1', '超管用户', '1', '配置管理', '2018-08-29 09:49:22', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[154]message】应用的【admin20180829094844】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('331', '1', '超管用户', '1', '配置管理', '2018-08-29 09:52:00', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[154]message】应用的【admin20180829095110】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('332', '1', '超管用户', '1', '配置管理', '2018-08-29 09:52:40', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[154]message】应用的【admin20180829095110】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('333', '1', '超管用户', '2', '版本管理', '2018-08-29 10:25:14', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180829_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('334', '1', '超管用户', '1', '配置管理', '2018-08-29 17:27:03', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[131]app】应用的【admin20180829172647】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('335', '1', '超管用户', '2', '版本管理', '2018-08-30 15:24:52', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180830_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('336', '1', '超管用户', '1', '配置管理', '2018-08-30 16:41:49', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180830163942】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('337', '1', '超管用户', '1', '配置管理', '2018-08-30 16:46:07', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180830163942】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('338', '1', '超管用户', '1', '配置管理', '2018-08-30 16:47:36', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180830163942】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('339', '1', '超管用户', '1', '配置管理', '2018-08-30 16:56:22', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180830163942】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('340', '1', '超管用户', '1', '配置管理', '2018-08-30 16:56:45', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180830163942】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('341', '1', '超管用户', '2', '版本管理', '2018-08-30 16:58:58', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_00]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('342', '1', '超管用户', '2', '版本管理', '2018-08-30 17:01:19', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_00]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('343', '1', '超管用户', '2', '版本管理', '2018-08-30 17:01:28', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_00]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('344', '1', '超管用户', '2', '版本管理', '2018-08-30 17:01:31', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', '将【DEV】环境下【[128]admin】应用的【admin20180830163942】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('345', '1', '超管用户', '2', '版本管理', '2018-08-30 17:01:31', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_00]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('346', '1', '超管用户', '2', '版本管理', '2018-08-30 17:01:32', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_00]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('347', '1', '超管用户', '2', '版本管理', '2018-08-30 17:01:50', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_00]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('348', '1', '超管用户', '2', '版本管理', '2018-08-30 18:57:59', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_00]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('349', '1', '超管用户', '2', '版本管理', '2018-08-30 18:57:59', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_00]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('350', '1', '超管用户', '2', '版本管理', '2018-08-30 18:58:00', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_00]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('351', '1', '超管用户', '2', '版本管理', '2018-08-30 18:58:00', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_00]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('352', '1', '超管用户', '1', '配置管理', '2018-08-30 19:00:44', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180830185957】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('353', '1', '超管用户', '1', '配置管理', '2018-08-30 19:01:14', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180830185957】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('354', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 19:06:49', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_01]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('355', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 19:09:34', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:应用[admin]发布版本创建失败, 错误原因：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('356', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 19:17:19', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:应用[admin]发布版本创建失败, 错误原因：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('357', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 19:20:26', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:应用[admin]发布版本创建失败, 错误原因：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('358', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 19:23:42', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:应用[admin]发布版本创建失败, 错误原因：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('359', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 19:35:16', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:应用[admin]发布版本创建失败, 错误原因：null', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('360', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 19:38:03', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180830_19】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('361', '1', '超管用户', '2', '版本管理', '2018-08-30 20:01:44', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_01]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('362', '1', '超管用户', '2', '版本管理', '2018-08-30 20:01:44', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_01]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('363', '1', '超管用户', '1', '配置管理', '2018-08-30 20:02:41', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180830200119】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('364', '1', '超管用户', '2', '版本管理', '2018-08-30 20:04:57', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_01]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('365', '1', '超管用户', '1', '配置管理', '2018-08-30 20:05:20', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180830200119】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('366', '1', '超管用户', '2', '版本管理', '2018-08-30 20:05:20', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_01]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('367', '1', '超管用户', '2', '版本管理', '2018-08-30 20:05:53', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_01]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('368', '1', '超管用户', '2', '版本管理', '2018-08-30 20:06:54', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', '在【DEV】环境下制作了【[129]admin】应用的【V_20180830_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('369', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 20:19:07', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180830_25】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('370', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 20:19:57', '用户【Xxxxx】在【版本管理】模块进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180830_02】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('371', '1', '超管用户', '2', '版本管理', '2018-08-30 20:20:33', '用户【超管用户】在【版本管理】模块进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180830_03】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('372', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 20:27:23', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_03]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('373', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 20:29:17', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180830_27】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('374', '1', '超管用户', '2', '版本管理', '2018-08-30 20:30:18', '用户【超管用户】在【版本管理】模块进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180830_04】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('375', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 20:30:47', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_04]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('376', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 20:32:40', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180830_29】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('377', '1', '超管用户', '2', '版本管理', '2018-08-30 20:33:37', '用户【超管用户】在【版本管理】模块进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180830_05】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('378', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 20:33:59', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_05]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('379', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 20:34:28', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180830_31】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('380', '1', '超管用户', '2', '版本管理', '2018-08-30 20:37:22', '用户【超管用户】在【版本管理】模块进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180830_06】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('381', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 20:37:26', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180830_32】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('382', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 20:37:49', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_06]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('383', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 20:38:50', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180830_34】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('384', '1', '超管用户', '2', '版本管理', '2018-08-30 20:39:01', '用户【超管用户】在【版本管理】模块进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180830_07】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('385', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 20:52:06', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:标签[V_20180830_07]不存在', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('386', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 20:57:53', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：Cannot lock \\home\\app\\E00P00\\E001\\P010\\.git\\index. Ensure that no other process has an open file handle on the lock file \\home\\app\\E00P00\\E001\\P010\\.git\\index.lock, then you may delete the lock file and retry.', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('387', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 20:58:19', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：Cannot lock \\home\\app\\E00P00\\E001\\P010\\.git\\index. Ensure that no other process has an open file handle on the lock file \\home\\app\\E00P00\\E001\\P010\\.git\\index.lock, then you may delete the lock file and retry.', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('388', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 20:58:59', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', 'Ex:应用[admin]检出本地分支[null]错误：Cannot lock \\home\\app\\E00P00\\E001\\P010\\.git\\index. Ensure that no other process has an open file handle on the lock file \\home\\app\\E00P00\\E001\\P010\\.git\\index.lock, then you may delete the lock file and retry.', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('389', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 21:00:45', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180830_40】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('390', '1', '超管用户', '2', '版本管理', '2018-08-30 21:00:57', '用户【超管用户】在【版本管理】模块进行了【制作应用版本】操作', '制作了【[128]admin】应用的【V_20180830_08】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('391', '100', 'Xxxxx', '2', '版本管理', '2018-08-30 21:04:59', '用户【Xxxxx】在【版本管理】模块进行了【制作发布版本】操作', '在【DEV】环境下制作了【[128]admin】应用的【V_20180830_41】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('392', '1', '超管用户', '1', '配置管理', '2018-08-31 14:46:22', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180831144335】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('393', '1', '超管用户', '1', '配置管理', '2018-08-31 14:52:40', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180831144717】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('394', '1', '超管用户', '1', '配置管理', '2018-08-31 14:53:02', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180831144717】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('395', '1', '超管用户', '1', '配置管理', '2018-08-31 14:53:18', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180831144717】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('396', '1', '超管用户', '1', '配置管理', '2018-08-31 14:53:18', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180831144335】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('397', '1', '超管用户', '1', '配置管理', '2018-08-31 14:53:18', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180831144717】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('398', '1', '超管用户', '1', '配置管理', '2018-08-31 14:57:57', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831145530】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('399', '1', '超管用户', '1', '配置管理', '2018-08-31 14:58:27', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831145530】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('400', '1', '超管用户', '1', '配置管理', '2018-08-31 15:09:05', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831150058】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('401', '1', '超管用户', '1', '配置管理', '2018-08-31 15:13:20', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831151220】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('402', '1', '超管用户', '1', '配置管理', '2018-08-31 15:23:15', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831150058】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('403', '1', '超管用户', '1', '配置管理', '2018-08-31 15:23:16', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831151220】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('404', '1', '超管用户', '1', '配置管理', '2018-08-31 15:23:20', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831151220】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('405', '1', '超管用户', '1', '配置管理', '2018-08-31 15:23:24', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180831144717】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('406', '1', '超管用户', '1', '配置管理', '2018-08-31 15:23:54', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831150058】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('407', '1', '超管用户', '1', '配置管理', '2018-08-31 15:26:12', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831150058】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('408', '1', '超管用户', '1', '配置管理', '2018-08-31 15:29:43', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831152751】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('409', '1', '超管用户', '1', '配置管理', '2018-08-31 15:29:59', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831145530】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('410', '1', '超管用户', '1', '配置管理', '2018-08-31 15:30:02', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831151220】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('411', '1', '超管用户', '1', '配置管理', '2018-08-31 15:30:02', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831152751】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('412', '1', '超管用户', '1', '配置管理', '2018-08-31 15:30:05', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180831144717】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('413', '1', '超管用户', '1', '配置管理', '2018-08-31 15:30:07', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831150058】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('414', '1', '超管用户', '1', '配置管理', '2018-08-31 15:30:07', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831150058】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('415', '1', '超管用户', '1', '配置管理', '2018-08-31 15:30:07', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831152751】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('416', '1', '超管用户', '1', '配置管理', '2018-08-31 15:31:17', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831153056】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('417', '1', '超管用户', '1', '配置管理', '2018-08-31 15:32:08', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831153129】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('418', '1', '超管用户', '1', '配置管理', '2018-08-31 15:32:33', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831153129】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('419', '1', '超管用户', '1', '配置管理', '2018-08-31 15:32:35', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831153056】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('420', '1', '超管用户', '1', '配置管理', '2018-08-31 15:41:42', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180831154053】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('421', '1', '超管用户', '1', '配置管理', '2018-08-31 15:43:27', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180831154215】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('422', '1', '超管用户', '1', '配置管理', '2018-08-31 15:43:45', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180831154215】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('423', '1', '超管用户', '1', '配置管理', '2018-08-31 15:43:47', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180831154053】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('424', '1', '超管用户', '1', '配置管理', '2018-08-31 15:46:02', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180831154448】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('425', '1', '超管用户', '1', '配置管理', '2018-08-31 15:48:47', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180831154611】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('426', '1', '超管用户', '1', '配置管理', '2018-08-31 15:49:32', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180831154611】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('427', '1', '超管用户', '1', '配置管理', '2018-08-31 15:49:34', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180831154448】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('428', '1', '超管用户', '1', '配置管理', '2018-08-31 18:58:46', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180831154611】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('429', '1', '超管用户', '1', '配置管理', '2018-08-31 18:58:49', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180831144717】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('430', '1', '超管用户', '1', '配置管理', '2018-08-31 19:27:54', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180831154215】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('431', '1', '超管用户', '1', '配置管理', '2018-08-31 19:27:55', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831150058】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('432', '1', '超管用户', '1', '配置管理', '2018-08-31 19:30:36', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831192859】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('433', '1', '超管用户', '1', '配置管理', '2018-08-31 19:32:34', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831193046】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('434', '1', '超管用户', '1', '配置管理', '2018-08-31 19:37:41', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180831154448】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('435', '1', '超管用户', '1', '配置管理', '2018-08-31 19:37:44', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831193046】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('436', '1', '超管用户', '1', '配置管理', '2018-08-31 19:37:45', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[129]admin】应用的【admin20180831144717】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('437', '1', '超管用户', '1', '配置管理', '2018-08-31 19:37:47', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831193046】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('438', '1', '超管用户', '1', '配置管理', '2018-08-31 19:37:49', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831193046】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('439', '1', '超管用户', '1', '配置管理', '2018-08-31 19:37:49', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180831154448】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('440', '1', '超管用户', '1', '配置管理', '2018-08-31 19:37:49', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831192859】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('441', '1', '超管用户', '1', '配置管理', '2018-08-31 19:37:49', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180831154611】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('442', '1', '超管用户', '1', '配置管理', '2018-08-31 19:37:59', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180831154611】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('443', '1', '超管用户', '1', '配置管理', '2018-08-31 19:37:59', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831193046】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('444', '1', '超管用户', '1', '配置管理', '2018-08-31 19:37:59', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831150058】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('445', '1', '超管用户', '1', '配置管理', '2018-08-31 19:38:31', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[135]app】应用的【admin20180831192859】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('446', '1', '超管用户', '1', '配置管理', '2018-09-03 14:47:39', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903144651】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('447', '1', '超管用户', '1', '配置管理', '2018-09-03 14:48:21', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903144651】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('448', '1', '超管用户', '1', '配置管理', '2018-09-03 14:49:26', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903144845】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('449', '1', '超管用户', '1', '配置管理', '2018-09-03 14:50:29', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903144940】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('450', '1', '超管用户', '1', '配置管理', '2018-09-03 14:51:16', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903144845】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('451', '1', '超管用户', '1', '配置管理', '2018-09-03 14:51:18', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903144940】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('452', '1', '超管用户', '1', '配置管理', '2018-09-03 14:56:24', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903145558】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('453', '1', '超管用户', '1', '配置管理', '2018-09-03 14:56:52', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903145634】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('454', '1', '超管用户', '1', '配置管理', '2018-09-03 15:06:34', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903145634】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('455', '1', '超管用户', '1', '配置管理', '2018-09-03 15:07:26', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903145558】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('456', '1', '超管用户', '1', '配置管理', '2018-09-03 15:42:43', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903154219】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('457', '1', '超管用户', '1', '配置管理', '2018-09-03 15:43:09', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903154252】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('458', '1', '超管用户', '1', '配置管理', '2018-09-03 15:43:22', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903154252】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('459', '1', '超管用户', '1', '配置管理', '2018-09-03 15:43:27', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903154219】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('460', '1', '超管用户', '1', '配置管理', '2018-09-03 16:33:50', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903163323】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('461', '1', '超管用户', '1', '配置管理', '2018-09-03 16:34:13', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903163357】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('462', '1', '超管用户', '1', '配置管理', '2018-09-03 16:34:39', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903163323】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('463', '1', '超管用户', '1', '配置管理', '2018-09-03 16:34:43', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903163357】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('464', '1', '超管用户', '1', '配置管理', '2018-09-03 16:36:36', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903163544】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('465', '1', '超管用户', '1', '配置管理', '2018-09-03 16:37:00', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903163357】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('466', '1', '超管用户', '1', '配置管理', '2018-09-03 16:37:01', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903163544】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('467', '1', '超管用户', '1', '配置管理', '2018-09-03 16:39:25', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903163857】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('468', '1', '超管用户', '1', '配置管理', '2018-09-03 16:43:21', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903163857】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('469', '1', '超管用户', '1', '配置管理', '2018-09-03 16:44:06', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903164350】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('470', '1', '超管用户', '1', '配置管理', '2018-09-03 16:44:19', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903164350】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('471', '1', '超管用户', '1', '配置管理', '2018-09-03 16:44:19', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', 'Ex:Conflicting', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('472', '1', '超管用户', '1', '配置管理', '2018-09-03 16:48:16', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', 'Ex:Conflicting', '1');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('473', '1', '超管用户', '1', '配置管理', '2018-09-03 16:48:25', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903163857】分支【审核不通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('474', '1', '超管用户', '1', '配置管理', '2018-09-03 16:49:26', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903164857】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('475', '1', '超管用户', '1', '配置管理', '2018-09-03 16:50:02', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903164936】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('476', '1', '超管用户', '1', '配置管理', '2018-09-03 16:50:13', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903164936】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('477', '1', '超管用户', '1', '配置管理', '2018-09-03 16:50:18', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180903164857】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('478', '1', '超管用户', '1', '配置管理', '2018-09-03 17:06:14', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[128]admin】应用的【admin20180903155330】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('479', '1', '超管用户', '2', '版本管理', '2018-09-04 10:34:55', '用户【超管用户】在【版本管理】模块进行了【制作应用版本】操作', '制作了【[124]taskserver】应用的【V_20180904_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('480', '1', '超管用户', '1', '配置管理', '2018-09-04 10:35:38', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[124]taskserver】应用的【admin20180904103507】分支提交审核', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('481', '1', '超管用户', '1', '配置管理', '2018-09-04 10:35:49', '用户【超管用户】在【配置管理】模块进行了【审核配置】操作', '将【DEV】环境下【[124]taskserver】应用的【admin20180904103507】分支【审核通过】', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('482', '1', '超管用户', '2', '版本管理', '2018-09-04 10:36:46', '用户【超管用户】在【版本管理】模块进行了【制作发布版本】操作', '在【DEV】环境下制作了【[124]taskserver】应用的【V_20180904_00】版本', '0');
INSERT INTO `OPS_SYS_LOG_RECORD` VALUES ('483', '1', '超管用户', '1', '配置管理', '2018-09-04 20:01:05', '用户【超管用户】在【配置管理】模块进行了【修改配置】操作', '将【DEV】环境下【[136]taskserver】应用的【admin20180904200011】分支提交审核', '0');

-- ----------------------------
-- Table structure for `OPS_SYS_LOG_TYPE`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_SYS_LOG_TYPE`;
CREATE TABLE `OPS_SYS_LOG_TYPE` (
  `TYPE_ID` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '日志类型ID',
  `TYPE_NAME` varchar(255) DEFAULT NULL COMMENT '类型名',
  `IS_ENABLE` int(5) DEFAULT '0' COMMENT '是否生效 0开启，1关闭',
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OPS_SYS_LOG_TYPE
-- ----------------------------
INSERT INTO `OPS_SYS_LOG_TYPE` VALUES ('1', '配置管理', '0');
INSERT INTO `OPS_SYS_LOG_TYPE` VALUES ('2', '版本管理', '0');
INSERT INTO `OPS_SYS_LOG_TYPE` VALUES ('3', '应用发布', '0');

-- ----------------------------
-- Table structure for `OPS_SYS_PARAMETER`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_SYS_PARAMETER`;
CREATE TABLE `OPS_SYS_PARAMETER` (
  `SYS_PARAMETER_ID` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '系统参数ID',
  `SYS_PARAMETER_NAME` varchar(50) NOT NULL COMMENT '系统参数名称',
  `SYS_PARAMETER_VALUE` varchar(255) NOT NULL COMMENT '系统参数值',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`SYS_PARAMETER_ID`) USING BTREE,
  UNIQUE KEY `SYS_PARAMETER_NAME` (`SYS_PARAMETER_NAME`) USING BTREE COMMENT '配置项键名唯一'
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of OPS_SYS_PARAMETER
-- ----------------------------
INSERT INTO `OPS_SYS_PARAMETER` VALUES ('1', 'applicationVersionHomePath', '/home/app', '2018-08-03 11:10:20', '2018-08-03 13:41:51');
INSERT INTO `OPS_SYS_PARAMETER` VALUES ('2', 'configVersionHomePath', '/home/config/', '2018-08-02 19:17:51', '2018-08-03 11:34:24');
INSERT INTO `OPS_SYS_PARAMETER` VALUES ('3', 'deployVersionHomePath', '/home/deployapp/', '2018-08-02 19:18:10', '2018-08-03 11:37:14');
INSERT INTO `OPS_SYS_PARAMETER` VALUES ('4', 'loginSinging', 'beedevops007', '2018-08-03 13:41:23', '2018-08-03 13:41:57');
INSERT INTO `OPS_SYS_PARAMETER` VALUES ('5', 'loginTimeout', '30', '2018-08-03 13:41:23', '2018-08-14 14:45:55');
INSERT INTO `OPS_SYS_PARAMETER` VALUES ('6', 'loginExpireTime', '1800', '2018-08-03 13:41:23', '2018-08-03 13:42:01');
INSERT INTO `OPS_SYS_PARAMETER` VALUES ('7', 'deployLogPath', '/home/log/publish/', '2018-08-03 14:08:30', '2018-08-03 14:08:30');
INSERT INTO `OPS_SYS_PARAMETER` VALUES ('8', 'gitlabUsername', 'chunhai.yang', '2018-08-03 14:08:30', '2018-08-22 15:58:53');
INSERT INTO `OPS_SYS_PARAMETER` VALUES ('9', 'gitlabPassword', 'yh201314', '2018-08-03 14:08:30', '2018-08-14 15:46:25');
INSERT INTO `OPS_SYS_PARAMETER` VALUES ('10', 'gitlabRepoBranch', 'master', '2018-08-03 14:08:30', '2018-08-03 14:08:30');
INSERT INTO `OPS_SYS_PARAMETER` VALUES ('11', 'kscAccessKey', 'AKLT2YN27VrtSCG-h93DG8aBOw', '2018-08-03 14:39:25', '2018-08-03 14:39:25');
INSERT INTO `OPS_SYS_PARAMETER` VALUES ('12', 'kscPrivateKey', 'OFd4v2YRSqZLGeK7KADz/sprJCaF6AlSOY2wu5v13ok3gI+kx1pXEg9faQF6444I4g==', '2018-08-03 14:39:25', '2018-08-03 14:39:25');
INSERT INTO `OPS_SYS_PARAMETER` VALUES ('13', 'kscEndpoint', 'api.ksyun.com', '2018-08-03 14:39:25', '2018-08-28 19:48:42');
INSERT INTO `OPS_SYS_PARAMETER` VALUES ('14', 'tomcatCheckTime', '180', '0000-00-00 00:00:00', '2018-09-04 14:25:53');
INSERT INTO `OPS_SYS_PARAMETER` VALUES ('15', 'backLogPath', 'E:/logs', '2018-09-04 10:04:30', '2018-09-04 11:32:44');

-- ----------------------------
-- Table structure for `OPS_VERSION_APP`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_VERSION_APP`;
CREATE TABLE `OPS_VERSION_APP` (
  `APP_VER_ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '版本ID',
  `APP_CODE` varchar(10) NOT NULL COMMENT '应用编码',
  `APP_ID` bigint(11) unsigned NOT NULL COMMENT '应用ID',
  `APP_VER_NAME` varchar(50) DEFAULT NULL COMMENT '版本名称',
  `VERSION_CODE` varchar(50) NOT NULL COMMENT '版本编号',
  `COMMIT_ID` varchar(255) DEFAULT NULL COMMENT '提交记录ID',
  `STATUS` int(11) NOT NULL DEFAULT '0' COMMENT '版本状态 0为可用，1为不可用',
  `DESCRIPTION` text COMMENT '描述新',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `OPERATE_USER_ID` bigint(11) unsigned DEFAULT NULL COMMENT '操作员',
  `TAG_URL` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '距上次版本文件变更记录',
  `USED` int(11) DEFAULT NULL,
  PRIMARY KEY (`APP_VER_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=463 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='应用版本';

-- ----------------------------
-- Records of OPS_VERSION_APP
-- ----------------------------
INSERT INTO `OPS_VERSION_APP` VALUES ('418', 'P028', '148', null, 'V_20180824_00', '48c931a02a7adcc792dc6b23e417afc9597f09b4', '0', 'Commit ID: 48c931a0\n\nAuthor: 杨春海\n\nTime: Jul 27 2018, 6:51 PM\n\nMessage: 更新 test.jsp', '2018-08-24 11:11:45', '2018-08-24 11:11:45', '1', null, '0');
INSERT INTO `OPS_VERSION_APP` VALUES ('419', 'P010', '128', null, 'V_20180824_00', 'a942509c84e24208b4084d7900426510cffcd468', '0', 'Commit ID: a942509c\n\nAuthor: 杨亮\n\nTime: Aug 23 2018, 9:01 PM\n\nMessage: 更新 add_file', '2018-08-24 13:58:10', '2018-08-24 13:58:10', '1', null, '0');
INSERT INTO `OPS_VERSION_APP` VALUES ('420', 'P010', '128', null, 'V_20180824_01', 'bce0c7ee84d285fe66fa62e84eaecae8494ed03b', '0', 'whj.txt...', '2018-08-24 15:10:57', '2018-08-24 15:10:57', '1', 'V_20180824_00', '0');
INSERT INTO `OPS_VERSION_APP` VALUES ('421', 'P010', '128', null, 'V_20180824_02', '3e2d71a0ed54afdc7e1ec31ad7175d716948f1e7', '0', 'haha...', '2018-08-24 15:13:52', '2018-08-24 15:13:52', '1', 'V_20180824_01', '0');
INSERT INTO `OPS_VERSION_APP` VALUES ('422', 'P028', '148', null, 'V_20180824_01', '0233fe9a2ec3bd9e1e6b9286ef3cfc69a1ac8dbd', '0', 'admin committed files unziped from zip file..', '2018-08-24 17:56:39', '2018-08-24 17:56:39', '1', 'V_20180824_00', '0');
INSERT INTO `OPS_VERSION_APP` VALUES ('423', 'P028', '148', null, 'V_20180824_02', '9f8bddb1f155897f56adf137ab001084747eb9ea', '0', 'admin committed files unziped from zip file..', '2018-08-24 18:02:57', '2018-08-24 18:02:57', '1', 'V_20180824_01', '0');
INSERT INTO `OPS_VERSION_APP` VALUES ('424', 'P028', '148', null, 'V_20180824_03', 'a0ad6efab46d365c5fc7e4a427b67581ce167d89', '0', 'admin 01', '2018-08-24 21:15:27', '2018-08-24 21:15:27', '1', 'V_20180824_02', '0');
INSERT INTO `OPS_VERSION_APP` VALUES ('425', 'P028', '148', null, 'V_20180824_04', 'babc1794f7aaab959fe1e7dc10f8183091b8a0de', '0', 'admin 123', '2018-08-24 21:19:26', '2018-08-24 21:19:26', '1', 'V_20180824_03', '0');
INSERT INTO `OPS_VERSION_APP` VALUES ('426', 'P028', '148', null, 'V_20180824_05', '1c2ad1aa6f75c01d539b4dff1d98bedcca408bf3', '0', 'admin 123', '2018-08-24 21:19:31', '2018-08-24 21:19:31', '1', 'V_20180824_04', '0');
INSERT INTO `OPS_VERSION_APP` VALUES ('427', 'P028', '148', null, 'V_20180825_00', '95589a3f5e10967150b40a0d1a4afe1da7349df6', '0', 'admin 更新应用版本\r\n8/25 11:38', '2018-08-25 11:38:18', '2018-08-25 14:14:55', '1', 'V_20180824_05', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('429', 'P001', '129', null, 'V_20180825_00', '492096b724a7d48c50c1f7178e45c1cf32700f1a', '0', 'admin 8/25 17:19', '2018-08-25 17:24:38', '2018-08-25 17:31:41', '1', null, '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('431', 'P001', '129', null, 'V_20180827_00', '4b8a5005feb5d2819fa81056e047d168fcaa1d6b', '0', 'admin 8/27', '2018-08-27 13:54:39', '2018-08-27 13:54:39', '1', 'V_20180825_00', '0');
INSERT INTO `OPS_VERSION_APP` VALUES ('432', 'P010', '128', null, 'V_20180827_00', 'a5b652801af2dcb900c602628d58814b0453e2f0', '0', '测试', '2018-08-27 16:29:43', '2018-08-27 16:29:43', '1', 'V_20180824_02', '0');
INSERT INTO `OPS_VERSION_APP` VALUES ('434', 'P015', '135', null, 'V_20180828_00', 'a92e41233c17e880cc07433996b4113fd2be4c62', '0', '1130', '2018-08-28 11:30:28', '2018-08-28 11:53:10', '1', null, '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('435', 'P010', '128', null, 'V_20180828_00', '7f329c5a49568380194aa5f9edabe6ab2568154c', '0', 'admin 111111111....', '2018-08-28 11:37:33', '2018-08-28 11:37:33', '1', 'V_20180827_00', '0');
INSERT INTO `OPS_VERSION_APP` VALUES ('436', 'P010', '128', null, 'V_20180828_01', 'dceec83318636217e5dab4ca2efe62daa6abed6d', '0', 'admin 123', '2018-08-28 11:45:18', '2018-08-28 11:45:18', '1', 'V_20180828_00', '0');
INSERT INTO `OPS_VERSION_APP` VALUES ('437', 'P010', '128', null, 'V_20180828_02', '451dd427889aab0a1d106e7d76fa9f43155c584a', '0', 'admin 更新彩票admin应用包\r\n添加Today is Tuesday\r\n8/28 16:04', '2018-08-28 16:07:38', '2018-08-28 16:21:22', '1', 'V_20180828_01', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('438', 'P028', '148', null, 'V_20180828_00', '70e9e53673f4d880650a2d32927f610150defafb', '0', 'admin 更新彩票Pay应用包\r\n8/27 16:03', '2018-08-28 16:07:44', '2018-08-28 16:20:56', '1', 'V_20180825_00', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('439', 'P010', '128', null, 'V_20180828_03', 'bbd68065e12959250c18c12cb8173350df564a43', '0', 'admin 更新admin应用包\r\nC_amdin_0828_00.zip\r\n8/28 17:00', '2018-08-28 17:02:05', '2018-08-28 17:50:32', '1', 'V_20180828_02', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('440', 'P010', '128', null, 'V_20180828_04', 'cf61bd602eb8d23bee26a239bfb0cf8c872f19e8', '0', 'admin应用版本\n2018/08/28 17:32', '2018-08-28 17:32:40', '2018-08-28 17:54:52', '1', 'V_20180828_03', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('441', 'P010', '128', null, 'V_20180828_05', 'c6b0e69a1590da60870e1f69f72249cfd98e5e85', '0', '123456', '2018-08-28 17:58:22', '2018-08-28 17:58:22', '1', 'V_20180828_04', '0');
INSERT INTO `OPS_VERSION_APP` VALUES ('442', 'P010', '128', null, 'V_20180828_06', '957f4e0400d5e8b5faab1463c4b8f888b4243e34', '0', 'admin 更新应用版本\r\n8/28 19:51', '2018-08-28 19:54:43', '2018-08-28 19:56:19', '1', 'V_20180828_05', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('443', 'P010', '128', null, 'V_20180828_07', '68b536a25b2733c645e081c6f4ad9b35cfd4042d', '0', 'admin 上传更新应用包\r\n文件结构不合法\r\n8/28 20:10', '2018-08-28 20:11:50', '2018-08-28 20:53:42', '1', 'V_20180828_06', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('444', 'P010', '128', null, 'V_20180828_08', '6909031a15472b11cfa3bed13719ebd483a6a2cf', '0', 'admin 新增审核状态。。。。', '2018-08-28 20:54:03', '2018-08-28 20:54:03', '1', 'V_20180828_07', '0');
INSERT INTO `OPS_VERSION_APP` VALUES ('445', 'P010', '128', null, 'V_20180828_09', 'd868d7294d7ae651748343ad0e20f31ea8c15e62', '0', '123', '2018-08-28 20:55:31', '2018-08-28 20:59:12', '1', 'V_20180828_08', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('446', 'P010', '128', null, 'V_20180828_10', 'b412d74eb7a7ed5723f149e24117b6f72b9941e6', '0', 'admin liping  lalalalala.....这是审核状态。。。。', '2018-08-28 21:00:01', '2018-08-28 21:00:01', '1', 'V_20180828_09', '0');
INSERT INTO `OPS_VERSION_APP` VALUES ('447', 'P010', '128', null, 'V_20180828_11', '05ca6be419e738e55a5906b2713503f8b72199a6', '0', 'asda', '2018-08-28 21:03:45', '2018-08-28 21:04:33', '1', 'V_20180828_10', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('448', 'P010', '128', null, 'V_20180828_12', '66bc6be3d552a661a6e8599d6540fe9fa37ab0f6', '0', 'admin 123456...', '2018-08-28 21:10:54', '2018-08-28 21:16:28', '1', 'V_20180828_11', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('449', 'P010', '128', null, 'V_20180828_13', '2c8fb7216a6e0f98f4b431db841c07542e847832', '0', 'admin应用版本', '2018-08-28 21:17:56', '2018-08-28 21:22:43', '1', 'V_20180828_12', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('450', 'P010', '128', null, 'V_20180828_14', 'a8c57ae4f83ec88fbb0ad357ecc26d50c8cbdca5', '0', 'admin 李萍。。。这是审核状态。。。', '2018-08-28 21:25:18', '2018-08-28 21:26:00', '1', 'V_20180828_13', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('451', 'P010', '128', null, 'V_20180828_15', '6a14da1fbc722a658d2ac5729d699671130d7286', '0', 'admin 111111', '2018-08-28 21:29:26', '2018-08-30 15:24:46', '1', 'V_20180828_14', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('452', 'P010', '128', null, 'V_20180830_00', 'ec88f62b3e6a4be66499878c33e4ecc66528f5ca', '0', 'admin 8/30 16:39\r\n更新应用发布包', '2018-08-30 16:44:52', '2018-08-30 16:44:52', '1', 'V_20180828_15', '0');
INSERT INTO `OPS_VERSION_APP` VALUES ('453', 'P010', '128', null, 'V_20180830_01', 'b9426be0993f4066bb41fa74661b77b275216d7e', '0', 'admin 8/30 19:00', '2018-08-30 19:03:45', '2018-08-30 19:38:03', '1', 'V_20180830_00', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('454', 'P001', '129', null, 'V_20180830_00', '5282729b69ba5c7253426d7e2a2e87617755b73a', '0', 'admin 更新应用包\r\nHello，This is Devops！\r\n.<8/30 19:55>', '2018-08-30 20:00:47', '2018-08-30 20:06:54', '1', 'V_20180827_00', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('455', 'P010', '128', null, 'V_20180830_02', '841fe054acdddf81a9ac4adb52c3772dbed3bd1e', '0', '123123', '2018-08-30 20:19:55', '2018-08-30 20:19:55', '100', 'V_20180830_01', '0');
INSERT INTO `OPS_VERSION_APP` VALUES ('456', 'P010', '128', null, 'V_20180830_03', '87d7db71ca16cd54dc6568c215699ecdcaff3a5f', '0', 'updata 132', '2018-08-30 20:20:16', '2018-08-30 20:29:16', '1', 'V_20180830_02', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('457', 'P010', '128', null, 'V_20180830_04', 'd8e1c26a4e63a83a0cc1cf64a28bb72f4fbab408', '0', '123123', '2018-08-30 20:30:06', '2018-08-30 20:32:39', '1', 'V_20180830_03', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('458', 'P010', '128', null, 'V_20180830_05', 'c7596e6cd9a97da75e17cffc5308f240c62a90ca', '0', 'admin应用版本', '2018-08-30 20:33:34', '2018-08-30 20:34:27', '1', 'V_20180830_04', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('459', 'P010', '128', null, 'V_20180830_06', '6b4b8f9076bfec0fc5a8d8093eccff8c7a4ff976', '0', '123123', '2018-08-30 20:37:20', '2018-08-30 20:38:50', '1', 'V_20180830_05', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('460', 'P010', '128', null, 'V_20180830_07', '278dd398c74f5af1462ff4c9f449c98d0f76622d', '0', '阿瓦达', '2018-08-30 20:38:59', '2018-08-30 21:00:45', '1', 'V_20180830_06', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('461', 'P010', '128', null, 'V_20180830_08', '83a8cd615695609f7490510bfd4146a272436d06', '0', 'admin发布版本', '2018-08-30 21:00:50', '2018-08-30 21:04:59', '1', 'V_20180830_07', '1');
INSERT INTO `OPS_VERSION_APP` VALUES ('462', 'P006', '124', null, 'V_20180904_00', '5505490eac38638f44f8acdb276dfedcb0154eb3', '0', '新增taskserver版本', '2018-09-04 10:34:53', '2018-09-04 10:36:45', '1', null, '1');

-- ----------------------------
-- Table structure for `OPS_VERSION_APP_DEP`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_VERSION_APP_DEP`;
CREATE TABLE `OPS_VERSION_APP_DEP` (
  `DEP_APP_VER_ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '版本ID',
  `APP_ENV_ID` bigint(11) unsigned DEFAULT NULL COMMENT '应用环境ID',
  `APP_VERSION_ID` bigint(11) unsigned NOT NULL COMMENT '应用版本ID',
  `CONFIG_VERSION_ID` bigint(11) unsigned DEFAULT NULL COMMENT '配置版本ID',
  `VERSION_CODE` varchar(50) NOT NULL COMMENT '发布版本CODE',
  `COMMIT_ID` varchar(255) DEFAULT NULL COMMENT '提交记录ID',
  `STATUS` int(11) NOT NULL DEFAULT '0' COMMENT '版本状态 0为可用，1为不可用',
  `DESCRIPTION` varchar(1024) DEFAULT NULL COMMENT '描述新',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `OPERATE_USER_ID` bigint(11) unsigned DEFAULT NULL COMMENT '操作员',
  `TAG_URL` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '距上次版本文件变更记录',
  `USED` int(11) DEFAULT NULL,
  PRIMARY KEY (`DEP_APP_VER_ID`) USING BTREE,
  KEY `index_appenv` (`APP_ENV_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=369 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='应用发布版本';

-- ----------------------------
-- Records of OPS_VERSION_APP_DEP
-- ----------------------------
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('337', '283', '427', '302', 'V_20180825_00', null, '0', '制定发布版本\n8/25 14:14', '2018-08-25 14:14:55', '2018-08-25 14:14:55', '1', null, '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('338', '274', '428', '303', 'V_20180825_00', null, '0', '制定admin发布版本\n8/25 14:24', '2018-08-25 14:25:19', '2018-08-25 14:25:19', '1', null, '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('339', '288', '429', '304', 'V_20180825_00', null, '0', '制定发布版本\n8/25 17:31', '2018-08-25 17:31:41', '2018-08-25 17:31:41', '1', null, '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('340', '274', '430', '303', 'V_20180827_00', null, '0', '制定发布版本', '2018-08-27 14:24:40', '2018-08-27 14:24:40', '1', 'V_20180825_00', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('341', '289', '434', null, 'V_20180828_02', null, '0', '制作发布版本 static', '2018-08-28 11:52:58', '2018-09-04 14:34:21', '1', 'V_20180828_01', '1');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('342', '283', '438', '306', 'V_20180828_00', null, '0', '1. 制定彩票admin发布版本\n2. 制定彩票pay发布版本', '2018-08-28 16:20:56', '2018-08-30 17:17:49', '1', 'V_20180825_00', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('343', '274', '437', '305', 'V_20180828_00', null, '0', '1. 制定彩票admin发布版本\n2. 制定彩票pay发布版本', '2018-08-28 16:21:22', '2018-09-04 14:22:37', '1', 'V_20180827_00', '1');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('344', '274', '437', '305', 'V_20180828_09', null, '0', 'admin', '2018-08-28 17:30:11', '2018-08-28 17:30:11', '1', 'V_20180828_08', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('345', '274', '439', '305', 'V_20180828_12', null, '0', '123321', '2018-08-28 17:50:32', '2018-08-28 17:50:32', '1', 'V_20180828_11', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('346', '274', '440', '305', 'V_20180828_13', null, '0', '17:54...', '2018-08-28 17:54:52', '2018-08-28 17:54:52', '1', 'V_20180828_12', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('347', '274', '442', '307', 'V_20180828_16', null, '0', '发布admin版本\n8/28 19:55', '2018-08-28 19:56:19', '2018-08-30 10:04:32', '1', 'V_20180828_15', '1');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('348', '274', '443', '307', 'V_20180828_27', null, '0', '奥维德', '2018-08-28 20:53:42', '2018-08-28 20:53:42', '1', 'V_20180828_26', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('349', '274', '445', '307', 'V_20180828_29', null, '0', 'admin发布版本', '2018-08-28 20:59:12', '2018-08-28 20:59:12', '1', 'V_20180828_28', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('350', '274', '445', '307', 'V_20180828_30', null, '0', '发布版本admin', '2018-08-28 20:59:57', '2018-08-28 20:59:57', '1', 'V_20180828_29', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('351', '274', '447', '307', 'V_20180828_32', null, '0', 'asdasd', '2018-08-28 21:04:33', '2018-08-28 21:04:33', '1', 'V_20180828_31', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('352', '274', '448', '307', 'V_20180828_34', null, '0', 'amdin 发布版本', '2018-08-28 21:16:28', '2018-08-28 21:16:28', '1', 'V_20180828_33', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('353', '274', '449', '307', 'V_20180828_36', null, '0', 'admin 发布版本2123', '2018-08-28 21:22:43', '2018-08-28 21:22:43', '1', 'V_20180828_35', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('354', '274', '449', '307', 'V_20180828_37', null, '0', 'admin发布版本', '2018-08-28 21:23:37', '2018-08-28 21:23:37', '1', 'V_20180828_36', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('355', '274', '450', '307', 'V_20180828_39', null, '0', 'admin发布版本', '2018-08-28 21:26:00', '2018-08-28 21:26:00', '1', 'V_20180828_38', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('356', '274', '443', '307', 'V_20180829_00', null, '0', '制定admin发布版本\n8/29', '2018-08-29 10:25:03', '2018-08-30 17:38:35', '1', 'V_20180828_39', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('357', '274', '451', '307', 'V_20180830_00', null, '0', '8/30', '2018-08-30 15:24:46', '2018-08-30 17:48:57', '1', 'V_20180829_00', '1');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('358', '274', '453', '313', 'V_20180830_19', null, '0', '123', '2018-08-30 19:38:03', '2018-08-30 19:38:03', '100', 'V_20180830_18', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('359', '288', '454', '314', 'V_20180830_00', null, '0', '8/30-20:06', '2018-08-30 20:06:54', '2018-08-30 20:06:54', '1', 'V_20180825_00', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('360', '274', '453', '312', 'V_20180830_25', null, '0', '123', '2018-08-30 20:19:07', '2018-08-30 20:19:07', '100', 'V_20180830_24', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('361', '274', '456', '313', 'V_20180830_27', null, '0', '123', '2018-08-30 20:29:16', '2018-08-30 20:29:16', '100', 'V_20180830_26', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('362', '274', '457', '313', 'V_20180830_29', null, '0', 'admin发布版本', '2018-08-30 20:32:39', '2018-08-30 20:32:39', '100', 'V_20180830_28', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('363', '274', '458', '313', 'V_20180830_31', null, '0', 'admin发布版本', '2018-08-30 20:34:27', '2018-08-30 20:34:27', '100', 'V_20180830_30', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('364', '274', '458', '313', 'V_20180830_32', null, '0', 'admin发布版本', '2018-08-30 20:37:26', '2018-08-30 20:37:26', '100', 'V_20180830_31', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('365', '274', '459', '313', 'V_20180830_34', null, '0', '发布版本21313 人定胜天', '2018-08-30 20:38:50', '2018-08-30 20:38:50', '100', 'V_20180830_33', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('366', '274', '460', '313', 'V_20180830_40', null, '0', '阿斯顿', '2018-08-30 21:00:45', '2018-08-30 21:00:45', '100', 'V_20180830_39', '0');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('367', '274', '461', '313', 'V_20180830_41', null, '0', 'admin发布版本', '2018-08-30 21:04:58', '2018-09-04 17:53:35', '100', 'V_20180830_40', '1');
INSERT INTO `OPS_VERSION_APP_DEP` VALUES ('368', '272', '462', '338', 'V_20180904_00', null, '0', '指定taskserver发布版本', '2018-09-04 10:36:45', '2018-09-04 10:39:57', '1', null, '1');

-- ----------------------------
-- Table structure for `OPS_VERSION_APP_DEP_HIS`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_VERSION_APP_DEP_HIS`;
CREATE TABLE `OPS_VERSION_APP_DEP_HIS` (
  `DEP_VER_HIS_ID` bigint(10) NOT NULL AUTO_INCREMENT,
  `APP_ENV_ID` bigint(11) DEFAULT NULL,
  `APP_ID` bigint(11) DEFAULT NULL,
  `APP_VERSION_ID` bigint(11) DEFAULT NULL,
  `CONFIG_VERSION_ID` bigint(11) DEFAULT NULL,
  `VERSION_CODE` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(1024) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `OPERATE_USER_ID` bigint(11) DEFAULT NULL,
  `PRODUCTION_STATUS` int(11) DEFAULT NULL,
  `STATUS_MESSAGE` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`DEP_VER_HIS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=382 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OPS_VERSION_APP_DEP_HIS
-- ----------------------------
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('289', '283', '148', '427', '302', 'V_20180825_00', '制定发布版本\n8/25 14:14', '2018-08-25 14:14:36', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('290', '274', '128', '428', '303', 'V_20180825_00', '制定admin发布版本\n8/25 14:24', '2018-08-25 14:24:36', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('291', '288', '129', '429', '304', 'V_20180825_00', '制定发布版本\n8/25 17:31', '2018-08-25 17:31:08', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('292', '274', '128', '430', '303', 'V_20180827_00', '制定发布版本', '2018-08-27 14:23:57', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('293', '289', '135', '433', null, 'V_20180828_00', 'static 发布版本', '2018-08-28 11:28:08', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('294', '289', '135', '434', null, 'V_20180828_01', 'static发布版本', '2018-08-28 11:31:26', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('295', '289', '135', '434', null, 'V_20180828_02', '制作发布版本 static', '2018-08-28 11:51:34', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('296', '274', '128', '437', '305', 'V_20180828_00', '1. 制定彩票admin发布版本\n2. 制定彩票pay发布版本', '2018-08-28 16:20:39', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('297', '283', '148', '438', '306', 'V_20180828_00', '1. 制定彩票admin发布版本\n2. 制定彩票pay发布版本', '2018-08-28 16:20:39', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('298', '274', '128', '439', '305', 'V_20180828_01', '更新制定发布版本\n8/28 17:03', '2018-08-28 17:02:43', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('299', '274', '128', '439', '305', 'V_20180828_02', '更新制定发布版本\n8/28 17:03', '2018-08-28 17:03:21', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('300', '274', '128', '439', '305', 'V_20180828_03', '更新制定发布版本\n8/28 17:05', '2018-08-28 17:04:53', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('301', '274', '128', '439', '305', 'V_20180828_04', '制定发布版本\n8/28 17:05', '2018-08-28 17:05:28', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('302', '274', '128', '439', '305', 'V_20180828_05', '更新制定发布版本\n8/28 17:06', '2018-08-28 17:06:28', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('303', '274', '128', '439', '305', 'V_20180828_06', 'admin发布版本', '2018-08-28 17:19:36', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('304', '274', '128', '439', '305', 'V_20180828_07', 'admin发布版本', '2018-08-28 17:19:42', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('305', '274', '128', '439', '305', 'V_20180828_08', 'admin发布版本', '2018-08-28 17:26:35', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('306', '274', '128', '437', '305', 'V_20180828_09', 'admin', '2018-08-28 17:29:59', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('307', '274', '128', '440', '305', 'V_20180828_10', 'admin发布版本', '2018-08-28 17:33:01', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('308', '274', '128', '440', '305', 'V_20180828_11', 'admin\n发布版本', '2018-08-28 17:34:35', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('309', '274', '128', '439', '305', 'V_20180828_12', '123321', '2018-08-28 17:42:03', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('310', '274', '128', '440', '305', 'V_20180828_13', '17:54...', '2018-08-28 17:54:04', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('311', '274', '128', '441', '305', 'V_20180828_14', '1236546', '2018-08-28 17:59:12', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('312', '274', '128', '441', '305', 'V_20180828_15', '1322', '2018-08-28 18:00:53', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('313', '274', '128', '442', '307', 'V_20180828_16', '发布admin版本\n8/28 19:55', '2018-08-28 19:55:33', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('314', '274', '128', '443', '307', 'V_20180828_17', '制定应用版本\n8/28 20:12\n应用版本异常', '2018-08-28 20:12:38', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('315', '274', '128', '443', '307', 'V_20180828_18', '制定应用版本\n8/28 20:12\n应用版本异常', '2018-08-28 20:13:01', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('316', '274', '128', '443', '307', 'V_20180828_19', 'admin发布版本', '2018-08-28 20:22:52', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('317', '274', '128', '443', '307', 'V_20180828_20', 'admin发布版本', '2018-08-28 20:24:06', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('318', '274', '128', '443', '307', 'V_20180828_21', 'admin发布版本', '2018-08-28 20:26:34', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('319', '274', '128', '443', '307', 'V_20180828_22', '123', '2018-08-28 20:30:33', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('320', '274', '128', '443', '307', 'V_20180828_23', 'admin 发布版本', '2018-08-28 20:33:02', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('321', '274', '128', '443', '307', 'V_20180828_24', 'admin 发布版本', '2018-08-28 20:34:43', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('322', '274', '128', '443', '307', 'V_20180828_25', '阿斯达13', '2018-08-28 20:41:39', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('323', '274', '128', '443', '307', 'V_20180828_26', '阿斯达12', '2018-08-28 20:48:59', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('324', '274', '128', '443', '307', 'V_20180828_27', '奥维德', '2018-08-28 20:52:38', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('325', '274', '128', '445', '307', 'V_20180828_28', 'admin发布版本', '2018-08-28 20:55:46', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('326', '274', '128', '445', '307', 'V_20180828_29', 'admin发布版本', '2018-08-28 20:58:36', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('327', '274', '128', '445', '307', 'V_20180828_30', '发布版本admin', '2018-08-28 20:59:38', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('328', '274', '128', '447', '307', 'V_20180828_31', 'asdasd', '2018-08-28 21:04:05', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('329', '274', '128', '447', '307', 'V_20180828_32', 'asdasd', '2018-08-28 21:04:11', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('330', '274', '128', '448', '307', 'V_20180828_33', 'amdin 发布版本', '2018-08-28 21:16:00', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('331', '274', '128', '448', '307', 'V_20180828_34', 'amdin 发布版本', '2018-08-28 21:16:04', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('332', '274', '128', '449', '307', 'V_20180828_35', 'admin 发布版本2123', '2018-08-28 21:18:46', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('333', '274', '128', '449', '307', 'V_20180828_36', 'admin 发布版本2123', '2018-08-28 21:19:51', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('334', '274', '128', '449', '307', 'V_20180828_37', 'admin发布版本', '2018-08-28 21:23:14', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('335', '274', '128', '450', '307', 'V_20180828_38', 'admin发布版本', '2018-08-28 21:25:38', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('336', '274', '128', '450', '307', 'V_20180828_39', 'admin发布版本', '2018-08-28 21:25:41', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('337', '274', '128', '443', '307', 'V_20180829_00', '制定admin发布版本\n8/29', '2018-08-29 10:24:15', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('338', '274', '128', '451', '307', 'V_20180830_00', '8/30', '2018-08-30 15:24:07', '1', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('339', '274', '128', '452', '312', 'V_20180830_01', '制定发布版本\n8/30\n16:59', '2018-08-30 16:58:49', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('340', '274', '128', '452', '312', 'V_20180830_02', '制定发布版本\n8/30\n16:59', '2018-08-30 17:01:18', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('341', '274', '128', '452', '312', 'V_20180830_03', '制定发布版本\n8/30\n16:59', '2018-08-30 17:01:27', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('342', '274', '128', '452', '312', 'V_20180830_04', '制定发布版本\n8/30\n16:59', '2018-08-30 17:01:31', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('343', '274', '128', '452', '312', 'V_20180830_05', '制定发布版本\n8/30\n16:59', '2018-08-30 17:01:31', '1', '300', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('344', '274', '128', '452', '312', 'V_20180830_06', '8/30\n17:01', '2018-08-30 17:01:49', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('345', '274', '128', '452', '312', 'V_20180830_07', '8/30\n17:01', '2018-08-30 18:57:54', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('346', '274', '128', '452', '312', 'V_20180830_08', '8/30\n17:01', '2018-08-30 18:57:55', '1', '300', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('347', '274', '128', '452', '312', 'V_20180830_09', '8/30\n17:01', '2018-08-30 18:57:56', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('348', '274', '128', '452', '312', 'V_20180830_10', '8/30\n17:01', '2018-08-30 18:57:59', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('349', '274', '128', '452', '312', 'V_20180830_11', 'admin发布版本', '2018-08-30 19:00:34', '100', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('350', '274', '128', '453', '313', 'V_20180830_12', 'a萨达', '2018-08-30 19:03:56', '100', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('351', '274', '128', '453', '313', 'V_20180830_13', '暗文', '2018-08-30 19:04:57', '100', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('352', '274', '128', '453', '313', 'V_20180830_14', '阿斯顿', '2018-08-30 19:08:07', '100', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('353', '274', '128', '453', '313', 'V_20180830_15', '昂达', '2018-08-30 19:10:00', '100', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('354', '274', '128', '453', '313', 'V_20180830_16', '阿斯顿', '2018-08-30 19:18:06', '100', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('355', '274', '128', '453', '313', 'V_20180830_17', '昂达', '2018-08-30 19:20:32', '100', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('356', '274', '128', '453', '313', 'V_20180830_18', 'asd', '2018-08-30 19:24:28', '100', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('357', '274', '128', '453', '313', 'V_20180830_19', '123', '2018-08-30 19:36:09', '100', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('358', '274', '128', '453', '313', 'V_20180830_20', '123', '2018-08-30 20:01:32', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('359', '274', '128', '453', '313', 'V_20180830_21', '123', '2018-08-30 20:01:35', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('360', '274', '128', '453', '313', 'V_20180830_22', '嘻嘻', '2018-08-30 20:04:56', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('361', '274', '128', '453', '312', 'V_20180830_23', '嘻嘻', '2018-08-30 20:05:20', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('362', '274', '128', '453', '312', 'V_20180830_24', '方法', '2018-08-30 20:05:53', '1', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('363', '288', '129', '454', '314', 'V_20180830_00', '8/30-20:06', '2018-08-30 20:06:13', '1', '200', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('364', '274', '128', '453', '312', 'V_20180830_25', '123', '2018-08-30 20:12:09', '100', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('365', '274', '128', '456', '313', 'V_20180830_26', 'admin发布版本', '2018-08-30 20:21:07', '100', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('366', '274', '128', '456', '313', 'V_20180830_27', '123', '2018-08-30 20:27:57', '100', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('367', '274', '128', '457', '313', 'V_20180830_28', 'admin发布版本', '2018-08-30 20:30:45', '100', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('368', '274', '128', '457', '313', 'V_20180830_29', 'admin发布版本', '2018-08-30 20:31:51', '100', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('369', '274', '128', '458', '313', 'V_20180830_30', 'admin发布版本', '2018-08-30 20:33:57', '100', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('370', '274', '128', '458', '313', 'V_20180830_31', 'admin发布版本', '2018-08-30 20:34:06', '100', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('371', '274', '128', '458', '313', 'V_20180830_32', 'admin发布版本', '2018-08-30 20:37:04', '100', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('372', '274', '128', '459', '313', 'V_20180830_33', '发布版本21313 人定胜天', '2018-08-30 20:37:47', '100', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('373', '274', '128', '459', '313', 'V_20180830_34', '发布版本21313 人定胜天', '2018-08-30 20:38:25', '100', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('374', '274', '128', '460', '313', 'V_20180830_35', 'admin发布版本', '2018-08-30 20:48:59', '100', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('375', '274', '128', '460', '313', 'V_20180830_36', 'admin发布版本', '2018-08-30 20:52:21', '100', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('376', '274', '128', '460', '313', 'V_20180830_37', 'admin，in', '2018-08-30 20:57:52', '100', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('377', '274', '128', '460', '313', 'V_20180830_38', '阿斯顿', '2018-08-30 20:58:19', '100', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('378', '274', '128', '460', '313', 'V_20180830_39', '阿斯顿', '2018-08-30 20:58:59', '100', '0', '制作失败');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('379', '274', '128', '460', '313', 'V_20180830_40', '阿斯顿', '2018-08-30 20:59:50', '100', '0', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('380', '274', '128', '461', '313', 'V_20180830_41', 'admin发布版本', '2018-08-30 21:01:09', '100', '200', '制作成功');
INSERT INTO `OPS_VERSION_APP_DEP_HIS` VALUES ('381', '272', '124', '462', '338', 'V_20180904_00', '指定taskserver发布版本', '2018-09-04 10:36:07', '1', '200', '制作成功');

-- ----------------------------
-- Table structure for `OPS_VERSION_APP_IMG`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_VERSION_APP_IMG`;
CREATE TABLE `OPS_VERSION_APP_IMG` (
  `APP_IMAGES_ID` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '应用镜像id',
  `APP_ID` bigint(10) unsigned DEFAULT NULL COMMENT '应用Id',
  `ENV_ID` bigint(10) unsigned DEFAULT NULL COMMENT '环境ID',
  `PRO_ID` bigint(10) unsigned DEFAULT NULL COMMENT '项目ID',
  `APP_VERSION_ID` bigint(11) unsigned NOT NULL COMMENT '应用版本ID',
  `CONFIG_VERSION_ID` bigint(11) unsigned NOT NULL COMMENT '配置版本ID',
  `VERSION_CODE` varchar(50) NOT NULL COMMENT '发布版本CODE',
  `APP_IMAGES_NAME` varchar(50) NOT NULL COMMENT '应用镜像名称',
  `APP_IMAGES_TAG` varchar(50) NOT NULL DEFAULT 'latest' COMMENT '应用镜像标签',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `OPERATE_USER_ID` bigint(11) unsigned DEFAULT NULL COMMENT '操作员',
  `GIT_URL` varchar(200) DEFAULT NULL COMMENT '远程仓库地址',
  PRIMARY KEY (`APP_IMAGES_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of OPS_VERSION_APP_IMG
-- ----------------------------

-- ----------------------------
-- Table structure for `OPS_VERSION_APP_SYNC`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_VERSION_APP_SYNC`;
CREATE TABLE `OPS_VERSION_APP_SYNC` (
  `SYNC_ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '同步ID',
  `OLD_ENV_ID` bigint(10) unsigned NOT NULL COMMENT '原环境',
  `SYNC_ENV_ID` bigint(10) unsigned NOT NULL COMMENT '同步环境',
  `SYNC_APP_ID` bigint(10) unsigned NOT NULL COMMENT '同步应用',
  `SYNC_VERSION_CODE` varchar(10) CHARACTER SET utf8mb4 NOT NULL COMMENT '同步版本号',
  `OPERATE_USER_ID` bigint(11) unsigned NOT NULL COMMENT '操作员',
  `SYNC_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '同步时间',
  `SYNC_FOLDER_NAME` varchar(256) NOT NULL COMMENT '同步文件',
  PRIMARY KEY (`SYNC_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of OPS_VERSION_APP_SYNC
-- ----------------------------

-- ----------------------------
-- Table structure for `OPS_VERSION_CONFIG`
-- ----------------------------
DROP TABLE IF EXISTS `OPS_VERSION_CONFIG`;
CREATE TABLE `OPS_VERSION_CONFIG` (
  `CONFIG_VER_ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '版本ID',
  `APP_ENV_ID` bigint(11) unsigned DEFAULT NULL COMMENT '应用ID',
  `VERSION_NAME` varchar(50) DEFAULT NULL COMMENT '版本名称',
  `VERSION_CODE` varchar(50) NOT NULL COMMENT '版本编号',
  `COMMIT_ID` varchar(255) DEFAULT NULL COMMENT '提交记录ID',
  `STATUS` int(11) NOT NULL DEFAULT '0' COMMENT '版本状态 0为可用，1为不可用',
  `DESCRIPTION` varchar(1024) DEFAULT NULL COMMENT '描述新',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `OPERATE_USER_ID` bigint(11) unsigned DEFAULT NULL COMMENT '操作员',
  `TAG_URL` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '距上次版本文件变更记录',
  `USED` int(11) DEFAULT NULL,
  PRIMARY KEY (`CONFIG_VER_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=339 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='配置版本';

-- ----------------------------
-- Records of OPS_VERSION_CONFIG
-- ----------------------------
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('302', '283', null, 'V_20180825_00', null, '0', '拉取制定配置版本\n8/25 14:13', '2018-08-25 14:13:20', '2018-08-25 14:14:55', '1', null, '1');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('303', '274', null, 'V_20180825_00', null, '0', '14:22', '2018-08-25 14:23:00', '2018-08-25 14:25:20', '1', null, '1');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('304', '288', null, 'V_20180825_00', null, '0', '17:29', '2018-08-25 17:30:36', '2018-08-25 17:31:41', '1', null, '1');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('305', '274', null, 'V_20180828_00', null, '0', '制定配置版本\n8/28 16:11', '2018-08-28 16:12:44', '2018-08-28 16:21:22', '1', 'V_20180825_00', '1');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('306', '283', null, 'V_20180828_00', null, '0', '8/28 16:16\n制定彩票pay配置版本', '2018-08-28 16:17:06', '2018-08-28 16:20:56', '1', 'V_20180825_00', '1');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('307', '274', null, 'V_20180828_01', null, '0', '8/28 19:53\n制定配置版本\n删除配置文件\n修改配置文件', '2018-08-28 19:54:32', '2018-08-28 19:56:19', '1', 'V_20180828_00', '1');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('308', '293', null, 'V_20180829_00', null, '0', '测试', '2018-08-29 09:46:37', '2018-08-29 09:46:37', '1', null, '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('309', '293', null, 'V_20180829_01', null, '0', '测试', '2018-08-29 09:48:21', '2018-08-29 09:48:21', '1', 'V_20180829_00', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('310', '293', null, 'V_20180829_02', null, '0', '11', '2018-08-29 09:49:22', '2018-08-29 09:49:22', '1', 'V_20180829_01', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('311', '293', null, 'V_20180829_03', null, '0', '删除测试增加的文件', '2018-08-29 09:52:40', '2018-08-29 09:52:40', '1', 'V_20180829_02', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('312', '274', null, 'V_20180830_00', null, '0', '8/30\n./<16:56>', '2018-08-30 16:56:43', '2018-08-30 20:19:07', '1', 'V_20180828_01', '1');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('313', '274', null, 'V_20180830_01', null, '0', '19:00', '2018-08-30 19:01:14', '2018-08-30 19:38:03', '1', 'V_20180830_00', '1');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('314', '288', null, 'V_20180830_00', null, '0', '8/30 20:03', '2018-08-30 20:05:20', '2018-08-30 20:06:54', '1', 'V_20180825_00', '1');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('315', '288', null, 'V_20180831_00', null, '0', '修改配置文件\n1. 修改配置文件，与上次修改文档不同\n2. 修改配置文件，与上次修改文档相同，当修改地方不一样\n# 2018-08-30 14:52', '2018-08-31 14:53:02', '2018-08-31 14:53:02', '1', 'V_20180830_00', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('316', '289', null, 'V_20180831_00', null, '0', '修改配置文档\n1. 增加配置文档\n2. 编辑配置文档\n2018-08-31 14:58', '2018-08-31 14:58:27', '2018-08-31 14:58:27', '1', null, '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('317', '289', null, 'V_20180831_01', null, '0', '修改配置文档\n2018-08-31 15:13', '2018-08-31 15:23:06', '2018-08-31 15:23:06', '1', 'V_20180831_00', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('318', '289', null, 'V_20180831_02', null, '0', '08-31 15:31', '2018-08-31 15:30:02', '2018-08-31 15:30:02', '1', 'V_20180831_01', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('319', '289', null, 'V_20180831_03', null, '0', '08-31 15:32', '2018-08-31 15:32:33', '2018-08-31 15:32:33', '1', 'V_20180831_02', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('320', '289', null, 'V_20180831_04', null, '0', '08-31 15:31 ', '2018-08-31 15:32:35', '2018-08-31 15:32:35', '1', 'V_20180831_03', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('321', '290', null, 'V_20180831_00', null, '0', '修改配置文档，分支号：1154215 0831-15:43', '2018-08-31 15:43:45', '2018-08-31 15:43:45', '1', null, '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('322', '290', null, 'V_20180831_01', null, '0', '08-31 15:42', '2018-08-31 15:43:47', '2018-08-31 15:43:47', '1', 'V_20180831_00', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('323', '290', null, 'V_20180831_02', null, '0', '修改配置文件 08-31 15:47 分支号：154611\n新增文档\n修改文档', '2018-08-31 15:49:31', '2018-08-31 15:49:31', '1', 'V_20180831_01', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('324', '290', null, 'V_20180831_03', null, '0', '修改配置文件，分支号154448 08-31 15:45', '2018-08-31 15:49:33', '2018-08-31 15:49:33', '1', 'V_20180831_02', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('325', '289', null, 'V_20180831_05', null, '0', '修改配置文件\n1.修改配置文件，add_file-003 修改的地方与上个分支不相同\n分支号：193046\n08-31 19:32', '2018-08-31 19:37:44', '2018-08-31 19:37:44', '1', 'V_20180831_04', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('326', '290', null, 'V_20180903_00', null, '0', '09-03 14:47 修改文档一次', '2018-09-03 14:48:21', '2018-09-03 14:48:21', '1', 'V_20180831_03', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('327', '290', null, 'V_20180903_01', null, '0', '09-03 14:49 修改文档 第二次', '2018-09-03 14:51:16', '2018-09-03 14:51:16', '1', 'V_20180903_00', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('328', '290', null, 'V_20180903_02', null, '0', '09-03 14:50 修改文档 第二次 2', '2018-09-03 14:51:18', '2018-09-03 14:51:18', '1', 'V_20180903_01', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('329', '290', null, 'V_20180903_03', null, '0', '09-03 14:56 修改文档 第3次 2', '2018-09-03 15:06:20', '2018-09-03 15:06:20', '1', 'V_20180903_02', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('330', '290', null, 'V_20180903_04', null, '0', '09-03 14:56 修改文档 第3次 1', '2018-09-03 15:07:26', '2018-09-03 15:07:26', '1', 'V_20180903_03', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('331', '290', null, 'V_20180903_05', null, '0', '09-03 14:56 修改文档 第4次 2', '2018-09-03 15:43:22', '2018-09-03 15:43:22', '1', 'V_20180903_04', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('332', '290', null, 'V_20180903_06', null, '0', '09-03 14:56 修改文档 第4次 1', '2018-09-03 15:43:26', '2018-09-03 15:43:26', '1', 'V_20180903_05', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('333', '290', null, 'V_20180903_07', null, '0', '09-03 16:33 修改文档 第5次 2', '2018-09-03 16:34:43', '2018-09-03 16:34:43', '1', 'V_20180903_06', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('334', '290', null, 'V_20180903_08', null, '0', '09-03 16:36 修改文档 第5次 3', '2018-09-03 16:37:01', '2018-09-03 16:37:01', '1', 'V_20180903_07', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('335', '290', null, 'V_20180903_09', null, '0', '09-03 16:43 修改文档 第6次 33', '2018-09-03 16:44:19', '2018-09-03 16:44:19', '1', 'V_20180903_08', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('336', '290', null, 'V_20180903_10', null, '0', '09-03 16:50 修改文档 第7次 2', '2018-09-03 16:50:13', '2018-09-03 16:50:13', '1', 'V_20180903_09', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('337', '290', null, 'V_20180903_11', null, '0', '09-03 16:49 修改文档 第7次 1', '2018-09-03 16:50:18', '2018-09-03 16:50:18', '1', 'V_20180903_10', '0');
INSERT INTO `OPS_VERSION_CONFIG` VALUES ('338', '272', null, 'V_20180904_00', null, '0', '生成配置版本', '2018-09-04 10:35:49', '2018-09-04 10:36:45', '1', null, '1');
