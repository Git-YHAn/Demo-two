export const assetsNameValidate = (rule, value, callback) => {
  var info = ''
  if (value === '') {
    callback(new Error('服务器名称不能为空！'))
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

export const sshAddressValidate = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('服务器地址不能为空！'))
  } else {
    var reg = /^((25[0-5]|2[0-4]\d|[1]{1}\d{1}\d{1}|[1-9]{1}\d{1}|\d{1})($|(?!\.$)\.)){4}$/
    if (!reg.test(value)) {
      callback(new Error('请输入正确的地址'))
    } else {
      callback()
    }
  }
}

export const innerIpValidate = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('内网IP不能为空！'))
  } else {
    var reg = /^((25[0-5]|2[0-4]\d|[1]{1}\d{1}\d{1}|[1-9]{1}\d{1}|\d{1})($|(?!\.$)\.)){4}$/
    if (!reg.test(value)) {
      callback(new Error('请输入正确的地址'))
    } else {
      callback()
    }
  }
}

export const outterIpValidate = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('外网IP不能为空！'))
  } else {
    var reg = /^((25[0-5]|2[0-4]\d|[1]{1}\d{1}\d{1}|[1-9]{1}\d{1}|\d{1})($|(?!\.$)\.)){4}$/
    if (!reg.test(value)) {
      callback(new Error('请输入正确的地址'))
    } else {
      callback()
    }
  }
}

export const sshPortValidate = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('端口号不能为空！'))
  } else {
    var reg = /^[0-9]+$/
    if (!reg.test(value)) {
      callback(new Error('只能填入数字！'))
    } else {
      callback()
    }
  }
}

export const hostAccountValidate = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('服务器账号不能为空！'))
  } else {
    var reg = /^[\w]+$/
    if (!reg.test(value)) {
      callback(new Error('请输入正确的账号！'))
    } else {
      callback()
    }
  }
}

export const execContentValidate = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('执行模板内容不能为空！'))
  } else {
    callback()
  }
}
