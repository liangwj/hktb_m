package com.example.hktb.activity;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

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
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class WorkSpaceActivity extends Activity {
	private static Boolean isExit = false;
	private SharedPreferences sp;
	public List<WorkSpacesData> workSpace;
	public List<MobileMenu> mobileMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workspace_act);

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
			for (int i = 0; i < workSpace.size(); i++) {
				if (workSpace.get(i).isIs_support_mobile()) {
					String mobilemenu = workSpace.get(0).getMobile_menu();
					JSONArray jsonB = new JSONArray(mobilemenu);

					Type listType2 = new TypeToken<List<MobileMenu>>() {
					}.getType();
					mobileMenu = gson.fromJson(jsonB.toString(), listType2);

				}
			}
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

		final ExpandableListAdapter adapter = new BaseExpandableListAdapter() {

			private String[] space = new String[workSpace.size()];
			private String[][] menu = new String[workSpace.size()][mobileMenu
					.size()];

			// 自己定义一个获得文字信息的方法
			TextView getTextView() {
				for (int i = 0; i < workSpace.size(); i++) {
					space[i] = workSpace.get(i).getName();
					for (int j = 0; j < mobileMenu.size(); j++) {
						menu[i][j] = mobileMenu.get(j).getName();
					}

				}
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT, 100);
				TextView textView = new TextView(WorkSpaceActivity.this);
				textView.setLayoutParams(lp);
				textView.setGravity(Gravity.CENTER_VERTICAL);
				textView.setPadding(36, 0, 0, 0);
				textView.setTextSize(40);
				textView.setTextColor(Color.BLACK);
				return textView;
			}

			TextView getTextViewChild() {
				for (int i = 0; i < workSpace.size(); i++) {
					space[i] = workSpace.get(i).getName();
					for (int j = 0; j < mobileMenu.size(); j++) {
						menu[i][j] = mobileMenu.get(j).getName();
					}

				}
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT, 100);
				TextView textView = new TextView(WorkSpaceActivity.this);
				textView.setLayoutParams(lp);
				textView.setGravity(Gravity.CENTER_VERTICAL);
				textView.setPadding(36, 0, 0, 0);
				textView.setTextSize(20);
				textView.setTextColor(Color.BLACK);
				return textView;
			}

			// 重写ExpandableListAdapter中的各个方法
			@Override
			public int getGroupCount() {
				return space.length;
			}

			@Override
			public Object getGroup(int groupPosition) {
				return space[groupPosition];
			}

			@Override
			public long getGroupId(int groupPosition) {
				return groupPosition;
			}

			@Override
			public int getChildrenCount(int groupPosition) {
				return menu[groupPosition].length;
			}

			@Override
			public Object getChild(int groupPosition, int childPosition) {
				return menu[groupPosition][childPosition];
			}

			@Override
			public long getChildId(int groupPosition, int childPosition) {
				return childPosition;
			}

			@Override
			public boolean hasStableIds() {
				return true;
			}

			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				LinearLayout ll = new LinearLayout(WorkSpaceActivity.this);
				ll.setOrientation(0);
				TextView textView = getTextView();
				textView.setTextColor(Color.BLACK);
				textView.setText(getGroup(groupPosition).toString());
				ll.addView(textView);

				return ll;
			}

			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				LinearLayout ll = new LinearLayout(WorkSpaceActivity.this);
				ll.setOrientation(0);
				TextView textView = getTextViewChild();
				textView.setText(getChild(groupPosition, childPosition)
						.toString());
				ll.addView(textView);
				return ll;
			}

			@Override
			public boolean isChildSelectable(int groupPosition,
					int childPosition) {
				return true;
			}

		};

		ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.title);
		expandableListView.setAdapter(adapter);

		// 设置item点击的监听器
		expandableListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				Intent intent = new Intent(WorkSpaceActivity.this,
						ShowDataActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.translate_in,
						R.anim.translate_out);

				Toast.makeText(
						WorkSpaceActivity.this,
						adapter.getChild(groupPosition, childPosition)
								.toString(), Toast.LENGTH_SHORT).show();

				return false;
			}
		});
	}

	public List<MobileMenu> getMobileMenu() {
		return mobileMenu;
	}

	public void setMobileMenu(List<MobileMenu> mobileMenu) {
		this.mobileMenu = mobileMenu;
	}

	public List<WorkSpacesData> getWorkSpace() {
		return workSpace;
	}

	public void setWorkSpace(List<WorkSpacesData> workSpace) {
		this.workSpace = workSpace;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click(); // 调用双击退出函数
		}
		return false;
	}

	private void exitBy2Click() {
		Timer tExit = null;
		if (isExit == false) {
			isExit = true; // 准备退出
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = false; // 取消退出
				}
			}, 3000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
		} else {
			finish();
			System.exit(0);
		}
	}

}
