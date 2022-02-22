/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.makosdanii.mywebapplication.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.time.Instant;

/**
 *
 * @author user
 */
@Named("home")
@SessionScoped
public class HomeBean implements Serializable {

    private boolean isAdmin;
    private boolean loggedIn;
    private long sessionCreationTime;
    private final long SESSION_TIME_60SEC_IN_MILLI = 60000;

    @PostConstruct
    public void init() {
        isAdmin = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("isAdmin") == "true";
        loggedIn = false;
        this.getSession();
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
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
        if (!loggedIn) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(isAdmin);
            if (session.isNew()) {
                loggedIn = true;
                sessionCreationTime = session.getCreationTime();
            }
        }

        if (Instant.now().toEpochMilli() - sessionCreationTime > SESSION_TIME_60SEC_IN_MILLI) {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

            loggedIn = false;
        }
    }
}
