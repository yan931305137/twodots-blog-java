package com.twodots.blog.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * SearchDao
 * @ date 2024/7/5 15:18
 */
@Mapper
public interface SearchDao {

    List<Map<String, Object>> getSearch(String kw, Integer page, Integer size);

    Integer getSearchCount(String kw);
}
