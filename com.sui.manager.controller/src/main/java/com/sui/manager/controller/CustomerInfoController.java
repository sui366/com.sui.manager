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
import com.sui.manager.common.business.Result;
import com.sui.manager.common.entity.po.CustomerInfo;
import com.sui.manager.common.entity.qo.CustomerInfoQo;
import com.sui.manager.common.entity.vo.CustomerInfoVo;
import com.sui.manager.controller.common.BaseController;
import com.sui.manager.service.CustomerInfoService;

@Controller
public class CustomerInfoController extends BaseController<CustomerInfo, CustomerInfoService> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8989911612366040457L;
	
	private Logger logger = Logger.getLogger(getClass());

	/**
	 * 监控列表
	 */
	@RequestMapping("/customer/info/list.shtml")
	public ModelAndView list(CustomerInfoQo query, HttpServletRequest request) throws Exception {
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

		return new ModelAndView("customer/info-list").addObject("result", result);
	}

	/**
	 * table数据
	 */
	@RequestMapping("/customer/info/tableData")
	@ResponseBody
	public Object tableData(CustomerInfoQo query, HttpServletRequest request) throws Exception {
		List<Condition> conditions = new ArrayList<Condition>();
		Page page = null;
		try {
			page = bo.list(query, conditions);
		} catch (Exception e) {
			logger.error("获取列表数据异常", e);
		}
		List<Object> list = new ArrayList<Object>();

		@SuppressWarnings("unchecked")
		List<CustomerInfoVo> pageList = (List<CustomerInfoVo>) page.getRows();

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
	@RequestMapping("/customer/info/updatePage.shtml")
	public ModelAndView updatePage(CustomerInfoQo query, HttpServletRequest request) throws Exception{
		Result result = new Result();
		
		if(query.getId() != null){
			
			CustomerInfo po = bo.get(query.getId());
			CustomerInfoVo vo = new CustomerInfoVo();
			BeanUtils.copyProperties(vo, po);
			result.setValue("obj", vo);
		}
		
		return new ModelAndView("customer/info-update").addObject("result", result);
	}
	
	/**
	 * 更新操作
	 */
	@RequestMapping("/customer/info/update")
	@ResponseBody
	public Result update(CustomerInfo po, HttpServletRequest request) throws Exception{
		Result result = new Result();
		
		CustomerInfoQo qo = new CustomerInfoQo();
		
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
	@RequestMapping("/customer/info/delete")
	@ResponseBody
	public Result monitorConfigDelete(CustomerInfoQo qo, HttpServletRequest request)  throws Exception{
		Result result = new Result();
		
		if(null == qo.getId()){
			result.setFailureMsg("", "请选择要删除的数据");
			return result;
		}
		
		CustomerInfo po = bo.get(qo.getId());
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
	
//	@InitBinder  
//    protected void initBinder(HttpServletRequest request,  
//            ServletRequestDataBinder binder) throws Exception {  
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
//            CustomDateEditor editor = new CustomDateEditor(df, false);  
//            binder.registerCustomEditor(Date.class, editor);  
//    }  
}
