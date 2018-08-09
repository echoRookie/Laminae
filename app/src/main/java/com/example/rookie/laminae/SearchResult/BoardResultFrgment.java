package com.example.rookie.laminae.searchResult;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rookie.laminae.api.SearchAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.search.SearchBoardListBean;
import com.example.rookie.laminae.httputils.RetrofitClient;
import com.example.rookie.laminae.util.Base64;
import com.example.rookie.laminae.util.Constant;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rookie on 2018/5/7.
 */

public class BoardResultFrgment extends Fragment {
    private BoardResultAdapter myAdapter;
    private RecyclerView recyclerView;
    private static final String SEARCHKEY = Constant.SEARCHKEY;
    private String searchKey;//搜索关键字
    private int index = 1;//联网起始页
    //    返回实例本身
    public static BoardResultFrgment newInstance(String searchKey) {
        BoardResultFrgment boardResultFrgment = new BoardResultFrgment();
        Bundle bundle = new Bundle();
        bundle.putString(SEARCHKEY, searchKey);
        boardResultFrgment.setArguments(bundle);
        return boardResultFrgment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_base,container,false);
        Bundle bundle = getArguments();
        searchKey = bundle.getString(SEARCHKEY);
        recyclerView = (RecyclerView) view.findViewById(R.id.base_recyclerView);
        getBoardHttp();
        return view;
    }
    public void getBoardHttp(){
        RetrofitClient client = RetrofitClient.getInstance();
        SearchAPI searchAPI = client.createService(SearchAPI.class);
        Observable<SearchBoardListBean> observable = searchAPI.httpsBoardSearchRx(Base64.mClientInto,searchKey,index,20);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchBoardListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchBoardListBean value) {
//                      判断是否此字段的搜索结果
                        if(value!=null && value.getBoards().size()>0){
                            Log.d("BoardResultFragment", "onNext: "+value.getBoards().size());
                            myAdapter = new BoardResultAdapter(getContext(),value.getBoards());
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                            recyclerView.setLayoutManager(gridLayoutManager);
                            recyclerView.setAdapter(myAdapter);
                        }

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
