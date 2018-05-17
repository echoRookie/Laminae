package com.example.rookie.laminae.user.UserPins;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rookie.laminae.R;
import com.example.rookie.laminae.imagedetial.ImageDetialActivity;
import com.example.rookie.laminae.util.Constant;
import com.example.rookie.laminae.util.ImageLoadBuider;

import java.util.List;

/**
 * Created by rookie on 2018/4/22.
 */

public class UserPinsAdapter extends RecyclerView.Adapter<UserPinsAdapter.MyViewHolder> {
    private final String URL = "http://img.hb.aicdn.com/";
    private List<UserPinsBean.UserPinsItem> myPins;
    private Context myContext;
    public UserPinsAdapter(List<UserPinsBean.UserPinsItem> list,Context context){
        this.myPins = list;
        this.myContext = context;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_pins_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    public List<UserPinsBean.UserPinsItem> getMyPins() {
        return myPins;
    }

    public void setMyPins(List<UserPinsBean.UserPinsItem> myPins) {
        this.myPins = myPins;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final UserPinsBean.UserPinsItem  pinsInfo = myPins.get(position);
        ImageLoadBuider.ImageLoadFromParamsGeneral(myContext,holder.pinsCover,pinsInfo.getFile().getKey());
        holder.pinsCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, ImageDetialActivity.class);
                intent.putExtra(Constant.PINSID,pinsInfo.getPin_id());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                myContext.startActivity(intent);
            }
        });
        holder.pinsText.setText(pinsInfo.getRaw_text());
        if (pinsInfo.getBoard().getTitle()!=null){
        holder.pinsBoard.setText("来自 "+pinsInfo.getBoard().getTitle());}
        holder.pinsFollow.setText("采集 "+pinsInfo.getLike_count()+"分享 "+pinsInfo.getRepin_count());
    }

    @Override
    public int getItemCount() {
        return myPins.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView pinsCover;
        TextView  pinsText;
        TextView  pinsBoard;
        TextView  pinsFollow;
        public MyViewHolder(View itemView) {
            super(itemView);
            pinsCover = (ImageView) itemView.findViewById(R.id.user_pins_cover);
            pinsText = (TextView) itemView.findViewById(R.id.user_pins_text);
            pinsBoard = (TextView) itemView.findViewById(R.id.user_pins_board);
            pinsFollow = (TextView) itemView.findViewById(R.id.user_pins_follow);
        }
    }
}
