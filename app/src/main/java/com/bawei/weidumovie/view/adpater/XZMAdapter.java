package com.bawei.weidumovie.view.adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.weidumovie.R;
import com.bawei.weidumovie.model.bean.XuanZuo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：染<p>
 * <p>创建时间：2019/11/15<p>
 * <p>更改时间：2019/11/15<p>
 */
public class XZMAdapter extends RecyclerView.Adapter<XZMAdapter.MyViewHolder> {

    private List<XuanZuo> list;
    private Context context;

    public XZMAdapter(Context context) {
        list = new ArrayList<>();
        this.context = context;
    }
    public void  addAll(List<XuanZuo> data){
        if (data != null) {
            list.addAll(data);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_xz, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.xz_tv.setText(list.get(i).screeningHall);
        myViewHolder.xz_time1.setText(list.get(i).beginTime);
        myViewHolder.xz_time2.setText(list.get(i).endTime);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

         TextView xz_tv,xz_time1,xz_time2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            xz_tv = itemView.findViewById(R.id.xz_tv);
            xz_time1 = itemView.findViewById(R.id.xz_time1);
            xz_time2 = itemView.findViewById(R.id.xz_time2);
        }
    }
}
