package com.example.demo_initializer;



import com.example.demo_initializer.Controllers.AdminController;

import com.example.demo_initializer.Controllers.UserController;
import com.example.demo_initializer.Repositories.AdminRepository;

import com.example.demo_initializer.Repositories.UserRepository;
import com.example.demo_initializer.components.Admin;

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

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;


public class MockitoUserTest {

    @InjectMocks
    UserController userController;

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
    public void  testGetAllUsers()
    {
        List<User> myList=new ArrayList<User>();
        User newUser = new User("ionut","ionutpass","0746656076","ionut@email.com","ileanda","");
        User newUser1 = new User("ionut1","ionutpass1","0746656076","ionut@email.com","ileanda","");
        myList.add(newUser);
        myList.add(newUser1);
        when(userRepository.findAll()).thenReturn(myList);

        List<User>returnedList = userController.getall();
        assertEquals(myList,returnedList);

    }







}
