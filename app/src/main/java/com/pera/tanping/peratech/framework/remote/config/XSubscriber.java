package com.pera.tanping.peratech.framework.remote.config;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.kongzue.dialog.v2.WaitDialog;
import com.tpnet.remote.RSubscriber;

/**
 * Created by tp on 2018/04/02.
 * <p>
 * 基础订阅类。提供弹窗的逻辑。不提供gson异常处理
 */

public abstract class XSubscriber<T>
        extends RSubscriber<T> {

    public Context context;
    public WaitDialog mAlertDialog;
    public boolean isShowDialog = false;

    public XSubscriber(Context context) {
        this.context = context;
        this.isShowDialog = true;

    }

    public XSubscriber(Context context, boolean isShowDialog) {
        this.context = context;
        this.isShowDialog = isShowDialog;

        if (isShowDialog) {
            mAlertDialog = WaitDialog.show(context,"加载中...");
        }
    }


    @Override
    public void showDialog() {
        super.showDialog();

        try {
            if (isShowDialog) {
//                showRequestDialog();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeDialog() {
        super.closeDialog();

        try {
            if (context != null) {

            }

            if (mAlertDialog != null) {
                mAlertDialog.doDismiss();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void showRequestDialog() {

        try {
           mAlertDialog.showDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
