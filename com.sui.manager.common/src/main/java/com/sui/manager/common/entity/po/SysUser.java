package com.sui.manager.common.entity.po;

import com.shunwang.business.framework.pojo.BasePojo;
import java.sql.Timestamp;

public class SysUser extends BasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String dept;
	private String officePhone;
	private String mobilePhone;
	private String email;
	private String remark;
	private String insertUser;
	private Timestamp insertTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	public Timestamp getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}

	@Override
	public String toString() {
		return "SysUser "+ 
				"[id=" + id +
				", name=" + name + 
				", dept=" + dept + 
				", officePhone=" + officePhone + 
				", mobilePhone=" + mobilePhone + 
				", email=" + email + 
				", remark=" + remark + 
				", insertUser=" + insertUser + 
				", insertTime=" + insertTime + 
		"]";
	}

}