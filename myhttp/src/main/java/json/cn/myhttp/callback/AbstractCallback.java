package json.cn.myhttp.callback;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

import json.cn.myhttp.HttpStatus;

/**
 * Created by wangkang on 2019/8/3.
 */

public abstract class AbstractCallback<T> implements ICallBack<T> {

    private String path;

    @Override
    public T parse(HttpURLConnection connection) throws Exception {

        int status = connection.getResponseCode();
        if (status == HttpStatus.CODE_OK) {

            if(path == null){
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                InputStream is = connection.getInputStream();
                byte[] buffer = new byte[2048];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
                is.close();
                bos.flush();
                bos.close();

                String result = new String(bos.toByteArray());
                return bindData(result);
            }else {
                // 文件下载
                FileOutputStream fos = new FileOutputStream(path);
                InputStream is = connection.getInputStream();
                byte[] buffer = new byte[2048];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                is.close();
                fos.flush();
                fos.close();

                return bindData(path);
            }

        }
        return  null;
    }

    /**
     * 解析数据，如何解析由子类实现
     * @param result
     * @return
     */
    protected abstract T bindData(String result) throws Exception;

    /**
     * 设置文件下载缓存路径
     * @param path
     * @return
     */
    public ICallBack setCachePath(String path){
        this.path = path;
        return this;

    }

}
