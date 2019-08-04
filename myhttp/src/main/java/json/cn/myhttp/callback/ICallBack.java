package json.cn.myhttp.callback;

import java.net.HttpURLConnection;

import json.cn.myhttp.OnProgressUpdatedListener;

/**
 * Created by wangkang on 2019/8/2.
 */

public interface ICallBack<T> {

    /**
     * 成功
     * @param response
     */
    void onSuccess(T response);

    /**
     * 失败
     * @param e
     */
    void onFailure(Exception e);


    public T parse(HttpURLConnection connection) throws Exception;

    /**
     *
     * @param connection
     * @param listener
     * @return
     * @throws Exception
     */
    T parse(HttpURLConnection connection, OnProgressUpdatedListener listener) throws Exception;

    /**
     * 下载进度更新
     * @param curLen
     * @param totalLen
     */
    void onProgressUpdate(int curLen, int totalLen);
}
