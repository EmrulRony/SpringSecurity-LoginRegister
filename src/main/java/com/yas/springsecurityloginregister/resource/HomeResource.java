package com.yas.springsecurityloginregister.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeResource{
    
    @GetMapping
    public String home(){
        return "<h2>this is home</h2>";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "<h2>This is admin</h2>";
    }

    @RequestMapping("/user")
    public String user(){
        return "<h2>This is user</h2>";
    }
}