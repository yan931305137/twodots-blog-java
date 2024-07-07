package com.twodots.blog.service.Imp;

import com.twodots.blog.dao.CommentDao;
import com.twodots.blog.entity.BlogComment;
import com.twodots.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * CommentServiceImpl
 * @ date 2024/7/6 21:58
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;


    @Override
    public List<Map<String, Object>> getCommentByBlogId(Integer id) {

        return commentDao.getCommentByBlogId(id);
    }

    @Override
    public void postComment(BlogComment blogComment) {
        blogComment.setComment_create_time(LocalDateTime.now());
        commentDao.postComment(blogComment);
    }

    @Override
    public void putComment(BlogComment blogComment) {

    }

    @Override
    public void deleteComment(Integer id) {

        commentDao.deleteComment(id);

    }
}
