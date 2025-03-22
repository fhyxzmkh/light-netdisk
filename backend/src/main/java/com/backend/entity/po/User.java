package com.backend.entity.po;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("user_info")
public class User implements Serializable {

	/**
	 * 用户ID
	 */
	@TableId(type = IdType.AUTO)
	private Integer userId;

	/**
	 * 用户昵称
	 */
	private String username;

	/**
	 * 用户头像
	 */
	private String avatar;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 注册时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date registerTime;

	/**
	 * 上次登录时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;

	/**
	 * 0禁用 1启用
	 */
	private Integer status;

	/**
	 * 已使用空间 单位字节
	 */
	private Long useSpace;

	/**
	 * 总空间 单位字节
	 */
	private Long totalSpace;

	/**
	 * 0普通用户 1管理员
	 */
	private Integer type;

}
