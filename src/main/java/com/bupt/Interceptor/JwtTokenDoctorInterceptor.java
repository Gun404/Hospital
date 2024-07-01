package com.bupt.Interceptor;


import com.alibaba.fastjson.JSONObject;
import com.bupt.Constant.JwtClaimsConstant;
import com.bupt.Constant.MessageConstant;
import com.bupt.Context.NameContext;
import com.bupt.Pojo.Result;
import com.bupt.Utils.JwtProperties;
import com.bupt.Utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.lang.Strings;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenDoctorInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getDoctorTokenName());
        //如果没有令牌，则不放行
        if (!Strings.hasLength(token)) {
            log.info("没有令牌，不允放行");
            Result error = Result.error(MessageConstant.USER_NOT_LOGIN);
            //不是controller类，不能自动将Result类转换为json格式数据，需手动转换(引入fastjson依赖，来自alibaba)
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }
        //2、校验令牌
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getDoctorSecretKey(), token);
            String doctorName = claims.get(JwtClaimsConstant.DOCTOR_NAME).toString();
            log.info("医生姓名：{}",doctorName );
            NameContext.setCurrentName(doctorName);
            //3、通过，放行
            log.info("令牌合法，放行");
            return true;
        } catch (Exception ex) {
            //4、不通过，响应401状态码
            log.info("令牌不合法，不予放行");
            response.setStatus(401);
            Result error = Result.error(MessageConstant.USER_NOT_LOGIN);
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }
    }
}
