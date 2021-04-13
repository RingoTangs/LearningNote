package com.ymy.boot.conf.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymy.boot.entity.ResponseBean;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class RequestFailureHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ResponseBean responseBean;
        if (authException instanceof InsufficientAuthenticationException) {
            responseBean = ResponseBean.failure("尚未登录, 请登录!");
        } else {
            responseBean = ResponseBean.failure("访问失败!");
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(new ObjectMapper().writeValueAsString(responseBean));
        printWriter.flush();
        printWriter.close();
    }
}
