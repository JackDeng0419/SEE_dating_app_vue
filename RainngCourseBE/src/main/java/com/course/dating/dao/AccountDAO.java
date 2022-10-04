package com.course.dating.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.course.dating.mapper.AccountMapper;
import com.course.dating.entity.AccountEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDAO{
    private final AccountMapper mapper;

    public AccountDAO(AccountMapper mapper) {
        this.mapper = mapper;
    }

    public AccountEntity getByUsername(String username) {
        LambdaQueryWrapper<AccountEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AccountEntity::getUsername, username);
        return mapper.selectOne(wrapper);
    }

    public int insert(String username, String password, String mobile_number, String email) {
        return mapper.create_new(username, password, mobile_number, email);
    }

    public int delete(Integer id) {
        return mapper.deleteById(id);
    }

    public AccountEntity get(Integer id) {
        return mapper.selectById(id);
    }

    public int update(AccountEntity entity) {
        return mapper.updateById(entity);
    }

    public List<AccountEntity> list() {
        return mapper.selectList(new LambdaQueryWrapper<>());
    }
}
