package com.twodots.blog.service;

import com.twodots.blog.entity.BlogComment;

import java.util.List;
import java.util.Map;

/**
 * CommentService
 *
 * @ date 2024/7/5 15:19
 */
public interface CommentService {

    //  查询该文章所有评论
    List<Map<String, Object>> getCommentByBlogId(Integer id);

    //  添加评论
    void postComment(BlogComment blogComment);

    //  管理员修改评论
    void putComment(BlogComment blogComment);

    //  管理员删除评论
    void deleteComment(Integer id);
}
