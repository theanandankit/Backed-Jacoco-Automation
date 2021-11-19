package com.Rakuten.Automation.Controller.Impl;

import com.Rakuten.Automation.Controller.UserController;
import com.Rakuten.Automation.Model.EntryInfo;
import com.Rakuten.Automation.Model.User;
import com.Rakuten.Automation.Service.UserService;
import com.Rakuten.Automation.Utils.LoadInitialData;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@Setter
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoadInitialData loadInitialData;

    @Override
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {

        List<User> result = userService.getUsers();

        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(result);

    }

    @Override
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer userId) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        User result = userService.getUserById(userId);

        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(result);
    }

    @Override
    @GetMapping("/user/adduser")
    public ResponseEntity<EntryInfo> saveData() throws FileNotFoundException {
        boolean result = loadInitialData.LoadData("src/main/resources/dataset.json");

        if (!result) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new EntryInfo("Not saved"));
        }

        return ResponseEntity.ok().body(new EntryInfo("Successfully Data imported to Database"));
    }


}
