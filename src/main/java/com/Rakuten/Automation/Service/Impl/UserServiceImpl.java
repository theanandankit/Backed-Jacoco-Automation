package com.Rakuten.Automation.Service.Impl;

import com.Rakuten.Automation.Model.User;
import com.Rakuten.Automation.Repositories.UserRepository;
import com.Rakuten.Automation.Service.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        if (id == null) {
            return null;
        }

        return userRepository.getById(id);
    }

    @Override
    public List<User> saveUsers(List<User> user) {
        if (user == null) {
            return null;
        }

        List<User> res = userRepository.saveAll(user);
        return res;
    }


}
