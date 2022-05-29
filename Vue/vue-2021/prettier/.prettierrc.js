// prettier-ignore
// https://www.cnblogs.com/jiaoshou/p/12004077.html

// 该配置文件会继承 editorConfig 的配置
// 使用 prettier 需要安装 vscode 插件 Prettier-Code formatter
module.exports = {
  //useEditorConfig: false,       // 不使用 EditorConfig 的配置
  printWidth: 300,              // 指定代码的最佳长度超过则换行
  semi: false,                  // 行尾不要分号
  singleQuote: true,            // 使用单引号
  trailingComma: 'none',        // 末尾不需要逗号
  tabWidth: 2,                  // 使用 2 个空格缩进
  useTabs: false,               // 不使用缩进符，而使用空格
  quoteProps: 'as-needed',      // 对象的 key 仅在必要时用引号
  jsxSingleQuote: false,        // jsx 不使用单引号，而使用双引号
  bracketSpacing: true,         // 大括号内的首尾需要空格
  jsxBracketSameLine: false,    // jsx 标签的反尖括号需要换行
  arrowParens: 'always',        // 箭头函数，只有一个参数的时候，也需要括号
  endOfLine: 'lf',              // 换行符使用 lf
}
