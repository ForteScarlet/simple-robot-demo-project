# Mirai-Demo 

此目录为[simple-robot-component-mirai](https://github.com/ForteScarlet/simple-robot-component-mirai) 的Listener 示例
 
# 如何使用这个Demo?

首先确保你想要使用[Mirai](https://github.com/mamoe/mirai) 来作为BOT的核心。

由于使用了Mirai核心，所以首先我们需要修改pom.xml中的一个依赖，默认情况下使用的是cqhttp的插件，
所以我们需要修改pom文件将其注释掉。

需要注释(停止使用)的依赖：
```xml
         <!-- simbot cqhttp 组件 -->
         <dependency>
              <groupId>io.github.ForteScarlet.simple-robot-core</groupId>
              <artifactId>component-forcoolqhttpapi</artifactId>
              <version>${simbot.comp-cqhttp.version}</version>
         </dependency>
```

然后启用mirai的依赖，此时mirai的依赖应该处于注释状态，将其解除注释.
需要解除注释(启用)的依赖：

```xml
        <!-- simbot mirai 组件，默认使用cqhttp，而不是mirai -->
        <dependency>
            <groupId>love.forte.simple-robot-component</groupId>
            <artifactId>component-mirai</artifactId>
            <version>${simbot.comp-mirai.version}</version>
        </dependency>
```

然后 你需在配置文件 simple-robot-conf.properties 中修改两个配置
* 配置包扫描路径，将默认的根路径修改为Mirai组件的路径

```properties
    #需要进行的包扫描路径，默认为空，即扫描启动器根路径
    simbot.core.scannerPackage=love.forte.simbot.demo.listener.miraimirai
```

* 配置bot的登录信息
```properties
    simbot.core.bots=QQ号:密码
    #示例 simbot.core.bots=123456789:1a2b3c4d
```

在做完以上的修改后，你就可以是使用JavaDemoApplication来运行你的bot了，
不出意外的话你的控制台最终会输出：

```
[2020-07-01T01:19:10.649] [INFO] 账号信息获取成功: 账号: {QQ账号}, 昵称: {QQ昵称}, 等级: -1
```

这时，你的bot就运行成功了，接下来我建议你去阅读一下GroupDemoListener与PrivateDemoListener的源代码，
了解一下程序究竟是如何与Mirai核心进行交互的，同时学习如何自定义自己的机器人

# 说明

这个Demo是由[HeilantG](https://github.com/HeilantG) 所撰写，大部分代码是对base做出的轻微修改，如果在运行中出现了什么问题，
欢迎加入[simple-robot交流装逼群](shang.qq.com/wpa/qunwpa?idkey=e1bfa2749c42f6fcefa68d0836661e72364cea1638a6c8cd8334167052031bcc)
向群主提问