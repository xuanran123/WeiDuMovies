package com.bawei.weidumovie.presenter;

import com.bawei.weidumovie.app.Api;
import com.bawei.weidumovie.model.bean.Request;
import com.bawei.weidumovie.model.bean.Requests;
import com.bawei.weidumovie.model.okhttp.OkHttp;
import com.bawei.weidumovie.view.consion.DataCall;
import com.bawei.weidumovie.view.consion.DataCalls;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：染<p>
 * <p>创建时间：2019/11/14<p>
 * <p>更改时间：2019/11/14<p>
 */
public class PeriodPresenter {

    private DataCalls dataCalls;

    public PeriodPresenter(DataCalls dataCalls) {
        this.dataCalls = dataCalls;
    }


    public void Request() {

        Api api = OkHttp.getInstance().create(Api.class);
         api.findDateList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Requests>() {
                    @Override
                    public void accept(Requests object) throws Exception {
                        if (dataCalls != null) {
                            dataCalls.Success(object);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        if (dataCalls != null) {
                            dataCalls.Error(throwable.getMessage());
                        }
                    }
                });
    }
}
