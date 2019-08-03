package json.cn.myhttp;

import java.util.Map;

/**
 * Created by wangkang on 2019/8/2.
 * 构建请求对象，包含请求相关参数
 */
public class Request {

    public String url;
    public String content;
    public Map<String,String> headers;
    public RequestMethod method;

    public Request(String url){
        this.url = url;
        this.method = RequestMethod.GET;
    }

    public Request(String url, RequestMethod method){
        this.url = url;
        this.method = method;
    }
}
