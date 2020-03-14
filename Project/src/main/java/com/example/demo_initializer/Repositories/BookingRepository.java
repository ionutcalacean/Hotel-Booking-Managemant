package com.example.demo_initializer.Repositories;

import com.example.demo_initializer.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    List<Booking> findByPriceLessThan(double price);
}
