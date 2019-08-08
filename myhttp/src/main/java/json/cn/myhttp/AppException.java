package json.cn.myhttp;

/**
 * Created by wangkang on 2019/8/4.
 */

public class AppException extends Exception {

    public int statusCode;
    public String message;

    public AppException(){}

    public AppException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }

    public AppException(String message) {
        super(message);
        this.message = message;
    }
}
