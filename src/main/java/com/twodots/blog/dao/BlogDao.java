package com.twodots.blog.dao;

import com.twodots.blog.entity.BlogTag;
import com.twodots.blog.entity.Blogs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * BlogDao
 * @ date 2024/7/5 15:18
 */
@Mapper
public interface BlogDao {

    List<Map<String, Object>> getBlog(Integer page, Integer pageSize);

    Map<String, Object> getBlogById(Integer id);

    void addView(Integer id);

    List<Map<String, Object>> getBlogRecommend();

    List<Map<String, Object>> adminGetBlog(Integer page, Integer size);

    Map<String, Object> adminGetBlogById(Integer id);

    Integer getBlogCount();

    Integer getAdminBlogCount();

    void saveBlog(Blogs blogs);

    void deleteBlog(Integer id);

    void putBlog(Blogs blogs);

    void deleteBlogTag(Integer id);

    void postBlogTag(BlogTag blogTag);

}
