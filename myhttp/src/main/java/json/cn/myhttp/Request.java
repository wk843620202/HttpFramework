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
     * 是否需要更新
     */
    public boolean enableProgressUdate = false;

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

    public void enableProgressUdated(boolean enableProgressUdate) {
        this.enableProgressUdate = enableProgressUdate;
    }
}
