package com.example.hktb.activity;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.example.hktb.R;
import com.example.hktb.fragment.AtmeFragment;
import com.example.hktb.fragment.AttentionFragment;
import com.example.hktb.fragment.BaseInfoFragment;
import com.example.hktb.fragment.HelloFragment;
import com.example.hktb.fragment.MyListFragment;
import com.example.hktb.util.HttpUtils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ShowDetailActivity extends Activity {
	private FragmentManager fragmentManager;
	private RadioGroup radioGroup;
	private static RadioButton rb01;
	private static RadioButton rb02;
	private static RadioButton rb03;
	private static RadioButton rb04;
	private Node node, nodes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showdetail_act);

		if (Build.VERSION.SDK_INT >= 11) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
					.penaltyLog().penaltyDeath().build());
		}

		rb01 = (RadioButton) this.findViewById(R.id.rb01);
		rb02 = (RadioButton) this.findViewById(R.id.rb02);
		rb03 = (RadioButton) this.findViewById(R.id.rb03);
		rb04 = (RadioButton) this.findViewById(R.id.rb04);

		fragmentManager = getFragmentManager();
		radioGroup = (RadioGroup) findViewById(R.id.rg_tab);
		radioGroup
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						FragmentTransaction transaction = fragmentManager
								.beginTransaction();
						Fragment fragment = getInstanceByIndex(checkedId);
						transaction.replace(R.id.content, fragment);
						transaction.commit();
					}
				});

		// 取出获得的Intent
		Intent intent = getIntent();
		// 取出Intent中的数据，通过键值对的方式
		String strXml = intent.getStringExtra("xml");
		String url = "http://fitark.org:7500/files/" + strXml;
		System.out.println(url);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 得到一个DocumentBuilder解析类
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();

			// 接收一个xml的字符串来解析xml,Document代表整个xml文档
			Document document = builder.parse(HttpUtils.getXML(url));
			// 得到xml文档的根元素节点
			Element rootElement = document.getDocumentElement();

			// NodeList nodeList = rootElement.getElementsByTagName("dg");
			NodeList items = rootElement.getElementsByTagName("de");
			for(int i=0;i<items.getLength();i++)
				             {				                
				                 Element item=(Element)items.item(i);
				               String a =item.getAttribute("name");
				               String b = item.getAttribute("value");
				                System.out.println(a+"-------"+b);
				             }

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Fragment getInstanceByIndex(int index) {
		Fragment fragment = null;
		if (rb01.getId() == index) {
			fragment =  new BaseInfoFragment() ;
			return fragment;
		} else if (rb02.getId() == index) {
			fragment = new AtmeFragment();
			return fragment;
		} else if (rb03.getId() == index) {
			fragment = new MyListFragment();
			return fragment;
		} else if (rb04.getId() == index) {
			fragment = new HelloFragment();
			return fragment;
		}
		return fragment;
	}

}
