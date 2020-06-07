# 小麦购代码生成器
## 项目介绍

小麦购代码生成器(xiaomaigou_code_generator)一款由小麦购商城(XiaoMaiGou.COM)开发的基于FreeMarker模板引擎的“代码生成神器”。即便是有几百个表的工程，也可以在瞬间完成基础代码的构建！用户只需建立数据库表结构，简单的几步操作就可以快速生成可以运行的一整套代码，可以极大地缩短开发周期，降低人力成本。小麦购代码生成器内置了当前java和前端主流的架构模板，SpringBoot+Mybatis-plus+Swagger、前后端分离的vue+elementUI 模板、swagger API模板、数据库文档模板等。 用户可以通过自己开发模板也实现生成php、python、C# 、c++、数据库存储过程等其它编程语言的代码。

项目地址： https://github.com/xiaomaigou/xiaomaigou_code_generator

## 安装教程

1.安装jdk1.8并配置环境变量；

2.运行startup.bat或者startup.sh；也可执行命令：

```shell
java -jar xiaomaigou_code_generator-1.2.3.jar
```

## 使用说明

1.建立数据库表，并设置字段的备注(用于生成Swagger备注等);

支持自定义类名className和字段名称attrName，只需在表备注或者字段备注开始部分设置自定义类名或者字段名称并使用英文模式下的分号";"与其它备注分隔即可；如表备注："ContentCategory;内容（广告）分类表"，则生成的类名className为："ContentCategory";字段备注:"ContentCategoryId;内容分类ID"，则生成的字段名称attrName即为："contentCategoryId"。

2.修改application-prod.properties中数据库配置信息和generator.properties中项目信息；

3.运行startup.bat或者startup.sh；也可执行命令：

```shell
java -jar xiaomaigou_code_generator-1.2.3.jar
```

4.浏览器访问

```
http://localhost:8081/
```

5.选择需要生成代码的表后点击“生成代码”，下载代码生成后，使用IDEA打开代码即可；

## Support

## Documentation

## Fork and Contribute

欢迎Pull request或贡献代码生成模板。

## About

小麦：xiaomaiyun@gmail.com













