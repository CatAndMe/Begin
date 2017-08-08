package com.mjl.controller.SignAction;

import com.mjl.model.Sign;
import com.mjl.service.SignService;
import net.sf.json.JSONObject;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Object SignIn(HttpSession session, HttpServletRequest request, @RequestParam Integer emplId, @RequestParam String date, @RequestParam String time) throws ParseException {
        List<Sign> signLog=signService.getAllSignById(emplId);
        Sign lastSignLog=signLog.get(signLog.size()-1);
        String message=null;
        Map<String,Object> signObject=new HashMap<String, Object>();

        //判断日期是否是今日
        if (time.toString().equals(lastSignLog.getTime())){
            //签退
            message=signService.signOut(emplId,date,time);
            if (message.equals("成功")==true){
                return JSONObject.fromObject(signObject.put("message",message));
            }else if (message.equals("确认早退")==true){
                return JSONObject.fromObject(signObject.put("message",message));
            }else if (message.equals("用户不存在")==true){
                return JSONObject.fromObject(signObject.put("message",message));
            }else {
                return JSONObject.fromObject(signObject.put("message",message));
            }
        }else {
            //判断之前是否签退
            if (lastSignLog.getSignOut()==null){
                //返回前台补签
                return JSONObject.fromObject(signObject.put("message","补签退"));
            }else {
                //新建签到记录
                message=signService.signLogin(emplId,date,time);
                if (message.equals("成功")==true){
                    return JSONObject.fromObject(signObject.put("message",message));
                }else if (message.equals("确认迟到")==true){
                    return JSONObject.fromObject(signObject.put("message",message));
                }else if (message.equals("用户不存在")==true){
                    return JSONObject.fromObject(signObject.put("message",message));
                }else {
                    return JSONObject.fromObject(signObject.put("message",message));
                }

            }
        }
    }

    @RequestMapping("/Late")
    @ResponseBody
    public  void Late(HttpSession session, HttpServletRequest request, @RequestParam Integer emplId, @RequestParam String date, @RequestParam String time,@RequestParam String reason) throws ParseException {
        if (reason.equals("确认")){
            signService.late(emplId,date,time,reason);
        }else if (reason.equals("病假")){
            signService.late(emplId,date,time,reason);
        }else if (reason.equals("事假")){
            signService.late(emplId,date,time,reason);
        }else {
            signService.late(emplId,date,time,reason);
        }
    }

    @RequestMapping("/Leave")
    @ResponseBody
    public  void Leave(HttpSession session, HttpServletRequest request, @RequestParam Integer emplId, @RequestParam String date, @RequestParam String time,@RequestParam String reason) throws ParseException {
        if (reason.equals("确认")){
            signService.updateSignOutState(emplId,reason,time,date);
        }else if (reason.equals("病假")){
            signService.updateSignOutState(emplId,reason,time,date);
        }else if (reason.equals("事假")){
            signService.updateSignOutState(emplId,reason,time,date);
        }else if(reason.equals("加班")){
            signService.updateSignOutState(emplId,reason,time,date);
        }else {
            signService.updateSignOutState(emplId,reason,time,date);
        }
    }



}
