package com.sui.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shunwang.business.framework.mybatis.query.condition.Condition;
import com.shunwang.business.framework.pojo.Page;
import com.shunwang.business.framework.spring.mvc.controller.CrudController;
import com.sui.manager.common.business.Result;
import com.sui.manager.common.entity.po.CustomerService;
import com.sui.manager.common.entity.qo.CustomerServiceQo;
import com.sui.manager.common.entity.vo.CustomerSalesVo;
import com.sui.manager.service.CustomerServiceService;

@Controller
public class CustomerServiceController extends CrudController<CustomerService, CustomerServiceService> {
	
	private Logger logger = Logger.getLogger(getClass());

	/**
	 * 列表
	 */
	@RequestMapping("/customer/service/list.shtml")
	public ModelAndView list(CustomerServiceQo query, HttpServletRequest request) throws Exception {
		Result result = new Result(request);
		List<Condition> conditions = new ArrayList<Condition>();
		Page page = null;
		try {
			page = bo.list(query, conditions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.setPage(page, request);

		return new ModelAndView("customer/service-list").addObject("result", result);
	}

	/**
	 * table数据
	 */
	@RequestMapping("/customer/service/tableData")
	@ResponseBody
	public Object tableData(CustomerServiceQo query, HttpServletRequest request) throws Exception {
		List<Condition> conditions = new ArrayList<Condition>();
		Page page = null;
		try {
			page = bo.list(query, conditions);
		} catch (Exception e) {
			logger.error("获取列表数据异常", e);
		}
		List<Object> list = new ArrayList<Object>();

		@SuppressWarnings("unchecked")
		List<CustomerSalesVo> pageList = (List<CustomerSalesVo>) page.getRows();

		list.add(pageList);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", bo.queryCnt(query, conditions));
		list.add(map);

		map.put("rows", page.getRows());

		return map;
	}
	
	/**
	 * 跳到更新页面
	 */
	@RequestMapping("/customer/service/updatePage.shtml")
	public ModelAndView updatePage(CustomerServiceQo query, HttpServletRequest request) throws Exception{
		Result result = new Result();
		
		if(query.getId() != null){
			
			CustomerService po = bo.get(query.getId());
			CustomerSalesVo vo = new CustomerSalesVo();
			BeanUtils.copyProperties(vo, po);
			result.setValue("obj", vo);
		}
		
		return new ModelAndView("customer/service-update").addObject("result", result);
	}
	
	/**
	 * 更新操作
	 */
	@RequestMapping("/customer/service/update")
	@ResponseBody
	public Result update(CustomerService po, HttpServletRequest request) throws Exception{
		Result result = new Result();
		
		CustomerServiceQo qo = new CustomerServiceQo();
		
		if(po.getId() != null){
			bo.update(po);
		}else{
			bo.save(po);
		}
		
		return result;
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping("/customer/service/delete")
	@ResponseBody
	public Result monitorConfigDelete(CustomerServiceQo qo, HttpServletRequest request)  throws Exception{
		Result result = new Result();
		
		if(null == qo.getId()){
			result.setFailureMsg("", "请选择要删除的数据");
			return result;
		}
		
		CustomerService po = bo.get(qo.getId());
		if(null == po){
			result.setFailureMsg("", "删除的数据不存在");
			return result;
		}
		
		try {
			bo.delete(qo.getId());
		} catch (Exception e) {
			logger.error("删除程序类型数据异常", e);
			result.setFailureMsg("", "删除程序类型数据异常");
		}
		
		return result;
	}
}
