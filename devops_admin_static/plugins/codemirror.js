import Vue from 'vue'
import VueCodemirror from 'vue-codemirror'

// google DiffMatchPatch
import DiffMatchPatch from 'diff-match-patch'
// language
import 'codemirror/mode/javascript/javascript.js'
import 'codemirror/mode/xml/xml.js'
import 'codemirror/mode/diff/diff.js'
import 'codemirror/mode/properties/properties.js'
// theme css
import 'codemirror/theme/monokai.css'
// require active-line.js
import 'codemirror/addon/selection/active-line.js'
// styleSelectedText
import 'codemirror/addon/selection/mark-selection.js'
import 'codemirror/addon/search/searchcursor.js'
// hint
import 'codemirror/addon/hint/show-hint.js'
import 'codemirror/addon/hint/show-hint.css'
import 'codemirror/addon/hint/javascript-hint.js'
import 'codemirror/addon/hint/xml-hint.js'
// highlightSelectionMatches
import 'codemirror/addon/scroll/annotatescrollbar.js'
import 'codemirror/addon/search/matchesonscrollbar.js'
import 'codemirror/addon/search/match-highlighter.js'
// keyMap
import 'codemirror/mode/clike/clike.js'
import 'codemirror/addon/edit/matchbrackets.js'
import 'codemirror/addon/comment/comment.js'
import 'codemirror/addon/dialog/dialog.js'
import 'codemirror/addon/dialog/dialog.css'
import 'codemirror/addon/search/search.js'
import 'codemirror/keymap/sublime.js'
// merge css
import 'codemirror/addon/merge/merge.css'
// merge js
import 'codemirror/addon/merge/merge.js'
// foldGutter
// import 'codemirror/addon/fold/foldgutter.css'
// import 'codemirror/addon/fold/brace-fold.js'
// import 'codemirror/addon/fold/comment-fold.js'
// import 'codemirror/addon/fold/foldcode.js'
// import 'codemirror/addon/fold/foldgutter.js'
// import 'codemirror/addon/fold/indent-fold.js'
// import 'codemirror/addon/fold/markdown-fold.js'
// import 'codemirror/addon/fold/xml-fold.js'

// more...

// DiffMatchPatch config with global
window.diff_match_patch = DiffMatchPatch
window.DIFF_DELETE = -1
window.DIFF_INSERT = 1
window.DIFF_EQUAL = 0

Vue.use(VueCodemirror, {
  options: {
    tabSize: 4,
    styleActiveLine: true,
    lineNumbers: true,
    styleSelectedText: false,
    line: true,
    // foldGutter: true,
    // gutters: ['CodeMirror-linenumbers', 'CodeMirror-foldgutter'],
    highlightSelectionMatches: { showToken: /\w/, annotateScrollbar: true },
    mode: 'text/javascript',
    // hint.js options
    hintOptions: {
      // 当匹配只有一项的时候是否自动补全
      completeSingle: false
    },
    // 快捷键 可提供三种模式 sublime、emacs、vim
    keyMap: 'sublime',
    // matchBrackets: true,
    showCursorWhenSelecting: true,
    theme: 'monokai',
    extraKeys: { 'Ctrl': 'autocomplete' }
  }
})
