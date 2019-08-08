package cn.json.httpframework;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import json.cn.myhttp.AppException;
import json.cn.myhttp.callback.FileCallback;
import json.cn.myhttp.callback.JsonCallback;
import json.cn.myhttp.Request;
import json.cn.myhttp.RequestMethod;
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

             //   testPostOnSubThread();

             //   testPostOnSubThreadForDownload();
                testPostOnSubThreadForDownloadProgress();
            }
        });
    }


    public static void testGetOnSubThread(){

        String url = "http://api.stay4it.com/v1/public/core/?service=user.getAll";
        Request request = new Request(url);

        request.setCallBack(new JsonCallback<String>() {

            @Override
            public void onSuccess(String response) {

            }

            @Override
            public void onFailure(AppException e) {

            }
        });
        RequestTask requestTask = new RequestTask(request);
        requestTask.execute();
    }

    public static void testPostOnSubThread(){

        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String content = "account=stay4it&password=123456";
        Request request = new Request(url, RequestMethod.POST);
        request.content = content;

        request.setCallBack(new JsonCallback<User>() {
            @Override
            public void onSuccess(User response) {
                Log.d("result",response.toString());
            }

            @Override
            public void onFailure(AppException e) {
                Log.d("result",e.toString());
            }
        });
        RequestTask requestTask = new RequestTask(request);
        requestTask.execute();
    }


    /**
     * 文件下载
     */
    public static void testPostOnSubThreadForDownload(){

        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String content = "account=stay4it&password=123456";
        String savedPath = Environment.getExternalStorageDirectory() + File.separator + "demo.txt";
        File file = new File(savedPath);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Request request = new Request(url, RequestMethod.POST);
        request.content = content;

        request.setCallBack(new FileCallback() {

            @Override
            public void onSuccess(String response) {
                Log.e("path",response);
            }

            @Override
            public void onFailure(AppException e) {
                Log.e("path",e.toString());
            }
        }.setCachePath(savedPath));
        RequestTask requestTask = new RequestTask(request);
        requestTask.execute();
    }

    /**
     * 文件下载进度更新
     */
    public static void testPostOnSubThreadForDownloadProgress(){

        String url = "http://api.stay4it.com/uploads/test.jpg";

        String savedPath = Environment.getExternalStorageDirectory() + File.separator + "text.jpg";
        File file = new File(savedPath);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Request request = new Request(url, RequestMethod.GET);
        request.setCallBack(new FileCallback() {
            @Override
            public void onProgressUpdate(int curLen, int totalLen) {
                super.onProgressUpdate(curLen, totalLen);
                Log.e("progress", curLen + "/" + totalLen);
            }

            @Override
            public void onSuccess(String response) {
                Log.e("path",response);
            }

            @Override
            public void onFailure(AppException e) {
                if(e.statusCode == 403){
                    // password incorrect
                }
                Log.e("errorCode",e.statusCode + ""+ "----" + e.message);
            }
        }.setCachePath(savedPath));
        request.enableProgressUdated(true);
        RequestTask requestTask = new RequestTask(request);
        requestTask.execute();
    }

}
