package com.bawei.weidumovie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.bawei.weidumovie.R;
import com.bawei.weidumovie.model.bean.Request;
import com.bawei.weidumovie.model.bean.Requests;
import com.bawei.weidumovie.presenter.PeriodPresenter;
import com.bawei.weidumovie.view.adpater.Madapter;
import com.bawei.weidumovie.view.consion.DataCall;
import com.bawei.weidumovie.view.consion.DataCalls;
import com.bawei.weidumovie.view.fragment_more.Fragment_pq;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ScheduleActivity extends BaseActivity {


    @BindView(R.id.pq_tab)
    TabLayout pqTab;
    @BindView(R.id.pq_vp)
    ViewPager pqVp;
    private PeriodPresenter periodPresenter;
    private ArrayList<Fragment> list;
    private Madapter madapter;
    private int id;


    @Override
    protected void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        pqTab.addTab(pqTab.newTab());
        pqTab.addTab(pqTab.newTab());
        pqTab.addTab(pqTab.newTab());
        pqTab.addTab(pqTab.newTab());
        pqTab.addTab(pqTab.newTab());
        pqTab.addTab(pqTab.newTab());
        pqTab.addTab(pqTab.newTab());

        list = new ArrayList<>();
        list.add(new Fragment_pq(id));
        list.add(new Fragment_pq(id));
        list.add(new Fragment_pq(id));
        list.add(new Fragment_pq(id));
        list.add(new Fragment_pq(id));
        list.add(new Fragment_pq(id));
        list.add(new Fragment_pq(id));


        madapter = new Madapter(getSupportFragmentManager(),list);
        pqVp.setAdapter(madapter);
        pqTab.setupWithViewPager(pqVp);
        periodPresenter = new PeriodPresenter(new PeriodPresen());
        periodPresenter.Request();
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_schedule;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }

    private class PeriodPresen implements DataCalls {

        @Override
        public void Success(Requests data) {
            List<String> result = data.result;
            String s = result.toString();
            Toast.makeText(ScheduleActivity.this, s, Toast.LENGTH_SHORT).show();
            String[] split = s.split(",");
            pqTab.getTabAt(0).setText(split[0]);
            pqTab.getTabAt(1).setText(split[1]);
            pqTab.getTabAt(2).setText(split[2]);
            pqTab.getTabAt(3).setText(split[3]);
            pqTab.getTabAt(4).setText(split[4]);
            pqTab.getTabAt(5).setText(split[5]);
            pqTab.getTabAt(6).setText(split[6]);
        }

        @Override
        public void Error(String request) {
            Toast.makeText(ScheduleActivity.this, request, Toast.LENGTH_SHORT).show();
        }
    }

    private Datatable datatable;

    public void setDatatable(Datatable datatable) {
        this.datatable = datatable;
    }

    public interface Datatable{
        void success(int movoieId);
    }
}
