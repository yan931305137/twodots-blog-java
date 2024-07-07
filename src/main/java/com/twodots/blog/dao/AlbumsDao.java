package com.twodots.blog.dao;

import com.twodots.blog.entity.Albums;
import com.twodots.blog.entity.Photos;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * AlbumsDao
 * @ date 2024/7/5 15:18
 */
@Mapper
public interface AlbumsDao {

    List<Map<String, Object>> getAlbums();

    Albums getAlbumById(Integer id);

    void saveAlbums(Albums albums);

    void putAlbums(Albums albums);

    void deleteAlbums(Integer id);

    List<String> getPhotoNameByAlbumId(Integer id);

    List<Integer> getAlbumSonIdByAlbumId(Integer id);

}
