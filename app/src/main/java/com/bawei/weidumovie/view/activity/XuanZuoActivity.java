package com.bawei.weidumovie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.weidumovie.R;
import com.bawei.weidumovie.model.bean.DetailsBean;
import com.bawei.weidumovie.model.bean.Request;
import com.bawei.weidumovie.presenter.MovieDetailPresenter;
import com.bawei.weidumovie.presenter.MoviePresenter;
import com.bawei.weidumovie.view.adpater.MoviedetailsVpDapter;
import com.bawei.weidumovie.view.consion.DataCall;
import com.bawei.weidumovie.view.xuanzuofragment.PriceFragment;
import com.bawei.weidumovie.view.xuanzuofragment.QuYuFragment;
import com.bawei.weidumovie.view.xuanzuofragment.TimeFragment;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class XuanZuoActivity extends BaseActivity {


    @BindView(R.id.returnhot)
    ImageView returnhot;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.comment)
    TextView comment;
    @BindView(R.id.movie_name)
    TextView movieName;
    @BindView(R.id.movie_type)
    TextView movieType;
    @BindView(R.id.movie_duration)
    TextView movieDuration;
    @BindView(R.id.movie_time)
    TextView movieTime;
    @BindView(R.id.movie_area)
    TextView movieArea;
    @BindView(R.id.detailtab)
    TabLayout detailtab;
    @BindView(R.id.detailvp)
    ViewPager detailvp;
    @BindView(R.id.yingting_player)
    JCVideoPlayer mYingtingPlayer;
    private MoviePresenter moviePresenter;
    private int movieid;
    public ArrayList<String> strings = new ArrayList<>();
    public ArrayList<Fragment> fragments = new ArrayList<>();
    private MovieDetailPresenter movieDetailPresenter;

    @Override
    protected void initView(Bundle savedInstanceState) {

        Intent intent = getIntent();
        movieid = intent.getIntExtra("movieid", 0);
        Toast.makeText(this, movieid + "", Toast.LENGTH_SHORT).show();
        movieDetailPresenter = new MovieDetailPresenter(new MovieDetailPresen());
        movieDetailPresenter.Request(movieid);
        strings.add("海淀区");
        strings.add("今天");
        strings.add("价格最低");
        fragments.add(new QuYuFragment());
        fragments.add(new TimeFragment());
        fragments.add(new PriceFragment());
        detailvp.setAdapter(new MoviedetailsVpDapter(getSupportFragmentManager(), XuanZuoActivity.this, strings, fragments));
        detailtab.setupWithViewPager(detailvp, true);
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_xuan_zuo;
    }




    private class MovieDetailPresen implements DataCall<DetailsBean> {
        @Override
        public void Success(DetailsBean data) {
            movieName.setText(data.name);
            movieType.setText(data.movieType);
            movieDuration.setText(data.duration);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy" + "年" + "MM" + "月" + "dd" + "日");
            String format = simpleDateFormat.format(data.releaseTime);
            movieTime.setText(format);
            movieArea.setText(data.placeOrigin + "上映");
            //设置播放视频地址
            mYingtingPlayer.setUp(data.shortFilmList.get(0).videoUrl,"");
            //设置播放器第一帧
            Glide.with(XuanZuoActivity.this).load(data.shortFilmList.get(0).imageUrl).into(mYingtingPlayer.ivThumb);
        }

        @Override
        public void Error(Request request) {

        }
    }
}
