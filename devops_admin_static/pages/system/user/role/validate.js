export const roleNameValidate = (rule, value, callback) => {
  var info = ''
  if (value === '') {
    callback(new Error('角色名不能为空！'))
  } else {
    var reg = /^[\w#-＿\u4e00-\u9fa5]+$/
    var len = String(value).length
    if (len < 3) {
      info = info + '角色名长度不能少于3位！'
    }
    if (len > 20) {
      info = info + '角色名长度不能高于20位！'
    }
    if (!reg.test(value)) {
      info = info + '角色名只能由汉字组成！'
    }
    if (info !== '') {
      callback(new Error(info))
    } else {
      callback()
    }
  }
}
export const descValidate = (rule, value, callback) => {
  if (value === '') {
    callback()
  } else {
    var len = String(value).length
    if (len > 50) {
      callback(new Error('描述过长，50个字以内！'))
    } else {
      callback()
    }
  }
}
