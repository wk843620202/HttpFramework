package cn.json.httpframework;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import json.cn.myhttp.HttpUrlConnectionUtil;
import json.cn.myhttp.Request;

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
             //   testGet();
                testPost();
            }
        });
    }


    public static void testGet(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://api.stay4it.com/v1/public/core/?service=user.getAll";
                Request request = new Request(url);
                String response = null;
                try {
                    response = HttpUrlConnectionUtil.execute(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("result",response);
            }
        }).start();
    }

    public static void testPost(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
                String content = "account=stay4it&password=123456";
                Request request = new Request(url, Request.RequestMethod.POST);
                request.content = content;
                String response = null;
                try {
                    response = HttpUrlConnectionUtil.execute(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("result",response);
            }
        }).start();
    }

}
