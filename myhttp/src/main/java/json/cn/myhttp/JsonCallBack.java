package json.cn.myhttp;


import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
            //获取一个类中泛型的实际类型 important
            Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            return gson.fromJson(data.toString(),type);
        }
        return  null;
    }

}
