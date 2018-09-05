<template>
<div class="blank-page-container">
  <div class="filter-box">
    <el-input placeholder="输入关键字进行过滤" v-model="filterText"></el-input>
  </div>
  <el-tree v-if="files" ref="files" :data="files" :filter-node-method="filterNode" :props="defaultProps" highlight-current clearable node-key="id">
    <div class="custom-tree-node row" slot-scope="{ node, data }">
      <div class="col-xs-4"><a href="javascript:;"><i class="mgr5 fa" :class="getResourceIcon(data, node)"></i>{{ data.resourceName }}</a></div>
      <div class="col-xs-2 text-right">{{ data.lastModified }}</div>
      <div class="col-xs-3 text-right">{{ data.size|sizeTransfer }}</div>
      <div class="col-xs-3 text-right" v-if="data.type===1">
        <el-button type="text" v-if="!node.data.downStatus" @click.stop.prevent="nodeClickEvent(data,node)">
          <span><i class="mgr5 fa fa-download"></i>下载</span>
        </el-button>
        <el-button type="text" v-if="node.data.downStatus===1">
          <span class="font-yellow-gold"><i class="mgr5 fa fa-spinner fa-spin"></i>下载中</span>
        </el-button>
        <el-button type="text" v-if="node.data.downStatus===2">
          <span class="font-green-jungle"><i class="mgr5  fa fa-check"></i>下载成功</span>
        </el-button>
      </div>
    </div>
  </el-tree>
</div>
</template>
<script>
import { mapActions } from 'vuex'

export default {
  data() {
    return {
      filterText: '',
      files: [],
      defaultProps: {
        children: 'children',
        label: 'resourceName',
        id: 'logPath',
        isLeaf: true
      }
    }
  },
  watch: {
    filterText(val) {
      this.$refs.files.filter(val)
    }
  },
  computed: {
    instanceId: function () {
      return this.$route.query.instanceId
    },
    detailId: function () {
      return this.$route.query.detailId
    }
  },
  methods: {
    ...mapActions('action', ['searchAppInstanceLogs', 'downloadAppLog']),
    async queryAppInstanceLogs() {
      let response = await this.searchAppInstanceLogs({ appInstanceId: this.instanceId, appInstanceDetailId: this.detailId })
      if (~~response.code === 1) {
        this.files = response.data
      }
    },
    filterNode(value, data) {
      if (!value) return true
      return data.resourceName.indexOf(value) !== -1
    },
    async nodeClickEvent(data, node) {
      if (data.type === 1) {
        this.$set(data, 'downStatus', 1)
        await this.downloadAppLog(data.logPath)
        this.$set(data, 'downStatus', 2)
      }
    },
    getResourceIcon(data, node) {
      if (data.type === 0 && !node.expanded) {
        return 'fa-folder-o'
      } else if (data.type === 0 && node.expanded) {
        return 'fa-folder-open-o'
      } else {
        return 'fa-file-o'
      }
    }
  },
  mounted() {
    this.queryAppInstanceLogs()
  }
}
</script>
<style scoped>
.blank-page-container {
  min-width: 700px;
  padding-top: 20px;
}

.custom-tree-node {
  width: 100%;
  font-size: 14px;
  padding-right: 8px;
}

.filter-box {
  padding: 0 20px 20px;
}
</style>
