package com.pera.tanping.peratech;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pera.tanping.peratech.framework.base.BaseFragment;

/**
 * @author ChayChan
 * @date 2017/6/23  11:22
 */
public class TabFragment extends BaseFragment {

    public static final String CONTENT = "content";
    private TextView mTextView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle) {
        mTextView = new TextView(getActivity());
        mTextView.setGravity(Gravity.CENTER);
        String content = getArguments().getString(CONTENT);
        mTextView.setText(content);
        return mTextView;
    }

    @Override
    public int getContentResId() {
        return 0;
    }


}
