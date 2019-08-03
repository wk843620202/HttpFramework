package json.cn.myhttp;


import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by wangkang on 2019/8/3.
 */

public abstract class JsonCallBack<T> implements ICallBack<T> {


    private Class<T> clz;

    @Override
    public T parse(HttpURLConnection connection) throws Exception {

        int status = connection.getResponseCode();
        if (status == 200) {
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

            JSONObject jsonObject = new JSONObject(result);
            JSONObject data = jsonObject.optJSONObject("data");

            Gson gson = new Gson();
            return gson.fromJson(data.toString(),clz);
        }
        return  null;
    }

    public ICallBack setReturnType(Class<T> clz){
        this.clz = clz;
        return this;
    }
}
