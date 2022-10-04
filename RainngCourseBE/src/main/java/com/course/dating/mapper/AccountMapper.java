package com.course.dating.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.course.dating.entity.AccountEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper extends BaseMapper<AccountEntity> {
    int create_new(@Param("username") String username, @Param("password")String password, @Param("mobileNumber")String mobileNumber,@Param("email")String email);
}
