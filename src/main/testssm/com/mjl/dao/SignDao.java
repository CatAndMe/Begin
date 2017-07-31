package com.mjl.dao;

import com.mjl.model.Sign;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SignDao {
    Sign selectByEmplId(Integer EmplId);
    Sign selectByEmplName(String EmplName);
    List<Sign> getAllSign();
    List<Sign> getSignLogsByPage(@Param("start") Integer start, @Param("end")Integer end,@Param("time")String time);
    int getAllSignCount(@Param("time")String time);
    int addSign(Sign sign);
    int addSignByLeave(Sign sign);
    List<Sign> selectByDate(String time);

}
