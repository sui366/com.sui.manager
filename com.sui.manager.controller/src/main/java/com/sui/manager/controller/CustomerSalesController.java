package com.sui.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shunwang.business.framework.mybatis.query.condition.Condition;
import com.shunwang.business.framework.mybatis.query.condition.ConditionFactory;
import com.shunwang.business.framework.pojo.Page;
import com.sui.manager.common.business.Result;
import com.sui.manager.common.entity.po.CustomerInfo;
import com.sui.manager.common.entity.po.CustomerSales;
import com.sui.manager.common.entity.qo.CustomerInfoQo;
import com.sui.manager.common.entity.qo.CustomerSalesQo;
import com.sui.manager.common.entity.vo.CustomerSalesVo;
import com.sui.manager.controller.common.BaseController;
import com.sui.manager.service.CustomerInfoService;
import com.sui.manager.service.CustomerSalesService;
import com.sui.manager.service.SysUserService;

@Controller
public class CustomerSalesController extends BaseController<CustomerSales, CustomerSalesService> {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -4975488583084983592L;

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private CustomerInfoService customerInfoService;
	/**
	 * 列表
	 */
	@RequestMapping("/customer/sales/list.shtml")
	public ModelAndView list(CustomerSalesQo query, HttpServletRequest request) throws Exception {
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

		return new ModelAndView("customer/sales-list").addObject("result", result);
	}

	/**
	 * table数据
	 */
	@RequestMapping("/customer/sales/tableData")
	@ResponseBody
	public Object tableData(CustomerSalesQo query, HttpServletRequest request) throws Exception {
		List<Condition> conditions = new ArrayList<Condition>();
		
		if(StringUtils.isNotBlank(query.getCustomerName())){
			conditions.add(ConditionFactory.buildSqlCondition("info.name  like '%"+query.getCustomerName()+"%'"));
		}
		
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
	@RequestMapping("/customer/sales/updatePage.shtml")
	public ModelAndView updatePage(CustomerSalesQo query, HttpServletRequest request) throws Exception{
		Result result = new Result();
		
		if(query.getId() != null){
			
			CustomerSales po = bo.get(query.getId());
			CustomerSalesVo vo = new CustomerSalesVo();
			BeanUtils.copyProperties(vo, po);
			result.setValue("obj", vo);
		}
		
//		SysUserQo userQo = new SysUserQo();
//		userQo.setStatus(UserStatusEnum.ON_LINE.getType());
//		List<SysUser> userList = sysUserService.query(userQo);
//		result.setValue("userList", userList);
		
		CustomerInfoQo infoQo = new CustomerInfoQo();
		infoQo.setRp(999);
		List<CustomerInfo> infoList = customerInfoService.query(infoQo);
		result.setValue("infoList", infoList);
		
		return new ModelAndView("customer/sales-update").addObject("result", result);
	}
	
	/**
	 * 更新操作
	 */
	@RequestMapping("/customer/sales/update")
	@ResponseBody
	public Result update(CustomerSales po, HttpServletRequest request) throws Exception{
		Result result = new Result();
		
		CustomerSalesQo qo = new CustomerSalesQo();
		
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
	@RequestMapping("/customer/sales/delete")
	@ResponseBody
	public Result monitorConfigDelete(CustomerSalesQo qo, HttpServletRequest request)  throws Exception{
		Result result = new Result();
		
		if(null == qo.getId()){
			result.setFailureMsg("", "请选择要删除的数据");
			return result;
		}
		
		CustomerSales po = bo.get(qo.getId());
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
