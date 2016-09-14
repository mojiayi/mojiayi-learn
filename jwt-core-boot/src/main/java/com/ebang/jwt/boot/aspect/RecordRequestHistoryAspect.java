package com.ebang.jwt.boot.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ebang.annotation.ReqType;
import com.ebang.condition.BaseCondition;
import com.ebang.interfaces.SysBasicService;
import com.ebang.protocol.RequestMsg;
import com.ebang.utils.StringHelper;

@Aspect
public class RecordRequestHistoryAspect {
	private static final Logger LOG = LoggerFactory.getLogger("ROLLING_FILE");

	@Autowired
	private SysBasicService sysBasicService;

	@Before("execution(public * com.ebang.oa.*ServiceImpl.*(..))")
	public void logRequestHistory(JoinPoint point) {
		try {
			Signature signature = point.getStaticPart().getSignature();
			String methodName = signature.getName();
			Method[] methods = signature.getDeclaringType().getMethods();
			String typeName = null;
			for (Method method : methods) {
				if (methodName.equals(method.getName())) {
					ReqType annotation = method.getAnnotation(ReqType.class);
					if (annotation == null) {
						continue;
					}
					typeName = annotation.typeName();
					break;
				}
			}
			if (StringHelper.isNotEmpty(typeName)) {
				Object[] args = point.getArgs();
				String policeNo = "";
				if (args != null && args.length > 0) {
					for (Object arg : args) {
						if (arg instanceof BaseCondition) {
							policeNo = BaseCondition.class.cast(args[0]).getPoliceNo();
							break;
						}
					}
				}
				sysBasicService.logRequestHistory(policeNo, typeName);
			}
		} catch (Exception e) {
			LOG.error("记录客户端请求ReqType出现异常", e);
		}
	}

	@Before("execution(public * com.ebang.jwt.boot.controller.*Controller.*(..))")
	public void logToFile(JoinPoint point) {
		try {
			Object[] args = point.getArgs();
			RequestMsg reqBean = null;
			if (args != null && args.length > 0) {
				for (Object arg : args) {
					if (arg instanceof RequestMsg) {
						reqBean = RequestMsg.class.cast(arg);
						break;
					}
				}
			}
			if (reqBean != null) {
				StringBuilder log = new StringBuilder(32);
				log.append("收到请求,reqType=");
				log.append(reqBean.getMsgType());
				log.append(",condition=");
				log.append(reqBean.getCondition());
				log.append(",isList=");
				log.append(reqBean.getIsList());
				LOG.info(new String(log));
			}
		} catch (Exception e) {
			LOG.error("输出客户端请求到日志文件出现异常", e);
		}
	}
}
