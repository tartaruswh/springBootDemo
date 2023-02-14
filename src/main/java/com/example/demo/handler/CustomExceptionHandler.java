package com.example.demo.handler;

import com.example.demo.utils.JsonData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//@RestControllerAdvice   //标记专门处理异常的类，返回json数据   另一种ControllerAdvice，如果需要返回json数据，则方法需要加@ResponseBody
@ControllerAdvice   //异常返回页面时使用
public class CustomExceptionHandler {
//    @ExceptionHandler(value = Exception.class)
//    Object handlerException(Exception e, HttpServletRequest httpServletRequest) {
//        return JsonData.buildError("服务端出问题", -2);
//    }
//
//    @ExceptionHandler(value = RuntimeException.class)
//    Object handlerRuntimeException(Exception e, HttpServletRequest httpServletRequest) {
//        return JsonData.buildError("runtimeExceptionyichang ", -2);
//    }
    /**
     * 异常时返回页面
     */
    @ExceptionHandler(value = Exception.class)
    Object handlerPageException(Exception e, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error.html");
        modelAndView.addObject("msg", e.getMessage());
        return modelAndView;
    }

}
