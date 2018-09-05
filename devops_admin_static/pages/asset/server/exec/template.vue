<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="模板名称" label-width="80px">
        <el-input v-model="filterTplName" placeholder="请输入环境名称" clearable
                  @clear="reloadPage"></el-input>
      </el-form-item>
      <el-form-item label="模板类型" label-width="80px">
        <el-input v-model="filterTplType" placeholder="请输入项目名称" clearable
                  @clear="reloadPage"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getExecuteTemplate">查询</el-button>
      </el-form-item>
      <el-form-item style="float:right;">
        <el-button type="primary" @click="setAddTplHandler">新增模板</el-button>
      </el-form-item>
    </el-form>
    <!--显示-->
    <el-table ref="templateTableData" :data="templateTableData" border max-height="450">
      <el-table-column prop="tplName" label="模板名称" min-width="100px"></el-table-column>
      <el-table-column prop="tplType" label="模板类型" min-width="100px"></el-table-column>
      <el-table-column prop="tplDescription" label="模板描述" min-width="100px"></el-table-column>
      <el-table-column label="操作" min-width="160px">
        <template slot-scope="scope">
          <el-button type="text" @click="setEditTplHandler(scope.row)">编辑</el-button>
          <el-button type="text" @click="deleteTemplate(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <footer style="margin-top: 10px;">
      <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize" @pagenum="setPageNo"></pagination>
    </footer>

    <add-tpl-dialog :addTplHandler="tplHandler" @reset="reloadPage"></add-tpl-dialog>
    <edit-tpl-dialog ref="editTpl" :editTplHandler="tplHandler" :currentTpl="selectRow" @reset="reloadPage"></edit-tpl-dialog>
  </div>
</template>

<script>
  import {mapActions} from 'vuex'
  import addTplDialog from './addTpl'
  import editTplDialog from './editTpl'

  export default {
    components: {
      addTplDialog,
      editTplDialog
    },
    props: {
      handler: String
    },
    data() {
      return {
        tplHandler: '',
        selectRow: {},
        page: {pageNum: 1, pageSize: 10},
        filterTplName: null,
        filterTplType: null,
        templateTableData: []
      }
    },
    watch: {
      handler: async function(val) {
        this.initialVisible = val === 'EXECUTE_TEMPLATE'
        if (this.initialVisible) {
          this.reloadPage()
        }
      }
    },
    methods: {
      ...mapActions('action', ['queryExecuteTemplates', 'delExecuteTemplate']),
      resetHandler() {
        this.tplHandler = ''
      },
      setAddTplHandler() {
        this.tplHandler = 'ADD_TEMPLATE'
      },
      setEditTplHandler(row) {
        this.tplHandler = 'EDIT_TEMPLATE'
        this.selectRow = row
      },
      setPageSize: function(size) {
        this.page.pageSize = size
        this.reloadPage()
      },
      setPageNo: function(num) {
        this.page.pageNum = num
        this.reloadPage()
      },
      reloadPage() {
        this.resetHandler()
        this.getExecuteTemplate()
      },
      async getExecuteTemplate() {
        let response = await this.queryExecuteTemplates({
          'pageSize': this.page.pageSize,
          'pageNum': this.page.pageNum,
          'tplName': this.filterTplName,
          'tplType': this.filterTplType
        })
        if (response.code === '1') {
          this.templateTableData = response.data.results
          this.page = response.data.meta
        }
      },
      async deleteTemplate(row) {
        let response = await this.delExecuteTemplate({'tplId': row.tplId})
        if (response.code === '1') {
          this.$msg.success('删除成功')
        }
        this.getExecuteTemplate()
      },
      async updateTemplate(tpl) {
        this.$refs.editTpl.update(tpl)
      }
    },
    async destroyed() {
      this.templateTableData = []
    }
  }
</script>
