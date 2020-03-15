package com.example.demo_initializer;


import com.example.demo_initializer.Repositories.UserRepository;
import com.example.demo_initializer.components.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping(value="/users")
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    @GetMapping(value="/getall")
    public List<User> getall()
    {
        return userRepository.findAll();
    }

    @PostMapping(value="/create")
    public @ResponseBody  ResponseEntity<String> create(@RequestParam String username, @RequestParam String password, @RequestParam String phone,
                                            @RequestParam String email, @RequestParam String city)
    {
        User foo = userRepository.findById(username)
                .orElse(null);
        if(foo != null)
        {
            return new ResponseEntity<String>(
                    "username already in use",
                    HttpStatus.BAD_REQUEST);
        }
        User user = new User(username,password,phone,email,city);

        userRepository.save(user);

        return new ResponseEntity<>(
                "User added succesfull!",
                HttpStatus.OK);
    }

    @PostMapping(value="/deleteall")
    public List<User> deleteall()
    {
        userRepository.deleteAll();

        return userRepository.findAll();
    }





}
