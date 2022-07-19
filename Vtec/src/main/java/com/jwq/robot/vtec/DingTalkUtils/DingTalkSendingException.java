package com.jwq.robot.vtec.DingTalkUtils;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.jwq.robot.vtec.config.DingTalkConfig;
import com.jwq.robot.vtec.param.DingTalkMsg;
import com.taobao.api.ApiException;

public class DingTalkSendingException {


    public static void dingTalkSendingExceptionByMarkdown(Exception e) throws ApiException {
        StackTraceElement stackTrace = e.getStackTrace()[0];
        String s = "**异常信息：**" + e.getMessage() +
                "  \n  " + "  **所在文件：**  " + stackTrace.getFileName() +
                "  \n  " + "  **报错行号：**  " + stackTrace.getLineNumber() +
                "  \n  " + "  **相关方法：**  " + stackTrace.getMethodName() +
                "  \n  " + "  **异常class：**  " + stackTrace.getClass() +
                "  \n  " + "  **异常className：**  " + stackTrace.getClassName()
                ;
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(DingTalkMsg.getMsgTypeMarkdown());
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("异常告警通知！");
        markdown.setText(s);
        request.setMarkdown(markdown);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setIsAtAll(true);
        request.setAt(at);

        OapiRobotSendResponse response = new OapiRobotSendResponse();
        try{
            response = DingTalkConfig.client().execute(request);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        System.out.println("钉钉机器人异常通知返回信息："+ JSON.toJSONString(response));
    }
}
