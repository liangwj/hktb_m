package com.example.hktb.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
	/**
	 * 根据路径获取服务器端的xml文件数据流
	 * 
	 * @param path
	 *            xml所在的服务器文件路径
	 * @return InputStream xml文件的数据流
	 */
	public static InputStream getXML(String path) {
		InputStream inputStream = null;
		try {
			URL url = new URL(path);
			if (url != null) {
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setConnectTimeout(3000);
				connection.setDoInput(true);
				connection.setRequestMethod("GET");
				int code = connection.getResponseCode();
				if (code == 200) {// 连接成功
					inputStream = connection.getInputStream();
					return inputStream;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}