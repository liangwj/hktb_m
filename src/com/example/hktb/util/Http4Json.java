package com.example.hktb.util;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class Http4Json {
	public static HttpResponse post(String url, Map<String, String> map) {
		HttpResponse response = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost(url);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// 循环取出参数，以及参数值，传入
			for (String key : map.keySet()) {
				params.add(new BasicNameValuePair(key, map.get(key)));
			}
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 获取返回的数据
			response = (HttpResponse) client.execute(request);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}
}
