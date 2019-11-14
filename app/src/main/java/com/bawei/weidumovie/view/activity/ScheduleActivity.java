package com.bawei.weidumovie.view.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.weidumovie.R;
import com.bawei.weidumovie.model.bean.Request;
import com.bawei.weidumovie.presenter.PeriodPresenter;
import com.bawei.weidumovie.view.consion.DataCall;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

public class ScheduleActivity extends BaseActivity {


    private PeriodPresenter periodPresenter;
    private TextView pq_one;
    private TextView pq_two;
    private TextView pq_three;
    private TextView pq_fore;
    private TextView pq_five;
    private TextView pq_six;
    private TextView pq_seven;
    private XRecyclerView pq_xrlv;

    @Override
    protected void initView(Bundle savedInstanceState) {
        pq_one = findViewById(R.id.pq_one);
        pq_two = findViewById(R.id.pq_two);
        pq_three = findViewById(R.id.pq_three);
        pq_fore = findViewById(R.id.pq_fore);
        pq_five = findViewById(R.id.pq_five);
        pq_six = findViewById(R.id.pq_six);
        pq_seven = findViewById(R.id.pq_seven);
        pq_xrlv = findViewById(R.id.pq_xrlv);

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

    private class PeriodPresen implements DataCall<Request> {



        @Override
        public void Success(Request data) {
            ArrayList<String> list = (ArrayList<String>) data.result;
            Toast.makeText(ScheduleActivity.this, list.get(0), Toast.LENGTH_SHORT).show();
            String one = list.get(0);
            String two = list.get(1);
            String three = list.get(2);
            String fore = list.get(3);
            String five = list.get(4);
            String six = list.get(5);
            String seven = list.get(6);

            pq_one.setText(one);
            pq_two.setText(two);
            pq_three.setText(three);
            pq_fore.setText(fore);
            pq_five.setText(five);
            pq_six.setText(six);
            pq_seven.setText(seven);
        }

        @Override
        public void Error(Request request) {

        }
    }
}
