package com.example.hktb.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
	/**
	 * ����·����ȡ�������˵�xml�ļ�������
	 * 
	 * @param path
	 *            xml���ڵķ������ļ�·��
	 * @return InputStream xml�ļ���������
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
				if (code == 200) {// ���ӳɹ�
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