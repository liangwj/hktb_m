package com.example.hktb.activity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.hktb.R;

import com.example.hktb.util.Http4Json;
import android.app.Activity;
import android.os.Bundle;

public class WorkSpaceActivity extends Activity {
	private Map<String, Object> map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workspace_act);

		try {

			String id = "113983038848113";

			HttpGet request = new HttpGet("http://fitark.org:9000/users/" + id
					+ "/workspaces.json");

			HttpResponse response = null;
			HttpClient client = new DefaultHttpClient();
			response = client.execute(request);
			String re = EntityUtils.toString(response.getEntity());
			// System.out.println(re);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
