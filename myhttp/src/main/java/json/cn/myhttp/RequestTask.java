package json.cn.myhttp;

import android.os.AsyncTask;

import java.net.HttpURLConnection;

/**
 * Created by wangkang on 2019/8/2.
 */

public class RequestTask extends AsyncTask<Void,Integer,Object> {

    Request request;


    public RequestTask(Request request){
        this.request = request;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Void... Strings) {
        try {
            HttpURLConnection connection = HttpUrlConnectionUtil.execute(request);
            if(request.enableProgressUpdate){
                return request.mICallBack.parse(connection, new OnProgressUpdatedListener() {
                    @Override
                    public void onProgressUpdate(int curLen, int totalLen) {
                        publishProgress(curLen,totalLen);
                    }
                });
            }else {
                return request.mICallBack.parse(connection);
            }
        } catch (AppException e) {
            e.printStackTrace();
            return e;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if(request.mICallBack != null){
            request.mICallBack.onProgressUpdate(values[0],values[1]);
        }
    }

    @Override
    protected void onPostExecute(Object s) {
        super.onPostExecute(s);
        if(s instanceof AppException){
            if(request.onGlobalExceptionListener != null){
                if(!request.onGlobalExceptionListener.handleException((AppException) s)){
                    request.mICallBack.onFailure((AppException) s);
                }
            }else {
                request.mICallBack.onFailure((AppException) s);
            }

        }else {
            request.mICallBack.onSuccess(s);
        }
    }
}
