<template lang="html">
  <div class="tabbable-custom">
  <!-- <ul class="nav nav-tabs">
  <li class="active">
    <a href="#tab_1_1_1" data-toggle="tab" aria-expanded="true"> Section 1 </a>
  </li>
  <li class="">
    <a href="#tab_1_1_2" data-toggle="tab" aria-expanded="false"> Section 2 </a>
  </li>
  </ul> -->
    <div class="tab-content">
      <div class="tab-pane active" >
        <div class="row">
            <ul class="nav nav-pills nav-justified steps">
              <li :class="{'active':createStep===1}"><a href="javascript:;" class="step"><span class="number"> 1 </span><span class="desc"> 选择配置 </span></a></li>
              <li :class="{'active':createStep===2}"><a href="javascript:;" class="step"><span class="number"> 2 </span><span class="desc"> 选择弹性IP </span></a></li>
              <li :class="{'active':createStep===3}"><a href="javascript:;" class="step"><span class="number"> 3 </span><span class="desc"> 设置VPC </span></a></li>
              <li :class="{'active':createStep===4}"><a href="javascript:;" class="step"><span class="number"> 4 </span><span class="desc"> 设置基本信息 </span></a></li>
          </ul>
        </div>
        <div class="row">
            <div class="col-xs-8 form-content">
              <el-form ref="form1"  v-show="createStep===1" :model="form"  style="margin-top: 20px;font-family: sans-serif;" label-width="180px" size="small">
                  <el-form-item label="计费方式：">
                    <el-radio-group v-model="form.chargeType">
                      <el-radio-button label="Daily">按日月结</el-radio-button>
                    </el-radio-group>
                  </el-form-item>
                  <el-form-item label="数据中心：">
                    <el-radio-group v-model="form.region">
                      <el-radio-button v-for="item in regions" :key="item.region" :label="item.region">{{item.regionName}}</el-radio-button>
                    </el-radio-group>
                  </el-form-item>
                  <el-form-item label="可用区：" style="height:40px">
                    <el-radio-group v-model="form.avaliableZone">
                        <el-radio-button v-for="item in currentZones" :key="item.availabilityZone" :label="item">{{item.name}}</el-radio-button>
                    </el-radio-group>
                  </el-form-item>
                  <el-form-item label="系列：">
                    <el-radio-group v-model="form.serial">
                      <el-radio-button v-for="item in currentSerials" :key="item" :label="item">系列{{item}}</el-radio-button>
                    </el-radio-group>
                  </el-form-item>
                  <el-form-item label="云服务器类型：">
                    <el-radio-group v-model="form.instanceFamily">
                      <el-radio-button v-for="item in currentInstanceFamilies" :key="item" :label="item">{{instanceFamilys[item]}}</el-radio-button>
                    </el-radio-group>
                  </el-form-item>
                  <el-form-item label="联网增强：">
                    <el-switch v-model="form.sriovNetSupport" :disabled="currentSriovNetSupports.length<2" :active-text="form.sriovNetSupport?'启用':'禁用'"></el-switch>
                  </el-form-item>
                  <el-form-item label="CPU：">
                    <el-radio-group v-model="form.cpu">
                      <el-radio-button v-for="item in currentCpus" :key="item" :label="item">{{item}}核</el-radio-button>
                    </el-radio-group>
                  </el-form-item>
                  <el-form-item label="内存：">
                    <el-radio-group v-model="form.memory">
                      <el-radio-button v-for="item in currentMemories" :key="item" :label="item">{{item}}G</el-radio-button>
                    </el-radio-group>
                  </el-form-item>
                  <el-form-item label="镜像类型：">
                    <el-radio-group v-model="form.imageType" @change="changeImageTypeEvent">
                      <el-radio label="system">标准镜像</el-radio>
                      <el-radio label="custom">自定义镜像</el-radio>
                      <el-radio label="share">共享镜像</el-radio>
                    </el-radio-group>
                  </el-form-item>
                  <el-form-item label="镜像：" prop="imageId">
                    <el-select class="fw" v-model="form.imageId" placeholder="请选择系统镜像" value-key="imageId" popper-class="cst-group-dropdown">
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
                  <el-form-item label="系统盘：">
                    <el-select  style="width:140px" disabled v-model="form.sysDiskTitle" >
                      <el-option :label="form.sysDiskTitle" :value="form.sysDiskTitle"></el-option>
                    </el-select>
                    <span class="mgl10">免费赠送 ({{form.osType}} {{form.sysDiskGb}}GB)</span>
                  </el-form-item>
                  <el-form-item v-if="form.dataDiskType==='ssd'" label="数据盘：" prop="dataDiskGb" :rules="dataDiskRules">
                      <el-input style="width:360px" v-model.number="form.dataDiskGb" >
                        <el-select style="width:140px" v-model="form.dataDiskTitle" disabled slot="prepend">
                          <el-option :label="form.dataDiskTitle" :value="form.dataDiskTitle"></el-option>
                        </el-select>
                        <template slot="append">GB</template>
                      </el-input>
                      <el-tooltip class="item" effect="light" placement="top">
                        <template slot="content">
                          <span>自定义容量范围为{{currentProduct.dataDiskMin}}~{{currentProduct.dataDiskMax}}G</span>
                        </template>
                        <i class="icon-question tooltip-icon mgl10"></i>
                      </el-tooltip>
                  </el-form-item>
                  <template v-else-if="form.dataDiskType==='ebs_ssd'">
                    <el-form-item v-if="form.ebsDataDisks.length>0" v-for="(item,idx) in form.ebsDataDisks" :key="idx" :label="idx===0?'数据盘：':''" :prop="`ebsDataDisks[${idx}].size`" :rules="dataDiskRules">
                        <el-input style="width:360px" v-model.number="item.size" >
                          <el-select style="width:140px" v-model="form.dataDiskTitle" disabled slot="prepend">
                            <el-option :label="form.dataDiskTitle" :value="form.dataDiskTitle"></el-option>
                          </el-select>
                          <template slot="append">GB</template>
                        </el-input>
                        <el-tooltip class="item" effect="light" placement="top">
                          <template slot="content">
                            <span >容量范围为10~16000G</span>
                          </template>
                          <i class="icon-question tooltip-icon mgl10"></i>
                        </el-tooltip>
                        <el-button type="text" style="float:right;" @click="deleteEbsDataDisk(idx)">删除</el-button>
                    </el-form-item>
                    <el-form-item v-if="form.ebsDataDisks.length<3" :label="form.ebsDataDisks.length===0?'数据盘':''" >
                      <el-button type="plain" @click="addEbsDataDisk"><i class="fa fa-plus"> 增加一块</i></el-button>
                    </el-form-item>
                  </template>
                  <el-form-item label="购买数量：" prop="maxCount">
                    <el-input-number v-model.number="form.maxCount" :min="1" :max="49" ></el-input-number>
                  </el-form-item>
              </el-form>
              <el-form ref="form2" :model="form"   v-show="createStep===2" style="margin-top: 20px;font-family: sans-serif;" label-width="180px" size="small">
                <el-form-item label="绑定弹性IP：" >
                  <el-radio-group v-model="form.createEip" disabled>
                    <el-radio :label="true">购买新的弹性IP</el-radio>
                    <el-radio :label="false">稍后购买</el-radio>
                  </el-radio-group>
                  <div class="font-red">本平台暂不支持购买弹性IP</div>
                </el-form-item>
              </el-form>
              <el-form ref="form3" :model="form"   v-show="createStep===3" style="margin-top: 20px;font-family: sans-serif;" label-width="180px" size="small">
                <el-form-item label="关联VPC：" >
                  <el-select v-model="form.vpcId" style="width:350px;">
                    <el-option v-for="item in currentVpcs" :key="item.vpcId" :label="`${item.vpcName}（${item.cidrBlock}）`" :value="item.vpcId"></el-option>
                  </el-select>
                  <el-button type="text" @click="refreshCurrentVpcs"><i class="icon-refresh mgl10" :class="{'fa-spin': refreshVpcs}"></i></el-button>
                </el-form-item>
                <el-form-item label="关联子网：" >
                  <el-select v-model="form.subnetId" style="width:350px;">
                    <el-option v-if="currentSubnets&&currentSubnets.length>0" v-for="item in currentSubnets" :key="item.subnetId" :label="`${item.subnetName}（${item.cidrBlock}）`" :value="item.subnetId"></el-option>
                  </el-select>
                  <div class="font-red">只显示({{this.form.avaliableZone.name}})下的子网</div>
                </el-form-item>
                <el-form-item label="服务器IP：" >
                  <el-radio-group  v-model="form.isAutoIP">
                    <el-radio-button :label="true">自动分配</el-radio-button>
                    <el-radio-button v-if="form.maxCount===1" :label="false">指定IP</el-radio-button>
                  </el-radio-group>
                </el-form-item>
                <el-form-item prop="privateIpAddress" :show-message="false" :rules="privateIpAddressRules">
                  <ipv4-input v-if="!form.isAutoIP" :disableNum="3" v-model="form.privateIpAddress"></ipv4-input>
                  <div class="font-red" v-show="!ipAvailable">已被分配</div>
                </el-form-item>
                <el-form-item label="关联安全组(防火墙)：" >
                  <el-select v-model="form.securityGroupId" style="width:350px;">
                    <el-option v-if="currentSecurityGroups&&currentSecurityGroups.length>0" v-for="item in currentSecurityGroups" :key="item.securityGroupId" :label="item.securityGroupName" :value="item.securityGroupId"></el-option>
                  </el-select>
                </el-form-item>
              </el-form>
              <el-form ref="form4" :model="form" :rules="rules4"  v-show="createStep===4" style="margin-top: 20px;font-family: sans-serif;" label-width="180px" size="small">
                <el-form-item label="所属项目：" >
                  <el-select v-model="form.projectId" style="width:350px;">
                    <el-option v-for="item in projects" :key="item.projectId" :label="item.projectName" :value="item.projectId"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="服务器名称：" prop="instanceName" >
                  <el-input style="width:350px" v-model="form.instanceName" >
                  <div class="font-red" v-show="form.maxCount>1">可以通过添加后缀，为多台服务器创建不同的名称</div>
                </el-input>
                </el-form-item>
                <el-form-item label="后缀起始值：" v-if="form.maxCount>1" >
                  <el-input-number v-model="form.instanceNameSuffix" :min="0" :max="9999" controls-position="right" ></el-input-number>
                </el-input>
                </el-form-item>
                <el-form-item label="管理员账户">
                  <template v-if="currentImage&&currentImage.platform.indexOf('windows')>-1">kingsoft</template>
                  <template v-else-if="currentImage&&currentImage.platform.indexOf('ubuntu')>-1">ubuntu</template>
                  <template v-else>root</template>
                </el-form-item>
                <el-form-item label="管理员密码" prop="instancePassword">
                  <el-input type="password" style="width:350px" v-model="form.instancePassword"></el-input>
                  <el-tooltip class="item" effect="light" placement="top">
                    <template slot="content">
                      <span v-html>8-32个字符，必须包含大小写字母和数字，支持英文特殊字符!$%()*+,-./:;<=>?@[]^_`{|}~</span>
                    </template>
                    <i class="icon-question tooltip-icon mgl10"></i>
                  </el-tooltip>
                </el-form-item>
                <el-form-item label="确认密码" prop="pwdConfirm">
                  <el-input type="password" style="width:350px" v-model="form.pwdConfirm"></el-input>
                </el-form-item>
              </el-form>
            </div>
            <div class="col-xs-4">
              <div class="create-info">
                <div class="head">
                  <h3>云服务器</h3>
                  <span class="region"><span >{{regionMap[form.region]}} ({{form.avaliableZone.name}})</span></span>
                </div>
                <div  class="body">
                  <div  class="option-item c-clearfix ">
                    <label  class="option-name">计费方式:</label>
                    <div  class="option-group">{{chargeTypeMap[form.chargeType]}}</div>
                  </div>
                  <div  class="option-item c-clearfix ">
                    <label  class="option-name">配置:</label>
                    <div  class="option-group">{{form.cpu}}核 {{form.memory}}GB （{{instanceFamilys[form.instanceFamily]}} {{form.sriovNetSupport?'支持联网增强':''}}）</div>
                  </div>
                  <div  class="option-item c-clearfix ">
                    <label  class="option-name">数据盘:</label>
                    <div  class="option-group">
                      <div >1块{{form.sysDiskTitle}}系统盘（{{form.sysDiskGb}}GB）</div>
                      <div v-if="form.dataDiskType==='ssd'">1块{{form.dataDiskTitle}}数据盘（{{form.dataDiskGb}}GB）</div>
                      <div v-else v-for="item in form.ebsDataDisks">1块{{form.dataDiskTitle}}数据盘（{{item.size}}GB）</div>
                    </div>
                  </div>
                  <div  class="option-item c-clearfix ">
                    <label  class="option-name">镜像:</label>
                    <div  class="option-group">{{currentImage&&imageTypeKvm[currentImage.platform]}}</div>
                  </div>
                  <div  class="option-item c-clearfix ">
                    <label  class="option-name">购买量:</label>
                    <div  class="option-group">&nbsp;<span  class="text-warning">{{form.maxCount}}</span>台</div>
                  </div>
                  <p  class="text-muted charge-tip">提示：按月单位除以自然天数结算，实际以月账单为准</p>
                </div>
              </div>
            </div>
        </div>
      </div>
      <div class="footer-btns">
        <el-button type="primary" @click="previousStep" v-if="createStep!==1">上一步</el-button>
        <el-button type="primary" @click="nextStep" v-if="createStep!==4">下一步</el-button>
        <el-button type="primary" @click="confirmCreate" v-if="createStep===4">确定</el-button>
        <el-button type="plain" @click="confirmCancel">取消</el-button>
      </div>
    </div>
  </div>

</template>

<script>
import ipv4Input from '@/components/form/ipv4Input.vue'
import { mapActions, mapState, mapGetters } from 'vuex'
export default {
  components: {
    ipv4Input
  },
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else if (!/^[0-9a-zA-Z!$%()*+,-./:;<=>?@[\]^_`{|}~]{8,32}$/m.test(value) || !/[0-9]+/.test(value) || !/[a-z]+/.test(value) || !/[A-Z]+/.test(value)) {
        callback(new Error('请填写正确的密码格式'))
      } else {
        if (this.form.pwdConfirm !== '') {
          this.$refs['form4'].validateField('pwdConfirm')
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
      createStep: 1,
      refreshVpcs: false,
      currentZones: [],
      groupImages: [],
      currentImages: [],
      currentVpcs: [],
      currentSubnets: [],
      currentAvailableAddresses: [],
      currentSecurityGroups: [],
      diskStepValue: 10,
      form: {
        chargeType: 'Daily',
        region: 'cn-beijing-6',
        avaliableZone: {},
        serial: 1,
        instanceFamily: 'C1',
        sriovNetSupport: false,
        cpu: 8,
        memory: 32,
        imageType: 'system',
        imageId: '',
        osType: 'linux',
        maxCount: 1,
        sysDiskType: 'ssd',
        sysDiskTitle: '本地SSD盘',
        sysDiskGb: 20,
        dataDiskType: 'ssd',
        dataDiskTitle: '本地SSD盘',
        dataDiskGb: 0,
        ebsDataDisks: [],
        createEip: false,
        vpcId: '',
        subnetId: '',
        isAutoIP: true,
        privateIpAddress: '',
        securityGroupId: '',
        projectId: 0,
        instanceName: '',
        instanceNameSuffix: ''
      },
      rules4: {
        instancePassword: [
          { validator: validatePass, trigger: 'blur' },
          { min: 8, max: 32, message: '长度在 8 到 32 个字符', trigger: 'blur' }

        ],
        pwdConfirm: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        instanceName: [
          { required: true, message: '请填写服务器名称', trigger: 'change' }
        ]
      }
    }
  },
  watch: {
    'form.region': function (val) {
      this.searchInstanceFamilys(val)
      this.setCurrentZones(val)
      this.currentVpcs = []
    },
    'form.imageId': function (val) {
      let image = this.currentImage
      if (/win.*2008/i.test(image.platform)) {
        this.form.osType = 'Windows'
        this.form.sysDiskGb = 40
      } else if (/win.*/i.test(image.platform)) {
        this.form.osType = 'Windows'
        this.form.sysDiskGb = 50
      } else {
        this.form.osType = 'Linux'
        this.form.sysDiskGb = 20
      }
    },
    'form.instanceFamily': function (val) {
      if (/N\d+/i.test(val)) {
        this.form.sysDiskType = 'ebs_ssd'
        this.form.sysDiskTitle = '云硬盘(SSD)'
        this.form.dataDiskType = 'ebs_ssd'
        this.form.dataDiskTitle = '云硬盘(SSD)'
      } else {
        this.form.sysDiskType = 'ssd'
        this.form.sysDiskTitle = '本地SSD盘'
        this.form.dataDiskType = 'ssd'
        this.form.dataDiskTitle = '本地SSD盘'
      }
    },
    'form.vpcId': async function () {
      this.searchSubnets()
      this.searchSecurityGroups()
    },
    'form.subnetId': async function () {
      this.searchSubnetAvailableAddresses()
    },
    createStep: function (val) {
      if (val === 3 && (!this.currentVpcs || this.currentVpcs.length === 0)) {
        this.refreshCurrentVpcs()
      } else if (val === 4 && (!this.projects || this.projects.length === 0)) {
        this.searchProjects()
      }
    }
  },
  computed: {
    ...mapGetters('ksyun', ['imageTypeKvm', 'ebsNameMap', 'regionMap', 'chargeTypeMap']),
    ...mapState('ksyun', ['instanceTypeConfigs', 'regions', 'availabilityZones', 'instanceFamilys', 'images', 'projects']),
    dataDiskRules() {
      let min = this.form.dataDiskType === 'ebs_ssd' ? 10 : this.currentProduct.dataDiskMin
      let max = this.form.dataDiskType === 'ebs_ssd' ? 16000 : this.currentProduct.dataDiskMax
      let validator = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('数据盘大小不能为空'))
        } else if (Number(value) !== 0 && !Number(value)) {
          callback(new Error('数据盘大小必须为数字值'))
        } else if (value > max || value < min) {
          callback(new Error(`数据盘大小超出范围(${min}~${max}GB)`))
        } else {
          callback()
        }
      }
      return [{ validator: validator, trigger: 'blur' }]
    },
    privateIpAddressRules() {
      let validator = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('IP地址不能为空'))
        } else if (!this.ipAvailable) {
          callback(new Error('IP地址已分配'))
        } else {
          callback()
        }
      }
      return this.form.isAutoIP ? [] : [{ validator: validator, trigger: 'blur' }]
    },
    currentSerials() {
      let serials = []
      this.avaliableZoneConfigs.forEach(item => {
        let serial = Number(item.instanceFamily.replace(/[^0-9]/ig, ''))
        if (serials.indexOf(serial) < 0) {
          serials.push(serial)
        }
      })
      if (serials.length > 0 && serials.indexOf(this.form.serial) < 0) {
        this.form.serial = serials[0]
      }
      return serials.sort((a, b) => a > b)
    },
    currentInstanceFamilies() {
      let families = []
      this.avaliableZoneConfigs.forEach(item => {
        if (this.form.serial === Number(item.instanceFamily.replace(/[^0-9]/ig, '')) && families.indexOf(item.instanceFamily) < 0) {
          families.push(item.instanceFamily)
        }
      })
      if (families.length > 0 && families.indexOf(this.form.instanceFamily) < 0) {
        this.form.instanceFamily = families[0]
      }
      return families.sort((a, b) => a > b)
    },
    currentSriovNetSupports() {
      let supports = []
      this.avaliableZoneConfigs.forEach(item => {
        let support = Boolean(item.sriovNetSupport)
        if (this.form.instanceFamily === item.instanceFamily && supports.indexOf(support) < 0) {
          supports.push(support)
        }
      })
      if (supports.length > 0 && supports.indexOf(this.form.sriovNetSupport) < 0) {
        this.form.sriovNetSupport = supports[0]
      }
      return supports.sort((a, b) => a > b)
    },
    currentCpus() {
      let cpus = []
      this.avaliableZoneConfigs.forEach(item => {
        if (this.form.instanceFamily === item.instanceFamily && this.form.sriovNetSupport === Boolean(item.sriovNetSupport) && cpus.indexOf(item.cpu) < 0) {
          cpus.push(item.cpu)
        }
      })
      if (cpus.length > 0 && cpus.indexOf(this.form.cpu) < 0) {
        this.form.cpu = cpus[0]
      }
      return cpus.sort((a, b) => a > b)
    },
    currentMemories() {
      let memories = []
      this.avaliableZoneConfigs.forEach(item => {
        if (this.form.instanceFamily === item.instanceFamily &&
          this.form.sriovNetSupport === Boolean(item.sriovNetSupport) &&
          this.form.cpu === item.cpu &&
          memories.indexOf(item.memory) < 0) {
          memories.push(item.memory)
        }
      })
      if (memories.length > 0 && memories.indexOf(this.form.memory) < 0) {
        this.form.memory = memories[0]
      }
      return memories.sort((a, b) => a > b)
    },
    currentProduct() {
      let products = this.avaliableZoneConfigs.filter(item => {
        return this.form.instanceFamily === item.instanceFamily &&
          this.form.sriovNetSupport === Boolean(item.sriovNetSupport) &&
          this.form.cpu === item.cpu &&
          this.form.memory === item.memory
      })
      return (products && products[0]) || {}
    },
    avaliableZoneConfigs() {
      let zone = this.form.avaliableZone
      let configs = this.instanceTypeConfigs.filter(item => {
        let zoneSet = item.availabilityZoneSet.map(zoneItem => {
          return zoneItem.azCode
        }).filter(code => {
          return !zone || code === zone.availabilityZone
        })
        return zoneSet.length > 0
      })
      return configs || []
    },
    ipAvailable() {
      return this.form.isAutoIP || !this.form.privateIpAddress || this.currentAvailableAddresses.indexOf(this.form.privateIpAddress) > -1
    },
    currentImage: function () {
      return this.currentImages.filter(item => item.imageId === this.form.imageId)[0]
    }
  },
  methods: {
    ...mapActions('action', ['searchKsyunVpcs', 'searchKsyunSubnetByVpcId', 'searchKsyunSubnetAvailableAddresses', 'searchKsyunSecurityGroupsByVpcId', 'runKsyunKesInstance']),
    ...mapActions('ksyun', ['searchInstanceTypeConfigs', 'searchRegions', 'searchInstanceFamilys', 'searchAvailabilityZones', 'searchImagesByType', 'searchProjects']),
    async setCurrentZones(region) {
      if (!this.availabilityZones[region]) {
        await this.searchAvailabilityZones(region)
      }
      this.currentZones = this.availabilityZones[region]
      if (this.currentZones && this.currentZones.indexOf(this.form.avaliableZone) < 0) {
        this.form.avaliableZone = this.currentZones[0]
      }
    },
    async changeImageTypeEvent(value) {
      await this.getGroupSystemImage()
      if (this.images[value] && this.images[value].length > 0) {
        this.form.imageId = this.images[value][0].imageId
      } else {
        this.form.imageId = ''
      }
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
    async refreshCurrentVpcs() {
      this.refreshVpcs = true
      let response = await this.searchKsyunVpcs(this.form.region)
      if (~~response.code === 1) {
        this.currentVpcs = response.data
        this.form.vpcId = this.currentVpcs && this.currentVpcs[0] && this.currentVpcs[0].vpcId
      }
      this.refreshVpcs = false
    },
    async searchSubnetAvailableAddresses() {
      let response = await this.searchKsyunSubnetAvailableAddresses(this.form.subnetId)
      if (~~response.code === 1) {
        this.currentAvailableAddresses = response.data
        this.form.privateIpAddress = response.data[0]
      }
    },
    async searchSecurityGroups() {
      let response = await this.searchKsyunSecurityGroupsByVpcId(this.form.vpcId)
      if (~~response.code === 1) {
        this.currentSecurityGroups = response.data
        this.form.securityGroupId = this.currentSecurityGroups && this.currentSecurityGroups[0] && this.currentSecurityGroups[0].securityGroupId
      }
    },
    async searchSubnets() {
      let response = await this.searchKsyunSubnetByVpcId(this.form.vpcId)
      if (~~response.code === 1) {
        this.currentSubnets = response.data.filter(item => item.availabilityZoneName === this.form.avaliableZone.availabilityZone)
        this.form.subnetId = this.currentSubnets && this.currentSubnets[0] && this.currentSubnets[0].subnetId
      }
    },
    addEbsDataDisk() {
      if (this.form.ebsDataDisks.length < 3) {
        this.form.ebsDataDisks.push({ type: 'SSD2.0', size: 500, deleteWithInstance: true })
      }
    },
    deleteEbsDataDisk(idx) {
      this.form.ebsDataDisks.splice(idx, 1)
    },
    previousStep() {
      this.createStep--
    },
    nextStep() {
      this.$refs[`form${this.createStep}`].validate(async (valid, object) => {
        if (valid) {
          this.createStep++
        } else {
          this.showValidMsg(valid, object)
          return false
        }
      })
    },
    confirmCreate() {
      this.$refs[`form4`].validate(async (valid, object) => {
        if (valid) {
          if (!this.currentProduct) {
            this.$msg.error('当前提交信息错误：当前产品不存在')
            return false
          }
          this.form.instanceType = this.currentProduct.instanceType
          let response = await this.runKsyunKesInstance(this.form)
          if (~~response.code === 1) {
            this.$msg.success('创建云服务器实例成功！')
            // this.$router.push('/asset/ksyun/kec')
            this.$router.back()
          } else {
            this.$msg.error(response.message)
          }
        } else {
          this.showValidMsg(valid, object)
          return false
        }
      })
    },
    confirmCancel() {
      this.$confirm(`确定要放弃购买云服务器吗？`, '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(() => {
        this.$router.back()
      })
    },
    showValidMsg(valid, object) {
      let msg = ''
      for (let key in object) {
        let validArr = object[key]
        if (validArr.length > 0) {
          msg = validArr[0].message
          this.$msg.error(msg)
          return false
        }
      }
    }
  },
  async mounted() {
    this.searchInstanceTypeConfigs()
    this.searchRegions()
    this.searchInstanceFamilys(this.form.region)
    this.setCurrentZones(this.form.region)
    this.changeImageTypeEvent(this.form.imageType)
  }
}
</script>

<style lang="css" scoped>
.form-content{
  font-size: 12px;
  border-right: 1px dashed #979797;
  padding-right: 20px;
  overflow: visible;
  clear: both;
  box-sizing: border-box;
  height:100%;
  min-height: 680px;
}
.create-info {
  font-size: 14px;
  overflow: auto;
  color: #666;
  min-height: 680px;
}
.create-info .head{
  border-bottom: 1px solid #ebebeb;
}
.create-info .head h3 {
  margin: 0 20px 10px 0;
  display: inline-block;
}
.create-info .head .region {
  display: inline-block;
  margin-bottom: 8px;
  vertical-align: bottom;
}
.create-info .body {
  padding: 10px 0;
  margin-bottom: 10px;
}
.create-info .body .option-item {
  padding: 5px 0;
  display: table;
  position: relative;
}
.create-info .body .option-item .option-name {
  color: #666;
  text-transform: capitalize;
  display: table-cell;
  text-align: right;
  font-weight: 400;
  width: 80px;
  height: auto;
  line-height: normal;
  padding: 0;
  font-size: 12px;
}
.create-info .body .option-item .option-group {
  position: relative;
  padding-top: 1px;
  display: table-cell;
  padding-left: 10px;
}
.footer-btns{
  padding: 10px 40px;
  border-top: 1px dashed #979797;
  text-align: right;
}
.icon-refresh{
  font-size: 16px;
}
.charge-tip{
  font-size: 12px;
  margin: 10px auto;
}
</style>
