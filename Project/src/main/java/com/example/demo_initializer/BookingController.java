package com.example.demo_initializer;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/booking")
public class BookingController {
    private List<Booking> bookings;

    public BookingController(){
        bookings = new ArrayList<>();

        bookings.add(new Booking("Perla",299,3));
        bookings.add(new Booking("Delfin",150,2));
        bookings.add(new Booking("International",200,5));

    }

    @RequestMapping(value="/getall",method= RequestMethod.GET)
    public List<Booking>getAll()
    {
        return bookings;
    }

    @RequestMapping(value = "/getlowcost/{price}",method = RequestMethod.GET)
    public List<Booking> getLowCost(@PathVariable double price)
    {
        return bookings.stream().filter(x->x.getPrice() <= price).collect(Collectors.toList());
    }
    @RequestMapping(value = "/create",method=RequestMethod.POST)
    public List<Booking> create(@RequestBody Booking booking)
    {
        bookings.add(booking);

        return bookings;
    }

}
