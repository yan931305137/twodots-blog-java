package com.twodots.blog.service;

import com.twodots.blog.entity.Blogs;

import java.util.List;
import java.util.Map;

/**
 * BlogService
 *
 * @ date 2024/7/5 15:19
 */
public interface BlogService {
    //    查询首页所有文章
    List<Map<String, Object>> getBlog(Integer page, Integer size, Integer pageSize);

    //    根据ID查询文章
    Map<String, Object> getBlogById(Integer id);

    //    查询推荐文章
    List<Map<String, Object>> getBlogRecommend();

    Integer getBlogCount();


    //    管理员操作

    //    查询文章
    List<Map<String, Object>> adminGetBlog(Integer page, Integer size, Integer pageSize);

    //    根据ID查询文章
    Map<String, Object> adminGetBlogById(Integer id);

    //    保存文章
    void saveBlog(Blogs blogs);

    //    删除文章
    void deleteBlog(Integer id);

    //    修改文章
    void putBlog(Blogs blogs);

    //    查询文章总数
    Integer getAdminBlogCount();
}
