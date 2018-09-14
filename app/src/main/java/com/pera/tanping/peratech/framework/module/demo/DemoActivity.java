package com.pera.tanping.peratech.framework.module.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.pera.tanping.peratech.framework.base.BaseActivity;


public class DemoActivity extends BaseActivity {



    @Override
    public Fragment getContentFragment() {
        return new DemoFragment() ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }



}
