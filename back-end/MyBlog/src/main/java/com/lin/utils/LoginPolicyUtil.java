package com.lin.utils;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lin.domain.User;

@Component
public class LoginPolicyUtil {

	private static final Logger log = LoggerFactory.getLogger(LoginPolicyUtil.class);

	@Autowired
	private PwUtil pwUtil;

	@Qualifier(value = "sessionIdSet")
	@Autowired
	private Set<String> sessionIdSet;
	
	@Value(value = "${sessionTimeOut}")
	private Integer sessionTimeOut;

	public boolean validate(User loginUser, User user, HttpServletRequest request) {
		log.info("name:{}", loginUser.getName());
		log.info("password:{}", loginUser.getPassword());
		boolean isPasswordOk = validatePassword(loginUser, user, request);
		replaceSessionIdIfDuplicatedLogin(request);
		if (isPasswordOk) {
			request.getSession().setMaxInactiveInterval(sessionTimeOut);
			sessionIdSet.add(request.getSession().getId());
		}
		else
			request.getSession().invalidate();
		return isPasswordOk ? true : false;
	}

	private boolean validatePassword(User loginUser, User user, HttpServletRequest request) {
		String encryptedPw = pwUtil.encrypt(loginUser.getPassword());
		return user.getPassword().equals(encryptedPw);
	}

	private void replaceSessionIdIfDuplicatedLogin(HttpServletRequest request) {
		if (!request.getSession().isNew()) {
			log.info("Invalidated sessionId:{}", request.getSession().getId());
			sessionIdSet.removeIf(s -> s.equals(request.getSession().getId()));
			request.getSession().invalidate();
		}
	}
}
