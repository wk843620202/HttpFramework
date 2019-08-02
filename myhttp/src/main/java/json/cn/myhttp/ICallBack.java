package json.cn.myhttp;

/**
 * Created by wangkang on 2019/8/2.
 */

public interface ICallBack {

    void onSuccess(String response);
    void onFailure(Exception e);
}
