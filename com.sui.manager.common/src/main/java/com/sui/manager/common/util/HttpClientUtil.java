package com.sui.manager.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientUtil {
	public final static Logger LOGGER = Logger.getLogger(HttpClientUtil.class);
	
	private static final int TIMEOUT = 10 * 1000;// 连接超时时间
	private static final int SO_TIMEOUT = 10 * 1000;// 数据传输超时
	
	private static final int MAX_TOTAL_CONN = 100;
	private static final int DEFAULT_MAX_PER_ROUTE = 10;
	
	private static final PoolingClientConnectionManager connectionManager;
	private static final HttpParams params;

	static {
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
		schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
		
		connectionManager = new PoolingClientConnectionManager(schemeRegistry);
		connectionManager.setMaxTotal(MAX_TOTAL_CONN);
		connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);

		params = new BasicHttpParams();
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIMEOUT);
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);
	}
	
	public static DefaultHttpClient getHttpClient(){
		return new DefaultHttpClient(connectionManager, params);
	}
	
	/**
	 * 执行Post请求
	 * @author gc.nie
	 * @param url
	 * @return
	 */
	public static String doPost(String url, Map<String, String> paramMap) {
		HttpPost httpPost = null;
		try {
			UrlEncodedFormEntity urlEntity = new UrlEncodedFormEntity(buildParam(paramMap), Consts.UTF_8);
			httpPost = new HttpPost(url.trim());
			httpPost.setEntity(urlEntity);
			HttpClient httpclient = getHttpClient();
			HttpResponse response = httpclient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == statusCode) {
				return EntityUtils.toString(response.getEntity());
			}else{
				LOGGER.error("Http connection fail, response statusCode is " + statusCode);
			}
		} catch (Exception e) {
			LOGGER.error("Http connection error", e);
		} finally {
			if (httpPost != null) {
				httpPost.releaseConnection();
			}
		}
		return null;
	}
	/**
	 * 请求参数
	 * @param paramMap
	 * @return
	 */
	private static List<NameValuePair> buildParam(Map<String, String> paramMap){
		List<NameValuePair> listPair = new ArrayList<NameValuePair>();
		for (Entry<String, String> entry : paramMap.entrySet()) {
			listPair.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return listPair;
	}
	
}
