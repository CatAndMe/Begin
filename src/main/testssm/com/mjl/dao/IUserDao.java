package com.mjl.dao;

import com.mjl.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by alvin on 15/9/7.
 * 此类为接口模式下的配置
 */

public interface IUserDao {
    //这里以接口形式定义了数据库操作方法,我们只需
    // 在Mybatis映射文件中对其进行映射就可以直接使用
    User selectById(Integer EmplId);
    User selectByName(String EmplName);
    List<User> getAllUser();
    List<User> getUserByPage(@Param("start") Integer start, @Param("end")Integer end,@Param("emplName")String emplName,@Param("emplId")Integer emplId);
    Integer gerAllUserCount(@Param("EmplName")String EmplName,@Param("EmplId")Integer EmplId);
    boolean addUser(User user);
    boolean deleteById(Integer EmplId);
    boolean updateUserById(User user);
    boolean updateEmailById(@Param("EmplId_")Integer EmplId,@Param("EmplEmail_")String EmplEmail);
}
