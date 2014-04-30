package com.example.hktb.fragment;

import com.example.hktb.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class B extends Activity {

	private ImageView imgV1;
	private WebView wv2a;
	private WebView wv2b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.b);
		imgV1 = (ImageView) this.findViewById(R.id.imgV1);

		wv2a = (WebView) this.findViewById(R.id.wv2a);
		wv2b = (WebView) this.findViewById(R.id.wv2b);

		Intent intent = getIntent();
		String pic = intent.getStringExtra("pic");
		if (pic.equals("None")) {
			imgV1.setBackgroundResource(R.drawable.nopic);
		} else {
			String url = "http://fitark.org:7500/files/";
			String[] newpic = pic.split(",");

			wv2a.getSettings().setBuiltInZoomControls(true);
			wv2a.getSettings().setUseWideViewPort(true);
			wv2a.getSettings().setLoadWithOverviewMode(true);

			wv2a.loadUrl(url + newpic[0]);

		}

	}
}
