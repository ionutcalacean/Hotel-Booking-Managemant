package com.example.demo_initializer;



import com.example.demo_initializer.Repositories.RoomRepository;
import com.example.demo_initializer.components.ConferenceRoom;
import com.example.demo_initializer.components.Room;
import com.example.demo_initializer.components.RoomFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/rooms")
public class RoomController {
    private RoomRepository roomRepository;

    @Autowired
    public RoomController(RoomRepository roomRepository)
    {
        this.roomRepository=roomRepository;
    }

    @GetMapping(value="/getall")
    public List<Room> getAll()
    {
        return roomRepository.findAll();
    }

    @PostMapping(value="/create")
    public @ResponseBody  List<Room> create(@RequestParam String roomtype,@RequestParam int price, @RequestParam int floor, @RequestParam int roomNb, @RequestParam int capacity,
                                       @RequestParam(defaultValue = "true") boolean free, @RequestParam(defaultValue= "3") int hours)
    {
        RoomFactory myRoomFactory=new RoomFactory();
        Room newRoom=myRoomFactory.getRoom(roomtype);

        newRoom.setPricePerNight(price);
        newRoom.setFloor(floor);
        newRoom.setRoomNb(roomNb);
        newRoom.setCapacity(capacity);
        newRoom.setFree(free);
        if(newRoom instanceof ConferenceRoom)
        {
            ((ConferenceRoom) newRoom).setHours(hours);
        }

        roomRepository.save(newRoom);

        return roomRepository.findAll();
    }

    @DeleteMapping(value="/deleteall")
    public List<Room> deleteall()
    {
        roomRepository.deleteAll();

        return roomRepository.findAll();
    }






}
