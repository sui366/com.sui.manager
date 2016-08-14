package com.sui.manager.service;

import org.springframework.stereotype.Service;

import com.sui.manager.common.entity.po.CustomerSales;
import com.sui.manager.dao.CustomerSalesDao;
import com.shunwang.business.framework.bo.CrudBo;

@Service
public class CustomerSalesService extends CrudBo<CustomerSales, CustomerSalesDao> {

}
