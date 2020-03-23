package com.example.demo_initializer;

import com.example.demo_initializer.Controllers.BookingController;
import com.example.demo_initializer.Controllers.FullController;
import com.example.demo_initializer.Repositories.BookingRepository;
import com.example.demo_initializer.components.Booking;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class FullControllerBookingTest {


    @InjectMocks
    FullController fullControllerl;

    @Mock
    BookingRepository bookingRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateBooking()
    {
        User newUser = new User("ionut","ionutpass","0746656076","ionut@email.com","ileanda");
        Hotel newHotel = new Hotel("Delfin","Principala","Mamaia","0741912423");
        Room newRoom = new Room(250,1,6,2,true,newHotel);
        Booking newBooking= new Booking(newUser,newRoom,250,4);

        when(bookingRepository.save(newBooking)).thenReturn(newBooking);

        Booking result = fullControllerl.createBooking(newBooking);

        assertEquals(newBooking,result);

    }

}
