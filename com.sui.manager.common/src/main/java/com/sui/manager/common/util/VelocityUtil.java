package com.sui.manager.common.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import com.shunwang.business.framework.util.DateUtil;
import com.sui.manager.common.constant.Constants;
import com.sui.manager.common.entity.po.SysUser;

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
	
	public static String currentUserName(HttpServletRequest request) {
		SysUser sysUser = null;
		try {
			sysUser = (SysUser)request.getSession().getAttribute(Constants.LOGIN_USER);
		} catch (Exception e) {
			return null;
		}
		if(sysUser != null){
			return sysUser.getLoginName();
		}
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.err.println(DigestUtils.md5Hex("123456"));
	}

}
