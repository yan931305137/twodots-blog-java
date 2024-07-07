package com.twodots.blog.service;

import com.twodots.blog.entity.Albums;
import com.twodots.blog.entity.Photos;

import java.util.List;
import java.util.Map;

/**
 * AlbumsService
 *
 * @ date 2024/7/5 15:19
 */
public interface AlbumsService {

    //    查询所有相册（包含一二级）
    List<Map<String, Object>> getAlbums();

    //    根据ID查相册信息
    Albums getAlbumById(Integer id);

    //    创建相册
    void saveAlbums(Albums albums);

    //    修改相册
    void putAlbums(Albums albums);

    //    删除相册
    void deleteAlbums(Integer id);

    List<String> getPhotoNameByAlbumId(Integer id);

    List<Integer> getAlbumSonIdByAlbumId(Integer id);

}
