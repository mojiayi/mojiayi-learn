package com.ebang.jwt.core;

import java.io.UnsupportedEncodingException;
import java.util.Collections;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.ebang.constants.MsgType;
import com.ebang.protocol.RequestMsg;

/**
 * Unit test for simple App.
 */
public class AppTest {
	@Test
	public void getCzrk() throws UnsupportedEncodingException {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		RequestMsg reqBean = new RequestMsg();
		reqBean.setIsList(1);
		reqBean.setMsgType(MsgType.CZRK);
		String condition = "{\"xzqh\":\"430524\",\"page\":{\"currentEnd\":6,\"currentIndex\":0,\"pn\":1,\"ps\":10,\"tp\":1,\"tr\":6},\"xm\":\"刘帮\",\"cxr\":\"张律\",\"sfzh\":\"\",\"hh\":\"430524405006794\",\"cxrsfz\":\"430302198408093278\",\"cxrdw\":\"430300000000\",\"policeNo\":\"051087\"}";
		reqBean.setCondition(condition);
		String url = "http://localhost:8082/zhcxryxx/czrk";
		System.out.println("reqBean=" + JSON.toJSONString(reqBean));
		HttpEntity<RequestMsg> reqEntity = new HttpEntity<RequestMsg>(reqBean, headers);

		ResponseEntity<String> rspEntity = restTemplate.exchange(url, HttpMethod.POST, reqEntity, String.class);

		System.out.println(rspEntity.getBody());
	}
}
