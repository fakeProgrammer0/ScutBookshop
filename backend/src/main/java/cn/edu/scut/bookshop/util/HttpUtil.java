package cn.edu.scut.bookshop.util;


import cn.edu.scut.bookshop.domain.ErrorCode;

import javax.servlet.http.HttpServletResponse;

public class HttpUtil
{
    public static void setResponseStatus(HttpServletResponse response, ErrorCode errorCode)
    {
        response.setStatus(errorCode.getHttpStatus());
    }
}
