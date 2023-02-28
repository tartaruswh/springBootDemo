package com.example.demo.intercepter;

import com.example.demo.model.User;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.utils.JsonData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoginIntercepter preHandle====");//获取token的两种方式：1.header中获取；2.parameter中获取
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        System.out.println(token);
        //如果两种获取token都为空，则返回提示信息
        //未登录
        if (!StringUtils.isEmpty(token)) {
            User user = UserServiceImpl.sessionMap.get(token);
            if (user != null) {
                //如果为已登录，返回true，则不执行拦截,执行正常请求
                return true;
            } else {
                //传入token错误，不执行请求，通过自定义renderJson返回
                JsonData jsonData = JsonData.buildError("登录无效，token无效", -2);
                String jsonStr = objectMapper.writeValueAsString(jsonData);
                renderjson(response, jsonStr);
                return false;
            }
        }else {
            //未登录
            JsonData jsonData1 = JsonData.buildError("未登录", -3);
            String jsonStr1 = objectMapper.writeValueAsString(jsonData1);
            renderjson(response, jsonStr1);
            return false;
        }
//        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    //将拦截器响应结果以传入的json格式返回给前端，正常是以controller接口返回的，该方式printWriter返回
    private void renderjson (HttpServletResponse response, String json){
        //响应时设置请求体格式
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try {
            PrintWriter printWriter = response.getWriter();
            //返回字符串格式数据
            printWriter.print(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("LoginIntercepter postHandle====");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("LoginIntercepter afterCompletion====");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
