package com.twodots.blog.dao;

import com.twodots.blog.entity.Photos;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * CommentDao
 *
 * @ date 2024/7/5 15:18
 */
@Mapper
public interface PhotosDao {
    List<Map<String, Object>> getPhotos(Integer album_id, Integer page, Integer pageSize);

    Integer getPhotoCountById(Integer id);

    Photos getPhotoById(Integer id);

    void savePhotos(Photos photos);

    void putPhotos(Photos photos);

    void deletePhotos(Integer photoId);

    String getPhotoNameById(Integer photoId);
}
