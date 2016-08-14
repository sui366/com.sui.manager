package com.sui.manager.service;

import org.springframework.stereotype.Service;

import com.sui.manager.common.entity.po.CustomerService;
import com.sui.manager.dao.CustomerServiceDao;
import com.shunwang.business.framework.bo.CrudBo;

@Service
public class CustomerServiceService extends CrudBo<CustomerService, CustomerServiceDao> {

}
