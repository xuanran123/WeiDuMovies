package com.bawei.weidumovie.view.adpater;
/*时间:2019/11/12 0012
创建人:郭学飞*/


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.weidumovie.R;
import com.bawei.weidumovie.model.bean.ResultBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CommentDapter extends RecyclerView.Adapter<CommentDapter.MyViewHolder>{

    private List<ResultBean> list;
    private Context context;

    public CommentDapter( Context context) {
        list = new ArrayList<>();
        this.context = context;
    }
    public void addAll(List<ResultBean> data){
        if (data != null) {
            list.addAll(data);
        }else {
            Log.d("datasize",data.size()+"");
        }
    }
    @NonNull
    @Override
    public CommentDapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentDapter.MyViewHolder myViewHolder, int i) {
        Glide.with(context).load(list.get(i).commentHeadPic).apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(myViewHolder.comment_avatar);
        Log.d("datasize",list.get(i).commentUserName);
        myViewHolder.comment_name.setText(list.get(i).commentUserName);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM"+"-"+"dd"+"  "+"HH"+"mm");
        String format = simpleDateFormat.format(list.get(i).commentTime);
        myViewHolder.comment_datetime.setText(format);
        myViewHolder.comment_tvcomment.setText(list.get(i).commentContent);
        myViewHolder.comment_praise.setText(list.get(i).greatNum+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView comment_name,comment_datetime,comment_tvcomment,comment_praise;
        ImageView comment_avatar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            comment_name= itemView.findViewById(R.id.comment_name);
            comment_datetime= itemView.findViewById(R.id.comment_datetime);
            comment_praise= itemView.findViewById(R.id.comment_praise);
            comment_avatar= itemView.findViewById(R.id.comment_avatar);
            comment_tvcomment=itemView.findViewById(R.id.comment_tvcomment);
        }
    }
}
