package com.sui.manager.common.entity.vo;

import com.sui.manager.common.entity.po.SysUser;
import com.sui.manager.common.enums.UserStatusEnum;

public class SysUserVo extends SysUser {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String statusStr;

	public String getStatusStr() {
		if(null != getStatus()){
			setStatusStr(UserStatusEnum.getByType(getStatus()).getName());
		}
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	
	

}