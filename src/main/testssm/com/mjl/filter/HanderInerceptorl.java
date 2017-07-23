package com.mjl.filter;

import com.mjl.model.User;
import com.mjl.service.UserService;
import com.mjl.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class HanderInerceptorl implements HandlerInterceptor {
    @Autowired
    UserService userService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2) throws Exception {
        //获取请求的url
        User user=(User)request.getSession().getAttribute("user");

        if(user!=null   ){
            return true;
        }else{
            return false;
        }
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
