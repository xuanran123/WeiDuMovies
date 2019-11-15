package com.bawei.weidumovie.view.moviedetailsfragment;
/*时间:2019/11/8 0008
创建人:郭学飞*/


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bawei.weidumovie.R;
import com.bawei.weidumovie.model.bean.DetailsBean;
import com.bawei.weidumovie.model.bean.MovieActor;
import com.bawei.weidumovie.model.bean.MovieDirec;
import com.bawei.weidumovie.model.bean.Request;
import com.bawei.weidumovie.model.bean.ShortFilm;
import com.bawei.weidumovie.presenter.MovieDetailPresenter;
import com.bawei.weidumovie.view.adpater.DaoyanDapter;
import com.bawei.weidumovie.view.adpater.VideoDapter;
import com.bawei.weidumovie.view.adpater.YanyuanDapter;
import com.bawei.weidumovie.view.consion.DataCall;

import java.util.List;

public class ForeshowFragment extends Fragment {

    private RecyclerView videolist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_foreshow, null);
        videolist = v.findViewById(R.id.videoplaye_recyclerview);
        Intent intent = getActivity().getIntent();
        int movieid = intent.getIntExtra("movieid", 0);
        MovieDetailPresenter movieDetailPresenter = new MovieDetailPresenter(new MovieDetailPresen());
        movieDetailPresenter.Request(movieid);
        return v;
    }

    private class MovieDetailPresen implements DataCall<DetailsBean> {
        @Override
        public void Success(DetailsBean data) {
            List<ShortFilm> shortFilmList = data.shortFilmList;
            LinearLayoutManager linearLayoutManagerdaoyan = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            videolist.setAdapter(new VideoDapter(shortFilmList,getContext()));
            videolist.setLayoutManager(linearLayoutManagerdaoyan);
        }

        @Override
        public void Error(Request request) {
            Toast.makeText(getContext(), request.message, Toast.LENGTH_SHORT).show();
        }
    }
}
