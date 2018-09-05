<template>
<el-dialog title="更改记录" :visible.sync="visible" @close="reset">
  <pre class="grid-content grid-content bg-purple-light" style="height:300px" v-html="obj.fileChange"></pre>
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
    handler: async function (val) {
      this.visible = val === 'CHANGE'
    }
  },
  methods: {
    reset() {
      this.$emit('reset')
    }
  }
}
</script>
