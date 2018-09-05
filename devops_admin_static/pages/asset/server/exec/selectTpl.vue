<template>
  <el-dialog title="模板列表" :visible.sync="isVisible" @close="reset">
    <el-form v-model="keywords" :inline="true" onsubmit="return false;" style="margin-bottom: 10px;">
      <el-form-item>
        <el-input v-model="keywords.keywordTplName" clearable placeholder="输入模板名称" @keyup.enter.native="getExecuteTemplates" @clear="getExecuteTemplates"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="keywords.keywordTplType" clearable placeholder="输入模板类型" @keyup.enter.native="getExecuteTemplates" @clear="getExecuteTemplates"></el-input>
      </el-form-item>
      <el-form-item style="float: right; margin-right: 20px;">
        <el-button v-auth="'search'" type="primary" @click="getExecuteTemplates">搜索</el-button>
      </el-form-item>
    </el-form>
    <el-table ref="templateTableData" :data="templateTableData" @current-change="handleCurrentChange" border highlight-current-row height="400px">
      <el-table-column prop="tplName" label="模板名称" width="200px"></el-table-column>
      <el-table-column prop="tplType" label="模板类型" width="200px"></el-table-column>
      <el-table-column prop="tplDescription" label="模板描述" min-width="120px"></el-table-column>
    </el-table>
    <el-row style="float: right;margin-top: 5px;margin-right: 10px;">
      <el-button @click="cancel" type="primary">取消</el-button>
      <el-button @click="save" type="primary" :disabled="saveDisabled">保存</el-button>
    </el-row>
    <template slot="footer">
      <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize" @pagenum="setPageNo"></pagination>
    </template>
  </el-dialog>
</template>

<script>
  import {mapActions} from 'vuex'

  export default {
    props: {
      handler: String
    },
    data() {
      return {
        isVisible: false,
        saveDisabled: true,
        page: {pageNum: 1, pageSize: 10},
        templateTableData: [],
        currentTemplate: null,
        keywords: {keywordTplName: '', keywordTplType: ''}
      }
    },
    watch: {
      handler: async function (val) {
        this.isVisible = val === 'SELECT_TEMPLATES'
        if (this.isVisible) {
          this.reloadPage()
        }
      }
    },
    methods: {
      ...mapActions('action', ['queryExecuteTemplates']),
      reset() {
        this.$emit('reset')
      },
      reloadPage() {
        this.page.pageNum = 1
        this.page.pageSize = 10
        this.getExecuteTemplates()
      },
      setPageSize: function (size) {
        this.page.pageSize = size
        this.getExecuteTemplates()
      },
      setPageNo: function (num) {
        this.page.pageNum = num
        this.getExecuteTemplates()
      },
      handleCurrentChange(val) {
        this.currentTemplate = val
        this.saveDisabled = val === null
      },
      save() {
        this.$emit('fromSelectTpl', this.currentTemplate)
        this.reset()
      },
      cancel() {
        this.currentTemplate = null
        this.$emit('fromSelectTpl', {tplId: null, tplContent: ''})
        this.reset()
      },
      setCurrent(row) {
        this.$refs.templateTableData.setCurrentRow(row)
      },
      async getExecuteTemplates() {
        let response = await this.queryExecuteTemplates({
          'tplName': this.keywords.keywordTplName,
          'tplType': this.keywords.keywordTplType,
          'pageSize': this.page.pageSize,
          'pageNum': this.page.pageNum
        })
        if (response.code === '1') {
          this.templateTableData = response.data.results
          this.page = response.data.meta
          this.checkAndSetSelectedTemplate()
        }
      },
      checkAndSetSelectedTemplate() {
        if (this.currentTemplate) {
          this.templateTableData.forEach(tpl => {
            if (this.currentTemplate.tplId === tpl.tplId) {
              this.$nextTick(function() {
                this.setCurrent(tpl)
              })
            }
          })
        }
      }
    },
    async destroyed() {
      this.templateTableData = []
      this.currentTemplate = null
    }
  }
</script>
