# 一、Python基础语法

## 1. print()

### 1.1. 输出内容

```python
# 输出函数print()
# 1、数字
print(520)
print(98.5)

# 2、字符串
print("Hello World")

# 3、含有运算符的表达式
print(520 + 1)
```



### 1.2. 输出到文件

```python
"""
数据输出到文件中
1、指定盘符, "a+"如果文件不存在就创建, 文件存在就追加！
2、 注意：使用 file=fp
"""
fp = open("E:/text.txt", "a+")
print("Hello World", file=fp)
fp.close()
```



## 2. 转义字符

```python
"""
转义字符
\n: 换行
\t: 制表位
\r: 回车
\b: 回退一格
 r: 字符串里是啥就输出啥, 转移字符'\n'就直接输出'\n'
"""
print("Hello\nWorld");
print("Hello\tWorld");

# world 会覆盖前面的 HelloABC
print("HelloABC\rWorld");

# world 会回退一格
print("Hello\bWorld");

# \" 表示双引号不再是字符串的边界
print("老师说：\"大家好\" ")

# print(r"Hello\nWorld\") 注意最后一个字符不能是 '\', 但是可以是'\\'
print(r"Hello\nWorld")
print(r"Hello\nWorld\\")
```



## 3. 标识符和保留字

### 3.1. 保留字

```python
# 保留字 关键字 python中默认使用的
# keyword.py
kwlist = [
    'False',
    'None',
    'True',
    '__peg_parser__',
    'and',
    'as',
    'assert',
    'async',
    'await',
    'break',
    'class',
    'continue',
    'def',
    'del',
    'elif',
    'else',
    'except',
    'finally',
    'for',
    'from',
    'global',
    'if',
    'import',
    'in',
    'is',
    'lambda',
    'nonlocal',
    'not',
    'or',
    'pass',
    'raise',
    'return',
    'try',
    'while',
    'with',
    'yield'
]
```



### 3.2. 标识符

**变量、函数、类、模块和其他对象起的名字就叫做标识符**。

**规则**：

- 字母、数字、下划线；
- 不能以数字开头；
- 不能是 python 的保留字；
- 严格区分大小写。



## 4. 变量

**变量由三部分组成**：

- **标识**：表示对象所存储的内存地址，使用内置函数 `id(obj)` 可以获取；
- **类型**：表示对象的数据类型，使用内置函数 `type(obj)` 可以获取；
- **值**：表示对象所存储的具体数据，使用 `print(obj)` 进行打印输出。

```python
name = "Ringo"
print("标识", id(name))
print("类型", type(name))
print("值", name)
```

```python
# 输出结果
标识 2378859882928
类型 <class 'str'>
值 Ringo
```



## 5. 数据类型

### 5.1. 整数类型

```python
'''
python 整数可以表示为：二进制、八进制、十进制（默认）、十六进制。
二进制：需要以 '0b' 开头
八进制：需要以 '0o' 开头
十六进制：需要以 '0x' 开头
'''
print('十进制', 118)
print('二进制', 0b1011)
print('八进制', 0o13)
print('十六进制', 0xB)
```



### 5.2. 浮点类型

- 浮点数由整数部分和小数部分组成；
- 浮点数存储不精确，需要导入`decimal`模块。

```python
"""
浮点类型: 不精确, 会出现误差
"""
n1 = 1.1
n2 = 2.2
print(n1 + n2)  # 3.3000000000000003

# 解决办法
from decimal import Decimal

# 注意：Decimal() 中的参数要写成字符串
print(Decimal('1.1') + Decimal('2.2'))  # 3.3
```



### 5.3. 布尔类型

- `True` 表示真，`False` 表示假；
- 布尔值可以转化为整数：`True -> 1; False -> 0`。

```python
a = True
b = False
print(a, type(a))  # True <class 'bool'>
print(b, type(b))  # False <class 'bool'>

# bool可以转成整数
print(a + 1)  # 2
print(b + 1)  # 1
```



### 5.4. 字符串类型

- 字符串又被成为不可变的字符序列；
- 可以使用单引号，双引号，三引号来定义。
- **单引号和双引号定义的字符串必须在一行，三引号定义的字符串可以分布在连续的多行**。

```python
str1 = "123"
str2 = '123'
str3 = """123"""
str4 = """a
        123"""
print(str1, str2, str3, str4)
```

```python
# 输出结果
123 123 123 a
        123
```



## 6. 数据类型转换

- `str()`：将其他数据类型转成字符串。
- `int()`：将其他数据类型转成整数。
  - **文字类和小数类字符串**，无法转换成整数；
  - 浮点数转成成整数：抹零取整。
- `float()`：将其他数据类型转成浮点数。
  - **文字类字符串**无法转成浮点数；
  - 整数转成浮点数，末尾为`.0`。

```python
name = "张三"
age = 20
# name 和 age数据类型不同
print(type(name), type(age))  # <class 'str'> <class 'int'>

# print("我叫" + name + ", 今年" + age + "岁") 报错
# TypeError: can only concatenate str (not "int") to str
print("我叫" + name + ", 今年" + str(age) + "岁")  # 正确
```

```python
print(int(3.52))  # 3
print(int(True))  # 1
print(int(False))  # 0
# print(int("3.1")) 报错！
print(float(6))  # 6.0
print(float("6.1")) # 6.1
```



## 7. input()

`input()` 基本使用：

```python
# input(xxx)：参数是提示语, 输入的值会赋给变量 word
word = input("what's your name?\n")
print("你的名字是: ", word)
print(type(word))  # <class 'str'> 字符串类型
```



`input()` 小练习 整数相加：

```python
n1 = int(input("请输入整数1: \n"))
n2 = int(input("请输入整数2: \n"))
print('结果: ', n1 + n2, type(n1 + n2))
```

```python
# 控制台打印
请输入整数1: 
10
请输入整数2: 
20
结果:  30 <class 'int'>
```



## 8. 运算符

### 8.1. 算数运算符

- **标准运算符**：加(+)、减(-)、乘(*)、除(/)、整除(//)。
  - **整除(//)永远都是向下取整**。
- **取余运算符**：%。
- **幂运算符**：**。

```python
print(3 / 2)  # 1.5 除法
print(3 // 2)  # 1  整除
print(10 % 3)  # 1 取余
print(2 ** 3)  # 8 幂运算
```

```python
# 难点: 一正一复向下取整
print(9 // 4)  # 2
print(-9 // -4)  # 2
print(9 / -4)  # -2.25
print(9 // -4)  # -3 [-2.25向下取整就是-3]
print(-9 // 4)  # -3
```

```python
# 取余一正一负公式：余数=被除数-除数*商
print(9 % -4)  # -3 [9 // -4 = -3 ==> 余数 = 9 - (-4) * (-3) = -3]
print(-9 % -4)  # -1 [-9 // -4 = 2 ==> 余数 = -9 - (-4) * 2 = -1]
print(-9 % 4)  # 3 [-9 // 4 = -3 ==> 余数 = -9 - 4 * (-3) = 3]
```



### 8.2. 赋值运算符

- **执行顺序：右 → 左**。
- **支持链式赋值**：`a=b=c=20`
- **支持参数赋值**：`+=、-=、*=、/=、//=、%=`。
- **支持系列解包赋值**：`a,b,c = 20,30,40`。

```python
# 多个引用指向同一个地址
a = b = c = 20
print(a, id(a))  # 20 2241448799120
print(b, id(b))  # 20 2241448799120
print(c, id(c))  # 20 2241448799120
```

```python
a, b, c = 20, 30, 40
a += b  # a = 50
a //= c  # a = 1
print(a, b, c)  # 1 30 40
```

```python
# 交换两个变量的值
a, b = 30, 40
print("交换之前", a, b)  # 交换之前 30 40
a, b = b, a
print("交换之后", a, b)  # 交换之后 40 30
```



### 8.3. 比较运算符

- **对象值的比较**：`>、<、 >=、 <=、 !=、 ==`。
- **对象地址的比较**：`is、is not`。

```python
a, b = 10, 10
print(a > b)  # 10 大于 10 吗? False
print(a == b)  # True
print(a is b)  # a和b所指向的地址一样吗? True
print(a is not b)  # False
print(id(a), id(b))  # 2272178563664 2272178563664
```

```python
list1 = [11, 22, 33]
list2 = [11, 22, 33]
print(list1 == list2)  # 两个数组的值是否相等?  True
print(type(list1), type(list2))  # <class 'list'> <class 'list'>
print(list1 is list2)  # 两个数组的地址是否相等?  False
print(id(list1), id(list2))  # 1757002346688 1757002347328
```



### 8.4. 布尔运算符

`and、or、not、in、not in`

```python
a, b, c = 10, 20, 30
print(a == 10 and b == 20)  # True
print(a == 10 and b == c)  # False
print(a == b or c == 30)  # True
print(a == b)  # False
print(not a == b)  # True
```

```python
list = [1, 2, 3, 4]
print(1 in list)  # True
print(1 not in list)  # False
print(10 in list)  # False
```

```python
str = "Hello World"
print("H" in str)  # True
print(" " in str)  # True
print("ld" in str) # True
```



### 8.5. 位运算符

- **位与&**：对应数位都是1，结果数位才是1，否则为0。
- **位或|**：对应数位都是0，结果数位才是0，否则为1。
- **左移位运算<<**：高位溢出舍弃，低位补0。
- **左移位运算>>**：低位溢出舍弃，高位补0。

```python
a = 25  # 11001
print(a >> 2)  # 00110 十进制6
print(a << 2)  # 1100100 十进制100
```



### 8.6. 运算符优先级

- **[1]算数运算**：先算乘除，后算加减，有幂运算最先算。
- **[2]位运算**：先算移位运算，再位与，最后位或。
- **[3]比较运算**：`>、<、>=、<=、==、!=`
- **[4]布尔运算**：`and or `
- **[5]赋值运算**: `=`

## 9. 对象的布尔值

**Python一切皆对象，所有对象都有一个布尔值**。

使用内置函数`bool()`可以获取对象的布尔值。

- **布尔值为False的对象**：
  - False
  - 数值0
  - None
  - 空字符串
  - 空列表
  - 空元组
  - 空字典
  - 空集合
- **其他对象的布尔值都为True**。

```python
# False
print(bool(False))
# 数值0
print(bool(0))
print(bool(0.0))
# None
print(bool(None))
# 空字符串
print(bool(""))
print(bool(''))
# 空列表
print(bool([]))
print(bool(list()))
# 空元组
print(bool(()))
print(bool(tuple()))
# 空字典
print(bool({}))
print(bool(dict()))
# 空集合
print(bool(set()))
```



## 10. if-else

### 10.1. 双分支结构

```python
# 1、if + true/false
# 2、用缩进来代表是当前分支下语句
money = 1000  # 余额
s = int(input("请输入取款金额: \n"))
if s <= money:
    money -= s
    print("取款成功, 余额为: ", money)
else:
    print("您没有这么多钱, 取款失败！")
```



### 10.2. 多分支结构

```python
i = int(input("请输入一个正整数:\n"))
if i >= 90:
    print("优")
elif i < 90 and i >= 80:
    print("良")
elif 60 <= i < 80:
    print("牛批")
else:
    print("差")
```



### 10.3. 条件表达试

```python
a, b = 10, 20
print(str(a) + "大于等于" + str(b) if a >= b else str(a) + "小于等于" + str(b))
# 输出：10小于等于20
```



## 11. PASS语句

**PASS**：语句什么都不做，只是一个占位符，用在语法上需要语句的地方。

**什么时候用**：先搭建语法结构，还没想好代码怎么写的时候用。

```python
answser = input("您是会员吗？y/n\n")
if answser == "y":
    pass
else:
    pass
```



## 12. 循环

### 12.1. range()

- `range()`： 用于生成一个整数序列。
- **创建 range 对象的三种方式**
  - `range(stop)`：创建一个 `0~stop` 之间的序列，步长为1。
  - `range(start, stop)`：创建一个 `start~stop` 之间的序列，步长为1。
  - `range(start, stop, step)`: 创建一个 `start~stop` 之间的序列，步长为step。
- **range()返回值是一个迭代器对象，需要去遍历**。
- **range()的优点(不用不计算长度)**：。不管range对象表示的整数序列有多长，所有range对象占用的内存空间都是相同的，因为仅仅需要存储`start，stop，step`，只有当用到range对象时、才会去计算序列中的相关元素。
- `in、not in`判断整数序列中是否存在（不存在）指定的整数。

```python
# 1、range(stop)
a = range(10)
print(a)  # range(0, 10)
print(list(a))  # [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

# 2、range(start, stop)
b = range(3, 8)
print(b)  # range(3, 8)
print(list(b))  # [3, 4, 5, 6, 7]

# 3、range(start, stop, step)
c = range(2, 10, 3)
print(c)  # range(2, 10, 3)
print(list(c))  # [2, 5, 8]
```



### 12.2. while循环

```python
# 步骤：
#   1、初始化变量
#   2、条件判断
#   3、条件执行体（循环体）
#   4、改变变量

# 计算 1-100 内偶数的和
i = 1
sum = 0
while i <= 100:
    if not bool(i % 2):
        sum += i
    i += 1
print(sum)
```



### 12.3. for-in循环

```python
# 计算 1-100 内偶数的和
sum = 0
for i in range(2, 101, 2):
    sum += i
print(sum)
```

```python
# 遍历字符串
for i in "python":
    print(i)
```

```python
# 100~999的水仙花数
for i in range(100, 1000):
    temp = i
    sum = 0
    while temp > 0:
        sum += (temp % 10) ** 3
        temp //= 10
    if sum == i:
        print(i, end=' ')
```



### 12.4. else

- `if-else`：if条件表达式不成立时执行else。

- `for-else、while-else`：没有碰到break时执行else。

```python
# 模拟输入3次密码
for i in range(3):
    pwd = input("输入密码: ")
    if pwd == "8888":
        print("密码正确！")
        break
    else:
        print("密码错误！")
else:
    print("对不起, 三次密码均输入错误")
```



### 12.5. 嵌套循环

```python
# 嵌套循环, 九九乘法表
for i in range(1, 10):
    for j in range(1, i + 1):
        print(i, "*", j, "=", i * j, end="\t")
    print()
```



## 13. 列表

### 13.1. 列表创建

```python
# 1、创建列表的第一种方式：使用[]
list1 = ["Hello", "World", 97]
list11 = ["Hello", "World", 97]
# 2、创建列表的第二种方式：使用 list()
list2 = list(["Hello", "World", 97])

print(list1[0])  # Hello 通过下标获取

print(type(list1))  # <class 'list'>
print(list1 == list2)  # True 值相等
print(list1 is list2)  # False 地址不等
print(list1 is list11)  # False 地址不等
```



### 13.2. 列表特点

- 列表元素按顺序有序排列；
- 索引映射唯一数据；
- 列表可以存储重复数据；
- 列表可以存储任意类型数据；
- 根据需要动态分配和回收内存。



### 13.3. 获取列表元素

**（1）获取指定元素下标**

```python
arr = ["Hello", "World", 98, "Hello"]

# index() 获取指定元素下标
# 如果列表中有相同元素, 只返回第一个元素的下标
print(arr.index("Hello")) # 打印 0

# index(target, start, stop)
# 在 [start, stop) 范围内获得指定元素的下标
print(arr.index("Hello", 1, 4)) # 打印 3
```



**（2）获取列表中单个元素**

- 正向索引从 0 到 N-1，如：`list[0]`；
- 逆向索引从 -N 到 -1，如：list[-N]；
- 指定的索引不存在，则抛出异常。

```python
arr = ["Hello", "World", 98, "Hello"]

# arr[0] 和 arr[-N] 代表的是同一个值
N = len(arr)
print(arr[0], arr[-N]) # Hello Hello
```



**（3）获取列表中多个元素——切片**

- **语法**：`list[start: stop: step ]`
- **切片的结果**：原列表片段的拷贝。

- step默认步长为1,  可以简写为 `list[start, stop]`。
- step为正数：从start开始向后开始切片。
- step为负数：从start开始向前开始切片。

```python
arr = [1, 3, 4, 5, 6]
'''
start stop step 省略
start 默认是 0
stop 默认是 N
step 默认是 1
'''
print(arr[::])  # [1, 3, 4, 5, 6]
print(arr[: 4])  # [1, 3, 4, 5]
print(arr[1: 4: 2])  # [3, 5]
```



### 13.4. 遍历列表

- `in、not in` 可以判断元素是否存在列表中。
- `for in` 可以遍历列表。

```python
arr = [1, 3, 4, 5, 6]
print(1 in arr)  # True
print(8 in arr)  # False

# 遍历列表
for i in arr:
    print(i, end=' ')  # 1 3 4 5 6
```



### 13.5. 列表添加元素

- `append()`：在列表的**末尾添加一个**元素。
- `extend()`：在列表的**末尾至少添加**一个元素。
- `insert()`：在列表的**任意位置添加一个**元素。
- 切片：在列表的任意位置添加至少一个元素。

```python
arr = [1, 2, 3, 4, 5]

# append(n)
arr.append(10)
print(arr)  # [1, 2, 3, 4, 5, 10]

# extend(n...)
arr.extend(range(10, 15, 2))
print(arr)  # [1, 2, 3, 4, 5, 10, 10, 12, 14]

# insert(index, n)
arr.insert(3, 99)  # [1, 2, 3, 99, 4, 5, 10, 10, 12, 14]
print(arr)

# 切片替换
arr[1:] = ["Hello", "World"]
print(arr)  # [1, 'Hello', 'World']
```



### 13.6. 列表删除元素

- `remove(value)`：删除列表中的指定元素。
  - 一次删除一个元素；
  - 重复元素只删除第一个；
  - 元素不存在则抛出异常。
- `pop(index)`：删除指定索引位置的元素。
  - 指定索引不存在则抛异常；
  - 不指定索引删除列表最后一个元素。
- 切片：一次至少删除一个元素。
- `clear()`：清空列表。
- `del list`：删除列表。

```python
arr = [1, 3, 4, 6, 1, 3]

# remove(value) 从列表中移除指定元素
# 如果有重复元素, 只移除第一个元素
arr.remove(3)
print(arr)  # [1, 4, 6, 1, 3]

# pop(index) 删除指定索引位置的元素
arr.pop(2)
print(arr)  # [1, 4, 1, 3]

# pop() 不指定索引, 删除列表最后一个元素
arr.pop()
print(arr)  # [1, 4, 1]

# 切片
arr[2:] = []
print(arr)  # [1, 4]

# clear() 清空列表
arr.clear()
print(arr)  # []

# del list：删除列表对象
del arr
print(id(arr))  # NameError: name 'arr' is not defined
```



### 13.7. 修改列表元素

```python
arr = [10, 20, 30, 40]
# 修改列表指定下标的值
arr[1] = 99
print(arr)  # [10, 99, 30, 40]

# 用切片修改
arr[1:4] = [8848]
print(arr)  # [10, 8848]
```



### 13.8. 列表排序

- 调用`sort()`方法，列中的所有元素默认按照从小到大的顺序进行排列。
  - 可以指定`reverse = True`，进行降序排序。
- `sorted()` 函数对所有可迭代的对象进行排序操作。

> **sort 与 sorted 区别：**
>
> sort 是应用在 list 上的方法，sorted 可以对所有可迭代的对象进行排序操作。
>
> list 的 sort 方法返回的是对已经存在的列表进行操作，无返回值，而内建函数 sorted 方法返回的是一个新的 list，而不是在原来的基础上进行的操作。

```python
arr = [8, 9, 1]
# reverse=False 升序排序
arr.sort(reverse=False)
print(arr)  # [1, 8, 9]

# reverse=True 降序排序
print(sorted(arr, reverse=True))  # [9, 8, 1]
```



### 13.9. 列表生成式

```python
# 语法: [表达式 for i in 可迭代对象]

list1 = [i for i in range(1, 11)]
print(list1)  # [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

list2 = [i * i for i in range(1, 11)]
print(list2)  # [1, 4, 9, 16, 25, 36, 49, 64, 81, 100]

list3 = [i * 2 for i in range(1, 6)]
print(list3)  # [2, 4, 6, 8, 10]
```



## 14. 字典

### 14.1. 字典特点

**字典是另一种可变容器模型，且可存储任意类型对象，字典是无序的集合**。

字典的每个键值 **key=>value** 对用冒号 **:** 分割，每个键值对之间用逗号 **,** 分割，整个字典包括在花括号 **{}** 中 ,格式如下所示：

```python
d = {key1: value1, key2: value2 }
```

>键一般是唯一的，如果重复最后的一个键值对会替换前面的，值不需要唯一。
>
>**值可以取任何数据类型，但键必须是不可变的，如字符串，数字或元组**。

```python
# 当 key 重复时, 后边会覆盖前边！
dict = {"name": "zs", "age": 18, "sex": "男", "a": 1, "a": 5}
print(dict)  # {'name': 'zs', 'age': 18, 'sex': '男', 'a': 5}
```



### 14.2. 创建字典

```python
# 创建字典

# 1、使用花括号创建
dict1 = {"name": "zs", "age": 18, "sex": "男", "a": 1, "a": 5}

# 2、dict()函数创建
dict2 = dict(name='jack', age=20)
print(dict1)  # {'name': 'zs', 'age': 18, 'sex': '男', 'a': 5}
print(dict2)  # {'name': 'jack', 'age': 20}
print(type(dict1)) # <class 'dict'>
```



### 14.3. 字典元素的获取

```python
dict1 = {"name": "zs", "age": 18, "sex": "男", "a": 1, "a": 5}

# 获取字典元素
# 1、方式一
print(dict1["name"])  # zs
# print(dict1["aaa"]) # 不存在会报错

# 2、方式二
print(dict1.get("name"))  # zs
print(dict1.get("aaa")) # None
print(dict1.get('bbb', 99))  # 99
```



### 14.4. key的判断

- `in`：指定的key在字典中存在返回True；
- `not in`：指定的key在字典中不存在返回True。

```python
dict1 = {"name": "zs", "age": 18, "sex": "男", "a": 1, "a": 5}

print('name' in dict1)  # True
print('name' not in dict1)  # False
```



### 14.5. 字典元素删除

```python
# del

dict1 = {"name": "zs", "age": 18, "sex": "男", "a": 1, "a": 5}

# 删除字典中的指定元素
del dict1['name']
print(dict1)  # {'age': 18, 'sex': '男', 'a': 5}

# 清空字典
dict1.clear()
print(dict1) # {}
```



### 14.6. 字典元素增和改

```python
dict1 = {"name": "zs", "age": 18, "sex": "男", "a": 1, "a": 5}

# 字典元素的新增
dict1['b'] = 33
print(dict1)  # {'name': 'zs', 'age': 18, 'sex': '男', 'a': 5, 'b': 33}

# 修改指定字典元素
dict1['a'] = 11
print(dict1) # {'name': 'zs', 'age': 18, 'sex': '男', 'a': 11, 'b': 33}
```



### 14.7. 获取字典视图

- `keys()`：获取字典中所有的key。
- `values()`：获取字典中所有的value。
- `items()`：获取字典中所有key，value对。

```python
dict = {'name': 'zs', 'age': 18}

print(dict.keys())  # dict_keys(['name', 'age'])
print(dict.values())  # dict_values(['zs', 18])
print(dict.items())  # dict_items([('name', 'zs'), ('age', 18)])
```



### 14.8. 遍历字典

```python
dict = {'name': 'zs', 'age': 18}
for key in dict:
    print(key, dict.get(key))

# 输出结果
name zs
age 18
```



### 14.9. 字典生成式

