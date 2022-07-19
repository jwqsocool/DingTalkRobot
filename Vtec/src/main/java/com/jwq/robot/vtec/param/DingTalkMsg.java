package com.jwq.robot.vtec.param;

import java.util.List;

public class DingTalkMsg {



    /**
     * 钉钉机器人可以发送的消息类型(注意：不要修改！)
     * 官方文档网址 <https://open.dingtalk.com/document/group/message-types-and-data-format>
     */
    private static final String MSG_TYPE_TEXT = "text";
    private static final String MSG_TYPE_LINK = "link";
    private static final String MSG_TYPE_MARKDOWN = "markdown";
    private static final String MSG_TYPE_ACTION_CARD = "actionCard";
    private static final String MSG_TYPE_FEED_CARD = "feedCard";

    private String msgtype;

    private TextMsg text;

    private LinkMsg link;

    private MarkdownMsg markdown;

    private ActioncardMsg actioncard;

    //采用静态内部类的形式组织嵌套实体类
    public static class TextMsg{
        private String content;
        private List<String> mobileList;
        private boolean isAtAll;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getMobileList() {
            return mobileList;
        }

        public void setMobileList(List<String> mobileList) {
            this.mobileList = mobileList;
        }

        public boolean isAtAll() {
            return isAtAll;
        }

        public void setAtAll(boolean isAtAll) {
            this.isAtAll = isAtAll;
        }
    }

    public static class LinkMsg{
        private String title;
        private String text;
        private String messageUrl;
        private String picUrl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getMessageUrl() {
            return messageUrl;
        }

        public void setMessageUrl(String messageUrl) {
            this.messageUrl = messageUrl;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }
    }

    public static class MarkdownMsg{
        private String title;
        private String markdownText;
        private List<String> mobileList;
        private boolean isAtAll;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMarkdownText() {
            return markdownText;
        }

        public void setMarkdownText(String markdownText) {
            this.markdownText = markdownText;
        }

        public List<String> getMobileList() {
            return mobileList;
        }

        public void setMobileList(List<String> mobileList) {
            this.mobileList = mobileList;
        }

        public boolean isAtAll() {
            return isAtAll;
        }

        public void setAtAll(boolean isAtAll) {
            this.isAtAll = isAtAll;
        }
    }

    public static class ActioncardMsg{
        private String title;
        private String actioncardText;
        private List<Btns> btns ;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getActioncardText() {
            return actioncardText;
        }

        public void setActioncardText(String actioncardText) {
            this.actioncardText = actioncardText;
        }

        public List<Btns> getBtns() {
            return btns;
        }

        public void setBtns(List<Btns> btns) {
            this.btns = btns;
        }

        private final boolean btnOrientation = true;

        private final boolean hideAvatar = false;
    }

     public static class Btns {
        private String title;
        private String actionURL;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getActionURL() {
            return actionURL;
        }

        public void setActionURL(String actionURL) {
            this.actionURL = actionURL;
        }
    }

    /**
     *Getter
     *消息类型不建议修改所以无setter
     *
     */
    public static String getMsgTypeText() {
        return MSG_TYPE_TEXT;
    }

    public static String getMsgTypeLink() {
        return MSG_TYPE_LINK;
    }

    public static String getMsgTypeMarkdown() {
        return MSG_TYPE_MARKDOWN;
    }

    public static String getMsgTypeActionCard() {
        return MSG_TYPE_ACTION_CARD;
    }

    public static String getMsgTypeFeedCard() {
        return MSG_TYPE_FEED_CARD;
    }


}
