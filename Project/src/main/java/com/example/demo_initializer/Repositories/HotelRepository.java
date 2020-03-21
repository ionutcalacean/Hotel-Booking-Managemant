package com.example.demo_initializer.Repositories;

import com.example.demo_initializer.components.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
}
