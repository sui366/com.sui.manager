package com.sui.manager.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
/**
 * @Description 用来获取properties文件中的配置
 * @author gc.nie
 * @date 2014-5-12 下午7:23:42
 * @Copyright Copyright (c) 2013 Shunwang,Inc. All Rights Reserved.
 */
public class PropertiesUtil extends PropertyPlaceholderConfigurer {
	private static Map<String, String> propertiesMap = new HashMap<String, String>();
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            if(!keyStr.startsWith("sockIOPool") && keyStr.indexOf(".c3p0.") < 0){
            	propertiesMap.put(keyStr, props.getProperty(keyStr));
            }
        }  
	}

	public static String getProperty(String key) {
		return propertiesMap.get(key);
	}
}
