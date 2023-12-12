package com.newproject.ecomm.service;

import com.newproject.ecomm.model.User;

public interface UserService {
    public User saveUser(User user);
    public User updateUserCreds(User user, String key);
    public  String userLogOut(String key);
}
