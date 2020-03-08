package com.example.demo_initializer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoTest {

    @RequestMapping("/test")
    public String hello(){
        return "test succes";
    }

}
