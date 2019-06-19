package com.lin.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.lin.domain.User;

@Mapper
public interface UserMapper {

	@Select(value = "SELECT ID,NAME,TYPE,CREATE_DATE,PASSWORD FROM USER WHERE NAME = #{name}")
	User findByName(@Param(value = "name") String name);

	@Insert(value = "INSERT INTO USER(NAME,TYPE,CREATE_DATE,PASSWORD) VALUES(#{user.name},#{user.type},#{user.createDate},#{user.password})")
	void insertUser(@Param(value = "user") User user);
}
