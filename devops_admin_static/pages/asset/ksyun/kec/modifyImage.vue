<template>
<el-dialog title="开启服务器" :visible.sync="visible" @close="reset">
  <span>已经选择<span class="font-green-jungle">{{instances.length}}</span>台服务器，其中有<span class="font-green-jungle">{{instances.filter(item=>item.instanceState.name!=='stopped').length}}</span>台不能重装系统</span>
  <el-table :data="instances">
    <el-table-column label="服务器名称" prop="instanceName" min-width="120" align="left" ></el-table-column>
    <el-table-column label="操作系统" prop="image.platform" min-width="120" align="left" ></el-table-column>
    <el-table-column label="内网地址" prop="privateIpAddress" min-width="120" align="left"></el-table-column>
    <el-table-column label="能否重装" min-width="150" align="left">
      <template slot-scope="scope">
        <span v-if="scope.row.instanceState.name!=='stopped'" class="font-red">关闭的云服务器才可重装</span>
        <span v-else class="font-green-jungle">可重装</span>
      </template>
    </el-table-column>
  </el-table>

  <el-form ref="form" :model="form" :rules="rules" style="margin-top: 20px;" label-width="100px" status-icon>
    <el-form-item label="镜像类型">
      <el-radio-group v-model="form.imageType" @change="changeImageTypeEvent">
        <el-radio label="system">标准镜像</el-radio>
        <el-radio label="custom">自定义镜像</el-radio>
        <el-radio label="share">共享镜像</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="镜像" prop="imageId">
      <el-select class="fw" v-model="form.imageId" placeholder="请选择系统镜像" value-key="imageId" popper-class="custom-dropdown">
        <el-option-group v-for="group in groupImages" :key="group.label" :label="group.label">
          <el-option v-for="item in group.options" :label="item.name" :key="item.imageId" :value="item.imageId">
            <div>
              <span>{{item.name}}</span>
              <p :title="item.name">{{ imageTypeKvm[item.platform]||item.platform }}</p>
            </div>
          </el-option>
        </el-option-group>
      </el-select>
    </el-form-item>
    <el-form-item label="管理员账户">
      <template v-if="currentImage&&currentImage.platform.indexOf('windows')>-1">kingsoft</template>
      <template v-else-if="currentImage&&currentImage.platform.indexOf('ubuntu')>-1">ubuntu</template>
      <template v-else>root</template>
    </el-form-item>
    <el-form-item label="管理员密码" prop="instancePassword">
      <el-input type="password" style="width:300px" v-model="form.instancePassword"></el-input>
      <el-tooltip class="item" effect="light" placement="top">
        <template slot="content">
          <span v-html>8-32个字符，必须包含大小写字母和数字，支持英文特殊字符!$%()*+,-./:;<=>?@[]^_`{|}~</span>
        </template>
        <i class="icon-question tooltip-icon mgl10"></i>
      </el-tooltip>
    </el-form-item>
    <el-form-item label="确认密码" prop="pwdConfirm">
      <el-input type="password" style="width:300px" v-model="form.pwdConfirm"></el-input>
    </el-form-item>
  </el-form>
  <div slot="footer">
    <el-button type="primary" :loading='loading' :disabled='loading' @click="confirm">确认</el-button>
    <el-button @click="reset" :disabled='loading'>取消</el-button>
  </div>
</el-dialog>
</template>
<script>
import { mapActions, mapState, mapGetters } from 'vuex'
export default {
  props: {
    instances: Array
  },
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else if (!/^[0-9a-zA-Z!$%()*+,-./:;<=>?@[\]^_`{|}~]{8,32}$/m.test(value) || !/[0-9]+/.test(value) || !/[a-z]+/.test(value) || !/[A-Z]+/.test(value)) {
        callback(new Error('请填写正确的密码格式'))
      } else {
        if (this.form.pwdConfirm !== '') {
          this.$refs.form.validateField('pwdConfirm')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.form.instancePassword) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      currentImages: [],
      groupImages: [],
      visible: true,
      loading: false,
      form: {
        imageType: 'system',
        imageId: '',
        pwd: '',
        pwdConfirm: ''
      },
      rules: {
        instancePassword: [
          { validator: validatePass, trigger: 'blur' },
          { min: 8, max: 32, message: '长度在 8 到 32 个字符', trigger: 'blur' }
        ],
        pwdConfirm: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        imageId: [
          { required: true, message: '请选择镜像', trigger: 'change' }
        ]
      }
    }
  },
  computed: {
    ...mapGetters('ksyun', ['imageTypeKvm']),
    ...mapState('ksyun', ['images']),
    currentImage: function () {
      return this.images && this.images[this.form.imageType] && this.images[this.form.imageType].filter(item => item.imageId === this.form.imageId)[0]
    }
  },
  methods: {
    ...mapActions('action', ['modifyKsyunKecInstanceImage']),
    ...mapActions('ksyun', ['searchImagesByType']),
    async changeImageTypeEvent(value) {
      this.loading = true
      await this.getGroupSystemImage()
      if (this.images[value] && this.images[value].length > 0) {
        this.form.imageId = this.images[value][0].imageId
      } else {
        this.form.imageId = ''
      }
      this.loading = false
    },
    async getGroupSystemImage() {
      this.currentImages = await this.searchImagesByType(this.form.imageType)
      let groups = {}
      this.groupImages = []
      if (this.currentImages) {
        this.currentImages.forEach(item => {
          if (item.platform.indexOf('debian') > -1) {
            if (!groups['debian']) { groups['debian'] = [] }
            groups['debian'].push(item)
          } else if (item.platform.indexOf('ubuntu') > -1) {
            if (!groups['ubuntu']) { groups['ubuntu'] = [] }
            groups['ubuntu'].push(item)
          } else if (item.platform.indexOf('fedora') > -1) {
            if (!groups['fedora']) { groups['fedora'] = [] }
            groups['fedora'].push(item)
          } else if (item.platform.indexOf('windows') > -1) {
            if (!groups['windows']) { groups['windows'] = [] }
            groups['windows'].push(item)
          } else if (item.platform.indexOf('centos') > -1) {
            if (!groups['centos']) { groups['centos'] = [] }
            groups['centos'].push(item)
          }
        })
        for (let key in groups) {
          this.groupImages.push({ 'label': key, 'options': groups[key] })
        }
      }
    },
    async confirm() {
      this.$refs['form'].validate(async (valid, object) => {
        if (valid) {
          this.loading = true
          let instanceIds = this.instances.filter(item => {
            return item.instanceState.name === 'stopped'
          }).map(item => {
            return item.instanceId
          })
          if (instanceIds.length === 0) {
            this.$msg.error('没有可以重装系统的实例！')
            return
          }
          let response = await this.modifyKsyunKecInstanceImage({ instanceIds, imageId: this.form.imageId, instancePassword: this.form.instancePassword, keepImageLogin: false })
          if (response.code === '1') {
            this.$emit('save', response.data)
          } else {
            this.$msg.error(response.msg)
          }
          this.loading = false
        } else {
          let msg = ''
          for (let key in object) {
            let validArr = object[key]
            if (validArr.length > 0) {
              msg = validArr[0].message
              this.$msg.error(msg)
              return false
            }
          }
          return false
        }
      })
    },
    reset() {
      this.$emit('reset')
    }
  },
  mounted() {
    this.changeImageTypeEvent(this.form.imageType)
  }
}
</script>
<style lang="css" scoped>
.custom-dropdown .el-select-dropdown__item {
  height: auto;
  line-height: auto;
}

.custom-dropdown li.el-select-dropdown__item span {
  float: left;
}

.custom-dropdown li.el-select-dropdown__item p {
  float: right;
  color: #8492a6;
  font-size: 13px;
  text-decoration: none;
  max-width: 500px;
  overflow: hidden;
  text-overflow: ellipsis;
  margin: 0;
  padding-left: 10px;
}
</style>
