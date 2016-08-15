package com.sui.manager.common.util.excel;

import java.text.NumberFormat;

import org.apache.commons.lang3.StringUtils;


public class NumberUtils {

	/**
	 * 格式化double,按照四舍五入，小数位数由参数fractionDigits控制
	 * 
	 * @param source
	 * @param fractionDigits
	 * 			小数位数
	 * @return 格式化字符串 例如:3.233
	 */
	public static String format(Double source, int fractionDigits) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(fractionDigits);
		nf.setMinimumFractionDigits(fractionDigits);
		return nf.format(source);
	}
	
	/**
	 * 格式化double,按照四舍五入，小数位数由参数fractionDigits控制
	 * 
	 * @param source
	 * @param fractionDigits
	 * 			小数位数
	 * @return 格式化字符串 例如:3.233
	 */
	public static Double formatDouble(Double source, int fractionDigits) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(fractionDigits);
		nf.setMinimumFractionDigits(fractionDigits);
		return Double.parseDouble(nf.format(source).replaceAll(",", ""));
	}
	
	
	/**
	 * 格式化double,按照四舍五入，小数位数由参数fractionDigits控制
	 * 
	 * @param source
	 * @param fractionDigits
	 * 			小数位数
	 * @return 格式化字符串 例如:3.233
	 */
	public static String format(Long source, int fractionDigits) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(fractionDigits);
		nf.setMinimumFractionDigits(fractionDigits);
		return nf.format(source);
	}
	/**
	 * source:3,4.00,569.8均为整数
	 * 4.1不是整数
	 * @return
	 */
	public static boolean isLong(String source){
		try {
			if (!org.apache.commons.lang3.math.NumberUtils.isNumber(source)) {
				return false;
			}
			double d = Double.parseDouble(source);
			if (d == Double.parseDouble(clearFractionZero(source))) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
			return false;
	}
	
	/**
	 * 
	* @package: com.skymobi.common.utils
	* @Title: NumberUtils.java   function: clearPointZore 
	* @Description: 如果小数点后面全是0，则返回整数部分
	* @param doubleNum
	* @return 如果小数点后面全是0，则返回整数部分
	*
	 */
	public static String clearPointZore(String doubleNum){
		int index = doubleNum.lastIndexOf(".");
		String pointNumStr = doubleNum.substring(index+1);
		Integer m = Integer.parseInt(pointNumStr);
		if(m>0){
			return doubleNum;
		}else if(m==0){
			return doubleNum.substring(0, index);
		}else
			return doubleNum;
	}
	
	/**
	 * 获取整数部分
	 * @return
	 */
	public static String clearFractionZero(String source){
		if(StringUtils.isBlank(source)){
			return source;
		}
		if(source.indexOf(".")>=0){
			source = source.substring(0,source.lastIndexOf("."));
		}
		return source;
	}
	
	public static void main(String args[]){
		System.out.println(org.apache.commons.lang3.math.NumberUtils.isDigits("0.067"));
//		String result = format(3.95D,0);
//		System.out.println(result);
		System.out.println(isLong(null));
		System.out.println(clearFractionZero("2011050"));
	}
	
}
