package com.mjl.controller.AdminAction;
import com.mjl.model.Sign;
import com.mjl.model.User;
import com.mjl.service.SignService;
import com.mjl.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @ResponseBody
    public Object list(Model model, HttpServletRequest request, @RequestParam Integer pageNum,
                       @RequestParam Integer pageSize, @RequestParam String emplName_, @RequestParam Integer emplId_){
        User user=(User)request.getSession().getAttribute("user");
        Integer total=0;
        Map<String,Object> pageObject=new HashMap<String, Object>();
        List<User> emplList;
        if (emplId_==null){
            emplId_=0;
        }
        //处理分页参数
        if(pageNum==null||pageNum==0){
            pageNum=1;
        }
        if(pageSize==null||pageSize==0){
            pageSize=20;
        }
        //是否做了员工号查询
        total=userService.gerAllUserCount(emplName_,emplId_);

        int totalPages=(int)Math.ceil(Float.valueOf(total)/pageSize);//向上取整，格式化total

        pageObject.put("pageNum",pageNum); //页码
        pageObject.put("pageSize",pageSize);//页数
        pageObject.put("totalItemCount",total);  //总条目数
        pageObject.put("totalPageCount",totalPages);//总页面
        pageObject.put("hasNextPage",pageNum<totalPages);
        pageObject.put("hasPrefPage",pageNum>1);
        if(pageNum>totalPages){
            //超出页码

        }
        //是否做了员工号查询
        emplList=userService.gerUserByPage(pageNum,pageSize,emplName_,emplId_);
        pageObject.put("Data", emplList);
        pageObject.put("itemCount", emplList == null ? 0 : emplList.size());
        request.setAttribute("signList", emplList);

        return JSONObject.fromObject(pageObject);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Object add(HttpServletRequest request,@RequestParam String userName,@RequestParam String userPassword,
                      @RequestParam String userEmail,@RequestParam Integer userId,@RequestParam Integer userAdmin
                      ){
        User user=new User();
        boolean message;
        Map<String,Object> messageJeson=new HashMap<String,Object>();
        user.setUsername(userName);
        user.setPassword(userPassword);
        user.setId(userId);
        user.setEmail(userEmail);
        user.setEmplAdmin(userAdmin);
        if (userService.addUser(user)==true){
            message=true;
            messageJeson.put("message",message);
            return messageJeson;
        }else {
            message=false;
            messageJeson.put("message",message);
            return messageJeson;
        }


    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer userId,HttpServletRequest request){
        Map<String,Object> messageJeson=new HashMap<String,Object>();
        boolean message;
        if (userService.deleteById(userId)==true && userId!=null){
            message=true;
            messageJeson.put("message",message);
            return messageJeson;
        }
        else {
            message=false;
            messageJeson.put("message",message);
            return messageJeson;
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
