package com.example.demo_initializer.Repositories;

import com.example.demo_initializer.components.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,String> {

    Admin findByUsername(String username);
}

