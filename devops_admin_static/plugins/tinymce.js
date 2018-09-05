import Vue from 'vue'
import tinymce from 'vue-tinymce-editor'
tinymce.props = {
  id: {
    type: String,
    required: true
  },
  htmlClass: { default: '', type: String },
  value: { default: '' },
  plugins: {
    default: function () {
      return [
        'advlist autolink lists link image charmap print preview hr anchor pagebreak',
        'searchreplace wordcount visualblocks visualchars code fullscreen',
        'insertdatetime media nonbreaking save table contextmenu directionality',
        'template paste textcolor colorpicker textpattern imagetools toc help hr'
      ]
    },
    type: Array
  },
  toolbar1: { default: 'code template | insertdatetime link image media print | charmap nonbreaking | alignleft aligncenter alignright alignjustify | colorpicker table', type: String },
  toolbar2: { default: 'formatselect | bold italic  strikethrough underline forecolor backcolor superscript subscript hr | numlist bullist outdent indent  | removeformat', type: String },
  other_options: {
    default: function () {
      return {
        height: 300,
        menubar: false,
        language_url: '/js/tinymce/langs/zh_CN.js'
      }
    },
    type: Object
  },
  readonly: { default: false, type: Boolean }
}
Vue.component('tinymce', tinymce)
