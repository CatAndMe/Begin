package com.mjl.controller.SignAction;

import com.mjl.model.Sign;
import com.mjl.service.SignService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

//@Controller注解用于标示本类为web层控制组件
@Controller
//@RequestMapping("/Sign")用于标定访问时对url位置
@RequestMapping("/SignAction")
//在默认情况下springmvc的实例都是单例模式,所以使用scope域将其注解为每次都创建一个新的实例
@Scope("prototype")
public class SignAction {
    @Autowired
    SignService signService;
    //显示所有的记录
    @RequestMapping("/SignIn")
    @ResponseBody
    public void SignIn(HttpSession session, HttpServletRequest request, @RequestParam Integer emplId, @RequestParam String date, @RequestParam String time){
        List<Sign> signLog=signService.getAllSignById(emplId);
        Sign lastSignLog=signLog.get(signLog.size()-1);
        //判断日期是否是今日
        if (time.toString().equals(lastSignLog.getTime())){
            //签退
            try {
                signService.signOut(emplId,date,time);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else {
            //判断之前是否签退
            if (lastSignLog.getSignOut()==null){
                //返回前台补签

            }else {
                //新建签到记录
                try {
                    signService.signLogin(emplId,date,time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping("/Late")
    @ResponseBody
    public  void Late(HttpServletRequest request, HttpSession session, @RequestParam String reason){
        if (reason.equals("确认")){

        }else if (reason.equals("病假")){

        }else if (reason.equals("事假")){

        }else {

        }
    }

    @RequestMapping("/Leave")
    @ResponseBody
    public  void Leave(HttpServletRequest request, HttpSession session, @RequestParam String reason){
        if (reason.equals("确认")){

        }else if (reason.equals("病假")){

        }else if (reason.equals("事假")){

        }else {

        }
    }



}
