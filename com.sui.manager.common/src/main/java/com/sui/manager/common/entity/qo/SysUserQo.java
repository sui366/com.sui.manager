package com.sui.manager.common.entity.qo;

import com.shunwang.business.framework.mybatis.annotion.SingleValue;


public class SysUserQo extends PageQo {
	
	private Integer id;
	
	private Integer status;
	
	private String name;
	
	private String loginName;
	
	private String password;

	@SingleValue(column = "id", equal = "=")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@SingleValue(column = "status", equal = "=")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@SingleValue(column = "name", equal = "=")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SingleValue(column = "login_name", equal = "=")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@SingleValue(column = "password", equal = "=")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}