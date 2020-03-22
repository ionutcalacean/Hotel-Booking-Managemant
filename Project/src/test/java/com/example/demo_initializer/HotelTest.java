package com.example.demo_initializer;

import com.example.demo_initializer.Repositories.HotelRepository;
import com.example.demo_initializer.components.Hotel;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class HotelTest {

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    public void createHotelTest()
    {
        Hotel newHotel = new Hotel("Delfin","Principala","Mamaia","0741912423");
        hotelRepository.save(newHotel);
        Hotel searchHotel = hotelRepository.findByHotelNameAndAndCity("Delfin","Mamaia");
        assertNotNull(newHotel);
        assertEquals(newHotel,searchHotel);
    }


}
