package com.twodots.blog.controller;

import com.twodots.blog.entity.Photos;
import com.twodots.blog.service.PhotosService;
import com.twodots.blog.util.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("albums")
@Api(tags = "相片管理接口")
public class PhotosController {

    @Autowired
    private PhotosService photosService;

    @Value("${img.dir}")
    private String realPath;

    @ApiOperation("根据照片ID查询照片")
    @RequestMapping(value = "/photos/{id}", method = RequestMethod.GET)
    public AjaxResponse getPhotoById(@PathVariable("id") Integer id) {
        return AjaxResponse.success(photosService.getPhotoById(id), "查询成功");
    }

    @ApiOperation("批量上传照片")
    @RequestMapping(value = "/admin/photos/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse uploadPhotos(@RequestParam("photoList") MultipartFile[] photoList,
                                     @PathVariable("id") Integer id) throws IOException {

        String imgPath = realPath + "/photoImg/";


        for (MultipartFile photoFile : photoList) {
            Photos photo = new Photos();
            photo.setAlbums_tag(id);
            String newFileName = UUID.randomUUID() + "." + FilenameUtils.getExtension(photoFile.getOriginalFilename());
            photoFile.transferTo(new File(imgPath, newFileName));
//            Map<String, Object> photoInformation = PhotoUtils.photoSave(imgPath, photoFile);
//            photo.setPhoto_filming_time((Date) photoInformation.get("img_filming_time"));
            photo.setPhoto_introduction(UUID.randomUUID().toString());
            photo.setPhoto_save_name(newFileName);
            photo.setPhoto_name(UUID.randomUUID().toString());
            photosService.savePhotos(photo);
        }
        return AjaxResponse.success("添加成功");
    }

    @ApiOperation("修改照片信息")
    @RequestMapping(value = "/admin/photos", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResponse updatePhoto(@ApiIgnore Photos photos) {
        photosService.putPhotos(photos);
        return AjaxResponse.success("修改成功");
    }

    @ApiOperation("批量删除照片")
    @RequestMapping(value = "/admin/photoIdList", method = RequestMethod.DELETE)
    public AjaxResponse deletePhotos(@RequestBody List<Integer> photoIdList) {
        String imgPath = realPath + "/photoImg";
        for (Integer photoId : photoIdList) {
            String fileName = photosService.deletePhotos(photoId);
            if (fileName != null) {
                File delFile = new File(imgPath, fileName);
                delFile.delete();
            }
        }
        return AjaxResponse.success("删除成功");
    }

    @ApiOperation("分页查询相册照片")
    @RequestMapping(value = "/{album_id}/page/{page}", method = RequestMethod.GET)
    public AjaxResponse getPhotosByAlbumId(@PathVariable("album_id") Integer albumId,
                                           @PathVariable("page") Integer page) {
        Integer pageSize = 25;
        Integer photoCount = photosService.getPhotoCountById(albumId);
        String size = photoCount.toString();
        List<Map<String, Object>> list = photosService.getPhotos(albumId, page, pageSize);
        AjaxResponse ajaxResponse;
        if (!list.isEmpty()) {
            ajaxResponse = AjaxResponse.success(list, "查询成功", size);
        } else {
            ajaxResponse = AjaxResponse.success(404, "超出范围");
        }
        return ajaxResponse;
    }

}
