package com.bawei.weidumovie.view.fragment;
/*时间:2019/11/8 0008
创建人:郭学飞*/


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.weidumovie.R;

public class ThirdlyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     View v= LayoutInflater.from(getContext()).inflate(R.layout.my_fragment_layout,null);
        return v;
    }
}
