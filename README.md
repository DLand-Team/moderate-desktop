# moderate-desktop
基于Java+chromium的桌面程序开发框架

#### 使用JetBrains Runtime 17，springboot 3.0，JCEF版本为chromium 104

#### JetBrains Runtime下载地址
- https://github.com/JetBrains/JetBrainsRuntime/releases
- 在Binaries for developers这一栏中下载“JBR with JCEF”，文件名以“jbrsdk_jcef”开头的

#### 为什么选择Java
- 大部分前端开发者的技术栈升级路线为java，只有极少数才会去接触QT或C#
- 同时后端程序也能快速上手桌面程序开发

#### 相比于Electron的优势
- 引入springboot，可以使用http与websocket进行webview与java的交互，更加符合web开发人员的习惯
- 引入springboot，可以使用spring的周边生态，例如实现客户端使用mybatis操作数据库
- 掌握了浏览器内核，实现多webview模式
- java提供了原生多线程支持
- 通过java也能够更加简单地使用native库，例如opencv
