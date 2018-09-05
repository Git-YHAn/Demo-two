<template>
  <el-tabs v-model="tabValue" @tab-click="tabClick">
    <el-tab-pane label="批量执行" name="batchExecutePane" type="info">
      <portlet :showPageBar="false" :showTitle="false">
        <template slot="search-bar">
          <div class="base-class">
            <span>执行主机: </span>
            <span style="margin-left: 20px;">{{selectAssetsIds.length}} 台</span>
          </div>
          <div class="base-class">
            <el-input v-model="selectAssetsSshAddr" type="textarea" placeholder="目标主机IP,多个用逗号分隔" disabled></el-input>
          </div>
          <div class="base-class">
            <el-button type="primary" @click="selectHostEvent()">从服务器列表中选择+</el-button>
            <el-button type="primary" @click="selectTplHandler()">从执行模板中选择+</el-button>
            <el-button @click="verifyBeforeExecute()" type="primary">开始执行</el-button>
            <el-button @click="clearTemplate()" v-if="inputCode.length > 0" type="warning" style="float: right;">清空控制台</el-button>
          </div>
          <el-row v-if="tplSaveBtnVisible" style="float: right;">
            <el-button size="mini" style="margin-right: 10px;" @click="revertCurrentTpl">恢复模板</el-button>
            <el-button size="mini" type="primary" @click="updateCurrentTpl">更新模板</el-button>
          </el-row>
        </template>
        <template class="codewrapper file-content">
          <no-ssr placeholder="Loading...">
            <codemirror v-model="inputCode" :autofocus="true"></codemirror>
          </no-ssr>
        </template>
      </portlet>
    </el-tab-pane>
    <el-tab-pane label="执行模板" name="executeTplPane" type="info">
      <execute-tpl-component ref="tpl" :handler="handler" @reset="resetHandler"></execute-tpl-component>
    </el-tab-pane>
    <el-tab-pane label="执行历史" name="executeHisPane" type="info">
      <execute-his-component :handler="handler" @reset="resetHandler"></execute-his-component>
    </el-tab-pane>
    <result-component :handler="handler" :execResultMap="execResultMap" @reset="resetHandler"></result-component>
    <select-tpl-dialog ref="selectTpl" :handler="handler" :currentTpl="selectTemplate" @reset="resetHandler" @fromSelectTpl="setTemplates"></select-tpl-dialog>
    <select-host-dialog :handler="handler" @reset="resetHandler" @setAssetsHosts="setAssetsHosts"></select-host-dialog>
  </el-tabs>
</template>
<script type='text/javascript'>
  import {mapActions} from 'vuex'
  import executeTplComponent from './exec/template'
  import executeHisComponent from './exec/history'
  import resultComponent from './exec/result'
  import selectTplDialog from './exec/selectTpl'
  import selectHostDialog from './exec/selectHost'

  export default {
    components: {
      executeTplComponent,
      executeHisComponent,
      resultComponent,
      selectTplDialog,
      selectHostDialog
    },
    props: {
      handlers: String
    },
    data() {
      return {
        handler: '',
        inputCode: '',
        tabValue: 'batchExecutePane',
        selectAssetsSshAddr: '',
        selectAssetsIds: [],
        page: {pageNum: 1, pageSize: 10},
        executeVisible: false,
        execResultMap: [],
        selectTemplate: {tplId: null, tplContent: ''},
        execRegExp: /rm/g,
        tplSaveBtnVisible: false
      }
    },
    watch: {
      handlers: async function(val) {
        this.executeVisible = val === 'CONNECT'
        if (this.executeVisible) {
          this.inputCode = ''
          this.selectAssetsSshAddr = ''
          this.resetHandler()
        } else {
          this.reset()
        }
      },
      inputCode: async function() {
        this.tplSaveBtnVisible = this.selectTemplate.tplId && this.selectTemplate.tplContent !== this.inputCode
      }
    },
    methods: {
      ...mapActions('action', ['assetsExecute']),
      templateHandler() {
        this.handler = 'EXECUTE_TEMPLATE'
      },
      historyHandler() {
        this.handler = 'EXECUTE_HISTORY'
      },
      resultHandler() {
        this.handler = 'EXECUTE_RESULT'
      },
      selectTplHandler() {
        this.handler = 'SELECT_TEMPLATES'
      },
      tabClick() {
        if (this.tabValue === 'executeTplPane') {
          this.templateHandler()
        } else if (this.tabValue === 'executeHisPane') {
          this.historyHandler()
        } else {
          this.resetHandler()
        }
      },
      resetHandler() {
        this.handler = ''
        this.tabValue = 'batchExecutePane'
      },
      reset() {
        if (this.handler === 'EXECUTE_HISTORY') {
          this.$emit('reset')
        }
      },
      setPageForExecHistory(page) {
        this.$emit('setPageForExecHistory', page)
      },
      selectHostEvent() {
        this.handler = 'SELECT_HOST'
      },
      setAssetsHosts(selectHostIds, selectSshAddress) {
        this.selectAssetsIds = selectHostIds
        this.selectAssetsSshAddr = selectSshAddress
      },
      setTemplates(val) {
        this.selectTemplate = val
        this.inputCode = val.tplContent
      },
      clearTemplate() {
        this.selectTemplate = {tplId: null, tplContent: ''}
        this.inputCode = ''
      },
      hiddenTplSaveBtnVisible() {
        this.tplSaveBtnVisible = false
      },
      revertCurrentTpl() {
        this.inputCode = this.selectTemplate.tplContent
        this.hiddenTplSaveBtnVisible()
      },
      async updateCurrentTpl() {
        this.selectTemplate.tplContent = this.inputCode
        this.$refs.tpl.updateTemplate(this.selectTemplate)
        this.hiddenTplSaveBtnVisible()
      },
      verifyBeforeExecute() {
        let selectTpl = this.selectTemplate
        let inputCode = this.inputCode
        if (!inputCode || inputCode === '') {
          this.$msg.warning('请输入有效命令!')
          return
        } else if (this.selectAssetsIds.length === 0) {
          this.$msg.warning('请选择执行主机!')
          return
        } else if (!selectTpl.tplId && this.execRegExp.test(inputCode)) {
          // 禁止直接输入'rm'的命令，但执行模板里允许包含'rm'命令
          this.$msg.warning('禁止直接输入 `rm` 命令!')
          return
        } else if (this.tplSaveBtnVisible) {
          this.$msg.warning('请保存修改后的模板内容!')
          return
        }
        // 执行模板里只能保存不需要传入参数的脚本
        this.startExecute()
      },
      async startExecute() {
        let tplContent = this.selectTemplate.tplContent
        let tplId = this.selectTemplate.tplId
        let inputCode = null
        if (tplContent !== this.inputCode) {
          tplId = null
          inputCode = this.inputCode
        }
        let execRequest = {
          assetsIds: this.selectAssetsIds,
          moduleId: tplId,
          order: inputCode
        }
        let response = await this.assetsExecute(execRequest)
        if (response.code === '1') {
          this.execResultMap = response.data
          this.resultHandler()
        } else {
          this.$msg.error('命令执行失败: ' + response.msg)
        }
      }
    },
    mounted() {
      this.resetHandler()
    },
    beforeDestroy() {
      this.tabValue = 'batchExecutePane'
    }
  }
</script>
<style>
  .base-class {
    text-align: left;
    padding-bottom: 10px;
  }
</style>
