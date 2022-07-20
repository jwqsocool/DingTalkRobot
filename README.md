# **DingTalkRobot**
钉钉机器人
目前还在开发中，现在可以实现向指定钉钉群发送不同类型的消息，以及简单回复功能，并且可以检测全局异常并发送告警信息到指定群中

## **打包并导入** 
打包并导入Jar包至需要此工具类的项目中,具体教程详见下链接
>[导入教程](https://blog.csdn.net/weixin_45480245/article/details/118707400?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-4-118707400-blog-115514519.pc_relevant_multi_platform_whitelistv1_exp2&spm=1001.2101.3001.4242.3&utm_relevant_index=7)

## **maven依赖**
添加以下依赖以确保运行
```Java
        <dependency>
            <groupId>com.jwq.robot.vtec</groupId>
            <artifactId>Vtec</artifactId>
            <version>0.1.0</version>
            <scope>system</scope>
            <systemPath>${project.basedir}/lib/Vtec.jar</systemPath>
        </dependency>
        <dependency>
            <groupId>com.aliyun</groupId>
            <artifactId>dingtalk</artifactId>
            <version>1.3.73</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>2.0.7</version>
        </dependency>
        <dependency>
            <groupId>com.aliyun</groupId>
            <artifactId>alibaba-dingtalk-service-sdk</artifactId>
            <version>2.0.0</version>
        </dependency>

```

## **使用例**
使用发送到指定钉钉群的工具类

```Java
@RestController
public class Dingtest1 {

    GroupConfig group = new GroupConfig(
            "_此处填写自己机器人的webhook_",
            "_此处为机器人测试群中的加签_");

    @GetMapping("/test")
    public void test() {

        DingTalkSendingUtilsV1.sendTextV1(group, "haha", true);

    }
}
```
使用全局异常监控工具类
```Java
@RestController
@ControllerAdvice
public class Dingtest2 {

    GroupConfig group = new GroupConfig(
            "_此处填写自己机器人的webhook_",
            "_此处为机器人测试群中的加签_");

    @ExceptionHandler(value = Exception.class)
    public void globalExceptionHandler(Exception e) throws ApiException {
        //全局异常捕获，并使用异常发送工具类进行消息推送
        DingTalkSendingException.dingTalkSendingExceptionByMarkdown(e,group);
    }

    @GetMapping("/test")
    public void test() {
            System.out.println(1/0); //测试异常监控运行
    }

}
```
