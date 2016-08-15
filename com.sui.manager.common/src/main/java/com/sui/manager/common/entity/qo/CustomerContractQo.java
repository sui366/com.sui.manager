package com.sui.manager.common.entity.qo;

import org.apache.commons.lang3.StringUtils;

import com.shunwang.business.framework.mybatis.annotion.SingleValue;


public class CustomerContractQo extends PageQo {
	private Integer id;

	private String name;

	@SingleValue(column = "id", equal = "=")
	public Integer getId() {
		return id;
	}

	@SingleValue(column = "name", equal = "like")
	public String getName() {
		if(StringUtils.isNotBlank(name)){
			setName("%"+name+"%");
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}