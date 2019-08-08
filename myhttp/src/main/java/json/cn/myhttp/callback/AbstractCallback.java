package json.cn.myhttp.callback;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;

import json.cn.myhttp.AppException;
import json.cn.myhttp.HttpStatus;
import json.cn.myhttp.OnProgressUpdatedListener;

/**
 * Created by wangkang on 2019/8/3.
 */

public abstract class AbstractCallback<T> implements ICallBack<T> {

    private String path;

    @Override
    public T parse(HttpURLConnection connection) throws AppException {
        return  parse(connection, null);
    }

    @Override
    public T parse(HttpURLConnection connection, OnProgressUpdatedListener listener) throws AppException {

        try{

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

                    int totalLen = connection.getContentLength();

                    byte[] buffer = new byte[2048];
                    int len;
                    int curLen = 0;
                    while ((len = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                        curLen += len;
                        if(listener != null){
                            listener.onProgressUpdate(curLen, totalLen);
                        }
                    }
                    is.close();
                    fos.flush();
                    fos.close();

                    return bindData(path);
                }
            }else {
                throw new AppException(status, connection.getResponseMessage());
            }

        }catch (SocketTimeoutException e) {
            //网络请求超时
            throw new AppException(AppException.ErrorType.TIMEOUT, e.getMessage());
        }catch (Exception e){
            throw new AppException(AppException.ErrorType.SERVER, e.getMessage());
        }
    }

    /**
     * 解析数据，如何解析由子类实现
     * @param result
     * @return
     */
    protected abstract T bindData(String result) throws AppException;

    /**
     * 设置文件下载缓存路径
     * @param path
     * @return
     */
    public ICallBack setCachePath(String path){
        this.path = path;
        return this;

    }

    @Override
    public void onProgressUpdate(int curLen, int totalLen) {

    }
}
