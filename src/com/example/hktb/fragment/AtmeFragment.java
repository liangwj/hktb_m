package com.example.hktb.fragment;

import com.example.hktb.R;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AtmeFragment extends BaseFragment {  
	  
    @Override  
    public String initContent() {  
        return "这是@我界面";  
    }  
}  