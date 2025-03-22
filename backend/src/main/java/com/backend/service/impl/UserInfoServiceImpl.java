package com.backend.service.impl;

import java.util.List;

import com.backend.entity.po.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.enums.PageSize;
import com.backend.entity.query.UserInfoQuery;
import com.backend.entity.vo.PaginationResultVO;
import com.backend.entity.query.SimplePage;
import com.backend.mappers.UserInfoMapper;
import com.backend.service.UserInfoService;
import com.backend.utils.StringTools;


/**
 *  业务接口实现
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Resource
	private UserInfoMapper<User, UserInfoQuery> userInfoMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<User> findListByParam(UserInfoQuery param) {
		return this.userInfoMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(UserInfoQuery param) {
		return this.userInfoMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<User> findListByPage(UserInfoQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<User> list = this.findListByParam(param);
		PaginationResultVO<User> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(User bean) {
		return this.userInfoMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<User> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userInfoMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<User> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userInfoMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(User bean, UserInfoQuery param) {
		StringTools.checkParam(param);
		return this.userInfoMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(UserInfoQuery param) {
		StringTools.checkParam(param);
		return this.userInfoMapper.deleteByParam(param);
	}

	/**
	 * 根据UserId获取对象
	 */
	@Override
	public User getUserInfoByUserId(Integer userId) {
		return this.userInfoMapper.selectByUserId(userId);
	}

	/**
	 * 根据UserId修改
	 */
	@Override
	public Integer updateUserInfoByUserId(User bean, Integer userId) {
		return this.userInfoMapper.updateByUserId(bean, userId);
	}

	/**
	 * 根据UserId删除
	 */
	@Override
	public Integer deleteUserInfoByUserId(Integer userId) {
		return this.userInfoMapper.deleteByUserId(userId);
	}

	/**
	 * 根据Username获取对象
	 */
	@Override
	public User getUserInfoByUsername(String username) {
		return this.userInfoMapper.selectByUsername(username);
	}

	/**
	 * 根据Username修改
	 */
	@Override
	public Integer updateUserInfoByUsername(User bean, String username) {
		return this.userInfoMapper.updateByUsername(bean, username);
	}

	/**
	 * 根据Username删除
	 */
	@Override
	public Integer deleteUserInfoByUsername(String username) {
		return this.userInfoMapper.deleteByUsername(username);
	}
}