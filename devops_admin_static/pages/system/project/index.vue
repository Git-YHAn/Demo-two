<template>
  <div>
    <el-form :inline="true" onsubmit="return false;">
      <el-form-item label="项目名称">
        <el-input placeholder="项目名称" v-model="keyWords" @keyup.enter.native="queryProjects"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button v-auth="'search'" type="primary" @click="queryProjects">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button v-auth="'add'" type="primary" @click="add()">新增</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="projects" border row-key='proId' @expand-change="show" :expand-row-keys="expands">
      <el-table-column type="expand" prop="proId">
        <template slot-scope="props">
          <app-table ref="appTableChild" :proId="props.row.proId" :datas="apps"></app-table>
        </template>
      </el-table-column>
      <el-table-column prop="proName" label="项目名称" min-width="100px"></el-table-column>
      <el-table-column prop="proCode" label="项目编码" min-width="100px"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" min-width="100px"></el-table-column>
      <el-table-column prop="updateTime" label="更新时间" min-width="100px"></el-table-column>
      <el-table-column prop="description" label="项目描述" min-width="150px" show-overflow-tooltip></el-table-column>
      <el-table-column label="操作" min-width="100px" show-overflow-tooltip>
        <template slot-scope="scope">
          <el-button v-auth="'edit'" type="text" @click="edit(scope.row)">编辑</el-button>
          <el-button v-auth="'added'" type="text" @click="added(scope.row)">新增子应用</el-button>
          <el-button v-auth="'delete'" type="text" @click="del(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-footer class="footer">
      <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize" @pagenum="setPageNo"></pagination>
    </el-footer>
    <add-form :handler="handler" @save="reloadPage" @reset="resetHandler"></add-form>
    <added-form :obj="selectRow" :handler="handler" @save="afterAdded" @reset="resetHandler"></added-form>
    <edit-form :obj="selectRow" :handler="handler" @save="reloadPage" @reset="resetHandler"></edit-form>
  </div>
</template>
<script>
  import {mapActions} from 'vuex'
  import addForm from './add.vue'
  import editForm from './edit.vue'
  import addedForm from './application/add.vue'
  import appTable from './application/index.vue'

  export default {
    components: {addForm, editForm, addedForm, appTable},
    data() {
      return {
        keyWords: '',
        jobs: [],
        projects: [],
        handler: '',
        selectRow: {},
        page: {pageNum: 1, pageSize: 30},
        expands: [],
        apps: []
      }
    },
    methods: {
      ...mapActions('action', ['searchProjects', 'deleteProject', 'searchAppsByPro']),
      async show(row) {
        if (this.expands && this.expands.length > 0) {
          // 关闭当前展开的行
          if (this.expands[0] === row.proId) {
            this.expands = []
            return
          }
        }
        let response = await this.searchAppsByPro(row.proId)
        if (response.code === '1') {
          this.apps = response.data
        }

        // 先清空展开行的 keys 数组
        this.expands = []
        this.expands.push(row.proId)
      },
      reloadPage() {
        this.page.pageNum = 1
        this.resetHandler()
        this.queryProjects()
      },
      resetHandler() {
        this.handler = ''
      },
      add() {
        this.handler = 'ADD'
      },
      added(row) {
        this.handler = 'ADDED'
        this.selectRow = row
      },
      afterAdded() {
        this.$refs.appTableChild.queryApps()
        this.reloadPage()
      },
      edit(row) {
        this.handler = 'EDIT'
        this.selectRow = row
      },
      setPageSize: function(size) {
        this.page.pageSize = size
        this.page.pageNum = 1
        this.queryProjects(size, 1)
      },
      setPageNo: function(num) {
        this.page.pageNum = num
        this.queryProjects(undefined, num)
      },
      async queryProjects() {
        let response = await this.searchProjects({'keyWords': this.keyWords, 'pageSize': this.page.pageSize, 'pageNum': this.page.pageNum})
        if (response.code === '1') {
          this.projects = response.data.results
          this.page = response.data.meta
        }
        return response
      },
      async del(row) {
        if (row.proId) {
          this.$confirm('是否删除?', '提示', {confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning'}).then(async () => {
            let response = await this.deleteProject(row.proId)
            if (response.code === '1') {
              this.$msg.success('删除成功！')
              let res = await this.queryProjects()
              this.jobs = res.data.results
            } else {
              this.$msg.error('删除失败：', response.msg)
            }
          })
        }
      }
    },
    async created() {
      this.queryProjects()
    }
  }
</script>
