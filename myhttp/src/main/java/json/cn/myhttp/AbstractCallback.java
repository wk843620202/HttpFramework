package json.cn.myhttp;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by wangkang on 2019/8/3.
 */

public abstract class AbstractCallback<T> implements ICallBack<T> {

    @Override
    public T parse(HttpURLConnection connection) throws Exception {

        int status = connection.getResponseCode();
        if (status == HttpStatus.CODE_OK) {
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
        }
        return  null;
    }

    /**
     * 解析数据，如何解析由子类实现
     * @param result
     * @return
     */
    protected abstract T bindData(String result) throws Exception;

}
