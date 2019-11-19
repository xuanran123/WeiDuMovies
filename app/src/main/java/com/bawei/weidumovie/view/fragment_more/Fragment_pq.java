package com.bawei.weidumovie.view.fragment_more;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.weidumovie.R;
import com.bawei.weidumovie.model.bean.Request;
import com.bawei.weidumovie.model.bean.YingYuanPaiQi;
import com.bawei.weidumovie.presenter.PQPresenter;
import com.bawei.weidumovie.view.adpater.PQMadapter;
import com.bawei.weidumovie.view.consion.DataCall;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：染<p>
 * <p>创建时间：2019/11/15<p>
 * <p>更改时间：2019/11/15<p>
 */
@SuppressLint("ValidFragment")
public class Fragment_pq extends Fragment {
    private int status;
    private XRecyclerView pq_xrlv;
    private PQPresenter pqPresenter;
    private PQMadapter pqMadapter;

    @SuppressLint("ValidFragment")
    public Fragment_pq(int i) {
        this.status = i;
    }

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_pq, null);
        initView(view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pq_xrlv.setLayoutManager(linearLayoutManager);
        pqMadapter = new PQMadapter(getContext());
        pq_xrlv.setAdapter(pqMadapter);
        pqPresenter = new PQPresenter(new PQPresen());
        pqPresenter.Request(status,1,5);
        return view;
    }

    private void initView(View view) {
        pq_xrlv = (XRecyclerView) view.findViewById(R.id.pq_xrlv);
    }

    private class PQPresen implements DataCall<List<YingYuanPaiQi>> {
        @Override
        public void Success(List<YingYuanPaiQi> data) {
            pqMadapter.addAll(data);

            pqMadapter.notifyDataSetChanged();
        }

        @Override
        public void Error(Request request) {

        }
    }
}
