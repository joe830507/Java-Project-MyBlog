package com.lin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lin.domain.User;
import com.lin.mapper.UserMapper;
import com.lin.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserMapper userMapper;

	public User findByName(String name) {
		return userMapper.findByName(name);
	}

	public void insertUser(User user) {
		userMapper.insertUser(user);
	}

	@Override
	public User findById(String id) {
		return userMapper.findById(id);
	}
}
