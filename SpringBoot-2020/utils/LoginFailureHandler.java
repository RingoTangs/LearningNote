package com.ymy.boot.conf.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymy.boot.entity.ResponseBean;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.AccessDeniedException;

/**
 * 登录失败处理器
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");

        ResponseBean responseBean;
        if (exception instanceof BadCredentialsException) {
            responseBean = ResponseBean.failure("用户名或密码错误!");
        } else if (exception instanceof UsernameNotFoundException) {
            responseBean = ResponseBean.failure("用户名不存在!");
        } else if (exception instanceof AuthenticationServiceException) {
            responseBean = ResponseBean.failure("权限验证失败!");
        } else if (exception instanceof LockedException) {
            responseBean = ResponseBean.failure("账户被锁定!");
        } else if (exception instanceof AccountExpiredException) {
            responseBean = ResponseBean.failure("账户过期!");
        } else if (exception instanceof DisabledException) {
            responseBean = ResponseBean.failure("账户被禁用!");
        } else {
            responseBean = ResponseBean.failure("登录失败!");
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(new ObjectMapper().writeValueAsString(responseBean));
        printWriter.flush();
        printWriter.close();
    }
}
