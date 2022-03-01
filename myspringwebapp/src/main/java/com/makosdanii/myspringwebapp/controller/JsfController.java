/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.makosdanii.myspringwebapp.controller;

import com.makosdanii.myspringwebapp.entity.Users;
import com.makosdanii.myspringwebapp.repository.UserRepository;
import javax.annotation.PostConstruct;
import javax.faces.annotation.FacesConfig;
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

@RequestScope
@Controller(value = "jsf")
public class JsfController {

    @PostConstruct
    public String init(){
    return "nothing to see ";
    }

    public String redirectData()
    {
        return "/data.xhtml?faces-redirect=true";
    }
}
