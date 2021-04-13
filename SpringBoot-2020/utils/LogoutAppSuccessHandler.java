package com.ymy.boot.conf.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymy.boot.entity.Hr;
import com.ymy.boot.entity.ResponseBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LogoutAppSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");

        Hr hr = null;
        if (authentication != null) {
            hr = (Hr) authentication.getPrincipal();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(new ObjectMapper().writeValueAsString(ResponseBean.ok("成功注销!", hr)));
        printWriter.flush();
        printWriter.close();
    }
}
