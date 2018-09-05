<template>
  <el-dialog title="授权管理" :visible.sync="visible" @close="reset">
    <el-form label-width="80px" ref='authForm'>
      <el-tree v-if="resourceTree" ref="resourceTree" :data="resourceTree" show-checkbox highlight-current :default-expanded-keys="defaultExpandKeys" node-key="resourceId" :props="defaultProps">
        <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        </span>
      </el-tree>
    </el-form>
    <div slot="footer">
      <el-button type="primary" @click="save" >保存</el-button>
      <el-button @click="reset" >取消</el-button>
    </div>
  </el-dialog>
</template>
<script>
import { mapActions } from 'vuex'
export default {
  props: {
    handler: String,
    roles: Array,
    obj: Object,
    resourceTree: Array
  },
  data() {
    return {
      defaultProps: {
        children: 'children',
        label: 'resourceName',
        id: 'resourceId'
      },
      visible: false,
      title: '',
      loading: false,
      checkedRoles: null
    }
  },
  watch: {
    handler: async function (val) {
      this.visible = val === 'AUTH'
      if (this.visible) {
        this.getRoleResource()
      }
    }
  },
  computed: {
    defaultExpandKeys: function () {
      return this.resourceTree.map((item) => {
        return item.resourceId
      })
    }
  },
  methods: {
    ...mapActions('action', ['searchRoleResource', 'getAdminUserRole', 'saveAdminUserRole', 'searchResourceTree', 'addRoleResource']),
    async getRoleResource() {
      let response = await this.searchRoleResource(this.obj.roleId)
      if (response.code === '1') {
        this.$refs.resourceTree.setCheckedKeys(response.data)
      }
    },
    async queryResourceTree() {
      let res = await this.searchResourceTree()
      if (res.code === '1') {
        this.resourceTree = res.data
      }
    },
    changeRoleEvent: function (val) {
      this.checkedRoles = val
    },
    async save(evnt, node) {
      let chooseNodes = this.$refs.resourceTree.getCheckedKeys()
      let roleId = this.obj.roleId
      let response = await this.addRoleResource({ 'resourceId': chooseNodes, 'roleId': roleId })
      if (response.code === '1') {
        this.$msg.success('保存成功！')
      } else {
        this.$msg.error('保存失败：', response.msg)
      }
      this.$emit('save', response.code)
    },
    reset() {
      this.$emit('reset')
    }
  }
}
</script>
