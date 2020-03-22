package com.example.demo_initializer;



import com.example.demo_initializer.Controllers.AdminController;
import com.example.demo_initializer.Controllers.HotelController;
import com.example.demo_initializer.Repositories.AdminRepository;
import com.example.demo_initializer.Repositories.HotelRepository;
import com.example.demo_initializer.components.Admin;
import com.example.demo_initializer.components.Hotel;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


public class MockitoAdminTest {

    @InjectMocks
    AdminController adminController;

    @Mock
    AdminRepository adminRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void  testGetAllAdmins()
    {
        List<Admin> myList=new ArrayList<Admin>();
        Admin newAdmin = new Admin("ionut1","ionut1");
        Admin newAdmin1 = new Admin("ionut2","ionut2");
        myList.add(newAdmin);
        myList.add(newAdmin1);
        when(adminRepository.findAll()).thenReturn(myList);

        List<Admin>returnedList = adminController.getall();
        assertEquals(myList,returnedList);

    }







}
