/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.makosdanii.myspringwebapp.controller;

import com.makosdanii.myspringwebapp.business.UserService;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

/**
 *
 * @author user
 */
@Controller("registrate")
@RequestScope
public class RegistrationController {

    @Inject
    UserService userServ;

    private String email;
    private String firstname;
    private String lastname;
    private String address;
    private String password;
    private Boolean isAdmin = false;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void registrate() {
        FacesMessage msg;
        if (userServ.addUser(email, firstname, lastname, address,
                password, isAdmin)) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered successfully", null);
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unsuccessfull registration", null);

        }
        FacesContext.getCurrentInstance().addMessage("register", msg);
    }

}
