package com.twodots.blog.dao;

import com.twodots.blog.entity.BlogComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * CommentDao
 * @ date 2024/7/5 15:18
 */
@Mapper
public interface CommentDao {

    List<Map<String, Object>> getCommentByBlogId(Integer id);

    void postComment(BlogComment blogComment);

    void putComment(BlogComment blogComment);

    void deleteComment(Integer id);
}
