package com.example.demo.filter;

import com.example.demo.model.User;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.utils.JsonData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.thymeleaf.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//映射到/api/v1/pri/*接口controller中
@WebFilter(urlPatterns = "/api/v1/pri/*",filterName = "LoginFilter")
public class LoginFilter implements Filter {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init LoginFilter");
    }

    /**
     * 订单功能：需首先验证是否登录，通过获取请求中是否存在token，已登录则有token，未登录则无token
     * @param servletRequest   拦截器获取到的请求信息
     * @param servletResponse   拦截器获取到的响应信息
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("doFilter LoginFilter=======");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //获取token的两种方式：1.header中获取；2.parameter中获取
        String token = req.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            token = req.getParameter("token");
        }
        System.out.println(token);
        //如果两种获取token都为空，则返回提示信息
        //未登录
        if (!StringUtils.isEmpty(token)) {
            User user = UserServiceImpl.sessionMap.get(token);
            if (user != null) {
                //如果为已登录，则不执行拦截
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                //传入token错误
                JsonData jsonData = JsonData.buildError("登录无效，token无效", -2);
                String jsonStr = objectMapper.writeValueAsString(jsonData);
                renderjson(resp, jsonStr);
            }
        }else {
            //未登录
            JsonData jsonData1 = JsonData.buildError("未登录", -3);
            String jsonStr1 = objectMapper.writeValueAsString(jsonData1);
            renderjson(resp, jsonStr1);
        }
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
    public void destroy() {
        System.out.println("destory LoginFilter");
    }
}
