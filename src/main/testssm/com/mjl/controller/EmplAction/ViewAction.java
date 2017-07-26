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
    public Object logListByPage(Model model,HttpServletRequest request, @RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String date){
        //处理分页参数
        if(pageNum==null||pageNum==0){
            pageNum=1;
        }
        if(pageSize==null||pageSize==0){
            pageSize=20;
        }
        int total=signService.getAllSignCount();
        int totalPages=(int)Math.ceil(Float.valueOf(total)/pageSize);//向上取整，格式化total

        Map<String,Object> pageObject=new HashMap<String, Object>();
        pageObject.put("pageNum",pageNum); //页码
        pageObject.put("pageSize",pageSize);//页数
        pageObject.put("totalItemCount",total);  //总条目数
        pageObject.put("totalPageCount",totalPages);//总页面
        pageObject.put("hasNextPage",pageNum<totalPages);
        pageObject.put("hasPrefPage",pageNum>1);
        if(pageNum>totalPages){
            //超出页码

        }


            List<Sign> signList = signService.getSignLogsByPage(pageNum, pageSize,date);
            pageObject.put("Data", signList);
            pageObject.put("itemCount", signList == null ? 0 : signList.size());
            request.setAttribute("signList", signList);

        return JSONObject.fromObject(pageObject);
    }



}
