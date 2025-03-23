package com.backend.service.impl;

import java.util.List;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.backend.entity.enums.PageSize;
import com.backend.entity.query.FileInfoQuery;
import com.backend.entity.po.FileInfo;
import com.backend.entity.vo.PaginationResultVO;
import com.backend.entity.query.SimplePage;
import com.backend.mappers.FileInfoMapper;
import com.backend.service.FileInfoService;
import com.backend.utils.StringTools;


/**
 *  业务接口实现
 */
@Service("fileInfoService")
public class FileInfoServiceImpl implements FileInfoService {

	@Resource
	private FileInfoMapper<FileInfo, FileInfoQuery> fileInfoMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<FileInfo> findListByParam(FileInfoQuery param) {
		return this.fileInfoMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(FileInfoQuery param) {
		return this.fileInfoMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<FileInfo> findListByPage(FileInfoQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<FileInfo> list = this.findListByParam(param);
		PaginationResultVO<FileInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(FileInfo bean) {
		return this.fileInfoMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<FileInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.fileInfoMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<FileInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.fileInfoMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(FileInfo bean, FileInfoQuery param) {
		StringTools.checkParam(param);
		return this.fileInfoMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(FileInfoQuery param) {
		StringTools.checkParam(param);
		return this.fileInfoMapper.deleteByParam(param);
	}

	/**
	 * 根据FileId获取对象
	 */
	@Override
	public FileInfo getFileInfoByFileId(String fileId) {
		return this.fileInfoMapper.selectByFileId(fileId);
	}

	/**
	 * 根据FileId修改
	 */
	@Override
	public Integer updateFileInfoByFileId(FileInfo bean, String fileId) {
		return this.fileInfoMapper.updateByFileId(bean, fileId);
	}

	/**
	 * 根据FileId删除
	 */
	@Override
	public Integer deleteFileInfoByFileId(String fileId) {
		return this.fileInfoMapper.deleteByFileId(fileId);
	}
}