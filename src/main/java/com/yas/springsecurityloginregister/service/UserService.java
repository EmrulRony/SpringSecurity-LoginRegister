package com.yas.springsecurityloginregister.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.yas.springsecurityloginregister.Entity.Role;
import com.yas.springsecurityloginregister.Entity.User;
import com.yas.springsecurityloginregister.dao.RoleRepository;
import com.yas.springsecurityloginregister.dao.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    @Autowired
    private UserRepository userDao;
    @Autowired
    private RoleRepository roleDao;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
    // @Autowired
    // private NoOpPasswordEncoder noOpPasswordEncoder;

    // @Autowired
    // public UserService(UserRepository userDao, RoleRepository roleDao, NoOpPasswordEncoder noOpPasswordEncoder) {
    //     this.userDao = userDao;
    //     this.roleDao = roleDao;
    //     this.noOpPasswordEncoder = noOpPasswordEncoder;
    // }

    public User findUserMyEmail(String email){
        return userDao.findByEmail(email);
    }

    public void saveUser(User user){
        // user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleDao.findByName("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userDao.save(user);
    }

    public List<User> findAllUser(){
        return userDao.findAll();   
    }
    
}