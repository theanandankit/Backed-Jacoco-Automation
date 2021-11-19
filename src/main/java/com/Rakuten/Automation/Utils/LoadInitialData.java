package com.Rakuten.Automation.Utils;

import com.Rakuten.Automation.Model.User;
import com.Rakuten.Automation.Service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

@Controller
@Setter
public class LoadInitialData {

    @Autowired
    private UserService service;

    public boolean LoadData(String path) throws FileNotFoundException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        User[] list = gson.fromJson(new FileReader(path), User[].class);

        List<User> result = service.saveUsers(Arrays.asList(list));

        if (result == null) {
            return false;
        }

        return true;
    }
}
