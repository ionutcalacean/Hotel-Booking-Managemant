package com.example.demo_initializer.Controllers;


import com.example.demo_initializer.Repositories.UserRepository;
import com.example.demo_initializer.components.Observer;
import com.example.demo_initializer.components.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping(value="/users")
public class UserController  {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    /**
     * preluare lista cu utilzatorii
     * @return lista
     */
    @GetMapping(value="/getall")
    @CrossOrigin(origins = "*")
    public List<User> getall()
    {
        return userRepository.findAll();
    }

    /**
     * crearea unui utiloztori cu parametrii de request din POST
     * @param username
     * @param password
     * @param phone
     * @param email
     * @param city
     * @param news
     * @return Un raspuns sub forma de string si un HttpStatus pentru confirmarea crearii
     */
    @PostMapping(value="/create")
    @CrossOrigin(origins = "*")
    public @ResponseBody  ResponseEntity<String> create(@RequestParam String username, @RequestParam String password, @RequestParam String phone,
                                            @RequestParam String email, @RequestParam String city, @RequestParam String news)
    {
        User foo = userRepository.findById(username)
                .orElse(null);
        if(foo != null)
        {
            return new ResponseEntity<String>(
                    "\"username already in use\"",
                    HttpStatus.BAD_REQUEST);
        }
        User user = new User(username,password,phone,email,city,news);

        userRepository.save(user);

        return new ResponseEntity<>(
                "\"User added succesfull!\"",
                HttpStatus.OK);
    }

    /**
     * modificare utilozator
     * @param newUser body json cu detaliile noi
     * @param username pentru utilizatorul care se modifica
     * @return Un raspuns sub forma de string si un HttpStatus pentru confirmarea modificarii
     */
    @PutMapping(value = "/update/{username}")
    @CrossOrigin(origins = "*")
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
            foo.setNews(newUser.getNews());
            userRepository.save(foo);
            return new ResponseEntity<>("\"User updated successful!\"",HttpStatus.OK);
        }
        else
        {
            newUser.setUsername(username);
            userRepository.save(newUser);
            return new ResponseEntity<>("\"User not found, but addded to database successful!\"",HttpStatus.OK);
        }
    }

    /**
     * stergerea unui utilizator
     * @param username pentru utilizatorul sters
     * @return Un raspuns sub forma de string si un HttpStatus pentru confirmarea stergerii
     */
    @DeleteMapping(value= "/deletebyid/{username}")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable String username)
    {
        User foo = userRepository.findById(username).orElse(null);
        if(foo!= null)
        {
            userRepository.deleteById(username);
            return new ResponseEntity<>("\"User deleted successful!\"", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("\"User not found, try other username !\"",HttpStatus.BAD_REQUEST);
    }

    /**
     * stergerea tuturor utilizatorilor
     * @return
     */
    @DeleteMapping(value="/deleteall")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<String> deleteall()
    {
        userRepository.deleteAll();

        return new ResponseEntity<>("\"All users deleted successful!\"",HttpStatus.OK);
    }

    /**
     * Metoda de gasire a unui utilizator dupa username
     * @param username
     * @return
     */
    @GetMapping(value = "/findByUsername")
    @CrossOrigin(origins = "*")
    public User findByUsername(@RequestParam String username)
    {
        return userRepository.findByUsername(username);
    }

    /**
     * metoda filtrare utilizatori dupa oras utila la statistici
     * @param city orasul din care se doresc afisati utilzatorii
     * @return lista cu utilizatorii din acel orass
     */
    @GetMapping(value = "/findByCity")
    @CrossOrigin(origins = "*")
    public List<User> findByCity(@RequestParam String city){
        return userRepository.findByCity(city);
    }

    @GetMapping(value = "/login")
    @CrossOrigin(origins = "*")
    public User login(@RequestParam String username, @RequestParam String password)
    {
        List<User> users = userRepository.findAll();

        for(User u:users)
        {
            if(u.getUsername().equals(username) && u.getPassword().equals(password))
                return u;
        }
        return null;
    }

}
