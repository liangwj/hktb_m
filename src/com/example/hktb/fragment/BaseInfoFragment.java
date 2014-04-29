package com.example.hktb.fragment;

import com.example.hktb.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BaseInfoFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment, null);
		TextView textView = (TextView) view.findViewById(R.id.txt_content);
		textView.setText("我是基础信息");
		return view;
	}

}
