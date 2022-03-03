/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.makosdanii.myspringwebapp.controller;

import com.makosdanii.myspringwebapp.business.UserService;
import com.makosdanii.myspringwebapp.entity.Users;
import com.makosdanii.myspringwebapp.repository.UserRepository;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

/**
 *
 * @author user
 */
@FacesConfig(
        version = FacesConfig.Version.JSF_2_3
)

@Scope("session")
@Controller(value = "jsf")
public class JsfController implements Serializable {

    private final UserService userserv;

    private String user;
    private String passw;

    @Autowired
    public JsfController(UserService userserv) {
        this.userserv = userserv;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    @PostConstruct
    public String init() {
        return "nothing to see ";
    }

    public void login() {
        Users u = this.userserv.searchUser(this.user);

        if (u.getRoles() == null || !u.getRoles().getRolename().equals("admin")) {
            FacesContext.getCurrentInstance().addMessage("authform:user",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Admin not recognized", null));
            return;
        }
        if (!u.getPassword().equals(this.passw)) {
            FacesContext.getCurrentInstance().addMessage("authform:passw",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Wrong password", null));
            return;
        }
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
        session.setAttribute("isAdmin", true);
    }

    public void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    public String redirectData() {
        return "/data.xhtml?faces-redirect=true";
    }
}
