package com.mjl.service;

import com.mjl.dao.SignDao;
import com.mjl.model.Sign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.plugin2.message.Serializer;

import java.io.Serializable;
import java.util.List;

@Service("SignService")

public class SignServiceImpl implements SignService {
    @Autowired
    SignDao signMapper;

    public Sign selectByEmplId(Integer EmplId) {
        return signMapper.selectByEmplId(EmplId);
    }

    public Sign selectByEmplName(String EmplName) {
        return null;
    }

    public List<Sign> getAllSign() {
        return signMapper.getAllSign();
    }

    public List<Sign> getSignLogsByPage(Integer page, Integer pageSize,String time) {
//        time.replaceAll("-","");
        time=time.toString();
        page=(page-1)*pageSize;
        return signMapper.getSignLogsByPage(page,pageSize,time);
    }


    public int getAllSignCount(String time) {
        return signMapper.getAllSignCount(time);
    }

    public int getAllSignCount() {
        return signMapper.getAllSignCount(null);
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
}
