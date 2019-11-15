package com.bawei.weidumovie.view.moviedetailsfragment;
/*时间:2019/11/8 0008
创建人:郭学飞*/


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bawei.weidumovie.R;
import com.bawei.weidumovie.model.bean.Request;
import com.bawei.weidumovie.model.bean.ResultBean;
import com.bawei.weidumovie.presenter.CommentPresenter;
import com.bawei.weidumovie.view.adpater.CommentDapter;
import com.bawei.weidumovie.view.consion.DataCall;

import java.util.List;

public class FilmreviewFragment extends Fragment {
    private int page=1;
    private int count=5;
    private RecyclerView commentlist;
    private CommentDapter commentDapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_filmreview, null);
        commentlist = v.findViewById(R.id.comment_recyclerview);
        Intent intent = getActivity().getIntent();
        int movieid = intent.getIntExtra("movieid", 0);
        commentDapter = new CommentDapter(getActivity());
        commentlist.setAdapter(commentDapter);
        CommentPresenter commentPresenter=new CommentPresenter(new CommentPresen());
        commentPresenter.Request(movieid,page,count);
        return v;
    }


    private class CommentPresen implements DataCall<List<ResultBean>> {

        @Override
        public void Success(List<ResultBean> data) {
            commentDapter.addAll(data);
            commentDapter.notifyDataSetChanged();

        }

        @Override
        public void Error(Request request) {
            Toast.makeText(getContext(), request.message, Toast.LENGTH_SHORT).show();
        }
    }
}
