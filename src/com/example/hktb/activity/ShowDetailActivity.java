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
import com.example.hktb.util.HttpUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

public class ShowDetailActivity extends Activity {
	private TextView tv1;
	private TextView tv2;
	private Node node, nodes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showdetail_act);

		tv1 = (TextView) this.findViewById(R.id.tv1);
		tv2 = (TextView) this.findViewById(R.id.tv2);

		// ȡ����õ�Intent
		Intent intent = getIntent();
		// ȡ��Intent�е����ݣ�ͨ����ֵ�Եķ�ʽ
		String strXml = intent.getStringExtra("xml");
		String url = "http://fitark.org:7500/files/" + strXml;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// �õ�һ��DocumentBuilder������
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();

			// ����һ��xml���ַ���������xml,Document��������xml�ĵ�
			Document document = builder.parse(HttpUtils.getXML(url));
			// �õ�xml�ĵ��ĸ�Ԫ�ؽڵ�
			Element rootElement = document.getDocumentElement();

			NodeList nodeList = rootElement.getElementsByTagName("dg");
			NodeList nodeList2 = rootElement.getElementsByTagName("de");
			for (int i = 0; i < nodeList2.getLength(); i++) {
				node = nodeList2.item(i);
				NamedNodeMap nodemap = node.getAttributes();
				System.out.println("�ڵ��ı�ֵ��" + node.getTextContent());
				int k = 0;
				nodes = nodemap.item(k);
				if (nodes.getTextContent().equals("EMR00001020101002")) {
					tv1.setText(nodemap.item(k + 6).getTextContent());
				}
				if (nodes.getTextContent().equals("EMR00001020101003")) {
					tv2.setText(nodemap.item(k + 6).getTextContent());
				}
				System.out.println("���ԣ�" + nodes.getNodeName() + "="
						+ nodes.getTextContent());

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

		tv1.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		tv1.getPaint().setAntiAlias(true);

		tv2.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		tv2.getPaint().setAntiAlias(true);

	}
}
