package com.lin.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lin.interceptor.RequestInterceptor;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Autowired
	RequestInterceptor requestInterceptor;

	@Bean(value = "sessionIdSet")
	public Set<String> getSessionIds() {
		return new HashSet<String>();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestInterceptor);
	}
}
