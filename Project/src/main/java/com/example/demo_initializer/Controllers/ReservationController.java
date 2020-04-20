package com.example.demo_initializer.Controllers;


import com.example.demo_initializer.Repositories.ReservationRepository;
import com.example.demo_initializer.components.Booking;
import com.example.demo_initializer.components.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/reservation")
public class ReservationController {
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository)
    {
        this.reservationRepository=reservationRepository;
    }

    /**
     * metoda pentru preliarea tuturor rezervarilor
     * @return lista cu toate rezervarile existente in baza de date
     */
    @RequestMapping(value="/getall",method= RequestMethod.GET)
    public List<Reservation> getAll()
    {

        return reservationRepository.findAll();
    }

    /**
     * inserarea unei rezervari in baza de date, inserarea se face cu succes doar daca nu exista rezervari pentru acea camera in acea parioada
     * @param reservation obiectul de inserat sub forma unui body Json
     * @return Un raspuns sub forma de string si un HttpStatus pentru confirmarea crearii
     */
    @RequestMapping(value = "/create",method=RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> create(@RequestBody Reservation reservation )
    {
        List<Reservation> allReservations = reservationRepository.findAll();

        List<Reservation> thisRoomRes = new ArrayList<Reservation>();

        for(Reservation res : allReservations)
        {
            if(res.getRoom().getRoomId()==reservation.getRoom().getRoomId())
                thisRoomRes.add(res);
        }

        boolean overlaps = false;
        for(Reservation myRes : thisRoomRes)
        {

            if(myRes.getInDate().before(reservation.getOutDate()) && myRes.getOutDate().after(reservation.getInDate()))
            {
                overlaps=true;
            }

        }
        if(!overlaps) {


            try {
                reservationRepository.save(reservation);
            } catch (DataIntegrityViolationException e) {
                return new ResponseEntity<>("Data intergrity Violation Exception!", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>("Booking successful added to database!", HttpStatus.OK);
        }
        else return new ResponseEntity<>("Room not free in this period!", HttpStatus.OK);
    }

    /**
     * modificarea unei rezervari
     * @param reservation obiectul de modificat sub forma unui body Json
     * @param id identificare rezervare care se doreste modificata
     * @return Un raspuns sub forma de string si un HttpStatus pentru confirmarea modificarii
     */
    @PutMapping(value = "/update/{id}")
    public @ResponseBody ResponseEntity<String> update(@RequestBody Reservation reservation , @PathVariable  Long id)
    {
        Reservation foo= reservationRepository.findById(id).orElse(null);
        if(foo != null)
        {
            foo.setUser(reservation.getUser());
            foo.setInDate(reservation.getInDate());
            foo.setOutDate(reservation.getOutDate());
            reservationRepository.save(foo);
            return new ResponseEntity<>("Booking successful updated!",HttpStatus.OK);
        }
        else
        {
            reservation.setId(id);
            reservationRepository.save(reservation);
            return new ResponseEntity<>("Booking not found, but added to database successful",HttpStatus.OK);
        }

    }

    /**
     * stergerea unei rezervari
     * @param id al rezervarii
     * @return Un raspuns sub forma de string si un HttpStatus pentru confirmarea stergerii
     */
    @DeleteMapping(value= "/deletebyid/{id}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable long id)
    {
        Reservation foo = reservationRepository.findById(id).orElse(null);
        if(foo!= null)
        {
            reservationRepository.deleteById(id);
            return new ResponseEntity<>("Reservation deleted successful!",HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Reservation not found, try other id!",HttpStatus.OK);
    }

    /**
     * stergerea tuturor rezervarilor
     * @return Un raspuns sub forma de string si un HttpStatus pentru confirmarea stergerii
     */
    @DeleteMapping("/deleteall")
    public @ResponseBody ResponseEntity<String> deleteAll()
    {
        reservationRepository.deleteAll();

        return new ResponseEntity<>("All bookings deleted!" , HttpStatus.OK);
    }
}
