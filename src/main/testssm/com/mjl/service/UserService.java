package com.mjl.service;

import com.mjl.model.User;
import org.springframework.ui.Model;

import java.util.List;

/**
 * Created by alvin on 15/9/7.
 */
public interface UserService {
    public boolean login(int userId,String password);
    public List<User> getAllUser();
    public User selectById(Integer id);
    public User selectByName(String username);
    public boolean addUser(User useradd);
    public boolean deleteById(Integer id);
    public boolean updateUserById(User userUpdate);
}
