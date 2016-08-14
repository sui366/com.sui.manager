package com.sui.manager.common.entity.qo;

import com.shunwang.business.framework.mybatis.annotion.SingleValue;


public class SysUserQo extends PageQo {
	
	private Integer id;

	@SingleValue(column = "id", equal = "=")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}