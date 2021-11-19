package com.Rakuten.Automation.UnitTests.Utils;

import com.Rakuten.Automation.Model.User;
import com.Rakuten.Automation.Repositories.UserRepository;
import com.Rakuten.Automation.Service.UserService;
import com.Rakuten.Automation.Utils.LoadInitialData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoadInitialDataTests {

    private static final Integer USER_ID = 1;

    private static final String FIRST_NAME = "Ankit";

    private static final String LAST_NAME = "Kumar";

    private static final String GENDER = "M";

    private static final String PASSWORD = "1234";

    private static final String DATA_PATH = "src/main/resources/dataset.json";

    private LoadInitialData loadInitialData;

    private User user;

    private List<User> list;

    @Mock
    private UserService service;

    @Before
    public void setup() {
        loadInitialData = new LoadInitialData();

        user = new User();
        user.setId(USER_ID);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setGender(GENDER);
        user.setPassword(PASSWORD);

        list = new ArrayList<>();
        list.add(user);

        loadInitialData.setService(service);
    }

    @Test
    public void LoadData() throws FileNotFoundException {
        when(service.saveUsers(any())).thenReturn(list);

        boolean result = loadInitialData.LoadData(DATA_PATH);

        assertThat(result, notNullValue());
        assertThat(result, equalTo(true));
    }

    @Test
    public void LoadDataWhenDataNotFetched() throws FileNotFoundException {
        when(service.saveUsers(any())).thenReturn(null);

        boolean result = loadInitialData.LoadData(DATA_PATH);

        assertThat(result, notNullValue());
        assertThat(result, equalTo(false));
    }
}
