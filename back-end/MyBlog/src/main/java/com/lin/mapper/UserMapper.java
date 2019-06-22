package com.lin.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.lin.domain.User;

@Mapper
public interface UserMapper {

	@Select(value = "SELECT ID,NAME,CREATE_DATE,UPDATE_DATE,PASSWORD FROM USER WHERE NAME = #{name}")
	User findByName(@Param(value = "name") String name);
	
	@Select(value = "SELECT ID,NAME,CREATE_DATE,UPDATE_DATE,PASSWORD FROM USER WHERE ID = #{id}")
	User findById(@Param(value = "id") String id);

	@Insert(value = "INSERT INTO USER(ID,NAME,CREATE_DATE,PASSWORD) VALUES(#{user.id},#{user.name},#{user.createDate},#{user.password})")
	void insertUser(@Param(value = "user") User user);
}
