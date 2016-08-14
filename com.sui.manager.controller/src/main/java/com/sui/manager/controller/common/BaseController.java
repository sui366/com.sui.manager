package com.sui.manager.controller.common;

import java.io.Serializable;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.shunwang.business.framework.bo.CrudBo;
import com.shunwang.business.framework.dao.CrudDao;
import com.shunwang.business.framework.pojo.BasePojo;
import com.shunwang.business.framework.spring.mvc.controller.CrudController;
import com.sui.manager.common.business.DateEditor;

@SuppressWarnings("serial")
public abstract class BaseController<Pojo extends BasePojo, Bo extends CrudBo<Pojo, ? extends CrudDao<Pojo>>> extends CrudController<Pojo, Bo>  implements Serializable{

	/** 记录日志的接口. */
	protected Logger logger = Logger.getLogger(getClass());

	/**
	 * 
	 */
	public BaseController() {
		super();
	}
	
	
	@InitBinder  
    protected void initBinder(WebDataBinder binder) {  
		binder.registerCustomEditor(Date.class, new DateEditor());  
    }
	
	
}