/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.makosdanii.myspringwebapp.business;

import com.makosdanii.myspringwebapp.entity.Roles;
import com.makosdanii.myspringwebapp.entity.Users;
import com.makosdanii.myspringwebapp.repository.RoleRepository;
import com.makosdanii.myspringwebapp.repository.UserRepository;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service
@ComponentScan
//@Qualifier("userService")
public class UserService {
    
    private final int ADMIN_ROLE = 1;
    private final int USER_ROLE = 2;
    
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final static Logger mylogger = Logger.getLogger(UserService.class);
    
    @Autowired
    public UserService(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }
    
    @PostConstruct
    public void init() {
        DOMConfigurator.configure("log4j.xml");
        mylogger.info("Started");
//mylogger.info(System.getProperty("user.dir"));
    }
    
    public Iterable<Users> filterUser(String id) {
        mylogger.info("Filtered for " + id);
        return userRepo.findByEmailContaining(id);
    }
    
    public Users searchUser(String id) {
        mylogger.info("Auth process for id " + id);
        return userRepo.findById(id).orElse(new Users());
    }
    
    public boolean addUser(String email, String firstname,
            String lastname, String address, String password, Boolean isAdmin) {
        
        if (email.isEmpty() || firstname.isEmpty()
                || lastname.isEmpty() || password.isEmpty()) {
            return false;
        }
        
        if (userRepo.existsById(email)) {
            return false;
        }
        
        Roles r;
        if (isAdmin) {
            r = this.roleRepo.findById(ADMIN_ROLE).orElse(new Roles());
        } else {
            r = this.roleRepo.findById(USER_ROLE).orElse(new Roles());
        }
        
        userRepo.save(new Users(email, firstname, lastname,
                address, password, r));
        
        mylogger.info("Added user with id " + email);
        return true;
    }
    
    public boolean deleteUser(String id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            mylogger.info("Deleted user with id " + id);
            return true;
        }
        return false;
    }
    
}
