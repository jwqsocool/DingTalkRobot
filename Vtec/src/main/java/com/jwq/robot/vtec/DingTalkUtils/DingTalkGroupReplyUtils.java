package com.jwq.robot.vtec.DingTalkUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.jwq.robot.vtec.param.DingTalkMsg;
import com.taobao.api.ApiException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RequestMapping
@RestController
public class DingTalkGroupReplyUtils {
    private static final String TEXT = "text@个人效果";
    private static final String TEXT_ALL = "text@全体效果";
    private static final String MARKDOWN = "markdown@个人效果";
    private static final String MARKDOWN_ALL = "markdown@全体效果";
    private static final String ACTION_CARD = "actioncard@个人效果";
    private static final String ACTION_CARD_ALL = "actioncard@全体效果";

    @RequestMapping(value = "/robot", method = RequestMethod.POST)
    public String GroupReplyRobot(@RequestBody(required = false) JSONObject json) {
        System.out.println(JSON.toJSONString(json));
        String content = json.getJSONObject("text").get("content").toString().replaceAll(" ","");
        System.out.println(content);
        //获取用户userId
        String userId = json.getString("senderStaffId");
        String sessionWebhook = json.getString("sessionWebhook");
        DingTalkClient client = new DefaultDingTalkClient(sessionWebhook);
        switch (content) {
            case TEXT -> Text(client, userId, false);
            case MARKDOWN -> Markdown(client, userId, false);
            case ACTION_CARD -> Actioncard(client, userId, false);
            case TEXT_ALL -> Text(client, userId, true);
            case MARKDOWN_ALL -> Markdown(client, userId, true);
            case ACTION_CARD_ALL -> Actioncard(client, userId, true);
        }
        return null;
    }

    /**
     * 实现@
     * @param client
     * @param userId
     */

    private void Text(DingTalkClient client, String userId, boolean isAtAll) {
        try {
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype(DingTalkMsg.getMsgTypeText());
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            if (!isAtAll) {
                text.setContent("@" + userId + "  \n  " + "Text测试@个人");
            }else {
                text.setContent("Text测试@全体" + "  \n  " );
            }
            request.setText(text);
            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
            at.setAtUserIds(Arrays.asList(userId));
            //isAtAll类型如果不为Boolean，请升级至最新SDK
            at.setIsAtAll(isAtAll);
            request.setAt(at);
            OapiRobotSendResponse response = client.execute(request);
            System.out.println(response.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void Markdown(DingTalkClient client, String userId, boolean isAtAll) {
        try {
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype(DingTalkMsg.getMsgTypeMarkdown());
            OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
            if (!isAtAll) {
                markdown.setTitle("Markdown@个人效果");
                markdown.setText("@" + userId + "  \n  " +
                        "**Markdown@个人效果**  \n  " +
                        "*Markdown@个人效果*");
            }else {
                markdown.setTitle("Markdown@全体效果");
                markdown.setText("@" + "全体\n" +
                        "**Markdown@全体效果**  \n  " +
                        "*Markdown@全体效果* \n ");
            }
            request.setMarkdown(markdown);
            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
            at.setAtUserIds(Arrays.asList(userId));
            //isAtAll类型如果不为Boolean，请升级至最新SDK
            at.setIsAtAll(isAtAll);
            request.setAt(at);
            OapiRobotSendResponse response = client.execute(request);
            System.out.println(response.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void Actioncard(DingTalkClient client, String userId, boolean isAtAll) {
        try {
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype(DingTalkMsg.getMsgTypeActionCard());
            OapiRobotSendRequest.Actioncard actionCard = new OapiRobotSendRequest.Actioncard();
            if(!isAtAll){
                actionCard.setTitle("actionCard@个人");
                actionCard.setText("@" + userId + "  \n  " +
                        "*Vtec*" + "  \n  " +
                        "![这是一张图片](https://bkimg.cdn.bcebos.com/pic/b8389b504fc2d562d2d1cae3e71190ef77c66ce6?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto)  \n  " +
                        "@个人");
            }else {
                actionCard.setTitle("actionCard@全体");
                actionCard.setText("@" + "全体\n" +
                        "*Vtec*" + "  \n  " +
                        "![这是一张图片](https://bkimg.cdn.bcebos.com/pic/b8389b504fc2d562d2d1cae3e71190ef77c66ce6?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto)  \n  " +
                        "@全体");
            }
            actionCard.setSingleTitle("阅读全文");
            actionCard.setSingleURL("https://baike.baidu.com/item/VTEC%E7%B3%BB%E7%BB%9F/428016?fromtitle=VTEC&fromid=578440&fr=aladdin");
            request.setActionCard(actionCard);
            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
            at.setAtUserIds(Arrays.asList(userId));
            //isAtAll类型如果不为Boolean，请升级至最新SDK
            at.setIsAtAll(isAtAll);
            request.setAt(at);
            OapiRobotSendResponse response = client.execute(request);
            System.out.println(response.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


}
