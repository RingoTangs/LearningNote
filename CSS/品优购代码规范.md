# 代码规范

## 1. 概述

欢迎使用品优购代码规范， 这个是我借鉴京东前端代码规范，组织的品优购内部规范。旨在增强团队开发协作、提高代码质量和打造开发基石的编码规范，

以下规范是团队基本约定的内容，必须严格遵循。

#### HTML规范

基于 [W3C](http://www.w3.org/)、[苹果开发者](https://developer.apple.com/) 等官方文档，并结合团队业务和开发过程中总结的规范约定，让页面HTML代码更具语义性。

#### 图片规范

了解各种图片格式特性，根据特性制定图片规范，包括但不限于图片的质量约定、图片引入方式、图片合并处理等，旨在从图片层面优化页面性能。

#### CSS规范

统一规范团队 CSS 代码书写风格和使用 CSS 预编译语言语法风格，提供常用媒体查询语句和浏览器私有属性引用，并从业务层面统一规范常用模块的引用。

#### 命名规范

从 `目录`、`图片`、`HTML/CSS文件`、`ClassName` 的命名等层面约定规范团队的命名习惯，增强团队代码的可读性。

##  2. HTML 规范

###  DOCTYPE 声明

HTML文件必须加上 DOCTYPE 声明，并统一使用 HTML5 的文档声明：
~~~html
<!DOCTYPE html>
~~~

**HTML5标准模版**

```html
<!DOCTYPE html>
  <html lang="zh-CN">
  <head>
  <meta charset="UTF-8">
  <title>HTML5标准模版</title>
  </head>
  <body>

  </body>
</html>
```

### 页面语言lang

推荐使用属性值 `cmn-Hans-CN`（简体, 中国大陆），但是考虑浏览器和操作系统的兼容性，目前仍然使用 `zh-CN` 属性值

```
<html lang="zh-CN">
```

更多地区语言参考：

```
zh-SG 中文 (简体, 新加坡)   对应 cmn-Hans-SG 普通话 (简体, 新加坡)
zh-HK 中文 (繁体, 香港)     对应 cmn-Hant-HK 普通话 (繁体, 香港)
zh-MO 中文 (繁体, 澳门)     对应 cmn-Hant-MO 普通话 (繁体, 澳门)
zh-TW 中文 (繁体, 台湾)     对应 cmn-Hant-TW 普通话 (繁体, 台湾)
```

### charset 字符集合

一般情况下统一使用 “UTF-8” 编码

```
<meta charset="UTF-8">
```

由于历史原因，有些业务可能会使用 “GBK” 编码

```
<meta charset="GBK">
```

请尽量统一写成标准的 “UTF-8”，不要写成 “utf-8” 或 “utf8” 或 “UTF8”。根据 [IETF对UTF-8的定义](http://www.ietf.org/rfc/rfc3629)，其编码标准的写法是 “UTF-8”；而 UTF8 或 utf8 的写法只是出现在某些编程系统中，如 .NET framework 的类 System.Text.Encoding 中的一个属性名就叫 UTF8。

### 书写风格
#### HTML代码大小写

HTML标签名、类名、标签属性和大部分属性值统一用小写

*推荐：*

```
<div class="demo"></div>
```

*不推荐：*

```
<div class="DEMO"></div>
	
<DIV CLASS="DEMO"></DIV>
```
### 类型属性

不需要为 CSS、JS 指定类型属性，HTML5 中默认已包含

*推荐：*

```
<link rel="stylesheet" href="" >
<script src=""></script>
```

*不推荐：*

```
<link rel="stylesheet" type="text/css" href="" >
<script type="text/javascript" src="" ></script>
```

### 元素属性

- 元素属性值使用双引号语法
- 元素属性值可以写上的都写上

*推荐：*

```
<input type="text">
<input type="radio" name="name" checked="checked" >
```

*不推荐：*

```
<input type=text>	
<input type='text'>
<input type="radio" name="name" checked >
```
### 特殊字符引用
文本可以和字符引用混合出现。这种方法可以用来转义在文本中不能合法出现的字符。

在 HTML 中不能使用小于号 “<” 和大于号 “>”特殊字符，浏览器会将它们作为标签解析，若要正确显示，在 HTML 源代码中使用字符实体

*推荐：*

```
<a href="#">more&gt;&gt;</a>
```

*不推荐：*

```
<a href="#">more>></a>
```

### 代码缩进

统一使用四个空格进行代码缩进，使得各编辑器表现一致（各编辑器有相关配置）

```
<div class="jdc">
    <a href="#"></a>
</div>
```
### 代码嵌套

元素嵌套规范，每个块状元素独立一行，内联元素可选

*推荐：*

```
<div>
    <h1></h1>
    <p></p>
</div>	
<p><span></span><span></span></p>
```

*不推荐：*

```
<div>
    <h1></h1><p></p>
</div>	
<p> 
    <span></span>
    <span></span>
</p>
```

段落元素与标题元素只能嵌套内联元素

*推荐：*

```
<h1><span></span></h1>
<p><span></span><span></span></p>
```

*不推荐：*

```
<h1><div></div></h1>
<p><div></div><div></div></p>
```
## 3. 图片规范

### 内容图

内容图多以商品图等照片类图片形式存在，颜色较为丰富，文件体积较大

- 优先考虑 JPEG 格式，条件允许的话优先考虑 WebP 格式
- 尽量不使用PNG格式，PNG8 色位太低，PNG24 压缩率低，文件体积大
- **PC平台单张的图片的大小不应大于 200KB。**

### 背景图

背景图多为图标等颜色比较简单、文件体积不大、起修饰作用的图片

- PNG 与 GIF 格式，优先考虑使用 PNG 格式,PNG格式允许更多的颜色并提供更好的压缩率
- 图像颜色比较简单的，如纯色块线条图标，优先考虑使用 PNG8 格式，避免不使用 JPEG 格式
- 图像颜色丰富而且图片文件不太大的（40KB 以下）或有半透明效果的优先考虑 PNG24 格式
- 图像颜色丰富而且文件比较大的（40KB - 200KB）优先考虑 JPEG 格式
- 条件允许的，优先考虑 WebP 代替 PNG 和 JPEG 格式

## 4. CSS规范

### 代码格式化

样式书写一般有两种：一种是紧凑格式 (Compact)

```
.jdc{ display: block;width: 50px;}
```

一种是展开格式（Expanded）

```
.jdc {
    display: block;
    width: 50px;
}
```

**团队约定**

统一使用展开格式书写样式

### 代码大小写

样式选择器，属性名，属性值关键字全部使用小写字母书写，属性字符串允许使用大小写。

```
/* 推荐 */
.jdc{
	display:block;
}
	
/* 不推荐 */
.JDC{
	DISPLAY:BLOCK;
}
```

### 选择器

- 尽量少用通用选择器 `*`
- 不使用 ID 选择器
- 不使用无具体语义定义的标签选择器

```css
/* 推荐 */
.jdc {}
.jdc li {}
.jdc li p{}

/* 不推荐 */
*{}
#jdc {}
.jdc div{}
```

### 代码缩进

统一使用四个空格进行代码缩进，使得各编辑器表现一致（各编辑器有相关配置）

```
.jdc {
    width: 100%;
    height: 100%;
}
```

### 分号

每个属性声明末尾都要加分号；

```
.jdc {
    width: 100%;
    height: 100%;
}
```

### 代码易读性

左括号与类名之间一个空格，冒号与属性值之间一个空格

*推荐：*

```
.jdc { 
    width: 100%; 
}
```

*不推荐：*

```
.jdc{ 
    width:100%;
}
```

逗号分隔的取值，逗号之后一个空格

*推荐：*

```
.jdc {
    box-shadow: 1px 1px 1px #333, 2px 2px 2px #ccc;
}
```

*不推荐：*

```
.jdc {
    box-shadow: 1px 1px 1px #333,2px 2px 2px #ccc;
}
```

为单个css选择器或新申明开启新行

*推荐：*

```css
.jdc, 
.jdc_logo, 
.jdc_hd {
    color: #ff0;
}
.nav{
    color: #fff;
}
```

*不推荐：*

```css
.jdc,jdc_logo,.jdc_hd {
    color: #ff0;
}.nav{
    color: #fff;
}
```

颜色值 `rgb()` `rgba()` `hsl()` `hsla()` `rect()` 中不需有空格，且取值不要带有不必要的 0

*推荐：*

```
.jdc {
    color: rgba(255,255,255,.5);
}
```

*不推荐：*

```
.jdc {
    color: rgba( 255, 255, 255, 0.5 );
}
```

属性值十六进制数值能用简写的尽量用简写

*推荐：*

```
.jdc {
    color: #fff;
}
```

*不推荐：*

```css
.jdc {
    color: #ffffff;
}
```

不要为 `0` 指明单位

*推荐：*

```css
.jdc {
    margin: 0 10px;
}
```

*不推荐：*

```css
.jdc {
    margin: 0px 10px;
}
```

### 属性值引号

css属性值需要用到引号时，统一使用单引号

```css
/* 推荐 */
.jdc { 
	font-family: 'Hiragino Sans GB';
}

/* 不推荐 */
.jdc { 
	font-family: "Hiragino Sans GB";
}
```

### 属性书写顺序

建议遵循以下顺序：

1. 布局定位属性：display / position / float / clear / visibility / overflow（建议 display 第一个写，毕竟关系到模式）
2. 自身属性：width / height / margin / padding / border / background
3. 文本属性：color / font / text-decoration / text-align / vertical-align / white- space / break-word
4. 其他属性（CSS3）：content / cursor / border-radius / box-shadow / text-shadow / background:linear-gradient …

```css
.jdc {
    display: block;
    position: relative;
    float: left;
    width: 100px;
    height: 100px;
    margin: 0 10px;
    padding: 20px 0;
    font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;
    color: #333;
    background: rgba(0,0,0,.5);
    -webkit-border-radius: 10px;
    -moz-border-radius: 10px;
    -o-border-radius: 10px;
    -ms-border-radius: 10px;
    border-radius: 10px;
}
```

[mozilla官方属性顺序推荐](https://www.mozilla.org/css/base/content.css)

## 命名规范
由历史原因及个人习惯引起的 DOM 结构、命名不统一，导致不同成员在维护同一页面时，效率低下，迭代、维护成本极高。

### 目录命名
* 项目文件夹：shoping
* 样式文件夹：css
* 脚本文件夹：js
* 样式类图片文件夹：img
* 产品类图片文件夹： upload
* 字体类文件夹： fonts

### ClassName命名
ClassName的命名应该尽量精短、明确，必须以**字母开头命名**，且**全部字母为小写**，单词之间**统一使用下划线** “_” 连接

.nav_top

#### 常用命名推荐

**注意**：ad、banner、gg、guanggao 等有机会和广告挂勾的不建议直接用来做ClassName，因为有些浏览器插件（Chrome的广告拦截插件等）会直接过滤这些类名，因此

```
<div class="ad"></div>
```

这种广告的英文或拼音类名不应该出现

另外，**敏感不和谐字眼**也不应该出现，如：

```
<div class="fuck"></div>
<div class="jer"></div>
<div class="sm"></div>
<div class="gcd"></div> 
<div class="ass"></div> 
<div class="KMT"></div> 
...
```

| ClassName              | 含义                   |
| ---------------------- | -------------------- |
| about                  | 关于                   |
| account                | 账户                   |
| arrow                  | 箭头图标                 |
| article                | 文章                   |
| aside                  | 边栏                   |
| audio                  | 音频                   |
| avatar                 | 头像                   |
| bg,background          | 背景                   |
| bar                    | 栏（工具类）               |
| branding               | 品牌化                  |
| crumb,breadcrumbs      | 面包屑                  |
| btn,button             | 按钮                   |
| caption                | 标题，说明                |
| category               | 分类                   |
| chart                  | 图表                   |
| clearfix               | 清除浮动                 |
| close                  | 关闭                   |
| col,column             | 列                    |
| comment                | 评论                   |
| community              | 社区                   |
| container              | 容器                   |
| content                | 内容                   |
| copyright              | 版权                   |
| current                | 当前态，选中态              |
| default                | 默认                   |
| description            | 描述                   |
| details                | 细节                   |
| disabled               | 不可用                  |
| entry                  | 文章，博文                |
| error                  | 错误                   |
| even                   | 偶数，常用于多行列表或表格中       |
| fail                   | 失败（提示）               |
| feature                | 专题                   |
| fewer                  | 收起                   |
| field                  | 用于表单的输入区域            |
| figure                 | 图                    |
| filter                 | 筛选                   |
| first                  | 第一个，常用于列表中           |
| footer                 | 页脚                   |
| forum                  | 论坛                   |
| gallery                | 画廊                   |
| group                  | 模块，清除浮动              |
| header                 | 页头                   |
| help                   | 帮助                   |
| hide                   | 隐藏                   |
| hightlight             | 高亮                   |
| home                   | 主页                   |
| icon                   | 图标                   |
| info,information       | 信息                   |
| last                   | 最后一个，常用于列表中          |
| links                  | 链接                   |
| login                  | 登录                   |
| logout                 | 退出                   |
| logo                   | 标志                   |
| main                   | 主体                   |
| menu                   | 菜单                   |
| meta                   | 作者、更新时间等信息栏，一般位于标题之下 |
| module                 | 模块                   |
| more                   | 更多（展开）               |
| msg,message            | 消息                   |
| nav,navigation         | 导航                   |
| next                   | 下一页                  |
| nub                    | 小块                   |
| odd                    | 奇数，常用于多行列表或表格中       |
| off                    | 鼠标离开                 |
| on                     | 鼠标移过                 |
| output                 | 输出                   |
| pagination             | 分页                   |
| pop,popup              | 弹窗                   |
| preview                | 预览                   |
| previous               | 上一页                  |
| primary                | 主要                   |
| progress               | 进度条                  |
| promotion              | 促销                   |
| rcommd,recommendations | 推荐                   |
| reg,register           | 注册                   |
| save                   | 保存                   |
| search                 | 搜索                   |
| secondary              | 次要                   |
| section                | 区块                   |
| selected               | 已选                   |
| share                  | 分享                   |
| show                   | 显示                   |
| sidebar                | 边栏，侧栏                |
| slide                  | 幻灯片，图片切换             |
| sort                   | 排序                   |
| sub                    | 次级的，子级的              |
| submit                 | 提交                   |
| subscribe              | 订阅                   |
| subtitle               | 副标题                  |
| success                | 成功（提示）               |
| summary                | 摘要                   |
| tab                    | 标签页                  |
| table                  | 表格                   |
| txt,text               | 文本                   |
| thumbnail              | 缩略图                  |
| time                   | 时间                   |
| tips                   | 提示                   |
| title                  | 标题                   |
| video                  | 视频                   |
| wrap                   | 容器，包，一般用于最外层         |
| wrapper                | 容器，包，一般用于最外层         |