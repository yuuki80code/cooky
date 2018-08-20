package com.yuuki.cooky.common.handler;

import com.yuuki.cooky.common.model.ResponseVo;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public ResponseVo handleAuthorizationException() {
        return ResponseVo.error("暂无权限，请联系管理员！");
    }


}
