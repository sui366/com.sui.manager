/*
 * $Id$
 * Copyright (c)  by iCafeMavin Information Technology Inc. All right reserved.
 */
package com.sui.manager.common.util;



public final class PathHelper {

	/**
	 * 返回应用域名
	 * @return
	 */
	public static String getLocalUrl() {
		return PropertiesUtil.getProperty("local.url");
	}
	
	/**
	 * 返回静态域名
	 * @return
	 */
	public static String getStaticUrl() {
		return getLocalUrl();
//		return PropertiesUtil.getProperty("static.url");
	}
	
	public static String getExcelName() {
		return PropertiesUtil.getProperty("excel.name");
	}
	
	
}
