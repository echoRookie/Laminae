package com.example.rookie.laminae.Search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.rookie.laminae.R;

public class SearchActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        searchView =  (SearchView) toolbar.findViewById(R.id.searchview);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"点击搜索图标",Toast.LENGTH_SHORT).show();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(),"提交"+query,Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getApplicationContext(),newText,Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText(getApplicationContext(),"关闭",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
