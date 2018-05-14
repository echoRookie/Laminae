package com.example.rookie.laminae.main.classify;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rookie.laminae.R;
import com.example.rookie.laminae.db.Category;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by rookie on 2018/5/13.
 */

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.MyViewHolder> {
    private List<String> myList;
    private Context myContext;
    private UnselectAdapter unselectAdapter;

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    private List<String> type;

    public UnselectAdapter getUnselectAdapter() {
        return unselectAdapter;
    }

    public void setUnselectAdapter(UnselectAdapter unselectAdapter) {
        this.unselectAdapter = unselectAdapter;
    }



    public List<String> getMyList() {
        return myList;
    }

    public void setMyList(List<String> myList) {
        this.myList = myList;
    }

    public Context getMyContext() {
        return myContext;
    }

    public void setMyContext(Context myContext) {
        this.myContext = myContext;
    }

    public SelectAdapter(List<String> myList,List<String> type, Context myContext) {
        this.myList = myList;
        this.myContext = myContext;
        this.type = type;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pop_window_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int  position) {
        holder.categoryEdit.setText(myList.get(position));
        holder.categoryEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              将分类添加到未选中
                unselectAdapter.getMyList().add(myList.get(position));
                unselectAdapter.getType().add(type.get(position));
                unselectAdapter.notifyDataSetChanged();
//             删除保存用户保存到数据库的分类
                DataSupport.deleteAll(Category.class,"categoryName = ?",myList.get(position));
//              删除刚才选择的分类
                myList.remove(position);
                type.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView categoryEdit;
        public MyViewHolder(View itemView) {
            super(itemView);
            categoryEdit = (TextView) itemView.findViewById(R.id.popwindow_item_text);
        }
    }
}
