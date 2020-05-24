package com.example.demo_initializer.Controllers;

import com.example.demo_initializer.Repositories.AdminRepository;
import com.example.demo_initializer.components.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clasa controller pentru Admini
 */
@RestController
@RequestMapping(value="/admins")
public class AdminController {

    private AdminRepository adminRepository;

    @Autowired
    public AdminController(AdminRepository adminRepository)
    {
        this.adminRepository=adminRepository;
    }

    /**
     * metoda de tip get, pe server , prin accesare link localhost:8080/admins/getall
     * @return lista cu toti adminii din baza de date
     */
    @GetMapping(value="/getall")
    @CrossOrigin(origins = "*")
    public List<Admin> getall()
    {
        return adminRepository.findAll();
    }

    /**
     *  creeaza un admin si il adauga in baza de date
     * @param username  numele de utilizator pentru admin
     * @param password parola pentru admin
     * @return Un raspuns sub forma de string si un HttpStatus pentru confirmarea crearii
     */
    @PostMapping(value="/create")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<String> create(@RequestParam String username, @RequestParam String password)
    {
        Admin foo = adminRepository.findById(username)
                .orElse(null);
        if(foo != null)
        {
            return new ResponseEntity<String>(
                    "\"username already in use\"",
                    HttpStatus.BAD_REQUEST);
        }
        Admin admin = new Admin(username,password);

        adminRepository.save(admin);

        return new ResponseEntity<>(
                "\"Admin added succesfull!\"",
                HttpStatus.OK);
    }

    /**
     * modificare detalii admin existent sau introducere daca nu exista
     * @param newAdmin  obiect cu noile detalii
     * @param username pentru admin-ul care se modifica
     * @return Un raspuns sub forma de string si un HttpStatus pentru confirmarea modificarii
     */
    @PutMapping(value = "/update/{username}")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<String> update(@RequestBody Admin newAdmin, @PathVariable String username)
    {
        Admin foo = adminRepository.findById(username).orElse(null);
        if(foo != null)
        {
            adminRepository.deleteById(username);
            foo.setUsername(newAdmin.getUsername());
            foo.setPassword(newAdmin.getPassword());
            adminRepository.save(foo);
            return new ResponseEntity<>("\"Admin updated successful!\"",HttpStatus.OK);
        }
        else
        {
            newAdmin.setUsername(username);
            adminRepository.save(newAdmin);
            return new ResponseEntity<>("\"Admin not found, but addded to database successful!\"",HttpStatus.OK);
        }
    }

    /**
     * stergerea unui utilizator existent dupa username
     * @param username
     * @return Un raspuns sub forma de string si un HttpStatus pentru confirmarea stergerii
     */
    @DeleteMapping("/deletebyid/{username}")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<String> deleteEmployee(@PathVariable String username) {

        Admin foo = adminRepository.findById(username).orElse(null);
        if(foo!= null)
        {
            adminRepository.deleteById(username);
            return new ResponseEntity<>("\"Admin deleted successful!\"",HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("\"Admin not found, try other username!\"",HttpStatus.BAD_REQUEST);

    }

    /**
     * Stergerea tuturor adminilor
     * @return Un raspuns sub forma de string si un HttpStatus pentru confirmarea crearii
     */
    @DeleteMapping(value="/deleteall")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteall()
    {
        adminRepository.deleteAll();

        return new ResponseEntity<>("\"All admins deleted!\"",HttpStatus.OK);
    }

    /**
     * Metoda utila in cazul in care unul dintre admini isi uita parola,alt admin are acees la ea
     * @param username username-ul adminului folosit la logare
     * @return datele adminului
     */
    @GetMapping(value = "/findByUsername")
    @CrossOrigin(origins = "*")
    public Admin findByUsername(@RequestParam String username)
    {
        return adminRepository.findByUsername(username);
    }


    @GetMapping(value = "/login")
    @CrossOrigin(origins = "*")
    public Admin login(@RequestParam String username, @RequestParam String password)
    {
        List<Admin> users = adminRepository.findAll();

        for(Admin u:users)
        {
            if(u.getUsername().equals(username) && u.getPassword().equals(password))
                return u;
        }
        return null;
    }



}