package com.tmanagement.service;



import java.util.List;

import com.tmanagement.model.User;
import com.tmanagement.model.UserDto;

public interface UserService {

   
    List<User> findAll();
    User findOne(String username);

    User findById(int id);
    User updatePassword(User user);
    User findByUsername(String username);
}
