package com.mjl.dao;

import com.mjl.model.User;

import java.util.List;


/**
 * Created by alvin on 15/9/7.
 * 此类为接口模式下的配置
 */

public interface IUserDao {
    //这里以接口形式定义了数据库操作方法,我们只需
    // 在Mybatis映射文件中对其进行映射就可以直接使用
    public User selectById(Integer id);
    public User selectByName(String username);
    List<User> getAllUser();
    public boolean addUser(User user);
    public boolean deleteById(Integer id);
    public boolean updateUserById(User user);
}
