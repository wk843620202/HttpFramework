package json.cn.myhttp;

import java.util.Map;

/**
 * Created by wangkang on 2019/8/2.
 */

public class Request {

    public enum RequestMethod{
        GET,POST,PUT,DELETE
    }

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
