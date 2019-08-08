package json.cn.myhttp.callback;


import json.cn.myhttp.AppException;

/**
 * Created by wangkang on 2019/8/3.
 * 文件格式数据回调解析
 */
public abstract class FileCallback extends AbstractCallback<String> {

    @Override
    protected String bindData(String path) throws AppException {

        return path;
    }

}
