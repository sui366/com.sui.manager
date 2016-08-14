package com.sui.manager.service;

import org.springframework.stereotype.Service;

import com.sui.manager.common.entity.po.SysUser;
import com.sui.manager.dao.SysUserDao;
import com.shunwang.business.framework.bo.CrudBo;

@Service
public class SysUserService extends CrudBo<SysUser, SysUserDao> {

}
