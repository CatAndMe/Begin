package com.mjl.service;

import com.mjl.model.Sign;

import java.util.List;

public interface SignService {
    public Sign selectByEmplId(Integer EmplId);
    public Sign selectByEmplName(String EmplName);
    public List<Sign> getAllSign();
    List<Sign> getSignLogsByPage(Integer page,Integer pageSize);
    int getAllSignCount();
    public boolean addSign(Sign sign);
}
