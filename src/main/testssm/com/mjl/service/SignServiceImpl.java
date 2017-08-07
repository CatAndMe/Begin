package com.mjl.service;

import com.mjl.dao.IUserDao;
import com.mjl.dao.SignDao;
import com.mjl.model.Sign;
import com.mjl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.plugin2.message.Serializer;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("SignService")

public class SignServiceImpl implements SignService {
    @Autowired
    SignDao signMapper;
    @Autowired
    IUserDao Mapper;

    public Sign selectByEmplId(Integer EmplId) {
        return signMapper.selectByEmplId(EmplId);
    }

    public Sign selectByEmplName(String EmplName) {
        return null;
    }

    public List<Sign> getAllSign() {
        return signMapper.getAllSign();
    }

    public List<Sign> getSignLogsByPage(Integer page, Integer pageSize,String time,Integer id) {
//        time.replaceAll("-","");
        time=time.toString();
        page=(page-1)*pageSize;
        return signMapper.getSignLogsByPage(page,pageSize,time,id);
    }


    public int getAllSignCount(String time,int id) {
            return signMapper.getAllSignCount(time,id);

    }

    public int getAllSignCount() {
        return signMapper.getAllSignCount(null,0);
    }

    public boolean addSignByLeave(Sign sign) {
        if (signMapper.addSignByLeave(sign)>0){
            return true;
        }else {
            return false;
        }
    }


    public boolean addSign(Sign sign) {
        if (signMapper.addSign(sign)>0){
            return true;
        }else {
            return false;
        }

    }

    public List<Sign> selectByDate(String time) {
        time=time.toString();
        return signMapper.selectByDate(time);
    }

    public boolean updateSignOutState(String state,String leaveTime) {
        leaveTime=leaveTime.toString();
        return signMapper.updateSignOutState(null,state,leaveTime);
    }

    public boolean updateSignOut(String hours) {
        return false;
    }

    public List<Sign> getAllSignById(Integer id) {
        return signMapper.getAllSignById(id);
    }

    public String signLogin(Integer emplId, String date, String time) throws ParseException {
        Integer Id=emplId;
        User user=Mapper.selectById(Id);
        Date time_=null;
        SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss");
        time_=formatter.parse(time);
        Date loginDate=formatter.parse("09:30:00");
        if (user!=null){
            if(time_.getTime()-loginDate.getTime()>0){
                return "确认迟到";
            }else{
                Sign sign=new Sign();
                sign.setFormId(signMapper.getAllSignCount(null,0)+1);
                sign.setEmplName(user.getUsername());
                sign.setEmplId(emplId);
                sign.setLoginState("签到");
                sign.setLogin(time);
                sign.setTime(date);
                signMapper.addSign(sign);
                return  "成功";
            }
        }else {
            return "用户不存在";
        }

    }

    public String signOut(Integer emplId, String date, String time) throws ParseException {
        User user=Mapper.selectById(emplId);
        Date time_=null;
        SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss");
        time_=formatter.parse(time);
        if (user!=null){
            if(time_.before(formatter.parse("19:00:00"))==false){
                return "确认早退";
            }else{
                signMapper.updateSignOutState(time,"签退",date);
                return  "成功";
            }
        }else {
            return "用户不存在";
        }
    }
}
