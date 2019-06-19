package com.lin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lin.domain.User;
import com.lin.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public User findByName(String name) {
		return userMapper.findByName(name);
	}

	public void insertUser(User user) {
		userMapper.insertUser(user);
	}
}
