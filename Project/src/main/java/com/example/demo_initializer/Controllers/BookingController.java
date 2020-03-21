package com.example.demo_initializer.Controllers;

import com.example.demo_initializer.Repositories.BookingRepository;
import com.example.demo_initializer.components.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value="/booking")
public class BookingController {
    private BookingRepository bookingRepository;


    @Autowired
    public BookingController(BookingRepository bookingRepository){
    this.bookingRepository=bookingRepository;
    }

    @RequestMapping(value="/getall",method= RequestMethod.GET)
    public List<Booking>getAll()
    {

        return bookingRepository.findAll();
    }

    @RequestMapping(value = "/getlowcost/{price}",method = RequestMethod.GET)
    public List<Booking> getLowCost(@PathVariable double price)
    {
        return bookingRepository.findByPriceLessThan(price);
    }

    @RequestMapping(value = "/create",method=RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> create(@RequestBody Booking booking )
    {
        try{
            bookingRepository.save(booking);
        }
        catch(DataIntegrityViolationException e)
        {
            return new ResponseEntity<>("Data intergrity Violation Exception!",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Booking successful added to database!",HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public @ResponseBody ResponseEntity<String> update(@RequestBody Booking booking , @PathVariable  Long id)
    {
        Booking foo= bookingRepository.findById(id).orElse(null);
        if(foo != null)
        {
            foo.setUser(booking.getUser());
            foo.setNbOfNights((booking.getNbOfNights()));
            foo.setPrice(booking.getPrice());
            foo.setRoom(booking.getRoom());
            bookingRepository.save(foo);
            return new ResponseEntity<>("Booking successful updated!",HttpStatus.OK);
        }
        else
        {
            booking.setId(id);
            bookingRepository.save(booking);
            return new ResponseEntity<>("Booking not found, but added to database successful",HttpStatus.OK);
        }

    }

    @DeleteMapping(value= "/deletebyid/{id}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable long id)
    {
        Booking foo = bookingRepository.findById(id).orElse(null);
        if(foo!= null)
        {
            bookingRepository.deleteById(id);
            return new ResponseEntity<>("Booking deleted successful!",HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Booking not found, try other id!",HttpStatus.OK);
    }

    @DeleteMapping("/deleteall")
    public @ResponseBody ResponseEntity<String> deleteAll()
    {
        bookingRepository.deleteAll();

        return new ResponseEntity<>("All bookings deleted!" , HttpStatus.OK);
    }

}
