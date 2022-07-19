package com.jwq.robot.vtec.DingTalkUtils;

import com.alibaba.fastjson.JSON;
import com.aliyun.credentials.utils.StringUtils;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.jwq.robot.vtec.config.DingTalkConfig;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.jwq.robot.vtec.param.DingTalkMsg.*;

/**
 * Vtec机器人发送工具类
 */

@Slf4j
public class DingTalkSendingUtils {


    /**
     * 发送普通文本消息
     *
     * @param content    文本消息
     * @return OapiRobotSendResponse
     */

    public static OapiRobotSendResponse sendText(String content) {
        return sendMessageByTextAtNull(content);
    }

    public static OapiRobotSendResponse sendText(String content, boolean isAtAll) {
        return sendMessageByTextAtAll(content, isAtAll);
    }

    public static OapiRobotSendResponse sendText(String content, List<String> mobileList) {
        return sendMessageByTextAtSome(mobileList,content);
    }


    private static OapiRobotSendResponse sendMessageByTextAtNull(String content) {
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
            response = DingTalkConfig.client().execute(request);
            System.out.println("【DingTalkSendingUtils】发送普通文本消息 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            log.error("[发送普通文本消息]: 发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }

    private static OapiRobotSendResponse sendMessageByTextAtAll(String content,boolean isAtAll) {
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
            response = DingTalkConfig.client().execute(request);
            System.out.println("【DingTalkSendingUtils】发送普通文本消息 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            log.error("[发送普通文本消息]: 发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }
    private static OapiRobotSendResponse sendMessageByTextAtSome(List<String> mobileList, String content) {
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
            response = DingTalkConfig.client().execute(request);
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

    public static OapiRobotSendResponse sendLink(String title, String text, String messageUrl, String picUrl){
            return sendMessageByLinkWithPic(title,text,messageUrl,picUrl);
    }

    public static OapiRobotSendResponse sendLink(String title, String text, String messageUrl){
        return sendMessageByLinkWithoutPic(title,text,messageUrl);
    }

    private static OapiRobotSendResponse sendMessageByLinkWithPic(String title, String text, String messageUrl, String picUrl) {
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
            response = DingTalkConfig.client().execute(request);
            System.out.println("【DingTalkSendingUtils】发送link 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            log.error("[发送link 类型消息]: 发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }

    private static OapiRobotSendResponse sendMessageByLinkWithoutPic(String title, String text, String messageUrl) {
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
            response = DingTalkConfig.client().execute(request);
            System.out.println("【DingTalkSendingUtils】发送link 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            log.error("[发送link 类型消息]: 发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }

    /**
     * 发送Markdown 编辑格式的消息
     * <p>
     *
     *
     * @param markdownText 支持markdown 编辑格式的文本信息
     * @return OapiRobotSendResponse
     */

    public static OapiRobotSendResponse sendMarkdown(String title, String markdownText){
                    return sendMessageByMarkdownAtNull(title, markdownText);

    }

    public static OapiRobotSendResponse sendMarkdown(String title, String markdownText, boolean isAtAll){
                    return sendMessageByMarkdownAtAll(title, markdownText, isAtAll);
    }

    public static OapiRobotSendResponse sendMarkdown(String title, String markdownText, List<String> mobileList){
            return sendMessageByMarkdownAtSome(title, markdownText, mobileList);
    }

    private static OapiRobotSendResponse sendMessageByMarkdownAtNull(String title, String markdownText) {
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
            response = DingTalkConfig.client().execute(request);
            System.out.println("【DingTalkSendingUtils】发送link 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            log.error("[发送link 类型消息]: 发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }

    private static OapiRobotSendResponse sendMessageByMarkdownAtAll(String title, String markdownText, boolean isAtAll) {
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
            response = DingTalkConfig.client().execute(request);
            System.out.println("【DingTalkSendingUtils】发送link 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            log.error("[发送link 类型消息]: 发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }
    private static OapiRobotSendResponse sendMessageByMarkdownAtSome(String title, String markdownText, List<String> mobileList) {
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
            response = DingTalkConfig.client().execute(request);
            System.out.println("【DingTalkSendingUtils】发送link 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            log.error("[发送link 类型消息]: 发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }



//    private static OapiRobotSendResponse sendMessageByMarkdown(DingTalkClient client,String title, String markdownText, List<String> mobileList, boolean isAtAll) {
//        //参数	    类型	    必选	 说明
//        //msgtype	String	是	 此消息类型为固定markdown
//        //title	    String	是	 首屏会话透出的展示内容
//        //text      String	是	 markdown格式的消息
//        //atMobiles	Array	否	 被@人的手机号(在text内容里要有@手机号)
//        //isAtAll	bool	否	 @所有人时：true，否则为：false
//        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
//        markdown.setTitle(title);
//        markdown.setText(markdownText);
//        OapiRobotSendRequest request = new OapiRobotSendRequest();
//        request.setMsgtype(getMsgTypeMarkdown());
//        request.setMarkdown(markdown);
//        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
//        if (!CollectionUtils.isEmpty(mobileList)) {
//            // 发送消息并@以下手机号联系人
//            at.setAtMobiles(mobileList);
//        } else {
//            // 发送消息并@全体
//            at.setIsAtAll(isAtAll);
//        }
//        request.setAt(at);
//
//        OapiRobotSendResponse response = new OapiRobotSendResponse();
//        try {
//            response = client.execute(request);
//            System.out.println("【DingTalkSendingUtils】发送link 响应参数：" + JSON.toJSONString(response));
//        } catch (ApiException e) {
//            log.error("[发送link 类型消息]: 发送消息失败, 异常捕获{}", e.getMessage());
//        }
//        return response;
//    }

    /**
     * 整体跳转ActionCard类型的消息发送
     *
     * @param title          消息标题, 会话消息会展示标题
     * @param markdownText   markdown格式的消息
     * @param singleTitle    单个按钮的标题
     * @param singleURL      单个按钮的跳转链接
     * @param btnOrientation 是否横向排列(true 横向排列, false 纵向排列)
     * @param hideAvatar     是否隐藏发消息者头像(true 隐藏头像, false 不隐藏)
     * @return OapiRobotSendResponse
     */
    /*private static OapiRobotSendResponse sendMessageByActionCardSingle(String title, String markdownText, String singleTitle, String singleURL, boolean btnOrientation, boolean hideAvatar) {
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(markdownText)) {
            return null;
        }
        //参数	           类型	    必选	    说明
        //msgtype	       string	true	此消息类型为固定actionCard
        //title	           string	true	首屏会话透出的展示内容
        //text	           string	true	markdown格式的消息
        //singleTitle	   string	true	单个按钮的方案。(设置此项和singleURL后btns无效)
        //singleURL	       string	true	点击singleTitle按钮触发的URL
        //btnOrientation   string	false	0-按钮竖直排列，1-按钮横向排列
        //hideAvatar	   string	false	0-正常发消息者头像，1-隐藏发消息者头像
        OapiRobotSendRequest.Actioncard actionCard = new OapiRobotSendRequest.Actioncard();
        actionCard.setTitle(title);
        actionCard.setText(markdownText);
        actionCard.setSingleTitle(singleTitle);
        actionCard.setSingleURL(singleURL);
        // 此处默认为0
        actionCard.setBtnOrientation(btnOrientation ? "1" : "0");
        // 此处默认为0
        actionCard.setHideAvatar(hideAvatar ? "1" : "0");

        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(getMsgTypeActionCard());
        request.setActionCard(actionCard);
        OapiRobotSendResponse response = new OapiRobotSendResponse();
        try {
            response = getClient().execute(request);
            System.out.println("【DingTalkSendingUtils】整体跳转ActionCard类型的发送消息 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            log.error("[发送ActionCard 类型消息]: 整体跳转ActionCard类型的发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }*/

    /**
     * 独立跳转ActionCard类型 消息发送
     *
     * @param title          标题
     * @param markdownText   文本
     * @param btns           按钮列表
     * @param btnOrientation 是否横向排列(true 横向排列, false 纵向排列)
     * @param hideAvatar     是否隐藏发消息者头像(true 隐藏头像, false 不隐藏)
     * @return OapiRobotSendResponse
     */
    private static OapiRobotSendResponse sendMessageByActionCardMulti(DingTalkClient client,String title, String markdownText, List<OapiRobotSendRequest.Btns> btns, boolean btnOrientation, boolean hideAvatar) {
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(markdownText) || CollectionUtils.isEmpty(btns)) {
            return null;
        }
        //参数	            类型	    必选	    说明
        //msgtype	        string	true	此消息类型为固定actionCard
        //title	            string	true	首屏会话透出的展示内容
        //text	            string	true	markdown格式的消息
        //btns	            array	true	按钮的信息：title-按钮方案，actionURL-点击按钮触发的URL
        //btnOrientation	string	false	0-按钮竖直排列，1-按钮横向排列
        //hideAvatar	    string	false	0-正常发消息者头像，1-隐藏发消息者头像
        OapiRobotSendRequest.Actioncard actionCard = new OapiRobotSendRequest.Actioncard();
        actionCard.setTitle(title);
        actionCard.setText(markdownText);
        // 此处默认为0
        actionCard.setBtnOrientation(btnOrientation ? "1" : "0");
        // 此处默认为0
        actionCard.setHideAvatar(hideAvatar ? "1" : "0");

        actionCard.setBtns(btns);

        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(getMsgTypeActionCard());
        request.setActionCard(actionCard);
        OapiRobotSendResponse response = new OapiRobotSendResponse();
        try {
            response = client.execute(request);
            System.out.println("【DingTalkSendingUtils】独立跳转ActionCard类型发送消息 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            log.error("[发送ActionCard 类型消息]: 独立跳转ActionCard类型发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }

    /**
     * 发送FeedCard类型消息
     *
     * @param links 链接
     * @return OapiRobotSendResponse
     */
    private static OapiRobotSendResponse sendMessageByFeedCard(DingTalkClient client,List<OapiRobotSendRequest.Links> links) {
        if (CollectionUtils.isEmpty(links)) {
            return null;
        }
        //参数	        类型	    必选	    说明
        //msgtype	    string	true    此消息类型为固定feedCard
        //title	        string	true    单条信息文本
        //messageURL	string	true    点击单条信息到跳转链接
        //picURL	    string	true	单条信息后面图片的URL
        OapiRobotSendRequest.Feedcard feedcard = new OapiRobotSendRequest.Feedcard();
        feedcard.setLinks(links);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(getMsgTypeFeedCard());
        request.setFeedCard(feedcard);
        OapiRobotSendResponse response = new OapiRobotSendResponse();
        try {
            response = client.execute(request);
            System.out.println("【DingTalkSendingUtils】独立跳转ActionCard类型发送消息 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            log.error("[发送ActionCard 类型消息]: 独立跳转ActionCard类型发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }
        /*发送普通文本消息
        ArrayList<String> userList = ListUtil.toList("18134515390");
        sendMessageByText("收到请回复", userList, false);
        */

        /*发送link 类型消息
        String title = "Vtec is the best!";
        String text = "千禧年的自然吸气艺术";
        String messageUrl = "https://baike.baidu.com/item/VTEC%E7%B3%BB%E7%BB%9F/428016?fromtitle=VTEC&fromid=578440&fr=aladdin";
        String picUrl = "https://bkimg.cdn.bcebos.com/pic/b8389b504fc2d562d2d1cae3e71190ef77c66ce6?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto";
        sendMessageByLink(title, text, messageUrl, picUrl);
        */

        /*发送Markdown 编辑格式的消息
        String title = "Vtec";
        String markdownText = "#### Vtec is the best! @18134515390\n" +
                "> 拉到高转，你会听见冠军的声音\n\n" +
                "> ![screenshot](https://bkimg.cdn.bcebos.com/pic/b8389b504fc2d562d2d1cae3e71190ef77c66ce6?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto)\n" +
                "> ######[Honda Vtec技术](https://baike.baidu.com/item/VTEC%E7%B3%BB%E7%BB%9F/428016?fromtitle=VTEC&fromid=578440&fr=aladdin) \n";
        List<String> mobileList = ListUtil.toList("18134515390");
        boolean isAtAll = false;
        sendMessageByMarkdown(markdownText, mobileList, isAtAll);
        */

        /*整体跳转ActionCard类型的消息发送
        String title = "Vtec";
        String markdownText = "#### Vtec is the best! @18134515390\n" +
                "> 高角度凸轮轴高转介入工作\n\n" +
                "> ![screenshot](https://bkimg.cdn.bcebos.com/pic/b8389b504fc2d562d2d1cae3e71190ef77c66ce6?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto)\n" +
                "> ###### Honda Vtec技术 [百科](https://baike.baidu.com/item/VTEC%E7%B3%BB%E7%BB%9F/428016?fromtitle=VTEC&fromid=578440&fr=aladdin) \n";
        String singleTitle = "百度百科";
        String singleURL = "https://baike.baidu.com/item/VTEC%E7%B3%BB%E7%BB%9F/428016?fromtitle=VTEC&fromid=578440&fr=aladdin";
        boolean btnOrientation = true;
        boolean hideAvatar = false;
        sendMessageByActionCardSingle(title, markdownText, singleTitle, singleURL, btnOrientation, hideAvatar);
        */

        /*独立跳转ActionCard类型的消息发送
        String title = "Vtec";
        String markdownText = "#### Vtec is the best! @18134515390\n" +
                "> JDM不死\n\n" +
                "> ![screenshot](https://bkimg.cdn.bcebos.com/pic/b8389b504fc2d562d2d1cae3e71190ef77c66ce6?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto)\n" +
                "> ###### Honda Vtec技术 [百科](https://baike.baidu.com/item/VTEC%E7%B3%BB%E7%BB%9F/428016?fromtitle=VTEC&fromid=578440&fr=aladdin) \n";

        OapiRobotSendRequest.Btns btn = new OapiRobotSendRequest.Btns();
        btn.setTitle("Honda官网");//此为按钮
        btn.setActionURL("https://www.honda.com.cn/honda/cartechnology/more/20T.html");
        List<OapiRobotSendRequest.Btns> btns = ListUtil.toList(btn);
        boolean btnOrientation = true;
        boolean hideAvatar = false;
        sendMessageByActionCardMulti(title, markdownText, btns, btnOrientation, hideAvatar);
        */

        /*发送FeedCard类型消息
        OapiRobotSendRequest.Links link = new OapiRobotSendRequest.Links();
        link.setTitle("Vtec is the best!");
        link.setMessageURL("https://baike.baidu.com/item/VTEC%E7%B3%BB%E7%BB%9F/428016?fromtitle=VTEC&fromid=578440&fr=aladdin");
        link.setPicURL("https://bkimg.cdn.bcebos.com/pic/b8389b504fc2d562d2d1cae3e71190ef77c66ce6?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
        List<OapiRobotSendRequest.Links> links = ListUtil.toList(link);
        sendMessageByFeedCard(links);
         */

}
