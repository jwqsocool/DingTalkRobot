package com.jwq.robot.vtec.controller;


import com.jwq.robot.vtec.DingTalkUtils.DingTalkSendingUtilsV1;
import com.jwq.robot.vtec.config.GroupConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Component
@Resource
@RestController
@ControllerAdvice

public class TestController {


    GroupConfig jwq = new GroupConfig(
            "https://oapi.dingtalk.com/robot/send?access_token=ce293b5d77e4a26834d3faa6e68d58313d1ec75c753c31f50bc2a53342697f2f",
            "SEC5cf5211ca24054655370489c74d24889320c9725b4c1d96f6d6c89d60870e74a");

    GroupConfig shb = new GroupConfig(
            "https://oapi.dingtalk.com/robot/send?access_token=2f796082d567b9964391fb7974b6426879b320514e2d99ea753f8c16426a1c80",
            "SEC89d1250cfce81b8ba7b9daba5bcb20447ddd7290bd03313849ab5fb4b8b25f96");



    @GetMapping("/test")
    public void test() {

//        DingTalkSendingUtilsV1.sendTextV1_1(jwq, "haha");

        DingTalkSendingUtilsV1.sendV1_1(jwq,
                "markdown",
                "Vtec",
                "#### Vtec is the best!  \n  " +
                        "> 拉到高转，你会听见冠军的声音  \n  " +
                        "> ![screenshot](https://bkimg.cdn.bcebos.com/pic/b8389b504fc2d562d2d1cae3e71190ef77c66ce6?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto)  \n  " +
                        "> ######[Honda Vtec技术](https://baike.baidu.com/item/VTEC%E7%B3%BB%E7%BB%9F/428016?fromtitle=VTEC&fromid=578440&fr=aladdin)  \n  ",
                true);

//        System.out.println(1/0);

//        DingTalkSendingUtils.sendLink("Vtec", "Vtec is the best!",
//                "https://baike.baidu.com/item/VTEC%E7%B3%BB%E7%BB%9F/428016?fromtitle=VTEC&fromid=578440&fr=aladdin"
//                );
//

//        DingTalkSendingUtils.sendMarkdown(
//                "Vtec",
//                "#### Vtec is the best!  \n  " +
//                        "> 拉到高转，你会听见冠军的声音  \n  " +
//                        "> ![screenshot](https://bkimg.cdn.bcebos.com/pic/b8389b504fc2d562d2d1cae3e71190ef77c66ce6?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto)  \n  " +
//                        "> ######[Honda Vtec技术](https://baike.baidu.com/item/VTEC%E7%B3%BB%E7%BB%9F/428016?fromtitle=VTEC&fromid=578440&fr=aladdin)  \n  ",
//                true
//                );

//        DingTalkSendingUtils.sendText("hh", Arrays.asList("18134515390"));

//        DingTalkSendingUtils.sendText("haha");





    }
}
