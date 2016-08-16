package com.sui.manager.common.entity.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.sui.manager.common.entity.po.CustomerContract;

public class CustomerContractVo extends CustomerContract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String birthdayStr;
	
	private String customerName;

	public String getBirthdayStr() {
		if(null != getBirthday()){
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			setBirthdayStr(dateformat.format(getBirthday()));
		}
		return birthdayStr;
	}

	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	

}