const CompressionPlugin = require('compression-webpack-plugin')
const baseURL = 'http://192.168.100.80:18001'
const wsURL = 'http://192.168.100.80:18003'

module.exports = {
  env: {
    wsURL: wsURL
  },
  axios: {
    baseURL: baseURL,
    debug: false,
    // https: true,
    credentials: true,
    progress: true
    // proxy: true,
    // retry: { retries: 3 }
  },
  // proxy: {
  //   '/api/': 'http://api.example.com',
  //   '/api2/': 'http://api.another-website.com'
  // },
  /*
   ** Headers of the page
   */
  head: {
    title: 'DEVOPS管理平台',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=0,minimal-ui' },
      { hid: 'description', name: 'description', content: 'Nuxt.js project' },
      { name: 'msapplication-tap-highlight', content: 'no' },
      { name: 'apple-mobile-web-app-capable', content: 'yes' },
      { name: 'apple-mobile-web-app-status-bar-style', content: 'black' },
      { name: 'format-detection', content: 'telephone=no' },
      { name: 'renderer', content: 'webkit' },
      { name: 'HandheldFriendly', content: 'true' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },
  /*
   ** Global CSS
   */
  css: [
    'element-ui/lib/theme-chalk/index.css',
    'element-ui/lib/theme-chalk/display.css',
    'codemirror/lib/codemirror.css',
    'codemirror/addon/merge/merge.css',
    'codemirror/theme/base16-dark.css',
    '~assets/css/bootstrap.css',
    '~assets/css/simple-line-icons.css',
    '~assets/css/components-md.css',
    '~assets/css/layout.css',
    '~assets/css/login.css',
    '~assets/css/themes/darkblue.css',
    '~assets/css/custom.css',
    '~assets/css/main.css'
    // '~assets/css/main.css'
  ],
  /*
   ** Customize the progress-bar color
   */
  loading: { color: '#43C414' },
  /**
   * modules
   */
  modules: [
    '@nuxtjs/axios',
    '@nuxtjs/font-awesome'
  ],
  /**
   * Use this option to customize vue SSR bundle renderer. This option is skipped for spa mode.
   */
  render: {
    bundleRenderer: {
      directives: {
        auth: function (node, dir) {
          node.data.style.display = 'none'
        }
      }
    }
  },
  /**
   * plugins
   */
  plugins: [
    { src: '~/plugins/element-ui', ssr: true },
    { src: '~/plugins/components', ssr: true },
    { src: '~/plugins/directives', ssr: true },
    { src: '~/plugins/axios', ssr: true },
    { src: '~/plugins/socketio', ssr: false },
    { src: '~/plugins/localStorage', ssr: false },
    { src: '~/plugins/codemirror', ssr: false },
    { src: '~/plugins/tinymce', ssr: false },
    { src: '~/plugins/echarts', ssr: false }
  ],
  /**
   * router
   */
  router: {
    middleware: 'stats'
  },
  /*
   ** Build configuration
   */
  build: {
    vendor: [
      'axios'
      // 'element-ui'
    ],
    babel: {
      plugins: [
        [
          'component', [{
              libraryName: 'element-ui',
              styleLibraryName: 'theme-chalk'
            },
            'transform-async-to-generator',
            'transform-runtime'
          ]
        ]
      ]
    },
    loaders: [{
        test: /\.css$/,
        loader: 'vue-style-loader!css-loader'
      },
      {
        test: /\.(png|jpe?g|gif|svg)$/,
        loader: 'url-loader',
        query: {
          limit: 1000, // 1KO
          name: 'img/[name].[hash:7].[ext]'
        }
      },
      {
        test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
        loader: 'url-loader',
        query: {
          limit: 1000, // 1 KO
          name: 'fonts/[name].[hash:7].[ext]'
        }
      }
    ],
    postcss: [
      require('autoprefixer')({
        browsers: ['last 3 versions']
      })
    ],
    /*
     ** Run ESLINT on save
     */
    extend(config, ctx) {
      if (ctx.isClient) {
        config.module.rules.push({
          enforce: 'pre',
          test: /\.(js|vue)$/,
          loader: 'eslint-loader',
          exclude: /(node_modules)/
        })
      }
    },
    plugins: [
      new CompressionPlugin({
        asset: "[path].gz[query]",
        algorithm: "gzip",
        test: /\.js$|\.html|\.css$/,
        threshold: 10240,
        minRatio: 0.8
      })
    ]
  }
}
