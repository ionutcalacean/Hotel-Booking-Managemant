package com.example.demo_initializer;

import com.example.demo_initializer.Repositories.AdminRepository;
import com.example.demo_initializer.components.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/admins")
public class AdminController {

    private AdminRepository adminRepository;

    @Autowired
    public AdminController(AdminRepository adminRepository)
    {
        this.adminRepository=adminRepository;
    }

    @GetMapping(value="/getall")
    public List<Admin> getall()
    {
        return adminRepository.findAll();
    }

    @PostMapping(value="/create")
    public @ResponseBody ResponseEntity<String> create(@RequestParam String username, @RequestParam String password)
    {
        Admin foo = adminRepository.findById(username)
                .orElse(null);
        if(foo != null)
        {
            return new ResponseEntity<String>(
                    "username already in use",
                    HttpStatus.BAD_REQUEST);
        }
        Admin admin = new Admin(username,password);

        adminRepository.save(admin);

        return new ResponseEntity<>(
                "Admin added succesfull!",
                HttpStatus.OK);
    }

    @PostMapping(value="/deleteall")
    public List<Admin> deleteall()
    {
        adminRepository.deleteAll();

        return adminRepository.findAll();
    }





}