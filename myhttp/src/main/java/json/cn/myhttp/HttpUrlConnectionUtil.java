package json.cn.myhttp;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by wangkang on 2019/8/2.
 */
public class HttpUrlConnectionUtil {

    public static HttpURLConnection execute(Request request) throws Exception{
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
    private static HttpURLConnection get(Request request) throws Exception {

        HttpURLConnection connection = (HttpURLConnection) new URL(request.url).openConnection();
        connection.setConnectTimeout(15 * 1000);
        connection.setRequestMethod(request.method.name());
        connection.setReadTimeout(15 * 1000);

        addHeaders(connection, request.headers);

        return connection;

        /*int status = connection.getResponseCode();
        if(status == 200){
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            InputStream is = connection.getInputStream();
            byte[] buffer = new byte[2048];
            int len;
            while ((len = is.read(buffer)) != -1){
                bos.write(buffer,0,len);
            }
            is.close();
            bos.flush();
            bos.close();
            return new String(bos.toByteArray());
        }*/
    }

    /**
     * post请求
     * @param request
     * @return
     * @throws Exception
     */
    private static HttpURLConnection post(Request request) throws Exception {

        HttpURLConnection connection = (HttpURLConnection) new URL(request.url).openConnection();
        connection.setConnectTimeout(15 * 1000);
        connection.setRequestMethod(request.method.name());
        connection.setReadTimeout(15 * 1000);
        connection.setDoOutput(true);

        addHeaders(connection,request.headers);

        // 写入请求参数
        OutputStream os =  connection.getOutputStream();
        os.write(request.content.getBytes());

        return connection;
        /*
        int status = connection.getResponseCode();
        if(status == 200){
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            InputStream is = connection.getInputStream();
            byte[] buffer = new byte[2048];
            int len;
            while ((len = is.read(buffer)) != -1){
                bos.write(buffer,0,len);
            }
            is.close();
            bos.flush();
            bos.close();
            return new String(bos.toByteArray());
        }
        return null;*/
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
