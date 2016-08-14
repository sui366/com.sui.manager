package com.sui.manager.service;

import org.springframework.stereotype.Service;

import com.shunwang.business.framework.bo.CrudBo;
import com.sui.manager.common.entity.po.CustomerContract;
import com.sui.manager.dao.CustomerContractDao;

@Service
public class CustomerContractService extends CrudBo<CustomerContract, CustomerContractDao> {

}
