package com.ebang.jwt.boot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ebang.condition.RyxxCondition;
import com.ebang.interfaces.ZhcxService;
import com.ebang.protocol.RequestMsg;

/**
 * 综合查询人员信息查询接口
 * 
 * @author mojiayi
 */
@RestController
@RequestMapping("zhcxryxx")
public class ZhcxPersonController {
	@Autowired
	private ZhcxService zhcxService;

	/**
	 * 常住人口详情和列表查询
	 * 
	 * @param reqBean
	 * @return
	 */
	@RequestMapping(value = "czrk", method = RequestMethod.POST)
	@ResponseBody
	public String getPermenantResidence(HttpServletRequest request, RequestMsg reqBean) {
		RyxxCondition condition = JSON.parseObject(reqBean.getCondition(), RyxxCondition.class);
		int isList = reqBean.getIsList();
		String msgType = reqBean.getMsgType();
		return zhcxService.getPermenantResidence(condition, isList, msgType);
	}
}
