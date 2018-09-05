<template lang="html">
  <div class="ksc-ip-input">
    <input type="text" v-model="ip1" :disabled="disableNum>0" @keyup="keyUpEvent" @focus="focusEvent" @blur="blurEvent($event, ip1)">
    <span class="ip-input-dot">.</span>
    <input type="text" v-model="ip2" :disabled="disableNum>1" @keyup="keyUpEvent" @focus="focusEvent" @blur="blurEvent($event, ip2)">
    <span class="ip-input-dot">.</span>
    <input type="text" v-model="ip3" :disabled="disableNum>2" @keyup="keyUpEvent" @focus="focusEvent" @blur="blurEvent($event, ip3)">
    <span class="ip-input-dot">.</span>
    <input type="text" v-model="ip4" :disabled="disableNum>3" @keyup="keyUpEvent" @focus="focusEvent" @blur="blurEvent($event, ip4)">
  </div>
</template>

<script>
export default {
  props: {
    disableNum: Number,
    value: String
  },
  data() {
    return {
      ip1: 0,
      ip2: 0,
      ip3: 0,
      ip4: 0
    }
  },
  watch: {
    ip1(value) {
      this.ip1 = this.parseIpNum(value)
    },
    ip2(value) {
      this.ip2 = this.parseIpNum(value)
    },
    ip3(value) {
      this.ip3 = this.parseIpNum(value)
    },
    ip4(value) {
      this.ip4 = this.parseIpNum(value)
    },
    result(value) {
      this.$emit('input', value)
    },
    value(val) {
      this.parseIpAddress(val)
    }
  },
  computed: {
    result() {
      return this.ip1 + '.' + this.ip2 + '.' + this.ip3 + '.' + this.ip4
    }
  },
  methods: {
    parseIpAddress(addr) {
      if (addr) {
        let arr = addr.split('.')
        this.ip1 = arr[0]
        this.ip2 = arr[1]
        this.ip3 = arr[2]
        this.ip4 = arr[3]
      }
    },
    parseIpNum(num) {
      if (/^(2[0-4]\d)$|^(25[0-5])$|^([01]?\d\d?)$/.test(num)) {
        return Number(num)
      } else if (Number(num) && Number(num) > 255) {
        return 255
      } else {
        return Number(num) || 0
      }
    },
    keyUpEvent(ev) {
      if (ev.keyCode === 110 || ev.keyCode === 190) {
        if (ev.target.nextSibling && ev.target.nextSibling.nextSibling) {
          ev.target.nextSibling.nextSibling.focus()
        }
      }
    },
    focusEvent(ev) {
      ev.target.value = ''
    },
    blurEvent(ev, num) {
      ev.target.value = num
      this.$emit('blur')
    }
  },
  mounted() {
    this.parseIpAddress(this.value)
  }
}
</script>

<style lang="css" scoped>
.ksc-ip-input {
    display: inline-block;
    border: 1px solid #ddd;
    padding: 0 5px;
    height: 28px;
    line-height: 28px;
}
.ksc-ip-input input:disabled {
    color: #a0a0a0;
    background: #efefef;
    cursor: not-allowed;
}
.ksc-ip-input input {
    border: 0;
    width: 52px;
    text-align: center;
    outline: none;
    margin-left: 2px;
    margin-right: 2px;
    line-height: 1.15;
}
.ksc-ip-input .ip-input-dot {
    color: #999;
}
</style>
