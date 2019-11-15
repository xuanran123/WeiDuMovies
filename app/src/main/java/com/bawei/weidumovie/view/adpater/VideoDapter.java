package com.bawei.weidumovie.view.adpater;
/*时间:2019/11/12 0012
创建人:郭学飞*/


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.weidumovie.R;
import com.bawei.weidumovie.model.bean.MovieDirec;
import com.bawei.weidumovie.model.bean.ShortFilm;
import com.bumptech.glide.Glide;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class VideoDapter extends RecyclerView.Adapter<VideoDapter.MyViewHolder>{

    private  List<ShortFilm> list;
    private Context context;

    public VideoDapter( List<ShortFilm> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoDapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video, null);
        return new VideoDapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoDapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.video.setUp(list.get(i).videoUrl,null);
        Glide.with(context).load(list.get(i).videoUrl).into(myViewHolder.video.ivThumb);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        JCVideoPlayer video;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            video = itemView.findViewById(R.id.trailer_video);
        }
    }
}
