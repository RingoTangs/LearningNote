一、CSS基础入门

## 1. CSS代码风格

```css
/* 1、样式格式：展开格式 */
h3 {
    color: red;
    fonst-size: 20px;
}

/* 2、样式大小写：一般情况下采取小写的格式 */
h3 {
    color: pink;
}

/* 
  3、空格规范：
	(1) 属性值前面，冒号后面，保留一个空格
	(2) 选择器(标签)和大括号中间保留空格
*/
h3 {
    color: red;
}
```



## 2. CSS基础选择器

CSS选择器分为**基础选择器**和**复合选择器**两大类：

- 基础选择是由**单个选择器组成**的；
- 基础选择器又包括：标签选择器、类选择器、id选择器和通配符选择器。



### 2.1. 标签选择器

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>001-标签选择器</title>

    <!-- 标签选择器：是以标签名为选择器 -->
    <style>
        p {
            color: orange;
        }

        div {
            color: red;
        }
    </style>

</head>

<body>
    <p>男生</p>
    <p>男生</p>
    <p>男生</p>
    <div>女生</div>
    <div>女生</div>
    <div>女生</div>
</body>
```

![image-20210115191437979](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/标签选择器.png)



### 2.2. 类选择器

**(1) 语法**：

```css
/* 类名前加一个点 */
.类名 {
    key1: value1;
    ...
}
```

注意：

- 类选择器使用`"."`（英文点号）进行标识，后面紧跟类名（自定义的）；
- 长名称后者词组可以使用中横线`my-favor`来为选择器命名；
- 不要使用纯数字、中文等命名。

**(2) 类选择器使用**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>002-类选择器</title>

    <style>
        .target {
            color: red;
        }

        /* 这种用短横线来分隔 */
        .i-like {
            color: royalblue;
        }
    </style>

</head>

<body>
    <ul>
        <li class="target">1</li>
        <li class="target">2</li>
        <li class="i-like">3</li>
        <li class="i-like">4</li>
        <li>5</li>
    </ul>
</body>
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/类选择器.png" alt="image-20210115192438252" style="zoom:150%;" />



**(3) 使用类选择器画盒子**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>003-使用类选择器画盒子</title>

    <style>
        .box-red {
            /* 1、设置宽度 */
            width: 100px;

            /* 2、设置高度 */
            height: 100px;

            /* 3、设置背景颜色 */
            background-color: red;
        }

        .box-green {
            width: 100px;
            height: 100px;
            background-color: green;
        }
    </style>
</head>

<body>
    <div class="box-red"></div>
    <div class="box-green"></div>
    <div class="box-red"></div>
</body>
```



**(4) 类选择器—多类名**

我们可以给一个标签指定多个类名，从而达到更多的选择的目的。

**一句话：一个标签可以有多个名字**。

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>004-类选择器-多类名</title>

    <style>
        .current-color {
            color: red;
        }

        .current-size {
            font-size: 35px;
        }
    </style>

</head>

<body>
    <!-- 多个类名之间必须要有空格 -->
    <div class="current-color current-size">刘德华</div>
</body>
```

![image-20210115193919895](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/一个标签可以有多个名字.png)



**多类名的使用场景**：

- 抽取公共样式；
- 这些标签都可以调用这个公共的类，然后再调用自己独有的类；

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>003-使用类选择器画盒子</title>

    <style>
        /* 相当于抽取公共样式 */
        .box {
            width: 100px;
            height: 100px;
        }

        /* 对于特定的样式再具体写 */
        .red {
            border: 1px solid red;
        }

        .green {
            border: 1px solid black;
        }
    </style>
</head>

<body>
    <div class="box red"></div>
    <div class="box green"></div>
    <div class="box red"></div>
</body>
```

![image-20210115194629242](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/类选择器画盒子.vvf0hgpxlfk.png)



### 2.3. id选择器

**(1) 语法**：

```css
#id名 {
    key: value;
    ...
}
```



**(2) id选择器的使用**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>005-id选择器</title>
    <style>
        #hello {
            color: red;
        }
    </style>
</head>

<body>
    <h1 id="hello">你好</h1>
</body>
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/id选择器.2pzs8ia9xfy0.png" style="zoom:150%;" />



### 2.5. 类和id选择器区别

- 类选择器相当于人的名字，一个人可以有多个名字，一个标签可以有多个类；
- id选择器相当于人的身份证号，全国唯一，不可重复；
- id选择器和类选择器最大的不同在于使用的次数上；
- **类选择器在修改样式中用的最多**；
- **id选择器一般用于页面唯一性的元素上，经常和JavaScript搭配使用**。



### 2.6. 通配符选择器

**(1) 语法**

```css
/* 通配符选择器改变所有标签的样式 */
* {
    key: value;
    ...
}
```



## 3. 字体样式

### 3.1. font-family

**注意**：

- 各种字体之间必须使用英文状态下的逗号隔开；
- 一般情况下，如果有空格隔开的多个单词组成的字体，需要加单引号；
- 尽量使用系统默认的自带字体，保证在任何用户的浏览器中都能正确显示。
- 写多个字体，这样的作用是兼容性好。
- 最常用的几个字体 `body {font-family: 'Microsoft YaHei', tahoma, arial, 'Hiragino Sans GB'}`

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>006-font-family</title>

    <style>
        /* 常用的字体：微软雅黑 和 新罗马字体 */
        h2 {
            /* 设置文字字体：Microsoft YaHei ==> 微软雅黑 */
            font-family: 'Microsoft YaHei';
        }

        h3 {
            /* 写多个字体,这样的兼容性好！ */
            font-family: Georgia, 'Times New Roman', Times, serif;
        }
    </style>

</head>

<body>
    <h2>你好</h2>
    <h3>你好</h3>
</body>
```

![image-20210115205244081](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/font-family.4eolod23xka0.png)



### 3.2. font-size

`font-size`设置字体大小

```css
p {
    /* 这里要添加单位 */
    font-size: 20px;
}
```

**注意**：

- px（像素）大小是我们网页的最常用的单位；
- 谷歌浏览器默认的文字大小为 16px；
- 不同浏览器可能默认显示的字号大小不一致，我们尽量给一个明确值大小，不要默认大小；
- 我们可以给整个 `body` 指定整个页面文字大小。

### 3.3. font-weight

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/font-weight.47zd5gfr86g0.png" alt="image-20210115211309884" style="zoom: 150%;" />

| 属性值  | 描述                                                     |
| ------- | -------------------------------------------------------- |
| normal  | 默认值（不加粗的）                                       |
| bold    | 定义粗体（加粗的）                                       |
| 100-900 | 400等同于normal，而700等同于bold。注意数字后面不加单位。 |



### 3.4. font-style

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/font-style.57w00zuy3bs0.png" alt="image-20210115211731554" style="zoom: 200%;" />



## 4. 文本属性

### 4.1. color

color属性用于定义文本的颜色。

```css
div {
    color: red;
}
```

| 表示           | 属性值                    |
| -------------- | ------------------------- |
| 预定义的颜色值 | red, green, blue          |
| 十六进制       | #FF0000，#FF6600，#29D794 |
| RGB            | rgb(255, 0, 0)            |



### 4.2. text-align

`text-align`属性用于设置元素内文本内容的水平对齐方式。

```css
/* 本质是让div所在的盒子里面的文字水平居中对齐 */
div {
    text-align: center;
}
```

**text-align: center; 可以使盒子内所有的行内元素和行内块元素水平居中**。



### 4.3. text-decoration

`text-decoration`属性规定添加到文本的修饰。可以给文本添加下划线、删除线、上划线等。

该属性可以用于清除<a></a>的下划线！

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/text-decoration.3ribecezsi00.png" alt="image-20210115220355521" style="zoom:150%;" />



### 4.4. text-indent

`text-indent`属性用于文本**首行**缩进。

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/text-indent.1glrvcqo3ezk.png" alt="image-20210115221456984" style="zoom:150%;" />

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>007-text-indent</title>

    <style>
        p {
            /* 2em 表示2个文字大小 */
            text-indent: 2em;
        }
    </style>

</head>

<body>
    <p>
        今天天气真不错, 哈哈哈哈哈哈哈哈哈哈哈哈
        哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈
        哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈
        哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈
        哈哈哈哈哈哈哈哈哈哈哈哈
    </p>
    <p>2021年了，新的一年要开开心心!</p>
    <p>加油！</p>
</body>
```

![image-20210115221752601](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/text-indent-demo.368k8fmtqs40.png)



### 4.5. line-height

`line-height`属性用于设置行间的距离（行高）。可以控制文字行与行之间的距离。**该属性可以用于文本垂直居中**。

```css
p {
    line-height: 26px;
}
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/行间距.5dqbike7cbk0.png" alt="image-20210115222122487" style="zoom:150%;" />



## 5. CSS引入方式

按照CSS样式书写的位置（或者引入的方式），CSS样式可以分为三大类：

1. 行内样式表（行内式）
2. 内部样式表（嵌入式）
3. 外部样式表（链接式）

### 5.1. 内部样式表

```html
<style>
    div {
        color: red;
    }
</style>
```

- `<style>` 标签理论上可以放在文档的任何地方，但是一般会放在文档的 `head` 标签中；
- 此种方式可以方便的控制当前页面的CSS样式。



### 5.2. 行内样式表

```html
<p style="color: red; font-size: 18px">
    你好！
</p>
```

- `style` 其实就是标签的属性；
- 在双引号中间，写法要复合CSS规范；
- 行内样式表可以控制当前的标签设置样式；
- **不推荐大量使用**。



### 5.3. 外部样式表

在HTML页面中，使用 <link> 标签引入CSS文件。

```html
<link rel="stylesheet" href="CSS文件路径">
```

![image-20210116164312250](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/外部样式表.42743okk7cu0.png)



## 6. Emmet语法

Emmet语法可以快速生成HTML。

```html
<body>
    <!-- 1、tab/enter 可以快速生成标签 -->
    <div></div>

    <!-- 2、* 可以生成多个相同相同标签 -->
    <!-- div*3 -->
    <div></div>
    <div></div>
    <div></div>

    <!-- ul>li*3 -->
    <ul>
        <li></li>
        <li></li>
        <li></li>
    </ul>

    <!-- 3、父子级标签，可以使用 > -->
    <!-- ul>li -->
    <ul>
        <li></li>
    </ul>

    <!-- 4、兄弟级标签，使用 + -->
    <!-- div+p -->
    <div></div>
    <p></p>

    <!-- 5、生成带有类名或者id名字的, 直接.demo #one -->
    <!-- p.demo -->
    <p class="demo"></p>

    <!-- a#one -->
    <a href="" id="one"></a>

    <!-- 6、类名有顺序可以使用自增符号 -->
    <!-- p.demo$*5 -->
    <p class="demo1"></p>
    <p class="demo2"></p>
    <p class="demo3"></p>
    <p class="demo4"></p>
    <p class="demo5"></p>

    <!-- 7、想要生成的标签内部写内容可以使用{} -->
    <!-- div{Hello World}*5-->
    <div>Hello World</div>
    <div>Hello World</div>
    <div>Hello World</div>
    <div>Hello World</div>
    <div>Hello World</div>

    <!-- ul>li{$}*5 -->
    <ul>
        <li>1</li>
        <li>2</li>
        <li>3</li>
        <li>4</li>
        <li>5</li>
    </ul>
</body>
```

![image-20210116170459717](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/Emment语法.7fxqvtquszs0.png)



## 7. CSS复合选择器

### 7.1. 后代选择器

**(1)语法**：

```css
元素1 元素2 {
	key: value;
    ...
}
```

注意：

- 元素2一定是元素1的孩子；
- 元素1和 元素2中间用**空格隔开**；
- 元素1是父级，元素2是子级，最终选择的是**元素2**；
- 元素2可以是儿子，也可以是孙子等，只要是元素1的后代即可。

### 

**(2) 后代选择器的使用**：

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>010-后代选择器</title>

    <style>
        /* 将 ol 下的 li 选择出来 */
        ol li {
            color: red;
        }

        /* 将 ul 下的 li 选择出来 */
        ul li {
            color: green;
        }

        /* 标签选择器、类选择器 和 id选择器 都可以一起使用 */
        ul li #target {
            color: hotpink;
        }
    </style>

</head>

<body>
    <ol>
        <li>1</li>
        <li>2</li>
        <li>3</li>
        <li>4</li>
        <li>5</li>
    </ol>

    <ul>
        <li>1+10</li>
        <li>2+10</li>
        <li>3+10</li>
        <li>4+10</li>
        <li><span id="target">target</span></li>
    </ul>
</body>
```

![image-20210116182753125](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/后代选择器.41vw07su7hs0.png)



### 7.2. 子选择器

子选择器只能选择某元素最近一级的元素。**简单理解就是选亲儿子元素**。

**语法**：

```css
div > p {
    key: value;
    ...
}
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/子选择器.4ta3rgl3nsw0.png" alt="image-20210116183226730" style="zoom:150%;" />



### 7.3. 并集选择器

**并集选择器可以选择多组标签，同时为他们定义相同的样式**。通常用于集体声明。

**(1) 语法**：

```css
元素1, 元素2 {
    key: value;
    ...
}
```

注意：

- 并集选择器是各选择器通过英文逗号`,`连接而成；
- **任何形式的选择器都可以作为并集选择器的一部分**；



**(2) 并集选择器的使用**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>011-并集选择器</title>
    <style>
        /* 并集选择器约定，竖着写 */
        /* 一定要注意最后一个选择器不需要加逗号 */
        div,
        p,
        .list li {
            color: red;
        }
    </style>
</head>

<body>
    <div>111</div>
    <p>222</p>
    <ul class="list">
        <li>1</li>
        <li>2</li>
        <li>3</li>
    </ul>
</body>
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/并集选择器.lpx2kvoaqxs.png" alt="image-20210116203438330" style="zoom:150%;" />



## 8. 伪类选择器

**伪类选择器**用于向某些选择器添加特殊的效果，比如给链接添加特殊效果，或选择第1个，第n个元素。

伪类选择器最大的特点是**用冒号（:）表示**，比如：`hover、:first-child`。

### 8.1. 链接伪类选择器

```css
/* 四种伪类选择器 */
a:link 			/* 选择所有未被访问的链接 */
a:visited		/* 选择所有已经被访问的链接 */
a:hover			/* 选择鼠标指针位于其上的链接 */
a:active		/* 选择活动链接（鼠标按下未弹起的链接） */
```



**链接伪类选择器的使用**：

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>012-链接伪类选择器</title>

    <style>
        a {
            text-decoration: none;
            color: black;
        }

        /* 选择所有未访问的链接 */
        a:link {
            color: black;
        }

        /* 选择所有已经访问过的链接 */
        a:visited {
            color: purple;
        }

        /* 选择鼠标经过时的链接 */
        a:hover {
            color: blue;
        }

        /* 选择鼠标按下没有弹起的链接 */
        a:active {
            color: red;
        }
    </style>

</head>

<body>
    <a href="#">Hello World</a>
</body>
```

**注意**：

- 为了确保链接伪类选择器生效，请按照 **LVHA**的顺序声明：`:link、:visited、:hover、:active`；
- 记忆法：love hate 或者 lv hao；
- 因为 a 链接在浏览器中有默认样式，所以实际工作中都需要给链接单独指定样式。



**开发中常用的写法**：

```css
/* 标签选择器 */
a {
    color: black;
    text-decoration: none;
}

/* 链接伪类选择器, 当鼠标经过的时候文字变色 */
a:hover {
    color: blue;
}
```



### 8.2. :focus伪类选择器

**:focus伪类选择器**用于选取获得焦点的表单元素。

焦点就是光标，一般情况下 <input> 类表单元素才能获取，因此这个选择器也主要针对于表单元素来说。

```css
input:focus {
    background-color: red;
}
```



**:focus伪类选择器的用法**：

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>:focus伪类选择器</title>

    <style>
        /* 选择获得焦点(光标)的表单元素 */
        input:focus {
            background-color: skyblue;
            color: red;
        }
    </style>

</head>

<body>
    <label for="username">
        用户名：<input type="text" name="username" id="username">
    </label>
</body>
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/focus伪类选择器.8t2iuaag554.png" alt="image-20210116210602657" style="zoom:150%;" />



## 9. 元素的显示模式

### 9.1. 块级元素

常见的块元素：<h1>~<h6>、<p>、<div>、<ul>、<ol>、<li>等，**其中 <div> 是最典型的块级元素**。

**块级元素的特点**：

- 比较霸道，独占一行；
- 高度、宽度、外边距、内边距都可以控制；
- 宽度默认是容器（父级宽度）的100%；
- 是一个容器及盒子，里面可以放行内或者块级元素。

**注意**：

- **文字类的元素内不能使用块级元素**；
- 特别的，<p> 标签主要用于存放文字，因此 <p> 里面不能放块级元素，特别是不能放 <div>；
- 同理，<h1>~<h6>等都是文字类块级标签，里面也不能放其他块级元素。

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>014-块级元素</title>

    <style>
        div {
            border: 1px solid red;
            height: 30px;
            width: 300px;
        }
    </style>

</head>

<body>
    <div>块级元素 独占一行</div> 瑟瑟发抖
</body>
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/块儿级元素.265tl45757eo.png" alt="image-20210116213128600" style="zoom:150%;" />



### 9.2. 行内元素

常见的行内元素：<a>、<span> 等。

**行内元素特点**：

- 相邻行内元素在一行上，一行可以显示多个；
- 高、宽直接设置是无效的；
- 默认宽度就是它本身内容的宽度；
- **行内元素只能容纳文本或其他行内元素**。



**注意**：

- 链接里面不能再放链接；
- 特殊情况下 <a> 里面可以放块级元素，但是给 <a> 转换一下块级模式最安全；



### 9.3. 行内块元素

行内块元素：在行内元素中有几个特殊的标签 —— <img /> 、<input />、<td>，**它们同时具有块元素和行内元素的特点**。

行内块元素的特点：

- 和相邻行内元素（行内块）在一行上，但是他们之间会有空白缝隙。一行可以显示多个（行内元素特点）；
- 默认宽度就是它本身内容的宽度（行内元素特点）；
- 高度、行高、外边距和内边距都可以控制（块级元素特点）。



```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>015-行内块元素</title>

    <style>
        input {
            width: 200px;
            height: 35px;
        }
    </style>

</head>

<body>
    <input type="text" name="username" />
    <input type="password" name="password" />
</body>
```

![image-20210116220006524](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/行内块儿元素.7ijoj58rk6k0.png)



### 9.4. 显示模式的转换

特殊情况下，我们需要元素模式的转换。

**简单理解：一个模式的元素需要另外一种模式的特性**。

- 转换为块级元素：`display: block`；
- 转换为行内元素：`display: inline`。

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>016-显示模式的转换</title>

    <style>
        a {
            /* display: block; 将行内元素转换成块级元素 */
            display: block;
            color: black;
            background-color: skyblue;
            text-decoration: none;
            /* border: 1px solid red; */
            height: 36px;
            width: 200px;
            text-align: center;
            line-height: 36px;
        }

        /* 当鼠标经过时, <a></a> 标签会切换背景颜色 */
        a:hover {
            background-color: pink;
        }
    </style>

</head>

<body>
    <a href="#">Hello World</a>
</body>
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/显示模式的转换.6bcqkart67s0.png" alt="image-20210116222836003" style="zoom:150%;" />



## 10. CSS背景

### 10.1. 背景颜色

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/CSS背景颜色.2ghme0o4xedc.png" alt="image-20210117104949369" style="zoom:150%;" />



### 10.2. 背景图片

`background-image`属性描述了元素的背景图像。

实际开发中常见于logo或者一些装饰性的小图片或者超大的背景图片，优点是非常便于控制位置。

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/背景图片.44cls8nr7920.png" alt="image-20210117105418899" style="zoom:150%;" />



### 10.3. 背景平铺

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/背景平铺.1ftezv07z7wg.png" alt="image-20210117110240986" style="zoom: 200%;" />

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>017-背景图片</title>

    <style>
        div {
            width: 500px;
            height: 500px;
            border: 1px solid red;
            background-image: url(../images/bd.png);
            /* 默认情况下,背景图片是平铺的 */
            background-repeat: no-repeat;
        }
    </style>

</head>

<body>
    <div></div>
</body>
```

![image-20210117110443233](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/背景平铺案例.2ukt5rzs8300.png)



### 10.4. 背景位置

**background-position**

**(1) 参数是方位名词**

- 如果指定的两个值都是方位名词，则两个值前后顺序无关，比如 left top 和 top left 一致；

  ```css
  /* 如果参数是方位名词 right center 和 center right 是等价的 */
  background-position: center bottom;
  
  background-position: right center;
  ```

- 如果只指定了一个方位名词，另一个省略，则第二个值默认居中对齐；

  ```css
  /* 此时 y轴方向上是靠下对齐的 第二个参数省略 那么x轴方向是水平居中显示的 */
  background-position: bottom;
  
  /* 此时 x轴方向上是靠右对齐的 第二个参数省略 那么y轴方向是垂直居中显示的 */
  background-position: right;
  ```



**(2) 参数是精确单位**

- 如果参数值是精确坐标，那么第一个肯定是 x 坐标，第二个肯定是 y 坐标；

  ```css
  /* x轴一定是50px, y轴一定是20px */
  background-position: 50px 20px;
  
  /* x轴是20px, y轴默认是center */
  background-position: 20px;
  ```



**(3) 参数是混合单位**

- 如果指定的两个值是精确单位和方位名词混合使用，那么第一个肯定是 x 坐标，第二个肯定是 y 坐标;

  ```css
  /* x轴居中对齐, y轴20px */
  background-position: center 20px;
  ```

  





### 10.5. 背景图片案例

**(1) 案例一：装饰类图片**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>018-背景图片案例</title>
    <style>
        h3 {
            width: 118px;
            height: 41px;
            /* background-color: pink; */
            font-weight: 400;
            font-size: 14px;
            line-height: 41px;
            background-image: url(../images/title_sprite.png);
            background-repeat: no-repeat;
            background-position: -2.4em;
            text-indent: 1.8em;
        }
    </style>
</head>

<body>
    <h3>成长守护平台</h3>
</body>
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/背景图片案例1.5n4g12lzc280.png" alt="image-20210117114252412" style="zoom:200%;" />



**(2) 超大背景图片显示**

```css
body {
    background-image: url(../images/146-bigskin-1.jpg);
    background-repeat: no-repeat;

    /* 水平居中 靠上显示 */
    background-position: center top;
}
```



### 10.6. 背景固定

`background-attachment`属性设置背景图像是否固定或者随着页面的其余部分滚动。

`background-attachment`后期可以制作视差滚动的效果。

```css
background-attachment: scroll | fixed;
```

| 参数   | 说明                         |
| ------ | ---------------------------- |
| scroll | 背景图像随对象内容滚动(默认) |
| fixed  | 背景图像固定                 |



```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>020-背景固定</title>

    <style>
        body {
            background-image: url(../images/146-bigskin-1.jpg);
            background-position: center top;
            background-repeat: no-repeat;

            /* 背景图像固定到目标位置, 默认是scroll */
            background-attachment: fixed;
            color: pink;
        }
    </style>

</head>
```



### 10.7. 背景复合写法

为了简化背景属性的代码，我们可以将这些属性合并简写在同一个属性 background 中。

```css
background: 背景颜色 背景图片地址 背景平铺 背景图像滚动 背景图片位置
```

```css
background: transparent url(../images/146-bigskin-1.jpg) no-repeat fixed center top;
```



### 10.8. 背景颜色半透明

语法：

```css
background: rgba(0, 0, 0, 0.3);
```

- 走后一个参数代表透明度，取值范围在 0~1之间；
- 我们习惯将最后一个参数0.3省略，写 `background: rgba(0, 0, 0, .3);`即可；
- **注意：背景半透明是指盒子背景半透明，盒子里面的内容不受影响**。



## 11. 五彩导航案例

**练习价值**：

- 链接属于行内元素，但是此时需要宽度和高度，因此需要模式转换；
- 里面的文字需要水平居中和垂直居中，因此需要单行文字垂直居中的代码；
- 链接里面需要设置背景图片，因此需要用到背景的相关属性设置；
- 鼠标经过变化背景图片，因此需要用到链接伪类选择器。

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>022-五彩导航栏案例</title>

    <style>
        .nav a {
            /* 行业块元素 */
            display: inline-block;
            width: 120px;

            /* 行高 < 高度 文字会偏上 */
            height: 58px;
            line-height: 54px;
            text-align: center;
            text-decoration: none;
            color: white;
        }

        .nav .layout {
            background-repeat: no-repeat;
            background-position: center top;
        }

        .nav .bg1 {
            background-image: url(../images/bg1.png);
        }

        .nav .bg2 {
            background-image: url(../images/bg2.png);
        }

        .nav .bg3 {
            background-image: url(../images/bg3.jpg);
        }

        .nav .bg4 {
            background-image: url(../images/bg4.png);
        }

        .nav .bg5 {
            background-image: url(../images/bg5.png);
        }

        /* 这里是a:hover的使用 */
        .nav .bg1:hover {
            background-image: url(../images/bg11.png);
        }
    </style>

</head>

<body>
    <div class="nav">
        <a href="#" class="bg1 layout">五彩导航栏</a>
        <a href="#" class="bg2 layout">五彩导航栏</a>
        <a href="#" class="bg3 layout">五彩导航栏</a>
        <a href="#" class="bg4 layout">五彩导航栏</a>
        <a href="#" class="bg5 layout">五彩导航栏</a>
    </div>
</body>
```

![image-20210121202217683](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/五彩导航栏.2igf152kuqq0.png)

## 12. CSS三大特性

### 12.1. 层叠性

相同选择器给设置相同的样式，此时一个样式就会**覆盖（层叠）**另一个冲突的样式。

层叠性主要解决样式冲突的问题。

**层叠行原则**：

- 样式冲突，遵循的原则是**就近原则**，哪个样式离结构近，就执行哪个样式；
- 样式不冲突，就不会重叠。

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>023-层叠性</title>

    <style>
        div {
            color: red;
            font-size: 18px;
        }

        div {
            color: pink;
        }
    </style>

</head>

<body>
    <div>123</div>
</body>
```

![image-20210121214955099](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/层叠性.4y18gid1vgw0.png)



### 12.2. 继承性

CSS中的继承：子标签会继承父标签的某些样式，如文本颜色和字号。

简单理解就是：子承父业。

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>024-继承性</title>

    <style>
        div {
            color: pink;
            font-size: 18px;
        }
    </style>

</head>

<body>
    <div>
        <p>123</p>
    </div>
</body>
```

![image-20210121215439207](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/继承性.3hqi650lskk0.png)



**注意**：

- 恰当的使用继承可以简化代码，降低CSS样式的复杂性；
- 子元素可以继承父元素的样式（text-，font-，line-这些元素开头的可以继承，以及color属性）。



### 12.3. 行高的继承性

```css
body {
    /* 1.5：行高是当前文字大小的1.5被 */
    font: 12px/1.5 Microsoft YaHei;
}
```

- 行高可以不跟单位；
- 如果子元素没有设置行高，则会继承父元素的行高为1.5；
- 此时子元素的行高是：当前子元素的文字大小 * 1.5；
- **body行高 1.5 这样的写法最大的优势就是里面子元素可以根据自己文字大小自动调整行高**。



### 12.4. 优先级

**(1) 优先级和权重**

当同一个元素指定多个选择器，就会有优先级的产生。

- 选择器相同，则执行层叠性。
- **选择器不同，则根据选择器权重执行**。

| 选择器               | 选择器权重 |
| -------------------- | ---------- |
| 继承 或者 *          | 0 0 0 0    |
| 元素选择器           | 0 0 0 1    |
| 类选择器，伪类选择器 | 0 0 1 0    |
| ID选择器             | 0 1 0 0    |
| 行内样式 style=""    | 1 0 0 0    |
| !important 重要的    | 无穷大     |

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/优先级.1iatl5f5l6hs.png" alt="image-20210123182853149"  />

**注意**：

- 权重是有4组数字组成，但是不会有进位；
- 可以理解位类选择器永远大于元素选择器，id选择器永远大于类选择器，以此类推。。
- **继承的权重是0，如果该元素没有直接选中，不管父元素权重多高，子元素权重都是0**。
- **我们看标签到底执行哪个样式，就先看这个标签有没有直接被选出来**。

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>026-CSS优先级</title>

    <style>
        /* 父亲的权重很高，但是p标签继承的权重是0 */
        #father {
            color: red;
        }

        /* p的标签选择器权重是 1 > 0 */
        /* 所以文字显示粉色 */
        p {
            color: pink;
        }
    </style>
</head>

<body>
    <div id="father">
        <p>Hello World</p>
    </div>
</body>
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/优先级案例.3ks6yyd7kza0.png" alt="image-20210123183801590" style="zoom:150%;" />



**(2) 权重的叠加**

**权重叠加：如果是复合选择器，则会有权重叠加，需要计算权重**。

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>027-权重叠加</title>

    <style>
        /* 复合选择器会有权重叠加的问题 */
        /* ul li 的权重是 0 0 0 1 + 0 0 0 1 = 0 0 0 2 */
        ul li {
            color: green;
        }

        /* li 的权重是 0 0 0 1 */
        li {
            color: red;
        }

        /* 类选择器 + 标签选择器 ==> 0 0 1 0 + 0 0 0 1 = 0 0 1 1 */
        .nav li {
            color: pink;
        }
    </style>

</head>

<body>
    <ul class="nav">
        <li>1</li>
        <li>2</li>
        <li>3</li>
    </ul>
</body>
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/权重的叠加.5ahu4pf5n340.png" alt="image-20210123184756600" style="zoom:150%;" />



## 13. 盒子模型

### 13.1. 边框(border)

border可以设置元素的边框。边框有三部分组成：边框宽度、边框样式和边框颜色。

**语法**：

```css
border: border-width || border-style || border-color
```

| 属性         | 作用                     |
| ------------ | ------------------------ |
| border-width | 定义边框的粗细，单位是px |
| border-style | 边框的样式               |
| border-color | 边框的颜色               |

![image-20210123214600849](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/边框.3jjl7x3gs180.png)



### 13.2. 边框的复合写法

**（1）边框简写**：

```css
border: 1px solid red; /* 没有先后顺序 */ 
```



**（2）边框分开写法**：

```css
border-top: 1px solid red; /* 只设定上边框,其余同理 */
```



**（3）边框的层叠性**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>028-边框</title>

    <style>
        /* 请给一个 200*200 的盒子，设置上边框为红色， 其余边框为蓝色 */
        div {
            width: 200px;
            height: 200px;
            border: 1px solid blue;

            /* CSS的层叠性，border 和 border-top 不可以颠倒顺序 */
            border-top: 1px solid red;
        }
    </style>

</head>

<body>
    <div></div>
</body>
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/边框的层叠性.5opulbmxiwg0.png" alt="image-20210123215501364" style="zoom:150%;" />



### 13.3. 合并相邻边框

`border-collapse`属性控制浏览器绘制表格边框的方式。它控制相邻单元格的边框。

**语法**：

```css
border-collapse: collapse;
```

- collapse 单词是合并的意思；
- border-collapse: collapse; 表示相邻边框合并在一起；

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>029-表格细线边框</title>

    <style>
        table {
            width: 200px;
            height: 100px;

        }

        table th {
            height: 35px;
        }

        table,
        table td,
        table th {
            border: 1px solid pink;
            text-align: center;
            font-size: 14px;

            /* 合并相邻的边框 */
            border-collapse: collapse;
        }
    </style>

</head>

<body>
    <table>
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>性别</th>
        </tr>
        <tr>
            <td>1</td>
            <td>张三</td>
            <td>男</td>
        </tr>
        <tr>
            <td>2</td>
            <td>李四</td>
            <td>男</td>
        </tr>
    </table>
</body>
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/合并相邻的边框.52i4i6qft140.png" alt="image-20210123221318573" style="zoom:150%;" />



### 13.4. 内边距

`padding`属性用于设置内边距，即边框与内容之间的距离。

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>030-内边距</title>

    <style>
        div {
            /* 宽度和高度 表示的是内容的宽高 */
            height: 200px;
            width: 200px;
            border: 5px solid pink;

            /* padding 是内容和边框的距离 */
            padding: 20px;
        }
    </style>

</head>

<body>
    <div>
        Hello World!
    </div>
</body>
```

![image-20210123222325956](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/内边距.1qlrb4vltrwg.png)



**padding会影响实际盒子的大小**



### 13.5. padding复合属性

| 值的个数                     | 含义                                                 |
| ---------------------------- | ---------------------------------------------------- |
| padding: 5px;                | 1个值，代表上下左右都有5px                           |
| padding: 5px 10px;           | 2个值，代表上下5px, 左右10px                         |
| padding: 5px 10px 20px;      | 3个值，代表上内边距5px，左右内边距10px，下内边距20px |
| padding: 5px 10px 20px 30px; | 4个值，上5px 右10px 下20px 左30px(上右下左 顺时针)   |



### 13.6. padding应用

**(1) 新浪导航栏**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>031-新浪导航栏</title>

    <style>
        .nav {
            height: 41px;
            /* border: 1px solid red; */
            background-color: #fcfcfc;
            border-top: 3px solid #ff8500;
            border-bottom: 1px solid #edeef0;
        }

        .nav a {
            /* a属于行内元素 此时必须要转换 行内块元素 */
            display: inline-block;
            text-decoration: none;
            color: #4c4c4c;
            font-size: 14px;
            height: 41px;
            /* border: 1px solid red; */
            line-height: 41px;
            padding-left: 15px;
            padding-right: 15px;
        }

        .nav a:hover {
            color: #ff8500;
            background-color: #eee;
        }
    </style>

</head>

<body>
    <div class="nav">
        <a href="#">设为首页</a>
        <a href="#">手机新浪网</a>
        <a href="#">移动客户端</a>
        <a href="#">博客</a>
        <a href="#">微博</a>
        <a href="#">关注我</a>
    </div>
</body>
```

![image-20210124143515703](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/新浪导航栏.3470c90cdue0.png)



**(2) 小米侧边栏**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>032-小米侧边栏</title>

    <style>
        .side-bar {
            width: 200px;
            height: 280px;
            background-color: #55585a;
        }

        .side-bar a {
            display: block;
            /* 里面的盒子不指定宽度就默认和父亲一样宽，这时候再使用padding-left 就不会撑开盒子 */
            height: 40px;
            line-height: 40px;
            text-decoration: none;
            color: #fff;
            padding-left: 30px;
        }

        .side-bar a:hover {
            background-color: #ff6700;
        }
    </style>

</head>

<body>
    <div class="side-bar">
        <a href="#">手机 电话卡</a>
        <a href="#">电视 盒子</a>
        <a href="#">笔记本 平板</a>
        <a href="#">出行 穿戴</a>
        <a href="#">智能 路由器</a>
        <a href="#">健康 儿童</a>
        <a href="#">耳机 音响</a>
    </div>
</body>
```

![image-20210124145700959](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/小米侧边栏.3kzz03cumqm0.png)



### 13.7. 外边距应用

`margin`属性用于设置外边距，即控制盒子和盒子之间的距离。

**margin 简写方式代表的意义和 padding 完全一致**。



**外边距可以让块级盒子水平居中，但是必须满足两个条件**：

- 盒子必须指定了宽度。
- **盒子左右的外边距都设置为auto**。

```css
/* 常见的三种写法 */
margin-left: auto; margin-right: auto;

margin: auto;

/* 用的最多 */
margin: 0 auto;
```

**注意：以上方法是让块级元素水平居中，行内元素或者行内块元素水平居中给其父元素添加 text-align:center 即可**。



```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>033-外边距</title>

    <style>
        .head {
            height: 300px;
            width: 70%;
            background-color: pink;
            margin: 0 auto;
        }
    </style>

</head>

<body>
    <div class="head"></div>
</body>
```

![image-20210124151325337](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/外边距的应用.7f5hjxy4xjs0.png)



### 13.8. 外边距合并问题

**(1) 嵌套块元素垂直外边距的塌陷**

对于两个嵌套关系（父子关系）的块元素，父元素有上外边距同时子元素也有上外边距，此时父元素会塌陷较大的外边距值。

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/外边距合并问题.2f62apawglxc.png" alt="image-20210124152146695" style="zoom:200%;" />

**浮动的盒子不会有外边距合并的问题！**

**解决方案**：

- 可以为父元素定义上边框；

  ```css
  border: 1px solid transparent;
  ```

- 可以为父元素定义上内边距；

  ```css
  padding: 1px;
  ```

- 可以为父元素添加`overflow: hidden`。

  ```css
  overflow: hidden;
  ```



### 13.9. 清除内外边距

网页元素很多都带有默认的内外边距，而且不同的浏览器默认的也不一致。

**因此我们在布局前，首先要清除下网页元素的内外边距**。

```css
/* 这句话是 CSS 的第一行代码 */
* {
    /* 清除内边距 */
    padding: 0;

    /* 清除外边距 */
    margin: 0;
}
```

**注意：行内元素为了照顾兼容性，尽量只设置左右内外边距，不要设置上下内外边距。但是转换为块级元素就可以了**。



### 13.10. 案例

**(1) 简单盒子布局**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>036-综合在线案例</title>

    <style>
        * {
            padding: 0;
            margin: 0;
        }

        body {
            background-color: #f5f5f5;
        }

        .box {
            /* border: 1px solid red; */
            width: 298px;
            height: 415px;
            background-color: white;
            margin: 100px auto;
        }

        .box img {
            /* 设置图片宽度和外边盒子一样宽 */
            width: 100%;
        }

        .box p {
            /* border: 1px solid black; */
            height: 70px;
            font-size: 14px;
            padding: 0 28px;
            margin-top: 30px;
        }

        .box .appraise {
            /* border: 1px solid blue; */
            font-size: 12px;
            color: #b0b0b0;
            padding: 0 28px;
            margin-top: 20px;
        }

        .box .info {
            /* border: 1px solid green; */
            margin-top: 15px;
            padding: 0 28px;
        }

        .box .info h4 {
            font-size: 14px;
            font-weight: normal;
        }

        .box .info h4 a {
            /* border: 1px solid purple; */
            text-decoration: none;
            color: #333;

        }

        .box .info h4 em {
            /* 将 em 的斜线去掉 */
            font-style: normal;
            color: #ebe4e0;
            margin: 0 6px 0 10px;
        }

        .box .info h4 .price {
            color: #ff6700;
        }
    </style>

</head>

<body>
    <div class="box">
        <img src="../images/img.jpg" alt="img" />
        <p class="review">快递牛，整体不错蓝牙可以说秒连。红米给力</p>
        <div class="appraise">来自于 117384232 的评价</div>
        <div class="info">
            <h4>
                <a href="#">Redmi AirDots真无线蓝...</a>
                <em>|</em>
                <span class="price">99.99元</span>
            </h4>
        </div>
    </div>
</body>
```

![image-20210124193808527](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/简单盒子布局.2cw53q2gium8.png)



**(2) 快报案例**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>037-快报案例</title>

    <style>
        * {
            margin: 0;
            padding: 0;
        }

        .box {
            width: 248px;
            height: 163px;
            border: 1px solid #ccc;
            margin: 100px auto;
        }

        .box h3 {
            /* border: 1px solid red; */
            border-bottom: 1px dashed #ccc;
            font-weight: 400;
            font-size: 14px;
            height: 32px;
            line-height: 32px;
            padding-left: 15px;
            margin-bottom: 7px;
        }

        .box ul li {
            /* border: 1px solid red; */
            list-style-type: none;
            font-size: 12px;
            height: 23px;
            line-height: 23px;
        }

        .box ul li a {
            /* border: 1px solid red; */
            text-decoration: none;
            color: #666;
            margin-left: 20px;
        }

        .box ul li a:hover {
            text-decoration: underline;
        }
    </style>

</head>

<body>
    <div class="box">
        <h3>品优购快报</h3>
        <ul>
            <li><a href="#">【特惠】爆款耳机5折秒！</a></li>
            <li><a href="#">【特惠】母亲节，健康好礼低至5折！</a></li>
            <li><a href="#">【特惠】爆款耳机5折秒！</a></li>
            <li><a href="#">【特惠】9.9元洗100张照片！</a></li>
            <li><a href="#">【特惠】长虹智能空调立省1000</a></li>
        </ul>
    </div>
</body>
```

![image-20210124200826980](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/快报案例.202jj3dqa2f4.png)



### 13.11. 圆角边框

![image-20210124222601677](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/圆角边框.1e0by2xdtp8g.png)

**语法**：

```css
border-radius: length;
```

- 参数值可以为**数值或百分比**的形式；
- 如果是正方形，想要设置为一个圆，把数值修改为高度或者宽度的一半即可，或者直接写为50%；
- 如果是矩形，想要设置为一个圆角矩形，则数值修改为矩形高度的一半即可；
- `border-radius`是一个**简写属性**，可以跟四个值，分别代表左上角、右上角、右下角、左下角。

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/image.1rijp1rc0x34.png" alt="image-20210124223018575" style="zoom:150%;" />

### 13.12. 盒子阴影

**语法**：

```CSS
box-shadow: h-shadow v-shadow blur spread color inset;
```

| 值       | 描述                                     |
| -------- | ---------------------------------------- |
| h-shadow | 必需。水平阴影的位置。允许负值           |
| v-shadow | 必需。垂直阴影的位置。允许负值。         |
| blur     | 可选。模糊距离。                         |
| spread   | 可选。阴影的尺寸。                       |
| color    | 可选。阴影的颜色。                       |
| inset    | 可选。将外部阴影（outset）改为内部阴影。 |

**注意**：

- 默认的是外阴影（outset），但是不可以写这个单词，否则导致阴影无效；
- 盒子阴影不占用空间，不会影响其他盒子排列。

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>039-盒子阴影</title>

    <style>
        div {
            width: 200px;
            height: 200px;
            background-color: pink;
            margin: 0 auto;
        }

        /* 鼠标经过时会有阴影 */
        div:hover {
            box-shadow: 10px 10px 10px 10px rgba(0, 0, 0, .3);
        }
    </style>

</head>

<body>
    <div></div>
</body>
```

![image-20210129122127759](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/盒子阴影.5qw86d5kqn00.png)



## 14. 浮动

### 14.1. 何为浮动

**网页布局第一准则：多个块级元素纵向排列找标准流，多个块级元素横向排列找浮动**。

`float` 属性用于创建浮动框，将其移动到一边，直到左边缘或右边缘及包含块或另一个浮动框的边缘。

**语法**：

```css
选择器 {
    float: 属性值;
}
```

| 属性值 | 描述               |
| ------ | ------------------ |
| none   | 元素不浮动（默认） |
| left   | 元素向左浮动       |
| right  | 元素向右浮动       |



```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>040-浮动</title>

    <style>
        .box {
            width: 500px;
            height: 200px;
            /* background-color: black; */
            border: 1px solid red;
            ;
            margin: 0 auto;
        }

        .box .left {
            width: 100px;
            height: 100px;
            background-color: pink;
            float: left;
        }

        .box .right {
            width: 100px;
            height: 100px;
            background-color: skyblue;
            float: right;
        }
    </style>

</head>

<body>
    <div class="box">
        <div class="left"></div>
        <div class="right"></div>
    </div>
</body>
```

![image-20210129125133911](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/浮动.2k2487wopmy0.png)



### 14.2. 浮动脱标

设置了浮动（float）的元素最重要的特性：

- 脱离标准普通流的控制，移动到指定的位置；
- **浮动的盒子不再保留原来的位置**。

![image-20210129143809166](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/浮动脱标.3f9zhc159cu0.png)

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>041-浮动脱标</title>

    <style>
        .box {
            width: 500px;
            height: 300px;
            border: 1px solid red;
            margin: 0 auto;
        }

        /* 设置了浮动的盒子会飞起来 */
        .box .float {
            width: 100px;
            height: 100px;
            background-color: pink;
            float: left;
        }

        .box .standard {
            width: 150px;
            height: 150px;
            background-color: skyblue;
        }
    </style>

</head>

<body>
    <div class="box">
        <div class="float"></div>
        <div class="standard"></div>
    </div>
</body>
```

![image-20210129143845347](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/浮动脱标案例.1ex7vlqtoa5c.png)





### 14.3. 浮动元素一行显示

![image-20210129145007077](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/浮动元素一行显示.dqi2w04vcxk.png)



### 14.4. 浮动元素具有行内块特性

**浮动元素会具有行内块元素特性**。

**任何元素都可以浮动。不管原先是什么模式的元素，添加浮动后具有行内块元素相似的特性**。

- 如果块级盒子没有设置宽度，默认宽度和父级一样宽，但是添加浮动后，它的大小根据内容来决定；
- 浮动的盒子中间是没有缝隙的，是紧挨着的；
- 行内元素同理。

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>043-浮动元素具有行内块特性</title>

    <style>
        /* 如果行内元素需要浮动,则不需要将行内元素转换为块级元素, 就可以直接给宽和高*/
        span,
        div {
            width: 200px;
            height: 200px;
            background-color: pink;
            float: left;
        }

        p {
            height: 300px;
            background-color: purple;
            color: white;
            float: right;
        }
    </style>

</head>

<body>
    <span>1</span>
    <span>2</span>
    <div>div</div>
    <p>段落</p>
</body>
```

![image-20210129150403928](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/浮动元素具有行内块的性质.15p01nq2plj4.png)



### 14.5. 浮动元素搭配标准流父元素

为了约束浮动元素位置，我们网页布局一般采取的策略是：

**先用标准流的父元素排列上下位置，之后內部子元素采取浮动排列左右位置，符合网页布局第一准则**。

![image-20210129150933496](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/浮动元素搭配标准流父盒子.5dc1j7laj7o0.png)



### 14.6. 案例

**(1) 小米布局案例**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>浮动元素搭配标准流父盒子</title>

    <style>
        * {
            margin: 0;
            padding: 0;
        }

        .box {
            width: 1200px;
            height: 460px;
            background-color: pink;
            margin: 0 auto;
        }

        .box .left {
            width: 230px;
            height: 460px;
            background-color: #55585a;
            float: left;
        }

        .box .right {
            width: 970px;
            height: 460px;
            background-color: skyblue;
            float: left;
        }

        .box .left a {
            display: block;
            text-decoration: none;
            color: #fff;
            height: 65.7px;
            line-height: 65.7px;
            /* border: 1px solid red; */
            padding-left: 30px;
        }

        .box .left a:hover {
            background-color: #ff6700;
        }

        .box .right img {
            width: 970px;
            height: 460px;
        }
    </style>

</head>

<body>
    <div class="box">
        <div class="left">
            <a href="#">手机 电话卡</a>
            <a href="#">电视 盒子</a>
            <a href="#">笔记本 平板</a>
            <a href="#">出行 穿戴</a>
            <a href="#">智能 路由器</a>
            <a href="#">健康 儿童</a>
            <a href="#">耳机 音响</a>
        </div>
        <div class="right">
            <img src="../images/mobile.webp" />
        </div>
    </div>
</body>
```

![image-20210129153617740](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/小米布局案例.3ui8e6wqee00.png)



**(2) 布局**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>045-浮动元素搭配标准流父盒子2</title>

    <style>
        * {
            margin: 0;
            padding: 0;
        }

        .box {
            width: 499px;
            height: 200px;
            background-color: pink;
            margin: 0 auto;
            list-style: none;
        }

        .box li {
            float: left;
            width: 100px;
            height: 200px;
            background-color: purple;
            margin-right: 33px;
            color: white;
        }

        /* 权重！ */
        .box .last {
            margin-right: 0;
        }
    </style>

</head>

<body>
    <ul class="box">
        <li>1</li>
        <li>2</li>
        <li>3</li>
        <li class="last">4</li>
    </ul>
</body>
```

![image-20210129211935400](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/布局.1vueynd5sncw.png)



**(3) 手机模块**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>046-手机模块</title>

    <style>
        * {
            margin: 0;
            padding: 0;
        }

        .box {
            width: 1226px;
            height: 615px;
            background-color: pink;
            margin: 0 auto;
        }

        .box .left {
            width: 234px;
            height: 615px;
            background-color: purple;
            float: left;
        }

        .box .right {
            width: 992px;
            height: 615px;
            background-color: skyblue;
            float: left;
        }

        .box .right ul {
            list-style: none;
        }

        .box .right ul li {
            width: 234px;
            height: 300px;
            background-color: pink;
            float: left;
            margin-left: 14px;
            margin-bottom: 14px;
        }
    </style>

</head>

<body>
    <div class="box">
        <div class="left">左边</div>
        <div class="right">
            <ul>
                <li>1</li>
                <li>2</li>
                <li>3</li>
                <li>4</li>
                <li>5</li>
                <li>6</li>
                <li>7</li>
                <li>8</li>
            </ul>
        </div>
    </div>
</body>
```

![image-20210129213056122](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/手机模块布局.6jh6c2ez1kk0.png)



### 14.7. 浮动布局注意

**1、浮动和标准流的父盒子搭配**：先用标准流的父元素排列上下位置，之后内部子元素采取浮动排列左右位置。

**2、一个元素浮动了，理论上其余的兄弟元素也要浮动**：浮动的盒子只会影响浮动盒子后面的标准流，不会影响到前面的标准流。



### 14.8. 清除浮动

我们前面浮动元素有一个标准流的父元素，它们有一个共同的特点，都是有高度的！

但是，所有的父盒子都必须有高度吗？

理想中的状态，让子盒子撑开父盒子，有多少孩子，父盒子就有多高！



**(1) 为什么需要清除浮动**？

由于父级盒子很多情况下，不方便给高度，但是子盒子浮动又不占有位置，最后父盒子高度为0时，就会影响下面的标准流盒子。

![image-20210129222607346](E:\Typora\image\image-20210129222607346.png)

**由于浮动元素不再占用原来文档流的位置，所以它会对后面的元素排版产生影响**。



**(2) 清除浮动本质**：

- 清除浮动的本质即清除浮动造成的影响。

- **如果父盒子本身有高度，则不需要清除浮动**。
- **清除浮动之后，父级盒子就会根据浮动的子盒子自动检测高度，父级有了高度，就不会影响下面的标准流了**。

**语法**：

```css
选择器: {
    clear: 属性值;
}
```

| 属性值 | 描述                       |
| ------ | -------------------------- |
| left   | 清除左侧浮动影响           |
| right  | 清除右侧浮动影响           |
| both   | 同时清除左右两侧浮动的影响 |

实际上，几乎只用`clear: both`。

**清除浮动的策略：闭合浮动**。



### 14.9. 清除浮动的方法

**(1) 额外标签法**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>047-清除浮动-额外标签法</title>

    <style>
        .box {
            width: 500px;
            border: 1px solid red;
            margin: 0 auto;
        }

        .box .one {
            width: 200px;
            height: 200px;
            background-color: purple;
            float: left;
        }

        .box .two {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            float: left;
        }

        .footer {
            height: 300px;
            background-color: black;
        }

        /* 清除浮动 */
        .clear {
            clear: both;
        }
    </style>

</head>

<body>
    <div class="box">
        <div class="one">1</div>
        <div class="two">2</div>

        <!-- 额外标签 -->
        <div class="clear"></div>
    </div>

    <div class="footer">footer</div>
</body>
```

**注意：要求这个新的空标签必须是块级元素**。



**(2) 清除浮动——父元素添加overflow** 

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/清除浮动.4qwjexp69hc0.png" alt="image-20210130114118260" style="zoom:150%;" />



**(3) 清除浮动—— :after伪元素法(给父元素添加，建议使用)**

**语法**：

```css
.clearfix::after {
    content: "";
    display: block;
    height: 0;
    clear: both;
    visibility: hidden;
}

.clearfix {
    *zoom: 1;
}
```



```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>049-清除浮动-after伪元素</title>

    <style>
        /* 清除浮动 */
        .clearfix::after {
            content: "";
            display: block;
            height: 0;
            clear: both;
            visibility: hidden;
        }

        .clearfix {
            *zoom: 1;
        }

        .box {
            width: 500px;
            border: 1px solid red;
            margin: 0 auto;
        }

        .box .one {
            width: 200px;
            height: 200px;
            background-color: purple;
            float: left;
        }

        .box .two {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            float: left;
        }

        .footer {
            height: 300px;
            background-color: black;
        }
    </style>

</head>

<body>
    <!-- 给父元素添加清除浮动 -->
    <div class="box clearfix">
        <div class="one">1</div>
        <div class="two">2</div>
    </div>

    <div class="footer">footer</div>
</body>
```



**(4) 清除浮动——双伪元素清除浮动(给父元素添加)**

**语法**：

```css
/* 清除浮动 */
.clearfix::before,
.clearfix::after {
    content: "";
    display: table;
}

.clearfix::after {
    clear: both;
}

.clearfix {
    *zoom: 1;
}
```



## 15. 学成在线案例

### 15.1. CSS属性书写顺序

![image-20210130130634358](E:\Typora\image\image-20210130130634358.png)



### 15.2. 头部制作

**(1) 导航栏**

<img src="E:\Typora\image\image-20210130143829295.png" alt="image-20210130143829295" style="zoom:150%;" />



**(2) search搜索框**

![image-20210130150230811](E:\Typora\image\image-20210130150230811.png)



**(3) search搜索框button按钮制作**

![image-20210130151331356](E:\Typora\image\image-20210130151331356.png)

### 15.3. banner制作

<img src="E:\Typora\image\image-20210130152647116.png" alt="image-20210130152647116" style="zoom:150%;" />





## 16. 定位

### 16.1. 定位简介

**(1) 为什么需要定位**？

题问：以下情况使用标准流或浮动能实现吗？

- 某个元素可以自由的在一个盒子内移动位置，并且压住其他盒子；
- 当我们滚动窗口的时候，盒子是固定屏幕某个位置的。



**(2) 定位组成**

**定位**：将盒子定在某一个位置，所以定位也是在摆放盒子，按照定位的方式移动盒子。

**定位 = 定位模式 + 边偏移**。

- **定位模式**用于指定一个元素在文档中的定位方式；
- **边偏移**则决定了该元素的最终位置。



**(3) 定位模式**

定位模式决定元素的定位方式，通过CSS的 **position** 来设置，其值可以分为四个：

| 值       | 语义     |
| -------- | -------- |
| static   | 静态定位 |
| relative | 相对定位 |
| absolute | 绝对定位 |
| fixed    | 固定定位 |



**(4) 边偏移**

![image-20210130220150274](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/边偏移.7egoadxiexw0.png)



### 16.2. 静态定位

静态定位是元素的**默认定位方式，无定位的意思**。

**语法**：

```css
选择器 {
    position: static;
}
```

- 静态定位按照标准流特性摆放位置，它没有边偏移；
- 静态定位在布局时很少用到。



### 16.3 相对定位(重要)

**相对定位**是元素在移动位置的时候，是相对于它**原来的位置**来说的（自恋型）。

**语法**：

```css
选择器 {
    position: relative;
}
```



**相对定位特点**：

- 它是相对于自己原来的位置来移动的（**移动位置的时候参照点是自己原来的位置**）；
- 原来在标准流的位置继续占有，后面的盒子仍然以标准流的方式对待它。（**不脱标，继续保留原来的位置**）。

**因此，相对定位并没有脱标，它最典型的应用是用来限制绝对定位的**。



```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>050-相对定位</title>

    <style>
        * {
            margin: 0;
            padding: 0;
        }

        .box1 {
            /* 相对定位 */
            position: relative;
            top: 50px;
            left: 20px;

            width: 200px;
            height: 200px;
            background-color: pink;
        }

        .box2 {
            width: 200px;
            height: 200px;
            background-color: skyblue;
        }
    </style>

</head>

<body>
    <div class="box1"></div>
    <div class="box2"></div>
</body>
```

![image-20210130222324449](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/相对定位.3uc860s0tk20.png)





### 16.4. 绝对定位(重要)

绝对定位是元素在移动的时候，相对于它的**祖先元素**来说的（拼爹型）。

**绝对定位的特点**：

- 如果没有**祖先元素或者祖先元素没有定位**，则以浏览器为准定位（Document文档）；
- 如果祖先元素有定位（相对、绝对、固定定位），则以最近一级的有定位祖先元素为参考点移动位置；
- 绝对定位不再占有原来的位置（**脱离标准流**）。





**(1) 父级没有定位的情况**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>051-绝对定位</title>

    <style>
        .box {
            width: 500px;
            height: 500px;
            background-color: gray;
            margin: 0 auto;
        }

        .box .box1 {
            position: absolute;
            left: 100px;
            top: 150px;

            width: 200px;
            height: 200px;
            background-color: pink;
        }

        .box .box2 {
            width: 200px;
            height: 200px;
            background-color: skyblue;
        }
    </style>

</head>

<body>
    <div class="box">
        <div class="box1"></div>
        <div class="box2"></div>
    </div>
</body>
```

![image-20210130223439771](E:\Typora\image\image-20210629195024129.png)



**(2) 父级元素有定位，绝对定位脱标**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>051-绝对定位</title>

    <style>
        .box {
            /* 父级盒子有定位了 */
            position: relative;

            width: 500px;
            height: 500px;
            background-color: gray;
            margin: 0 auto;
        }

        .box .box1 {
            /* box1绝对定位 */
            position: absolute;
            left: 100px;
            top: 150px;

            width: 200px;
            height: 200px;
            background-color: pink;
        }

        .box .box2 {
            width: 200px;
            height: 200px;
            background-color: skyblue;
        }
    </style>

</head>

<body>
    <div class="box">
        <div class="box1"></div>
        <div class="box2"></div>
    </div>
</body>
```

![image-20210130224738431](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/绝对定位.5a3us6fe9zc0.png)



### 16.5. 子绝父相

![image-20210131123034729](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/子绝父相.6junyjq79fk0.png)



### 16.6. 固定定位(重要)

**固定定位是元素固定于浏览器可视区的位置**。

主要使用场景：可以在浏览器页面滚动时元素的位置不会改变。

**固定定位的特点**：

- 以浏览器的可视窗口为参照点移动元素；
  - 和父元素没有任何关系。
  - 不随浏览器滚动条的滚动而发生变化。 
- **固定定位不再占有原先的位置**。

固定定位也是脱标的，其实固定定位也可以看作是一种特殊的绝对定位。



### 16.6. 固定定位小技巧

**固定到版心右侧**。

**算法**：

- 让固定定位的盒子 `left:50%`，走到浏览器可视区（也可以看做版心）的一半位置；
- 让固定定位的盒子 `margin-left：版心宽度一半的距离。`



```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>052-固定定位小技巧</title>

    <style>
        .w {
            width: 800px;
            height: 3000px;
            margin: 200px auto;
            background-color: pink;
        }

        .box {
            /* 固定定位 */
            position: fixed;
            left: 50%;
            margin-left: 400px;

            width: 100px;
            height: 100px;
            background-color: skyblue;
        }
    </style>

</head>

<body>
    <div class="box"></div>
    <div class="w"></div>
</body>
```

![image-20210131133730088](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/固定定位.1xjgog1n8wn4.png)



### 16.7. 粘性定位sticky

**粘性定位**可以看成是相对定位和固定定位的混合。

**语法**：

```css
选择器 {
    position: sticky;
    top: 10px;
}
```

**粘性定位特点**：

- 以浏览器的可视窗口为参照点移动元素（固定定位特点）；
- **粘性定位占有原来的位置**（相对定位特点）；
- 必须添加top、left、right、bottom其中一个才有效。

**兼容性差，IE不支持**。



### 16.8. 定位总结

![image-20210131135445741](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/定位总结.zgzue075s4w.png)



### 16.9. 定位叠放次序z-index

在使用定位布局时，可能会出现盒子重叠的情况。

此时，可以使用 z-index 来控制盒子的前后次序（z轴）。

**语法**：

```css
选择器 {
    z-index: 1;
}
```

- 数值可以是正整数，负整数或0，默认是auto，数值越大，盒子越靠上；
- 如果`z-index`值相同，则按照书写顺序，后来居上；
- **数字后面不能加单位**；
- **只有定位的盒子才有 z-index 属性**。



### 16.10. 绝对定位盒子居中

加了绝对定位的盒子不能通过 `margin: 0 auto`水平居中，但是可以通过以下计算方法实现水平和垂直居中。

![image-20210131170602027](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/绝对定位盒子居中.202cfvibqti8.png)



```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>055-绝对定位盒子水平居中</title>

    <style>
        .box {
            position: absolute;
            /* 1、left走50%，父容器宽度的一半 */
            left: 50%;

            /* 2、Margin负值往左边走 自己盒子宽度的一半 */
            margin-left: -100px;

            width: 200px;
            height: 200px;
            background-color: pink;
        }
    </style>

</head>

<body>
    <div class="box"></div>
</body>
```

![image-20210131170656206](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/绝对定位盒子居中.1trkzjkcrm9s.png)

### 16.11. 定位的特殊特性

**绝对定位和固定定位也和浮动类似**：

- 行内元素添加绝对/固定定位，可以直接设置高度和宽度；
- 块级元素添加绝对或者固定定位，如果不给宽度或者高度，默认大小是内容大小。



**脱标的盒子不会触发外边距塌陷**：

- 浮动、绝对定位（固定定位）元素都不会触发外边距合并问题。



**绝对定位（固定定位）会完全压住盒子，盒子里的文字也会被压住**

- 浮动元素不同，只会压住它下面标准流的盒子，但是不会压住下面标准流盒子里面的文字（图片）；
- 但是绝对定位（固定定位）会压住下面标准流所有的内容。

![image-20210131173437220](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/浮动的特点.19mlbwiz2ylc.png)



## 17. 淘宝轮播图

### 17.1. CSS做轮播图

![image-20210131202130552](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/淘宝轮播图布局.5f3gbmqzmrw0.png)

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>056-淘宝轮播图</title>

    <style>
        * {
            margin: 0;
            padding: 0;
        }

        a {
            text-decoration: none;
        }

        li {
            list-style: none;
        }

        .tb-promo {
            position: relative;

            width: 520px;
            height: 280px;
            background-color: pink;
            margin: 100px auto;
        }

        .tb-promo img {
            width: 520px;
            height: 280px;
        }

        /* 并集选择器可以集体声明相同的样式 */
        .prev,
        .next {
            position: absolute;
            top: 50%;
            margin-top: -15px;

            width: 20px;
            height: 30px;
            line-height: 30px;
            color: #fff;
            background-color: rgba(0, 0, 0, .3);
            text-align: center;
        }

        .tb-promo .prev {
            left: 0;

            /* 圆角的做法 */
            border-top-right-radius: 15px;
            border-bottom-right-radius: 15px;
        }

        .tb-promo .next {
            right: 0;

            /* 圆角的做法 */
            border-top-left-radius: 15px;
            border-bottom-left-radius: 15px;
        }

        .tb-promo .promo-nav {
            position: absolute;
            left: 50%;
            margin-left: -35px;
            bottom: 15px;


            width: 70px;
            height: 13px;
            /* background-color: pink; */
            background: rgba(255, 255, 255, 0.5);

            /* 圆角矩形 */
            border-radius: 6px;
        }

        .tb-promo .promo-nav li {
            float: left;
            margin: 3px;

            width: 8px;
            height: 8px;
            background-color: #fff;
            border-radius: 4px;
        }

        .tb-promo .promo-nav .selected {
            background-color: #ff5000;
        }
    </style>

</head>

<body>
    <div class="tb-promo">
        <img src="../images/tb.jpg" />

        <!-- 左侧箭头 -->
        <a href="#" class="prev"> &lt; </a>

        <!-- 右侧箭头 -->
        <a href="#" class="next"> &gt; </a>

        <!-- 圆点按钮 -->
        <ul class="promo-nav">
            <li class="selected"></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
</body>
```

![image-20210131210818638](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/淘宝轮播图.56ku448mml00.png)



### 17.2. vue实现轮播图

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>index</title>

    <style>
        * {
            margin: 0;
            padding: 0;
        }

        a {
            text-decoration: none;
        }

        li {
            list-style: none;
        }

        .tb-promo {
            position: relative;

            width: 520px;
            height: 280px;
            background-color: pink;
            margin: 100px auto;
        }

        .tb-promo img {
            width: 520px;
            height: 280px;
        }

        /* 并集选择器可以集体声明相同的样式 */
        .prev,
        .next {
            position: absolute;
            top: 50%;
            margin-top: -15px;

            width: 20px;
            height: 30px;
            line-height: 30px;
            color: #fff;
            background-color: rgba(0, 0, 0, .3);
            text-align: center;
        }

        .tb-promo .prev {
            left: 0;

            /* 圆角的做法 */
            border-top-right-radius: 15px;
            border-bottom-right-radius: 15px;
        }

        .tb-promo .next {
            right: 0;

            /* 圆角的做法 */
            border-top-left-radius: 15px;
            border-bottom-left-radius: 15px;
        }

        .tb-promo .promo-nav {
            position: absolute;
            left: 50%;
            margin-left: -35px;
            bottom: 15px;


            width: 70px;
            height: 13px;
            /* background-color: pink; */
            background: rgba(255, 255, 255, 0.5);

            /* 圆角矩形 */
            border-radius: 6px;
        }

        .tb-promo .promo-nav li {
            float: left;
            margin: 3px;
        }

        .tb-promo .promo-nav li a {
            display: block;

            width: 8px;
            height: 8px;
            background-color: #fff;
            border-radius: 4px;
        }

        .tb-promo .promo-nav .selected {
            background-color: #ff5000;
        }
    </style>

</head>

<body>
    <div id="app">
        <div class="tb-promo" @mouseover="mouseHover()" @mouseout="mouseOut()">

            <img :src="lists[currentIndex]" />

            <!-- 左侧箭头 -->
            <a href="#" class="prev" @click.prevent="prevClick()"> &lt; </a>

            <!-- 右侧箭头 -->
            <a href="#" class="next" @click.prevent="nextClick()"> &gt; </a>

            <!-- 圆点按钮 -->
            <ul class="promo-nav">
                <li v-for="(item, index) in lists" :key="index">
                    <a href="#" :class="{selected: isselected(index)}" @click.prevent="btnClick(index)"></a>
                </li>
            </ul>
        </div>
    </div>


</body>

<script src="js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                lists: ["image/1.jpg", "image/2.jpg", "image/3.jpg", "image/4.jpg", "image/5.jpg"],
                currentIndex: 0,     // 默认显示图片
                timer: null,         // 定时器
            }
        },

        methods: {
            // 获得图片列表的长度
            getListsLength() {
                return this.lists.length;
            },

            // 判断轮播图下方按钮是否被选中
            isselected(index) {
                return this.currentIndex == index;
            },

            // 监听轮播图下方按钮点击
            btnClick(index) {
                this.currentIndex = index;
            },

            // 监听轮播图next按钮的点击
            nextClick() {
                if (this.currentIndex < this.getListsLength() - 1) {
                    this.currentIndex++;
                } else {
                    this.currentIndex = 0;
                }
            },

            // 监听轮播图prev按钮的点击
            prevClick() {
                if (this.currentIndex > 0) {
                    this.currentIndex--;
                } else {
                    this.currentIndex = this.getListsLength() - 1;
                }
            },

            // 定时器
            runInv() {
                this.timer = setInterval(() => {
                    this.nextClick();
                }, 3000);
            },

            // 监听鼠标经过轮播图清除定时器
            mouseHover() {
                clearInterval(this.timer);
            },

            // 监听鼠标移出轮播图
            mouseOut() {
                this.runInv();
            }
        },

        mounted() {
            this.runInv();
        },
    });
</script>

</html>
```



## 18. 网页布局总结

![image-20210201094952322](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/网页布局总结.1xw5y1v4n880.png)



## 19. 元素的显示与隐藏

类似网站广告，点击关闭就不见了，但是重新刷新页面，会重新出现！

### 19.1. display(重要)

display属性用于设置一个元素如何显示。

```css
display: none;  /* 隐藏对象 */
display: block; /* 除了转换为块级元素外，同时还有显示元素的意思 */
```

**display隐藏之后，元素不再占有原来的位置**。

应用及其广泛，搭配 JS 可以做很多效果。



### 19.2. visibility

![image-20210201100759881](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/visibility.mi6egohaops.png)



### 19.3. overflow溢出

![image-20210201101517632](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/overflow溢出.16v8q2ina6ak.png)



一般情况下，我们不想让溢出的内容显示出来，因为溢出的部分会影响布局；

**但是如果有定位的盒子，慎用 overflow: hidden 因为它会隐藏多余的部分**。



### 19.4. 土豆遮罩层案例

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>057-模仿土豆遮罩案例</title>

    <style>
        .box {
            position: relative;

            width: 444px;
            height: 320px;
            background-color: pink;
            margin: 30px auto;
        }

        .box img {
            width: 100%;
            height: 100%;
        }

        .box .mask {
            /* 隐藏遮罩层 */
            display: none;

            position: absolute;
            top: 0;
            left: 0;

            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, .4) url(../images/arr.png) no-repeat center;
        }

        /* 当鼠标经过盒子，让盒子里面的遮罩层显示出来 */
        .box:hover .mask {
            display: block;
        }
    </style>

</head>

<body>
    <div class="box">
        <div class="mask"></div>
        <img src="../images/tudou.jpg" alt="" />
    </div>
</body>
```

![image-20210201103901036](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/土豆遮罩层案例.5y0ml40d2s00.png)



# 二、CSS高级技巧

## 1. 精灵图

### 1.1. 为什么需要精灵图

![image-20210201104333906](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/为什么使用精灵图.ddb38i3t2i8.png)



### 1.2. 精灵图的使用

![image-20210201110214190](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/精灵图的使用.6fsjm9ud1mw0.png)

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>001-精灵图的使用</title>

    <style>
        .box1 {
            width: 60px;
            height: 60px;
            margin: 100px auto;
            background: url(../images/sprites.png) no-repeat;
            background-position: -182px 0;
        }

        .box2 {
            width: 27px;
            height: 25px;
            margin: 50px auto;
            background: url(../images/sprites.png) no-repeat;
            background-position: -155px -106px;
        }
    </style>

</head>

<body>
    <div class="box1"></div>
    <div class="box2"></div>
</body>
```

![image-20210201112309120](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/精灵图的应用.rvrafzv8kz4.png)



## 2. 字体图标

### 2.1. 字体图标优点

字体图标使用场景：主要用于显示网页中通用、常用的一些小图标。

精灵图有诸多优点，但是缺点很明显。

1. 图片文件还是比较大的；
2. 图片本身放大和缩小会失真；
3. 一旦图片制作完毕想要更换非常复杂。

**字体图标展示的是图标，但是本质展示的是字体**。



### 2.2. 字体图标使用

**步骤**：

1. 字体图标的下载；
2. 字体图标的引入（引入到HTML页面中）；
3. 字体图标的追加（以后添加新的小图标）。

**推荐下载网站**：

- https://icomoon.io/
- https://www.iconfont.cn/



![image-20210201120456128](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/字体文件格式.aamubsc2koc.png)



**字体图标的引入**：在下载好的字体图标中直接复制就可以了！

![image-20210201120944372](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/字体图标的引入.7gq2nfd73ng.png)

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>002-字体图标的使用</title>

    <style>
        @font-face {
            font-family: 'icomoon';
            src: url('fonts/icomoon.eot?yxikwm');
            src: url('fonts/icomoon.eot?yxikwm#iefix') format('embedded-opentype'),
                url('fonts/icomoon.ttf?yxikwm') format('truetype'),
                url('fonts/icomoon.woff?yxikwm') format('woff'),
                url('fonts/icomoon.svg?yxikwm#icomoon') format('svg');
            font-weight: normal;
            font-style: normal;
            font-display: block;
        }

        span {
            font-family: 'icomoon';
            font-size: 30px;
            color: pink;
        }
    </style>

</head>

<body>
    <!-- 重点 -->
    <span></span>
</body>
```



### 2.3. 字体图标的追加

如果工作中，原来的字体图标不够用了，我们需要添加新的字体图标到原来的字体文件中。

把压缩包里面的 `selection.json` 重新上传，然后选中自己想要的新的图标，重新下载压缩包，并替换原来的文件即可。

![image-20210201145049887](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/字体图标的追加.32m71nlqz8s0.png)



## 3. CSS三角

### 3.1. 三角的画法

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>003-CSS三角</title>

    <style>
        .box1 {
            width: 0;
            height: 0;
            border-top: 10px solid pink;
            border-left: 10px solid red;
            border-bottom: 10px solid blue;
            border-right: 10px solid green;

            margin: 0 auto;
        }

        /* 三角形的画法 */
        .box2 {
            width: 0;
            height: 0;
            line-height: 0;
            font-size: 0;
            border: 10px solid transparent;
            border-top-color: pink;

            margin: 30px auto;
        }
    </style>

</head>

<body>
    <div class="box1"></div>
    <div class="box2"></div>
</body>
```

![image-20210201150405738](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/CSS画三角.31vbcax1ibs0.png)



**语法**：

```css
.box2 {
    width: 0;
    height: 0;
    line-height: 0;
    font-size: 0;
    
    /* 三角的大小取决于像素值 */
    border: 10px solid transparent;
    border-top-color: pink;

    margin: 30px auto;
}
```





### 3.2. 京东三角

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>004-京东三角</title>

    <style>
        .jd {
            position: relative;

            width: 100px;
            height: 100px;
            background-color: pink;
            margin: 30px auto;
        }

        .jd .triangle {
            position: absolute;
            top: -20px;
            right: 10px;

            width: 0;
            height: 0;
            line-height: 0;
            font-size: 0;
            border: 10px solid transparent;
            border-bottom-color: pink;
        }
    </style>

</head>

<body>
    <div class="jd">
        <div class="triangle"></div>
    </div>
</body>
```

![image-20210201151423915](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/京东三角.1h3lgn9hxh6o.png)



## 4. 用户界面

### 4.1. 鼠标样式cursor

**语法**：

```css
li {
	cursor: pointer;
}
```

| 属性值      | 描述     |
| ----------- | -------- |
| default     | 小白默认 |
| pointer     | 小手     |
| move        | 移动     |
| text        | 文本     |
| not-allowed | 禁止     |



### 4.2. 取消表单轮廓

给表单添加 `outline: 0;` 或者 `outline: none;` 样式之后，就可以去掉默认的蓝色边框。 

```css
input {
	outline: none;
}
```



### 4.3. 防止拖拽文本域

```css
textarea {
    resize: none;
}
```



## 5. 行内块和文字垂直居中

![image-20210201162255131](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/行内块和文字垂直居中.6nuwy8tfzxk0.png)



`img、textarea`  行内块元素都默认和文字基线对齐。



## 6. 图片底部默认空白缝隙

**图片底部默认空白缝隙：原因是因为图片默认和文字基线对齐**。

主要解决方法有两种：

1. 给图片添加 `vertical-align: bottom;`;
2. 把图片转换为块级元素 `display: block`。

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>图片底部默认空白缝隙</title>

    <style>
        div {
            border: 2px solid red;
        }

        div img {
            vertical-align: bottom;
        }
    </style>

</head>

<body>
    <div>
        <img src="../images/tudou.jpg" /> 你好Hello World!
    </div>
</body>
```

![image-20210201163805102](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/文字底部默认空白缝隙.6yc899siy3c0.png)



## 7. 溢出文字省略号显示

### 7.1. 单行文本

![image-20210201164045077](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/单行文本溢出.6dn5hbowh8s0.png)



```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>溢出文字省略号显示</title>

    <style>
        div {
            width: 150px;
            height: 80px;
            background-color: pink;
            margin: 100px auto;

            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis
        }
    </style>

</head>

<body>
    <div>
        设也不说，此处省略一万字
    </div>
</body>
```

![image-20210201164459439](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/单行文本溢出案例.2w26e1me6oo0.png)



### 7.2. 多行文本

![image-20210201165021968](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/多行文本.2iaesyxwobu0.png)



## 8. 常见布局技巧

### 8.1. margin负值的应用

![image-20210201184427235](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/margin负值的应用.10s5r9mlm59c.png)

**(1) 细线边框**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>margin负值的应用</title>

    <style>
        ul li {
            float: left;

            list-style-type: none;
            width: 150px;
            height: 200px;
            border: 2px solid black;
            margin-left: -2px;
        }
    </style>

</head>

<body>
    <ul class="clearfix">
        <li>1</li>
        <li>2</li>
        <li>3</li>
        <li>4</li>
        <li>5</li>
    </ul>
</body>
```

![image-20210201190042663](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/margin负值应用案例.4igvjpacmsc0.png)



**(2) 鼠标经过边框变色**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>margin负值的应用</title>

    <style>
        ul li {
            position: relative;
            float: left;

            list-style-type: none;
            width: 150px;
            height: 200px;
            border: 2px solid black;
            margin-left: -2px;
        }

        ul li:hover {
            /* 只有定位的盒子才能使用 z-index */
            z-index: 1;

            border-color: orange;
        }
    </style>

</head>

<body>
    <ul class="clearfix">
        <li>1</li>
        <li>2</li>
        <li>3</li>
        <li>4</li>
        <li>5</li>
    </ul>
</body>
```

![image-20210201190308531](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/鼠标经过盒子边框变色.1kco45uvgb28.png)



**注意：有相对定位的盒子默认会压住标准流的盒子，并且相对定位会保留原来盒子的位置**。



### 8.2. 文字围绕浮动元素

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>文字围绕浮动元素</title>

    <style>
        * {
            margin: 0;
            padding: 0;
        }

        .box {
            width: 300px;
            height: 70px;
            background-color: pink;
            margin: 0 auto;
        }

        .box .pic {
            float: left;

            width: 120px;
            height: 60px;
            margin: 5px 5px 3px 5px;
        }

        .box .pic img {
            width: 100%;
            height: 100%;
        }
    </style>

</head>

<body>
    <div class="box">
        <div class="pic">
            <img src="../images/img.png" />
        </div>
        <p>【集锦】热身赛-巴西0-1秘鲁 内马尔替补两人血染赛场</p>
    </div>
</body>
```

![image-20210201213929118](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/文字围绕浮动元素.2262bgqmk75s.png)



### 8.3. 行内块元素的巧用

**text-align: center; 可以使盒子内所有的行内元素和行内块元素水平居中**。

**行内块元素之间有缝隙**。

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>012-行内块的应用</title>

    <style>
        * {
            margin: 0;
            padding: 0;
        }

        .box {
            /* text-align: center; 可以使盒子内所有的行内元素和行内块元素水平居中 */
            text-align: center;
            margin-top: 50px;
        }

        .box a {
            /* 行内块元素中间有缝隙 */
            display: inline-block;

            width: 36px;
            height: 36px;
            line-height: 36px;
            text-decoration: none;
            text-align: center;
            background-color: #f7f7f7;
            color: #333;
            border: 1px solid #ccc;
            font-size: 14px;
        }

        .box .prev,
        .box .next {
            width: 85px;
        }

        .box .selected,
        .box .elp {
            border: none;
            background-color: #fff;
        }

        .box input {
            width: 45px;
            height: 36px;
            border: 1px solid #ccc;
            outline: none;
        }

        .box button {
            width: 60px;
            height: 36px;

            background-color: #f7f7f7;
            color: #333;
            border: 1px solid #ccc;
            outline: #333;
        }
    </style>

</head>

<body>
    <div class="box">
        <a href="#" class="prev">&lt;&lt;上一页</a>
        <a href="#">1</a>
        <a href="#" class="selected">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#" class="elp">...</a>
        <a href="#" class="next">&gt;&gt;下一页</a>

        到第
        <input type="text" name="" id="" />
        页

        <button>确定</button>
    </div>
</body>
```

![image-20210201214004613](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/行内块的应用.1ihwt0utz0w0.png)



### 8.4. CSS画直角三角形

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CSS三角秒用</title>

    <style>
        .box1 {
            width: 0;
            height: 0;

            /* 把上边框宽度调大 */
            border-top: 100px solid transparent;
            border-right: 50px solid pink;

            /* 左边和下边的边框宽度设置为 0 */
            border-bottom: 0 solid green;
            border-left: 0 solid skyblue;
        }
    </style>

</head>

<body>
    <div class="box1"></div>
</body>
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/CSS画直角三角形.6zqjlnduyx80.png" alt="image-20210201221554304" style="zoom:150%;" />



### 8.5. CSS三角妙用

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CSS三角秒用</title>

    <style>
        .price {
            width: 160px;
            height: 24px;
            line-height: 24px;
            border: 1px solid red;
            margin: 0 auto;
        }

        .price .now {
            position: relative;
            float: left;

            width: 90px;
            height: 100%;
            text-align: center;
            background-color: red;
            color: #fff;
            font-weight: 700;
            margin-right: 5px;
        }

        .price .now i {
            position: absolute;
            right: 0;
            top: 0;

            width: 0;
            height: 0;
            border-color: transparent #fff transparent transparent;
            border-style: solid;
            border-width: 24px 10px 0 0;
        }

        .price .past {
            font-size: 14px;
            color: gray;
            text-decoration: line-through;
        }
    </style>

</head>

<body>
    <div class="price">
        <span class="now">
            ￥1650
            <i></i>
        </span>
        <span class="past">￥2326</span>
    </div>
</body>
```

![image-20210201224528341](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/三角形妙用.4z9chhya33k0.png)

# 三、HTML5和CSS3提高

## 1. HTML5



### 1.1. 新增语义化标签

以前布局，基本用 div 来做。div 对于搜索引擎来说，是没有语义的。

- `<header>`：头部标签；
- `nav`：导航标签；
- `<article`：内容标签；
- `<section`：定义文档某个区域；
- `<aside>`：侧边栏标签；
- `<footer>`：尾部标签。

![image-20210202142023313](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/H5语义化标签.4z3xizfupfg0.png)



### 1.2. 视频标签

![image-20210202142228049](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/视频标签.2stsjsl1xz20.png)



**语法**：

```html
<video src="文件地址" controls="controls"></video>
```

```html
<video width="320" height="240" controls>
  <source src="movie.mp4" type="video/mp4">
  <source src="movie.ogg" type="video/ogg">
  <source src="movie.webm" type="video/webm">
  您的浏览器不支持 video 标签。
</video>
```

![image-20210202143226983](E:\Typora\image\image-20210629202011849.png)



### 1.3. 音频标签

![image-20210202143750520](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/音频标签.6zoo92l8sgk0.png)



### 1.4. 新增input类型

![image-20210202215200440](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/新增Input类型.73ldmtyny580.png)

### 1.5. 新增表单属性

![image-20210202221631155](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/新增表单属性.2vhzq4mg3e60.png)



## 2. CSS3

### 2.1. 属性选择器

![image-20210202225651851](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/新增属性选择器.42p18l14fme0.png)

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>003-属性选择器</title>

    <style>
        /* 必须都是input标签 但是同时具有name这个属性才会被选中 */
        .demo1 input[name] {
            color: pink;
        }

        /* 必须都是input标签 但是同时具有name="cde"这个属性才会被选中 */
        .demo2 input[name=cde] {
            color: skyblue;
        }

        /* div类名以icon开头 */
        .demo3 div[class^=icon] {
            width: 100px;
            height: 100px;
        }

        /* div类名以pink结尾 */
        .demo3 div[class$=pink] {
            background-color: pink;
        }

        /* div类名中含有skyblue */
        .demo3 div[class*=skyblue] {
            background-color: skyblue;
        }
    </style>

</head>

<body>
    <!-- 1、属性选择器可以不用借助类名和id -->
    <div class="demo1">
        <input type="text" value="123" name="abc" />
        <input type="text" value="123" />
    </div>

    <!-- 2、属性选择器可以选择 属性=值 的某些元素 -->
    <div class="demo2">
        <input type="text" value="123" name="abc" />
        <input type="text" value="123" name="cde" />
    </div>

    <!-- 3、属性选择器可以选择属性值开头的某些元素 -->
    <div class="demo3">
        <div class="icon1 pink">小图标1</div>
        <div class="icon2 pink">小图标2</div>
        <div class="icon3 skyblue">小图标3</div>
        <div class="icon4 skyblue">小图标4</div>
    </div>
</body>
```

![image-20210202225613974](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/属性选择器案例.3ky774i0esa0.png)



### 2.2. 结构伪类选择器

![image-20210202232531336](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/结构伪类选择器.6kmi03opafo.png)

### 2.3. nth-child(n)

![image-20210202231114693](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/nth-child.5u5zn5b0v000.png)

```css
/* 选出所有奇数行  <==> nth-child(2n+1) */
ul li:nth-child(odd) {
    background-color: #ccc;
}

/* 选出所有偶数行 <==> nth-child(2n) */
ul li:nth-child(even) {
    background-color: gray;
}
```



**隔行变色案例**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>004-nth-child(n)隔行变色</title>

    <style>
        /* 选出所有奇数行  <==> nth-child(2n+1) */
        ul li:nth-child(odd) {
            background-color: #ccc;
        }

        /* 选出所有偶数行 <==> nth-child(2n) */
        ul li:nth-child(even) {
            background-color: gray;
        }
    </style>

</head>

<body>
    <ul>
        <li>我是盒子1</li>
        <li>我是盒子2</li>
        <li>我是盒子3</li>
        <li>我是盒子4</li>
        <li>我是盒子5</li>
        <li>我是盒子6</li>
        <li>我是盒子7</li>
        <li>我是盒子8</li>
    </ul>
</body>
```

![image-20210202230951479](E:\Typora\image\image-20210202230951479.png)



### 2.4. nth-of-type(n)套路

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>005-nth-child(n)和nth-of-type(n)的区别.html</title>

    <style>
        /* nth-child(n) 会把所有的盒子都排列序号 */
        /* 执行的时候先看 :nth-child(1) 之后再回去看前面的 div */
        section div:nth-child(1) {
            background-color: pink;
        }

        /* nth-of-type(n) 会把指定类型的盒子排列序号 */
        /* 执行的时候先看 div(指定的元素) 之后再回去看 nth-of-type(1)  */
        section div:nth-of-type(1) {
            background-color: skyblue;
        }
    </style>

</head>

<body>
    <section>
        <p>1</p>
        <div>2</div>
        <div>3</div>
    </section>
</body>
```

![image-20210202232609011](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/nth-of-type.kzj39vn2b5c.png)



### 2.5. 伪元素选择器(重点)

伪元素选择器可以帮助我们利用CSS创建新的标签元素，而不需要HTML标签，从而简化HTML结构。

| 选择符   | 描述                     |
| -------- | ------------------------ |
| ::before | 在元素内部的前面插入内容 |
| ::after  | 在元素内部的后面插入内容 |

![image-20210203094744044](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/伪元素选择器.3syztchmdrs0.png)



### 2.6. 伪元素字体图标

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> 伪元素字体图标</title>

    <style>
        /* 引入字体图标 */
        @font-face {
            font-family: 'icomoon';
            src: url('fonts/icomoon.eot?dtgvx2');
            src: url('fonts/icomoon.eot?dtgvx2#iefix') format('embedded-opentype'),
                url('fonts/icomoon.ttf?dtgvx2') format('truetype'),
                url('fonts/icomoon.woff?dtgvx2') format('woff'),
                url('fonts/icomoon.svg?dtgvx2#icomoon') format('svg');
            font-weight: normal;
            font-style: normal;
            font-display: block;
        }

        .box {
            position: relative;

            width: 200px;
            height: 30px;
            line-height: 30px;
            border: 1px solid red;
        }

        .box::after {
            position: absolute;
            top: 0;
            right: 10px;

            content: "\ea43";

            font-family: 'icomoon';
            cursor: pointer;
            color: red;
        }
    </style>

</head>

<body>
    <div class="box"></div>
</body>
```

![image-20210203102111082](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/伪元素字体图标案例.4t90rrab3fy0.png)



### 2.7. 仿土豆遮罩效果

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>007-土豆遮罩案例</title>

    <style>
        .box {
            position: relative;

            width: 444px;
            height: 320px;
            background-color: pink;
            margin: 30px auto;
        }

        .box img {
            width: 100%;
            height: 100%;
        }

        .box::after {
            position: absolute;
            top: 0;
            right: 0;

            /* 隐藏遮罩层 */
            display: none;

            content: "";
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, .3) url(../images/arr.png) no-repeat center;
        }

        /* 鼠标经过时显示元素 */
        .box:hover::after {
            display: block;
        }
    </style>

</head>

<body>
    <div class="box">
        <img src="../images/tudou.jpg" alt="">
    </div>
</body>
```

![image-20210203103243681](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/土豆防遮罩层案例.pi6xx3xeqf4.png)



### 2.8. CSS3盒子模型

![image-20210203105808029](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/CSS3盒子模型.2l3tulmc0g20.png)

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>008-CSS3盒子模型</title>

    <style>
        div {
            width: 200px;
            height: 200px;
            background-color: pink;
            border: 20px solid red;
            box-sizing: content-box;
        }

        /* 无论如何增加边框, 盒子大小都不会变 */
        p {
            width: 200px;
            height: 200px;
            background-color: pink;
            border: 20px solid red;
            box-sizing: border-box;
        }
    </style>

</head>

<body>
    <div></div>

    <p></p>
</body>
```

![image-20210203110254506](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/CSS3盒子模型案例.5x89pm9ycw00.png)



### 2.9. CSS3图片模糊处理

CSS3滤镜 Filter：Filter CSS属性将模糊或颜色偏移等图形效果应用于元素。

```css
filter: function(); 
例如：
filter: blur(5px); /* blur()模糊处理  数值越大越模糊 */
```

![image-20210203110656246](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/CSS3图片模糊处理.7iismntf9940.png)



### 2.10. calc计算盒子宽度

![image-20210203110812676](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/CSS3 calc（）函数.5r5du19bzxw0.png)



### 2.11. 过渡(transition重点)

![image-20210203111049388](E:\Typora\image\image-20210203111049388.png)

**语法**：

```css
transition: 要过渡的属性 花费时间 运动曲线 何时开始；
```

![image-20210203111732838](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/CSS3过渡.66o613cmg340.png)

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>009-过渡</title>

    <style>
        div {
            width: 200px;
            height: 100px; mn  
            background-color: pink;

            /* 用逗号来分隔 */
            /* transition: width 1s ease 0s, height 1s ease 0s; */

            /* 如果多个属性都要变化写all就可以 */
            transition: all 1s ease 0s;
        }

        div:hover {
            width: 400px;
            height: 300px;
        }
    </style>

</head>

<body>
    <div></div>
</body>
```

### 2.12. 过渡案例-进度条

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>010-过渡练习-进度条</title>

    <style>
        .bar {
            width: 150px;
            height: 15px;
            border: 1px solid red;
            border-radius: 7px;
            padding: 1px;
        }

        .bar .bar_in {
            width: 50%;
            height: 100%;
            background-color: red;
            border-radius: 7px;

            transition: all 1s ease 0s;
        }

        .bar .bar_in:hover {
            width: 80%;
        }
    </style>

</head>

<body>
    <div class="bar">
        <div class="bar_in"></div>
    </div>
</body>
```

![image-20210203122549528](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/过渡案例-进度条.3nmsjwaasyi0.png)



### 2.13. 小米官网过渡案例

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>011-小米过渡案例</title>

    <style>
        .box {
            position: relative;

            width: 55px;
            height: 55px;
            background-color: #ff6700;
            margin-left: 0;
        }

        .box::after,
        .box::before {
            position: absolute;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            content: "";
            transition: all .2s;
        }

        .box::before {
            background: url(../images/mi-home.png) no-repeat center;
            margin-left: -55px;
        }


        .box::after {
            background: url(../images/mi-logo.png) no-repeat center;

        }

        .box:hover::after {
            margin-left: 55px;
        }

        .box:hover::before {
            margin-left: 0;
        }
    </style>

</head>

<body>
    <div class="box"></div>
</body>
```

![image-20210203135116702](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/小米官网过渡案例.6o651pl7vtg0.png)



# 四、 CSS3弹性盒子布局

## 1. CSS3 弹性盒子属性

弹性盒子由弹性容器(Flex container)和弹性子元素(Flex item)组成。

弹性容器通过设置 display 属性的值为 flex 或 inline-flex将其定义为弹性容器。

弹性容器内包含了一个或多个弹性子元素。

**注意：** 弹性容器外及弹性子元素内是正常渲染的。弹性盒子只定义了弹性子元素如何在弹性容器内布局。

弹性子元素通常在弹性盒子内一行显示。默认情况每个容器只有一行。

### 1.1. display: flex

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>弹性盒子</title>
    
    <style type="text/css">
        .flex-container {
            display: flex;
			/* 默认靠左，可以设置 direction 使得盒子靠右 */
            direction: rtl;
            
            width: 400px;
            height: 250px;
            background-color: lightgray;
        }

        .flex-item {
            background-color: cornflowerblue;
            width: 100px;
            height: 100px;
            margin: 10px;
        }
    </style>
</head>

<body>
    <div class="flex-container">
        <div class="flex-item">1</div>
        <div class="flex-item">2</div>
        <div class="flex-item">3</div>
    </div>
</body>

</html>
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/弹性盒子.46ylva21mm2.png" alt="弹性盒子" style="zoom:150%;" />



### 1.2. justify-content

内容对齐（justify-content）属性应用在弹性容器上，把弹性项沿着弹性容器的主轴线（main axis）对齐。

justify-content 语法如下：

```css
justify-content: flex-start | flex-end | center | space-between | space-around
```

各个值解析:

- flex-start：弹性项目向行头紧挨着填充。这个是默认值。第一个弹性项的main-start外边距边线被放置在该行的main-start边线，而后续弹性项依次平齐摆放。
- flex-end：弹性项目向行尾紧挨着填充。第一个弹性项的main-end外边距边线被放置在该行的main-end边线，而后续弹性项依次平齐摆放。
- center：弹性项目居中紧挨着填充。（如果剩余的自由空间是负的，则弹性项目将在两个方向上同时溢出）。
- space-between：弹性项目平均分布在该行上。如果剩余空间为负或者只有一个弹性项，则该值等同于flex-start。否则，第1个弹性项的外边距和行的main-start边线对齐，而最后1个弹性项的外边距和行的main-end边线对齐，然后剩余的弹性项分布在该行上，相邻项目的间隔相等。
- space-around：弹性项目平均分布在该行上，两边留有一半的间隔空间。如果剩余空间为负或者只有一个弹性项，则该值等同于center。否则，弹性项目沿该行分布，且彼此间隔相等（比如是20px），同时首尾两边和弹性容器之间留有一半的间隔（1/2*20px=10px）。

![效果图](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/justify-content 效果图.6jugvbuuzio0.png)

```css
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>justify-content</title>
    <style type="text/css">
        .flex-container {
            display: flex;

            /* 详情看效果图 */
            justify-content: space-around;

            width: 400px;
            height: 250px;
            background-color: lightgray;
        }

        .flex-item {
            background-color: cornflowerblue;
            width: 100px;
            height: 100px;
            margin: 10px;
        }
    </style>
</head>

<body>
    <div class="flex-container">
        <div class="flex-item">1</div>
        <div class="flex-item">2</div>
        <div class="flex-item">3</div>
    </div>
</body>

</html>
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/justify-content案例.69l889okjnk0.png" alt="justify-content案例" style="zoom:150%;" />



### 1.3. align-items

`align-items` 设置或检索弹性盒子元素在侧轴（纵轴）方向上的对齐方式。

`justify-content`可以表示水平方向的对齐。

**语法**：

```css
align-items: flex-start | flex-end | center | baseline | stretch
```

各个值解析:

- flex-start：弹性盒子元素的侧轴（纵轴）起始位置的边界紧靠住该行的侧轴起始边界。
- flex-end：弹性盒子元素的侧轴（纵轴）起始位置的边界紧靠住该行的侧轴结束边界。
- center：弹性盒子元素在该行的侧轴（纵轴）上居中放置。（如果该行的尺寸小于弹性盒子元素的尺寸，则会向两个方向溢出相同的长度）。
- baseline：如弹性盒子元素的行内轴与侧轴为同一条，则该值与'flex-start'等效。其它情况下，该值将参与基线对齐。
- stretch（默认）：如果指定侧轴大小的属性值为'auto'，则其值会使项目的边距盒的尺寸尽可能接近所在行的尺寸，但同时会遵照'min/max-width/height'属性的限制。



### 1.4. flex-wrap

**flex-wrap** 属性用于指定弹性盒子的子元素换行方式。

**语法**：

```css
flex-wrap: nowrap|wrap|wrap-reverse|initial|inherit;
```

各个值解析:

- **nowrap** - 默认， 弹性容器为单行。该情况下弹性子项可能会溢出容器。
- **wrap** - 弹性容器为多行。该情况下弹性子项溢出的部分会被放置到新行，子项内部会发生断行
- **wrap-reverse** -反转 wrap 排列。

```css
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>弹性盒子换行</title>
    <style type="text/css">
        .flex-container {
            display: flex;
            flex-wrap: wrap;

            background-color: darkgray;
            width: 300px;
            height: 250px;
        }

        .container-item {
            width: 100px;
            height: 100px;
            margin: 10px;
            background-color: cornflowerblue;
        }
    </style>
</head>
<body>
    <div class="flex-container">
        <div class="container-item">1</div>
        <div class="container-item">2</div>
        <div class="container-item">3</div>
    </div>
</body>
</html>
```

![弹性盒子换行](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/弹性盒子换行.6refccowh1c0.png)

### 1.5. align-content

`align-content` 属性用于修改 `flex-wrap` 属性的行为。类似于 `align-items`, 但它不是设置弹性子元素的对齐，而是设置各个行的对齐。

**语法**：

```css
align-content: flex-start | flex-end | center | space-between | space-around | stretch
```

各个值解析:

- `stretch` - 默认。各行将会伸展以占用剩余的空间。
- `flex-start` - 各行向弹性盒容器的起始位置堆叠。
- `flex-end` - 各行向弹性盒容器的结束位置堆叠。
- `center` -各行向弹性盒容器的中间位置堆叠。
- `space-between` -各行在弹性盒容器中平均分布。
- `space-around` - 各行在弹性盒容器中平均分布，两端保留子元素与子元素之间间距大小的一半。



## 2. 弹性子元素属性

### 2.1. order

用整数值来定义排列顺序，数值小的排在前面。可以为负值。

```css
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>order排序</title>
    <style type="text/css">
        .flex-container {
            display: flex;

            width: 400px;
            height: 250px;
            background-color: lightgray;
        }

        .flex-item {
            background-color: cornflowerblue;
            width: 100px;
            height: 100px;
            margin: 10px;
        }

        .first {
            /* 设置order盒子会排序 */
            order: -1;
        }
    </style>
</head>

<body>
    <div class="flex-container">
        <div class="flex-item">1</div>
        <div class="flex-item first">2</div>
        <div class="flex-item">3</div>
    </div>
</body>

</html>
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/order.6oyedle10p00.png" alt="order" style="zoom:150%;" />



### 2.2. 对齐

设置"margin"值为"auto"值，自动获取弹性容器中剩余的空间。

所以设置垂直方向margin值为"auto"，可以使弹性子元素在弹性容器的两上轴方向都完全居中。

```css
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>对齐</title>
    <style type="text/css">
        .flex-container {
            display: flex;

            width: 400px;
            height: 250px;
            background-color: lightgrey;
        }

        .flex-item {
            width: 75px;
            height: 75px;
            margin: 10px;
            background-color: cornflowerblue;
        }

        .flex-item:nth-child(1) {
            /* 在第一个弹性子元素上设置了 margin-right: auto; 它将剩余的空间放置在元素的右侧. */
            margin-right: auto;
        }
    </style>
</head>

<body>
    <div class="flex-container">
        <div class="flex-item">1</div>
        <div class="flex-item">2</div>
        <div class="flex-item">3</div>
    </div>
</body>

</html>
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/对齐.223p8u9jhtz4.png" alt="对齐" style="zoom:150%;" />



### 2.3. 居中

弹性子元素设置 `margin: auto`。

```css
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>对齐</title>
    <style type="text/css">
        .flex-container {
            display: flex;

            width: 400px;
            height: 250px;
            background-color: lightgrey;
        }

        .flex-item {
            width: 75px;
            height: 75px;

            /* 如果只有一个弹性子盒子，该子盒子就会处于正中间 */
            margin: auto;
            background-color: cornflowerblue;
        }
    </style>
</head>

<body>
    <div class="flex-container">
        <div class="flex-item">1</div>
        <div class="flex-item">2</div>
        <div class="flex-item">3</div>
    </div>
</body>

</html>
```

![居中](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/居中.4jam1q4z4tk0.png)



### 2.4. align-self

`align-self` 属性用于设置弹性元素自身在侧轴（纵轴）方向上的对齐方式。

**语法**：

```css
align-self: auto | flex-start | flex-end | center | baseline | stretch
```

各个值解析:

- auto：如果'align-self'的值为'auto'，则其计算值为元素的父元素的'align-items'值，如果其没有父元素，则计算值为'stretch'。
- flex-start：弹性盒子元素的侧轴（纵轴）起始位置的边界紧靠住该行的侧轴起始边界。
- flex-end：弹性盒子元素的侧轴（纵轴）起始位置的边界紧靠住该行的侧轴结束边界。
- center：弹性盒子元素在该行的侧轴（纵轴）上居中放置。（如果该行的尺寸小于弹性盒子元素的尺寸，则会向两个方向溢出相同的长度）。
- baseline：如弹性盒子元素的行内轴与侧轴为同一条，则该值与'flex-start'等效。其它情况下，该值将参与基线对齐。
- stretch：如果指定侧轴大小的属性值为'auto'，则其值会使项目的边距盒的尺寸尽可能接近所在行的尺寸，但同时会遵照'min/max-width/height'属性的限制。

```css
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>align-self</title>
    <style type="text/css">
        .flex-container {
            display: flex;

            width: 400px;
            height: 250px;
            background-color: lightgrey;
        }

        .flex-item {
            width: 60px;
            min-height: 100px;
            background-color: cornflowerblue;
            margin: 10px;
        }

        .item1 {
            align-self: auto;
        }

        .item2 {
            align-self: flex-start;
        }

        .item3 {
            align-self: flex-end;
        }

        .item4 {
            align-self: center;
        }

        .item5 {
            align-self: baseline;
        }

        .item6 {
            align-self: stretch;
        }
    </style>
</head>

<body>
    <div class="flex-container">
        <div class="flex-item item1">auto</div>
        <div class="flex-item item2">flex-start</div>
        <div class="flex-item item3">flex-end</div>
        <div class="flex-item item4">center</div>
        <div class="flex-item item5">baseline</div>
        <div class="flex-item item6">stretch</div>
    </div>
</body>

</html>
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/align-self.3x6mnqtqjx40.png" alt="align-self" style="zoom:150%;" />

### 2.5. flex

`flex` 属性用于指定弹性子元素如何分配空间。

语法：

```css
flex: auto | initial | none | inherit |  [ flex-grow ] || [ flex-shrink ] || [ flex-basis ]
```

各个值解析:

- auto: 计算值为 1 1 auto
- initial: 计算值为 0 1 auto
- none：计算值为 0 0 auto
- inherit：从父元素继承
- [ flex-grow ]：定义弹性盒子元素的扩展比率。
- [ flex-shrink ]：定义弹性盒子元素的收缩比率。
- [ flex-basis ]：定义弹性盒子元素的默认基准值。

以下实例中，第一个弹性子元素占用了 2/4 的空间，其他两个各占 1/4 的空间:

```css
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>flex</title>
    <style type="text/css">
        .flex-container {
            display: -webkit-flex;
            display: flex;
            width: 400px;
            height: 250px;
            background-color: lightgrey;
        }

        .flex-item {
            background-color: cornflowerblue;
            margin: 10px;
        }

        .item1 {
            flex: 2;
        }

        .item2 {
            flex: 1;
        }

        .item3 {
            flex: 1;
        }
    </style>
</head>

<body>
    <div class="flex-container">
        <div class="flex-item item1">flex-start</div>
        <div class="flex-item item2">flex-end</div>
        <div class="flex-item item3">center</div>
    </div>
</body>

</html>
```

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/CSS/flex.1y4elckw6f8.png" alt="flex" style="zoom:150%;" />

