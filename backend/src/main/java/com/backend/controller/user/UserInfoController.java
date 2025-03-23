package com.backend.controller.user;

import java.util.List;

import com.backend.controller.common.ABaseController;
import com.backend.entity.po.User;
import com.backend.entity.query.UserInfoQuery;
import com.backend.entity.vo.ResponseVO;
import com.backend.service.UserInfoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *  Controller
 */
@RestController("userInfoController")
@RequestMapping("/userInfo")
public class UserInfoController extends ABaseController {

	@Resource
	private UserInfoService userInfoService;

	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("/loadDataList")
	public ResponseVO loadDataList(UserInfoQuery query){
		return getSuccessResponseVO(userInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public ResponseVO add(User bean) {
		userInfoService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("/addBatch")
	public ResponseVO addBatch(@RequestBody List<User> listBean) {
		userInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<User> listBean) {
		userInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据UserId查询对象
	 */
	@RequestMapping("/getUserInfoByUserId")
	public ResponseVO getUserInfoByUserId(Integer userId) {
		return getSuccessResponseVO(userInfoService.getUserInfoByUserId(userId));
	}

	/**
	 * 根据UserId修改对象
	 */
	@RequestMapping("/updateUserInfoByUserId")
	public ResponseVO updateUserInfoByUserId(User bean,Integer userId) {
		userInfoService.updateUserInfoByUserId(bean,userId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据UserId删除
	 */
	@RequestMapping("/deleteUserInfoByUserId")
	public ResponseVO deleteUserInfoByUserId(Integer userId) {
		userInfoService.deleteUserInfoByUserId(userId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Username查询对象
	 */
	@RequestMapping("/getUserInfoByUsername")
	public ResponseVO getUserInfoByUsername(String username) {
		return getSuccessResponseVO(userInfoService.getUserInfoByUsername(username));
	}

	/**
	 * 根据Username修改对象
	 */
	@RequestMapping("/updateUserInfoByUsername")
	public ResponseVO updateUserInfoByUsername(User bean,String username) {
		userInfoService.updateUserInfoByUsername(bean,username);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Username删除
	 */
	@RequestMapping("/deleteUserInfoByUsername")
	public ResponseVO deleteUserInfoByUsername(String username) {
		userInfoService.deleteUserInfoByUsername(username);
		return getSuccessResponseVO(null);
	}
}