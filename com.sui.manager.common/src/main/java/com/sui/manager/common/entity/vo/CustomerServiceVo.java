package com.sui.manager.common.entity.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.sui.manager.common.entity.po.CustomerService;

public class CustomerServiceVo extends CustomerService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String feedDateStr;
	private String hopeSoleDateStr;
	private String visitDateStr;
	
	
	public String getFeedDateStr() {
		if(null != getFeedDate()){
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			setFeedDateStr(dateformat.format(getFeedDate()));
		}
		return feedDateStr;
	}
	public void setFeedDateStr(String feedDateStr) {
		this.feedDateStr = feedDateStr;
	}
	public String getHopeSoleDateStr() {
		if(null != getHopeSolveDate()){
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			setHopeSoleDateStr(dateformat.format(getHopeSolveDate()));
		}
		return hopeSoleDateStr;
	}
	public void setHopeSoleDateStr(String hopeSoleDateStr) {
		
		this.hopeSoleDateStr = hopeSoleDateStr;
	}
	public String getVisitDateStr() {
		if(null != getVisitDate()){
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			setVisitDateStr(dateformat.format(getVisitDate()));
		}
		return visitDateStr;
	}
	public void setVisitDateStr(String visitDateStr) {
		this.visitDateStr = visitDateStr;
	}
	
	
}