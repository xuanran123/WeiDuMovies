package com.bawei.weidumovie.presenter;
/*时间:2019/11/15 0015
创建人:郭学飞*/


import com.bawei.weidumovie.app.Api;
import com.bawei.weidumovie.view.consion.DataCall;

import io.reactivex.Observable;

public class CommentPresenter extends BasePresenter{

    public CommentPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable GetModel(Api api, Object... args) {
        return api.findComment((int)args[0],(int)args[1],(int)args[2]);
    }
}
