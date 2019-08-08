package json.cn.myhttp;

import java.util.Map;

import json.cn.myhttp.callback.ICallBack;

/**
 * Created by wangkang on 2019/8/2.
 * 构建请求对象，包含请求相关参数
 */
public class Request {

    /**
     * 请求url
     */
    public String url;

    /**
     * 请求内容
     */
    public String content;

    /**
     * 请求头
     */
    public Map<String,String> headers;

    /**
     * 请求方法
     */
    public RequestMethod method;

    /**
     * 请求回调
     */
    public ICallBack mICallBack;

    /**
     * 请求最大重试次数
     */
    public int maxRetryCount = 3;

    /**
     * 是否需要更新
     */
    public boolean enableProgressUpdate = false;
    public OnGlobalExceptionListener onGlobalExceptionListener;

    public Request(String url){
        this.url = url;
        this.method = RequestMethod.GET;
    }

    public Request(String url, RequestMethod method){
        this.url = url;
        this.method = method;
    }

    public void setCallBack(ICallBack callBack){
        this.mICallBack = callBack;
    }

    /**
     * 设置是否有进度
     * @param enableProgressUdate
     */
    public void enableProgressUpdated(boolean enableProgressUdate) {
        this.enableProgressUpdate = enableProgressUdate;
    }

    /**
     * 设置全局异常监听
     * @param onGlobalExceptionListener
     */
    public void setOnGlobalExceptionListener(OnGlobalExceptionListener onGlobalExceptionListener) {
        this.onGlobalExceptionListener = onGlobalExceptionListener;
    }
}
