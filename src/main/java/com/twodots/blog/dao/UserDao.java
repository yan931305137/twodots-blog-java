package com.twodots.blog.dao;

import com.twodots.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;


/**
 * UserDao
 * @ date 2024/7/5 15:18
 */
@Mapper
public interface UserDao {

    //    用户登录
    User login(Map<String, String> name_password);
}
