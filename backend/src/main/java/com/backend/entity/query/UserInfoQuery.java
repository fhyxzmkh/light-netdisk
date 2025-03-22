package com.backend.entity.query;

import java.util.Date;


/**
 * 参数
 */
public class UserInfoQuery extends BaseParam {


	/**
	 * 用户ID
	 */
	private Integer userId;

	/**
	 * 用户昵称
	 */
	private String username;

	private String usernameFuzzy;

	/**
	 * 用户头像
	 */
	private String avatar;

	private String avatarFuzzy;

	/**
	 * 密码
	 */
	private String password;

	private String passwordFuzzy;

	/**
	 * 注册时间
	 */
	private String registerTime;

	private String registerTimeStart;

	private String registerTimeEnd;

	/**
	 * 上次登录时间
	 */
	private String lastLoginTime;

	private String lastLoginTimeStart;

	private String lastLoginTimeEnd;

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


	public void setUserId(Integer userId){
		this.userId = userId;
	}

	public Integer getUserId(){
		return this.userId;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return this.username;
	}

	public void setUsernameFuzzy(String usernameFuzzy){
		this.usernameFuzzy = usernameFuzzy;
	}

	public String getUsernameFuzzy(){
		return this.usernameFuzzy;
	}

	public void setAvatar(String avatar){
		this.avatar = avatar;
	}

	public String getAvatar(){
		return this.avatar;
	}

	public void setAvatarFuzzy(String avatarFuzzy){
		this.avatarFuzzy = avatarFuzzy;
	}

	public String getAvatarFuzzy(){
		return this.avatarFuzzy;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return this.password;
	}

	public void setPasswordFuzzy(String passwordFuzzy){
		this.passwordFuzzy = passwordFuzzy;
	}

	public String getPasswordFuzzy(){
		return this.passwordFuzzy;
	}

	public void setRegisterTime(String registerTime){
		this.registerTime = registerTime;
	}

	public String getRegisterTime(){
		return this.registerTime;
	}

	public void setRegisterTimeStart(String registerTimeStart){
		this.registerTimeStart = registerTimeStart;
	}

	public String getRegisterTimeStart(){
		return this.registerTimeStart;
	}
	public void setRegisterTimeEnd(String registerTimeEnd){
		this.registerTimeEnd = registerTimeEnd;
	}

	public String getRegisterTimeEnd(){
		return this.registerTimeEnd;
	}

	public void setLastLoginTime(String lastLoginTime){
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginTime(){
		return this.lastLoginTime;
	}

	public void setLastLoginTimeStart(String lastLoginTimeStart){
		this.lastLoginTimeStart = lastLoginTimeStart;
	}

	public String getLastLoginTimeStart(){
		return this.lastLoginTimeStart;
	}
	public void setLastLoginTimeEnd(String lastLoginTimeEnd){
		this.lastLoginTimeEnd = lastLoginTimeEnd;
	}

	public String getLastLoginTimeEnd(){
		return this.lastLoginTimeEnd;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setUseSpace(Long useSpace){
		this.useSpace = useSpace;
	}

	public Long getUseSpace(){
		return this.useSpace;
	}

	public void setTotalSpace(Long totalSpace){
		this.totalSpace = totalSpace;
	}

	public Long getTotalSpace(){
		return this.totalSpace;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public Integer getType(){
		return this.type;
	}

}
