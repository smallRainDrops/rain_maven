package com.example.crudproject.intercept;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 79018
 */
@Slf4j
public class SecurityCheckInterceptor  implements HandlerInterceptor {
    @Autowired
    protected MessageSource messageSource;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        //健康检查接口不拦截
//        if(StringUtils.contains(url,"/test")
//                || StringUtils.contains(url,"/dictUrl")){
//            return true;
//        }else{
//            return false;
//        }
        return true;
    }



}
