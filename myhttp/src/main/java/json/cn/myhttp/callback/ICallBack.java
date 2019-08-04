package json.cn.myhttp.callback;

import java.net.HttpURLConnection;

/**
 * Created by wangkang on 2019/8/2.
 */

public interface ICallBack<T> {

    void onSuccess(T response);
    void onFailure(Exception e);

    T parse(HttpURLConnection connection) throws Exception;
}
