package com.twodots.blog.service.Imp;

import com.twodots.blog.dao.UserDao;
import com.twodots.blog.entity.User;
import com.twodots.blog.service.UserService;
import com.twodots.blog.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * UserServiceImpl
 * @ date 2024/7/56 13:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public Map<String, Object> login(Map<String, String> name_password) {
        User userDB = userDao.login(name_password);
        Map<String, Object> map = new HashMap<>();

        if (userDB != null) {
//            登陆成功返回token
            Map<String, String> payload = new HashMap<>();
            payload.put("name", userDB.getUser_name());
            payload.put("email", userDB.getUser_email());
            String token = JWTUtils.getToken(payload);
            map.put("user_name", userDB.getUser_name());
            map.put("token", token);
        }
        return map;

    }
}
