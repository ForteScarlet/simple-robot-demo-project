package love.forte.simbot.demo.listener.base

import com.forte.qqrobot.anno.Filter
import com.forte.qqrobot.anno.Listen
import com.forte.qqrobot.anno.depend.Beans
import com.forte.qqrobot.anno.depend.FilterValue
import com.forte.qqrobot.anno.template.OnPrivate
import com.forte.qqrobot.beans.messages.msgget.PrivateMsg
import com.forte.qqrobot.beans.messages.types.MsgGetTypes
import com.forte.qqrobot.sender.MsgSender

/**
 *
 *  用作示例的监听器类
 *
 *
 *  类上需要标注@beans，因为此框架存在依赖注入功能。
 *
 *
 *  此示例类中监听函数是与私聊有关的。
 *
 *
 *  当你出现了：发送消息成功无报错、酷Q日志中也显示发送成功无报错，但是实际上机器人没有发出任何消息的时候，此时大概率是消息被屏蔽。
 * 这种情况的原因很多，例如机器人账号异地登录、等级太低、没有活跃度、很少登录、腾讯看你像是机器人等各种因素。
 *
 *
 *  解决办法目前已知可尝试：手动登录bot账号去水群、发消息提升活跃度、多挂机两天摆脱嫌疑、提升活跃度、充值会员（不一定能行）等方法。
 *
 * @author [ ForteScarlet ](https://github.com/ForteScarlet)
 */
@Beans
class PrivateDemoListenerKt {
    /**
     *
     *  测试此监听消息的方法：私聊机器人发送任意消息。
     *
     *
     *  监听私信消息，打印在控制台，不做任何回复。
     *
     *
     *  [OnPrivate]注解代表监听私信消息，
     * 他等同于参数为[MsgGetTypes.privateMsg]的[Listen]
     *
     *
     *  监听注解相关的详细内容参考文档或入群询问：
     *
     *  http://simple-robot-doc.forte.love/1408365
     *
     *  http://simple-robot-doc.forte.love/1780853
     *
     *
     *  也就是说，@OnPrivate 等同于 @Listen(MsgGetTypes.privateMsg)
     *
     *  @OnPrivate注解属于一种模板注解，其他类似的模板注解参考包路径[com.forte.qqrobot.anno.template]下的全部注解。
     *
     * @param priMsg 由于你监听的是“私信消息”，因此你的参数中可以填入私信消息对应的封装接口，即[PrivateMsg] <br></br>
     * 至于其他监听类型应该填写什么参数，你可以参考[MsgGetTypes]枚举的元素和他们的参数。基本上都是见明知意的东西。
     * @param sender 送信器。所有的监听函数都可以注入这个类，它包含了三大送信器来支持你发送、获取消息。
     */
    @OnPrivate
    fun privateMsg(priMsg: PrivateMsg, sender: MsgSender?) { // thisCode 代表当前接收到消息的机器人账号。
        val botCode = priMsg.thisCode
        // 发消息人的昵称
        val nickname = priMsg.nickname
        // 发消息人的账号
        val code = priMsg.code
        // 发消息人发的消息
        val msg = priMsg.msg
        // 拼接控制台要打印的消息，在开头加一个“kt”以便于跟java的监听日志有所区分
        val printMsg = "kt 机器人 $botCode 接收到了 $nickname($code) 的私信消息：$msg"
        // 红色显眼儿一点
        System.err.println(printMsg)
    }

    /**
     *
     *
     *  测试此监听消息的方法：私聊机器人，发送一句：我是张三,我今年24岁了
     *
     *
     *  第二个示例，依旧是监听私信消息，但是多了一个注解：[Filter]
     *
     *
     *  filter用来过滤接收到的消息的内容，其参数很多，且支持自定义过滤器，详细的相关内容请查阅文档或入群询问 :
     *
     *  http://simple-robot-doc.forte.love/1408366
     *
     *  http://simple-robot-doc.forte.love/1780854
     *
     *  http://simple-robot-doc.forte.love/1536507
     *
     *
     *  此处介绍的主要是filter的正则消息匹配与消息内容提取的相关内容。
     *
     *  可以看到，下面的filter参数为“我是{{name}},我今年{{age,\d+}}岁了” 这并不是传统的正则书写规则，而是配合了参数提取的匹配内容。
     *
     *  参数中有两个参数：name和age，这两个参数分别对应了filter参数中的{{name}}中的name和{{age,\d+}}中的age。
     *
     *  下面的过滤的消息匹配正则为："我是(.+),我今年(\d+)岁了", 当接收到了符合规则的消息后，
     * 通过[FilterValue]注解在参数中指定对应名称的参数即可获取到从消息中提取出来的消息。
     *
     *
     *  过滤参数提取的详细内容，可参考文档：http://simple-robot-doc.forte.love/1780854
     *
     *
     *  当然，如果你不打算使用参数提取，那么你可以直接使用普通的正则。
     *
     *
     *  不过需要注意，参数提取只能在[Filter.keywordMatchType] 的参数为 与正则相关 的匹配方式的时候才能够正常使用。
     *
     */
    @Listen(MsgGetTypes.privateMsg)
    @Filter("我是{{name,.+}},我今年{{age,\\d+}}岁了")
    fun whoAreYou(priMsg: PrivateMsg?, sender: MsgSender, @FilterValue("name") name: String, @FilterValue("age") age: Long) { // 由于拼接的东西比较长，用java自带的MessageFormat对消息进行格式化，会比较直观
        // 格式化为msg
        val sendMsg = "kt 我知道啦！你是「$age」岁的「$name」对吧！"
        // 发送私信消息。下述几个方法效果相同。
        sender.SENDER.sendPrivateMsg(priMsg, sendMsg)
        //        sender.SENDER.sendPrivateMsg(priMsg.getQQ(), sendMsg);
//        sender.SENDER.sendPrivateMsg(priMsg.getQQCode(), sendMsg);
//        sender.SENDER.sendPrivateMsg(priMsg.getCode(), sendMsg);
    }
}