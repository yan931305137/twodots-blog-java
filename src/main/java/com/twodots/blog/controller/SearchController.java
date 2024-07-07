package com.twodots.blog.controller;

import com.twodots.blog.service.SearchService;
import com.twodots.blog.util.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * SearchController
 *
 * @date 2024/7/5 15:18
 */
@RestController
@RequestMapping("/search")
@Api(tags = "搜索接口")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @ApiOperation(value = "获取博客列表", notes = "根据关键词和页码获取博客列表")
    @GetMapping("/{kw}/{page}")
    public AjaxResponse getBlog(@PathVariable String kw,
                                @PathVariable Integer page) {
        Integer pageSize = 8;
        Integer blogSize = searchService.getSearchCount(kw);
        String size = blogSize.toString();

        List<Map<String, Object>> list = searchService.getSearch(kw, page, blogSize, pageSize);

        AjaxResponse ajaxResponse;
        if (!list.isEmpty()) {
            ajaxResponse = AjaxResponse.success(list, "查询成功", size);
        } else {
            ajaxResponse = AjaxResponse.success(404, "超出范围");
        }
        return ajaxResponse;
    }

    @ApiOperation(value = "管理员获取博客列表", notes = "管理员根据关键词和页码获取博客列表")
    @GetMapping("/admin/{kw}/{page}")
    public AjaxResponse getAdminBlog(@PathVariable String kw,
                                     @PathVariable Integer page) {
        Integer pageSize = 8;
        Integer blogSize = searchService.getSearchCount(kw);
        String size = blogSize.toString();
        List<Map<String, Object>> list = searchService.getSearch(kw, page, blogSize, pageSize);

        AjaxResponse ajaxResponse;
        if (!list.isEmpty()) {
            ajaxResponse = AjaxResponse.success(list, "查询成功", size);
        } else {
            ajaxResponse = AjaxResponse.success(404, "超出范围");
        }
        return ajaxResponse;
    }
}
