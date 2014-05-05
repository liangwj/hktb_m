package com.example.hktb.fragment;

import com.example.hktb.R;

import android.app.Activity;
import android.os.Bundle;

import java.util.List;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.hktb.R;

public class C extends Activity {
	static String TAG = "C";
	String src = "http://fitark.org:7500/files/50c9c8594ad9423891e7282a5379c50d.flv";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c);
		if (check()) {
			final WebView mWebFlash = (WebView) findViewById(R.id.video_web_view);
			WebSettings settings = mWebFlash.getSettings();
			settings.setJavaScriptEnabled(true);
			settings.setAllowFileAccess(true);
			settings.setPluginState(PluginState.ON);
			mWebFlash.setWebChromeClient(new WebChromeClient());
			mWebFlash.addJavascriptInterface(new CallJava(), "CallJava");
			mWebFlash.setBackgroundColor(0);
			String html = "<embed width=\"100%\" height=\"100%\" id=\"fmovie\" src=\"http://fitark.org:8082/playback.swf?src=" + src + "\" type=\"application/x-shockwave-flash\">";
			 mWebFlash.loadDataWithBaseURL(null, html, "text/html", "utf-8",
			 null);
			mWebFlash.getSettings().setLayoutAlgorithm(
					LayoutAlgorithm.SINGLE_COLUMN);
			mWebFlash.getSettings().setJavaScriptEnabled(true); // 设置支持Javascript
			mWebFlash.requestFocus();
			Toast.makeText(this, "播放视频", 50);
//			mWebFlash.loadUrl("file:///android_asset/player.html");
//			mWebFlash.loadUrl("javascript:setVideoSrc(22)");
		} else {
			Toast.makeText(this, "xxxxxxxxxx", 50);
		}
	}

	// 检查浏览器是否支持swf
	private boolean check() {
		PackageManager pm = getPackageManager();
		List<PackageInfo> infoList = pm
				.getInstalledPackages(PackageManager.GET_SERVICES);
		for (PackageInfo info : infoList) {
			if ("com.adobe.flashplayer".equals(info.packageName)) {
				return true;
			}
		}
		return false;
	}

	private final class CallJava {
		public String getVideoSrc() {
			return src;
		}

		public void consoleFlashProgress(float progressSize) {
			Log.v(TAG, "yyy");
		}

		public void FlashLoaded() {
			Log.v(TAG, "xxx");
		}
	}

}

