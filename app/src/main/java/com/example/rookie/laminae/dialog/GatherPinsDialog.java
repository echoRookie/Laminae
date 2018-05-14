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
import com.example.rookie.laminae.api.UserAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.entity.BoardListInfoBean;
import com.example.rookie.laminae.entity.GatherResultBean;
import com.example.rookie.laminae.httputils.RetrofitClient;
import com.example.rookie.laminae.imagedetial.ImageDetialActivity;
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
 * Created by rookie on 2018/5/11.
 */

public class GatherPinsDialog extends AppCompatDialogFragment {
    private EditText editText;
    private String m;
    private Spinner spinner;
    private int pinsId;
    private String editDescription;
    private List<String> boartTitle;
    private List<Integer> boardId;
    private ArrayAdapter myAdapter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        setBoardTitleInfo();
        View view = layoutInflater.inflate(R.layout.dialog_gather_pins,null);
        editText = (EditText) view.findViewById(R.id.edit_describe);
        spinner = (Spinner) view.findViewById(R.id.spinner_title);
        editDescription = ((ImageDetialActivity) getActivity()).getPinsDescription();
        editText.setText(editDescription);
        pinsId = ((ImageDetialActivity)getActivity()).getPinsId();
        builder.setTitle("采集")
                .setView(view)
                .setPositiveButton("确定采集", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Gather", "onClick: "+spinner.getSelectedItem());
                        Log.d("Gather", "onClick: "+boardId.get(spinner.getSelectedItemPosition()));
                        userOperatePins(String.valueOf(boardId.get(spinner.getSelectedItemPosition())),editDescription,String.valueOf(pinsId));
                    }
                })
                .setNegativeButton("取消",null);
                return  builder.create();
    }
    public void setBoardTitleInfo(){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        UserAPI userAPI = retrofitClient.createService(UserAPI.class);
        m = (String) SPUtils.get(getContext(), Constant.USERAUthorization, Base64.mClientInto);
        Observable<BoardListInfoBean> observable = userAPI.httpsBoardListInfo(m,Constant.OPERATEBOARDEXTRA);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BoardListInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BoardListInfoBean value) {
                        Log.d("GatherPins", "onNext: "+value.getBoards().size());
                        for (int i = 0;i<value.getBoards().size();i++){
                            boartTitle = new ArrayList<String>();
                            boardId = new ArrayList<Integer>();
                            boartTitle.add(value.getBoards().get(i).getTitle());
                            boardId.add(value.getBoards().get(i).getBoard_id());
                        }
                        myAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item,boartTitle);
                        spinner.setAdapter(myAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void userOperatePins(String boardId,String text,String pinsId){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        OperateAPI operateAPI = retrofitClient.createService(OperateAPI.class);
        Observable<GatherResultBean> observable = operateAPI.httpsGatherPins(m,boardId,text,pinsId);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GatherResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GatherResultBean value) {

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
