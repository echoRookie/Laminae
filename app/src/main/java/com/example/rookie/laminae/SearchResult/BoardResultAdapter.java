package com.example.rookie.laminae.SearchResult;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rookie.laminae.R;
import com.example.rookie.laminae.Search.SearchBoardListBean;
import com.example.rookie.laminae.boardDetial.BoardDetialActivity;
import com.example.rookie.laminae.util.Constant;
import com.example.rookie.laminae.util.ImageLoadBuider;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by rookie on 2018/5/7.
 * 画板搜索结果的适配器
 */

public class BoardResultAdapter extends RecyclerView.Adapter<BoardResultAdapter.MyViewHolder> {
    private Context myContext;
    private List<SearchBoardListBean.BoardPinsBean> myBoards;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_board_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    public BoardResultAdapter(Context myContext, List<SearchBoardListBean.BoardPinsBean> myBoards) {
        this.myContext = myContext;
        this.myBoards = myBoards;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final SearchBoardListBean.BoardPinsBean boardPinsBean = myBoards.get(position);
        ImageLoadBuider.ImageLoadFromParamsGeneral(myContext,holder.boardCover,boardPinsBean.getPins().get(0).getFile().getKey());
        holder.boardTitle.setText(boardPinsBean.getTitle());
        holder.boardUsername.setText(boardPinsBean.getUser().getUsername());
        ImageLoadBuider.ImageLoadFromParamsGeneral(myContext,holder.boardUserIcon,boardPinsBean.getUser().getAvatar().getKey());
        holder.boardFollowCount.setText("喜欢"+boardPinsBean.getLike_count()+" 关注"+boardPinsBean.getFollow_count());
        holder.boardFlag.setText("关注");
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
        TextView  boardTitle;
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
