package com.twodots.blog.service.Imp;

import com.twodots.blog.dao.BlogDao;
import com.twodots.blog.dao.TypeDao;
import com.twodots.blog.entity.BlogTag;
import com.twodots.blog.entity.Blogs;
import com.twodots.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * BlogServiceImpl
 * @ date 2024/7/5 15:19
 */

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;

    @Autowired
    TypeDao typeDao;


    @Override
    public List<Map<String, Object>> getBlog(Integer page, Integer size, Integer pageSize) {
//        每页条数
        List<Map<String, Object>> list = blogDao.getBlog((page-1)*pageSize, pageSize);
        List<Map<String, Object>> newList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> value = list.get(i);
            value.put("tags", typeDao.getTagByBlogId((Integer) value.get("blog_id")));
            newList.add(value);
        }
        return newList;
    }

    @Override
    public Map<String, Object> getBlogById(Integer id) {
        blogDao.addView(id);
        Map<String, Object> map = new HashMap(blogDao.getBlogById(id));
        map.put("types", typeDao.getTagByBlogId(id));
        return map;
    }

    @Override
    public List<Map<String, Object>> getBlogRecommend() {
        return blogDao.getBlogRecommend();
    }

    @Override
    public Integer getBlogCount() {
        return blogDao.getBlogCount();
    }

    @Override
    public List<Map<String, Object>> adminGetBlog(Integer page, Integer size, Integer pageSize) {
        List<Map<String, Object>> list = blogDao.adminGetBlog((page-1)*pageSize, pageSize);
        return list;
    }

    @Override
    public Map<String, Object>  adminGetBlogById(Integer id) {

        Map<String, Object> map = new HashMap(blogDao.adminGetBlogById(id));
        map.put("type_tags", typeDao.getTagByBlogId(id));
        return map;
    }

    @Override
    public void saveBlog(Blogs blogs) {
        blogs.setBlog_views(0L);
        blogs.setUser_id(1);
        blogs.setBlog_update_time(new Date());
        blogDao.saveBlog(blogs);

        List<Integer> tags = blogs.getTags();
//        清空文章标签
        blogDao.deleteBlogTag(blogs.getBlog_id());

        for(Integer tag_id: tags){
            BlogTag blogTag = new BlogTag();
            blogTag.setBlog_id(blogs.getBlog_id());
            blogTag.setTag_id(tag_id);
            blogDao.postBlogTag(blogTag);
        }
    }

    @Override
    public void deleteBlog(Integer id) {
        blogDao.deleteBlog(id);
    }

    @Override
    public void putBlog(Blogs blogs) {
        blogs.setBlog_update_time(new Date());
        blogDao.putBlog(blogs);

        List<Integer> tags = blogs.getTags();
//        清空文章标签
        blogDao.deleteBlogTag(blogs.getBlog_id());

        for(Integer tag_id: tags){
            BlogTag blogTag = new BlogTag();
            blogTag.setBlog_id(blogs.getBlog_id());
            blogTag.setTag_id(tag_id);
            blogDao.postBlogTag(blogTag);
        }
    }

    @Override
    public Integer getAdminBlogCount() {
        return blogDao.getAdminBlogCount();
    }


}
