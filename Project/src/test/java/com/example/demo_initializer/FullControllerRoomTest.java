package com.example.demo_initializer;

import com.example.demo_initializer.Controllers.FullController;
import com.example.demo_initializer.Controllers.RoomController;
import com.example.demo_initializer.Repositories.RoomRepository;
import com.example.demo_initializer.Repositories.UserRepository;
import com.example.demo_initializer.components.Hotel;
import com.example.demo_initializer.components.Room;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class FullControllerRoomTest {
    @InjectMocks
    FullController fullController;

    @Mock
    RoomRepository roomRepository;
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
    public void deleteRoomTest()
    {
        //when(roomRepository.findById(anyLong())).thenReturn(null);

        Long id = Long.valueOf(3);
        Room result = fullController.deleteRoom(id);

        assertEquals(null,result);
    }

    @Test
    public void checkRoomsTest()
    {
        Hotel newHotel = new Hotel("Delfin","Principala","Mamaia","0741912423");
        Room newRoom = new Room(250,1,6,2,false,newHotel);
        Room newRoom1 = new Room(250,1,7,2,false,newHotel);
        List<Room> roomsList = new ArrayList<Room>();
        roomsList.add(newRoom);
        roomsList.add(newRoom1);
        when(roomRepository.findAll()).thenReturn(roomsList);

        User newUser = new User("ionut","ionutpass","0746656076","ionut@email.com","ileanda","");
        User newUser1 = new User("ioana","ionutpass","0746656076","ioana@email.com","ileanda","");

        List<User> usersList = new ArrayList<User>();
        usersList.add(newUser);
        usersList.add(newUser1);

        when(userRepository.findAll()).thenReturn(usersList);

        List<User> users=fullController.checkRooms();

            for(User usr: users)
                assertEquals("No free rooms!",usr.getNews());
    }


}
