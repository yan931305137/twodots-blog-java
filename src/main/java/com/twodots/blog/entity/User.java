package com.twodots.blog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户实体")
@EqualsAndHashCode(callSuper = false)
public class User {

    @ApiModelProperty(value = "用户ID", example = "1")
    private Integer user_id;

    @ApiModelProperty(value = "用户名", example = "twodots")
    private String user_name;

    @ApiModelProperty(value = "用户密码", example = "password")
    private String user_password;

    @ApiModelProperty(value = "用户邮箱", example = "example@example.com")
    private String user_email;

    @ApiModelProperty(value = "用户头像地址", example = "https://example.com/avatar.jpg")
    private String user_avatar;

    @ApiModelProperty(value = "用户组", example = "1")
    private Integer user_group;

}
