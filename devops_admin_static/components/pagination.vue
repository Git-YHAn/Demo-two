<template>
<el-pagination style="text-align:center" @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="current" :page-sizes="[10,30,60,100]" :page-size="pagesize"
               layout="total, sizes, prev, pager, next, jumper" :total="total">
</el-pagination>
</template>
<script>
export default {
  name: 'pagination',
  props: {
    current: Number,
    total: Number,
    pagesize: Number
  },
  methods: {
    handleSizeChange(val) {
      this.$emit('pagesize', val)
    },
    handleCurrentChange(val) {
      this.$emit('pagenum', val)
    }
  }
}
</script>
