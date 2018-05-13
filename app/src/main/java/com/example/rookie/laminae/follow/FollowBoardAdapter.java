package com.example.rookie.laminae.follow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rookie.laminae.R;
import com.example.rookie.laminae.boardDetial.BoardDetialActivity;
import com.example.rookie.laminae.entity.FollowBoardListBean;
import com.example.rookie.laminae.search.SearchBoardListBean;
import com.example.rookie.laminae.searchResult.BoardResultAdapter;
import com.example.rookie.laminae.util.Constant;
import com.example.rookie.laminae.util.ImageLoadBuider;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by rookie on 2018/5/13.
 */

public class FollowBoardAdapter extends RecyclerView.Adapter<FollowBoardAdapter.MyViewHolder> {
    private List<SearchBoardListBean.BoardPinsBean> myBoards;
    private Context myContext;

    public FollowBoardAdapter(List<SearchBoardListBean.BoardPinsBean> myList, Context context) {
        this.myBoards = myList;
        this.myContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_board_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final SearchBoardListBean.BoardPinsBean boardPinsBean = myBoards.get(position);
        ImageLoadBuider.ImageLoadFromParamsGeneral(myContext,holder.boardCover,boardPinsBean.getPins().get(0).getFile().getKey());
        holder.boardTitle.setText(boardPinsBean.getTitle());
        holder.boardUsername.setText(boardPinsBean.getUser().getUsername());
        ImageLoadBuider.ImageLoadFromParamsGeneral(myContext,holder.boardUserIcon,boardPinsBean.getUser().getAvatar().getKey());
        holder.boardFollowCount.setText("喜欢"+boardPinsBean.getLike_count()+" 关注"+boardPinsBean.getFollow_count());
        holder.boardFlag.setText("已关注");
        holder.boardCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, BoardDetialActivity.class);
                intent.putExtra(Constant.BOARDID,boardPinsBean.getBoard_id());
                intent.putExtra(Constant.BOARDTITLE,boardPinsBean.getTitle());
                intent.putExtra(Constant.BOARDDESCRIPTION,boardPinsBean.getDescription());
                myContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myBoards.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView boardCover;
        TextView boardTitle;
        TextView  boardUsername;
        CircleImageView boardUserIcon;
        TextView boardFollowCount;
        TextView boardFlag;
        public MyViewHolder(View itemView) {
            super(itemView);
            boardCover = (ImageView) itemView.findViewById(R.id.result_board_cover);
            boardTitle = (TextView) itemView.findViewById(R.id.result_board_title);
            boardUsername = (TextView) itemView.findViewById(R.id.result_board_username);
            boardUserIcon = (CircleImageView) itemView.findViewById(R.id.result_board_usericon);
            boardFollowCount = (TextView) itemView.findViewById(R.id.result_board_follow);
            boardFlag = (TextView) itemView.findViewById(R.id.result_board_flag);
        }

    }
}
