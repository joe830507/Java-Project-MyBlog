package com.lin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lin.domain.User;
import com.lin.responseCodeEnum.ResponseCode;
import com.lin.service.impl.UserService;
import com.lin.utils.LoginPolicyUtil;
import com.lin.utils.PwUtil;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private PwUtil pwUtil;

	@Autowired
	private LoginPolicyUtil loginPolicyUtil;

	@PostMapping(value = "/login")
	public Map<String, Object> login(@RequestBody User loginUser, HttpServletRequest request) {
		Map<String, Object> response = new HashMap<String, Object>();
		User user = userService.findByName(loginUser.getName());
		if (!loginPolicyUtil.validate(loginUser, user, request)) {
			ResponseCode.LOGIN_FAILED.setResponseInfo(response);
		}
		ResponseCode.REQUEST_SUCCESS.setResponseInfo(response);
		return response;
	}

	@PostMapping(value = "/addUser")
	public Map<String, Object> addUser(@RequestBody User user) {
		Map<String, Object> response = new HashMap<String, Object>();
		String encryptedPw = pwUtil.encrypt(user.getPassword());
		user.setId(UUID.randomUUID().toString());
		user.setPassword(encryptedPw);
		user.setCreateDate(new Date());
		try {
			userService.insertUser(user);
		} catch (Exception e) {
			log.error("failed to add user:{}", e);
			ResponseCode.ADD_USER_FAILED.setResponseInfo(response);
		}
		ResponseCode.REQUEST_SUCCESS.setResponseInfo(response);
		return response;
	}

	@PostMapping(value = "/test")
	public Map<String, Object> test(HttpServletRequest req) {
		Map<String, Object> response = new HashMap<String, Object>();
		log.info("current_session_id:{}", req.getSession().getId());
		log.info("isNew:{}", req.getSession().isNew());
		ResponseCode.REQUEST_SUCCESS.setResponseInfo(response);
		return response;
	}
}
