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

    public List<Sign> getSignByTimeAndId(String startTime, String endTime, Integer Id) {
        return signMapper.getSignByTimeAndId(Id,startTime,endTime);
    }

    public List<Sign> getSignLogsByPage(Integer page, Integer pageSize,String startTime,String endTime,Integer Id) {
//        time.replaceAll("-","");
        page=(page-1)*pageSize;
        return signMapper.getSignLogsByPage(page,pageSize,Id,startTime,endTime);
    }

    public int getAllSignCount(String startTime,String endTime,Integer Id) {
            return signMapper.getAllSignCount(Id,startTime,endTime);

    }

    public int getAllSignCount() {
        return signMapper.getAllSignCount(0,null,null);
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
                sign.setFormId(signMapper.getAllSignCount(0,null,null)+1);
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
        Integer Id=emplId;
        User user=Mapper.selectById(emplId);
        Date time_=null;
        SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss");
        time_=formatter.parse(time);
        Date leaveDate=formatter.parse("19:00:00");
        if (user!=null){
            if(time_.getTime()-leaveDate.getTime()<0){
                return "确认早退";
            }else{
                signMapper.updateSignOutState(time,"签退",date,null,null);
                return  "成功";
            }
        }else {
            return "用户不存在";
        }
    }

    public boolean late(Integer emplId, String date, String time,String state) throws ParseException {
        Integer Id=emplId;
        User user=Mapper.selectById(Id);
        Sign sign=new Sign();
        sign.setFormId(signMapper.getAllSignCount(0,null,null)+1);
        sign.setEmplName(user.getUsername());
        sign.setEmplId(emplId);
        sign.setLoginState(state);
        sign.setLogin(time);
        sign.setTime(date);

        if (signMapper.addSign(sign)>0){
            return true;
        }else {
            return false;
        }

    }

    public boolean updateSignOutState(Integer emplId,String state,String leaveTime,String trueTime) {
        List<Sign> signLog=getAllSignById(emplId);
        Sign lastSignLog=signLog.get(signLog.size()-1);
        leaveTime=leaveTime.toString();
        return signMapper.updateSignOutState(leaveTime,state,null,trueTime,lastSignLog.getFormId());
    }
}
