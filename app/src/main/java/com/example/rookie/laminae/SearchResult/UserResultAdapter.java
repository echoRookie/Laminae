package com.example.rookie.laminae.searchResult;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rookie.laminae.R;
import com.example.rookie.laminae.search.SearchPeopleListBean;
import com.example.rookie.laminae.util.ImageLoadBuider;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by rookie on 2018/5/9.
 */

public class UserResultAdapter extends RecyclerView.Adapter<UserResultAdapter.MyViewHolder>{
    private List<SearchPeopleListBean.UsersBean> myUsers;
    private Context myContext;
    public UserResultAdapter(List<SearchPeopleListBean.UsersBean> myUsers, Context myContext) {
        this.myUsers = myUsers;
        this.myContext = myContext;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_people_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SearchPeopleListBean.UsersBean usersBean = myUsers.get(position);
        ImageLoadBuider.ImageLoadFromParamsGeneral(getMyContext(),holder.userIcon,usersBean.getAvatar().getKey());
        holder.username.setText(usersBean.getUsername());
        holder.followCount.setText(String.valueOf(usersBean.getFollower_count()));

    }

    @Override
    public int getItemCount() {
        return myUsers.size();
    }

    public List<SearchPeopleListBean.UsersBean> getMyUsers() {

        return myUsers;
    }

    public void setMyUsers(List<SearchPeopleListBean.UsersBean> myUsers) {
        this.myUsers = myUsers;
    }

    public Context getMyContext() {
        return myContext;
    }

    public void setMyContext(Context myContext) {
        this.myContext = myContext;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView userIcon;
        TextView username;
        TextView followCount;
        TextView isfollow;
        public MyViewHolder(View itemView) {
            super(itemView);
            userIcon = (CircleImageView) itemView.findViewById(R.id.result_user_icon);
            username = (TextView) itemView.findViewById(R.id.result_user_name);
            followCount = (TextView) itemView.findViewById(R.id.result_user_follow_count);
            isfollow = (TextView) itemView.findViewById(R.id.result_user_isfollow);
        }
    }
}
