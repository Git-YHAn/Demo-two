import fileDownload from 'js-file-download'

export default function ({
  store,
  $axios,
  redirect
}) {
  $axios.onRequest(config => {
    if (typeof window === 'object') {
      config.headers.common['Authorization'] = store.state.token
    }
  })

  $axios.onResponse(response => {
    if (response.headers.authorization) {
      store.commit('SET_TOKEN', response.headers.authorization)
    }
    if (response.config.responseType === 'blob') {
      jsDownLoadFile(response)
    } else if (response.headers && (response.headers['content-type'] === 'application/x-msdownload' || response.headers['content-type'] === 'application/octet-stream')) {
      iframeDownLoadFile(response)
    }
  })

  function iframeDownLoadFile(response) {
    if (typeof document === 'object') {
      let iframe = document.createElement('iframe')
      iframe.style.display = 'none'
      iframe.src = response.request.responseURL
      iframe.onload = function () {
        document.body.removeChild(iframe)
      }
      document.body.appendChild(iframe)
    }
  }

  async function jsDownLoadFile(response) {
    let resBlob = response.data // <--- store the blob if it is
    let resData = null
    try {
      let resText = await new Promise((resolve, reject) => {
        let reader = new FileReader()
        reader.addEventListener('abort', reject)
        reader.addEventListener('error', reject)
        reader.addEventListener('loadend', () => {
          resolve(reader.result)
        })
        reader.readAsText(resBlob)
      })
      resData = JSON.parse(resText) // <--- try to parse as json evantually
    } catch (err) {}

    if (resData) {
      if (resData.error) { // handle error
      } else { // handle data
      }
    } else { // handle blob
      fileDownload(resBlob, response.headers.filename)
    }
  }

  // $axios.onRequestError(err => {
  // console.error(err)
  // })

  // $axios.onResponseError(err => {
  // console.error(err)
  // })

  $axios.onError(error => {
    const code = parseInt(error.response && error.response.status)
    if (code === 403 || code === 401) {
      if (typeof window === 'object') {
        store.commit('SET_TOKEN', null)
        redirect('/login')
      }
    }
  })
}
