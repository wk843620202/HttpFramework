package json.cn.myhttp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by wangkang on 2019/8/2.
 */
public class HttpUrlConnectionUtil {

    public static HttpURLConnection execute(Request request) throws AppException{
        switch (request.method){
            case GET:
            case DELETE:
                return get(request);
            case POST:
            case PUT:
                return post(request);
        }
        return null;
    }

    /**
     * get 请求
     * @param request
     * @return
     * @throws Exception
     */
    private static HttpURLConnection get(Request request) throws AppException {

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(request.url).openConnection();
            connection.setConnectTimeout(15 * 1000);
            connection.setRequestMethod(request.method.name());
            connection.setReadTimeout(15 * 1000);

            addHeaders(connection, request.headers);

        } catch (IOException e) {
            throw new AppException();
        }
        return connection;

    }

    /**
     * post请求
     * @param request
     * @return
     * @throws Exception
     */
    private static HttpURLConnection post(Request request) throws AppException {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(request.url).openConnection();
            connection.setConnectTimeout(15 * 1000);
            connection.setRequestMethod(request.method.name());
            connection.setReadTimeout(15 * 1000);
            connection.setDoOutput(true);

            addHeaders(connection,request.headers);

            // 写入请求参数
            OutputStream os =  connection.getOutputStream();
            os.write(request.content.getBytes());
        }catch (IOException e){
            throw new AppException();
        }

        return connection;
    }

    /**
     * 添加请求头
     * @param connection
     * @param headers
     */
    private static void addHeaders(HttpURLConnection connection,Map<String,String> headers){
        if(headers == null || headers.isEmpty()){
            return;
        }
        for (Map.Entry<String,String> entry: headers.entrySet()){
            connection.addRequestProperty(entry.getKey(),entry.getValue());
        }
    }

}
