<template>
<el-dialog :title="title" :visible.sync="visible" @close="reset">
  <el-table ref="table" :data="roles" tooltip-effect="dark" @selection-change="changeRoleEvent" v-loading="loading">
    <el-table-column type="selection" width="55"></el-table-column>
    <el-table-column prop="roleName" label="角色名称" width="120"></el-table-column>
  </el-table>
  <div slot="footer">
    <el-button type="primary" @click="save" :disabled='this.obj.adminUserId === 1?true:false'>保存</el-button>
    <el-button @click="reset" :disabled="loading">取消</el-button>
  </div>
</el-dialog>
</template>
<script>
import { mapActions } from 'vuex'
export default {
  props: {
    handler: String,
    roles: Array,
    obj: Object
  },
  data() {
    return {
      visible: false,
      title: '',
      loading: false,
      checkedRoles: null
    }
  },
  watch: {
    handler: async function (val) {
      this.visible = val === 'ROLE_AUTH'
      if (this.visible) {
        this.title = `用户[${this.obj.username}] 角色授权`
        let roleIds = await this.getAdminUserRole(this.obj.adminUserId)
        this.roles.filter((item) => {
          return roleIds.data.indexOf(item.roleId) > -1
        }).forEach((item) => {
          this.$nextTick(function () {
            this.$refs.table.toggleRowSelection(item, true)
          })
        })
      }
    }
  },
  methods: {
    ...mapActions('action', ['getAdminUserRole', 'saveAdminUserRole']),
    changeRoleEvent: function (val) {
      this.checkedRoles = val
    },
    async save(form) {
      if (this.checkedRoles != null) {
        let userId = this.obj.adminUserId
        let roleId = this.checkedRoles.map((item) => {
          return item.roleId
        })
        let response = await this.saveAdminUserRole({ userId, roleId })
        if (response.code === '1') {
          this.$msg.success('保存成功！')
        } else {
          this.$msg.error('保存失败：', response.msg)
        }
        this.$emit('save', response.code)
      } else {
        this.$emit('reset')
      }
    },
    reset() {
      this.checkedRoles = null
      this.$refs.table.clearSelection()
      this.$emit('reset')
    }
  }
}
</script>
