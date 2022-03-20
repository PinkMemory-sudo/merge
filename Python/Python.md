

Python的环境变量



```python
#! usr/bin/python
# coding=utf-8
```

Python3.X 源码文件默认使用utf-8编码，所以可以正常解析中文，无需指定 UTF-8 编码。



# 概念



**交互式编程**

交互式编程不需要提前写好脚本，可以通过解释器直接输入命令



**下划线**

单下划线开头表示私有的类属性



与Shell一样，一行命令可以通过\分割成多行



Python可以使用单引号双引号和三引号(三个单引号/三个双引号)来表示字符串，其中三引号可以由多行组成



**注释**

单行注释使用#多行注释使用三引号



输入函数

```python
raw_input("按下 enter 键退出，其他任意键显示...\n")
```



**输出函数**



# 变量



Python中不需要声明变量类型，每个变量在使用前都必须赋值，变量赋值以后该变量才会被创建。



**多个变量赋值**

```python
# 创建一个整形对象1赋值给a,b,c
a=b=c=1
a,b,c=1,2,"join"
```

**删除对象引用**

```python
# 删除a,b,c与整形对象1之间的引用
del a,b,c
```





Python的五个标准数据类型

- Numbers（数字）
- String（字符串）
- List（列表）
- Tuple（元组）
- Dictionary（字典）



## 数字

 不可改变的数据类型，这意味着改变数字数据类型会分配一个新的对象。 



Python支持四种不同的数字类型：

- int（有符号整型）
- long（长整型数值后加L，也可以代表八进制和十六进制）
- float（浮点型）
- complex（复数）

```
long 类型只存在于 Python2.X 版本中，在 2.2 以后的版本中，int 类型数据溢出后会自动转为long类型。在 Python3.X 版本中 long 类型被移除，使用 int 替代。
```



**常量**

| 常量 | 描述                                  |
| :--- | :------------------------------------ |
| pi   | 数学常量 pi（圆周率，一般以π来表示）  |
| e    | 数学常量 e，e即自然常数（自然常数）。 |



**math**

math提供了许多浮点型运算

**数学函数**

| 函数                                                         | 返回值 ( 描述 )                                              |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [abs(x)](https://www.runoob.com/python/func-number-abs.html) | 返回数字的绝对值，如abs(-10) 返回 10                         |
| [ceil(x)](https://www.runoob.com/python/func-number-ceil.html) | 返回数字的上入整数，如math.ceil(4.1) 返回 5                  |
| [cmp(x, y)](https://www.runoob.com/python/func-number-cmp.html) | 如果 x < y 返回 -1, 如果 x == y 返回 0, 如果 x > y 返回 1    |
| [exp(x)](https://www.runoob.com/python/func-number-exp.html) | 返回e的x次幂(ex),如math.exp(1) 返回2.718281828459045         |
| [fabs(x)](https://www.runoob.com/python/func-number-fabs.html) | 返回数字的绝对值，如math.fabs(-10) 返回10.0                  |
| [floor(x)](https://www.runoob.com/python/func-number-floor.html) | 返回数字的下舍整数，如math.floor(4.9)返回 4                  |
| [log(x)](https://www.runoob.com/python/func-number-log.html) | 如math.log(math.e)返回1.0,math.log(100,10)返回2.0            |
| [log10(x)](https://www.runoob.com/python/func-number-log10.html) | 返回以10为基数的x的对数，如math.log10(100)返回 2.0           |
| [max(x1, x2,...)](https://www.runoob.com/python/func-number-max.html) | 返回给定参数的最大值，参数可以为序列。                       |
| [min(x1, x2,...)](https://www.runoob.com/python/func-number-min.html) | 返回给定参数的最小值，参数可以为序列。                       |
| [modf(x)](https://www.runoob.com/python/func-number-modf.html) | 返回x的整数部分与小数部分，两部分的数值符号与x相同，整数部分以浮点型表示。 |
| [pow(x, y)](https://www.runoob.com/python/func-number-pow.html) | x**y 运算后的值。                                            |
| [round(x [,n\])](https://www.runoob.com/python/func-number-round.html) | 返回浮点数x的四舍五入值，如给出n值，则代表舍入到小数点后的位数。 |
| [sqrt(x)](https://www.runoob.com/python/func-number-sqrt.html) | 返回数字x的平方根                                            |

**Python随机数函数**

随机数可以用于数学，游戏，安全等领域中，还经常被嵌入到算法中，用以提高算法效率，并提高程序的安全性。

Python包含以下常用随机数函数：

| 函数                                                         | 描述                                                         |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [choice(seq)](https://www.runoob.com/python/func-number-choice.html) | 从序列的元素中随机挑选一个元素，比如random.choice(range(10))，从0到9中随机挑选一个整数。 |
| [randrange ([start,\] stop [,step])](https://www.runoob.com/python/func-number-randrange.html) | 从指定范围内，按指定基数递增的集合中获取一个随机数，基数默认值为 1 |
| [random()](https://www.runoob.com/python/func-number-random.html) | 随机生成下一个实数，它在[0,1)范围内。                        |
| [seed([x\])](https://www.runoob.com/python/func-number-seed.html) | 改变随机数生成器的种子seed。如果你不了解其原理，你不必特别去设定seed，Python会帮你选择seed。 |
| [shuffle(lst)](https://www.runoob.com/python/func-number-shuffle.html) | 将序列的所有元素随机排序                                     |
| [uniform(x, y)](https://www.runoob.com/python/func-number-uniform.html) | 随机生成下一个实数，它在[x,y]范围内。                        |



**Python三角函数**

| 函数                                                         | 描述                                              |
| :----------------------------------------------------------- | :------------------------------------------------ |
| [acos(x)](https://www.runoob.com/python/func-number-acos.html) | 返回x的反余弦弧度值。                             |
| [asin(x)](https://www.runoob.com/python/func-number-asin.html) | 返回x的反正弦弧度值。                             |
| [atan(x)](https://www.runoob.com/python/func-number-atan.html) | 返回x的反正切弧度值。                             |
| [atan2(y, x)](https://www.runoob.com/python/func-number-atan2.html) | 返回给定的 X 及 Y 坐标值的反正切值。              |
| [cos(x)](https://www.runoob.com/python/func-number-cos.html) | 返回x的弧度的余弦值。                             |
| [hypot(x, y)](https://www.runoob.com/python/func-number-hypot.html) | 返回欧几里德范数 sqrt(x*x + y*y)。                |
| [sin(x)](https://www.runoob.com/python/func-number-sin.html) | 返回的x弧度的正弦值。                             |
| [tan(x)](https://www.runoob.com/python/func-number-tan.html) | 返回x弧度的正切值。                               |
| [degrees(x)](https://www.runoob.com/python/func-number-degrees.html) | 将弧度转换为角度,如degrees(math.pi/2) ， 返回90.0 |
| [radians(x)](https://www.runoob.com/python/func-number-radians.html) | 将角度转换为弧度                                  |



## 字符串



字符串就是字符列表

字符列表的两种排序方式

* 0到n-1
* -n到-1



**截取字符串**

 使用 **[头下标:尾下标]** 来截取相应的字符串(不包含尾下标) ,下标可以使用两种不同的顺序进行截取，下标可以少一个，表示到头/到尾

```python
s = "a1a2···an" 
b=s[0,-2]
```

[头:尾:步长]

还可以指定第三个参数，表示隔几个取一次



**字符串运算**

+表示拼接字符串，*表示重复次数



**格式化字符串**

```python
"My name is %s and weight is %d kg!" % ('Zara', 21) 
```

| 符  号 | 描述                                 |
| :----- | :----------------------------------- |
| %c     | 格式化字符及其ASCII码                |
| %s     | 格式化字符串                         |
| %d     | 格式化整数                           |
| %u     | 格式化无符号整型                     |
| %o     | 格式化无符号八进制数                 |
| %x     | 格式化无符号十六进制数               |
| %X     | 格式化无符号十六进制数（大写）       |
| %f     | 格式化浮点数字，可指定小数点后的精度 |
| %e     | 用科学计数法格式化浮点数             |
| %E     | 作用同%e，用科学计数法格式化浮点数   |
| %g     | %f和%e的简写                         |
| %G     | %F 和 %E 的简写                      |
| %p     | 用十六进制数格式化变量的地址         |



**字符串内建函数**

[参考](https://www.runoob.com/python/python-strings.html)

| 函数                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| **[string.join(seq)](https://www.runoob.com/python/att-string-join.html)** | 以 string 作为分隔符，将 seq 中所有的元素(的字符串表示)合并为一个新的字符串 |
|                                                              |                                                              |



## 列表

[]表示

操作与字符列表相同

append()添加元素

**删除元素**

```python
list1 = ['physics', 'chemistry', 1997, 2000]
del list1[2]
```



**常用函数**

| 方法                                                         | 描述                     |
| ------------------------------------------------------------ | ------------------------ |
| [ cmp(list1, list2)](https://www.runoob.com/python/att-list-cmp.html) | 比大小                   |
| [ len(list)](https://www.runoob.com/python/att-list-len.html) | 元素个数                 |
| [max(list)](https://www.runoob.com/python/att-list-max.html) | 最大值                   |
| [min(list)](https://www.runoob.com/python/att-list-min.html) | 最小值                   |
| [ list(seq)](https://www.runoob.com/python/att-list-list.html) | 元组转数组               |
| [list.append(obj)](https://www.runoob.com/python/att-list-append.html) | 添加元素                 |
| [list.extend(seq)](https://www.runoob.com/python/att-list-extend.html) | 添加多个值               |
| [list.count(obj)](https://www.runoob.com/python/att-list-count.html) | 统计某个元素的个数       |
| [ list.index(obj)](https://www.runoob.com/python/att-list-index.html) | 查找元素第一个出现的位置 |
| [list.remove(obj)](https://www.runoob.com/python/att-list-remove.html) | 移除第一个匹配到的       |
| [ list.reverse()](https://www.runoob.com/python/att-list-reverse.html) | 反转列表的元素           |
| [ list.sort(cmp=None, key=None, reverse=False)](https://www.runoob.com/python/att-list-sort.html) | 对列表进行排序           |



## 元组

()表示，类似[]，但不能二次赋值

[ tuple(seq)](https://www.runoob.com/python/att-tuple-tuple.html)将列表转成元组



## 字典

{}表示

keys()获得所有key

values()获得所有value

**删除**

```python
tinydict = {'Name': 'Zara', 'Age': 7, 'Class': 'First'}
del tinydict['Name']  # 删除键是'Name'的条目
```



**常用函数**

| 方法                                                         | 描述                              |
| ------------------------------------------------------------ | --------------------------------- |
| has_key(obj)                                                 | 是否存在key                       |
| items()                                                      | 返回一个元组                      |
| [dict.update(dict2)](https://www.runoob.com/python/att-dictionary-update.html) | 用dict2中的数据去更新dict中的数据 |





## 类型转换

类似创建一个对象int(x)



# 运算符



**算数运算符**

| 运算符 | 描述 |
| ------ | ---- |
| +      | 加   |
| -      | 减   |
| *      | 乘   |
| /      | 除   |
| **     | 幂   |
| //     | 整除 |



**比较运算符**

| 运算符 | 描述         |
| ------ | ------------ |
| ==     | 内容是否相同 |
| !=     |              |
| >      |              |
| <      |              |
| \>=    |              |
| <=     |              |



**位运算符**

| 运算符 | 描述 |
| ------ | ---- |
| &      | 与   |
| \|     | 或   |
| ^      | 异或 |
| ~      | 取反 |
| <<     | 左移 |
| \>>    | 右移 |



**逻辑运算符**

| 运算符 | 描述 |
| ------ | ---- |
| and    |      |
| or     |      |
| not    |      |



**成员运算符**

| 运算符 | 描述                 |
| ------ | -------------------- |
| in     | 指定序列中存在该值   |
| not in | 指定序列中不存在该值 |



**身份运算符**

| 运算符  | 描述               |
| ------- | ------------------ |
| is      | 是否引用同一个对象 |
| not  is |                    |



# 条件语句



```python
if 判断条件：
    执行语句……
else：
    执行语句……
```



```python
if 判断条件1:
    执行语句1……
elif 判断条件2:
    执行语句2……
elif 判断条件3:
    执行语句3……
else:
    执行语句4……
```



# 循环语句



**while**

```python
while 判断条件(condition)：
    执行语句(statements)……
```



**for**

```python
for iterating_var in sequence:
   statements(s)
```



**pass**

 pass是空语句，是为了保持程序结构的完整性。 



# 时间日期



 Python 提供了一个 time 和 calendar 模块可以用于格式化日期和时间。 



## time模块



| 方法                                                 | 描述                                      |
| ---------------------------------------------------- | ----------------------------------------- |
| time.time()                                          | 获得当前时间戳(带小数点，可以用int转一下) |
| time.localtime()                                     | 获得当前时间元组                          |
| time.localtime(time.time())                          | 时间戳转时间                              |
| time.asctime()                                       | 格式化的时间                              |
| time.asctime( time.localtime(time.time()) )          | 格式化的时间                              |
| time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()) | 自定义格式化时间                          |



**时间元组**

用一个元组装起来的9组数字处理时间: 分别代表年月日时分秒星期，第几天，夏令时





# 函数



**函数的定义**

```
def functionname( parameters ):
   "函数_文档字符串"
   function_suite
   return [expression]
```



**默认参数**

```
def printinfo( name, age = 35 ):
   "打印任何传入的字符串"
   print "Name: ", name
   print "Age ", age
   return
```



**不定长参数**

```
def functionname([formal_args,] *var_args_tuple ):
   "函数_文档字符串"
   function_suite
   return [expression]
```



**匿名函数**

```
lambda [arg1 [,arg2,.....argn]]:expression
```



# 异常



```
try:
<语句>        #运行别的代码
except <名字>：
<语句>        #如果在try部份引发了'name'异常
except <名字>，<数据>:
<语句>        #如果引发了'name'异常，获得附加的数据
else:
<语句>        #如果没有异常发生
```



```
try:
<语句>
finally:
<语句>    #退出try时总会执行
raise
```





# 模块



模块是一个py文件

import搜索路径

* 当前所在目录
*   shell 变量 PYTHONPATH 下的每个目录 
*  Python会察看默认路径。UNIX下，默认路径一般为/usr/local/lib/python/ 

 模块搜索路径存储在 system 模块的 sys.path 变量中。变量里包含当前目录，PYTHONPATH和由安装过程决定的默认目录。 



from...import...

引入某个模块中的一部分



# IO



| 方法        | 描述                                                         |
| ----------- | ------------------------------------------------------------ |
| print()     | 输出到屏幕                                                   |
| row_input() | 从标准输入读取一个行，并返回一个字符串（去掉结尾的换行符）   |
| input()     | 和 **raw_input([prompt])** 函数基本类似，但是 input 可以接收一个Python表达式作为输入，并将运算结果返回 |



 Python 提供了必要的函数和方法进行默认情况下的文件基本操作。你可以用 **file** 对象做大部分的文件操作。 

**open**

获得一个file对象

```
file object = open(file_name [, access_mode][, buffering])
```

| 模式 | 描述                                                         |
| :--- | :----------------------------------------------------------- |
| t    | 文本模式 (默认)。                                            |
| x    | 写模式，新建一个文件，如果该文件已存在则会报错。             |
| b    | 二进制模式。                                                 |
| +    | 打开一个文件进行更新(可读可写)。                             |
| U    | 通用换行模式（不推荐）。                                     |
| r    | 以只读方式打开文件。文件的指针将会放在文件的开头。这是默认模式。 |
| rb   | 以二进制格式打开一个文件用于只读。文件指针将会放在文件的开头。这是默认模式。一般用于非文本文件如图片等。 |
| r+   | 打开一个文件用于读写。文件指针将会放在文件的开头。           |
| rb+  | 以二进制格式打开一个文件用于读写。文件指针将会放在文件的开头。一般用于非文本文件如图片等。 |
| w    | 打开一个文件只用于写入。如果该文件已存在则打开文件，并从开头开始编辑，即原有内容会被删除。如果该文件不存在，创建新文件。 |
| wb   | 以二进制格式打开一个文件只用于写入。如果该文件已存在则打开文件，并从开头开始编辑，即原有内容会被删除。如果该文件不存在，创建新文件。一般用于非文本文件如图片等。 |
| w+   | 打开一个文件用于读写。如果该文件已存在则打开文件，并从开头开始编辑，即原有内容会被删除。如果该文件不存在，创建新文件。 |
| wb+  | 以二进制格式打开一个文件用于读写。如果该文件已存在则打开文件，并从开头开始编辑，即原有内容会被删除。如果该文件不存在，创建新文件。一般用于非文本文件如图片等。 |
| a    | 打开一个文件用于追加。如果该文件已存在，文件指针将会放在文件的结尾。也就是说，新的内容将会被写入到已有内容之后。如果该文件不存在，创建新文件进行写入。 |
| ab   | 以二进制格式打开一个文件用于追加。如果该文件已存在，文件指针将会放在文件的结尾。也就是说，新的内容将会被写入到已有内容之后。如果该文件不存在，创建新文件进行写入。 |
| a+   | 打开一个文件用于读写。如果该文件已存在，文件指针将会放在文件的结尾。文件打开时会是追加模式。如果该文件不存在，创建新文件用于读写。 |
| ab+  | 以二进制格式打开一个文件用于追加。如果该文件已存在，文件指针将会放在文件的结尾。如果该文件不存在，创建新文件用于读写。 |



**close**

刷新缓冲区并关闭该文件



**write**

```
fileObject.write(string)
```

write方法不会在末尾添加\n



**read**

```
fileObject.read([count])
```



# OS

 Python的os模块提供了帮你执行文件处理操作的方法，比如重命名和删除文件。 

**rename**

**remove**

**mkdir**

**rmdir**



# 面向对象



## class



```
class ClassName:
   '类的帮助信息'   #类文档字符串
   class_suite  #类体
```

