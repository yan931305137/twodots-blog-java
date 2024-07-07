package com.twodots.blog.entity;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 评论实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("博客评论")
@EqualsAndHashCode(callSuper = false)
public class BlogComment {

    @ApiModelProperty(value = "评论ID")
    private Integer comment_id;

    @ApiModelProperty(value = "是否管理员")
    private Boolean comment_admin;

    @ApiModelProperty(value = "评论内容")
    private String comment_content;

    @ApiModelProperty(value = "评论者邮箱")
    private String comment_email;

    @ApiModelProperty(value = "评论者昵称")
    private String comment_nickname;

    @ApiModelProperty(value = "评论时间")
    private LocalDateTime comment_create_time;

    @ApiModelProperty(value = "博客ID")
    private Integer blog_id;

}
