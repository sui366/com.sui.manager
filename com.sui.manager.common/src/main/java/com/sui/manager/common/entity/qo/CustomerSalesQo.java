package com.sui.manager.common.entity.qo;

import com.shunwang.business.framework.mybatis.annotion.SingleValue;


public class CustomerSalesQo extends PageQo {
	private Integer id;
	
	private String customerName;

	@SingleValue(column = "id", equal = "=")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	

}