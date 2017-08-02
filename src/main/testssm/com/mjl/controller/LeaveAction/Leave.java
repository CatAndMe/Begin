package com.mjl.controller.LeaveAction;

import com.mjl.model.Sign;
import com.mjl.model.User;
import com.mjl.service.SignService;
import net.sf.json.JSONObject;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Object leave(@RequestParam String state,@RequestParam String reason,@RequestParam String leaveTime, HttpServletRequest request,HttpServletResponse response){
        String State=state;
        String Reason=reason;
        String LeaveTime=leaveTime;


        User user=(User)request.getSession().getAttribute("user");

        int formId=signService.getAllSignCount()+1;

        int userId=user.getId();//获取id

        String username=user.getUsername();//获取名字
        Map<String,Object> messageJeson=new HashMap<String, Object>();

        //判断是否重复签到
        if (state.equals("迟到")){
            if (signService.getAllSignCount(leaveTime,userId)==0){
                Sign leaveSign=new Sign();
                leaveSign.setFormId(formId);
                leaveSign.setEmplName(username);
                leaveSign.setTime(leaveTime);
                leaveSign.setEmplId(userId);
                leaveSign.setLoginState(reason);
                signService.addSign(leaveSign);
            }else{
                String message="一天内不能重复签到~(～￣▽￣)～";
                messageJeson.put("message",message);
                return JSONObject.fromObject(messageJeson);

            }

        }else{
            if (signService.getAllSignCount(leaveTime,userId)==1){
                signService.updateSignOutState(reason,leaveTime);
            }else {
                String message="这天没有签到(ToT)/~~~";
                messageJeson.put("message",message);
                return JSONObject.fromObject(messageJeson);

            }

        }

        return null;

    }
}
