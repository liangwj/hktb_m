package com.example.hktb.activity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.hktb.R;

import com.example.hktb.entity.LoginData;
import com.example.hktb.util.Http4Json;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	private boolean flag = false;
	private EditText ed_name;
	private EditText ed_psw;
	private Button btn_log;
	private Map<String, String> map;
	private MyTask mTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_act);

		ed_name = (EditText) this.findViewById(R.id.ed_name);
		ed_psw = (EditText) this.findViewById(R.id.ed_psw);
		btn_log = (Button) this.findViewById(R.id.btn_log);

		btn_log.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String name = ed_name.getText().toString().trim();
				String password = ed_psw.getText().toString().trim();

				// 传递参数到接口
				String url = "http://166.111.138.117:9000/sessions/login.json";

				mTask = new MyTask();
				mTask.execute(url, name, password);
				
			}

		});
	}

	private class MyTask extends AsyncTask<String, Integer, String> {

		@Override
		protected void onPreExecute() {
			System.out.println("进入线程");
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			map = new HashMap<String, String>();
			map.put("session[name]", params[1]);
			map.put("session[password]", params[2]);
			try {
				String re = EntityUtils.toString(Http4Json.post(params[0], map)
						.getEntity());
				System.out.println("已获取JSON");
				return re;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String re) {
			JSONObject jsonO;
			try {
				jsonO = new JSONObject(re);

				if (!re.isEmpty()) {
					flag = (Boolean) jsonO.get("success");
				}
				String data = jsonO.get("data").toString();

				Gson gson = new Gson();
				LoginData logData = gson.fromJson(data, LoginData.class);
				// 持久化
				SharedPreferences sharedPreferences = getSharedPreferences(
						"user_info", Context.MODE_PRIVATE);
				Editor editor = sharedPreferences.edit();// 获取编辑器
				editor.putString("user_id", logData.getId());
				editor.putString("user_rt", logData.getRemember_token());
				if (logData.getDoctor_id() != null) {
					editor.putString("pos_name", logData.getDoctor().getName());
					editor.putString("pos_title", logData.getDoctor()
							.getProfessional_title());
					System.out.println("医生");
				} else if ((logData.getNurse_id() != null)) {
					editor.putString("pos_name", logData.getNurse().getName());
					editor.putString("pos_title", logData.getNurse()
							.getProfessional_title());
					System.out.println("护士");
				}
				editor.commit();// 提交修改

				if (flag == true) {
					Intent intent = new Intent(LoginActivity.this,
							WorkSpaceActivity.class);
					startActivity(intent);
					overridePendingTransition(R.anim.translate_in,
							R.anim.translate_out);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
