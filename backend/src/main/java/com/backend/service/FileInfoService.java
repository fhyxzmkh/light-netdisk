package com.backend.service;

import java.util.List;

import com.backend.entity.query.FileInfoQuery;
import com.backend.entity.po.FileInfo;
import com.backend.entity.vo.PaginationResultVO;


/**
 *  业务接口
 */
public interface FileInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<FileInfo> findListByParam(FileInfoQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(FileInfoQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<FileInfo> findListByPage(FileInfoQuery param);

	/**
	 * 新增
	 */
	Integer add(FileInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<FileInfo> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<FileInfo> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(FileInfo bean,FileInfoQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(FileInfoQuery param);

	/**
	 * 根据FileId查询对象
	 */
	FileInfo getFileInfoByFileId(Integer fileId);


	/**
	 * 根据FileId修改
	 */
	Integer updateFileInfoByFileId(FileInfo bean,Integer fileId);


	/**
	 * 根据FileId删除
	 */
	Integer deleteFileInfoByFileId(Integer fileId);

}