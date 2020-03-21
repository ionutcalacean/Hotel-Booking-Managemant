package com.example.demo_initializer;


import com.example.demo_initializer.Repositories.HotelRepository;
import com.example.demo_initializer.components.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/hotel")
public class HotelController {

    private HotelRepository hotelRepository;

    @Autowired
    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public List<Hotel> getall() {
        return hotelRepository.findAll();
    }

    @PostMapping(value = "/create")
    public @ResponseBody
    ResponseEntity<String> create(@RequestBody Hotel hotel)
    {
        hotelRepository.save(hotel);

        return new ResponseEntity<>(
                "Hotel added succesfull: "+ hotel.getHotelName()+" S!",
                HttpStatus.OK);
    }

    @PostMapping(value = "/deleteall")
    public @ResponseBody ResponseEntity<String> deleteall()
    {
        hotelRepository.deleteAll();

        return new ResponseEntity<>( "All hotels deleted!", HttpStatus.OK);
    }



}
