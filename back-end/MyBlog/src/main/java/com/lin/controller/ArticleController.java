package com.lin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lin.domain.User;
import com.lin.service.UserService;

@RestController
@RequestMapping(value = "/article")
public class ArticleController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/index")
	public Map<String, Object> getSomething() {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = userService.findByName("joe");
		result.put("user", user);
		return result;
	}
}
