package com.kdgcsoft.power.common.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.kdgcsoft.power.common.bean.ConflictException;
import com.kdgcsoft.power.common.bean.SystemConstants;


/**
 * 统一拦截controller层抛出的异常
 * @author Administrator
 *
 */
public class ExceptionHandler implements HandlerExceptionResolver {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);
	  
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  
            Exception exception) {  
    	
    	if(exception instanceof DataIntegrityViolationException){
    		response.setStatus(520);
    		LOGGER.info("编码重复！");
    	} else if(exception instanceof AuthorizationException){
    		response.setStatus(521);
    		LOGGER.info("没有操作权限！");
    	} else if(exception instanceof ConflictException || exception instanceof ObjectOptimisticLockingFailureException) {
    		response.setStatus(523);
    		LOGGER.info(SystemConstants.MSG_IS_UPDATE);
    	} else{
    		response.setStatus(560);
    		LOGGER.error("操作失败！",exception);
    	}
    	
        ModelAndView model = new ModelAndView();
        model.addObject("exception", exception);
        model.setViewName("error/ExceptionError");
        
        return model;
          
    }  
} 