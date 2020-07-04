package love.forte.simbot.demo.listener.dice;

import cn.hutool.core.util.RandomUtil;
import com.forte.qqrobot.anno.Filter;
import com.forte.qqrobot.anno.depend.Beans;
import com.forte.qqrobot.anno.depend.FilterValue;
import com.forte.qqrobot.anno.template.OnGroup;
import com.forte.qqrobot.beans.messages.msgget.GroupMsg;
import com.forte.qqrobot.sender.MsgSender;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * 简易版的骰娘示例，给她一个值x，返回0-x之间的随机数
 *
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
@Beans
public class DiceListener {

    /**
     * 获取一个Random实例。
     * @return {@link Random}
     */
    private ThreadLocalRandom getRandom(){
        return ThreadLocalRandom.current();
    }

    /**
     * <pre> 监听符合正则“\.d(\d+)?”的消息，例如“.d6”或者“.d”, 并计算1-这个数字之间的随机数
     *
     * @param groupMsg 群消息的消息载体
     * @param sender   送信器
     * @param max      随机数最大值, 如果max为null，则默认为6
     */
    @OnGroup
    @Filter("\\.d{{max,(\\d+)?}}")
    public void dice(GroupMsg groupMsg, MsgSender sender, @FilterValue("max") Integer max){
        if(max == null){
            max = 6;
        }
        final int number = getRandom().nextInt(1, max + 1);
        // 将随机结果发送到这个群里
        sender.SENDER.sendGroupMsg(groupMsg, String.valueOf(number));
    }

}
