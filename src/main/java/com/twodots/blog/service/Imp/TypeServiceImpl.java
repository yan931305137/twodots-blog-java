package com.twodots.blog.service.Imp;

import com.twodots.blog.dao.TypeDao;
import com.twodots.blog.entity.Types;
import com.twodots.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * TypyServiceImpl
 * @ date 2024/7/5 23:52
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeDao typeDao;

    @Override
    public List<Map<String, String>> getType() {
        return typeDao.getType();
    }

    @Override
    public void postType(Types types) {
        typeDao.postType(types);
    }

    @Override
    public void deleteType(Integer id) {
        typeDao.deleteType(id);

    }

    @Override
    public void putType(Types types) {
        typeDao.putType(types);
    }


    @Override
    public List<Map<String, Object>> getTagByBlogId(Integer id) {
        return typeDao.getTagByBlogId(id);
    }

    @Override
    public List<Map<String, Object>> getBlogByTag(Integer id) {
        return typeDao.getBlogByTag(id);
    }

    @Override
    public List<Map<String, Object>> getAllTypeTagByPage(Integer page, Integer size, Integer pageSize) {
        List<Map<String, Object>> list = typeDao.getAllTypeTagByPage((page-1)*pageSize, pageSize);
        return list;
    }

    @Override
    public Integer getTypeCount() {
        return typeDao.getTypeCount();
    }

    @Override
    public List<Map<String, Object>> getAllTypeTag() {
        return typeDao.getAllTypeTag();
    }


}
