<template>
<el-dialog :title="filePath" :visible.sync="visible" @close="reset">
  <div style="height:500px;">
    <no-ssr placeholder="Loading...">
      <codemirror class="code-container fh" ref="codemirror" v-model="code" :options="options">
      </codemirror>
    </no-ssr>
  </div>
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
      visible: false,
      filePath: '',
      code: '',
      options: {
        mode: 'text/x-diff',
        readOnly: true
      }
    }
  },
  watch: {
    obj: function (val) {
      this.filePath = val.diffFile
      this.code = val.diffContent
    },
    handler: async function (val) {
      this.visible = val === 'DIFF'
    }
  },
  methods: {
    reset() {
      this.$emit('reset')
    }
  },
  mounted() {}
}
</script>
