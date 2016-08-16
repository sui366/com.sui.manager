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
import com.shunwang.business.framework.spring.mvc.controller.CrudController;
import com.sui.manager.common.business.Result;
import com.sui.manager.common.constant.Constants;
import com.sui.manager.common.entity.po.CustomerContract;
import com.sui.manager.common.entity.po.CustomerInfo;
import com.sui.manager.common.entity.qo.CustomerContractQo;
import com.sui.manager.common.entity.qo.CustomerInfoQo;
import com.sui.manager.common.entity.vo.CustomerContractVo;
import com.sui.manager.service.CustomerContractService;
import com.sui.manager.service.CustomerInfoService;

@Controller
public class CustomerContractController extends CrudController<CustomerContract, CustomerContractService> {

	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private CustomerInfoService customerInfoService;

	/**
	 * 列表
	 */
	@RequestMapping("/customer/contract/list.shtml")
	public ModelAndView list(CustomerContractQo query, HttpServletRequest request) throws Exception {
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

		return new ModelAndView("customer/contract-list").addObject("result", result);
	}

	/**
	 * table数据
	 */
	@RequestMapping("/customer/contract/tableData")
	@ResponseBody
	public Object tableData(CustomerContractQo query, HttpServletRequest request) throws Exception {
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
		List<CustomerContractVo> pageList = (List<CustomerContractVo>) page.getRows();

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
	@RequestMapping("/customer/contract/updatePage.shtml")
	public ModelAndView updatePage(CustomerContractQo query, HttpServletRequest request) throws Exception {
		Result result = new Result();

		if (query.getId() != null) {

			CustomerContract po = bo.get(query.getId());
			CustomerContractVo vo = new CustomerContractVo();
			BeanUtils.copyProperties(vo, po);
			result.setValue("obj", vo);
		}
		
		CustomerInfoQo infoQo = new CustomerInfoQo();
		infoQo.setRp(999);
		List<CustomerInfo> infoList = customerInfoService.query(infoQo);
		result.setValue("infoList", infoList);
		
		result.setValue("contractSex", Constants.CONSTANTSMAP.get(Constants.SEX));

		return new ModelAndView("customer/contract-update").addObject("result", result);
	}

	/**
	 * 更新操作
	 */
	@RequestMapping("/customer/contract/update")
	@ResponseBody
	public Result update(CustomerContract po, HttpServletRequest request) throws Exception {
		Result result = new Result();

		CustomerContractQo qo = new CustomerContractQo();

		if (po.getId() != null) {
			bo.update(po);
		} else {
			bo.save(po);
		}

		return result;
	}

	/**
	 * 删除操作
	 */
	@RequestMapping("/customer/contract/delete")
	@ResponseBody
	public Result monitorConfigDelete(CustomerContractQo qo, HttpServletRequest request) throws Exception {
		Result result = new Result();

		if (null == qo.getId()) {
			result.setFailureMsg("", "请选择要删除的数据");
			return result;
		}

		CustomerContract po = bo.get(qo.getId());
		if (null == po) {
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
	/**
	 * 获取客户联系人
	 */
	@RequestMapping("/customer/contract/info")
	@ResponseBody
	public Result contractInfo(CustomerContractQo qo, HttpServletRequest request) throws Exception {
		Result result = new Result();
		
		if (null == qo.getCustomerId()) {
			return result;
		}
		
		qo.setRp(999);
		List<CustomerContract> list = bo.query(qo);
		
		result.setValue("list", list);
		
		return result;
	}
}
