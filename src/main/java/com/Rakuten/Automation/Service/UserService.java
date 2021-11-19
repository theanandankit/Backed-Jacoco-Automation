package com.Rakuten.Automation.Service;

import com.Rakuten.Automation.Model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User getUserById(Integer id);

    List<User> saveUsers(List<User> user);
}
