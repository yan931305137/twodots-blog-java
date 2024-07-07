package com.twodots.blog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ author twodots
 * @ date 2024/7/5 15:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "相册实体")
@EqualsAndHashCode(callSuper = false)
public class Albums {

    /**
     * 相册ID
     */
    @ApiModelProperty(value = "相册ID", example = "1")
    private Integer album_id;

    /**
     * 相册名
     */
    @ApiModelProperty(value = "相册名", example = "我的相册")
    private String album_name;

    /**
     * 相册封面
     */
    @ApiModelProperty(value = "相册封面URL", example = "相册封面URL")
    private String album_cover;

    /**
     * 相册简介
     */
    @ApiModelProperty(value = "相册简介", example = "这是一个很棒的相册")
    private String album_introduction;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "相册创建时间", example = "2024-07-05T15:19:00Z")
    private Date album_create_time;

    /**
     * 相册级别
     */
    @ApiModelProperty(value = "相册级别", example = "1")
    private Integer album_level;

    /**
     * 所属一级相册
     */
    @ApiModelProperty(value = "所属一级相册ID", example = "0")
    private Integer album_type;
}
