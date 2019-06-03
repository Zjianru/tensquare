package com.tensquare.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.tensquare.sms.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/30
 * com.tensquare.sms.listener
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {
	@Autowired
	private SmsUtil smsUtil;
	@Value("${aliyun.sms.templateCode}")
	private String templateCode;
	@Value("${aliyun.sms.signName}")
	private String signName;

	@RabbitHandler
	public void executeSms(Map<String, String> map) {
		try {
			String checkCode = map.get("checkCode");
			smsUtil.sendSms(map.get("mobile"), templateCode, signName, "{\"checkcode\":\""+checkCode+"\"}");
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
}
