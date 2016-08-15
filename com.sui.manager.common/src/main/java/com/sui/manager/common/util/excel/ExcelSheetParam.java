package com.sui.manager.common.util.excel;

/**
 * 用于存放生成sheet的参数
 */
import java.util.List;

public class ExcelSheetParam {
	private String sheetName;   //sheet名
	private String[] fieldName; //列名
	private List<String[]> data; //数据
	
	
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public String[] getFieldName() {
		return fieldName;
	}
	public void setFieldName(String[] fieldName) {
		this.fieldName = fieldName;
	}
	public List<String[]> getData() {
		return data;
	}
	public void setData(List<String[]> data) {
		this.data = data;
	}
	
}
