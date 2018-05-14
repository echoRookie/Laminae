package com.example.rookie.laminae.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
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

public class BoardEditDialog extends AppCompatDialogFragment {
    private List<String> categoryTitle;
    private List<String> categoryId;
    private EditText editTitle;
    private EditText editDescription;
    private Spinner spinner;
    private ArrayAdapter myAdapter;
    private String boardId;
    private String boardTitle;
    private String boardDescription;
    private String auther;
    public static BoardEditDialog newInstance(String id,String title,String description){
        BoardEditDialog boardEditDialog = new BoardEditDialog();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.BOARDID,id);
        bundle.putString(Constant.BOARDTITLE,title);
        bundle.putString(Constant.BOARDDESCRIPTION,description);
        boardEditDialog.setArguments(bundle);
        return boardEditDialog;

    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCategory();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_edit_board,null);
        editTitle = (EditText) view.findViewById(R.id.edit_board_name);
        editTitle.setText(boardTitle);
        editDescription = (EditText) view.findViewById(R.id.edit_board_describe);
        editDescription.setText(boardDescription);
        spinner = (Spinner) view.findViewById(R.id.spinner_title);
        myAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item,categoryTitle);
        spinner.setAdapter(myAdapter);
        builder.setTitle("编辑画板")
                .setView(view)
                .setPositiveButton("确定修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String category = categoryId.get(spinner.getSelectedItemPosition());
                        userBoardEdit(boardId,editTitle.getText().toString(),editDescription.getText().toString(),category);
                        Log.d("dialog", "onClick: "+editTitle.getText().toString());
                    }
                })
                .setNegativeButton("取消",null)
                .setNeutralButton("确定删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userBoardDelete(boardId);
                    }
                });
        return builder.create();
    }

    /**
     * 设置画板分类
     */
    public void setCategory(){
        String[] title = getResources().getStringArray(R.array.title_array_all);
        String[] id = getResources().getStringArray(R.array.type_array_all);
        categoryTitle = new ArrayList<>();
        categoryId = new ArrayList<>();
        for(int i=1;i<title.length;i++){
            categoryTitle.add(title[i]);
            categoryId.add(id[i]);
        }
        Bundle bundle = getArguments();
        boardId = bundle.getString(Constant.BOARDID);
        boardTitle = bundle.getString(Constant.BOARDTITLE);
        boardDescription = bundle.getString(Constant.BOARDDESCRIPTION);

    }

    /**
     * 编辑画板
     * @param id
     * @param title
     * @param description
     * @param category
     */
    private void userBoardEdit(String id,String title,String description,String category){
        RetrofitClient re = RetrofitClient.getInstance();
        OperateAPI operateAPI = re.createService(OperateAPI.class);
        Observable<UserBoardSingleBean> observable = operateAPI.httpsEditBoard( (String) SPUtils.get(getContext(),Constant.USERAUthorization, Base64.mClientInto),id,title,description,category);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBoardSingleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserBoardSingleBean value) {
//                        Log.d("dialog", "onNext: "+value.getBoards().getTitle());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("dialog", "onError: "+e.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 删除画板
     * @param id
     */
    private void userBoardDelete(String id){
        RetrofitClient re = RetrofitClient.getInstance();
        OperateAPI operateAPI = re.createService(OperateAPI.class);
        Observable<UserBoardSingleBean> observable = operateAPI.httpsDeleteBoard( (String) SPUtils.get(getContext(),Constant.USERAUthorization, Base64.mClientInto),id,Constant.OPERATEDELETEBOARD);
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
}
