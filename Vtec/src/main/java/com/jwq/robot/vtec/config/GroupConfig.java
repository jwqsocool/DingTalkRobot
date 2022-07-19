package com.jwq.robot.vtec.config;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GroupConfig {

    private String WEBHOOK;
    private String SECRET;

    public String getWEBHOOK() {
        return WEBHOOK;
    }


    public void setWEBHOOK(String webhook) {
        WEBHOOK = webhook;
    }

    public String getSecret() {
        return SECRET;
    }


    public void setSecret(String secret) {
        SECRET = secret;
    }


    @Override
    public String toString() {
        try {
            return WEBHOOK + signV1(SECRET);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GroupConfig(String webhook, String secret) {
        super();
        WEBHOOK = webhook;
        SECRET = secret;
    }

    public static String signV1(String secret)throws Exception{
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), StandardCharsets.UTF_8);
        return "&timestamp=" + timestamp + "&sign=" + sign;
    }
}
