package com.bupt.Exception;

import com.bupt.Pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex){
        ex.printStackTrace();
        return  Result.error("操作错误，请联系管理员。错误详情:"+ex.getMessage());
    }
}
