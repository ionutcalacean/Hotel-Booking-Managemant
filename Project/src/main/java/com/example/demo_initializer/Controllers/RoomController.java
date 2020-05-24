package com.example.demo_initializer.Controllers;



import com.example.demo_initializer.Repositories.RoomRepository;
import com.example.demo_initializer.Repositories.UserRepository;
import com.example.demo_initializer.components.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping(value="/rooms")
public class RoomController {
    private RoomRepository roomRepository;
    private UserRepository userRepository;
    // array list pentru observatori
    private List<Observer> observers= new ArrayList<Observer>();

    @Autowired
    public RoomController(RoomRepository roomRepository,UserRepository userRepository)
    {
        this.roomRepository=roomRepository;
        this.userRepository=userRepository;
    }

    /**
     * preluarea tuturor camerelor
     * @return lista cu camerele
     */
    @GetMapping(value="/getall")
    @CrossOrigin(origins = "*")
    public List<Room> getAll()
    {
        return roomRepository.findAll();
    }

    @GetMapping(value="/getallPremium")
    @CrossOrigin(origins = "*")
    public List<Room> getAllPremium()
    {
        List<Room> rooms= roomRepository.findAll();

        List<Room> returns = new ArrayList<Room>();

        for(Room r: rooms)
        {
            if(r instanceof PremiumRoom)
                returns.add(r);
        }
        return returns;
    }

    @GetMapping(value="/getallRegular")
    @CrossOrigin(origins = "*")
    public List<Room> getAllRegular()
    {
        List<Room> rooms= roomRepository.findAll();

        List<Room> returns = new ArrayList<Room>();

        for(Room r: rooms)
        {
            if(r instanceof RegularRoom)
                returns.add(r);
        }
        return returns;
    }
    @GetMapping(value="/getallConference")
    @CrossOrigin(origins = "*")
    public List<Room> getAllConference()
    {
        List<Room> rooms= roomRepository.findAll();

        List<Room> returns = new ArrayList<Room>();

        for(Room r: rooms)
        {
            if(r instanceof ConferenceRoom)
                returns.add(r);
        }
        return returns;
    }


    /*@PostMapping(value="/create/{roomtype}")
    public @ResponseBody  List<Room> create(@RequestParam String roomtype,@RequestParam int price, @RequestParam int floor, @RequestParam int roomNb, @RequestParam int capacity,
                                            @RequestParam(defaultValue = "true") boolean free, @RequestParam(defaultValue= "3") int hours)*/

    /**
     * crearea unei camere
     * @param room body de tip json cu obiectul
     * @param roomtype tipul camerei pentru Factory pattern
     * @return Un raspuns sub forma de string si un HttpStatus pentru confirmarea crearii
     */
    @PostMapping(value="/create/{roomtype}/{balcony}/{roomService}/{spa}/{projector}/{leatherseats}/{matrimonialbad}")
    @CrossOrigin(origins = "*")
    public Room create(@RequestBody Room room, @PathVariable String roomtype, @PathVariable String balcony
       , @PathVariable String roomService, @PathVariable String spa, @PathVariable String projector
      ,@PathVariable String leatherseats, @PathVariable String matrimonialbad)
    {
        RoomFactory myRoomFactory=new RoomFactory();
        Room newRoom=myRoomFactory.getRoom(roomtype);

        newRoom.setPricePerNight(room.getPricePerNight());
        newRoom.setFloor(room.getFloor());
        newRoom.setRoomNb(room.getRoomNb());
        newRoom.setCapacity(room.getCapacity());
        newRoom.setFree(room.isFree());
        newRoom.setHotel(room.getHotel());


        if(newRoom instanceof PremiumRoom)
        {


            boolean balcony_b = (balcony.equals("true")) ? true : false;
            boolean roomService_b = (balcony.equals("true")) ? true : false;
            boolean spa_b = (balcony.equals("true")) ? true : false;
            ((PremiumRoom) newRoom).setBalcony(balcony_b);
            ((PremiumRoom) newRoom).setRoomService(roomService_b);
            ((PremiumRoom) newRoom).setSpa(spa_b);
        }
        if(newRoom instanceof ConferenceRoom)
        {


            boolean projector_b = (projector.equals("true")) ? true : false;
            boolean leatherSeats_b = (leatherseats.equals("true")) ? true : false;
            ((ConferenceRoom) newRoom).setProjector(projector_b);
            ((ConferenceRoom) newRoom).setLeatherSeats(leatherSeats_b);

        }
        if(newRoom instanceof RegularRoom)
        {


            boolean matrimonialBad_b = (matrimonialbad.equals("true")) ? true : false;
            ((RegularRoom) newRoom).setMatrimonialBad(matrimonialBad_b);


        }

        roomRepository.save(newRoom);

        return newRoom;
    }

    /**
     *  modificarea unei camere
     * @param room noile detalii
     * @param id pentru camera modificata
     * @return Un raspuns sub forma de string si un HttpStatus pentru confirmarea modificarii
     */
    @PutMapping(value = "/update/{id}/{balcony}/{roomService}/{spa}/{projector}/{leatherseats}/{matrimonialbad}")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<String> update(@RequestBody Room room , @PathVariable  Long id,@PathVariable String balcony
            , @PathVariable String roomService, @PathVariable String spa, @PathVariable String projector
            ,@PathVariable String leatherseats, @PathVariable String matrimonialbad) {
        Room foo = roomRepository.findById(id).orElse(null);
        if (foo != null) {
            foo.setFree(room.isFree());
            foo.setCapacity(room.getCapacity());
            foo.setRoomNb(room.getRoomNb());
            foo.setFloor(room.getFloor());
            foo.setPricePerNight(room.getPricePerNight());


            if(foo instanceof PremiumRoom)
            {


                boolean balcony_b = (balcony.equals("true")) ? true : false;
                boolean roomService_b = (balcony.equals("true")) ? true : false;
                boolean spa_b = (balcony.equals("true")) ? true : false;
                ((PremiumRoom) foo).setBalcony(balcony_b);
                ((PremiumRoom) foo).setRoomService(roomService_b);
                ((PremiumRoom) foo).setSpa(spa_b);
            }
            if(foo instanceof ConferenceRoom)
            {


                boolean projector_b = (projector.equals("true")) ? true : false;
                boolean leatherSeats_b = (leatherseats.equals("true")) ? true : false;
                ((ConferenceRoom) foo).setProjector(projector_b);
                ((ConferenceRoom) foo).setLeatherSeats(leatherSeats_b);

            }
            if(foo instanceof RegularRoom)
            {


                boolean matrimonialBad_b = (matrimonialbad.equals("true")) ? true : false;
                ((RegularRoom) foo).setMatrimonialBad(matrimonialBad_b);


            }

            roomRepository.save(foo);
            return new ResponseEntity<>("\"Room successful updated!\"", HttpStatus.OK);
        } else {
            room.setRoomId(id);

            return new ResponseEntity<>("\"Room not found\"", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * stergerea unei camere dupa id
     * @param id pt camera stearsa
     * @return Un raspuns sub forma de string si un HttpStatus pentru confirmarea stergerii
     */
    @DeleteMapping(value= "/deletebyid/{id}")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable long id)
    {
        Room foo = roomRepository.findById(id).orElse(null);
        if(foo!= null)
        {
            roomRepository.deleteById(id);
            return new ResponseEntity<>("\"Room deleted successful!\"", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("\"Room not found, try other  room id!\"",HttpStatus.BAD_REQUEST);
    }

    /**
     * stergerea tutuor camerelor
     * @return Un raspuns sub forma de string si un HttpStatus pentru confirmarea stergerii
     */
    @DeleteMapping(value="/deleteall")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<String> deleteall()
    {
        roomRepository.deleteAll();

        return new ResponseEntity<>("All rooms deleted successfull!",HttpStatus.OK);
    }


    /**
     * adaugare observator pentru camere
     * @param observer user-ul care trebuie notificat
     */
    public void addObserver(Observer observer){
        this.observers.add(observer);

    }

    /**
     * stergere observator pentru camerele hotelurilor
     * @param observer
     */
    public void removeObserver(Observer observer){
        this.observers.remove(observer);
    }

    /**
     * notificarea tuturor utilizatorilor cu privire la camerele hotelurilor
     * @param news stirea pe care trebuie sa o primeasca
     */
    public void notifyAllObservers(String news)
    {
        for(Observer obs: observers)
        {
            obs.update(news);
        }
    }

    @GetMapping(value="/check")
    @CrossOrigin(origins = "*")
    public void checkRooms()
    {
        List<User> usersList= userRepository.findAll();
        for(User usr: usersList)
        {
            this.addObserver(usr);
        }

        List<Room> roomsList = roomRepository.findAll();
        boolean freeFlag = false;
        for(Room rm: roomsList)
        {
            if (rm.isFree())
                freeFlag=true;
        }
        if(freeFlag)
            notifyAllObservers("Free rooms available");
        else
            notifyAllObservers("No free rooms!");
        //pentru salvare update-uri in observatori
        for(User usr: usersList)
        {
           userRepository.save(usr);
        }
    }


    /**
     * Motoda de filtrare pentru cautare camere la un anumit pret
     * @param price pretul pana la care sa se afiseze
     * @return lista de camere care se incadreaza in limita data
     */
    @RequestMapping(value = "/filterByPrice/{price}",method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public List<Room> getLowCost(@PathVariable int price)
    {
        return roomRepository.findByPricePerNightLessThan(price);
    }

    /**
     * Filtrare camere dupa etaj
     * @param floor etajul dorit
     * @return lista cu camerele care se portivesc filtrarii
     */
    @GetMapping(value = "/findByFloor")
    @CrossOrigin(origins = "*")
    public List<Room> findByFloor(@RequestParam int floor)
    {
        return roomRepository.findByFloor(floor);
    }

    /**
     * Metoda de filtrare camere dupa capacitate( 2 3 peroane, sau 20 -30 pentru conferinte)
     * @param capacity capacitatea dorita
     * @return lista cu camerele de capacitatea dorita
     */
    @GetMapping(value ="/findByCapacity")
    @CrossOrigin(origins = "*")
    public List<Room> findByCapacity(@RequestParam int capacity)
    {
        return roomRepository.findByCapacity(capacity);
    }

    /**
     * Metoda de afisare a cemerelor dintr-un hotel selectat dupa nume
     * @param hotelName numele hotelului unde se doreste vizualizarea camerelor
     * @return lista cu camerele hotelului
     */
    @GetMapping(value = "/findByHotel")
    @CrossOrigin(origins = "*")
    public List<Room> findByHotel(@RequestParam String hotelName)
    {
        List<Room> allRooms=roomRepository.findAll();
        List<Room> result=new ArrayList<Room>();
        for(Room r:allRooms)
        {
            if(r.getHotel().getHotelName().equals(hotelName))
                result.add(r);
        }
        return result;
    }

    /**
     * Metoda de filtrare si afisare doar a camerelor ramase libere
     * @return lista cu camerele libere
     */
    @GetMapping(value = "/findFreeRooms")
    @CrossOrigin(origins = "*")
    public List<Room> findFreeRooms()
    {
        List<Room> allRooms=roomRepository.findAll();
        List<Room> result=new ArrayList<Room>();
        for(Room r:allRooms)
        {
            if(r.isFree())
                result.add(r);
        }
        return result;
    }

    /**
     * Adaugare rezervare pentru un utilizator, necesita update metoda!
     * @param reservation
     * @param id
     * @return
     */
    @PutMapping(value = "/addReservation/{id}")
    public Room addReservation(@RequestBody Reservation reservation,@PathVariable Long id)
    {
        Room myRoom = roomRepository.findById(id).orElse(null);

      //  myRoom.getReservations().add(reservation);


        roomRepository.save(myRoom);

        return myRoom;

    }



}
