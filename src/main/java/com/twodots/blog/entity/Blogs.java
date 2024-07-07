package com.twodots.blog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("博客实体类")
@EqualsAndHashCode(callSuper = false)
public class Blogs {

    @ApiModelProperty(value = "博客ID", example = "1")
    private Integer blog_id;

    @ApiModelProperty(value = "标题", example = "Spring Boot入门")
    private String blog_title;

    @ApiModelProperty(value = "内容", example = "这是一篇关于Spring Boot的入门教程。")
    private String blog_content;

    @ApiModelProperty(value = "描述", example = "Spring Boot入门教程的简短描述。")
    private String blog_description;

    @ApiModelProperty(value = "更新时间", example = "2024-07-05T15:19:00Z")
    private Date blog_update_time;

    @ApiModelProperty(value = "查看次数", example = "100")
    private Long blog_views;

    @ApiModelProperty(value = "是否开启评论", example = "true")
    private Boolean blog_comment;

    @ApiModelProperty(value = "是否公开", example = "true")
    private Boolean blog_public;

    @ApiModelProperty(value = "是否发布", example = "true")
    private Boolean blog_release;

    @ApiModelProperty(value = "是否推荐", example = "false")
    private Boolean blog_recommend;

    @ApiModelProperty(value = "首图地址", example = "http://example.com/image.jpg")
    private String blog_first_img;

    @ApiModelProperty(value = "关联用户ID", example = "1")
    private Integer user_id;

    @ApiModelProperty(value = "关联标签ID列表", example = "[1, 2, 3]")
    private List<Integer> tags;
}
