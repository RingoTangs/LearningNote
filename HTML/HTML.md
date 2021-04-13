# 一、HTML基础

## 1. HTML结构

```html
<!-- 
    1、<!DOCTYPE html> 表示这是HTML5的网页 
    2、<!DOCTYPE html> 不是一个HTML标签，它就是文档类型声明的标签
-->
<!DOCTYPE html>

<!-- 
    1、lang="en"：表示网页语言是English
    2、lang="zh-CN"：定义网页语言为中文
    3、对于文档来说，定义语言为英文也可以写中文，主要是有浏览器自动翻译的提示功能
 -->
<html lang="en">

<head>
    <!-- 
        1、charset="UTF-8"：字符集为UTF-8，包含了全世界所有国家的字符
        2、charset="UTF-8" 这句必须写，否则会引起网页的乱码
        3、写标准的 "UTF-8", 不要写成 "utf8" 或者 "UTF8"。
     -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>

</body>

</html>
```



## 2. HTML标签

### 2.1. 文本格式化标签

| 语义   | 标签                           | 说明                                   |
| ------ | ------------------------------ | -------------------------------------- |
| 加粗   | <strong></strong> 或者 <b></b> | 更推荐使用<strong>标签加粗 语义更强烈  |
| 倾斜   | <em></em> 或者 <i></i>         | 更推荐使用<em>标签倾斜 语义更强烈      |
| 删除线 | <del></del> 或者 <s></s>       | 更推荐使用<del>标签删除 语义更强烈     |
| 下划线 | <ins></ins> 或者 <u></u>       | 更推荐使用<ins>标签加下划线 语义更强烈 |



### 2.2. div和span

<div> 和 <span> 没有语义，他们就是一个盒子，用来装内容。

```shell
# 1、<div> 自己独占一行
# 2、一行可以放很多个<span>, 但是<span> 和 <span> 之间是有距离的
```

![image-20210113104552573](E:\Typora\image\image-20210113104552573.png)



### 2.3. img标签

**<img /> 标签的属性**：

| 属性   | 属性值   | 说明                                 |
| ------ | -------- | ------------------------------------ |
| src    | 图片路径 | 必须属性                             |
| alt    | 文本     | 替换文本，图像不能显示时替换         |
| title  | 文本     | 提示文本，鼠标放到图像上，显示的文字 |
| width  | 像素     | 设置图像的宽度                       |
| height | 像素     | 设置图像的高度                       |
| border | 像素     | 设置图像的边框粗细                   |



### 2.4. 超链接标签

**(1) <a></a> 语法格式**：

```html
<a href="跳转目标" target="目标窗口的弹出方式">文本/图像</a>
```



**(2) <a> 标签的属性**：

| 属性   | 作用                                                         |
| ------ | ------------------------------------------------------------ |
| href   | 用于指定链接目标的URL地址                                    |
| target | 用于指定链接页面的打开方式，其中`_self`为默认值，`_blank`为在新窗口中打开 |



**(3) 链接的分类**

```html
<!-- 1、外部链接：href中必须要以http/https开头 -->
<a href="https://www.baidu.com" target="_blank">百度一下</a>

<!-- 2、内部链接：直接写网页路径即可 -->
<a href="../index.html" target="_blank">index</a>

<!-- 3、空链接：使用 # 即可 -->
<a href="#">公司简介</a>

<!-- 4、下载链接：地址链接的是 文件 .exe 或者 zip 等压缩包 -->
<a href="../img.zip">下载</a>

<!-- 5、网页元素链接：在网页中的各种元素，如文本、图像、表格、音频等都可以添加链接 -->
<a href="https://www.baidu.com">
    <img src="../img.jpg" alt="图片链接">
</a>
```



### 2.5. 锚点链接

**锚点链接：当我们点击链接的时候，实现快速定位**。

```html
<!-- 锚点链接用于快速定位 -->
<a href="#live">个人生活</a>

<h3 id="live">个人生活</h3>
```



### 2.6. 特殊字符

![image-20210113121019873](E:\Typora\image\image-20210113121019873.png)



### 2.7. 表格

**(1) 表格标签的使用**

**表格不是用来布局页面的，而是用来展示数据的**。

**表格属性**：

| 属性名      | 属性值              | 描述                                           |
| ----------- | ------------------- | ---------------------------------------------- |
| align       | left、center、right | 规定表格相对周围元素的对齐方式                 |
| border      | 1或""               | 规定单元格是否拥有边框，默认为""，表示没有边框 |
| cellpadding | 像素值              | 规定单元边沿与其内容之间的空白，默认为1像素    |
| cellspacing | 像素值              | 规定单元格之间的空白，默认2像素                |
| width       | 像素值/百分比       | 规定表格的宽度                                 |

```html
<body>
    <!-- cellspacing="0" 单元格之间就没有空隙了 -->
    <table align="center" border="1" cellpadding="30px" cellspacing="60px">
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>年龄</th>
        </tr>
        <tr>
            <td>1</td>
            <td>张三</td>
            <td>18</td>
        </tr>
        <tr>
            <td>2</td>
            <td>李四</td>
            <td>19</td>
        </tr>
    </table>
</body>
```

![image-20210113124404290](E:\Typora\image\image-20210113124404290.png)



**(2) 表格结构标签**

```html
<table>
    <thead></thead>
    <tbody></tbody>
    <tfoot></tfoot>
</table>
```



**(3) 合并单元格**

- 跨行合并：`rowspan="合并单元格的个数"`。
- 跨列合并：`colspan="合并单元格的个数"`。

```shell
# step1、先确定是跨行还是跨列合并。

# step2、找到目标单元格，写上合并方式 = 合并的单元格数量

# step3、删除多余的单元格
```



### 2.8. 列表

**表格是用来显示数据的，列表是用来布局的**。

**列表**最大的特点就是整齐、整洁、有序，它作为布局更加自由和方便。

**根据使用情景不同，列表可以分为三大类：无序列表、有序列表和自定义列表**。

**(1) 无序列表**

```shell
# 无序列表的特点：
# 1、无序列表的各个列表项之间没有顺序级别之分，是并列的。

# 2、<ul></ul> 中之只能嵌套 <li></li>，直接在 <ul></ul> 标签中输入其他标签或者文字的做法是不被允许的。

# 3、<li></li>相当于是一个容器，可以放任何元素。
```



**(2) 有序列表**

```html
<ol>
    <li>篮球</li>
    <li>足球</li>
    <li>羽毛球</li>
</ol>
```

![image-20210113170026879](E:\Typora\image\image-20210113170026879.png)

```shell
# 有序列表特点：
# 1、<ol></ol>中只能嵌套<li></li>，直接在<ol></ol>标签中输入其他标签或者文字是不被允许的。

# 2、<li></li>相当于是一个容器，可以放任何元素。

# 3、有序列表会带有自己样式属性，但在实际使用时，我们会使用CSS来设置。
```



**(3) 自定义列表：自定义列表经常用于对术语或名词进行解释和描述，定义列表的列表项前没有任何项目符号**。

**自定义列表语法**：

```html
<dl>
    <dt>名词1</dt>
    <dd>名词1解释1</dd>
    <dd>名词1解释2</dd>
</dl>
```



**自定义列表特点**：

```shell
# 1、<dl></dl> 里面只能包含 <dt> 和 <dd>。

# 2、<dt>和<dd>个数没有限制，经常是一个<dt>对应多个<dd>。
```





**自定义列表案例：**

```html
<dl>
    <dt>关于小米</dt>
    <dd>了解小米</dd>
    <dd>加入小米</dd>
    <dd>投资者关系</dd>
</dl>
```

![image-20210113171642801](E:\Typora\image\image-20210113171642801.png)



## 3.  表单

### 3.1. input

**(1) type属性的属性值及其描述**：

| 属性值   | 描述                                                         |
| -------- | ------------------------------------------------------------ |
| button   | 定义可点击按钮(多数情况下，用于JavaScript启动脚本)。         |
| checkbox | 定义复选框。                                                 |
| file     | 定义输入字段和"浏览"按钮，供文件上传。                       |
| hidden   | 定义隐藏的输入字段。                                         |
| image    | 定义图像形式的提交按钮。                                     |
| password | 定义密码字段。                                               |
| radio    | 定义单选按钮。                                               |
| reset    | 定义重置按钮，重置按钮会清楚表单中所有的数据。               |
| submit   | 定义提交按钮。提交按钮会把表单数据发送到服务器。             |
| text     | 定义单行的输入字段，用户可在其输入文本。默认长度为20个字符。 |



**(2) <input /> 除了type属性外，还有其他属性**：

| 属性      | 属性值     | 描述                                 |
| --------- | ---------- | ------------------------------------ |
| name      | 由用户定义 | 定义<input/>的名称                   |
| value     | 由用户定义 | 定义<input/>的值                     |
| checked   | checked    | 规定此<input/>元素首次加载时应被选中 |
| maxlength | 正整数     | 规定输入字段中的字符的最大长度       |

```html
<form action="" method="get">
    <!-- 1、type="text" ==> 文本框 -->
    <!-- maxlength 规定用户输入的字符的最长长度 -->
    用户名：<input type="text" name="username" maxlength="6" /> <br />

    <!-- 2、type="password" ==> 密码 -->
    密码：<input type="password" name="password" /> <br />

    <!-- 3、type="radio" ==> 单选框 -->
    <!-- 单选框和复选框可以设置 checked 默认选中 -->
    性别：
    <input type="radio" name="gender" value="male" checked>男
    <input type="radio" name="gender" value="female">女 <br />

    <!-- 4、type="checkbox" 复选框 -->
    <!-- checkbox 可以实现选多个 也可以实现选一个 -->
    爱好：
    <input type="checkbox" name="interest" value="basketball">篮球
    <input type="checkbox" name="interest" value="football">足球
    <input type="checkbox" name="interest" value="badminton">羽毛球
</form>
```

![image-20210113185201827](E:\Typora\image\image-20210113185201827.png)



### 3.2. label

```html
<!-- label 中的for属性与目标元素的id属性相同 -->
<!-- label增加用户体验 -->
<label for="username">
    用户名：<input type="text" name="username" id="username">
</label>
```



### 3.3. select

```html
<form action="" method="get">
    籍贯: 
    <!-- select下拉列表 -->
    <!-- selected 默认选中 -->
    <select name="borned">
        <option value="beijing">北京</option>
        <option value="hebei" selected>河北</option>
        <option value="tianjin">天津</option>
    </select>
</form>
```



### 3.4. textarea

**使用场景**：当用户输入内容较多的情况下，我们就不能使用文本框表单了，此时我们可以使用<textarea>标签。在表单元素中，<textarea>标签是用于定义多行文本输入的控件。

```html
<form action="" method="get">
    <label for="introduce">
        <!-- cols="30" ==> 每行可以写30个字符 -->
        <!-- rows="3" ==> 默认显示3行 -->
        简介：<textarea name="introduce" id="introduce" cols="30" rows="3"></textarea>
    </label>
</form>
```

<img src="E:\Typora\image\image-20210113194056474.png" alt="image-20210113194056474" style="zoom:150%;" />