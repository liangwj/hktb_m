package com.example.hktb.activity;

import com.example.hktb.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {

	private RelativeLayout spl_rl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_act);

		spl_rl = (RelativeLayout) this.findViewById(R.id.spl_rl);

		AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
		aa.setDuration(2000);
		spl_rl.startAnimation(aa);
		new Thread() {

			@Override
			public void run() {
				super.run();
				try {
					sleep(2000);
					loadMainUI();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}.start();
	
	}

	private void loadMainUI() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish(); // 把当前activity从任务栈里面移除

	}
}
