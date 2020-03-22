package com.example.demo_initializer;



import com.example.demo_initializer.Controllers.HotelController;
import com.example.demo_initializer.Repositories.HotelRepository;
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


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


public class MockitoHotelTest {

    @InjectMocks
    HotelController hotelController;

    @Mock
    HotelRepository hotelRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }


    @Test
        public void  testGetAllHotels()
    {
        List<Hotel> myList=new ArrayList<Hotel>();
        Hotel newHotel = new Hotel("Delfin","Principala","Mamaia","0741912423");
        Hotel newHotel2 = new Hotel("Delfin2","Principala2","Mamaia2","0741912423");
        myList.add(newHotel);
        myList.add(newHotel2);
        when(hotelRepository.findAll()).thenReturn(myList);

        List<Hotel> returnedList = hotelController.getall();
        System.out.println(returnedList.get(0).getHotelName()+returnedList.get(1).getHotelName());
        assertEquals(myList,returnedList);

    }







}
