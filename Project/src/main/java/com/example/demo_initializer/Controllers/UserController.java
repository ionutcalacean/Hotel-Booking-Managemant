package com.example.demo_initializer.Controllers;


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

    @PutMapping(value = "/update/{username}")
    public @ResponseBody ResponseEntity<String> update(@RequestBody User newUser, @PathVariable String username)
    {
        User foo = userRepository.findById(username).orElse(null);
        if(foo != null)
        {
            userRepository.deleteById(username);
            foo.setUsername(newUser.getUsername());
            foo.setCity(newUser.getCity());
            foo.setEmail(newUser.getEmail());
            foo.setPassword(newUser.getPassword());
            foo.setPhone(newUser.getPhone());
            userRepository.save(foo);
            return new ResponseEntity<>("User updated successful!",HttpStatus.OK);
        }
        else
        {
            newUser.setUsername(username);
            userRepository.save(newUser);
            return new ResponseEntity<>("User not found, but addded to database successful!",HttpStatus.OK);
        }
    }

    @DeleteMapping(value= "/deletebyid/{username}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable String username)
    {
        User foo = userRepository.findById(username).orElse(null);
        if(foo!= null)
        {
            userRepository.deleteById(username);
            return new ResponseEntity<>("User deleted successful!", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("User not found, try other username !",HttpStatus.OK);
    }


    @DeleteMapping(value="/deleteall")
    public @ResponseBody ResponseEntity<String> deleteall()
    {
        userRepository.deleteAll();

        return new ResponseEntity<>("All users deleted successful!",HttpStatus.OK);
    }





}
