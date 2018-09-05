export function dateFormat(time) {
  var myDate = new Date()
  var lw = new Date(myDate - 1000 * 60 * 60 * 24 * time)
  var lastY = lw.getFullYear()
  var lastM = lw.getMonth() + 1
  var lastD = lw.getDate()
  var startdate = lastY + '-' + (lastM < 10 ? '0' + lastM : lastM) + '-' + (lastD < 10 ? '0' + lastD : lastD)
  return startdate
}
