package com.yas.springsecurityloginregister.controller;

import java.util.Date;

import javax.validation.Valid;

import com.yas.springsecurityloginregister.Entity.User;
import com.yas.springsecurityloginregister.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController{

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("date", new Date().toString());
        return "test";
    }

    @GetMapping(value = {"/","/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("user", new User());
        return modelAndView;
    } 

    @PostMapping("/registration")
    public ModelAndView registration(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        System.out.println("Inside reg post   "+ user.getName() + user.getEmail() + user.getPassword());
        ModelAndView modelAndView = new ModelAndView();
        User userExist = userService.findUserMyEmail(user.getEmail());
        if (userExist != null){
            bindingResult.rejectValue("email", "error.user", "User with the email already exists");
        }
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("registration");
        }
        else{
            System.out.println("Inside else");
            user.setActive(true);
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User registration successful");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("users", userService.findAllUser());
        return modelAndView;
    }
}