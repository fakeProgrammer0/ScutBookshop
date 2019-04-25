package cn.edu.scut.bookshop.config;

import cn.edu.scut.bookshop.util.BusinessException;
import cn.edu.scut.bookshop.domain.ErrorCode;
import cn.edu.scut.bookshop.domain.Result;
import cn.edu.scut.bookshop.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 控制器增强
 * 用来捕获@RequestMapping的方法中所有抛出的BusinessException
 * 将error message 放入 Result中，返回给前端
 * */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
    
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    /**
     * 自定义错误处理方法
     * @param ex 业务逻辑抛出的异常
     * @param response Http响应
     * @return JSON化的响应消息
     * */
    public Result errorHandle(BusinessException ex, HttpServletResponse response){
        // 将错误信息输出到日志文件
        logger.error(ex.getErrorMsg());
        
        ErrorCode errorCode = ex.getErrorCode();
        HttpUtil.setResponseStatus(response, errorCode);
        return ex.getResultBuilder().build();
    }
    
}
