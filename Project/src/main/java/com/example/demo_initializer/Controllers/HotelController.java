package com.example.demo_initializer.Controllers;


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

    @PutMapping(value = "/update/{id}")
    public @ResponseBody ResponseEntity<String> update(@RequestBody Hotel hotel , @PathVariable  Long id)
    {
        Hotel foo= hotelRepository.findById(id).orElse(null);
        if(foo != null)
        {
           foo.setCity(hotel.getCity());
           foo.setHotelName(hotel.getHotelName());
           foo.setPhone(hotel.getPhone());
           foo.setStreet(hotel.getStreet());
           hotelRepository.save(foo);
            return new ResponseEntity<>("Hotel successful updated!",HttpStatus.OK);
        }
        else
        {
            hotel.setHotelId(id);
            hotelRepository.save(hotel);
            return new ResponseEntity<>("Hotel not found, but added to database successful",HttpStatus.OK);
        }

    }

    @DeleteMapping(value= "/deletebyid/{id}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable long id)
    {
        Hotel foo = hotelRepository.findById(id).orElse(null);
        if(foo!= null)
        {
            hotelRepository.deleteById(id);
            return new ResponseEntity<>("Hotel deleted successful!",HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Hotel not found, try other  hotel id!",HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteall")
    public @ResponseBody ResponseEntity<String> deleteall()
    {
        hotelRepository.deleteAll();

        return new ResponseEntity<>( "All hotels deleted!", HttpStatus.OK);
    }



}
