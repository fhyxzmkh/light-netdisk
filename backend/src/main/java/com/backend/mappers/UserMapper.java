package com.backend.mappers;

import com.backend.entity.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    Integer updateUserSpace(@Param("userId") Long userId, @Param("useSpace") Long useSpace, @Param("totalSpace") Long totalSpace);

}
