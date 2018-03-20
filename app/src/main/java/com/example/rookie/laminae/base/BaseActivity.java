package com.example.rookie.laminae.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by rookie on 2018/3/20.
 */

public class BaseActivity extends AppCompatActivity implements BaseView {
    private ProgressDialog myProgressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myProgressDialog = new ProgressDialog(this);
        myProgressDialog.setCancelable(false);
    }
    @Override
    public void showLodaing() {
        if(!myProgressDialog.isShowing()){
            myProgressDialog.show();
        }


    }

    @Override
    public void hideLodaing() {
        if(myProgressDialog.isShowing()){
            myProgressDialog.dismiss();
        }

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showError() {

    }

    @Override
    public Context getContext() {
        return BaseActivity.this;
    }


}
