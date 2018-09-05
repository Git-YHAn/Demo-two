<template>
  <div>
    <template v-if="handler === ''">
      <el-table :data="tableData" highlight-current-row >
        <el-table-column  prop="typeId" label="ID" min-width="20" align="center"> </el-table-column>
        <el-table-column  prop="typeName" label="类型名" min-width="180" align="center"> </el-table-column>
        <el-table-column  prop="isEnable" label="是否启用" min-width="180" align="center">
          <template slot-scope="scope">
            <span style="color: green" v-if="scope.row.isEnable === 0">是</span>
            <span v-else style="color: red" >否</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" min-width="100px" align="center" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-button style="color: red" type="text" v-if="scope.row.isEnable === 0" @click="chageEnable(1, scope.row)">禁用</el-button>
            <el-button style="color: green" type="text" v-else @click="chageEnable(0, scope.row)">启用</el-button>
            <el-button type="text"  @click="select(scope.row)">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>
    <select-form :obj="selectRow" v-if="handler === 'SELECT'" @reset="resetHandler"></select-form>
  </div>
</template>
<script type='text/javascript'>
import {
  mapActions
} from 'vuex'
import selectForm from './select.vue'
export default {
  components: {
    selectForm
  },
  data() {
    return {
      tableData: [{
        typeName: ''
      }],
      selectRow: {},
      handler: ''
    }
  },
  computed: {},
  methods: {
    ...mapActions('action', ['selectSysLogType', 'changeLogEnable']),
    select(row) {
      this.handler = 'SELECT'
      this.selectRow = row
    },
    resetHandler() {
      this.handler = ''
    },
    chageEnable(status, row) {
      row.isEnable = status
      this.changeLogEnable(row)
    },
    async searchSysLogType() {
      let response = await this.selectSysLogType()
      if (response.code === '1') {
        this.tableData = response.data
      }
    }
  },
  mounted() {
    this.searchSysLogType()
  }
}
</script>
