/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.makosdanii.mywebapplication.beans;

import com.makosdanii.mywebapplication.data.entity.Users;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
@Named("home")
@SessionScoped
public class HomeBean implements Serializable {

    private boolean loggedIn = false;
    private long sessionCreationTime;
    private final long SESSION_TIME_60SEC_IN_MILLI = 60000;

    private Persistance_mysql database;
    List<Users> users;

    @PostConstruct
    public void init() {
        database = new Persistance_mysql();
        try {
            database.connect("root", "mydb140222!");
        } catch (RuntimeException e) {
            System.err.println("com.makosdanii.mywebapplication.beans.Login.init()");
        }

//        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
//                .getRequest();
        ExternalContext req = FacesContext.getCurrentInstance().getExternalContext();

        if (req != null && req.getSessionMap().containsKey("isAdmin")) {
            users = database.executeQueryListUsers();
            this.getSession();
        }
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public long getSessionCreationTime() {
        return sessionCreationTime;
    }

    public void setSessionCreationTime(long sessionCreationTime) {
        this.sessionCreationTime = sessionCreationTime;
    }

    public void getSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

        if (session != null) {
            sessionCreationTime = session.getCreationTime();
            loggedIn = true;
        }

        if (Instant.now().toEpochMilli() - sessionCreationTime > SESSION_TIME_60SEC_IN_MILLI) {
            destroySession();
        }
    }

    public String destroySession() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//        try {
//            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/loginpage.xhtml");
//        } catch (IOException e) {
//        }
        return "faces/loginpage.xhtml";
    }
}
