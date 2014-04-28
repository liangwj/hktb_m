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
import com.example.hktb.entity.UsReports;
import com.example.hktb.entity.WorkSpacesData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ShowDataActivity extends Activity {
	public List<UsReports> usReports;
	private ListView lv;
	private SimpleAdapter simpleAdapter;
	private JSONArray jsonA;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showdata_act);

		HttpGet request = new HttpGet(
				"http://fitark.org:9000/us_reports.json?[us_report]");

		HttpResponse response = null;
		HttpClient client = new DefaultHttpClient();
		try {
			response = client.execute(request);
			String re = EntityUtils.toString(response.getEntity());

			JSONObject jsonO = new JSONObject(re);
			Gson gson = new Gson();
			String us_reports = jsonO.get("us_reports").toString();
			jsonA = new JSONArray(us_reports);

			Type listType = new TypeToken<List<UsReports>>() {
			}.getType();
			usReports = gson.fromJson(jsonA.toString(), listType);

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

		lv = (ListView) this.findViewById(R.id.lv);
		String names[] = new String[] { "time", "state", "id" };
		int ids[] = new int[] { R.id.time, R.id.state, R.id.id };
		simpleAdapter = new SimpleAdapter(this, getData(),
				R.layout.showdata_item, names, ids);
		lv.setAdapter(simpleAdapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long arg3) {

				String strXml = usReports.get(position).getReport_document_id();
				Intent intent = new Intent(ShowDataActivity.this,
						ShowDetailActivity.class);
				intent.putExtra("xml", strXml);
				startActivity(intent);
				overridePendingTransition(R.anim.translate_in,
						R.anim.translate_out);

			}
		});

	}

	private List<Map<String, String>> getData() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		for (int i = 0; i < usReports.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", "ID i£ó¡¡£º" + usReports.get(i).getId());
			map.put("time", "Time is £º"
					+ usReports.get(i).getAppointment_time());
			String state = usReports.get(i).getApproval_status();
			if (state.equals("0")) {
				map.put("state", "×´Ì¬£ºÎ´ÉóºË");
			} else if (state.equals("1")) {
				map.put("state", "×´Ì¬£ºÒÑÉóºË");
			} else if (state.equals("2")) {
				map.put("state", "×´Ì¬£º´ò»Ø");
			}
			list.add(map);
		}
		return list;
	}
}
