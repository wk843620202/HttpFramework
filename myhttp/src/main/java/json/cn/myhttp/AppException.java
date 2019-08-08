package json.cn.myhttp;

/**
 * Created by wangkang on 2019/8/4.
 */

public class AppException extends Exception {

    public int statusCode;
    public String errorMessage;

    public AppException(){}

    public AppException(int statusCode, String errorMessage) {
        super(errorMessage);
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public AppException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
