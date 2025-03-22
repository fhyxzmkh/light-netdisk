package com.backend.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *  数据库操作接口
 */
@Mapper
public interface FileInfoMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据FileId更新
	 */
	 Integer updateByFileId(@Param("bean") T t,@Param("fileId") Integer fileId);


	/**
	 * 根据FileId删除
	 */
	 Integer deleteByFileId(@Param("fileId") Integer fileId);


	/**
	 * 根据FileId获取对象
	 */
	 T selectByFileId(@Param("fileId") Integer fileId);


}
