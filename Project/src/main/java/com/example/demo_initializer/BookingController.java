package com.example.demo_initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public @ResponseBody   List<Booking> create(@RequestParam String hotelName, @RequestParam double price,@RequestParam int nbOfNight )
    {
        Booking booking=new Booking(hotelName,price,nbOfNight);

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
