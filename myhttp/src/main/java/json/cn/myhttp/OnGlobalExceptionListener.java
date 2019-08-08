package json.cn.myhttp;

/**
 * Created by wangkang on 2019/8/4.
 */
public interface OnGlobalExceptionListener {

    boolean handleException(AppException e);
}
