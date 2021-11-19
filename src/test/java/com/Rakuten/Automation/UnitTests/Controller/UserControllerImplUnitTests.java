package com.Rakuten.Automation.UnitTests.Controller;

import com.Rakuten.Automation.Controller.Impl.UserControllerImpl;
import com.Rakuten.Automation.Model.EntryInfo;
import com.Rakuten.Automation.Model.User;
import com.Rakuten.Automation.Service.UserService;
import com.Rakuten.Automation.Utils.LoadInitialData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerImplUnitTests {

    private static final Integer USER_ID = 1;

    private static final String FIRST_NAME = "Ankit";

    private static final String LAST_NAME = "Kumar";

    private static final String GENDER = "M";

    private static final String PASSWORD = "1234";

    private static final String DATA_PATH = "src/main/resources/dataset.json";

    private UserControllerImpl controller;

    private User user;

    private List<User> list;

    @Mock
    private UserService service;

    @Mock
    private LoadInitialData loadInitialData;

    @Before
    public void setup() {
        controller = new UserControllerImpl();

        user = new User();
        user.setId(USER_ID);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setGender(GENDER);
        user.setPassword(PASSWORD);

        list = new ArrayList<>();
        list.add(user);

        controller.setUserService(service);
        controller.setLoadInitialData(loadInitialData);
    }

    @Test
    public void getAllUser() {

        when(service.getUsers()).thenReturn(list);

        ResponseEntity<List<User>> result = controller.getAllUser();

        assertThat(result.getBody(), notNullValue());
        assertThat(result.getBody().size(), equalTo(1));
        assertThat(result.getBody().get(0).getFirstName(), equalTo(FIRST_NAME));
        assertThat(result.getBody().get(0).equals(user), equalTo(true));
    }

    @Test
    public void getAllUserWhenReturnNull() {

        when(service.getUsers()).thenReturn(null);

        ResponseEntity<List<User>> result = controller.getAllUser();

        assertThat(result.getBody(), equalTo(null));
    }

    @Test
    public void getUserByUuid() {
        when(service.getUserById(USER_ID)).thenReturn(user);

        ResponseEntity<User> result = controller.getUserById(1);

        assertThat(result.getBody(), notNullValue());
        assertThat(result.getBody().getFirstName(), equalTo(FIRST_NAME));
    }

    @Test
    public void getUserByUuidWhenIdIsNull() {
        ResponseEntity<User> result = controller.getUserById(null);

        assertThat(result.getBody(), equalTo(null));
        assertThat(result.getStatusCode().toString(), equalTo(HttpStatus.BAD_REQUEST.toString()));
    }

    @Test
    public void getUserByUuidWhenIdIsNotInDatabase() {
        when(service.getUserById(USER_ID)).thenReturn(null);

        ResponseEntity<User> result = controller.getUserById(USER_ID);

        assertThat(result.getBody(), equalTo(null));
        assertThat(result.getStatusCode().toString(), equalTo(HttpStatus.NOT_FOUND.toString()));
    }

    @Test
    public void saveInitialData() throws FileNotFoundException {
        when(loadInitialData.LoadData(DATA_PATH)).thenReturn(true);

        ResponseEntity<EntryInfo> result = controller.saveData();

        assertThat(result.getBody(), notNullValue());
        assertThat(result.getBody().getStatus(), equalTo("Successfully Data imported to Database"));
    }

    @Test
    public void saveInitialDataWhenDataNotSaved() throws FileNotFoundException {
        when(loadInitialData.LoadData(DATA_PATH)).thenReturn(false);

        ResponseEntity<EntryInfo> result = controller.saveData();

        assertThat(result.getBody(), notNullValue());
        assertThat(result.getBody().getStatus(), equalTo("Not saved"));
    }

}
