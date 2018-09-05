export const state = () => ({
  instanceTypeConfigs: [],
  regions: [],
  availabilityZones: {},
  instanceFamilys: {},
  images: {},
  projects: []
})

export const getters = {
  regionMap(state) {
    let regionMap = {}
    state.regions.forEach(item => {
      regionMap[item.region] = item.regionName
    })
    return regionMap
  },
  chargeTypeMap() {
    return {
      'Monthly': '包年包月',
      'Daily': '按日月结',
      'PostPaidByHour': '按小时月结'
    }
  },
  ebsNameMap() {
    return {
      'SSD': '云硬盘2.0(SSD)',
      'SATA': '云硬盘2.0(SATA)',
      'SSD3.0': '云硬盘3.0(SSD)'
    }
  },
  productTipMap() {
    return {
      40: ['适合各个行业通用计算需求，如：小型 Web 应用、中小型数据等。'],
      41: ['IO优化型I1实例是高磁盘IO的最佳选择，提供每秒数万次低延迟性随机 操作（IOPS）。', '适合于低延时、I/O密集型应用。'],
      45: ['适合于低延时、I/O密集型应用。'],
      42: ['采用Intel Xeon E5-2680 v3 (Haswell) 处理器，2.5GHz的主频，DDR4内存。', '建议用于游戏服务器、数据库服务器。'],
      80: ['采用Intel Xeon E5-2667 v4 (Broadwell) 处理器，3.2GHz的超高主频，DDR4内存。', '计算优化型C2实例是高计算性能和高并发读写的应用的最佳选择。', '建议用于大型游戏服务器、高性能计算以及其他计算密集型应用。'],
      78: ['采用Intel Xeon E5-2690 v4 (Broadwell) 处理器，2.6GHz的高主频，DDR4内存。', 'IO优化型I2实例是高磁盘IO的最佳选择，提供每秒数万次低延迟性随机 I/O 操作（IOPS）。', '适合于低延时、I/O密集型应用，建议用于游戏服务器、数据库服务器。'],
      43: ['存储优化型'],
      77: ['GPU通用计算型(P3)实例使用NVIDIA P40 型GPU加速卡单卡拥有12TFlops的单精度浮点运算能力和24GB显存。', '可以用于深度学习的训练与推理预测场景，也可以用于其他通用计算加速场景。'],
      101: ['GPU推理计算型P3I实例使用NVIDIA P4 型GPU加速卡，拥有单卡5.6TFlops的单精度浮点运算能力和8GB显存。', '可以用于深度学习的推理场景被称为推理加速器，也可以用于训练场景。'],
      110: ['2.7 GHz Intel Xeon® Platinum 8168 处理器，DDR4内存。', '高磁盘 IO 负载，低延迟高吞吐', '适用于MMOGPG、MOBA游戏前端等场景'],
      111: ['3.2 GHz Intel Xeon® Gold 6146  处理器，睿频高达3.6 GHz。DDR4内存。', '高计算性能和高并发读写', '适用于高负载数据库、高负载web等场景'],
      137: ['2.6GHz Intel Xeon E5-2690 v4 处理器，DDR4内存', '适合中小规模的企业级应用，数据库、缓存、搜索集群，以及中小型Web应用、手机应用等对资源没有特殊要求场景。'],
      142: ['GPU推理计算型P3IN使用NVIDIA P4型加速卡，拥有单卡5.6TFLOPS单精度浮点运算能力和8GB显存，可以用于深度学习的推理场景被称为推理加速器，也可用于训练场景'],
      125: ['GPU通用计算型P4V使用NVIDIA V100型加速卡，拥有单卡15TFLOPS单精度浮点运算能力以及125TFLOPS混合精度矩阵运算能力，16GB显存，使深度学习训练与推理过程有3X倍性能提升'],
      126: ['GPU图形计算型P4V使用AMD S7150型加速卡，拥有单卡3.77TFLOPS单精度浮点运算能力以及250 GFLOPS双精度浮点运算能力，8GB显存。', '能够轻松加速应用程序和处理复杂的计算工作流,适用于远程图形工作站、云游戏，云计算、VDI等虚拟领域使用场景。'],
      152: ['2.6 GHz Intel Xeon® Gold 6132 处理器，DDR4内存', '平衡的计算、内存和网络资源', '适用于各种类型和规模的企业级应用及数据库等场景']
    }
  },
  imageTypeKvm() {
    return {
      'debian-8.2': 'Debian-8.2 64位',
      'ubuntu-14.04': 'ubuntu-14.04 64位',
      'ubuntu-16.04': 'ubuntu-16.04 64位',
      'fedora-20': 'Fedora-20 64位',
      'windows-server_2008_r2_datacenter_64_en': 'Windows Server 2008 R2 Datacenter 64位英文版',
      'windows-server_2008_r2_datacenter_64_zh': 'Windows Server 2008 R2 Datacenter 64位中文版',
      'windows-server_2012_r2_datacenter_64_en': 'Windows Server 2012 R2 Datacenter 64位英文版',
      'windows-server_2012_r2_datacenter_64_zh': 'Windows Server 2012 R2 Datacenter 64位中文版',
      'centos-6.4': 'CentOS-6.4 64位',
      'centos-6.5': 'CentOS-6.5 64位',
      'centos-6.6': 'CentOS-6.6 64位',
      'centos-6.7': 'CentOS-6.7 64位',
      'centos-6.8': 'CentOS-6.8 64位',
      'centos-7.0': 'CentOS-7.0 64位',
      'centos-7.1': 'CentOS-7.1 64位',
      'centos-7.2': 'CentOS-7.2 64位',
      'centos-7.3': 'CentOS-7.3 64位'
    }
  }
}

export const actions = {
  async searchInstanceTypeConfigs({ commit, dispatch, state }) {
    if (!state.instanceTypeConfigs || state.instanceTypeConfigs.length === 0) {
      let response = await dispatch('action/searchKsyunKecInstanceTypeConfigs', {}, { root: true })
      if (~~response.code === 1) {
        commit('SET_INSTANCE_TYPE_CONFIG', response.data)
      }
    }
    return state.instanceTypeConfigs
  },
  async searchRegions({ commit, dispatch, state }) {
    if (!state.regions || state.regions.length === 0) {
      let response = await dispatch('action/searchKsyunKecRegions', {}, { root: true })
      if (~~response.code === 1) {
        let regions = response.data.sort((a, b) => {
          return a.regionName > b.regionName
        })
        commit('SET_REGIONS', regions)
      }
    }
    return state.regions
  },
  async searchInstanceFamilys({ commit, dispatch, state }, region) {
    let response = await dispatch('action/searchKsyunKecInstanceFamilys', region, { root: true })
    if (~~response.code === 1) {
      let instanceFamilys = state.instanceFamilys
      response.data.forEach(item => {
        if (!instanceFamilys[item.instanceFamily] ||
          instanceFamilys[item.instanceFamily].length > item.instanceFamilyName.length) {
          instanceFamilys[item.instanceFamily] = item.instanceFamilyName
        }
      })
      commit('SET_INSTANCE_FAMILYS', instanceFamilys)
    }
    return state.instanceFamilys
  },
  async searchAvailabilityZones({ commit, dispatch, state }, region) {
    if (!state.availabilityZones[region] || state.availabilityZones[region].length === 0) {
      let response = await dispatch('action/searchKsyunKecAvailabilityZones', region, { root: true })
      if (~~response.code === 1) {
        let availabilityZones = response.data.sort((a, b) => {
          return a.availabilityZone > b.availabilityZone
        }).map(item => {
          item.name = `可用区${item.availabilityZone.replace(item.region, '').toUpperCase()}`
          return item
        })
        commit('SET_AVAILABILITY_ZONES', { region, availabilityZones })
      }
    }
    return state.availabilityZones[region]
  },
  async searchImagesByType({ commit, dispatch, state }, imageType) {
    if (imageType && !state.images[imageType]) {
      let response = await dispatch('action/searchKsyunKecImagesByType', imageType, { root: true })
      if (~~response.code === 1) {
        if (response.data) {
          let images = response.data.sort((a, b) => a.name > b.name)
          commit('SET_IMAGES', { imageType, images })
        }
      }
    }
    return state.images[imageType]
  },
  async searchProjects({ commit, dispatch, state }) {
    if (!state.projects || state.projects.length === 0) {
      let response = await dispatch('action/searchKsyunAllProjectList', {}, { root: true })
      if (~~response.code === 1) {
        let result = response.data.sort((a, b) => {
          return a.projectId > b.projectId
        })
        commit('SET_PROJECTS', result)
      }
    }
    return state.projects
  }
}

export const mutations = {
  SET_INSTANCE_TYPE_CONFIG(state, configs) {
    state.instanceTypeConfigs = configs
  },
  SET_REGIONS(state, regions) {
    state.regions = regions
  },
  SET_AVAILABILITY_ZONES(state, { region, availabilityZones }) {
    state.availabilityZones[region] = availabilityZones
  },
  SET_INSTANCE_FAMILYS(state, instanceFamilys) {
    state.instanceFamilys = instanceFamilys
  },
  SET_IMAGES(state, { imageType, images }) {
    state.images[imageType] = images
  },
  SET_PROJECTS(state, projects) {
    state.projects = projects
  }
}
