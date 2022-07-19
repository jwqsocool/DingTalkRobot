package com.jwq.robot.vtec.config;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Component
public class DingTalkConfig {

    /**
     * ROBOT_CODE 对应 机器人AppKey
     * ROBOT_SECRET 对应 机器人AppSecret
     * OPEN_CONVERSATION_ID 对应 内部群会话的OpenConversationId
     * WEBHOOK 对应 发送消息群对应的webhook
     * SECRET 对应 机器人测试群中的加签
     */

    private static String ROBOT_CODE; //= "ding51oxpjzyhrzmqh6j";

    private static String ROBOT_SECRET;// = "xpcJaqazYCfm36i85tNrb-iUbAyiAefO9Oc5NLd_62HtG_QyFQB4tyNsmjDeoNmS";

    private static String OPEN_CONVERSATION_ID;// = "cidNzs330482gEyPbf0dZdnYA==";

    private static String WEBHOOK; // = "https://oapi.dingtalk.com/robot/send?access_token=ce293b5d77e4a26834d3faa6e68d58313d1ec75c753c31f50bc2a53342697f2f";

    private static String SECRET; // = "SEC5cf5211ca24054655370489c74d24889320c9725b4c1d96f6d6c89d60870e74a";

    public static String getWebhook() {
        return WEBHOOK;
    }

    @Value("${jwq.webhook}")
    public void setWebhook(String webhook) {
        WEBHOOK = webhook;
    }

    public String getSECRET() {
        return SECRET;
    }

    @Value("${jwq.secret}")
    public void setSECRET(String secret) {
        SECRET = secret;
    }

    public static String getRobotCode() {
        return ROBOT_CODE;
    }

    public static void setRobotCode(String robotCode) {
        ROBOT_CODE = robotCode;
    }
    public static String getRobotSecret() {
        return ROBOT_SECRET;
    }

    public static void setRobotSecret(String robotSecret) {
        ROBOT_SECRET = robotSecret;
    }

    public static String getOpenConversationId() {
        return OPEN_CONVERSATION_ID;
    }

    public static void setOpenConversationId(String openConversationId) {
        OPEN_CONVERSATION_ID = openConversationId;
    }

    /**
     * 获取签名
     * 把timestamp+"\n"+密钥当做签名字符串，使用HmacSHA256算法计算签名，然后进行Base64 encode，最后再把签名参数再进行urlEncode，得到最终的签名（需要使用UTF-8字符集）。
     * timestamp 当前时间戳，单位是毫秒，与请求调用时间误差不能超过1小时。
     * secret 密钥，机器人安全设置页面，加签一栏下面显示的SEC开头的字符串。
     *
     * @return java.lang.String
     */


    public static String sign()throws Exception{
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + SECRET;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), StandardCharsets.UTF_8);
        return "&timestamp=" + timestamp + "&sign=" + sign;
    }

    /**
     *客户端实例
     */
    public static DingTalkClient client(){
        try {
            return new DefaultDingTalkClient(WEBHOOK + sign());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
