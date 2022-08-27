package com.mall.lenovoMall.exception;

import com.mall.lenovoMall.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class XmExceptionHandler {

    @Autowired
    private ResultMessage resultMessage;

    @ExceptionHandler(XmException.class)
    public ResultMessage handleException(XmException e){
        ExceptionEnum em = e.getExceptionEnum();
        resultMessage.fail(em.getCode() + "", em.getMsg());
        return resultMessage;
    }
}
