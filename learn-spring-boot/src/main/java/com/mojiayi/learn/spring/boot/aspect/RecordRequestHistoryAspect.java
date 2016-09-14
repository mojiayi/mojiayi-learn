package com.mojiayi.learn.spring.boot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.mojiayi.learn.spring.boot.domain.BaseReq;

@Aspect
public class RecordRequestHistoryAspect {
	private static final Logger LOG = LoggerFactory.getLogger("ROLLING_FILE");

	@Before("execution(public * com.mojiayi.learn.spring.boot.controller.*Controller.*(..))")
	public void logToFile(JoinPoint point) {
		try {
			Object[] args = point.getArgs();
			BaseReq reqData = null;
			if (args != null && args.length > 0) {
				for (Object arg : args) {
					if (arg instanceof BaseReq) {
						reqData = BaseReq.class.cast(arg);
						break;
					}
				}
			}
			if (reqData != null) {
				LOG.info("收到请求,reqData=" + JSON.toJSONString(reqData));
			}
		} catch (Exception e) {
			LOG.error("输出请求数据到日志文件出现异常", e);
		}
	}
}
