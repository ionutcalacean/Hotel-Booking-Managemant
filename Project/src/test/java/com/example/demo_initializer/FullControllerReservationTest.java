package com.example.demo_initializer;

import com.example.demo_initializer.Controllers.FullController;
import com.example.demo_initializer.Repositories.ReservationRepository;
import com.example.demo_initializer.components.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class FullControllerReservationTest {


    @InjectMocks
    FullController fullController;

    @Mock
    ReservationRepository reservationRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateReservation()
    {
        User newUser = new User("eugen","password1","0745617000","eugen@email.com","Ileanda","");
        Hotel newHotel = new Hotel("Delfin","Principala","Mamaia","0741912423");
        Room newRoom = new Room(250,1,6,2,true,newHotel);
        SimpleDateFormat dateformat3 = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 =new Date();
        Date date2 =new Date();
        Date date3 =new Date();
        Date date4 =new Date();
        try {
            date1 = dateformat3.parse("17/04/2020");
            date2 = dateformat3.parse("20/04/2020");
            date3 = dateformat3.parse("18/04/2020");
            date4 = dateformat3.parse("25/04/2020");
        }
     catch (ParseException e) {
         e.printStackTrace();
     }
        List<Reservation> reservations = new ArrayList<Reservation>();
        Reservation reservation1= new Reservation(date1,date2,newUser,newRoom);
        reservations.add(reservation1);
        Reservation reservation2= new Reservation(date3,date4,newUser,newRoom);

        when(reservationRepository.findAll()).thenReturn(reservations);

        when(reservationRepository.save(reservation2)).thenReturn(null);
        Reservation result = fullController.createReservation(reservation2);

        assertEquals(null,result);

    }
}
