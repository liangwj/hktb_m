package com.example.hktb.activity;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.hktb.R;

import com.example.hktb.entity.LoginData;
import com.example.hktb.entity.MobileMenu;
import com.example.hktb.entity.WorkSpacesData;
import com.example.hktb.util.Http4Json;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class WorkSpaceActivity extends Activity {
	private SharedPreferences sp;
	private ListView lv;
	private List<WorkSpacesData> workSpace;

	// private List<MobileMenu> mobileMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workspace_act);

		lv = (ListView) this.findViewById(R.id.listView);

		try {

			sp = getSharedPreferences("user_info", Context.MODE_PRIVATE);
			String id = sp.getString("user_id", "");

			HttpGet request = new HttpGet("http://fitark.org:9000/users/" + id
					+ "/workspaces.json");

			HttpResponse response = null;
			HttpClient client = new DefaultHttpClient();
			response = client.execute(request);
			String re = EntityUtils.toString(response.getEntity());

			JSONObject jsonO = new JSONObject(re);
			Gson gson = new Gson();
			String workspace = jsonO.get("workspaces").toString();
			JSONArray jsonA = new JSONArray(workspace);

			Type listType = new TypeToken<List<WorkSpacesData>>() {
			}.getType();
			workSpace = gson.fromJson(jsonA.toString(), listType);
			// for (int i = 0; i < workSpace.size(); i++) {
			// if (workSpace.get(i).isIs_support_mobile()) {
			// String mobilemenu = workSpace.get(0).getMobile_menu();
			// JSONArray jsonB = new JSONArray(mobilemenu);
			//
			// Type listType2 = new TypeToken<List<MobileMenu>>() {
			// }.getType();
			// mobileMenu = gson.fromJson(jsonB.toString(), listType2);
			// }
			// }
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] strings = { "title" };// Map的key集合数组
		int[] ids = { R.id.title };// 对应布局文件的id
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, getData(),
				R.layout.workspace_litem, strings, ids);
		 lv.setAdapter(simpleAdapter);

	}

	private List<HashMap<String, Object>> getData() {

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = null;
		for (int i = 0; i < workSpace.size(); i++) {
			map = new HashMap<String, Object>();
			map.put("title", workSpace.get(i).getName());

			list.add(map);
		}
		return list;
	}

	public List<WorkSpacesData> getWorkSpace() {
		return workSpace;
	}

	public void setWorkSpace(List<WorkSpacesData> workSpace) {
		this.workSpace = workSpace;
	}
	
}
