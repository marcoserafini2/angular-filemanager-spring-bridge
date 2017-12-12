package it.ms.filemanager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import it.ms.filemanager.api.handler.AllowedInterceptor;
import it.ms.filemanager.api.handler.LogInterceptor;

@Configuration
public class ConfigurerAdapter extends WebMvcConfigurerAdapter {

	@Autowired
	AllowedInterceptor allowedInterceptor;

	@Autowired
	LogInterceptor logInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logInterceptor).addPathPatterns("/action/*");
		registry.addInterceptor(allowedInterceptor).addPathPatterns("/action/*");
	}
}