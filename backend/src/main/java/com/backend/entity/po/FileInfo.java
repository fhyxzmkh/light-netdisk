package com.backend.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import com.backend.entity.enums.DateTimePatternEnum;
import com.backend.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("file_info")
public class FileInfo implements Serializable {

	/**
	 * 文件ID
	 */
	private Integer fileId;

	/**
	 * 用户ID
	 */
	private Integer userId;

	/**
	 * 文件md5值
	 */
	private String fileMd5;

	/**
	 * 父级ID
	 */
	private Integer filePid;

	/**
	 * 文件大小
	 */
	private Long fileSize;

	/**
	 * 文件名
	 */
	private String fileName;

	/**
	 * 封面
	 */
	private String fileCover;

	/**
	 * 文件路径
	 */
	private String filePath;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 最后更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateTime;

	/**
	 * 0文件，1目录
	 */
	private Integer folderType;

	/**
	 * 文件分类：1视频 2音频 3图片 4文档 5其他
	 */
	private Integer fileCategory;

	/**
	 * 1视频 2音频 3图片 4pdf 5doc 6excel 7txt 8code 9其他
	 */
	private Integer fileType;

	/**
	 * 0转码中 1转码失败 2转码成功
	 */
	private Integer status;

	/**
	 * 0删除 1回收站 2正常
	 */
	private Integer delFlag;

	/**
	 * 进入回收站时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date recoveryTime;

}
