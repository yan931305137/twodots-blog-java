package com.twodots.blog.controller;

import com.twodots.blog.entity.Albums;
import com.twodots.blog.entity.Photos;
import com.twodots.blog.service.AlbumsService;
import com.twodots.blog.util.AjaxResponse;
import com.twodots.blog.util.PhotoUtils;
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
@Api(tags = "相册管理接口")
public class AlbumsController {

    @Autowired
    private AlbumsService albumsService;


    @Value("${img.dir}")
    private String realPath;

    @ApiOperation("查询所有相册")
    @RequestMapping(method = RequestMethod.GET)
    public AjaxResponse getAlbums() {
        return AjaxResponse.success(albumsService.getAlbums(), "查询成功");
    }

    @ApiOperation("根据ID查询相册")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AjaxResponse getAlbumsById(@PathVariable Integer id) {
        return AjaxResponse.success(albumsService.getAlbumById(id), "查询成功");
    }

    @ApiOperation("创建相册")
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse createAlbum(@ApiIgnore Albums albums, @RequestParam("cover") MultipartFile cover) throws IOException {
        String newFileName = UUID.randomUUID() + "." + FilenameUtils.getExtension(cover.getOriginalFilename());
        String imgPath = realPath + "/albumImg/";
        cover.transferTo(new File(imgPath, newFileName));
        albums.setAlbum_cover(newFileName);
        albumsService.saveAlbums(albums);
        return AjaxResponse.success("添加成功");
    }

    @ApiOperation("修改相册")
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResponse updateAlbum(@PathVariable("id") Integer id,
                                    @ApiIgnore Albums albums,
                                    @RequestParam(value = "cover", required = false) MultipartFile cover) throws IOException {
        albums.setAlbum_id(id);
        String imgPath = realPath + "/albumImg";
        String fileName = albums.getAlbum_cover();
        if (cover != null) {
            if (fileName != null) {
                File delFile = new File(imgPath, fileName);
                delFile.delete();
            }
            Map<String, Object> photoInformation = PhotoUtils.photoSave(imgPath, cover);
            albums.setAlbum_cover(photoInformation.get("newFileName").toString());
        }
        albumsService.putAlbums(albums);
        return AjaxResponse.success("修改成功");
    }

    @ApiOperation("删除相册")
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
    public AjaxResponse deleteAlbum(@PathVariable("id") Integer id) {
        String photoImgPath = realPath + "/photoImg";
        Albums albums = albumsService.getAlbumById(id);
        if (albums.getAlbum_level() == 0) {
            List<Integer> albumSonList = albumsService.getAlbumSonIdByAlbumId(id);
            for (Integer albumSonId : albumSonList) {
                List<String> photoSaveNameList = albumsService.getPhotoNameByAlbumId(albumSonId);
                for (String photoSaveName : photoSaveNameList) {
                    if (photoSaveName != null) {
                        File delFile = new File(photoImgPath, photoSaveName);
                        delFile.delete();
                    }
                }
                albumsService.deleteAlbums(albumSonId);
            }
        } else {
            List<String> photoSaveNameList = albumsService.getPhotoNameByAlbumId(id);
            for (String photoSaveName : photoSaveNameList) {
                if (photoSaveName != null) {
                    File delFile = new File(photoImgPath, photoSaveName);
                    delFile.delete();
                }
            }
            albumsService.deleteAlbums(id);
        }
        albumsService.deleteAlbums(id);
        return AjaxResponse.success("删除成功");
    }
}
