package com.backend.service;

import java.util.List;

import com.backend.entity.po.User;
import com.backend.entity.query.UserInfoQuery;
import com.backend.entity.vo.PaginationResultVO;


/**
 *  业务接口
 */
public interface UserInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<User> findListByParam(UserInfoQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(UserInfoQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<User> findListByPage(UserInfoQuery param);

	/**
	 * 新增
	 */
	Integer add(User bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<User> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<User> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(User bean,UserInfoQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(UserInfoQuery param);

	/**
	 * 根据UserId查询对象
	 */
	User getUserInfoByUserId(Integer userId);


	/**
	 * 根据UserId修改
	 */
	Integer updateUserInfoByUserId(User bean,Integer userId);


	/**
	 * 根据UserId删除
	 */
	Integer deleteUserInfoByUserId(Integer userId);


	/**
	 * 根据Username查询对象
	 */
	User getUserInfoByUsername(String username);


	/**
	 * 根据Username修改
	 */
	Integer updateUserInfoByUsername(User bean,String username);


	/**
	 * 根据Username删除
	 */
	Integer deleteUserInfoByUsername(String username);

}