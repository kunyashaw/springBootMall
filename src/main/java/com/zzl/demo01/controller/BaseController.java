package com.zzl.demo01.controller;

import com.zzl.demo01.api.CommonResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public CommonResult handleMissingParams(MissingServletRequestParameterException ex){
        return CommonResult.validateFailed("缺少参数:"+ex.getParameterName());
    }

    @ExceptionHandler(RuntimeException.class)
    public CommonResult handleError(RuntimeException re){
        return CommonResult.failed(re.getMessage());
    }
}
