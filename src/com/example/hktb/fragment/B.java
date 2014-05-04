package com.example.hktb.fragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.hktb.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class B extends Activity {

	private ImageView imgV1;
	private ImageView imgV2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		if (Build.VERSION.SDK_INT >= 11) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
					.penaltyLog().penaltyDeath().build());
		}

		super.onCreate(savedInstanceState);
		setContentView(R.layout.b);
		imgV1 = (ImageView) this.findViewById(R.id.imgV1);
		imgV2 = (ImageView) this.findViewById(R.id.imgV2);

		Intent intent = getIntent();
		String pic = intent.getStringExtra("pic");
		if (pic.equals("None")) {
			imgV1.setBackgroundResource(R.drawable.nopic);
		} else {
			String url = "http://166.111.138.117:7500/files/";
			String[] newpic = pic.split(",");
			imgV1.setImageBitmap(returnBitMap(url + newpic[0]));
			imgV2.setImageBitmap(returnBitMap(url + newpic[1]));

		}

	}

	public Bitmap returnBitMap(String url) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {
			myFileUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
}
