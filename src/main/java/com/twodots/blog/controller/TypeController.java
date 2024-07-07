package com.twodots.blog.controller;

import com.twodots.blog.entity.Types;
import com.twodots.blog.service.TypeService;
import com.twodots.blog.util.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * TypeController
 *
 * @date 2024/7/5 15:18
 */
@RestController
@RequestMapping("/types")
@Api(tags = "Type管理接口")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @ApiOperation(value = "查询所有Type")
    @GetMapping("")
    public AjaxResponse getType() {
        return AjaxResponse.success(typeService.getType(), "查询成功");
    }

    @ApiOperation(value = "根据标签查询文章")
    @GetMapping("/tags/{id}")
    public AjaxResponse getBlogByTag(@ApiParam(value = "标签ID", required = true) @PathVariable Integer id) {
        return AjaxResponse.success(typeService.getBlogByTag(id), "查询成功");
    }

    @ApiOperation(value = "查询所有Type和Tag")
    @GetMapping("/admin")
    public AjaxResponse getAllTypeTag() {
        return AjaxResponse.success(typeService.getAllTypeTag(), "查询成功");
    }

    @ApiOperation(value = "分页查询所有Type和Tag")
    @GetMapping("/admin/page/{page}")
    public AjaxResponse getAllTypeTagByPage(
            @ApiParam(value = "页码", required = true) @PathVariable Integer page) {
        Integer pageSize = 10;
        Integer blogSize = typeService.getTypeCount();
        String size = blogSize.toString();
        List<Map<String, Object>> list = typeService.getAllTypeTagByPage(page, blogSize, pageSize);

        if (!list.isEmpty()) {
            return AjaxResponse.success(list, "查询成功", size);
        } else {
            return AjaxResponse.success(404, "超出范围");
        }
    }

    @ApiOperation(value = "添加新的Type")
    @PostMapping("/admin")
    public AjaxResponse postType(@ApiParam(value = "Type对象", required = true) @RequestBody Types types) {
        typeService.postType(types);
        return AjaxResponse.success(200, "添加成功");
    }

    @ApiOperation(value = "删除指定ID的Type")
    @DeleteMapping("/admin/{id}")
    public AjaxResponse deleteType(@ApiParam(value = "Type ID", required = true) @PathVariable Integer id) {
        typeService.deleteType(id);
        return AjaxResponse.success(200, "删除成功");
    }

    @ApiOperation(value = "修改Type信息")
    @PutMapping("/admin")
    public AjaxResponse putType(@ApiParam(value = "Type对象", required = true) @RequestBody Types types) {
        typeService.putType(types);
        return AjaxResponse.success(200, "修改成功");
    }
}
