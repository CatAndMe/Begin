package com.mjl.controller.AdminAction;

import com.mjl.model.User;
import com.mjl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

//@Controller注解用于标示本类为web层控制组件
@Controller
//@RequestMapping("/user")用于标定访问时对url位置
@RequestMapping("/user")
//在默认情况下springmvc的实例都是单例模式,所以使用scope域将其注解为每次都创建一个新的实例
@Scope("prototype")
public class UserController {
    //自动注入业务层的userService类
    @Autowired
        UserService userService;

        //login业务的访问位置为/user/login
       @RequestMapping("/login")
       @ResponseBody
       public Object login(HttpServletRequest request,@RequestParam Integer loginId,@RequestParam String password){
        boolean message;
        HttpSession it=request.getSession();
        Map<String,Object> messageJeson=new HashMap<String, Object>();
        //调用login方法来验证是否是注册用户
        boolean loginType = userService.login(loginId,password);
        if(loginType){
            User user=userService.selectById(loginId);
            //如果验证通过,则将用户信息传到前台
            User user1=userService.selectById(user.getId());
            user1.setPassword(null);
            request.setAttribute("user",user1);
            it.setAttribute("user",user1);
            message=true;
            messageJeson.put("message",message);
            return messageJeson;
        }else{
            //若不对,则将错误信息显示到错误页面
            message=false;
            messageJeson.put("message",message);
            return messageJeson;
        }
       }

    @RequestMapping("/modifyPassword")
    @ResponseBody
       public Object hallo(HttpServletRequest request,@RequestParam String fistInput,@RequestParam String secondInput){
        boolean message;
        HttpSession it=request.getSession();
        User user=(User)request.getSession().getAttribute("user");
        Map<String,Object> messageJeson=new HashMap<String, Object>();
        if(fistInput.toString().equals(secondInput.toString())!=false){
            user.setPassword(secondInput);
            userService.updateUserById(user);
            message=true;
            return messageJeson.put("message",message);
        }else {
            message=false;
            messageJeson.put("message",message);
            return messageJeson;
        }

    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,HttpSession httpSession){
        httpSession.invalidate();
        return "UserView/login";
    }






}
