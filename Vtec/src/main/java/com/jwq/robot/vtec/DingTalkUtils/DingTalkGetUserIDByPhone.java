package com.jwq.robot.vtec.DingTalkUtils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiV2UserGetbymobileRequest;
import com.dingtalk.api.response.OapiV2UserGetbymobileResponse;
import com.taobao.api.ApiException;

public class DingTalkGetUserIDByPhone {

    public static String getUserID() throws ApiException{

        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/getbymobile");
        OapiV2UserGetbymobileRequest request = new OapiV2UserGetbymobileRequest();
        request.setMobile("18134515390");
        request.setHttpMethod("POST");
        OapiV2UserGetbymobileResponse response = client.execute(request, DingTalkGetTokenUtil.getAccessToken());
        return response.getBody();
    }

}
