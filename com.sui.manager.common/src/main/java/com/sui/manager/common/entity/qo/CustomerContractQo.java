package com.sui.manager.common.entity.qo;

import org.apache.commons.lang3.StringUtils;

import com.shunwang.business.framework.mybatis.annotion.SingleValue;


public class CustomerContractQo extends PageQo {
	private Integer id;

	private String name;
	
	private Integer customerId;
	
	private String customerName;

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

	@SingleValue(column = "customer_id", equal = "=")
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	
}