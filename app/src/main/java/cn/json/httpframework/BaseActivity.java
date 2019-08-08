package cn.json.httpframework;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import json.cn.myhttp.AppException;
import json.cn.myhttp.OnGlobalExceptionListener;


/**
 * Created by wangkang on 2019/8/2.
 */
public class BaseActivity extends Activity implements OnGlobalExceptionListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean handleException(AppException e) {
        if(e.statusCode == 403){
            if("toke invalid".equals(e.errorMessage)){
                // 处理toke 失效
                return true;
            }
        }
        return false;
    }
}
