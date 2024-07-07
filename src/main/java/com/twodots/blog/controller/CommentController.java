package com.twodots.blog.controller;

import com.twodots.blog.entity.BlogComment;
import com.twodots.blog.service.CommentService;
import com.twodots.blog.util.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * CommentController
 *
 * @date 2024/7/5 15:18
 */
@RestController
@RequestMapping("/comment")
@Api(tags = "评论管理")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    HttpServletRequest request;

    @ApiOperation("根据博客ID获取评论")
    @GetMapping("/{id}")
    public AjaxResponse getComment(@ApiParam(value = "博客ID", required = true) @PathVariable Integer id) {
        return AjaxResponse.success(commentService.getCommentByBlogId(id), "查询成功");
    }

    @ApiOperation("添加评论")
    @PostMapping("/")
    public AjaxResponse postComment(@ApiParam(value = "评论信息", required = true) @RequestBody BlogComment blogComment) {
        if (request.getHeader("token") == null) {
            blogComment.setComment_admin(false);
        } else {
            blogComment.setComment_admin(true);
            blogComment.setComment_nickname(blogComment.getComment_nickname());
        }
        commentService.postComment(blogComment);
        return AjaxResponse.success("添加成功");
    }

    @ApiOperation("修改评论")
    @PutMapping("/admin")
    public AjaxResponse putComment(@ApiParam(value = "评论信息", required = true) @RequestBody BlogComment blogComment) {
        commentService.putComment(blogComment);
        return AjaxResponse.success("修改成功");
    }

    @ApiOperation("删除评论")
    @DeleteMapping("/admin/{id}")
    public AjaxResponse deleteComment(@ApiParam(value = "评论ID", required = true) @PathVariable Integer id) {
        commentService.deleteComment(id);
        return AjaxResponse.success("删除成功");
    }
}
