package com.sui.manager.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;

public class DateConvert implements Converter {

	
	private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat TIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Override
	public Object convert(Class arg0, Object arg1) {
//		if(null != arg1 && arg1 instanceof Date){
//			if(arg1.toString().contains(":")){
//				return DATEFORMAT.format((Date)arg1);
//			}
//			else{
//				return TIMEFORMAT.format((Date)arg1);
//			}
//		}
		return arg1;

	}
}
