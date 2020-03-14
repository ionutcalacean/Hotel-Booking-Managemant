package com.example.demo_initializer;

import com.example.demo_initializer.Repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Booking> create(@RequestBody Booking booking )
    {
        bookingRepository.save(booking);

        return bookingRepository.findAll();
    }

    @RequestMapping(value= "/delete/{id}",method = RequestMethod.GET)
    public List<Booking> delete(@PathVariable long id)
    {
        bookingRepository.deleteById(id);

        return bookingRepository.findAll();
    }

    @GetMapping("/deleteall")
    public List<Booking> deleteAll()
    {
        bookingRepository.deleteAll();

        return bookingRepository.findAll();
    }

}
