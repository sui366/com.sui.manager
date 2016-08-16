package com.sui.manager.common.entity.po;

import java.sql.Timestamp;
import java.util.Date;

import com.shunwang.business.framework.pojo.BasePojo;

public class CustomerService extends BasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String title;
	private Integer customerId;
	private Integer contractId;
	private Integer reciveManId;
	private String type;
	private String status;
	private Date feedDate;
	private Date hopeSolveDate;
	private Date visitDate;
	private String solvers;
	private String grade;
	private String content;
	private String result;
	private String insertUser;
	private Timestamp insertTime;
	private String customerName;
	private String contractName;
	private String reciveManName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}
	public Integer getReciveManId() {
		return reciveManId;
	}

	public void setReciveManId(Integer reciveManId) {
		this.reciveManId = reciveManId;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public Date getFeedDate() {
		return feedDate;
	}

	public void setFeedDate(Date feedDate) {
		this.feedDate = feedDate;
	}
	public Date getHopeSolveDate() {
		return hopeSolveDate;
	}

	public void setHopeSolveDate(Date hopeSolveDate) {
		this.hopeSolveDate = hopeSolveDate;
	}
	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	public String getSolvers() {
		return solvers;
	}

	public void setSolvers(String solvers) {
		this.solvers = solvers;
	}
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getReciveManName() {
		return reciveManName;
	}

	public void setReciveManName(String reciveManName) {
		this.reciveManName = reciveManName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}


}