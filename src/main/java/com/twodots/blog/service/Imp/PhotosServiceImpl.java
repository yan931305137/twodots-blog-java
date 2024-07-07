package com.twodots.blog.service.Imp;

import com.twodots.blog.dao.PhotosDao;
import com.twodots.blog.entity.Photos;
import com.twodots.blog.service.PhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * AlbumsServiceImpl
 * @ date 2024/7/5 15:19
 */
@Service
public class PhotosServiceImpl implements PhotosService {

    @Autowired
    PhotosDao photosDao;

    @Override
    public List<Map<String, Object>> getPhotos(Integer album_id, Integer page, Integer pageSize) {
        return photosDao.getPhotos(album_id, (page-1)*pageSize, pageSize);
    }

    @Override
    public Integer getPhotoCountById(Integer id) {
        return photosDao.getPhotoCountById(id);
    }

    @Override
    public Photos getPhotoById(Integer id) {
        return photosDao.getPhotoById(id);
    }

    @Override
    public void savePhotos(Photos photos) {
        photosDao.savePhotos(photos);
    }

    @Override
    public void putPhotos(Photos photos) {
        photosDao.putPhotos(photos);
    }


    @Override
    @Transactional
    public String deletePhotos(Integer id) {
        String photoSaveName = photosDao.getPhotoNameById(id);
        photosDao.deletePhotos(id);
        return photoSaveName;
    }

}
