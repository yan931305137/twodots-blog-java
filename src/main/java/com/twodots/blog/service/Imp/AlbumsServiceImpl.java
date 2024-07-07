package com.twodots.blog.service.Imp;

import com.twodots.blog.dao.AlbumsDao;
import com.twodots.blog.entity.Albums;
import com.twodots.blog.entity.Photos;
import com.twodots.blog.service.AlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * AlbumsServiceImpl
 * @ date 2024/7/5 15:19
 */
@Service
public class AlbumsServiceImpl implements AlbumsService {

    @Autowired
    AlbumsDao albumsDao;



    @Override
    public List<Map<String, Object>> getAlbums() {
        return albumsDao.getAlbums();
    }

    @Override
    public Albums getAlbumById(Integer id) {
        return albumsDao.getAlbumById(id);
    }

    @Override
    public void saveAlbums(Albums albums) {
        albums.setAlbum_create_time(new Date());
        albumsDao.saveAlbums(albums);
    }

    @Override
    public void putAlbums(Albums albums) {
        albums.setAlbum_create_time(new Date());

        albumsDao.putAlbums(albums);
    }

    @Override
    public void deleteAlbums(Integer id) {
        albumsDao.deleteAlbums(id);
    }

    @Override
    public List<String> getPhotoNameByAlbumId(Integer id) {
        return albumsDao.getPhotoNameByAlbumId(id);
    }

    @Override
    public List<Integer> getAlbumSonIdByAlbumId(Integer id) {
        return albumsDao.getAlbumSonIdByAlbumId(id);
    }

}
