package com.yuuki.cooky.common.oauth2;


import com.alibaba.fastjson.JSON;
import com.yuuki.cooky.common.model.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class OAuth2Filter extends AuthenticatingFilter {


    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = getToken((HttpServletRequest) servletRequest);
        if (StringUtils.isAllBlank(token)) {
            return null;
        }
        return new OAuth2Token(token);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = getToken((HttpServletRequest) servletRequest);
        if (StringUtils.isBlank(token)) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setHeader("Acccess-Control-Allow-Credentials","true");
            response.setHeader("Access-Control-Origin","**");
            response.getWriter().write(JSON.toJSONString(ResponseVo.error("token 错误")));
            return false;
        }
        return executeLogin(servletRequest, servletResponse);
    }


    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Acccess-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Origin","**");
        try{
            Throwable throwable = e.getCause() == null? e : e.getCause();
            response.getWriter().write(JSON.toJSONString(ResponseVo.unAuth(throwable.getMessage())));
        }catch (IOException exception){

        }
        return false;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }

        return false;
    }


    private String getToken(HttpServletRequest request){
        return request.getHeader("Authorization");

    }
}
