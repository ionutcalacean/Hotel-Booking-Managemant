package com.example.demo_initializer;

import com.example.demo_initializer.Controllers.FullController;
import com.example.demo_initializer.Controllers.RoomController;
import com.example.demo_initializer.Repositories.RoomRepository;
import com.example.demo_initializer.components.Room;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class FullControllerRoomTest {
    @InjectMocks
    FullController fullController;

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
    public void deleteRoomTest()
    {
        //when(roomRepository.findById(anyLong())).thenReturn(null);

        Long id = Long.valueOf(3);
        Room result = fullController.deleteRoom(id);

        assertEquals(null,result);
    }


}
