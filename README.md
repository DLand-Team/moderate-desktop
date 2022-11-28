# moderate-desktop
基于Java+chromium的桌面程序开发框架

#### 使用JetBrains Runtime 17，SpringBoot 3.0，JCEF版本为Chromium 104

#### 项目进度
- 已搭建基于jcef的java桌面端框架

#### 下一目标
- 添加前端js部分，并整合入桌面端

#### 何为JetBrains Runtime
- JetBrains Runtime是一个运行时环境，用于在 Windows，MacOS和Linux上运行IntelliJ平台的各种产品。JetBrains Runtime基于OpenJDK项目，并进行了一些修改。这些修改包括：抗锯齿，Linux 上增强的字体渲染，HiDPI支持，对Swing的部分优化与扩展以及其他小的增强功能
- JetBrains Runtime 17（JDK 17）中集成了JCEF

#### 何为JCEF
- Java Chromium嵌入式框架（JCEF）。 一个简单的框架，用于使用Java编程语言在桌面应用程序中嵌入基于Chromium的浏览器
- JCEF项目地址：https://bitbucket.org/chromiumembedded/java-cef/src/master/

#### 获取JetBrains Runtime
- https://github.com/JetBrains/JetBrainsRuntime/releases
- 在Binaries for developers这一栏中下载“JBR with JCEF”，文件名以“jbrsdk_jcef”开头的

#### 为什么选择Java与JCEF
- 大部分前端开发者的技术栈升级路线为Java，只有极少数才会去接触QT或C#
- 同时后端程序员也能快速上手桌面程序开发
- JCEF使用完整的Chromium内核，兼容前端所有框架，使用js即可进行高效界面开发

#### 本框架运行原理
- 整体基于SpringBoot，运行在JetBrains Runtime中
- 框架代码使用Maven开发Java Web的模式
- 前端代码打包后，放入Java Web项目的resources/static目录下，也就是使用SpringBoot将前端代码发布到localhost下
- SpringBoot启动完成后，启动Java界面，在Java界面中运行Chromium内核，显示SpringBoot发布的前端页面
- JS与Java的交互，即成为了web项目前后端的交互。可以使用Http + Websocket的方式，也可以用Java去定义CEF与页面的交互接口

#### 相比于Electron的优势
- 无需繁重的Webpack配置，减少学习成本
- 引入SpringBoot，可以使用Http与Websocket进行WebView与Java的交互，更加符合Web开发人员的习惯，减少学习成本
- 引入SpringBoot，可以使用Spring的周边生态，例如实现客户端使用MyBatis操作数据库，减少学习成本
- 可实现多WebView模式，提高代码灵活性，实现“微前端”的概念
- 掌握了浏览器内核，可以自定义交互接口
- Java提供了原生多线程支持
- 通过Java也能够更加简单地使用Native库，例如OpenCV

#### 卷与反卷
- 卷，即同一个业务，换着花样，各种框架技术栈轮着写
- 反卷，即使用一套框架，一个技术栈，去应对各种不同的业务
- 以此框架来表示反对内卷的决心，不随波逐流，有自己的主见，有策略地学习
