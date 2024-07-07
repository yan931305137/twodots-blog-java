package com.twodots.blog.service;

import com.twodots.blog.entity.Photos;

import java.util.List;
import java.util.Map;

/**
 * AlbumsService
 *
 * @ date 2024/7/5 15:19
 */
public interface PhotosService {

    //    查询相册内的照片
    List<Map<String, Object>> getPhotos(Integer album_id, Integer page, Integer pageSize);

    //    查询相片数量
    Integer getPhotoCountById(Integer id);


    //    根据ID查照片信息
    Photos getPhotoById(Integer id);

    //    保存照片
    void savePhotos(Photos photos);

    //    修改照片
    void putPhotos(Photos photos);

    //    删除照片
    String deletePhotos(Integer id);

}
