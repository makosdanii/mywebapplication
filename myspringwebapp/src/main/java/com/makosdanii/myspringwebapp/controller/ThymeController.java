/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.makosdanii.myspringwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@RestController
public class ThymeController {

    @GetMapping("/hellothymeleaf")
    public ModelAndView init() {
//        model.addAttribute("greeting", "hellothere");
        return new ModelAndView("hellohtml.html", "bean", new BackingBean());
    }
}

class BackingBean{
private String greeting = "Hellothere";

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

}