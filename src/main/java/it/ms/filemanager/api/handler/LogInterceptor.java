package it.ms.filemanager.api.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

	private final static Logger LOG = LoggerFactory.getLogger(LogInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		RequestMapping requestMappingMethod = ((HandlerMethod)handler).getMethod().getAnnotation(RequestMapping.class);
		if(requestMappingMethod != null){
			requestMappingMethod.path();
			LOG.info("Start Request {} ", request.getServletPath());
			request.setAttribute("startTimeExecution", System.currentTimeMillis());
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		long time = System.currentTimeMillis() - (long) request.getAttribute("startTimeExecution");
		LOG.info("End Request {} in {} ms", request.getServletPath(), time);

		super.afterCompletion(request, response, handler, ex);
	}
}
