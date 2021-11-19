package com.Rakuten.Automation.UnitTests.Service;

import com.Rakuten.Automation.Model.User;
import com.Rakuten.Automation.Repositories.UserRepository;
import com.Rakuten.Automation.Service.Impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplUnitTests {

    private static final Integer USER_ID = 1;

    private static final String USERNAME = "theanandankit";

    private static final String FIRST_NAME = "Ankit";

    private static final String LAST_NAME = "Kumar";

    private static final String GENDER = "M";

    private static final String PASSWORD = "1234";

    private User user;

    private UserServiceImpl service;

    private List<User> list;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        service = new UserServiceImpl();

        user = new User();
        user.setId(USER_ID);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setGender(GENDER);
        user.setPassword(PASSWORD);
        user.setUsername(USERNAME);

        list = new ArrayList<>();
        list.add(user);

        service.setUserRepository(userRepository);
    }

    @Test
    public void getUser() {
        when(userRepository.findAll()).thenReturn(list);

        List<User> result = service.getUsers();

        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(1));
        assertThat(result.get(0).getId(), equalTo(USER_ID));
    }

    @Test
    public void getUserById() {
        when(userRepository.getById(USER_ID)).thenReturn(user);

        User result = service.getUserById(USER_ID);

        assertThat(result, notNullValue());
        assertThat(result.getId(), equalTo(USER_ID));
        assertThat(result.getUsername(), equalTo(USERNAME));
    }

    @Test
    public void getUsersByIdWhenReturnNull() {

        User result = service.getUserById(null);

        assertThat(result, equalTo(null));
    }

    @Test
    public void saveUsers() {
        when(userRepository.saveAll(list)).thenReturn(list);

        List<User> result = service.saveUsers(list);

        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(1));
        assertThat(result.get(0).getId(), equalTo(USER_ID));
    }

    @Test
    public void saveUsersWhenListIsNull() {

        List<User> result = service.saveUsers(null);

        assertThat(result, equalTo(null));
    }
}
