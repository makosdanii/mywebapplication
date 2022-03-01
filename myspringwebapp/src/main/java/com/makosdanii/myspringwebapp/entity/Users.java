/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.makosdanii.myspringwebapp.entity;


import com.sun.istack.NotNull;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "Users")
public class Users implements Serializable {

    @Id
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "firstname")
    private String firstname;

    @NotNull
    @Column(name = "lastname")
    private String lastname;

    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "password")
    private String password;

//    @NotNull
//    @Column(name = "roleid")
//    private int roleid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roleid", referencedColumnName = "id")
    private Roles role;

    public Users(String email, String firstname, String lastname, String address, String password, Roles role) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.password = password;
        this.role = role;
    }

    public Users() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

}
