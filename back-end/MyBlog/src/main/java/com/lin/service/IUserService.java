package com.lin.service;

import com.lin.domain.User;

public interface IUserService {

	public User findByName(String name);

	public User findById(String id);

	public void insertUser(User user);
}
