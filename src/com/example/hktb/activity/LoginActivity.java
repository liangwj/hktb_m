package com.example.hktb.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.example.hktb.R;

import com.example.hktb.util.Http4Json;
import com.example.hktb.util.JsonTools;

import android.app.Activity;
import android.content.Intent;
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
					// 传递参数到接口
					String url = "http://fitark.org:9000/sessions/login.json";

					Map<String, String> map = new HashMap<String, String>();
					map.put("session[name]", name);
					map.put("session[password]", password);

					String re = EntityUtils.toString(Http4Json.post(url, map)
							.getEntity());
					// 解析Json
					JSONObject jsonO = new JSONObject(re);
					boolean flag = (Boolean) jsonO.get("success");
					// success = true 封装数据对象 跳转界面
					if (flag == true) {
						Intent intent = new Intent(LoginActivity.this,
								WorkSpaceActivity.class);
						
						intent.putExtra("loginfo", re);
						startActivity(intent);
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
