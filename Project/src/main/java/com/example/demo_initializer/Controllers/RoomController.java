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
    public List<Room> getAll()
    {
        return roomRepository.findAll();
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
    @PostMapping(value="/create/{roomtype}")
    public @ResponseBody  List<Room> create(@RequestBody Room room, @PathVariable String roomtype)
    {
        RoomFactory myRoomFactory=new RoomFactory();
        Room newRoom=myRoomFactory.getRoom(roomtype);

        newRoom.setPricePerNight(room.getPricePerNight());
        newRoom.setFloor(room.getFloor());
        newRoom.setRoomNb(room.getRoomNb());
        newRoom.setCapacity(room.getCapacity());
        newRoom.setFree(room.isFree());
        newRoom.setHotel(room.getHotel());

        Scanner keyboard = new Scanner(System.in);
        if(newRoom instanceof PremiumRoom)
        {
            System.out.println("Introduce daca are balcon sau nu, roomService, spa:(sub forma true/fale -> enter true/false-> enter,etc):");

            boolean balcony = keyboard.nextBoolean();
            boolean roomService = keyboard.nextBoolean();
            boolean spa = keyboard.nextBoolean();
            ((PremiumRoom) newRoom).setBalcony(balcony);
            ((PremiumRoom) newRoom).setRoomService(roomService);
            ((PremiumRoom) newRoom).setSpa(spa);
        }
        if(newRoom instanceof ConferenceRoom)
        {
            System.out.println("Introduce daca are proiector sau nu, scaune de piele:(sub forma true/fale -> enter true/false-> enter,etc):");

            boolean projector = keyboard.nextBoolean();
            boolean leatherSeats = keyboard.nextBoolean();
            ((ConferenceRoom) newRoom).setProjector(projector);
            ((ConferenceRoom) newRoom).setLeatherSeats(leatherSeats);

        }
        if(newRoom instanceof RegularRoom)
        {
            System.out.println("Introduce daca are pat matrimonial sau nu:(sub forma true/fale -> enter true/false-> enter,etc):");

            boolean matrimonialBad = keyboard.nextBoolean();
            ((RegularRoom) newRoom).setMatrimonialBad(matrimonialBad);


        }

        roomRepository.save(newRoom);

        return roomRepository.findAll();
    }

    /**
     *  modificarea unei camere
     * @param room noile detalii
     * @param id pentru camera modificata
     * @return Un raspuns sub forma de string si un HttpStatus pentru confirmarea modificarii
     */
    @PutMapping(value = "/update/{id}")
    public @ResponseBody ResponseEntity<String> update(@RequestBody Room room , @PathVariable  Long id) {
        Room foo = roomRepository.findById(id).orElse(null);
        if (foo != null) {
            foo.setFree(room.isFree());
            foo.setCapacity(room.getCapacity());
            foo.setRoomNb(room.getRoomNb());
            foo.setFloor(room.getFloor());
            foo.setPricePerNight(room.getPricePerNight());

            Scanner keyboard = new Scanner(System.in);
            if(foo instanceof PremiumRoom)
            {
                System.out.println("Introduce daca are balcon sau nu, roomService, spa:(sub forma true/fale -> enter true/false-> enter,etc):");

                boolean balcony = keyboard.nextBoolean();
                boolean roomService = keyboard.nextBoolean();
                boolean spa = keyboard.nextBoolean();
                ((PremiumRoom) foo).setBalcony(balcony);
                ((PremiumRoom) foo).setRoomService(roomService);
                ((PremiumRoom) foo).setSpa(spa);
            }
            if(foo instanceof ConferenceRoom)
            {
                System.out.println("Introduce daca are proiector sau nu, scaune de piele:(sub forma true/fale -> enter true/false-> enter,etc):");

                boolean projector = keyboard.nextBoolean();
                boolean leatherSeats = keyboard.nextBoolean();
                ((ConferenceRoom) foo).setProjector(projector);
                ((ConferenceRoom) foo).setLeatherSeats(leatherSeats);

            }
            if(foo instanceof RegularRoom)
            {
                System.out.println("Introduce daca are pat matrimonial sau nu:(sub forma true/fale -> enter true/false-> enter,etc):");

                boolean matrimonialBad = keyboard.nextBoolean();
                ((RegularRoom) foo).setMatrimonialBad(matrimonialBad);


            }

            roomRepository.save(foo);
            return new ResponseEntity<>("Room successful updated!", HttpStatus.OK);
        } else {
            room.setRoomId(id);
            roomRepository.save(room);
            return new ResponseEntity<>("Room not found, but added to database successful", HttpStatus.OK);
        }
    }

    /**
     * stergerea unei camere dupa id
     * @param id pt camera stearsa
     * @return Un raspuns sub forma de string si un HttpStatus pentru confirmarea stergerii
     */
    @DeleteMapping(value= "/deletebyid/{id}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable long id)
    {
        Room foo = roomRepository.findById(id).orElse(null);
        if(foo!= null)
        {
            roomRepository.deleteById(id);
            return new ResponseEntity<>("Room deleted successful!", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Room not found, try other  room id!",HttpStatus.OK);
    }

    /**
     * stergerea tutuor camerelor
     * @return Un raspuns sub forma de string si un HttpStatus pentru confirmarea stergerii
     */
    @DeleteMapping(value="/deleteall")
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








}
