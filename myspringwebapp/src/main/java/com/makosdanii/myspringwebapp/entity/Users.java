/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.makosdanii.myspringwebapp.entity;

import com.makosdanii.myspringwebapp.repository.UserRepository;
import com.sun.istack.NotNull;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.springframework.data.domain.Persistable;

/**
 *
 * @author user
 */
@Entity
@Table(name = "users")
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

    @ManyToOne
    @JoinColumn(name = "roleid", referencedColumnName = "id")
    private Roles roles;

    public Users(String email, String firstname, String lastname, String address, String password, Roles role) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.password = password;
        this.roles = role;
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

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

//    @Override
//    public boolean isNew() {
//        return userRepo.existsById(this.email);
//    }
//
//    @Override
//    public String getId() {
//        return this.email;
//    }
}
