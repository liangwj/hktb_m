package com.example.hktb.activity;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.hktb.R;

import com.example.hktb.entity.LoginData;
import com.example.hktb.util.Http4Json;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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
					// ���ݲ������ӿ�
					String url = "http://fitark.org:9000/sessions/login.json";

					Map<String, String> map = new HashMap<String, String>();
					map.put("session[name]", name);
					map.put("session[password]", password);

					String re = EntityUtils.toString(Http4Json.post(url, map)
							.getEntity());
					// ����Json
					JSONObject jsonO = new JSONObject(re);
					boolean flag = (Boolean) jsonO.get("success");

					String data = jsonO.get("data").toString();

					Gson gson = new Gson();
					LoginData logData = gson.fromJson(data, LoginData.class);
					// �־û�
					SharedPreferences sharedPreferences = getSharedPreferences(
							"user_info", Context.MODE_PRIVATE);
					Editor editor = sharedPreferences.edit();// ��ȡ�༭��
					editor.putString("user_id", logData.getId());
					editor.putString("user_rt", logData.getRemember_token());
					editor.putString("pos_name", logData.getPosr().getName());
					editor.putString("pos_title", logData.getPosr()
							.getProfessional_title());
					editor.commit();// �ύ�޸�

					if (flag == true) {
						Intent intent = new Intent(LoginActivity.this,
								WorkSpaceActivity.class);
						startActivity(intent);
					} else {
						Toast.makeText(LoginActivity.this, "��½ʧ��",
								Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}
}
