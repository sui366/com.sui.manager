package com.sui.manager.service;

import org.springframework.stereotype.Service;

import com.sui.manager.common.entity.po.CustomerInfo;
import com.sui.manager.dao.CustomerInfoDao;
import com.shunwang.business.framework.bo.CrudBo;

@Service
public class CustomerInfoService extends CrudBo<CustomerInfo, CustomerInfoDao> {

}
