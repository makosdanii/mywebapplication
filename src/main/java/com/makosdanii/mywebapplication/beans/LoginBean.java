/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.makosdanii.mywebapplication.beans;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;

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

    public void greet() {
        isAdmin = email.contains("admin");
        String result = "";

        if (database.connectionCheck() && isAdmin) {
            result = database.executeQueryUsers("select * from users").get(0).getEmail();
        }

        val = "Hello " + email + " " + result;

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Not logged in", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void signIn() {
        isAdmin = email.contains("admin");
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("faces/homepage.xhtml?isAdmin=" + (isAdmin ? "true" : "false"));
        } catch (IOException e) {
        }
//        return "faces/homepage.xhtml?isAdmin=" + (isAdmin ? "true" : "false");
    }
}
