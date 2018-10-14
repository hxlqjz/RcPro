package com.kdgcsoft.power.common.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.html.HTMLLayout;
import ch.qos.logback.classic.html.UrlCssBuilder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.read.CyclicBufferAppender;

/**
 * 显示系统日志。基于Logback的CyclicBufferAppender。需要在logback.xml中预先配置该appender，且appender名称必须为"CYCLIC"。
 * @author hling
 *
 */
public class LogViewerServlet extends HttpServlet {

	private static final long serialVersionUID = -3551928133801157219L;
	
	private static final String CYCLIC_BUFFER_APPENDER_NAME = "CYCLIC";
	

	private static final Logger LOGGER = LoggerFactory.getLogger(LogViewerServlet.class);

	private static volatile CyclicBufferAppender<ILoggingEvent> cyclicBufferAppender;
	
	private static volatile HTMLLayout layout;
	static String PATTERN = "%d%-5p%logger{25}%m";

	@Override
	public void init() throws ServletException {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		initialize(lc);
		super.init();
	}

	void reacquireCBA() {
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		cyclicBufferAppender = (CyclicBufferAppender<ILoggingEvent>) context.getLogger(Logger.ROOT_LOGGER_NAME)
				.getAppender(CYCLIC_BUFFER_APPENDER_NAME);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long start = 0;
		
		// 获取需要显示日志的开始时间参数
		if (req.getParameter("start") != null) {
			try {
				start = Long.parseLong(req.getParameter("start"));
			} catch (NumberFormatException e) {
				start = 0;
			}
		}
		reacquireCBA();

		resp.setContentType("text/html");
		PrintWriter output = resp.getWriter();
        if(start == 0){
	       start = new Date().getTime();
        }
		long lastTimeStamp = printLogs(start, output);
		output.append(String.valueOf(lastTimeStamp));
		output.flush();
		output.close();
	}

	private long printLogs(long startTimeStamp, PrintWriter output) {
		int count = -1;
		long lastTimeStamp = 0;
		
		if (cyclicBufferAppender ==null) {
			return 0;
		}
		
		count = cyclicBufferAppender.getLength();

		if (count == -1) {
			output.append("<tr><td>Failed to locate CyclicBuffer</td></tr>\r\n");
		} else if (count == 0) {
			output.append("<tr><td>No logging events to display</td></tr>\r\n");
		} else {
			LoggingEvent le = null;
			for (int i = 0; i < count; i++) {
				le = (LoggingEvent) cyclicBufferAppender.get(i);
				if (le.getTimeStamp() > startTimeStamp) {
					output.append(layout.doLayout(le) + "\r\n");
				}
			}
			if (le != null) {
				lastTimeStamp = le.getTimeStamp();
			}
		}
		
		return lastTimeStamp;
	}

	private void initialize(LoggerContext context) {
		LOGGER.debug("Initializing ViewLastLog Servlet");
		cyclicBufferAppender = (CyclicBufferAppender<ILoggingEvent>) context.getLogger(Logger.ROOT_LOGGER_NAME)
				.getAppender(CYCLIC_BUFFER_APPENDER_NAME);

		layout = new HTMLLayout();
		layout.setContext(context);
		UrlCssBuilder cssBuilder = new UrlCssBuilder();
		cssBuilder.setUrl("../static/css/logviewer.css");
		layout.setCssBuilder(cssBuilder);
		layout.setPattern(PATTERN);
		layout.setTitle("Last Logging Events");
		layout.start();
	}

	public boolean isResetResistant() {
		return false;
	}

	public void onStop(LoggerContext arg0) {
	}
}