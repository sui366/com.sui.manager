package com.sui.manager.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shunwang.business.framework.spring.mvc.controller.CrudController;
import com.sui.manager.common.business.Result;
import com.sui.manager.common.entity.po.SysUser;
import com.sui.manager.service.SysUserService;

@Controller
public class HomeController extends CrudController<SysUser, SysUserService> {
	
	
	@RequestMapping("/homePage.shtml")
	public ModelAndView homePage(HttpServletRequest request) throws Exception{
		Result result = new Result(request);

		
		return new ModelAndView("welcome").addObject("result", result);
	}
	
}
