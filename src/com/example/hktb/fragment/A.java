package com.example.hktb.fragment;

import com.example.hktb.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class A extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a);

		Intent intent = getIntent();
		String url = intent.getStringExtra("rep");

		WebView wv1 = (WebView) this.findViewById(R.id.wv1);
		wv1.getSettings().setJavaScriptEnabled(true);
		wv1.getSettings().setBuiltInZoomControls(true);
		wv1.getSettings().setUseWideViewPort(true);
		wv1.getSettings().setLoadWithOverviewMode(true);
		wv1.loadUrl(url.replace("xml", "png"));
	}
}
