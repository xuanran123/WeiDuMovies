package com.bawei.weidumovie.view.xuanzuofragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.weidumovie.R;
import com.bawei.weidumovie.model.bean.Recommend;
import com.bawei.weidumovie.model.bean.Request;
import com.bawei.weidumovie.presenter.RecommendPresenter;
import com.bawei.weidumovie.view.adpater.RecMAdapter;
import com.bawei.weidumovie.view.consion.DataCall;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：染<p>
 * <p>创建时间：2019/11/15<p>
 * <p>更改时间：2019/11/15<p>
 */
public class QuYuFragment extends Fragment {
    private RecyclerView qy_rlv;
    private RecMAdapter recMAdapter;
    private RecommendPresenter recommendPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_fragquyu, null);
        initView(view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        qy_rlv.setLayoutManager(linearLayoutManager);
        recMAdapter = new RecMAdapter(getContext());
        qy_rlv.setAdapter(recMAdapter);
        recommendPresenter = new RecommendPresenter(new RecommendPresen());
        recommendPresenter.Request(1, 5);

        return view;
    }

    private void initView(View view) {
        qy_rlv = (RecyclerView) view.findViewById(R.id.qy_rlv);
    }
    private class RecommendPresen implements DataCall<List<Recommend>> {
        @Override
        public void Success(List<Recommend> data) {
            recMAdapter.addAll(data);

            recMAdapter.notifyDataSetChanged();
        }

        @Override
        public void Error(Request request) {

        }
    }
}
