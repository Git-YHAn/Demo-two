<template>
  <el-dialog title="执行结果" :visible.sync="resultVisible" @close="reset">
    <template v-for="item in results">
      <div style="margin-bottom: 10px;">
        <el-button type="primary" @click="clickExecuteResult(item)" :disabled=item.disabled>查看 {{item.ssh}}</el-button>
        <el-button type="primary" @click="terminateResult(item)">终止</el-button>
        <div v-if="item.result !== ''">
          <pre :id="item.key" style="max-height: 200px;margin-top: 10px;">{{item.result}}</pre>
        </div>
      </div>
    </template>
  </el-dialog>
</template>

<script>
  import {mapActions} from 'vuex'

  export default {
    props: {
      handler: String,
      execResultMap: null
    },
    data() {
      return {
        cancelDisable: true,
        initialTable: [],
        page: {pageNum: 1, pageSize: 10},
        keyWords: '',
        resultVisible: false,
        addTemplateVisible: false,
        execResult: '',
        executeTemplateQueryForm: [],
        executeTemplateTable: [],
        addTemplateForm: {
          tplName: '',
          tplType: '',
          tplContent: '',
          tplDescription: ''
        },
        rule: {},
        intervals: [],
        results: []
      }
    },
    watch: {
      handler: async function(val) {
        this.resultVisible = val === 'EXECUTE_RESULT'
        if (this.resultVisible) {
          this.results = this.execResultMap
        }
      }
    },
    methods: {
      ...mapActions('action', ['assetsGetResult', 'cancelResult']),
      reset() {
        this.intervals.forEach(e => {
          clearInterval(e.inter)
        })

        this.results = []
        this.$emit('reset')
      },
      resetHandler() {
        this.handler = ''
      },
      async clickExecuteResult(row) {
        // 定时轮询
        let inter = setInterval(this.getResult, 1000, row)
        let obj = {}
        obj.key = row.key
        obj.inter = inter
        this.intervals.push(obj)
        row.disabled = true
      },

      // 获取结果
      async getResult(row) {
        let response = await this.assetsGetResult({'key': row.key})
        if (response.code === '1') {
          row.result = response.data
        }
        this.scrollToBottom(row.key)
      },

      // 取消获取结果
      async terminateResult(row) {
        let response = await this.cancelResult({'key': row.key})
        if (response.code === '1') {
          this.intervals.forEach(e => {
            if (e.key === row.key) {
              clearInterval(e.inter)
            }
          })
        }
      },
      scrollToBottom(preId) {
        this.$nextTick(() => {
          let pre = document.getElementById(preId)
          if (pre) {
            pre.scrollTop = pre.scrollHeight
          }
        })
      }
    }
  }
</script>
