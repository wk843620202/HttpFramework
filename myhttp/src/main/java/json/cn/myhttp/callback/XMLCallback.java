package json.cn.myhttp.callback;


import json.cn.myhttp.AppException;

/**
 * Created by wangkang on 2019/8/3.
 * xml格式数据回调解析
 */
public abstract class XMLCallback<T> extends AbstractCallback<T> {

    @Override
    protected T bindData(String result) throws AppException {

        // todo

        return null;
    }

}
