package com.mjl.service;

import com.mjl.model.Sign;

import java.text.ParseException;
import java.util.List;

public interface SignService {
    public Sign selectByEmplId(Integer EmplId);
    public Sign selectByEmplName(String EmplName);
    public List<Sign> getAllSign();
    List<Sign> getSignByTimeAndId(String startTime,String endTime,Integer Id);
    List<Sign> getSignLogsByPage(Integer page,Integer pageSize,String startTime,String endTime,Integer Id);
    int getAllSignCount(String startTime,String endTime,Integer Id);
    int getAllSignCount();
    public boolean addSign(Sign sign);
    List<Sign> selectByDate(String time);
    List<Sign> getAllSignById(Integer id);
    String signLogin(Integer emplId,String date,String time) throws ParseException;
    String signOut(Integer emplId,String date,String time) throws ParseException;
    boolean late(Integer emplId,String date,String time,String state)throws ParseException;
    boolean updateSignOutState(Integer emplId,String state,String leaveTime,String trueTime)throws ParseException;
}
