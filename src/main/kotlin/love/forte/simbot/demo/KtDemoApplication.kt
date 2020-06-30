@file:Suppress("KDocUnresolvedReference")

package love.forte.simbot.demo

import com.forte.qqrobot.BaseApplication
import com.forte.qqrobot.SimpleRobotApplication

/**
 * 此类用于承载启动器注解 [SimpleRobotApplication]
 *
 * @author [ForteScarlet](https://github.com/ForteScarlet)
 */
@SimpleRobotApplication
class KtDemoApplication

/**
 * 通过BaseApplication启动
 */
fun main(args: Array<String>) {
    run(KtDemoApplication::class.java, *args)
//    BaseApplication.runAuto(KtDemoApplication::class.java, *args)
}


/** 简单写的run方法 */
@Suppress("HasPlatformType")
fun run(appClass: Class<*>, vararg args: String)
        = BaseApplication.runAuto(appClass, *args)!!
