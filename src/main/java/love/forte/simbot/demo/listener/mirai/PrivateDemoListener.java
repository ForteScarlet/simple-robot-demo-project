package love.forte.simbot.demo.listener.mirai;

import com.forte.qqrobot.anno.Listen;
import com.forte.qqrobot.anno.depend.Beans;
import com.forte.qqrobot.anno.template.OnPrivate;
import com.forte.qqrobot.beans.messages.msgget.PrivateMsg;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.sender.MsgSender;

import java.text.MessageFormat;

/**
 * <p> 用作示例的监听器类
 *
 * <p> 类上需要标注@beans，因为此框架存在依赖注入功能。
 *
 * <p> 此示例类中监听函数是与私聊有关的。
 *
 * <p> 当你出现了：发送消息成功无报错、酷Q日志中也显示发送成功无报错，但是实际上机器人没有发出任何消息的时候，此时大概率是消息被屏蔽。
 * 这种情况的原因很多，例如机器人账号异地登录、等级太低、没有活跃度、很少登录、腾讯看你像是机器人等各种因素。
 *
 * <p> 解决办法目前已知可尝试：手动登录bot账号去水群、发消息提升活跃度、多挂机两天摆脱嫌疑、提升活跃度、充值会员（不一定能行）等方法。
 *
 * @author Heilant Gong
 * https://github.com/HeilantG
 */

@Beans
public class PrivateDemoListener {
    /**
     * <p> 测试此监听消息的方法：私聊机器人发送任意消息。
     *
     * <p> 监听私信消息，打印在控制台，不做任何回复。
     *
     * <p> {@link OnPrivate}注解代表监听私信消息，
     * 他等同于参数为{@link MsgGetTypes#privateMsg}的{@link Listen}
     *
     * <p> 监听注解相关的详细内容参考文档或入群询问：
     * <p> http://simple-robot-doc.forte.love/1408365
     * <p> http://simple-robot-doc.forte.love/1780853
     *
     * <p> 也就是说，@OnPrivate 等同于 @Listen(MsgGetTypes.privateMsg)
     * <p> @OnPrivate注解属于一种模板注解，其他类似的模板注解参考包路径{@link com.forte.qqrobot.anno.template}下的全部注解。
     *
     * @param priMsg 由于你监听的是“私信消息”，因此你的参数中可以填入私信消息对应的封装接口，即{@link PrivateMsg} <br>
     *               至于其他监听类型应该填写什么参数，你可以参考{@link MsgGetTypes}枚举的元素和他们的参数。基本上都是见明知意的东西。
     * @param sender 送信器。所有的监听函数都可以注入这个类，它包含了三大送信器来支持你发送、获取消息。
     */
    @OnPrivate
    public void privateMsg(PrivateMsg priMsg, MsgSender sender) {
        // thisCode 代表当前接收到消息的机器人账号。
        final String botCode = priMsg.getThisCode();
        // 发消息人的昵称
        final String nickname = priMsg.getNickname();
        // 发消息人的账号
        final String code = priMsg.getCode();
        // 发消息人发的消息
        final String msg = priMsg.getMsg();

        // 由于拼接的东西比较长，用java自带的MessageFormat对消息进行格式化，会比较直观
        final MessageFormat message = new MessageFormat("机器人{0}接收到了{1}({2})的私信消息：{3}");

        final String printMsg = message.format(new Object[]{botCode, nickname, code, msg});

        // 红色显眼儿一点
        System.err.println(printMsg);
    }

}
