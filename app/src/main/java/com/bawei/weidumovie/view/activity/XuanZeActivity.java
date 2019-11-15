package com.bawei.weidumovie.view.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bawei.weidumovie.R;
import com.bawei.weidumovie.model.bean.Request;
import com.bawei.weidumovie.model.bean.XuanZuo;
import com.bawei.weidumovie.presenter.XuanZePresenter;
import com.bawei.weidumovie.view.adpater.XZMAdapter;
import com.bawei.weidumovie.view.adpater.ZWMadapter;
import com.bawei.weidumovie.view.consion.DataCall;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XuanZeActivity extends BaseActivity {


    @BindView(R.id.room_back)
    ImageView roomBack;
    @BindView(R.id.room_name)
    TextView roomName;
    @BindView(R.id.layout)
    LinearLayout layout;
//    @BindView(R.id.room_VideoPlayer)
//    fm.jiecao.jcvideoplayer_lib.JCVideoPlayer roomVideoPlayer;
    @BindView(R.id.room_movieSeat)
    RecyclerView roomMovieSeat;
    @BindView(R.id.real)
    RelativeLayout real;
    @BindView(R.id.room_time)
    TextView roomTime;
    @BindView(R.id.room_recycler)
    RecyclerView roomRecycler;
    @BindView(R.id.imag_gb)
    ImageView imagGb;
    @BindView(R.id.weixin)
    TextView weixin;
    @BindView(R.id.radio_zzfb)
    RadioButton radioZzfb;
    @BindView(R.id.radio_wx)
    RadioButton radioWx;
    @BindView(R.id.liner_lay)
    RelativeLayout linerLay;
    @BindView(R.id.btn_purchaseOrder)
    Button btnPurchaseOrder;
    @BindView(R.id.room_btn)
    Button roomBtn;
    private XuanZePresenter xuanZePresenter;
    private XZMAdapter xzmAdapter;
    private ZWMadapter zwMadapter;

    @Override
    protected void initView(Bundle savedInstanceState) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        roomRecycler.setLayoutManager(linearLayoutManager);
        xzmAdapter = new XZMAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,6);
        roomMovieSeat.setLayoutManager(gridLayoutManager);
        roomRecycler.setAdapter(xzmAdapter);
        zwMadapter = new ZWMadapter();
        roomMovieSeat.setAdapter(zwMadapter);
        xuanZePresenter = new XuanZePresenter(new XuanZePresen());
        xuanZePresenter.Request(25, 1);
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_xuan_ze;
    }


    private class XuanZePresen implements DataCall<List<XuanZuo>> {
        @Override
        public void Success(List<XuanZuo> data) {
            xzmAdapter.addAll(data);

            xzmAdapter.notifyDataSetChanged();
        }

        @Override
        public void Error(Request request) {

        }
    }
}
