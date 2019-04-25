package cn.edu.scut.bookshop.util;

import cn.edu.scut.bookshop.domain.ErrorCode;
import cn.edu.scut.bookshop.domain.Result;

/**
 * 自定义业务异常类，继承RuntimeException
 * 结合[全局异常处理]使用
 * @see cn.edu.scut.bookshop.config.GlobalDefaultExceptionHandler
 * */
public class BusinessException extends RuntimeException{
    private static long serialVersionUID = 1L;

    private ErrorCode errorCode;
    private String description;

    public BusinessException(){}

    public BusinessException(ErrorCode errorCode){
        this(errorCode, "");
    }
    
    public BusinessException(ErrorCode errorCode, String description)
    {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
        this.description = description;
    }
    
    public ErrorCode getErrorCode()
    {
        return errorCode;
    }
    
    public String getErrorMsg()
    {
        return errorCode.getMsg() + " : " + description;
    }
    
    public Result.ResultBuilder getResultBuilder()
    {
        return new Result.ResultBuilder(errorCode.getStatus(), getErrorMsg());
    }
}
