package love.forte.simbot.demo.listener.dice

import com.forte.qqrobot.anno.Filter
import com.forte.qqrobot.anno.depend.Beans
import com.forte.qqrobot.anno.depend.FilterValue
import com.forte.qqrobot.anno.template.OnGroup
import com.forte.qqrobot.beans.messages.msgget.GroupMsg
import com.forte.qqrobot.sender.MsgSender
import java.util.concurrent.ThreadLocalRandom

/**
 *
 * 简易版的骰娘示例，给她一个值x，返回0-x之间的随机数
 *
 * @author [ ForteScarlet ](https://github.com/ForteScarlet)
 */
@Beans
class DiceListenerKt {
    /**
     * 获取一个Random实例。
     * @return [ThreadLocalRandom]
     */
    private val random: ThreadLocalRandom
        get() = ThreadLocalRandom.current()

    /**
     * <pre> 监听符合正则“\.d\d+”的消息，例如“.d6”或“.d”, 并计算0-这个数字的随机数
     *
     * @param groupMsg 群消息的消息载体
     * @param sender   送信器
     * @param max      随机数最大值, 如果max为null，则默认为6
    </pre> */
    @OnGroup
    @Filter("\\.d{{max,(\\d+)?}}")
    fun dice(groupMsg: GroupMsg, sender: MsgSender, @FilterValue("max") max: Int?) {
        val number = random.nextInt(0, (max ?: 6) + 1)
        // 将随机结果发送到这个群里, 消息里加一个“kt”以区分java与kt
        sender.SENDER.sendGroupMsg(groupMsg, "(kt)$number")
    }
}