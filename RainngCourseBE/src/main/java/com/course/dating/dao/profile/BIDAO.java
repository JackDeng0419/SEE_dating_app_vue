package com.course.dating.dao.profile;

import com.course.dating.entity.profile.BIEntity;
import com.course.dating.mapper.profile.BIMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BIDAO {
    private final BIMapper mapper;

    public BIDAO(BIMapper mapper) {
        this.mapper = mapper;
    }

    public int insert(BIEntity entity) {
        return mapper.insert(entity);
    }

    public int delete(Integer id) {
        return mapper.deleteById(id);
    }

    public BIEntity get(Integer id) {
        return mapper.selectById(id);
    }

    public int update(BIEntity entity) {
        return mapper.updateById(entity);
    }

}
