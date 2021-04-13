package com.ymy.boot.conf.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymy.boot.entity.Hr;
import com.ymy.boot.entity.ResponseBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录成功处理器
 */
@Component
public class LoginSuccessHandler  implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();

        // Principal 就是 实体类 Hr 的信息
        Hr hr = (Hr) authentication.getPrincipal();
        hr.setPassword(null);
        String info = objectMapper.writeValueAsString(ResponseBean.ok("登录成功", hr));

        PrintWriter printWriter = response.getWriter();
        printWriter.write(info);
        printWriter.flush();
        printWriter.close();
    }
}
