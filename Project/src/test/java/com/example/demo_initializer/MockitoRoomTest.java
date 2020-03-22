package com.example.demo_initializer;




import com.example.demo_initializer.Controllers.RoomController;

import com.example.demo_initializer.Repositories.RoomRepository;

import com.example.demo_initializer.components.Hotel;
import com.example.demo_initializer.components.Room;
import org.junit.Before;
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


public class MockitoRoomTest {

    @InjectMocks
    RoomController roomController;

    @Mock
    RoomRepository roomRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void  testGetAllRooms()
    {
        List<Room> myList=new ArrayList<Room>();
        Hotel newHotel = new Hotel("Delfin","Principala","Mamaia","0741912423");
        Room newRoom = new Room(200,1,4,2,true,newHotel);
        Room newRoom2 = new Room(220,1,5,3,true,newHotel);
        myList.add(newRoom);
        myList.add(newRoom2);
        when(roomRepository.findAll()).thenReturn(myList);

        List<Room> returnedList = roomController.getAll();

        assertEquals(myList,returnedList);

    }







}
