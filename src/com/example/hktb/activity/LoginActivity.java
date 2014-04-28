package com.example.hktb.activity;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.util.EntityUtils;
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
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText ed_name;
	private EditText ed_psw;
	private Button btn_log;

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

				try {
					// 传递参数到接口
					String url = "http://fitark.org:9000/sessions/login.json";

					Map<String, String> map = new HashMap<String, String>();
					map.put("session[name]", name);
					map.put("session[password]", password);

					String re = EntityUtils.toString(Http4Json.post(url, map)
							.getEntity());
					// 解析Json放到对象中
					JSONObject jsonO = new JSONObject(re);
					boolean flag = (Boolean) jsonO.get("success");

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
						editor.putString("pos_name", logData.getDoctor()
								.getName());
						editor.putString("pos_title", logData.getDoctor()
								.getProfessional_title());
						System.out.println("医生");
					} else if ((logData.getNurse_id() != null)) {
						editor.putString("pos_name", logData.getNurse()
								.getName());
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
					} else {
						Toast.makeText(LoginActivity.this, "登陆失败",
								Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}
}
