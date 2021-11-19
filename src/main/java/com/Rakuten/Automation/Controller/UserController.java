package com.Rakuten.Automation.Controller;

import com.Rakuten.Automation.Model.EntryInfo;
import com.Rakuten.Automation.Model.User;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.util.List;

public interface UserController {

    ResponseEntity<List<User>> getAllUser() throws FileNotFoundException;

    ResponseEntity<User> getUserById(Integer userId);

    ResponseEntity<EntryInfo> saveData() throws FileNotFoundException;

}
