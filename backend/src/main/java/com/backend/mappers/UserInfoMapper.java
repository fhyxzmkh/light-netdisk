package com.backend.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *  数据库操作接口
 */
@Mapper
public interface UserInfoMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据UserId更新
	 */
	 Integer updateByUserId(@Param("bean") T t,@Param("userId") Integer userId);


	/**
	 * 根据UserId删除
	 */
	 Integer deleteByUserId(@Param("userId") Integer userId);


	/**
	 * 根据UserId获取对象
	 */
	 T selectByUserId(@Param("userId") Integer userId);


	/**
	 * 根据Username更新
	 */
	 Integer updateByUsername(@Param("bean") T t,@Param("username") String username);


	/**
	 * 根据Username删除
	 */
	 Integer deleteByUsername(@Param("username") String username);


	/**
	 * 根据Username获取对象
	 */
	 T selectByUsername(@Param("username") String username);


	 Integer updateUserSpace(@Param("userId") Integer userId, @Param("useSpace") Long useSpace, @Param("totalSpace") Long totalSpace);


}
