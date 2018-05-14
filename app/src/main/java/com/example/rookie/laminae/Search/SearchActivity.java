package com.example.rookie.laminae.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rookie.laminae.api.SearchAPI;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.searchResult.SearchResultActivity;
import com.example.rookie.laminae.httputils.RetrofitClient;
import com.example.rookie.laminae.util.Base64;
import com.example.rookie.laminae.util.Constant;
import com.google.android.flexbox.FlexboxLayout;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SearchView searchView;
    private FlexboxLayout flexboxLayout;
    private ListView hintListView;
    private List<String> hintList;
    private ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        toolbar.setTitle("搜索");
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        hintListView = (ListView) findViewById(R.id.hint_list) ;
        hintList = new ArrayList<>();
        myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,hintList);
        hintListView.setAdapter(myAdapter);
        searchView = (SearchView) toolbar.findViewById(R.id.searchview);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "点击搜索图标", Toast.LENGTH_SHORT).show();
            }
        });
        SearchView.SearchAutoComplete searchText = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        RxTextView.textChanges(searchText)
                .debounce(1,TimeUnit.SECONDS)
                .skip(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CharSequence value) {
                          getHintHttp(value.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
//      搜索框的监听事件
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(), "提交" + query, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                intent.putExtra(Constant.SEARCHKEY,query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getApplicationContext(), newText, Toast.LENGTH_SHORT).show();
//                getHintHttp(newText);
                return true;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText(getApplicationContext(), "关闭", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        flexboxLayout = (FlexboxLayout) findViewById(R.id.flexbox_layout);
       for(int i=0;i<10;i++){
            TextView textView = new TextView(this);
            textView.setBackground(getResources().getDrawable(R.drawable.label_bg_shape));
            textView.setText("Test Label");
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(16, 16, 16, 16);
            textView.setTextSize(16);
            textView.setTextColor(getResources().getColor(R.color.pink_300));
            flexboxLayout.addView(textView);
            //通过FlexboxLayout.LayoutParams 设置子元素支持的属性
            ViewGroup.LayoutParams params = textView.getLayoutParams();
            if (params instanceof FlexboxLayout.LayoutParams) {
                FlexboxLayout.LayoutParams layoutParams = (FlexboxLayout.LayoutParams) params;
                layoutParams.setFlexGrow(0.5f);
            }
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       /* 处理返回键的点击事件*/
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     *请求搜索的提示字
     */
    public void getHintHttp(String searchKey){
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        SearchAPI searchAPI = retrofitClient.createService(SearchAPI.class);
        Observable<SearchHintBean> observable = searchAPI.httpsSearHintBean(Base64.mClientInto,searchKey);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchHintBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchHintBean value) {
                        hintList.clear();
//                        int count = 8;
//                        if(value.getResult().size()<=count){
//                        for(int i=0;i<value.getResult().size();i++){
//                            hintList.add(value.getResult().get(i));
//                        }
//                        myAdapter.notifyDataSetChanged();}
//                        else {
//                            for(int i=0;i<count;i++){
//                                hintList.add(value.getResult().get(i));
//                            }
//                            myAdapter.notifyDataSetChanged();}
                        for(int i=0;i<value.getResult().size();i++){
                            hintList.add(value.getResult().get(i));
                        }
                        myAdapter.notifyDataSetChanged();


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
