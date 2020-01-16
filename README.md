# platform-parent

公共模组项目
这并不是一个业务项目，只是对其它项目的一个支撑，是基础的工具和模块，其它项目通过对这个项目的MAVEN引用，来实现工具共享。
此MAVEN项目只做公共模组项目使用，不在项目内做单元测试，测试包及测试方法在其它项目当中引用此MAVEN项目做测试.

*****欢迎大家指出错误和提交自己的分支代码*****
提交分支基于guest-develop-*分支

platform-common: 一般公共类，一般公共方法  
platform-core: 核心公共接口，核心模型  
platform-db:通用数据库操作类  
platform-cache: 通用缓存操作  
platform-auth: 权限共享元素  
 
