package com.sui.manager.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shunwang.business.framework.spring.mvc.controller.CrudController;
import com.sui.manager.common.business.Result;
import com.sui.manager.common.entity.po.SysUser;
import com.sui.manager.common.util.excel.ReadExcelUtils;
import com.sui.manager.service.SysUserService;

@Controller
public class HomeController extends CrudController<SysUser, SysUserService> {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private ReadExcelUtils readExcelUtils;
	
	@RequestMapping("/homePage.shtml")
	public ModelAndView homePage(HttpServletRequest request) throws Exception{
		Result result = new Result(request);
		
		return new ModelAndView("welcome").addObject("result", result);
	}
	
	@RequestMapping("/sysLogout.shtml")
	public ModelAndView sysLogout(HttpServletRequest request) throws Exception{
		request.getSession().invalidate();
		request.getSession().setMaxInactiveInterval(0);
//		Result result = new Result(request);
		
		return new ModelAndView("welcome");
	}
	
	/**
	 * 跳到更新页面
	 */
	@RequestMapping("/layout/constants.shtml")
	public ModelAndView updatePage(HttpServletRequest request) throws Exception{
		Result result = new Result(request);
		
		return new ModelAndView("layout/constants").addObject("result", result);
	}

	/**
	 * 重新加载系统参数
	 */
	@RequestMapping("/homePage/loadConstant")
	@ResponseBody
	public Result loadConstant() throws Exception {
		Result result = new Result();
		try {
			readExcelUtils.buildConstants();
		} catch (Exception e) {
			result.setFailureMsg("", e.getMessage());
			logger.error(e);
		}
		return result;
	}
}
