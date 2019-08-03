package cn.json.httpframework;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import json.cn.myhttp.JsonCallback;
import json.cn.myhttp.Request;
import json.cn.myhttp.RequestTask;

/**
 * Created by wangkang on 2019/8/2.
 */
public class MainActivity extends Activity {

    public TextView mTvDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvDetail = findViewById(R.id.tv_detail);
        findViewById(R.id.tv_get_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   testGetOnSubThread();

                testPostOnSubThread();
            }
        });
    }


    public static void testGetOnSubThread(){

        String url = "http://api.stay4it.com/v1/public/core/?service=user.getAll";
        Request request = new Request(url);
        RequestTask requestTask = new RequestTask(request);
        requestTask.setCallBack(new JsonCallback<String>() {

            @Override
            public void onSuccess(String response) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
        requestTask.execute();
    }

    public static void testPostOnSubThread(){

        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String content = "account=stay4it&password=123456";
        Request request = new Request(url, Request.RequestMethod.POST);
        request.content = content;
        RequestTask requestTask = new RequestTask(request);
        requestTask.setCallBack(new JsonCallback<User>() {
            @Override
            public void onSuccess(User response) {
                Log.d("result",response.toString());
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("result",e.toString());
            }
        });
        requestTask.execute();
    }

}
