package com.twodots.blog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 博客-标签关联实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("博客-标签关联信息")
@EqualsAndHashCode(callSuper = false)
public class BlogTag {

    /**
     * 博客ID
     */
    @ApiModelProperty(value = "博客ID", example = "1")
    private Integer blog_id;

    /**
     * 标签ID
     */
    @ApiModelProperty(value = "标签ID", example = "101")
    private Integer tag_id;

}
