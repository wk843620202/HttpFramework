package json.cn.myhttp;

import android.os.AsyncTask;

/**
 * Created by wangkang on 2019/8/2.
 */

public class RequestTask extends AsyncTask<Void,Integer,Object> {

    Request request;
    ICallBack mICallBack;

    public RequestTask(Request request){
        this.request = request;
    }

    public void setCallBack(ICallBack callBack){
        this.mICallBack = callBack;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Void... Strings) {
        try {
            String result = HttpUrlConnectionUtil.execute(request);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
    }

    @Override
    protected void onPostExecute(Object s) {
        super.onPostExecute(s);
        if(s instanceof Exception){
            mICallBack.onFailure((Exception) s);
        }else {
            mICallBack.onSuccess((String) s);
        }
    }
}
