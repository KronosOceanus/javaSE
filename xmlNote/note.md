[TOC]
# XML/反射

## XML
* 文档声明必须在0行0列，指定版本（1.0）和编码
```xml
<?xml version="1.0" encoding="utf-8"?>
```
* 一般只有一个根元素，标签通常写法
```xml
    <root>
        <a></a>
        <a/>
    </root>
```
* 要想不被解析，放在CDATA中
```xml
    <![CDATA[ content ]]]>
```

## DTD
#### 文档声明
* 内部DTD：
    只对当前XML有效
* 外部DTD-本地（SYSTEM）：
    公司内部对自己项目使用
* 外部DTD-公共（PUBLIC）：
    DTD文件在网络上，一般都有框架提供
    
#### 元素声明
* 定义元素语法：
```xml
    <!ELEMENT 元素名 元素描述>
```
元素名自定义，元素描述包括符号和数据类型
###### 符号
* ?：只能出现一次
* *：任意次
* +：一/多次
* ()：给元素分组
* |：任选
* ,：必须按照对应顺序出现
###### 类型
* #PCDATA 表示内容是文本，不是标签

例：
```xml
    <!ELEMENT servlet (servlet-name,description?,(servlet-class|jsp-file)))
```
servlet包含三个标签，且必须按照顺序出现，servlet-name必须且只能出现一次，
description可选一次，servlet-class/jsp-file二选一，且只能出现一次

#### 属性声明
语法：
```xml
    <!ATTLIST 元素名称 属性名称 属性类型 默认值>
```
###### 属性类型
* CDATA表示值为字符数据

###### 默认值
* #REQUIRED：必须指定
* #IMPLIED：非必须
* #FIXED value：属性值固定

例：
```xml
    <!ATTLIST payment type CDATA #IMPLIED>
```
对应实例：
```xml
    <payment type="种类" />
```

## XMLSchema（功能更完善的约束）
笔记在schema中





