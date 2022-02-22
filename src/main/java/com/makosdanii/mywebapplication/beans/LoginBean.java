/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.makosdanii.mywebapplication.beans;

import com.makosdanii.mywebapplication.data.entity.Users;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
@RequestScoped
@Named("login")
public class LoginBean {

    private boolean rememberme;

    private boolean isAdmin;
    private String val = "hello";
    private String email;
    private String password;
    private Persistance_mysql database;
    private final String ADMIN = "admin";

//    @Inject
//    SessionBean sessionBean;

    @PostConstruct
    private void init() {
        database = new Persistance_mysql();
        try {
            database.connect("root", "mydb140222!");
        } catch (RuntimeException e) {
            System.err.println("com.makosdanii.mywebapplication.beans.Login.init()");
        }
    }

    public boolean isRememberme() {
        return rememberme;
    }

    public void setRememberme(boolean rememberme) {
        this.rememberme = rememberme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public void signIn() {
        if (database.connectionCheck()) {
            List<Users> result = database.executeQueryLogin(email);

            if (result.size() == 0) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not registered", null);
                FacesContext.getCurrentInstance().addMessage("myform:email", message);
                return;
            }

            if (!result.get(0).getPassword().equals(this.password)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect password", null);
                FacesContext.getCurrentInstance().addMessage("myform:passw", message);
                return;
            }

            try {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("isAdmin", result.get(0).getRole().getRolename().equals(ADMIN));
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("faces/homepage.xhtml");
            } catch (IOException e) {
            }
        }

    }
}
