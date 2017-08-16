package com.mjl.dao;

import com.mjl.model.Sign;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SignDao {
    Sign selectByEmplId(Integer EmplId);
    Sign selectByEmplName(String EmplName);
    List<Sign> getAllSign();
    List<Sign> getAllSignById(@Param("id")Integer id);
    List<Sign> getSignByTimeAndId(@Param("id")Integer id,@Param("startTime")String startTime,@Param("endTime")String endTime);
    List<Sign> getSignLogsByPage(@Param("start") Integer start, @Param("end")Integer end,@Param("id")Integer id,@Param("startTime")String startTime,@Param("endTime")String endTime);
    int getAllSignCount(@Param("id")Integer id,@Param("startTime")String startTime,@Param("endTime")String endTime);
    int addSign(Sign sign);
    int addSignByLeave(Sign sign);
    List<Sign> selectByDate(String time);
    boolean updateSignOutState(@Param("signOut")String signOut,@Param("signOutState")String signOutState,@Param("time") String time,@Param("trueTime")String trueTime,@Param("formId")Integer formId);

}
