package com.sui.manager.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;


public class HttpConnector {

	public static boolean readFromGet(String url) {
		boolean result = false;
		HttpURLConnection connection = null;
		InputStream in = null;
		BufferedReader reader = null;

		try {
			URL address_url = new URL(url);
			connection = (HttpURLConnection) address_url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			int response_code = connection.getResponseCode();

			if (response_code == HttpURLConnection.HTTP_OK) {
				result = true;
			}
		} catch (Exception e) {
			return false;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			try {
				if (in != null) {
					in.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
			}
		}
		return result;
	}

	public static boolean readFromPost(String url, String content) {
		boolean result = false;
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		OutputStreamWriter writer = null;
		InputStream in = null;

		try {
			URL postUrl = new URL(url);
			connection = (HttpURLConnection) postUrl.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			if (StringUtils.isNotEmpty(content)) {
				writer = new OutputStreamWriter(connection.getOutputStream());
				writer.write(content);
				writer.flush();
			}
			int response_code = connection.getResponseCode();
			
			if (response_code == HttpURLConnection.HTTP_OK) {
				result = true;
			}
		} catch (Exception e) {
			return false;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			try {
				if (reader != null) {
					reader.close();
				}
				if (writer != null) {
					writer.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		
		System.out.println(HttpConnector.readFromGet("http://www.ifeng.com/"));
	}
}
