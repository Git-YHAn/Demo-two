export const usernameValidate = (rule, value, callback) => {
  var info = ''
  if (value === '') {
    callback(new Error('用户名不能为空！'))
  } else {
    var reg = /^[a-zA-Z0-9]+$/
    var len = String(value).length
    if (len < 5) {
      info = info + '长度不能少于5位！'
      // callback(new Error('用户名长度不能少于6位'))
    }
    if (len > 12) {
      info = info + '长度不能高于12位！'
      // callback(new Error('用户名长度不能高于10位'))
    }
    if (!reg.test(value)) {
      info = info + '只能由数字和字母组成！'
      // callback(new Error('用户名只能由数字和字母组成'))
    }
    if (info !== '') {
      callback(new Error(info))
    } else {
      callback()
    }
  }
}
export const realNameValidate = (rule, value, callback) => {
  var info = ''
  if (value === '') {
    callback(new Error('用户姓名不能为空！'))
  } else {
    var reg = /^([\u4e00-\u9fa5]+|[a-zA-Z\s]+)$/
    var len = String(value).length
    if (len < 2) {
      info = info + '长度不能少于2位！'
      // callback(new Error('长度不能少于2位'))
    }
    if (len > 20) {
      info = info + '长度不能高于20位！'
      // callback(new Error('长度不能高于20位'))
    }
    if (!reg.test(value)) {
      info = info + '只能由汉字或者字母组成！'
      // callback(new Error('只能由汉字或者字母组成'))
    }
    if (info !== '') {
      callback(new Error(info))
    } else {
      callback()
    }
  }
}
export const passwordValidate = (rule, value, callback) => {
  var info = ''
  if (value === '') {
    callback(new Error('登陆密码不能为空！'))
  } else {
    var reg = /^.*(?=.{6,})[A-Za-z](?=.*[0-9])(?=.*[~!@#$%^&*_=;:,./<>?]).*$/
    if (!reg.test(value)) {
      info = info + '密码长度最少6位，必须以字母开头，并包含数字和特殊字符'
    }
    if (info !== '') {
      callback(new Error(info))
    } else {
      callback()
    }
  }
}
export const emailValidate = (rule, value, callback) => {
  if (value === '') {
    callback()
  } else {
    var reg = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$/
    if (!reg.test(value)) {
      callback(new Error('用户邮箱格式错误！'))
    } else {
      callback()
    }
  }
}
export const mobileValidate = (rule, value, callback) => {
  if (value === '') {
    callback()
  } else {
    var reg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/
    if (!reg.test(value)) {
      callback(new Error('联系电话格式错误！'))
    } else {
      callback()
    }
  }
}
