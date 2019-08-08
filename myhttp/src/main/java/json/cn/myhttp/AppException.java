package json.cn.myhttp;

/**
 * Created by wangkang on 2019/8/4.
 */

public class AppException extends Exception {

    public int statusCode;
    public String errorMessage;
    public enum ErrorType{ TIMEOUT, SERVER, JSON, MANUAL}

    /**
     * 异常类型
     */
    public ErrorType errorType;

    public AppException(){}

    public AppException(int statusCode, String errorMessage) {
        super(errorMessage);
        this.errorType = ErrorType.SERVER;
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public AppException(ErrorType errorType, String errorMessage) {
        super(errorMessage);
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }
}
