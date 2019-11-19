package com.bawei.weidumovie.view.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bawei.weidumovie.R;
import com.bawei.weidumovie.disanfang.gaodeloction.MapLocationHelper;
import com.bawei.weidumovie.model.bean.Banners;
import com.bawei.weidumovie.model.bean.Home;
import com.bawei.weidumovie.model.bean.HomeOne;
import com.bawei.weidumovie.model.bean.Request;
import com.bawei.weidumovie.presenter.BannerPresente;
import com.bawei.weidumovie.presenter.HomePresenter;
import com.bawei.weidumovie.presenter.HomePresenter1;
import com.bawei.weidumovie.presenter.HomePresenter2;
import com.bawei.weidumovie.view.activity.MoreActivity;
import com.bawei.weidumovie.view.adpater.HomeMAdapter;
import com.bawei.weidumovie.view.adpater.HomeMAdapter1;
import com.bawei.weidumovie.view.adpater.HomeMAdapter2;
import com.bawei.weidumovie.view.consion.DataCall;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <p>文件描述：<p>
 * <p>作者：染<p>
 * <p>创建时间：2019/11/7<p>
 * <p>更改时间：2019/11/7<p>
 */
public class FilmFragment extends Fragment implements View.OnClickListener {
    TextView mLocation;
    Unbinder unbinder;
    private XBanner xbanner;
    private RecyclerView recycler_reying;
    private RecyclerView recycler_shangying;
    private ImageView rv_remen1;
    private TextView popularname_tv;
    private TextView popularscore_tv;
    private Button releasedmovie_bt;
    private RecyclerView recycler_remen;
    private BannerPresente bannerPresente;
    private HomePresenter homePresenter;
    private HomeMAdapter homeMAdapter, homeMAdapter2;
    private HomeMAdapter1 homeMAdapter1;
    private HomePresenter1 homePresenter1;
    private HomePresenter2 homePresenter2;
    private HomeMAdapter2 homeMAdapter3;
    private Boolean locationboolean = true;
    private TextView film_More;
    private TextView film_More1;
    private TextView film_More2;
    private MapLocationHelper helper;
    public AMapLocationClientOption mLocationOption = null;
    private AMapLocationClient mlocationClient;
    private static final int MY_PERMISSIONS_REQUEST_CALL_LOCATION = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_film, null);
        initView(view);

        //检查版本是否大于M
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_CALL_LOCATION);
            } else {
                //"权限已申请";
                showLocation();
            }
        }
        showLocation();


        //正在热映
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_reying.setLayoutManager(linearLayoutManager);
        //正在热映适配器
        homeMAdapter = new HomeMAdapter(getContext());
        recycler_reying.setAdapter(homeMAdapter);

        //即将上映
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_shangying.setLayoutManager(linearLayoutManager1);

        //即将上映适配器
        homeMAdapter1 = new HomeMAdapter1(getContext());
        recycler_shangying.setAdapter(homeMAdapter1);

        //热门电影
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_remen.setLayoutManager(linearLayoutManager2);
        //热门电影适配器
        homeMAdapter2 = new HomeMAdapter(getContext());
        recycler_remen.setAdapter(homeMAdapter2);
        //Banner轮播
        bannerPresente = new BannerPresente(new BannersPresen());
        bannerPresente.Request();
        //正在热映Presenter
        homePresenter = new HomePresenter(new HomePresen());
        homePresenter.Request(1, 5);
        //即将上映Presenter
        homePresenter1 = new HomePresenter1(new HomePresenOne());
        homePresenter1.Request(1, 4);
        //热门电影Presenter
        homePresenter2 = new HomePresenter2(new HomePresenTwo());
        homePresenter2.Request(1, 5);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    private void showLocation() {
        try {
            mlocationClient = new AMapLocationClient(getContext());
            mLocationOption = new AMapLocationClientOption();
            mLocationOption.setNeedAddress(true);
            mlocationClient.setLocationListener((AMapLocationListener) this);
            //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mLocationOption.setInterval(5000);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            //启动定位
            mlocationClient.startLocation();

        } catch (Exception e) {

        }
    }
    public void onLocationChanged(AMapLocation amapLocation) {
        try {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    //获取当前定位结果来源，如网络定位结果，详见定位类型表
                    Log.i("定位类型", amapLocation.getLocationType() + "");
                    Log.i("获取纬度", amapLocation.getLatitude() + "");
                    Log.i("获取经度", amapLocation.getLongitude() + "");
                    Log.i("获取精度信息", amapLocation.getAccuracy() + "");

                    //如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    Log.i("地址", amapLocation.getAddress());
                    Log.i("国家信息", amapLocation.getCountry());
                    Log.i("省信息", amapLocation.getProvince());
                    Log.i("城市信息", amapLocation.getCity());
                    Log.i("城区信息", amapLocation.getDistrict());
                    Log.i("街道信息", amapLocation.getStreet());
                    Log.i("街道门牌号信息", amapLocation.getStreetNum());
                    Log.i("城市编码", amapLocation.getCityCode());
                    Log.i("地区编码", amapLocation.getAdCode());
                    Log.i("获取当前定位点的AOI信息", amapLocation.getAoiName());
                    Log.i("获取当前室内定位的建筑物Id", amapLocation.getBuildingId());
                    Log.i("获取当前室内定位的楼层", amapLocation.getFloor());
                    Log.i("获取GPS的当前状态", amapLocation.getGpsAccuracyStatus() + "");

//                    district = amapLocation.getDistrict();
                    mLocation .setText(amapLocation.getDistrict());

                    // 停止定位
                    mlocationClient.stopLocation();
                    mlocationClient.stopAssistantLocation();
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        } catch (Exception e) {

        }
    }

    private void initView(View view) {
        xbanner = (XBanner) view.findViewById(R.id.xbanner);
        recycler_reying = (RecyclerView) view.findViewById(R.id.recycler_reying);
        recycler_shangying = (RecyclerView) view.findViewById(R.id.recycler_shangying);
        rv_remen1 = view.findViewById(R.id.rv_remen1);
        popularname_tv = (TextView) view.findViewById(R.id.popularname_tv);
        popularscore_tv = (TextView) view.findViewById(R.id.popularscore_tv);
        releasedmovie_bt = (Button) view.findViewById(R.id.releasedmovie_bt);
        recycler_remen = (RecyclerView) view.findViewById(R.id.recycler_remen);
        mLocation = (TextView) view.findViewById(R.id.location);

        film_More = (TextView) view.findViewById(R.id.film_More);
        film_More.setOnClickListener(this);
        film_More1 = (TextView) view.findViewById(R.id.film_More1);
        film_More1.setOnClickListener(this);
        film_More2 = (TextView) view.findViewById(R.id.film_More2);
        film_More2.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.film_More:
                Intent intent = new Intent(getContext(), MoreActivity.class);
                startActivity(intent);
                break;
            case R.id.film_More1:
                Intent intent1 = new Intent(getContext(), MoreActivity.class);
                startActivity(intent1);
                break;
            case R.id.film_More2:
                Intent intent2 = new Intent(getContext(), MoreActivity.class);
                startActivity(intent2);
                break;
        }
    }

    private class BannersPresen implements DataCall<List<Banners>> {
        @Override
        public void Success(final List<Banners> data) {

            xbanner.setData(data, null);
            xbanner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, View view, int position) {
                    Glide.with(getActivity()).load(data.get(position).imageUrl)
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                            .into((ImageView) view);
                }
            });
            // xbanner.setPageTransformer(Transformer.Default);
            //xbanner.setPageTransformer(Transformer.Cube);
            xbanner.setPageChangeDuration(1000);
        }

        @Override
        public void Error(Request request) {

        }
    }

    /**
     * 正在热映
     */

    private class HomePresen implements DataCall<List<Home>> {
        @Override
        public void Success(List<Home> data) {
            homeMAdapter.addAll(data);

            homeMAdapter.notifyDataSetChanged();
        }

        @Override
        public void Error(Request request) {

        }
    }

    /**
     * 即将上映
     */
    private class HomePresenOne implements DataCall<List<HomeOne>> {
        @Override
        public void Success(List<HomeOne> data) {
            homeMAdapter1.addAllOne(data);

            homeMAdapter1.notifyDataSetChanged();

        }

        @Override
        public void Error(Request request) {

        }
    }

    /**
     * 热门电影
     */
    private class HomePresenTwo implements DataCall<List<Home>> {
        @Override
        public void Success(List<Home> data) {
            homeMAdapter2.addAll(data);
            homeMAdapter2.notifyDataSetChanged();
            Glide.with(getActivity()).load(data.get(0).imageUrl).into(rv_remen1);
        }

        @Override
        public void Error(Request request) {

        }
    }

}
