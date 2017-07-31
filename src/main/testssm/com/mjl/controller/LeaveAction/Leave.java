package com.mjl.controller.LeaveAction;

import com.mjl.model.User;
import com.mjl.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

//@Controller注解用于标示本类为web层控制组件
@Controller
//@RequestMapping("/employee")用于标定访问时对url位置
@RequestMapping("/employee")
//在默认情况下springmvc的实例都是单例模式,所以使用scope域将其注解为每次都创建一个新的实例
@Scope("prototype")
public class Leave {
    @Autowired
    SignService signService;
    //显示所有的记录
    @RequestMapping("/leave")
    @ResponseBody
    //请假接口
    public boolean leave(@RequestParam String state,@RequestParam String reason, HttpSession session,HttpServletRequest request){
        String State=state;
        String Reason=reason;

        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式login
        System.out.println(day.format(new Date()));// new Date()为获取当前系统时间

        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        System.out.println(time.format(new Date()));// new Date()为获取当前系统时间
        User user=(User)request.getSession().getAttribute("user");

        int formId=signService.getAllSignCount();

        int userId=user.getId();//获取id

        String username=user.getUsername();//获取名字


        return true;
    }
}
