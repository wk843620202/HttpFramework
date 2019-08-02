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

    public static String execute(Request request) throws Exception{
        switch (request.method){
            case GET:
                return get(request);
            case POST:
                return post(request);
            case PUT:
                return post(request);
            case DELETE:
                return post(request);
        }
        return null;
    }


    private static String get(Request request) throws Exception {

        HttpURLConnection connection = (HttpURLConnection) new URL(request.url).openConnection();
        connection.setConnectTimeout(15 * 1000);
        connection.setRequestMethod(request.method.name());
        connection.setReadTimeout(15 * 1000);

        addHeaders(connection, request.headers);

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
        return null;
    }

    private static String post(Request request) throws Exception {

        HttpURLConnection connection = (HttpURLConnection) new URL(request.url).openConnection();
        connection.setConnectTimeout(15 * 1000);
        connection.setRequestMethod(request.method.name());
        connection.setReadTimeout(15 * 1000);
        connection.setDoOutput(true);

        addHeaders(connection,request.headers);

        // 写入请求参数
        OutputStream os =  connection.getOutputStream();
        os.write(request.content.getBytes());

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
        return null;
    }

    private static void addHeaders(HttpURLConnection connection,Map<String,String> headers){
        // 添加请求头
        if(headers == null || headers.isEmpty()){
            return;
        }
        for (Map.Entry<String,String> entry: headers.entrySet()){
            connection.addRequestProperty(entry.getKey(),entry.getValue());
        }
    }

}
