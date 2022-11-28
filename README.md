# moderate-desktop
基于Java+chromium的桌面程序开发框架

#### 使用JetBrains Runtime 17，springboot 3.0，JCEF版本为chromium 104

#### 项目进度
- 已搭建基于jcef的java桌面端框架

#### 下一目标
- 添加前端js部分，并整合入桌面端

#### JetBrains Runtime下载地址
- https://github.com/JetBrains/JetBrainsRuntime/releases
- 在Binaries for developers这一栏中下载“JBR with JCEF”，文件名以“jbrsdk_jcef”开头的

#### 为什么选择Java与JCEF
- 大部分前端开发者的技术栈升级路线为java，只有极少数才会去接触QT或C#
- 同时后端程序员也能快速上手桌面程序开发
- JCEF使用完整的chromium内核，兼容前端所有框架，使用js即可进行高效界面开发

#### 相比于Electron的优势
- 无需繁重的webpack配置，减少学习成本
- 引入springboot，可以使用http与websocket进行webview与java的交互，更加符合web开发人员的习惯，减少学习成本
- 引入springboot，可以使用spring的周边生态，例如实现客户端使用mybatis操作数据库，减少学习成本
- 可实现多webview模式，提高代码灵活性，实现“微前端”的概念
- 掌握了浏览器内核，可以自定义交互接口
- java提供了原生多线程支持
- 通过java也能够更加简单地使用native库，例如opencv

#### 卷与反卷
- 卷，即为同一个业务，换着花样，各种框架技术栈轮着写
- 反卷，即为使用一套框架，一个技术栈，去应对各种不同的业务
- 以此框架来表示反对内卷的决心
