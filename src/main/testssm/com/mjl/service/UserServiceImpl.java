package com.mjl.service;

import com.mjl.dao.IUserDao;
import com.mjl.model.Sign;
import com.mjl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by alvin on 15/9/7.
 */
//@Service("UserService") 注解用于标示此类为业务层组件,在使用时会被注解的类会自动由
    //spring进行注入,无需我们创建实例
@Service("userService")
public class UserServiceImpl implements UserService{

    //自动注入iuserdao 用于访问数据库
    @Autowired
    IUserDao Mapper;

    //登录方法的实现,从jsp页面获取username与password
    public boolean login(int userId, String password) {
//        System.out.println("输入的账号:" + username + "输入的密码:" + password);
        //对输入账号进行查询,取出数据库中保存对信息
        User user = Mapper.selectById(userId);
        if (user != null) {
//            System.out.println("查询出来的账号:" + user.getUsername() + "密码:" + user.getPassword());
//            System.out.println("---------");
            if (user.getId().equals(userId) && user.getPassword().equals(password))
                return true;

        }
        return false;

    }

    public List<User> getAllUser() {
        return Mapper.getAllUser();
    }

    public User selectById(Integer id) {
        return Mapper.selectById(id);
    }

    public User selectByName(String username) {
        return Mapper.selectByName(username);
    }

    public boolean addUser(User useradd) {
        //对输入账号进行查询,取出数据库中保存对信息

        if (Mapper.addUser(useradd)>0) {
                return true;
        }else{
            return false;
        }

    }

    public boolean deleteById(Integer id) {
        User user = Mapper.selectById(id);
        if (user != null) {
            Mapper.deleteById(id);
                return true;
        }else {
            return false;
        }

    }

    public boolean updateUserById(User userUpdate) {
        User user = Mapper.selectById(userUpdate.getId());
        if (user != null) {
            Mapper.updateUserById(userUpdate);
            return true;
        }else {
            return false;
        }

    }

    public boolean updateEmailById(Integer id_, String email_) {
        Integer id=(Integer)id_;
        String email=(String) email_;
        if (Mapper.updateEmailById(id,email) == true) {
            return true;
        }else {
            return false;
        }
    }


    public Integer gerAllUserCount(String emplName, Integer emplId) {

        return Mapper.gerAllUserCount(emplName,emplId);
    }

    public List<User> gerUserByPage(Integer page, Integer pageSize, String emplName, Integer emplId) {
//        time.replaceAll("-","");
        emplName=emplName.toString();
        Integer EmplId=emplId;
        page=(page-1)*pageSize;
        return Mapper.getUserByPage(page,pageSize,emplName,EmplId);
    }


}