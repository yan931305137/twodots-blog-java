package com.twodots.blog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 照片实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "照片对象", description = "包含照片相关信息的实体类")
@EqualsAndHashCode(callSuper = false)
public class Photos {

    @ApiModelProperty(value = "照片ID", example = "1")
    private Integer photo_id;

    @ApiModelProperty(value = "照片名", example = "Summer Vacation")
    private String photo_name;

    @ApiModelProperty(value = "保存文件名", example = "summer_vacation.jpg")
    private String photo_save_name;

    @ApiModelProperty(value = "照片简介", example = "A wonderful summer vacation in Hawaii.")
    private String photo_introduction;

    @ApiModelProperty(value = "拍摄时间", example = "2023-07-15")
    private Date photo_filming_time;

    @ApiModelProperty(value = "所属二级相册ID", example = "1")
    private Integer albums_tag;

}
