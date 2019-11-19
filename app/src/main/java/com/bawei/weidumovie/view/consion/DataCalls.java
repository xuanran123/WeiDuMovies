package com.bawei.weidumovie.view.consion;


import com.bawei.weidumovie.model.bean.Request;
import com.bawei.weidumovie.model.bean.Requests;

import java.util.List;


/**
 * <p>文件描述：<p>
 * <p>作者：染<p>
 * <p>创建时间：2019/11/6<p>
 * <p>更改时间：2019/11/6<p>
 */

public interface DataCalls {
    void Success(Requests data);
    void Error(String request);
}
