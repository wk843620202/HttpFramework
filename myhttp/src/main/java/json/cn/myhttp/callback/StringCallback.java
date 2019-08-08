package json.cn.myhttp.callback;


import json.cn.myhttp.AppException;

/**
 * Created by wangkang on 2019/8/3.
 * 字符串数据回调解析
 */
public abstract class StringCallback extends AbstractCallback {

    @Override
    protected String bindData(String result) throws AppException {
        return result;
    }

}
