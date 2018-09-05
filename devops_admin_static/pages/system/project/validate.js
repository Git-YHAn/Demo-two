export const proNameValidate = (rule, value, callback) => {
  var info = ''
  if (value === '') {
    callback(new Error('项目名称不能为空！'))
  } else {
    var reg = /^[\w#-＿\u4e00-\u9fa5]+$/
    var len = String(value).length
    if (len > 20) {
      info = info + '长度不能高于20位！'
    }
    if (!reg.test(value)) {
      info = info + '只能由数字和字母和#-_组成！'
    }
    if (info !== '') {
      callback(new Error(info))
    } else {
      callback()
    }
  }
}

export const proCodeValidate = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('项目编码不能为空！'))
  } else {
    var reg = /^[A-Za-z0-9]+$/
    if (!reg.test(value)) {
      callback(new Error('只能由数字和字母组成！'))
    } else {
      callback()
    }
  }
}

export const appNameValidate = (rule, value, callback) => {
  var info = ''
  if (value === '') {
    callback(new Error('应用名称不能为空！'))
  } else {
    var reg = /^[\w#-＿\u4e00-\u9fa5]+$/
    var len = String(value).length
    if (len > 20) {
      info = info + '长度不能高于20位！'
    }
    if (!reg.test(value)) {
      info = info + '只能由数字和字母和#-_组成！'
    }
    if (info !== '') {
      callback(new Error(info))
    } else {
      callback()
    }
  }
}

export const appCodeValidate = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('应用编码不能为空！'))
  } else {
    var reg = /^[A-Za-z0-9]+$/
    if (!reg.test(value)) {
      callback(new Error('只能由数字和字母组成！'))
    } else {
      callback()
    }
  }
}
