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

    @Autowired
    public UserService(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    public Iterable<Users> filterUser(String id) {
        return userRepo.findAllById(Arrays.asList(id));
    }

    public Users searchUser(String id) {
        return userRepo.findById(id).orElse(new Users());
    }

    public void addUser(String email, String firstname,
            String lastname, String address, String password, Boolean isAdmin) {

        Roles r;
        if (isAdmin) {
            r = this.roleRepo.findById(ADMIN_ROLE).orElse(new Roles());
        } else {
            r = this.roleRepo.findById(USER_ROLE).orElse(new Roles());
        }

        userRepo.save(new Users(email, firstname, lastname,
                address, password, r));
    }

    public boolean deleteUser(String id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }

}
