package com.lin.interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.google.gson.Gson;
import com.lin.responseCodeEnum.ResponseCode;

@Component
public class RequestInterceptor implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

	@Qualifier(value = "sessionIdSet")
	@Autowired
	private Set<String> sessionIdSet;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("-----------------------------Request Start-----------------------------");
		Cookie[] cookies = request.getCookies();
		Cookie JSESSIONID = Stream.of(cookies).filter(cookie -> cookie.getName().equals("JSESSIONID")).findFirst()
				.orElse(null);
		log.info("JSESSIONID:{}", JSESSIONID.getValue());
		log.info("RequestUri:{}", request.getRequestURI());
		Optional<String> sessionExists = sessionIdSet.stream().filter(s -> s.equals(JSESSIONID.getValue())).findAny();
		if (!sessionExists.isPresent()) {
			Map<String, Object> map = new HashMap<>();
			ResponseCode.DUPLICATED_LOGIN.setResponseInfo(map);
			Gson gson = new Gson();
			response.getWriter().append(gson.toJson(map));
			return false;
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("-----------------------------Request Done-----------------------------");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
