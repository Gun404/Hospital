package com.bupt.Config;

import com.bupt.Interceptor.JwtTokenAdminInterceptor;
import com.bupt.Interceptor.JwtTokenDoctorInterceptor;
import com.bupt.Interceptor.JwtTokenPatientInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册拦截器
 */
@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    @Autowired
    private JwtTokenDoctorInterceptor jwtTokenDoctorInterceptor;

    @Autowired
    private JwtTokenPatientInterceptor jwtTokenPatientInterceptor;

    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/register");

        registry.addInterceptor(jwtTokenDoctorInterceptor)
                .addPathPatterns("/doctor/**")
                .excludePathPatterns("/doctor/login")
                .excludePathPatterns("/doctor/register");

        registry.addInterceptor(jwtTokenPatientInterceptor)
                .addPathPatterns("/patient/**")
                .excludePathPatterns("/patient/login")
                .excludePathPatterns("/patient/register");
    }
}
