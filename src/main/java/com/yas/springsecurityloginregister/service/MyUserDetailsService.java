package com.yas.springsecurityloginregister.service;

import com.yas.springsecurityloginregister.Entity.User;
import com.yas.springsecurityloginregister.dao.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByEmail(username);
        if (user == null){
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}