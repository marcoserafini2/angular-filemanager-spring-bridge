package it.ms.filemanager.api.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import it.ms.filemanager.configuration.ConfigFileManager;
import it.ms.filemanager.model.Mode;

@Component
public class AllowedInterceptor extends HandlerInterceptorAdapter {

	private final static Logger LOG = LoggerFactory.getLogger(AllowedInterceptor.class);

	@Autowired
	ConfigFileManager configFileManager;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		RequestMapping requestMappingMethod = ((HandlerMethod) handler).getMethod().getAnnotation(RequestMapping.class);
		if (requestMappingMethod != null) {
			//Response 505
			if(!allowedMethod(requestMappingMethod.name())){
				LOG.debug("Request {} not allowed, response 405", requestMappingMethod.name());
				response.reset();
				response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		}

		return super.preHandle(request, response, handler);
	}


	private boolean allowedMethod(String name){
		try{
			Mode action = Mode.valueOf(name);
			switch (action) {
			case changePermission:
				return configFileManager.getAllowedActions().isChangePermissions();
			case compress:
				return configFileManager.getAllowedActions().isCompress();
			case copy:
				return configFileManager.getAllowedActions().isCopy();
			case createFolder:
				return configFileManager.getAllowedActions().isCreateFolder();
			case edit:
				return configFileManager.getAllowedActions().isEdit();
			case extract:
				return configFileManager.getAllowedActions().isExtract();
			case move:
				return configFileManager.getAllowedActions().isMove();
			case remove:
				return configFileManager.getAllowedActions().isRemove();
			case rename:
				return configFileManager.getAllowedActions().isRename();
			case upload:
				return configFileManager.getAllowedActions().isUpload();
			default:
				//Default allow action true
				return true;
			}
		}catch(IllegalArgumentException ex){
			//Default allow action true
			return true;
		}
	}
}
