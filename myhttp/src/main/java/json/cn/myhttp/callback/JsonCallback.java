package json.cn.myhttp.callback;


import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by wangkang on 2019/8/3.
 * json格式数据回调解析
 */
public abstract class JsonCallback<T> extends AbstractCallback<T> {

    @Override
    protected T bindData(String result) throws Exception {

        JSONObject jsonObject = new JSONObject(result);
        JSONObject data = jsonObject.optJSONObject("data");

        Gson gson = new Gson();
        //获取一个类中泛型的实际类型 important
        Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return gson.fromJson(data.toString(),type);
    }

}
