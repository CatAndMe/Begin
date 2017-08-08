package com.mjl.controller.EmplAction;

import com.mjl.model.Sign;
import com.mjl.model.User;
import com.mjl.service.SignService;
import jdk.nashorn.internal.runtime.JSONListAdapter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller注解用于标示本类为web层控制组件
@Controller
//@RequestMapping("/employee")用于标定访问时对url位置
@RequestMapping("/employee")
//在默认情况下springmvc的实例都是单例模式,所以使用scope域将其注解为每次都创建一个新的实例
@Scope("prototype")
public class ViewAction {
    @Autowired
    SignService signService;
    //显示所有的记录
    @RequestMapping("/show")
    public String list(HttpServletRequest request){
        List<Sign> signList = signService.getAllSign();
        request.setAttribute("signList",signList);
        return "SignView/SignList";
    }
    //........
    //签到日志上传接口
    @RequestMapping("/uploadLogs")
    @ResponseBody
    public void uploadLogs(HttpServletResponse response, HttpServletRequest request, @RequestParam(required = true) String jsonInfo){
        JSONArray jsStr = JSONArray.fromObject(jsonInfo);
        Sign[] sign = (Sign[]) JSONArray.toArray(jsStr,Sign.class);
        for (int i=0;i<sign.length;i++){
            signService.addSign(sign[i]);
        }
    }

    @RequestMapping("/logListByPage")
    @ResponseBody
    public Object logListByPage(Model model,HttpServletRequest request, @RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String date,@RequestParam Integer emplId) throws ParseException {
        User user=(User)request.getSession().getAttribute("user");
        Integer total=0;
        Map<String,Object> pageObject=new HashMap<String, Object>();
        List<Sign> signList=null;
        if (emplId==null){
            emplId=0;
        }
        //处理分页参数
        if(pageNum==null||pageNum==0){
            pageNum=1;
        }
        if(pageSize==null||pageSize==0){
            pageSize=20;
        }
        //是否做了员工号查询
        if (emplId==0){
            //否，显示管理员和非管理员的内容
            if (user.getEmplAdmin()>0){
                total=signService.getAllSignCount(date,emplId);
            }else {
                total=signService.getAllSignCount(date,user.getId());
            }
        }else {
            //是，如果是管理员则查询
            if (user.getEmplAdmin()>0){
                total=signService.getAllSignCount(date,emplId);
            }else {
                total=signService.getAllSignCount(date,user.getId());
            }
        }
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
        if (emplId==0){
            //否，显示管理员和非管理员的内容
            if (user.getEmplAdmin()>0){
                signList = signService.getSignLogsByPage(pageNum, pageSize,date,emplId);
            }else {
                signList = signService.getSignLogsByPage(pageNum, pageSize,date,user.getId());
            }
        }else {
            //是，如果是管理员则查询
            if (user.getEmplAdmin()>0){
                signList = signService.getSignLogsByPage(pageNum, pageSize,date,emplId);
            }else {
                signList = signService.getSignLogsByPage(pageNum, pageSize,date,user.getId());
                if ((Integer)emplId!=(Integer)user.getId()==true){
                    pageObject.put("Message","没有查询权限(*＾-＾*)");
                }
            }
        }
            pageObject.put("Data", signList);
            pageObject.put("itemCount", signList == null ? 0 : signList.size());
            request.setAttribute("signList", signList);
            boolean message=signService.updateSignOutState(1,"忘签","08:00:00","2017:05:29");
            System.out.println(message);
        return JSONObject.fromObject(pageObject);
    }



}
