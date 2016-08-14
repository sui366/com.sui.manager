package com.sui.manager.common.util;

import java.util.Date;

import com.shunwang.business.framework.util.DateUtil;

/**
 * @date   2016-05-12
 * @author xf.sui
 * @dscription velocity用参数
 *
 */
public final class VelocityUtil {

	public static String ymdhmsFormat(Date date){
		return DateUtil.ymdhmsFormat(date);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
