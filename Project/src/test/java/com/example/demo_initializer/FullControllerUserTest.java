package com.example.demo_initializer;

import com.example.demo_initializer.Controllers.FullController;
import com.example.demo_initializer.Controllers.UserController;
import com.example.demo_initializer.Repositories.UserRepository;
import com.example.demo_initializer.components.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class FullControllerUserTest {
    @InjectMocks
    FullController fullController;

    @Mock
    UserRepository userRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteAllUsers()
    {
       // when(userRepository.deleteAll()).thenReturn(null);
        doNothing().when(userRepository).deleteAll();
        User user = fullController.deleteAllUsers();

        assertEquals(null,user);
    }

    @Test
    public void findUserByUsernameTest()
    {
        User newUser = new User("ionut","ionutpass","0746656076","ionut@email.com","ileanda","");
        User newUser2 = new User("ionut2","ionutpass2","0746656000","ionut2@email.com","ileanda","");

        when(userRepository.findByUsername("ionut")).thenReturn(newUser);

        User result = fullController.findUserByUsername("ionut");

        assertEquals(newUser,result);
    }

    @Test
    public void findUserByCityTest()
    {
        User newUser = new User("ionut","ionutpass","0746656076","ionut@email.com","Ileanda","");
        User newUser2 = new User("ionut2","ionutpass2","0746656000","ionut2@email.com","Cluj","");
        User newUser3 = new User("ionut3","ionutpass3","0746656001","ionut3@email.com","Cluj","");

        List<User> expected =new ArrayList<User>();
        expected.add(newUser2);
        expected.add(newUser3);
        when(userRepository.findByCity("Cluj")).thenReturn(expected);

        List<User> result = fullController.findUserByCity("Cluj");

        assertEquals(expected,result);
    }


}
