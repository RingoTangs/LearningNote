package com.ymy.boot.conf;

import com.ymy.boot.conf.auth.inter.CurrentUserHasNeedRoleAccessDecisionManager;
import com.ymy.boot.conf.auth.inter.ParseUrl2UserRoleFilterInvocationSecurityMetadataSource;
import com.ymy.boot.conf.constant.SecurityConfigConstant;
import com.ymy.boot.conf.handler.LoginFailureHandler;
import com.ymy.boot.conf.handler.LoginSuccessHandler;
import com.ymy.boot.conf.handler.LogoutAppSuccessHandler;
import com.ymy.boot.conf.handler.RequestFailureHandler;
import com.ymy.boot.service.impl.HrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.Resource;

/**
 * spring-security 配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private HrServiceImpl hrServiceImpl;

    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Resource
    private LogoutAppSuccessHandler logoutAppSuccessHandler;

    @Autowired
    private ParseUrl2UserRoleFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

    @Autowired
    private CurrentUserHasNeedRoleAccessDecisionManager accessDecisionManager;

    @Resource
    private RequestFailureHandler requestFailureHandler;

    // 密码加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 权限验证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrServiceImpl);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 解决静态资源被拦截的问题
        web.ignoring().antMatchers(SecurityConfigConstant.STATIC_RESOURCE);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(accessDecisionManager);
                        object.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
                        return object;
                    }
                })

                .and()
                .formLogin()
                // 表单登录使用该接口
                .loginProcessingUrl(SecurityConfigConstant.LOGIN_PROCESSING_URL)

                // 如果未登录则重定向 ==> /login 接口；spring-security 默认 /login 是 html 页面
                // 这里如果返回 json 需要我们在 Controller 中自定义该接口
//                .loginPage(SecurityConfigConstant.LOGIN_PAGE)
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .permitAll()

                .and()
                .logout()
                .logoutUrl(SecurityConfigConstant.LOGOUT_URL)
                .logoutSuccessHandler(logoutAppSuccessHandler)

                // 请求失败时处理器 ==> 默认重定向到 "/login" ==> 现在不让重定向了, 直接返回提示
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(requestFailureHandler)

                .and()
                .csrf().disable();
    }
}
