package com.tensquare.sms.utils;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 短信工具类
 * @author Administrator
 *
 */
@Component
public class SmsUtil {


    static final String product = "Dysmsapi";
    static final String domain = "dysmsapi.aliyuncs.com";

    @Autowired
    private Environment env;


    /**
     * 发送短信
     * @param mobile 手机号
     * @param templateCode 模板号
     * @param signName 签名
     * @param param 参数
     * @return
     * @throws ClientException
     */
    public SendSmsResponse sendSms(String mobile, String templateCode, String signName, String param) throws ClientException {
        String accessKeyId =env.getProperty("aliyun.sms.accessKeyId");
        String accessKeySecret = env.getProperty("aliyun.sms.accessKeySecret");
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(mobile);
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setTemplateParam(param);
        request.setOutId("yourOutId");
        return acsClient.getAcsResponse(request);
    }

    /**
     *
     * @param mobile
     * @param bizId
     * @return
     * @throws ClientException
     */
    public  QuerySendDetailsResponse querySendDetails(String mobile,String bizId) throws ClientException {
        String accessKeyId =env.getProperty("accessKeyId");
        String accessKeySecret = env.getProperty("accessKeySecret");
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber(mobile);
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        request.setPageSize(10L);
        request.setCurrentPage(1L);
        return acsClient.getAcsResponse(request);
    }
}