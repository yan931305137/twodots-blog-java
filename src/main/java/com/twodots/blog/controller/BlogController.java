package com.twodots.blog.controller;

import com.twodots.blog.entity.Blogs;
import com.twodots.blog.service.BlogService;
import com.twodots.blog.util.AjaxResponse;
import io.swagger.annotations.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
/**
 * BlogController
 *
 * @date 2024/7/5 15:18
 */
@RestController
@RequestMapping("/blogs")
@Api(tags = "Blog Management API", description = "提供博客管理相关的 Rest API")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Value("${img.dir}")
    private String realPath;

    @ApiOperation("获取所有博客")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功获取博客列表"),
            @ApiResponse(code = 404, message = "超出范围")
    })
    @GetMapping("/page/{page}")
    public AjaxResponse getBlog(@PathVariable @ApiParam("页码") Integer page) {
        Integer pageSize = 8;
        Integer blogSize = blogService.getBlogCount();
        String size = blogSize.toString();
        List<Map<String, Object>> list = blogService.getBlog(page, blogSize, pageSize);

        if (!list.isEmpty()) {
            return AjaxResponse.success(list, "查询成功", size);
        } else {
            return AjaxResponse.success(404, "超出范围");
        }
    }

    @ApiOperation("根据ID获取博客")
    @GetMapping("/{id}")
    public AjaxResponse getBlogById(@PathVariable @ApiParam("博客ID") Integer id) {
        Map<String, Object> map = blogService.getBlogById(id);
        if ((Boolean) map.get("blog_release") && (Boolean) map.get("blog_public")) {
            return AjaxResponse.success(map, "查询成功");
        } else {
            return AjaxResponse.success(401, "无权限");
        }
    }

    @ApiOperation("获取推荐博客")
    @GetMapping("/recommend")
    public AjaxResponse getBlogRecommend() {
        return AjaxResponse.success(blogService.getBlogRecommend(), "查询成功");
    }

    @ApiOperation("管理员获取所有博客")
    @GetMapping("/admin/page/{page}")
    public AjaxResponse adminGetBlog(@PathVariable @ApiParam("页码") Integer page) {
        Integer pageSize = 8;
        Integer blogSize = blogService.getAdminBlogCount();
        String size = blogSize.toString();
        List<Map<String, Object>> list = blogService.adminGetBlog(page, blogSize, pageSize);

        if (!list.isEmpty()) {
            return AjaxResponse.success(list, "查询成功", size);
        } else {
            return AjaxResponse.success(404, "超出范围");
        }
    }

    @ApiOperation("管理员根据ID获取博客")
    @GetMapping("/admin/{id}")
    public AjaxResponse adminGetBlogById(@PathVariable @ApiParam("博客ID") Integer id) {
        return AjaxResponse.success(blogService.adminGetBlogById(id), "查询成功");
    }

    @ApiOperation("管理员添加博客")
    @PostMapping("/admin")
    public AjaxResponse saveBlog(@ApiParam("博客信息") Blogs blogs,
                                 @ApiParam("首页图片文件") MultipartFile first_img) throws IOException {
        String newFileName = UUID.randomUUID() + "." + FilenameUtils.getExtension(first_img.getOriginalFilename());
        String imgPath = realPath + "/" + "firstImg";
        first_img.transferTo(new File(imgPath, newFileName));
        blogs.setBlog_first_img(newFileName);
        blogService.saveBlog(blogs);
        return AjaxResponse.success("添加成功");
    }

    @ApiOperation("管理员删除博客")
    @DeleteMapping("/admin/{id}")
    public AjaxResponse deleteBlog(@PathVariable @ApiParam("博客ID") Integer id) {
        if (id == null) {
            return AjaxResponse.success(400, "无效ID");
        } else {
            blogService.deleteBlog(id);
            return AjaxResponse.success("删除成功");
        }
    }

    @ApiOperation("管理员修改博客")
    @PutMapping("/admin/{id}")
    public AjaxResponse putBlog(@ApiParam("博客信息") Blogs blogs,
                                @PathVariable @ApiParam("博客ID") Integer id,
                                @ApiParam("首页图片文件") MultipartFile first_img) throws IOException {

        blogs.setBlog_id(id);
        String imgPath = realPath + "/" + "firstImg";

        String fileName = blogs.getBlog_first_img();
        if (first_img != null) {
            if (fileName != null) {
                File delFile = new File(imgPath, fileName);
                delFile.delete();
            }
            String newFileName = UUID.randomUUID() + "." + FilenameUtils.getExtension(first_img.getOriginalFilename());
            blogs.setBlog_first_img(newFileName);
            first_img.transferTo(new File(imgPath, newFileName));
        }

        blogService.putBlog(blogs);
        return AjaxResponse.success("修改成功");
    }

    @ApiOperation("管理员上传文章内图片")
    @PostMapping("/admin/img")
    public AjaxResponse postImg(@ApiParam("内容图片文件") MultipartFile content_img) throws IOException {
        String newFileName = UUID.randomUUID() + "." + FilenameUtils.getExtension(content_img.getOriginalFilename());
        String imgPath = realPath + "/" + "contentImg";
        content_img.transferTo(new File(imgPath, newFileName));
        return AjaxResponse.success("添加成功", newFileName);
    }

    @ApiOperation("管理员删除文章内图片")
    @DeleteMapping("/admin/img/{imgName}")
    public AjaxResponse deleteImg(@PathVariable @ApiParam("图片名称") String imgName) {
        String imgPath = realPath + "/" + "contentImg";
        if (imgName != null) {
            File delFile = new File(imgPath, imgName);
            delFile.delete();
        }
        return AjaxResponse.success("删除成功");
    }
}
