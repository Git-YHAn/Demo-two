<template>
<el-dialog title="请选择服务器" :append-to-body="true" :visible.sync="visible" @close="reset">
  <el-table ref="table" :data="orderDetail.servers" tooltip-effect="dark" @selection-change="selectionChange">
    <el-table-column type="selection" width="55"></el-table-column>
    <el-table-column prop="assetsName" label="名称" min-width="30px" min-height="20px" style="text-align:center"></el-table-column>
    <el-table-column prop="sshAddress" label="IP" min-width="30px"></el-table-column>
  </el-table>
  <div slot="footer">
    <el-button type="primary" @click="save()">保存</el-button>
    <el-button @click="reset">取消</el-button>
  </div>
</el-dialog>
</template>
<script>
export default {
  props: {
    orderDetail: Object,
    visible: Boolean
  },
  data: function () {
    return {
      checkedServers: false
    }
  },
  watch: {
    visible() {
      this.initForm()
    }
  },
  methods: {
    save() {
      this.$emit('save', Object.assign(this.orderDetail, { 'selectedServers': this.checkedServers }))
    },
    reset() {
      this.$emit('reset')
    },
    selectionChange: function (val) {
      this.checkedServers = val
    },
    initForm: function () {
      this.checkedServers = this.orderDetail.selectedServers
      this.orderDetail.servers.forEach((item) => {
        this.$nextTick(function () {
          if (this.orderDetail.selectedServers.indexOf(item) > -1) {
            this.$refs.table.toggleRowSelection(item, true)
          } else {
            this.$refs.table.toggleRowSelection(item, false)
          }
        })
      })
    }
  },
  mounted() {}
}
</script>
