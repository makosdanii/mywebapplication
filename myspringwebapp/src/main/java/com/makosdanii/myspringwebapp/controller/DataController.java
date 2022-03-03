/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.makosdanii.myspringwebapp.controller;

import com.makosdanii.myspringwebapp.business.UserService;
import com.makosdanii.myspringwebapp.entity.Users;
import com.makosdanii.myspringwebapp.repository.RoleRepository;
import com.makosdanii.myspringwebapp.repository.UserRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.annotation.FacesConfig;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@FacesConfig(
        version = FacesConfig.Version.JSF_2_3
)
@Configuration
@ComponentScan("com.makosdanii.mywebapplication.business")

@RestController(value = "data")
@RequestScope
public class DataController implements Serializable {

    @Autowired
    public DataController(UserRepository repoUser, RoleRepository repoRole, UserService userServ) {
        this.repoUser = repoUser;
        this.repoRole = repoRole;
        this.userServ = userServ;
    }

    private final UserRepository repoUser;
    private final RoleRepository repoRole;
    private final UserService userServ;

    private Users queried;

    private List<Users> users;

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public Users getQueried() {
        return queried;
    }

    public void setQueried(Users query) {
        this.queried = query;
    }

    @PostConstruct
    public void init() {
        users = new ArrayList<>();
        repoUser.findAll().forEach(users::add);

    }

    public void filter() {
        users.clear();
        userServ.filterUser(
                ((HttpServletRequest) FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getRequest())
                        .getParameter("query"))
                .forEach(this.users::add);

    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id", required = false) String id,
            HttpServletRequest request) {
        boolean deleted = false;
        Object isAdmin = request.getSession().getAttribute("isAdmin");
        if (isAdmin != null && (boolean) isAdmin) {
            deleted = userServ.deleteUser(id);
        }

        return deleted ? "deleted" : "failed to delete";
    }

    public void remove() {
        userServ.deleteUser(
                ((HttpServletRequest) FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getRequest())
                        .getParameter("id"));
    }

    public void ajaxFilter(AjaxBehaviorEvent event) {
        filter();
    }
}
