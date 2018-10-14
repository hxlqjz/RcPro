package com.kdgcsoft.power.common.support;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kdgcsoft.power.common.bean.Principal;
import com.kdgcsoft.power.common.util.AjaxUtil;
import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.entity.fw.system.FwOpmonitor;
import com.kdgcsoft.power.service.fw.system.FwOpmonitorService;

public class TraceInterceptor implements MethodInterceptor{

	@Autowired(required = true)
	private HttpServletRequest request;
	
	@Autowired
	private FwOpmonitorService fwOpmonitorService;
	
	private static final Logger logger = LoggerFactory.getLogger(TraceInterceptor.class);
	
	public TraceInterceptor() {
		
		Properties properties = new Properties();
		
		InputStream is = TraceInterceptor.class.getClassLoader().getResourceAsStream("monitor.properties");
		try {
			properties.load(is);
			if(!StringUtil.isEmpty(properties.getProperty("monitor.log"))){
				String monitor = properties.getProperty("monitor.log");
				System.setProperty("monitor.log", monitor);
			}
			
		} catch (IOException e) {
			logger.error("Properties加载monitor.properties文件出错",e);
		} finally {
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					logger.error("monitor.properties输入流关闭出错",e);
				}
			}
		}
	}
	
	/**
	 * 对拦截的方法进行监控日志处理
	 * @param mAnnotation 方法的监控注解
	 * @param startDate   方法执行起始时间
	 * @param totalTime   方法执行的总时间
	 * @param result      方法执行的结果，成功或失败
	 */
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Logger logger=LoggerFactory.getLogger(invocation.getClass());
		long result = 0;
		long startTime = System.currentTimeMillis();
		try {
			logger.debug("------------【"+invocation.getMethod().getName()+"】:BEGIN!--(power-framework)------------");
			logger.debug("Class 名称："+invocation.getMethod().getDeclaringClass().getName());
			Class<?>[] clazz= invocation.getMethod().getParameterTypes();
			for(int i=0;i<clazz.length;i++){
				 logger.debug("arg["+i+"]类型: "+clazz[i]);
			}
			result = 0;
			return invocation.proceed();
		} catch (Exception e) {
			logger.error("Error happened in class {},in method {}",invocation.getMethod().getDeclaringClass().getName(),invocation.getMethod().getName());
			result = 1;
			throw e;
		}finally{
			logger.debug("------------【"+invocation.getMethod().getName()+"】:END!--(power-framework)------------");
			logger.debug("程序执行时间:" + (System.currentTimeMillis() - startTime));
			String monitor = System.getProperty("monitor.log");
			if("true".equals(monitor)){
				afterInvoke(invocation.getMethod(),startTime, result);
			}
		}
	}
	
	public void afterInvoke(Method method,long startTime,long result){
		Monitor mAnnotation= method.getAnnotation(Monitor.class);
		if(null!=mAnnotation){
			FwOpmonitor opLog=new FwOpmonitor();
			opLog.setCreateDate(new Date());
			opLog.setOpActioname(mAnnotation.actionName());
			opLog.setOpContent(mAnnotation.actionDescr());
			opLog.setOpResult(result);
			opLog.setOpOptime(Calendar.getInstance().getTime());
			opLog.setOpTotaltime(opLog.getOpOptime().getTime() -startTime);
			opLog.setOpUrl(request.getRequestURI());
			opLog.setOpIp(AjaxUtil.getIpAddr(request));		
			Subject subject = SecurityUtils.getSubject();
			if (subject != null) {
				Principal principal = (Principal) subject.getPrincipal();
				if (principal != null) {
					  opLog.setOpUserid(principal.getUserId());
				      opLog.setOpUsername(principal.getUserName());
				}
			}
			//保存操作日志信息到数据库中
			fwOpmonitorService.save(opLog);
		}
	}
	
}

