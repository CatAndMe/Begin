package com.mjl.controller.AdminAction;

import com.mjl.model.User;
import com.mjl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
//    @ResponseBody
       public String login(User user,HttpServletRequest request,Model model){
//        List<User> userList=userService.getAllUser();
//        model.addAttribute("userList",userList);
        HttpSession it=request.getSession();
        System.out.println(it);

        //调用login方法来验证是否是注册用户
        boolean loginType = userService.login(user.getUsername(),user.getPassword());
        if(loginType){
            //如果验证通过,则将用户信息传到前台
            request.setAttribute("user",user);
            it.setAttribute("user",user);
            //并跳转到success.jsp页面
            return "view/adminView";
        }else{
            //若不对,则将错误信息显示到错误页面
            request.setAttribute("message","用户名密码错误");
            return "UserView/LoginError";
        }
    }

    @RequestMapping("/hallo")
       public String hallo(){
           return "UserView/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,HttpSession httpSession){
        httpSession.invalidate();
        return "UserView/login";
    }






}
