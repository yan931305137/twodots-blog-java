package com.twodots.blog.controller;

import com.twodots.blog.service.UserService;
import com.twodots.blog.util.AjaxResponse;
import com.twodots.blog.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * UserController
 *
 * @date 2024/7/5 15:18
 */
@Api(tags = "用户登录接口")
@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登录", notes = "用户通过用户名和密码登录")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public AjaxResponse login(@ApiParam(value = "用户名和密码", required = true) @RequestBody Map<String, String> name_password) {

        String md5 = MD5Util.code(name_password.get("user_password"));
        name_password.put("user_password", md5);

        Map<String, Object> map = userService.login(name_password);

        AjaxResponse ajaxResponse;
        if (!map.isEmpty()) {
            ajaxResponse = AjaxResponse.success(map, "登录成功");
        } else {
            ajaxResponse = AjaxResponse.success(401, "登录失败");
        }
        return ajaxResponse;
    }
}
