package com.twodots.blog.service;


import java.util.Map;

/**
 * UserService
 *
 * @ date 2024/7/5 15:19
 */
public interface UserService {

    Map<String, Object> login(Map<String, String> name_password);
}
