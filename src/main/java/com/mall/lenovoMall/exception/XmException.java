package com.mall.lenovoMall.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class XmException extends RuntimeException{
    private ExceptionEnum exceptionEnum;
}
