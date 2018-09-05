<template>
<el-dialog title="文件变动" :visible.sync="visible" @close="reset">
  <el-table :data="tableData" border>
    <el-table-column prop="changeType" label="变更方式" min-width="80px" style="text-align:center"> </el-table-column>
    <el-table-column prop="path" label="文件路径" show-overflow-tooltip min-width="300px"></el-table-column>
  </el-table>
</el-dialog>
</template>
<script>
import { mapActions } from 'vuex'
export default {
  props: {
    obj: Object,
    handler: String
  },
  data() {
    return {
      visible: false,
      diffList: []
    }
  },
  computed: {
    tableData: function () {
      return this.diffList.map(item => {
        if (item.changeType === 'DELETE') {
          item.path = item.oldPath
        } else {
          item.path = item.newPath
        }
        return item
      })
    }
  },
  watch: {
    handler: async function (val) {
      this.visible = val === 'DIFF'
      if (this.visible) {
        let response = await this.showStagedDiff(this.obj)
        if (~~response.code === 1) {
          this.diffList = response.data
        }
      }
    }
  },
  methods: {
    ...mapActions('action', ['showStagedDiff']),
    reset() {
      this.$emit('reset')
    }
  }
}
</script>
