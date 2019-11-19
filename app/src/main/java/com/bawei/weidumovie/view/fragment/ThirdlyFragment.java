package com.bawei.weidumovie.view.fragment;
/*时间:2019/11/8 0008
创建人:郭学飞*/


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bawei.weidumovie.DaoMaster;
import com.bawei.weidumovie.DaoSession;
import com.bawei.weidumovie.R;
import com.bawei.weidumovie.UserDao;
import com.bawei.weidumovie.model.bean.User;
import com.bawei.weidumovie.view.activity.LoginActivity;
import com.bumptech.glide.Glide;

import java.util.List;

public class ThirdlyFragment extends Fragment {
    private ImageView my_xinxi;
    private ImageView my_head_ic;
    private RelativeLayout My_login;
    private ImageView my_dyp;
    private UserDao userDao;
    private String headPic;
    private String nickName;
    private TextView text_name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.my_fragment_layout, null);
        initView(v);
        My_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

//        text_name.setText(nickName);
//        Glide.with(getContext()).load(nickName).into(my_head_ic);
        return v;
    }

    private void initView(View v) {
        my_xinxi = (ImageView) v.findViewById(R.id.my_xinxi);
        my_head_ic = (ImageView) v.findViewById(R.id.my_head_ic);
        My_login = (RelativeLayout) v.findViewById(R.id.My_login);
        my_dyp = (ImageView) v.findViewById(R.id.my_dyp);
        text_name = (TextView) v.findViewById(R.id.text_name);
    }
}
