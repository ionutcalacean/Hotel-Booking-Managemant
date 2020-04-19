package com.example.demo_initializer.Repositories;

import com.example.demo_initializer.components.Hotel;
import com.example.demo_initializer.components.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {

   List<Room> findByPricePerNightLessThan(int price);
   List<Room> findByFloor(int floor);
   List<Room> findByCapacity(int capacity);
   List<Room> findByHotel(Hotel hotel);

}
