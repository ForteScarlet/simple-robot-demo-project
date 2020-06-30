package love.forte.simbot.demo.listener.base

import com.forte.qqrobot.anno.Filter
import com.forte.qqrobot.anno.Listen
import com.forte.qqrobot.anno.depend.Beans
import com.forte.qqrobot.anno.template.OnGroup
import com.forte.qqrobot.beans.messages.msgget.GroupMsg
import com.forte.qqrobot.beans.messages.types.MsgGetTypes
import com.forte.qqrobot.beans.types.KeywordMatchType
import com.forte.qqrobot.sender.MsgSender

/**
 *
 *
 *  用作示例的监听器类。默认的监听器示例。
 *
 *
 *  类上需要标注@beans，因为此框架存在依赖注入功能。
 *
 *
 *  此示例类中监听函数是与私聊有关的。
 *
 *
 *  当你出现了：发送消息成功无报错、酷Q日志中也显示发送成功无报错，但是实际上机器人没有发出任何消息的时候，此时大概率是消息被屏蔽。
 * 这种情况的原因很多，例如机器人账号异地登录、等级太低、没有活跃度、很少登录、腾讯看你像是机器人等各种因素
 * 。
 *
 *  解决办法目前已知可尝试：手动登录bot账号去水群、发消息提升活跃度、多挂机两天摆脱嫌疑、提升活跃度、充值会员（不一定能行）等方法。
 *
 * @author [ ForteScarlet ](https://github.com/ForteScarlet)
 */
@Beans
class GroupDemoListenerKt {
    /**
     *
     *  测试此监听消息的方法：在机器人所在群发送任意消息。
     *
     *
     *  监听群消息，打印在控制台，不做任何回复。
     *
     *
     *  [OnGroup]注解代表监听群消息，
     * 他等同于参数为[MsgGetTypes.groupMsg]的[Listen]
     *
     *
     *  监听注解相关的详细内容参考文档或入群询问：
     *
     *  http://simple-robot-doc.forte.love/1408365
     *
     *  http://simple-robot-doc.forte.love/1780853
     *
     *
     *  也就是说，@OnGroup 等同于 @Listen(MsgGetTypes.groupMsg)
     *
     *  @OnGroup注解属于一种模板注解，其他类似的模板注解参考包路径[com.forte.qqrobot.anno.template]下的全部注解。
     *
     * @param groupMsg 由于你监听的是“群消息”，因此你的参数中可以填入私信消息对应的封装接口，即[GroupMsg] <br></br>
     * 至于其他监听类型应该填写什么参数，你可以参考[MsgGetTypes]枚举的元素和他们的参数。基本上都是见明知意的东西。
     */
    @OnGroup
    fun onGroupMsg(groupMsg: GroupMsg) { // thisCode 代表当前接收到消息的机器人账号。
        val botCode = groupMsg.thisCode
        // 发消息人的群昵称或者昵称
        val nickname = groupMsg.remarkOrNickname
        // 发消息人的账号
        val code = groupMsg.code
        // 接收到消息的群号
        val groupCode = groupMsg.groupCode
        // 发消息人发的消息
        val msg = groupMsg.msg
        // 拼接控制台输出的消息，在开头加一个“kt”以便于跟java的监听日志有所区分
        val printMsg = "kt 机器人 $botCode 接收到了群 $groupCode 中 $nickname($code) 发送的群消息：$msg"
        // 红色显眼儿一点
        System.err.println(printMsg)
    }

    /**
     *
     *  测试此监听消息的方法：在机器人所在at他并说：hi
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
     *  此处介绍的主要是filter的at功能与keywordMatchType参数与其中的坑。
     *
     *  [Filter.at]参数代表是否只有被at的时候才会触发此监听。听上去很简单，不是吗？
     * 但是首先你要知道，at在qq中也属于消息中的一部分，以酷Q为例，酷Q会将at消息转化为对应的CQ码，
     * 因此当有人at机器人，并说了一句“hi”的时候，它实际接收到的消息是：“[CQ:at,qq=123456789] hi”而不是 “hi”或者 “@bot hi”。
     * 这个时候，假如你不修改[Filter.keywordMatchType]参数的话，你直接匹配“hi”是无法匹配到内容的。
     *
     *
     *  keywordMatchType参数内容是枚举[KeywordMatchType]，它代表了filter使用什么方式对消息进行匹配，例如正则、equals、contains等。
     * 此示例中，我的参数为[KeywordMatchType.RE_CQCODE_TRIM_EQUALS]，它代表：移除消息中的CQ码，并将消息执行trim(), 然后使用equals进行匹配。
     * 因此，at消息中的CQ码移除再trim后，留下的就是你想要匹配的“hi”了。
     *
     *
     *  至于枚举[KeywordMatchType]中都有哪些匹配方式，你可以参考文档或者直接点进去看看。基本上枚举名的含义很好猜的，我也全部写了注释。
     *
     *
     * @param groupMsg 由于你监听的是“群消息”，因此你的参数中可以填入私信消息对应的封装接口，即[GroupMsg] <br></br>
     * 至于其他监听类型应该填写什么参数，你可以参考[MsgGetTypes]枚举的元素和他们的参数。基本上都是见明知意的东西。
     *
     * @param sender 送信器。所有的监听函数都可以注入这个类，它包含了三大送信器来支持你发送、获取消息。当然，此方法中没有
     */
    @Listen(MsgGetTypes.groupMsg)
    @Filter(value = ["hi"], at = true, keywordMatchType = KeywordMatchType.RE_CQCODE_TRIM_EQUALS)
    fun whoAreYou(groupMsg: GroupMsg?, sender: MsgSender) {
        val sendMsg = "你AT我了！"
        // 发送群消息。下述几个方法效果相同。
        sender.SENDER.sendGroupMsg(groupMsg, sendMsg)
//        sender.SENDER.sendGroupMsg(groupMsg.getGroup(), sendMsg);
//        sender.SENDER.sendGroupMsg(groupMsg.getGroupCode(), sendMsg);
    }
}