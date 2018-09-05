export const envNameValidate = (rule, value, callback) => {
  var info = ''
  if (value === '') {
    callback(new Error('环境名称不能为空！'))
  } else {
    var reg = /^[\w#-＿\u4e00-\u9fa5]+$/
    if (value && String(value).length > 20) {
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

export const envCodeValidate = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('环境编码不能为空！'))
  } else {
    var reg = /^[A-Za-z0-9]+$/
    if (!reg.test(value)) {
      callback(new Error('只能由数字和字母组成！'))
    } else {
      callback()
    }
  }
}

export const priorityValidate = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('优先级不能为空！'))
  } else {
    var reg = /^[0-9]+$/
    if (!reg.test(value)) {
      callback(new Error('只能填入数字！'))
    } else {
      callback()
    }
  }
}
