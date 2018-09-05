export const actions = {
  // 首页
  getServerUseInfo({ state }) {
    return this.$axios.$get(`/assets/search/use/status`)
  },
  getUserCountInfo({ state }) {
    return this.$axios.$get('/user/count/info')
  },
  getDeployAppInfo({ state }) {
    return this.$axios.$get('/order/publish/search/status')
  },
  getDashboardDate({ satet }, {envId, proId}) {
    return this.$axios.$get(`/pro/select/dashboard/${envId}/${proId}`)
  },

  // 获取应用发布统计(包括发布成功率)
  getAppReleaseInfo({ state }, envId) {
    return this.$axios.$get(`/order/publish/release/info/${envId}`)
  },
  /* 应用环境管理 */
  // 应用环境主页面
  // 查询所有环境
  searchEnvs({ state }) {
    return this.$axios.$get('/env/query/all')
  },
  // 查询所有项目
  searchPros({ state }) {
    return this.$axios.$get('/pro/query/all')
  },
  // 通过项目查询应用
  searchAppsByPro({ state }, proId) {
    return this.$axios.$get(`/app/query/pro/${proId}`)
  },
  // 查询应用环境
  searchAppEnvs({ state }, { envId, proId, appName, pageSize, pageNum }) {
    return this.$axios.$get(`/appenv/search/appenvs/${pageNum}/${pageSize}/${proId}/${envId}/${appName}`)
  },
  searchAppInstance({ state }, { envId, proId, appName, pageSize, pageNum }) {
    return this.$axios.$get(`/app/instance/search/${pageNum}/${pageSize}/${proId}/${envId}/${appName}`)
  },
  searchAppInstanceLogs({ state }, { appInstanceId, appInstanceDetailId }) {
    return this.$axios.$post(`/appenv/query/app/logs`, { appInstanceId, appInstanceDetailId })
  },
  // 详情页面
  // 根据应用环境ID查询应用环境
  getAppEnv({ state }, appEnvId) {
    return this.$axios.$get(`/appenv/query/byid/${appEnvId}`)
  },
  // 应用编辑 查询可选的微服务Type
  queryAllMicroServiceTypes() {
    return this.$axios.$get(`/appenv/query/ms/types`)
  },
  // 修改应用环境
  updateAppEnv({ state }, appEnvInfo) {
    return this.$axios.$post('/appenv/update', appEnvInfo)
  },
  // 查询可用服务器
  searchUsableServers({ state }) {
    return this.$axios.$get('/assets/query/all')
  },
  // 按类型查询可用服务器
  searchUsableServersByType({ state }, type) {
    return this.$axios.$get(`/assets/query/bytype/${type}`)
  },
  // 查询应用环境配置的服务器
  getAppEnvServer({ state }, appEnvId) {
    return this.$axios.$get(`/assets/query/servers/${appEnvId}`)
  },
  // 应用环境配置服务器
  setServer({ state }, configInfo) {
    return this.$axios.$post('/assets/set/appenv', configInfo)
  },
  restartApp({ state }, { appEnvId, serverIds, deployType }) {
    return this.$axios.$post(`/appenv/restart`, { appEnvId, serverIds, deployType })
  },
  stopApp({ state }, { appEnvId, serverIds, deployType }) {
    return this.$axios.$post(`/appenv/stop`, { appEnvId, serverIds, deployType })
  },
  getAppEnvEnevnt({ state }, { appId, envId }) {
    return this.$axios.$get(`/appenv/query/envent/${appId}/${envId}`)
  },
  /* 服务器管理 */
  // 服务器主页面
  searchServers({ state }, { keyWords, pageSize, pageNum }) {
    return this.$axios.$get(`/assets/query/${pageNum}/${pageSize}/${keyWords}`)
  },
  getServerStatus({ state }, { proId, envId }) {
    return this.$axios.$get(`/assets/status/search/${proId}/${envId}`)
  },
  // 增加服务器
  addServer({ state }, serverInfo) {
    return this.$axios.$post('/assets/add', serverInfo)
  },
  // 测试连接服务器
  verifyServer({ state }, serverInfo) {
    return this.$axios.$post('/assets/verify', serverInfo)
  },
  // 修改服务器信息
  updateServer({ state }, serverInfo) {
    return this.$axios.$post('/assets/update', serverInfo)
  },
  // 根据服务器ID查询服务器
  getServer({ state }, hostId) {
    return this.$axios.$get(`/assets/select/byid/${hostId}`)
  },
  // 删除服务器
  deleteServer({ state }, assetsId) {
    return this.$axios.$delete(`/assets/delete/${assetsId}`)
  },
  searchUninitializedServers({ state }, filterInfo) {
    return this.$axios.$post('/assets/query/uninitialized', filterInfo)
  },
  doInitialServers({ state }, hostIds) {
    return this.$axios.$post(`/assets/init`, hostIds)
  },
  assetsExecute({ state }, executeRequest) {
    return this.$axios.$post(`/assets/execute`, executeRequest)
  },
  assetsGetResult({ state }, { key }) {
    return this.$axios.$get(`/assets/getResult/${key}`)
  },
  // 取消获取结果
  cancelResult({ state }, { key }) {
    return this.$axios.$get(`/assets/cancel/${key}`)
  },
  queryExecuteTemplates({ state }, executeTemplate) {
    return this.$axios.$post('/temp/query', executeTemplate)
  },
  addExecuteTemplate({ state }, executeTemplate) {
    return this.$axios.$post('/temp/add', executeTemplate)
  },
  updateExecuteTemplate({ state }, executeTemplate) {
    return this.$axios.$post('/temp/update', executeTemplate)
  },
  delExecuteTemplate({ state }, { tplId }) {
    return this.$axios.$get(`/temp/delete/${tplId}`)
  },
  queryAllExecHis({ state }, selectExecHis) {
    return this.$axios.$post('/exec/his/query', selectExecHis)
  },

  /* 任务管理 */
  // 任务主页面
  // 查询所有任务
  searchJobs({ state }, { keyWords, pageSize, pageNum }) {
    return this.$axios.$get(`/job/query/${pageNum}/${pageSize}/${keyWords}`)
  },
  // 增加任务
  addJob({ state }, jobInfo) {
    return this.$axios.$post('/job/add', jobInfo)
  },
  // 删除任务
  deleteJob({ state }, jobId) {
    return this.$axios.$delete(`/job/delete/${jobId}`)
  },
  // 根据任务ID查询任务
  getJob({ state }, jobId) {
    return this.$axios.$get(`/job/query/byid/${jobId}`)
  },
  // 修改任务
  updateJob({ state }, jobInfo) {
    return this.$axios.$post('/job/update', jobInfo)
  },

  /* 项目管理 */
  // 项目主页面
  // 按条件查询所有项目
  searchProjects({ state }, { keyWords, pageSize, pageNum }) {
    return this.$axios.$get(`/pro/query/${pageNum}/${pageSize}${keyWords ? ('/' + keyWords) : ''}`)
  },
  // 增加项目
  addProject({ state }, projectInfo) {
    return this.$axios.$post('/pro/add', projectInfo)
  },
  // 根据项目ID查询项目
  getProject({ state }, proId) {
    return this.$axios.$get(`/pro/query/byid/${proId}`)
  },
  // 修改项目
  updateProject({ state }, projectInfo) {
    return this.$axios.$post('/pro/update', projectInfo)
  },
  // 新增应用
  addApp({ state }, appInfo) {
    return this.$axios.$post('/app/add', appInfo)
  },
  searchAppTypes({ state }) {
    return this.$axios.$get('app/query/apptypes')
  },
  // 删除项目
  deleteProject({ state }, proId) {
    return this.$axios.$delete(`/pro/delete/${proId}`)
  },

  /* 应用管理 */
  // 应用主页面
  // 查询所有应用
  searchApps({ state }, { keyWords, pageSize, pageNum }) {
    return this.$axios.$get(`/app/query/${pageNum}/${pageSize}${keyWords ? ('/' + keyWords) : ''}`)
  },
  // 通过项目ID查询应用(带分页)
  searchAppsByProPage({ state }, { keyWord, proId, pageSize, pageNum }) {
    return this.$axios.$get(`/app/query/bypro/${pageNum}/${pageSize}/${proId}${keyWord ? ('/' + keyWord) : ''}`)
  },
  // 修改应用
  updateApp({ state }, appInfo) {
    return this.$axios.$post('/app/update', appInfo)
  },
  // 通过应用ID查询应用
  getApp({ state }, appId) {
    return this.$axios.$get(`/app/query/byid/${appId}`)
  },
  // 删除应用
  deleteApp({ state }, appId) {
    return this.$axios.$delete(`/app/delete/${appId}`)
  },

  /* 环境管理 */
  // 环境主页面
  // 查询所有环境(带分页)
  searchEnvsByPage({ state }, { keyWords, pageSize, pageNum }) {
    return this.$axios.$get(`/env/query/${pageNum}/${pageSize}/${keyWords}`)
  },
  // 通过环境ID查询应用环境中的项目
  searchProsByAppEnv({ state }, envId) {
    return this.$axios.$get(`/pro/query/appenv/${envId}`)
  },
  // 通过项目ID查询应用环境中的应用
  searchAppsByAppEnv({ state }, { envId, proId }) {
    return this.$axios.$get(`/app/query/appenv/${proId}/${envId}`)
  },
  // 添加环境
  addEnv({ state }, envInfo) {
    return this.$axios.$post('/environ/add', envInfo)
  },
  // 根据环境ID查询环境
  getEnv({ state }, envId) {
    return this.$axios.$get(`/env/query/byid/${envId}`)
  },
  // 修改环境
  updateEnv({ state }, envInfo) {
    return this.$axios.$post('/environ/update', envInfo)
  },
  // 删除环境
  deleteEnv({ state }, envId) {
    return this.$axios.$delete(`/env/delete/${envId}`)
  },
  // 查询未配置的应用
  serachNosetApp({ state }, { proId, envId }) {
    return this.$axios.$get(`/app/query/noset/${proId}/${envId}`)
  },
  // 给环境配置应用
  setApp({ state }, configInfo) {
    return this.$axios.$post('/environ/setapp', configInfo)
  },
  queryDeployEnvs({ state }) {
    return this.$axios.$get(`/environ/query/all`)
  },
  queryDeployApps({ state }, { proId, envId }) {
    return this.$axios.$get(`/app/query/appenv/${proId}/${envId}`)
  },
  queryAppBranches({ state }, { appId, proId, envId }) {
    return this.$axios.$get(`/deployconfig_version/query/app/branches/${proId}/${envId}/${appId}`)
  },
  checkNewBranch({ state }, { appId, proId, envId }) {
    return this.$axios.$get(`/deployconfig_version/checkout/app/branch/${proId}/${envId}/${appId}`)
  },
  checkMaster({ state }, { appId, proId, envId, branchName }) {
    return this.$axios.$get(`/deployconfig_version/checkout/app/master/${proId}/${envId}/${appId}/${branchName}`)
  },
  queryBranchFiles({ state }, { appId, proId, envId, branchName }) {
    return this.$axios.$get(`/deployconfig_version/query/branch/files/${proId}/${envId}/${appId}/${branchName}`)
  },
  getFileContent({ state }, { appId, proId, envId, branchName, filePath }) {
    return this.$axios.$post(`/deployconfig_version/file/content`, { appId, proId, envId, branchName, filePath })
  },
  dropBranch({ state }, { appId, proId, envId, branchName }) {
    return this.$axios.$get(`/deployconfig_version/drop/branch/${proId}/${envId}/${appId}/${branchName}`)
  },
  getOriginFileContent({ state }, { appId, proId, envId, filePath, branchName }) {
    return this.$axios.$post(`/deployconfig_version/file/content/origin`, { appId, proId, envId, filePath, branchName })
  },
  addFile({ state }, { appId, proId, envId, filePath, branchName, fileContent }) {
    return this.$axios.$post(`/deployconfig_version/file/add`, { appId, proId, envId, filePath, branchName, fileContent })
  },
  saveFileContent({ state }, { appId, proId, envId, filePath, branchName, fileContent }) {
    return this.$axios.$post(`/deployconfig_version/file/content/save`, { appId, proId, envId, filePath, branchName, fileContent })
  },
  deleteFile({ state }, { appId, proId, envId, filePath, branchName }) {
    return this.$axios.$post(`/deployconfig_version/file/delete`, { appId, proId, envId, filePath, branchName })
  },
  showStagedDiff({ state }, { appId, proId, envId, branchName }) {
    return this.$axios.$get(`/deployconfig_version/show/staged/diff/${proId}/${envId}/${appId}/${branchName}`)
  },
  pushBranch({ state }, branchInfo) {
    return this.$axios.$post('/deployconfig_version/push/branch', branchInfo)
  },
  /* ********************************************配置文件修改记录*********************************************** */
  queryConfigRecords({ state }, { proId, envId, appId, pageNum, pageSize }) {
    return this.$axios.$get(`/config/record/search/commit/${pageNum}/${pageSize}/${proId}/${envId}/${appId || ''}`)
  },
  auditConfigRecord({ state }, { recordId, status }) {
    return this.$axios.$post(`/config/record/commit/audit`, { recordId, status })
  },
  queryConfigRecordByUser({ state }, { proId, envId, appId, pageNum, pageSize }) {
    return this.$axios.$get(`/config/record/search/user/${pageNum}/${pageSize}/${proId}/${envId}/${appId || ''}`)
  },
  queryAppsByProject({ state }, proId) {
    return this.$axios.$get(`/app/query/pro/${proId}`)
  },
  queryDiffList({ state }, recordId) {
    return this.$axios.$get(`/config/record/query/diffs/${recordId}`)
  },
  withdrawnConfig({ state }, recordId) {
    return this.$axios.$post(`/config/record/commit/withdrawn`, { recordId })
  },

  // 应用管理审核  TODO
  auditAppConfigRecord({ state }, { recordId, status }) {
    return this.$axios.$post(`/appconfig/record/commit/audit`, { recordId, status })
  },

  // 查询需要审核的应用,即状态为已经提交的应用
  queryCommitAppConfigRecords({ state }, { proId, appId, pageNum, pageSize }) {
    return this.$axios.$get(`/appconfig/record/search/commit/${pageNum}/${pageSize}/${proId}/${appId || ''}`)
  },

  // 查询应用审核记录 TODO
  queryAppConfigRecordByUser({ state }, { proId, appId, pageNum, pageSize }) {
    return this.$axios.$get(`/appconfig/record/search/user/${pageNum}/${pageSize}/${proId}/${appId || ''}`)
  },

  // 查看变动
  queryAppDiffList({ state }, recordId) {
    return this.$axios.$get(`/appconfig/record/query/diffs/${recordId}`)
  },
  /* ******************************************应用版本************************************************** */
  searchAppVersions({ state }, { pageNum, pageSize, proId, envId, appName }) {
    return this.$axios.$get(`/app_version/search/${pageNum}/${pageSize}/${proId}/${envId}${appName ? ('/' + appName) : ''}`)
  },
  saveAppVersion({ state }, appVerionInfo) {
    return this.$axios.$post('/app_version/save', appVerionInfo)
  },
  queryAppEnvs({ state }, { proId }) {
    return this.$axios.$get(`/appenv/query/${proId}`)
  },
  syncAppVersion({ state }, form) {
    return this.$axios.$post('app_version/update', form)
  },
  searchAppSyncRecord({ state }, { pageNum, pageSize, proId, appName }) {
    return this.$axios.$get(`/app_version/select_sync/${pageNum}/${pageSize}/${proId}${appName ? ('/' + appName) : ''}`)
  },
  queryModifiedFiles({ state }, syncId) {
    return this.$axios.$get(`/app_version/select_sync_modify/${syncId}`)
  },
  queryEnvs({ state }, priority) {
    return this.$axios.$get(`/environ/query/low/${priority}`)
  },
  queryAppVersionsByPro({ state }, proId) {
    return this.$axios.$get(`/app/query_appver_app/${proId}`)
  },
  queryAppVersionCodes({ state }, { proId, appId }) {
    return this.$axios.$post('/app_version/query_appver_code', { proId, appId })
  },
  saveConfigVersion({ state }, configVersion) {
    return this.$axios.$post('/config_version/create', configVersion)
  },
  searchConfigVersion({ state }, { pageNum, pageSize, proId, envId, appName }) {
    return this.$axios.$get(`/config_version/search/${pageNum}/${pageSize}/${proId}/${envId}${appName ? ('/' + appName) : ''}`)
  },
  /* ******************************************发布版本************************************************** */
  searchReleaseVersion({ state }, { pageNum, pageSize, proId, envId, appName }) {
    return this.$axios.$get(`/release/search/${pageNum}/${pageSize}/${proId}/${envId}${appName ? ('/' + appName) : ''}`)
  },
  searchAppVersionByAppVerId({ state }, appVerId) {
    return this.$axios.$get(`/app_version/query/appver/${appVerId}`)
  },
  searchConfigVersionByAppVerId({ state }, configVerId) {
    return this.$axios.$get(`/config_version/query/configver/${configVerId}`)
  },
  getAppVersionCode({ state }, { appId, proId }) {
    return this.$axios.$get(`/app_version/search/byid/${proId}/${appId}`)
  },
  getConfigVersionCode({ state }, { appId, envId, proId }) {
    return this.$axios.$get(`/config_version/search/byid/${proId}/${envId}/${appId}`)
  },
  saveReleaseVersion({ state }, ReleaseVersion) {
    return this.$axios.$post('/release/create', ReleaseVersion)
  },
  queryPublishEnactApps({ state }, { proId, envId }) {
    return this.$axios.$get(`/release/apps/wait/${proId}/${envId}`)
  },
  queryMakingVersion({ state }, { proId, envId }) {
    return this.$axios.$get(`release/query/${proId}/${envId}`)
  },
  confirmInfo({ state }, appDepVerHisId) {
    return this.$axios.$get(`/release/change/${appDepVerHisId}`)
  },
  /* ****************************************************************************************************** */
  searchPublishHistory({ state }, { pageNum, pageSize, proId, envId, appName, optionStatus }) {
    return this.$axios.$get(`/publish/history/search/${pageNum}/${pageSize}/${proId}/${envId}/${optionStatus}${appName ? ('/' + appName) : ''}`)
  },
  queryPublishApps({ state }, { proId, envId }) {
    return this.$axios.$get(`/appenv/publish/apps/wait/${proId}/${envId}`)
  },
  queryPublishAppsByCon({ state }, { proId, envId }) {
    return this.$axios.$get(`/appenv/publish/apps/waits/${proId}/${envId}`)
  },
  queryPublishingApps({ state }, { proId, envId }) {
    return this.$axios.$get(`/appenv/publish/apps/${proId}/${envId}`)
  },
  queryPhyPublishingApps({ state }, { proId, envId }) {
    return this.$axios.$get(`/appenv/publish/apps/phy/${proId}/${envId}`)
  },
  queryConPublishingApps({ state }, { proId, envId }) {
    return this.$axios.$get(`/appenv/publish/apps/con/${proId}/${envId}`)
  },
  savePublishApp({ state }, app) {
    return this.$axios.$post(`/appenv/publish/save`, app)
  },
  rePublishApp({ state }, publishId) {
    return this.$axios.$post('/appenv/publish/republish', publishId)
  },
  publishOver({ state }, publishId) {
    return this.$axios.$post(`/appenv/publish/archive`, { publishId })
  },
  publishCancel({ state }, publishId) {
    return this.$axios.$post(`/appenv/publish/cancel`, { publishId })
  },
  searchAdminUsers({ state }, { keyWords, pageSize, pageNum }) {
    return this.$axios.$get(`/user/search/${pageNum}/${pageSize}/${keyWords}`)
  },
  getAdminUser({ state }, adminUserId) {
    return this.$axios.$get(`/user/get/${adminUserId}`)
  },
  addAdminUser({ state }, userInfo) {
    return this.$axios.$post('/user/add', userInfo)
  },
  updateAdminUser({ state }, userInfo) {
    return this.$axios.$post('/user/update', userInfo)
  },
  deleteAdminUser({ state }, adminUserId) {
    return this.$axios.$delete(`/user/delete/${adminUserId}`)
  },
  getAdminUserRole({ state }, adminUserId) {
    return this.$axios.$get(`/user/query/role/${adminUserId}`)
  },
  saveAdminUserRole({ state }, { userId, roleId }) {
    return this.$axios.$post('/user/save/role', { userId, roleId })
  },
  // 角色管理
  searchRoles({ state }) {
    return this.$axios.$get(`/role/all`)
  },
  searchRoleList({ state }, { pageSize, pageNum, roleName }) {
    return this.$axios.$get(`/role/list/${pageNum}/${pageSize}/${roleName}`)
  },
  addRole({ state }, roleInfo) {
    return this.$axios.$post('/role/addRole', roleInfo)
  },
  deleteRole({ state }, roleId) {
    return this.$axios.$delete(`/role/delete/${roleId}`)
  },
  updateRole({ state }, roleInfo) {
    return this.$axios.$post('/role/update', roleInfo)
  },
  addRoleResource({ state }, roleResource) {
    return this.$axios.$post('/role/update/resource', roleResource)
  },
  // 资源管理
  searchRoleResource({ state }, roleId) {
    // return this.$axios.$get(`/sys/resource/get_role_resource/${roleId}`)
    return this.$axios.$get(`/sys/resource/get/role/resource/${roleId}`)
  },
  searchResourceTree({ state }) {
    return this.$axios.$get('/sys/resource/get/tree')
  },
  addResource({ state }, resourceInfo) {
    return this.$axios.$post('/sys/resource/add/resource', resourceInfo)
  },
  editResource({ state }, resourceInfo) {
    return this.$axios.$post('/sys/resource/edit/resource', resourceInfo)
  },
  deleteResource({ state }, resourceInfo) {
    return this.$axios.$post('/sys/resource/delete/resource', resourceInfo)
  },
  deleteResourceById({ state }, resourceId) {
    return this.$axios.$post('/sys/resource/delete/resource', resourceId)
  },
  /* ******************************************系统参数************************************************** */
  searchAllOpsSysParameters() {
    return this.$axios.$get('/sys/param/search')
  },
  saveOpsSysParameter({ state }, sysParameter) {
    return this.$axios.$post('/sys/param/save', sysParameter)
  },
  updateOpsSysParameter({ state }, sysParameter) {
    return this.$axios.$post('/sys/param/update', sysParameter)
  },
  /* ******************************************应用实例************************************************** */
  queryAppInstance({ state }, { pageSize, pageNum, proId, envId, appName }) {
    return this.$axios.$get(`/app/instance/search/${pageNum}/${pageSize}/${proId}/${envId}${appName ? ('/' + appName) : ''}`)
  },
  /* ******************************************发布工单************************************************** */
  queryPublishOrders({ state }, { pageNum, pageSize, proId, envId, title }) {
    return this.$axios.$get(`/order/publish/search/orders/${pageNum}/${pageSize}/${proId}/${envId}${title ? ('/' + title) : ''}`)
  },
  getPublishOrderInfo({ state }, orderId) {
    return this.$axios.$get(`/order/publish/search/${orderId}`)
  },
  getPublishOrderDetail({ state }, orderDetailId) {
    return this.$axios.$get(`/order/publish/search/orderinfo/${orderDetailId}`)
  },
  queryApps({ state }, { proId, envId }) {
    return this.$axios.$get(`/order/publish/search/apps/${proId}/${envId}`)
  },
  saveOrderInfo({ state }, orderInfo) {
    return this.$axios.$post(`/order/publish/insert`, orderInfo)
  },
  dropPublishOrder({ state }, orderId) {
    return this.$axios.$post(`/order/publish/delete/${orderId}`)
  },
  getDepAppStatus({ state }, { proId, envId, time }) {
    return this.$axios.$get(`/order/publish/app/status/${time}/${proId}/${envId}`)
  },
  // 自动发布
  autoPublish({ state }, orderInfo) {
    return this.$axios.$post(`/order/publish/save`, orderInfo)
  },

  // 部署代码
  deployCode({ state }, orderInfo) {
    return this.$axios.$post(`/order/publish/deploy/code`, orderInfo)
  },

  // 重启服务
  restartService({ state }, orderInfo) {
    return this.$axios.$post(`/order/publish/restart/service`, orderInfo)
  },

  /* ******************************************日志管理************************************************** */
  selectSysLogType() {
    return this.$axios.$get('sys/log/search/type')
  },
  changeLogEnable({ state }, logType) {
    return this.$axios.$post('sys/log/change/type', logType)
  },
  selectSysLogRecord({ state }, { pageSize, pageNum, typeId, searchName, startTime, endTime }) {
    return this.$axios.$get(`sys/log/search/record/${pageNum}/${pageSize}/${typeId}/${searchName}/${startTime}/${endTime}`)
  },

  /* ******************************************金山云服KEC********************************************** */
  searchKsyunKecInstance({ state }, { pageSize, pageNum, region, keywords }) { // 查询实例
    return this.$axios.$get(`ksyun/kec/search/instance/${pageNum}/${pageSize}/${region}${keywords ? ('/' + keywords) : ''}`)
  },
  searchKsyunKecInstanceFamilys({ state }, region) { // 查询机型配置信息
    return this.$axios.$post(`ksyun/kec/search/instance/familys`, { region })
  },
  runKsyunKesInstance({ state }, { region, imageId, chargeType, purchaseTime, securityGroupId, maxCount, minCount, subnetId, instanceType, dataDiskGb, ebsDataDisks, instancePassword, keepImageLogin, privateIpAddress, instanceName, instanceNameSuffix, sriovNetSupport, projectId }) { // 创建实例
    return this.$axios.$post(`ksyun/kec/run/instance`, { region, imageId, chargeType, purchaseTime, securityGroupId, maxCount, minCount, subnetId, instanceType, dataDiskGb, ebsDataDisks, instancePassword, keepImageLogin, privateIpAddress, instanceName, instanceNameSuffix, sriovNetSupport, projectId })
  },
  startKsyunKecInstance({ state }, instanceIds) { // 开启实例
    return this.$axios.$post(`ksyun/kec/start/instance`, { instanceIds })
  },
  stopKsyunKecInstance({ state }, { instanceIds, forceStop }) { // 关闭实例
    return this.$axios.$post(`ksyun/kec/stop/instance`, { instanceIds, forceStop })
  },
  rebootKsyunKecInstance({ state }, instanceIds) { // 重启实例
    return this.$axios.$post(`ksyun/kec/reboot/instance`, { instanceIds })
  },
  modifyKsyunKecInstanceImage({ state }, { instanceIds, imageId, instancePassword, keepImageLogin }) { // 重装系统
    return this.$axios.$post(`ksyun/kec/modify/instance/image`, { instanceIds, imageId, instancePassword, keepImageLogin })
  },
  searchKsyunKecImagesByType({ state }, imageType) { // 查询镜像
    return this.$axios.$get(`ksyun/kec/search/image${imageType ? ('/' + imageType) : ''}`)
  },
  searchKsyunKecInstanceTypeConfigs({ state }) { // 查询机型套餐配置信息
    return this.$axios.$get(`ksyun/kec/search/instance/type/config`)
  },
  searchKsyunKecRegions({ state }) { // 查询地域列表
    return this.$axios.$get(`ksyun/kec/search/regions`)
  },
  searchKsyunKecAvailabilityZones({ state }, region) { // 查询可用区列表
    return this.$axios.$post(`ksyun/kec/search/available/zones`, { region })
  },
  searchKsyunVpcs({ state }, region) { // 查询vpc列表
    return this.$axios.$post(`ksyun/kec/search/vpcs`, { region })
  },
  searchKsyunSubnetByVpcId({ state }, vpcId) { // 通过vpcId查询子网列表
    return this.$axios.$get(`ksyun/kec/search/subnets/${vpcId}`)
  },
  searchKsyunSubnetAvailableAddresses({ state }, subnetId) { // 通过子网ID查询可用IP
    return this.$axios.$get(`ksyun/kec/search/subnets/ips/${subnetId}`)
  },
  searchKsyunSecurityGroupsByVpcId({ state }, vpcId) { // 通过vpcId查询子网列表
    return this.$axios.$get(`ksyun/kec/search/security/groups/${vpcId}`)
  },
  searchKsyunAllProjectList({ state }) { // 查询账号下所有项目
    return this.$axios.$get(`ksyun/kec/search/projects`)
  },
  /* **********************************通用接口******************************************** */
  downloadAppLog({ state }, logPath) {
    return this.$axios.$get(`/download/app/log/${logPath}`)
  }
}
