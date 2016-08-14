package com.sui.manager.common.entity.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.sui.manager.common.entity.po.CustomerInfo;

public class CustomerInfoVo extends CustomerInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String connectTimeStr;
	private String lastConnectTimeStr;

	public String getConnectTimeStr() {
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		setConnectTimeStr(dateformat.format(getConnectTime()));		
		return connectTimeStr;
	}

	public void setConnectTimeStr(String connectTimeStr) {
		this.connectTimeStr = connectTimeStr;
	}

	public String getLastConnectTimeStr() {
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		setLastConnectTimeStr(dateformat.format(getLastConnectTime()));
		return lastConnectTimeStr;
	}

	public void setLastConnectTimeStr(String lastConnectTimeStr) {
		this.lastConnectTimeStr = lastConnectTimeStr;
	}

}