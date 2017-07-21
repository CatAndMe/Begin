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

    private List<String> excludedUrls;
    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludedUrls = excludeUrls;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2) throws Exception {

        if (request.getSession().getAttribute("user")!=null){
            //登陆成功的用户
            return true;

        }else {
            //没有登陆，转向登陆界面
            request.getRequestDispatcher("/login.jsp").forward(request,arg1);
            return false;
        }


    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
