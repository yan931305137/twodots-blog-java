package com.twodots.blog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 分类实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分类实体类")
@EqualsAndHashCode(callSuper = false)
public class Types {

    @ApiModelProperty(value = "分类标签ID", example = "1")
    private Integer typeTagId;

    @ApiModelProperty(value = "分类标签名", example = "科技")
    private String typeTagName;

    @ApiModelProperty(value = "关联的父分类ID", example = "0")
    private Integer typeParentId;

    @ApiModelProperty(value = "分类标签级别", example = "1")
    private Integer typeTagLevel;

}
