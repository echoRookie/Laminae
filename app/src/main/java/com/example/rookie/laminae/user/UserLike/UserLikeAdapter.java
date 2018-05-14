package com.example.rookie.laminae.user.UserLike;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rookie.laminae.R;
import com.example.rookie.laminae.imagedetial.ImageDetialActivity;
import com.example.rookie.laminae.user.UserPins.UserPinsBean;
import com.example.rookie.laminae.util.Constant;
import com.example.rookie.laminae.util.ImageLoadBuider;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by rookie on 2018/4/22.
 */

public class UserLikeAdapter extends RecyclerView.Adapter<UserLikeAdapter.MyViewHolder> {
    private List<UserPinsBean.UserPinsItem> myLikes;
    private Context myContext;
    private String URL = "http://img.hb.aicdn.com/";
    public UserLikeAdapter(List<UserPinsBean.UserPinsItem> list,Context context){
        this.myLikes = list;
        this.myContext = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_like_item,parent,false);
        UserLikeAdapter.MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final UserPinsBean.UserPinsItem  pinsInfo = myLikes.get(position);
        ImageLoadBuider.ImageLoadFromParamsGeneral(myContext,holder.likeCover,pinsInfo.getFile().getKey());
        holder.likeCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pinsId = pinsInfo.getPin_id();
                Intent intent = new Intent(myContext, ImageDetialActivity.class);
                intent.putExtra(Constant.PINSID,pinsId);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                myContext.startActivity(intent);


            }
        });
        holder.likeText.setText(pinsInfo.getRaw_text());
        holder.likeBoard.setText("来自 "+pinsInfo.getBoard().getTitle()+" 画板");
        holder.likeFollow.setText("喜欢 "+pinsInfo.getLike_count()+" 转采"+pinsInfo.getRepin_count());
        holder.likeUsername.setText(pinsInfo.getUser().getUsername());
        ImageLoadBuider.ImageLoadFromParamsGeneral(myContext,holder.likeUseriocn,pinsInfo.getUser().getAvatar().getKey());

    }

    @Override
    public int getItemCount() {
        return myLikes.size();
    }

    public List<UserPinsBean.UserPinsItem> getMyLikes() {
        return myLikes;
    }

    public void setMyLikes(List<UserPinsBean.UserPinsItem> myLikes) {
        this.myLikes = myLikes;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView likeCover;
        TextView likeText;
        TextView likeBoard;
        TextView likeFollow;
        CircleImageView likeUseriocn;
        TextView likeUsername;
        public MyViewHolder(View itemView) {
            super(itemView);
            likeCover = (ImageView) itemView.findViewById(R.id.user_like_cover);
            likeText = (TextView) itemView.findViewById(R.id.user_like_text);
            likeBoard = (TextView) itemView.findViewById(R.id.user_like_board);
            likeFollow = (TextView) itemView.findViewById(R.id.user_like_follow);
            likeUseriocn = (CircleImageView) itemView.findViewById(R.id.user_like_usericon);
            likeUsername = (TextView) itemView.findViewById(R.id.user_like_username);
        }
    }
}
