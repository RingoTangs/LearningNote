// prettier-ignore
module.exports = {
  root: true,
  parserOptions: {
    parser: 'babel-eslint',
    sourceType: 'module'
  },
  env: {
    browser: true,
    node: true,
    es6: true
  },

  // eslint:recommended 所有在规则页面被标记为 "√" 的规则将会默认开启 
  // http://eslint.cn/docs/rules/
  extends: ['plugin:vue/recommended', 'eslint:recommended'],            

  // add your custom rules here
  //it is base on https://github.com/vuejs/eslint-config-vue
  rules: {
    'vue/max-attributes-per-line': [2, { singleline: 10, multiline: { max: 1, allowFirstLine: false } }], // 每行最大的属性数, 单行10个, 多行1个
    'vue/singleline-html-element-content-newline': 'off',
    'vue/multiline-html-element-content-newline': 'off',
	'vue/html-self-closing': [2, { 'html': { 'void': 'always' } }], // HTML 标签自闭和
    'vue/name-property-casing': ['error', 'PascalCase'],          // export vue 组件时的名字使用驼峰
    'vue/no-v-html': 'off',
    'accessor-pairs': 2,                                          // 对象中的 get 和 set 要成对出现, 只有 set 没有 get 会报错
    'arrow-spacing': [2, { before: true, after: true }],          // 要求箭头函数的 "=>" 之前/之后 有空格
    'block-spacing': [2, 'always'],                               // 强制在代码块中开括号前和闭括号后有空格
    'brace-style': [2, '1tbs', { allowSingleLine: true }],        // 大括号风格要求 allowSingleLine: true 表示允许块的开括号和闭括号在同一行
    'camelcase': [0, { properties: 'always' }],                   // 属性名称为驼峰风格（忽略）
    'comma-dangle': [2, 'never'],                                 // 禁止使用拖尾逗号
    'comma-spacing': [2, { before: false, after: true }],         // 强制在逗号周围使用空格（e.g.  const a = 10, b = 1）
    'comma-style': [2, 'last'],                                   // 逗号风格（要求逗号要放在一行的结尾）
    'constructor-super': 2,                                       // 要求在构造函数中有 super() 的调用（配置文件中的 "extends": "eslint:recommended" 属性启用了此规则。）
    'curly': [2, 'multi-line'],                                   // 强制所有控制语句使用一致的括号风格（单行语句可以省略大括号）
    'dot-location': [2, 'property'],                              // 强制要求"点操作符"和"属性"放在同一行
    'eol-last': 2,                                                // 强制要求在非空文件末尾至少存在一行空行
    'eqeqeq': ['error', 'always', { null: 'ignore' }],            // 强制要求在任何情况下使用 === 和 !== （不要对 null 应用此规则）
    'generator-star-spacing': [2, { before: true, after: true }], // 强制 generator 函数中 * 号周围有空格
    'handle-callback-err': [2, '^(err|error)$'],                  // 强制回调错误处理（当函数的参数中出现 'err' 或 'error' 时, 该规则会报告有未处理的错误）
    'indent': [2, 2, { SwitchCase: 1 }],                          // 强制缩进 2 个字符（switch-case 缩进一个字符）
    'jsx-quotes': [2, 'prefer-single'],
    'key-spacing': [2, { beforeColon: false, afterColon: true }], // 强制在对象字面量的键和值之间使用一致的空格
    'keyword-spacing': [2, { before: true, after: true }],        // 强制在关键字前后使用一致的空格
    'new-cap': [2, { newIsCap: true, capIsNew: false }],          // 强制要求构造函数首字母大写
    'new-parens': 2,                                              // 使用无参构造器创建对象时必须加上小括号（e.g. const p = new Person()）
    'no-array-constructor': 2,                                    // 不允许使用 Array 的构造函数
    'no-caller': 2,                                               // 禁止使用 arguments.caller 和 arguments.callee
    'no-console': 0,                                              // 可以使用 console
    'no-class-assign': 2,                                         // 不允许修改类声明的变量（配置文件中的 "extends": "eslint:recommended" 属性启用了此规则。）
    'no-cond-assign': 2,                                          // 禁止在条件语句中出现赋值操作符（配置文件中的 "extends": "eslint:recommended" 属性启用了此规则。）
    'no-const-assign': 2,                                         // 禁止修改 const 声明的变量
    'no-control-regex': 0,                                        // 禁止在正则表达式中使用控制字符
    'no-delete-var': 2,                                           // 禁止使用 "delete" 删除变量或对象的属性
    'no-dupe-args': 2,                                            // 禁止在函数定义或表达中出现重名参数
    'no-dupe-class-members': 2,                                   // 禁止类成员中出现重复的名称
    'no-dupe-keys': 2,                                            // 禁止对象字面量中出现重复的 key
    'no-duplicate-case': 2,                                       // 该规则禁止在 switch 语句中的 case 子句中出现重复的测试表达式
    'no-empty-character-class': 2,                                // 禁止在正则表达式中使用空字符集
    'no-empty-pattern': 2,                                        // 禁止使用空解构模式
    'no-eval': 2,                                                 // 禁用 eval()
    'no-ex-assign': 2,                                            // 禁止对 catch 子句中的异常重新赋值
    'no-extend-native': 2,                                        // 禁止扩展原生对象（e.g. 不允许 Object.prototype.extra = 55）
    'no-extra-bind': 2,                                           // 禁止不必要的函数绑定 bind()
    'no-extra-boolean-cast': 2,                                   // 禁止不必要的布尔类型转换
    'no-extra-parens': [2, 'functions'],                          // 禁止冗余的括号（"functions" 只在 函数表达式周围禁止不必要的圆括号）
    'no-fallthrough': 2,                                          // 禁止 case 语句落空
    'no-floating-decimal': 2,                                     // 禁止浮点小数 （e.g. 这种 .7 禁用）
    'no-func-assign': 2,                                          // 禁止对 function 声明重新赋值。
    'no-implied-eval': 2,                                         // 禁止使用类似 eval() 的方法
    'no-inner-declarations': [2, 'functions'],                    // 规则要求函数声明和变量声明（可选的）在程序或函数体的顶部
    'no-invalid-regexp': 2,                                       // 禁止在 RegExp 构造函数中出现无效的正则表达式
    'no-irregular-whitespace': 2,                                 // 禁止不规则的空白
    'no-iterator': 2,                                             // 禁止使用 __iterator__ （该属性已经废弃）
    'no-label-var': 2,                                            // 禁用与变量同名的标签                                         
    'no-labels': [2, { allowLoop: false, allowSwitch: false }],   // 禁用 label
    'no-lone-blocks': 2,                                          // 禁用不必要的嵌套块
    'no-mixed-spaces-and-tabs': 2,                                // 禁止使用 空格 和 tab 混合缩进
    'no-multi-spaces': 2,                                         // 禁止使用多个空格
    'no-multi-str': 2,                                            // 禁止使用多行字符串
    'no-multiple-empty-lines': [2, { max: 1 }],                   // 两行代码之间最多一个空行
    'no-unsafe-negation': 2,                                      // 禁止对关系运算符的左操作数使用否定操作符                             
    'no-new-object': 2,                                           // 禁止使用 Object 构造函数
    'no-new-require': 2,                                          // 不允许 new require
    'no-new-symbol': 2,                                           // 禁止使用 new 操作符调用 Symbol
    'no-new-wrappers': 2,                                         // 禁止对 String，Number 和 Boolean 使用 new 操作符
    'no-obj-calls': 2,                                            // 禁止将 Math、JSON 和 Reflect 对象当作函数进行调用
    'no-octal': 2,                                                // 禁用八进制字面量
    'no-octal-escape': 2,                                         // 禁止在字符串字面量中使用八进制转义序列
    'no-path-concat': 2,                                          // 禁止对 __dirname 和 __filename 进行字符串连接
    'no-proto': 2,                                                // 禁用__proto__（已经弃用）使用 Object.getPrototypeOf() 和 Object.setPrototypeOf() 代替。
    'no-redeclare': 2,                                            // 禁止重新声明变量
    'no-regex-spaces': 2,                                         // 禁止在正则表达式字面量中出现多个空格。
    'no-return-assign': [2, 'except-parens'],                     // 禁止在返回语句中赋值（禁止出现赋值语句，除非使用括号把它们括起来）
    'no-self-assign': 2,                                          // 禁止自身赋值
    'no-self-compare': 2,                                         // 禁止自身比较
    'no-sequences': 2,                                            // 不允许使用逗号操作符
    'func-call-spacing': 2,                                       // 禁止在函数名和开括号之间有空格
    'no-sparse-arrays': 2,                                        // 禁止使用稀疏数组，也就是逗号之前没有任何元素的数组。
    'no-this-before-super': 2,                                    // 禁止在构造函数中，在调用 super() 之前使用 this 或 super
    'no-throw-literal': 2,                                        // 禁止抛出异常字面量（e.g. throw new Error('errMessage')）
    'no-trailing-spaces': 2,                                      // 禁用行尾空白
    'no-undef': 2,                                                // 禁用未声明的变量 
    'no-undef-init': 2,                                           // 禁止将变量初始化为 undefined
    'no-unmodified-loop-condition': 2,                            // 禁用一成不变的循环条件
    'no-unneeded-ternary': [2, { defaultAssignment: false }],     // 当有更简单的结构可以代替三元操作符时，该规则禁止使用三元操作符。
    'no-unreachable': 2,                                          // 禁止在 return、throw、continue 和 break 语句后出现不可达代码。
    'no-unsafe-finally': 2,                                       // 禁止在 finally 语句块中出现 return、throw、break 和 continue 语句
    'no-unused-vars': [1, { vars: 'all', args: 'all' }],          // 消除未使用的变量、函数和函数参数（vars: 'all' 检查所有变量; args: 'none' 不检查参数）。
    'no-useless-call': 2,                                         // 禁止对函数 call() 和 apply() 进行不必要的调用 
    'no-useless-computed-key': 2,                                 // 禁止在对象中使用不必要的计算属性。（e.g. const obj = { [prefix + "bar"]: 9 }）
    'no-useless-constructor': 2,                                  // 禁用不必要的构造函数
    'no-useless-escape': 0,                                       // 禁用不必要的转义
    'no-whitespace-before-property': 2,                           // 禁止在点号周围或对象属性之前的左括号前出现空白
    'no-with': 2,                                                 // 禁用 with 语句
    'one-var': [2, { initialized: 'never' }],                     // 强制声明变量时要初始化就分多行, 没有初始化就可以在一行定义
    'operator-linebreak': [2, 'after', { overrides: { '?': 'before', ':': 'before' } }],  // 要求把换行符放在操作符后面
    'padded-blocks': [2, 'never'],                                // 禁止块语句和类的开始或末尾有空行
    'quotes': [2, 'single', { avoidEscape: true, allowTemplateLiterals: true }],          // 强制使用一致的反勾号、双引号或单引号
    'semi': [2, 'never'],                                         // 禁止在语句末尾使用分号
    'semi-spacing': [2, { before: false, after: true }],          // 分号前没有空格, 分号后可以有空格
    'space-before-blocks': [2, 'always'],                         // "always"，块语句必须总是至少有一个前置空格。
    'space-before-function-paren': [2, { anonymous: 'always', named: 'never', asyncArrow: 'always' }], // 函数括号之前的空格（匿名函数和箭头函数有, 具名函数没有）
    'space-in-parens': [2, 'never'],                              // 强制圆括号内没有空格
    'space-infix-ops': 2,                                         // 要求操作符周围有空格
    'space-unary-ops': [2, { words: true, nonwords: false }],     /* 强制 words 一元操作符后空格和 nonwords 一元操作符之前或之后的空格的一致性; 
                                                                     words - 适用于单词类一元操作符，例如：new、delete、typeof、void、yield; 
                                                                     nonwords - 适用于这些一元操作符: -、+、--、++、!、!!
                                                                   */  
    'spaced-comment': [2, 'always', { markers: ['global', 'globals', 'eslint', 'eslint-disable', '*package', '!', ','] }], // 强制注释中 // 或 /* 后空格的一致性
    'template-curly-spacing': [2, 'never'],                       // 强制模板字面量中不使用空格 （e.g. `hello, ${people.name}!`）
    'use-isnan': 2,                                               // 要求使用 Number.isNaN() 检查 NaN （Not a Number）
    'valid-typeof': 2,                                            // 强制 typeof 表达式与有效的字符串进行比较
    'wrap-iife': [2, 'any'],                                      // 要求所有的立即执行函数表达式使用括号包裹起来
    'yield-star-spacing': [2, 'both'],                            // 强制在 yield "*" 两边都加空格
    'yoda': [2, 'never'],                                         // 强制 if (color === 'red')
    'prefer-const': 2,                                            // 要求使用 const 声明那些声明后不再被修改的变量
    'no-debugger': process.env.NODE_ENV === 'production' ? 2 : 0, // 禁用 debugger
    'object-curly-spacing': [2, 'always'],                        // 要求对象花括号内有空格
    'array-bracket-spacing': [2, 'never']                         // 禁止在数组括号内出现空格
  }
}
