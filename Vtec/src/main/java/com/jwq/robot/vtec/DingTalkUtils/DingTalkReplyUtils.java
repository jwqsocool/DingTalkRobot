package com.jwq.robot.vtec.DingTalkUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTOHeaders;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTORequest;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTOResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.jwq.robot.vtec.config.DingTalkConfig.*;


@RequestMapping
@RestController
public class DingTalkReplyUtils {
    /**
     * 使用 Token 初始化账号Client
     *
     * @return Client
     * @throws Exception
     */

    public static com.aliyun.dingtalkrobot_1_0.Client createClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkrobot_1_0.Client(config);
    }



    @RequestMapping(value = "/robot1", method = RequestMethod.POST)
    public String helloRobots(@RequestBody(required = false) JSONObject json) throws Exception {
        System.out.println(JSON.toJSONString(json));
        String content = json.getJSONObject("text").getString("content");
        String userId = json.get("senderStaffId").toString();
        System.out.println(content);
        if (content.equals("发票")) {
            sendMessage1(userId);
        } else if (content.equals("发票要普票还是专票")) {
            sendMessage2(userId);
        } else if (content.equals("发票内容开什么")) {
            sendMessage3(userId);
        } else if (content.equals("差旅住宿普通发票可以报销吗")) {
            sendMessage4(userId);
        }
        return null;
    }



    public void sendMessage1(String userId) throws Exception {
        com.aliyun.dingtalkrobot_1_0.Client client = createClient();
        BatchSendOTOHeaders batchSendOTOHeaders = new BatchSendOTOHeaders();
        batchSendOTOHeaders.xAcsDingtalkAccessToken = DingTalkGetTokenUtil.getAccessToken();
        BatchSendOTORequest batchSendOTORequest = new BatchSendOTORequest()
                .setRobotCode(getRobotCode())
                .setUserIds(java.util.Arrays.asList(userId))
                .setMsgKey("sampleActionCard")
                .setMsgParam("{\n" +
                        "        \"title\": \"发票\",\n" +
                        "        \"text\": \"请问是要咨询发票的什么内容呢\",\n" +
                        "        \"actionTitle1\": \"发票要普票还是专票\",\n" +
                        "        \"actionURL1\": \"dtmd://dingtalkclient/sendMessage?content=%E5%8F%91%E7%A5%A8%E8%A6%81%E6%99%AE%E7%A5%A8%E8%BF%98%E6%98%AF%E4%B8%93%E7%A5%A8\",\n" +
                        "        \"actionTitle2\": \"发票内容开什么\",\n" +
                        "        \"actionURL2\": \"dtmd://dingtalkclient/sendMessage?content=%E5%8F%91%E7%A5%A8%E5%86%85%E5%AE%B9%E5%BC%80%E4%BB%80%E4%B9%88\",\n" +
                        "        \"actionTitle3\": \"差旅住宿普通发票可以报销吗\",\n" +
                        "        \"actionURL3\": \"dtmd://dingtalkclient/sendMessage?content=%E5%B7%AE%E6%97%85%E4%BD%8F%E5%AE%BF%E6%99%AE%E9%80%9A%E5%8F%91%E7%A5%A8%E5%8F%AF%E4%BB%A5%E6%8A%A5%E9%94%80%E5%90%97\"\n" +
                        "    }");
        try {
            BatchSendOTOResponse batchSendOTOResponse = client.batchSendOTOWithOptions(batchSendOTORequest, batchSendOTOHeaders, new RuntimeOptions());
            System.out.println(JSON.toJSONString(batchSendOTOResponse.getBody()));
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println(err.code);
                System.out.println(err.message);
            }
        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println(err.code);
                System.out.println(err.message);
            }
        }
    }

    public void sendMessage2(String userId) throws Exception {
        com.aliyun.dingtalkrobot_1_0.Client client = createClient();
        BatchSendOTOHeaders batchSendOTOHeaders = new BatchSendOTOHeaders();
        batchSendOTOHeaders.xAcsDingtalkAccessToken = DingTalkGetTokenUtil.getAccessToken();
        BatchSendOTORequest batchSendOTORequest = new BatchSendOTORequest()
                .setRobotCode(getRobotCode())
                .setUserIds(java.util.Arrays.asList(userId))
                .setMsgKey("sampleText")
                .setMsgParam("{\"content\": \"发票需要普票\"}");
        try {
            BatchSendOTOResponse batchSendOTOResponse = client.batchSendOTOWithOptions(batchSendOTORequest, batchSendOTOHeaders, new RuntimeOptions());
            System.out.println(JSON.toJSONString(batchSendOTOResponse.getBody()));
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println(err.code);
                System.out.println(err.message);
            }
        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println(err.code);
                System.out.println(err.message);
            }
        }
    }

    public void sendMessage3(String userId) throws Exception {
        com.aliyun.dingtalkrobot_1_0.Client client = createClient();
        BatchSendOTOHeaders batchSendOTOHeaders = new BatchSendOTOHeaders();
        batchSendOTOHeaders.xAcsDingtalkAccessToken = DingTalkGetTokenUtil.getAccessToken();
        BatchSendOTORequest batchSendOTORequest = new BatchSendOTORequest()
                .setRobotCode(getRobotCode())
                .setUserIds(java.util.Arrays.asList(userId))
                .setMsgKey("sampleMarkdown")
                .setMsgParam("{\n" +
                        "        \"title\": \"发票内容\",\n" +
                        "        \"text\": \"发票内容一般包括:票头、字轨号码、联次及用途、客户名称、银行开户账号、商(产)品名称或经营项目、计量单位、数量、单价、金额,以及大小写金额、经手人、单位印章、开票日期等。实行增值税的单位所使用的增值税专用发票还应有税种、税率、税额等内容。\"\n" +
                        "    }");
        try {
            BatchSendOTOResponse batchSendOTOResponse = client.batchSendOTOWithOptions(batchSendOTORequest, batchSendOTOHeaders, new RuntimeOptions());
            System.out.println(JSON.toJSONString(batchSendOTOResponse.getBody()));
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println(err.code);
                System.out.println(err.message);
            }
        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println(err.code);
                System.out.println(err.message);
            }
        }
    }

    public void sendMessage4(String userId) throws Exception {
        com.aliyun.dingtalkrobot_1_0.Client client = createClient();
        BatchSendOTOHeaders batchSendOTOHeaders = new BatchSendOTOHeaders();
        batchSendOTOHeaders.xAcsDingtalkAccessToken = DingTalkGetTokenUtil.getAccessToken();
        BatchSendOTORequest batchSendOTORequest = new BatchSendOTORequest()
                .setRobotCode(getRobotCode())
                .setUserIds(java.util.Arrays.asList(userId))
                .setMsgKey("sampleText")
                .setMsgParam("{\"content\": \"差旅住宿普通发票可以报销\"}");
        try {
            BatchSendOTOResponse batchSendOTOResponse = client.batchSendOTOWithOptions(batchSendOTORequest, batchSendOTOHeaders, new RuntimeOptions());
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println(err.code);
                System.out.println(err.message);
            }
        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
                System.out.println(err.code);
                System.out.println(err.message);
            }
        }
    }
}







