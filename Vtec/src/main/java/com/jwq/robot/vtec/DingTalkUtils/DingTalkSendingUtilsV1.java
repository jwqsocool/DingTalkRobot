package com.jwq.robot.vtec.DingTalkUtils;

import com.alibaba.fastjson.JSON;
import com.aliyun.credentials.utils.StringUtils;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.jwq.robot.vtec.config.GroupConfig;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.jwq.robot.vtec.param.DingTalkMsg.*;


@Slf4j
public class DingTalkSendingUtilsV1 {

    /**
     * 发送普通文本消息
     *
     * @param content    文本消息
     * @return OapiRobotSendResponse
     */

    public static OapiRobotSendResponse sendTextV1_1(GroupConfig group, String content){
        try {
            DingTalkClient client = new DefaultDingTalkClient(group.toString());
            return sendMessageByTextAtNullV1(client,content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static OapiRobotSendResponse sendV1_1(GroupConfig group, String msgtype, String content, boolean isAtAll) {
        if(msgtype == getMsgTypeText()){
        try {
            DingTalkClient client = new DefaultDingTalkClient(group.toString());
            return sendMessageByTextAtAllV1(client, content, isAtAll);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        }else {
            System.out.println("消息类型不对应，请检查");
            return null;
        }
    }

    public static OapiRobotSendResponse sendTextV1_1(GroupConfig group, String content, List<String> mobileList) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(group.toString());
            return sendMessageByTextAtSomeV1(client, mobileList, content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static OapiRobotSendResponse sendMessageByTextAtNullV1(DingTalkClient client, String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        //参数	    参数类型	  必须	说明
        //msgtype	String	  是	消息类型，此时固定为：text
        //content   String	  是	消息内容
        //atMobiles	Array	  否	被@人的手机号(在content里添加@人的手机号)
        //isAtAll	boolean	  否	@所有人时：true，否则为：false
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(content);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(getMsgTypeText());
        request.setText(text);
        OapiRobotSendResponse response = new OapiRobotSendResponse();
        try {
            response = client.execute(request);
            System.out.println("【DingTalkSendingUtils】发送普通文本消息 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            log.error("[发送普通文本消息]: 发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }

    private static OapiRobotSendResponse sendMessageByTextAtAllV1(DingTalkClient client,String content,boolean isAtAll) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        //参数	    参数类型	  必须	说明
        //msgtype	String	  是	消息类型，此时固定为：text
        //content   String	  是	消息内容
        //atMobiles	Array	  否	被@人的手机号(在content里添加@人的手机号)
        //isAtAll	boolean	  否	@所有人时：true，否则为：false
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(content);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        request.setMsgtype(getMsgTypeText());
        request.setText(text);
        at.setIsAtAll(isAtAll);
        request.setAt(at);
        OapiRobotSendResponse response = new OapiRobotSendResponse();
        try {
            response = client.execute(request);
            System.out.println("【DingTalkSendingUtils】发送普通文本消息 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            log.error("[发送普通文本消息]: 发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }
    private static OapiRobotSendResponse sendMessageByTextAtSomeV1(DingTalkClient client,List<String> mobileList, String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        //参数	    参数类型	  必须	说明
        //msgtype	String	  是	消息类型，此时固定为：text
        //content   String	  是	消息内容
        //atMobiles	Array	  否	被@人的手机号(在content里添加@人的手机号)
        //isAtAll	boolean	  否	@所有人时：true，否则为：false
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(content);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        request.setMsgtype(getMsgTypeText());
        request.setText(text);
        at.setAtMobiles(mobileList);
        at.setIsAtAll(false);
        request.setAt(at);
        OapiRobotSendResponse response = new OapiRobotSendResponse();
        try {
            response = client.execute(request);
            System.out.println("【DingTalkSendingUtils】发送普通文本消息 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            log.error("[发送普通文本消息]: 发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }

    /**
     * 发送link 类型消息
     *
     * @param title      消息标题
     * @param text       消息内容
     * @param messageUrl 点击消息后跳转的url
     * @param picUrl     插入图片的url
     * @return OapiRobotSendResponse
     */

    public static OapiRobotSendResponse sendLinkV1_1(GroupConfig group, String title, String text, String messageUrl, String picUrl){
        try {
            DingTalkClient client = new DefaultDingTalkClient(group.toString());
            return sendMessageByLinkWithPicV1(client,title,text,messageUrl,picUrl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static OapiRobotSendResponse sendLinkV1_1(GroupConfig group, String title, String text, String messageUrl){
        try {
            DingTalkClient client = new DefaultDingTalkClient(group.toString());
            return sendMessageByLinkWithoutPicV1(client,title,text,messageUrl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static OapiRobotSendResponse sendMessageByLinkWithPicV1(DingTalkClient client, String title, String text, String messageUrl, String picUrl) {
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(text) || StringUtils.isEmpty(messageUrl)) {
            return null;
        }
        //参数	        参数类型	必须	  说明
        //msgtype	    String	是	  消息类型，此时固定为：link
        //title	        String	是	  消息标题
        //text	        String	是	  消息内容。如果太长只会部分展示
        //messageUrl	String	是	  点击消息跳转的URL
        //picUrl	    String	否	  图片URL
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setTitle(title);
        link.setText(text);
        link.setMessageUrl(messageUrl);
        link.setPicUrl(picUrl);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(getMsgTypeLink());
        request.setLink(link);

        OapiRobotSendResponse response = new OapiRobotSendResponse();
        try {
            response = client.execute(request);
            System.out.println("【DingTalkSendingUtils】发送link 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            log.error("[发送link 类型消息]: 发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }

    private static OapiRobotSendResponse sendMessageByLinkWithoutPicV1(DingTalkClient client, String title, String text, String messageUrl) {
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(text) || StringUtils.isEmpty(messageUrl)) {
            return null;
        }
        //参数	        参数类型	必须	  说明
        //msgtype	    String	是	  消息类型，此时固定为：link
        //title	        String	是	  消息标题
        //text	        String	是	  消息内容。如果太长只会部分展示
        //messageUrl	String	是	  点击消息跳转的URL
        //picUrl	    String	否	  图片URL
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setTitle(title);
        link.setText(text);
        link.setMessageUrl(messageUrl);
        link.setPicUrl(null);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(getMsgTypeLink());
        request.setLink(link);

        OapiRobotSendResponse response = new OapiRobotSendResponse();
        try {
            response = client.execute(request);
            System.out.println("【DingTalkSendingUtils】发送link 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            log.error("[发送link 类型消息]: 发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }

    /**
     * 发送Markdown 编辑格式的消息
     * <p>
     * @param title        消息标题
     *
     * @param markdownText 支持markdown 编辑格式的文本信息
     * @return OapiRobotSendResponse
     */

    public static OapiRobotSendResponse sendMarkdownV1_1(GroupConfig group, String title, String markdownText){
        try {
            DingTalkClient client = new DefaultDingTalkClient(group.toString());
            return sendMessageByMarkdownAtNullV1(client,title, markdownText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static OapiRobotSendResponse sendV1_1(GroupConfig group, String msgtype, String title, String markdownText, boolean isAtAll){
        if(msgtype == getMsgTypeMarkdown()){
        try {
            DingTalkClient client = new DefaultDingTalkClient(group.toString());
            return sendMessageByMarkdownAtAllV1(client,title,markdownText,isAtAll);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        }else{
            System.out.println("消息类型不对应，请检查");
            return null;
        }
    }

    public static OapiRobotSendResponse sendMarkdownV1_1(GroupConfig group, String title, String markdownText, List<String> mobileList){
        try {
            DingTalkClient client = new DefaultDingTalkClient(group.toString());
            return sendMessageByMarkdownAtByPhoneV1(client,title,markdownText,mobileList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static OapiRobotSendResponse sendMessageByMarkdownAtNullV1(DingTalkClient client ,String title, String markdownText) {
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(markdownText)) {
            return null;
        }
        //参数	    类型	    必选	 说明
        //msgtype	String	是	 此消息类型为固定markdown
        //title	    String	是	 首屏会话透出的展示内容
        //text      String	是	 markdown格式的消息
        //atMobiles	Array	否	 被@人的手机号(在text内容里要有@手机号)
        //isAtAll	bool	否	 @所有人时：true，否则为：false
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle(title);
        markdown.setText(markdownText);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(getMsgTypeMarkdown());
        request.setMarkdown(markdown);
        OapiRobotSendResponse response = new OapiRobotSendResponse();
        try {
            response = client.execute(request);
            System.out.println("【DingTalkSendingUtils】发送link 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            log.error("[发送link 类型消息]: 发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }

    private static OapiRobotSendResponse sendMessageByMarkdownAtAllV1(DingTalkClient client, String title, String markdownText, boolean isAtAll) {
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(markdownText)) {
            return null;
        }
        //参数	    类型	    必选	 说明
        //msgtype	String	是	 此消息类型为固定markdown
        //title	    String	是	 首屏会话透出的展示内容
        //text      String	是	 markdown格式的消息
        //atMobiles	Array	否	 被@人的手机号(在text内容里要有@手机号)
        //isAtAll	bool	否	 @所有人时：true，否则为：false
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle(title);
        markdown.setText(markdownText);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(getMsgTypeMarkdown());
        request.setMarkdown(markdown);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setIsAtAll(isAtAll);
        request.setAt(at);
        OapiRobotSendResponse response = new OapiRobotSendResponse();
        try {
            response = client.execute(request);
            System.out.println("【DingTalkSendingUtils】发送link 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            log.error("[发送link 类型消息]: 发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }
    private static OapiRobotSendResponse sendMessageByMarkdownAtByPhoneV1(DingTalkClient client, String title, String markdownText, List<String> mobileList) {
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(markdownText)) {
            return null;
        }
        //参数	    类型	    必选	 说明
        //msgtype	String	是	 此消息类型为固定markdown
        //title	    String	是	 首屏会话透出的展示内容
        //text      String	是	 markdown格式的消息
        //atMobiles	Array	否	 被@人的手机号(在text内容里要有@手机号)
        //isAtAll	bool	否	 @所有人时：true，否则为：false
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle(title);
        markdown.setText(markdownText);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(getMsgTypeMarkdown());
        request.setMarkdown(markdown);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        // 发送消息并@以下手机号联系人
        at.setAtMobiles(mobileList);
        // 发送消息并@全体
        at.setIsAtAll(false);
        request.setAt(at);
        OapiRobotSendResponse response = new OapiRobotSendResponse();
        try {
            response = client.execute(request);
            System.out.println("【DingTalkSendingUtils】发送link 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            log.error("[发送link 类型消息]: 发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }

    private static OapiRobotSendResponse sendMessageByMarkdownAtByNameV1(DingTalkClient client, String title, String markdownText, List<String> userList) {
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(markdownText)) {
            return null;
        }
        //参数	    类型	    必选	 说明
        //msgtype	String	是	 此消息类型为固定markdown
        //title	    String	是	 首屏会话透出的展示内容
        //text      String	是	 markdown格式的消息
        //atMobiles	Array	否	 被@人的手机号(在text内容里要有@手机号)
        //isAtAll	bool	否	 @所有人时：true，否则为：false
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle(title);
        markdown.setText(markdownText);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(getMsgTypeMarkdown());
        request.setMarkdown(markdown);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        // 发送消息并@以下手机号联系人
        at.setAtUserIds(userList);
        // 发送消息并@全体
        at.setIsAtAll(false);
        request.setAt(at);
        OapiRobotSendResponse response = new OapiRobotSendResponse();
        try {
            response = client.execute(request);
            System.out.println("【DingTalkSendingUtils】发送link 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            log.error("[发送link 类型消息]: 发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }

}
