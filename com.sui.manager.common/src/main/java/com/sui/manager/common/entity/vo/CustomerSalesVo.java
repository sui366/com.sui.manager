package com.sui.manager.common.entity.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.sui.manager.common.entity.po.CustomerSales;

public class CustomerSalesVo extends CustomerSales {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String saleDateStr;
	private String signDateStr;
	private String sendDateStr;
	private String isBillStr;
	
	private String customerName;

	public String getSaleDateStr() {
		if(null != getSaleDate()){
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			setSaleDateStr(dateformat.format(getSaleDate()));
		}
		return saleDateStr;
	}

	public void setSaleDateStr(String saleDateStr) {
		this.saleDateStr = saleDateStr;
	}

	public String getSignDateStr() {
		if(null != getSignDate()){
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			setSignDateStr(dateformat.format(getSignDate()));
		}
		return signDateStr;
	}

	public void setSignDateStr(String signDateStr) {
		this.signDateStr = signDateStr;
	}

	public String getSendDateStr() {
		if(null != getSendDate()){
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			setSendDateStr(dateformat.format(getSendDate()));
		}
		return sendDateStr;
	}

	public void setSendDateStr(String sendDateStr) {
		this.sendDateStr = sendDateStr;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getIsBillStr() {
		if(null == getIsBill()){
			return "未开";
		}
		if(1 == getIsBill()){
			return "已开";
		}else {
			return "未开";
		}
	}

	public void setIsBillStr(String isBillStr) {
		this.isBillStr = isBillStr;
	}

}