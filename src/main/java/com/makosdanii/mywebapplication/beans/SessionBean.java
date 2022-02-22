/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.makosdanii.mywebapplication.beans;

import com.makosdanii.mywebapplication.data.entity.Users;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
//import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

/**
 *
 * @author user
 */
@Named("session")
//@Stateless
@DataSourceDefinition(
        name = "com.makosdanii_mywebapplication_war_1.0-SNAPSHOTPU",
        className = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "mydb140222!",
        databaseName = "mytelcodb",
        serverName = "localhost",
        portNumber = 3306,
        properties = {"jdbc:mysql://localhost:3306/mytelcodb?zeroDateTimeBehavior=CONVERT_TO_NULL"})
public class SessionBean {

    @PersistenceContext(unitName = "com.makosdanii_mywebapplication_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    private EntityManagerFactory emf;

    private EntityManager getEntityManager() {

        if (emf == null) {

            emf = Persistence.createEntityManagerFactory("com.makosdanii_mywebapplication_war_1.0-SNAPSHOTPU");

        }

        return emf.createEntityManager();

    }

    public Users[] getUsers() {

        EntityManager em = getEntityManager();

        try {

            Query q = em.createNativeQuery("select * from users");

            return (Users[]) q.getResultList().toArray();

        } finally {

            em.close();

        }

    }
}
