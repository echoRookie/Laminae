package com.example.rookie.laminae.boardDetial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.rookie.laminae.API.BoardDetailAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.entity.ListPinsBean;
import com.example.rookie.laminae.httpUtils.RetrofitClient;
import com.example.rookie.laminae.user.UserLike.UserLikeAdapter;
import com.example.rookie.laminae.user.UserPins.UserPinsAdapter;
import com.example.rookie.laminae.user.UserPins.UserPinsBean;
import com.example.rookie.laminae.util.Base64;
import com.example.rookie.laminae.util.Constant;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BoardDetialActivity extends AppCompatActivity {
    private String boardTitleText;
    private String boardDescriptionText;
    private int boardId;
    private Toolbar toolbar;
    private BoardRecommendAdapter myAdapter;
    private RecyclerView recyclerView;
    private TextView boardTitle;
    private TextView boardDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_detial);
        boardId = getIntent().getIntExtra(Constant.BOARDID,0);
        Log.d("boardId", "onNext: "+boardId);
        boardTitleText = getIntent().getStringExtra(Constant.BOARDTITLE);
        boardDescriptionText = getIntent().getStringExtra(Constant.BOARDDESCRIPTION);
        toolbar = (Toolbar) findViewById(R.id.board_detial_toolbar);
        toolbar.setTitle(boardTitleText);
        recyclerView = (RecyclerView) findViewById(R.id.board_detial_recyclerView);
        boardTitle = (TextView) findViewById(R.id.board_detial_title);
        boardDescription = (TextView)findViewById(R.id.board_detial_description);
        boardTitle.setText(boardTitleText);
        boardDescription.setText(boardDescriptionText);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getBoardPins();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public  void getBoardPins(){
        final RetrofitClient retrofitClient  = RetrofitClient.getInstance();
        BoardDetailAPI boardDetailAPI = retrofitClient.createService(BoardDetailAPI.class);
        Observable<ListPinsBean> observable = boardDetailAPI.httpsBoardPinsRx(Base64.mClientInto,String.valueOf(boardId),20);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListPinsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListPinsBean value) {
                        Log.d("boardDetile", "onNext: "+value.getPins().size()+value.getPins().get(0).getPin_id());
                        myAdapter = new BoardRecommendAdapter(value.getPins(),getApplicationContext());
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
                        recyclerView.setLayoutManager(gridLayoutManager);
                        recyclerView.setAdapter(myAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("board", "one: "+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
