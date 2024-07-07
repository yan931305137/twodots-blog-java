package com.twodots.blog.dao;

import com.twodots.blog.entity.Types;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * TypeDao
 * @ date 2024/7/5 15:18
 */
@Mapper
public interface TypeDao {

    List<Map<String, String>> getType();

    void postType(Types types);

    void deleteType(Integer id);

    void putType(Types types);


    List<Map<String, Object>> getTagByBlogId(Integer id);

    List<Map<String, Object>> getBlogByTag(Integer id);

    List<Map<String, Object>> getAllTypeTagByPage(Integer page, Integer size);

    Integer getTypeCount();

    List<Map<String, Object>> getAllTypeTag();
}


