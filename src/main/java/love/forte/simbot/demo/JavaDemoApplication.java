package love.forte.simbot.demo;

import com.forte.qqrobot.BaseApplication;
import com.forte.qqrobot.SimpleRobotApplication;

import java.io.IOException;

/**
 * 通过BaseApplication启动
 * 注意标记 {@link SimpleRobotApplication} 注解
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
@SimpleRobotApplication
public class JavaDemoApplication {
    public static void main(String[] args) throws IOException {
        BaseApplication.runAuto(JavaDemoApplication.class, args);
    }
}
