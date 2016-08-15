package com.sui.manager.common.entity.po;

import java.sql.Date;
import java.sql.Timestamp;

import com.shunwang.business.framework.pojo.BasePojo;

public class CustomerSales extends BasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String goodVersion;
	private Date saleDate;
	private Integer customerId;
	private Integer takeUserName;
	private Integer signUserName;
	private String goodNumber;
	private Date signDate;
	private Date sendDate;
	private String passNumber;
	private String code;
	private String pageNumber;
	private Integer isBill;
	private Double preMoney;
	private Double reciveMoney;
	private Timestamp insertTime;
	private String insertUser;
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getGoodVersion() {
		return goodVersion;
	}

	public void setGoodVersion(String goodVersion) {
		this.goodVersion = goodVersion;
	}
	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getGoodNumber() {
		return goodNumber;
	}

	public void setGoodNumber(String goodNumber) {
		this.goodNumber = goodNumber;
	}
	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getPassNumber() {
		return passNumber;
	}

	public void setPassNumber(String passNumber) {
		this.passNumber = passNumber;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getIsBill() {
		return isBill;
	}

	public void setIsBill(Integer isBill) {
		this.isBill = isBill;
	}
	public Double getPreMoney() {
		return preMoney;
	}

	public void setPreMoney(Double preMoney) {
		this.preMoney = preMoney;
	}
	public Double getReciveMoney() {
		return reciveMoney;
	}

	public void setReciveMoney(Double reciveMoney) {
		this.reciveMoney = reciveMoney;
	}
	public Timestamp getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}
	public String getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getTakeUserName() {
		return takeUserName;
	}

	public void setTakeUserName(Integer takeUserName) {
		this.takeUserName = takeUserName;
	}

	public Integer getSignUserName() {
		return signUserName;
	}

	public void setSignUserName(Integer signUserName) {
		this.signUserName = signUserName;
	}


}