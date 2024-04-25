package com.example.homework9.mapper;

import com.example.homework9.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    boolean loginByUsername(String username);

    User loginByUsernameAndPassword(String username, String password);
}
