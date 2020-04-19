package com.example.demo_initializer;

import com.example.demo_initializer.Controllers.FullController;
import com.example.demo_initializer.Controllers.HotelController;
import com.example.demo_initializer.Repositories.HotelRepository;
import com.example.demo_initializer.components.Admin;
import com.example.demo_initializer.components.Hotel;
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

public class FullControllerHotelTest {
    @InjectMocks
    FullController fullController;

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
    public void testUpdateHotel()
    {
        Hotel newHotel = new Hotel("Delfin","Principala","Mamaia","0741912423");
        Long my_id = Long.valueOf(3);
        when(hotelRepository.save(newHotel)).thenReturn(newHotel);

        Hotel result = fullController.updateHotel(newHotel,my_id);

        assertEquals(newHotel,result);
    }

    @Test
    public void testFindByCity()
    {
        Hotel newHotel = new Hotel("Delfin","Principala","Mamaia","0741912423");

        List<Hotel> expected = new ArrayList<Hotel>();
        expected.add(newHotel);
        when(hotelRepository.findByCity("Mamaia")).thenReturn(expected);

        List<Hotel> result = fullController.findHotelByCity("Mamaia");

        assertEquals(expected,result);

    }

    @Test
    public void testFindHotelBySearchString()
    {
        Hotel newHotel = new Hotel("Delfin","Principala","Mamaia","0741912423");
        Hotel newHotel2 = new Hotel("Perla","Principala2","Brasov","0743212423");

        List<Hotel> expected = new ArrayList<Hotel>();
        expected.add(newHotel);
        List<Hotel> expected2 = new ArrayList<Hotel>();
        expected2.add(newHotel2);
        when(hotelRepository.findByHotelName("Delfin")).thenReturn(expected);
        when(hotelRepository.findByHotelName("Perla")).thenReturn(expected2);

        List<Hotel> expected_concat = new ArrayList<Hotel>();
        expected_concat.addAll(expected);
        expected_concat.addAll(expected2);

        List<Hotel> result = fullController.findHotelBySearchString("Delfin Perla");

        assertEquals(expected_concat,result);

    }



}
