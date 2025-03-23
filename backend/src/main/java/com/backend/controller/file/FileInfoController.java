package com.backend.controller;

import java.util.List;

import com.backend.controller.common.ABaseController;
import com.backend.entity.query.FileInfoQuery;
import com.backend.entity.po.FileInfo;
import com.backend.entity.vo.ResponseVO;
import com.backend.service.FileInfoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  Controller
 */
@RestController("fileInfoController")
@RequestMapping("/fileInfo")
public class FileInfoController extends ABaseController {

	@Resource
	private FileInfoService fileInfoService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("/loadDataList")
	public ResponseVO loadDataList(FileInfoQuery query){
		return getSuccessResponseVO(fileInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public ResponseVO add(FileInfo bean) {
		fileInfoService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("/addBatch")
	public ResponseVO addBatch(@RequestBody List<FileInfo> listBean) {
		fileInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<FileInfo> listBean) {
		fileInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据FileId查询对象
	 */
	@RequestMapping("/getFileInfoByFileId")
	public ResponseVO getFileInfoByFileId(String fileId) {
		return getSuccessResponseVO(fileInfoService.getFileInfoByFileId(fileId));
	}

	/**
	 * 根据FileId修改对象
	 */
	@RequestMapping("/updateFileInfoByFileId")
	public ResponseVO updateFileInfoByFileId(FileInfo bean,String fileId) {
		fileInfoService.updateFileInfoByFileId(bean,fileId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据FileId删除
	 */
	@RequestMapping("/deleteFileInfoByFileId")
	public ResponseVO deleteFileInfoByFileId(String fileId) {
		fileInfoService.deleteFileInfoByFileId(fileId);
		return getSuccessResponseVO(null);
	}
}