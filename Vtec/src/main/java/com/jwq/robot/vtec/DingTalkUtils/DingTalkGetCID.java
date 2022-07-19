package com.jwq.robot.vtec.DingTalkUtils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiChatCreateRequest;
import com.dingtalk.api.response.OapiChatCreateResponse;
import com.taobao.api.ApiException;
import java.util.Arrays;

public class DingTalkGetCID {
    public static void main(String[] args) {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/chat/create");
            OapiChatCreateRequest req = new OapiChatCreateRequest();
            req.setName("jwq的内部群");
            req.setOwner("manager1057");
            req.setUseridlist(Arrays.asList("manager1057"));
            OapiChatCreateResponse rsp = client.execute(req, DingTalkGetTokenUtil.getAccessToken());
            System.out.println(rsp.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
