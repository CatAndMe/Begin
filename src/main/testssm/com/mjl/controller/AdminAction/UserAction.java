package com.mjl.controller.AdminAction;
import com.mjl.model.Sign;
import com.mjl.model.User;
import com.mjl.service.SignService;
import com.mjl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by 冰开水 on 2017/7/18.
 */

//@Controller注解用于标示本类为web层控制组件
@Controller
@RequestMapping("/Action")
//在默认情况下springmvc的实例都是单例模式,所以使用scope域将其注解为每次都创建一个新的实例
@Scope("prototype")
public class UserAction {
    //自动注入业务层的userService类
    @Autowired
    UserService userService;
    @Autowired
    SignService signService;


    @RequestMapping("/userList")
    public String list(HttpServletRequest request){
//        List<User> userList = userService.getAllUser();
//        request.setAttribute("userList",userList);
//        return "view/list";
        List<Sign> signList = signService.getAllSign();
        request.setAttribute("signList",signList);
        return "SignView/SignList";
    }

    @RequestMapping("/add")
    public String add(User user,HttpServletRequest request){
        if (userService.selectById(user.getId())==null && user.getPassword().length()!=0 && user.getUsername().length()!=0 && user.getUsername().lastIndexOf(" ")==-1 && user.getPassword().lastIndexOf(" ")==-1){
            if(userService.addUser(user)==true){
                return "view/Congratulation";
            }else {
                request.setAttribute("message","未知错误");
                return "view/error";
            }

        }else{
            if (userService.selectById(user.getId())!=null)
            {
                request.setAttribute("message","已存在用户ID");
                return "view/error";
            }else if(user.getPassword().length()==0|| user.getUsername().length()==0){
                request.setAttribute("message","请输入用户名或密码");
                return "view/error";
            }else if (user.getPassword().lastIndexOf(" ")!=-1 || user.getUsername().lastIndexOf(" ")!=-1){
                request.setAttribute("message","用户名和密码不能有空格");
                return "view/error";
            }
            else {
                request.setAttribute("message","未知错误");
                return "view/error";
            }

        }
    }

    @RequestMapping("/delete")
    public String delete(Integer id,HttpServletRequest request){
        if (userService.deleteById(id)==true && id!=null){
            request.setAttribute("message","删除成功");
            return "view/Congratulation";
        }
        else {
            return "view/error";
        }
    }

    @RequestMapping("/get")
    public String get(Integer id,HttpServletRequest request) throws IOException {
        User user = userService.selectById(id);
        request.setAttribute("user", user);
        return "view/show";
    }

    @RequestMapping("/update")
    public String update(User user,HttpServletRequest request){
        if (user.getId()!=null && user.getPassword().length()!=0 && user.getUsername().length()!=0 && user.getUsername().lastIndexOf(" ")==-1 && user.getPassword().lastIndexOf(" ")==-1){
            if(userService.updateUserById(user)==true){
                return "view/Congratulation";
            }else {
                request.setAttribute("message","未知错误");
                return "view/error";
            }
        }
        else {
            if (userService.selectById(user.getId())==null)
            {
                request.setAttribute("message","不存在用户ID");
                return "view/error";
            }else if(user.getPassword().length()==0|| user.getUsername().length()==0){
                request.setAttribute("message","请输入用户名或密码");
                return "view/error";
            }else if (user.getPassword().lastIndexOf(" ")!=-1 || user.getUsername().lastIndexOf(" ")!=-1){
                request.setAttribute("message","用户名和密码不能有空格");
                return "view/error";
            }
            else {
                request.setAttribute("message","未知错误");
                return "view/error";
            }
        }
    }

    @RequestMapping("/SuccessView")
    public String SuccessView(){
        return "view/adminView";
    }


}
