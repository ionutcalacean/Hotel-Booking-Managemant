package com.example.demo_initializer.Repositories;


import com.example.demo_initializer.components.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findByUsername(String username);
    List<User> findByCity(String city);


}
