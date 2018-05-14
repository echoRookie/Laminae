package com.example.rookie.laminae.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.rookie.laminae.api.OperateAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.entity.UserBoardSingleBean;
import com.example.rookie.laminae.httputils.RetrofitClient;
import com.example.rookie.laminae.util.Base64;
import com.example.rookie.laminae.util.Constant;
import com.example.rookie.laminae.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rookie on 2018/5/12.
 */

public class BoardAddDialog extends AppCompatDialogFragment {
    private Spinner spinner;
    private EditText editTitle;
    private EditText editDescription;
    private List<String> categoryTitle;
    private List<String> categoryId;
    private ArrayAdapter myAdapter;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setInfo();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_edit_board,null);
        editTitle = (EditText) view.findViewById(R.id.edit_board_name);
        editDescription = (EditText) view.findViewById(R.id.edit_board_describe);
        spinner = (Spinner) view.findViewById(R.id.spinner_title);
        myAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item,categoryTitle);
        spinner.setAdapter(myAdapter);
        builder.setTitle("新建画板")
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RetrofitClient retrofitClient = RetrofitClient.getInstance();
                        OperateAPI operateAPI = retrofitClient.createService(OperateAPI.class);
                        String auther = (String) SPUtils.get(getContext(), Constant.USERAUthorization, Base64.mClientInto);
                        String title = editTitle.getText().toString();
                        String description = editDescription.getText().toString();
                        String category = categoryId.get(spinner.getSelectedItemPosition());
                        Observable<UserBoardSingleBean> observable = operateAPI.httpsAddBoard((String) SPUtils.get(getContext(), Constant.USERAUthorization, Base64.mClientInto),title,description,category);
                        observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<UserBoardSingleBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(UserBoardSingleBean value) {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                })
                .setNegativeButton("取消",null);
        return builder.create();
    }
    public void setInfo(){
        String[] title = getResources().getStringArray(R.array.title_array_all);
        String[] id = getResources().getStringArray(R.array.type_array_all);
        categoryTitle = new ArrayList<>();
        categoryId = new ArrayList<>();
        for(int i=1;i<title.length;i++){
            categoryTitle.add(title[i]);
            categoryId.add(id[i]);
        }
    }
}
