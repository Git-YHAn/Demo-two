<template>
  <el-dialog :title='obj.versionCode' :visible.sync="visible" @close="reset">
    <el-scrollbar ref="consoleScrollBar" tag="div" style="overflow-wrap: break-word;"
                  wrap-class="el-select-dropdown__wrap" view-class="el-select-dropdown__list" :horizontal="false">
      <pre class="grid-content grid-content bg-purple-light" style="height:300px">{{ obj.description }}
      </pre>
    </el-scrollbar>
  </el-dialog>
</template>
<script>
  export default {
    props: {
      obj: Object,
      handler: String
    },
    data() {
      return {
        visible: false
      }
    },
    watch: {
      handler: async function(val) {
        this.visible = val === 'INFO'
      }
    },
    methods: {
      reset() {
        this.$emit('reset')
      }
    }
  }
</script>
