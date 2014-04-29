package com.example.hktb.activity;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.example.hktb.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.graphics.Bitmap;

public class ShowPDFActivity extends Activity {

	private WebView webView;
	private String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		Intent intent = getIntent();
		// 取出Intent中的数据，通过键值对的方式
		String strXml = intent.getStringExtra("xml");
		url = "http://fitark.org:7500/files/" + strXml;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showpdf_act);
		init();
	}

	private void init() {
		webView = (WebView) findViewById(R.id.webView);
		loadPDF();
	}

	private void loadPDF() {
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setSupportZoom(true);
		webView.getSettings().setDomStorageEnabled(true);
		webView.getSettings().setAllowFileAccess(true);
		webView.getSettings().setPluginsEnabled(true);
		webView.getSettings().setUseWideViewPort(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.requestFocus();
		webView.getSettings().setLoadWithOverviewMode(true);
		String pdfUrl = url.replace("xml", "pdf");

		PDDocument pddDocument;
		try {
		//	File file = new File(pdfUrl);
			pddDocument = PDDocument.load(new URL(pdfUrl));

			PDFTextStripper textStripper = new PDFTextStripper();
			System.out.println(textStripper.getText(pddDocument));
			pddDocument.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		webView.loadUrl(pdfUrl);

		webView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);

			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				super.onReceivedError(view, errorCode, description, failingUrl);

			}

		});

	}

}
