/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.makosdanii.mywebapplication.beans;

import com.makosdanii.mywebapplication.data.entity.Users;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author user
 */
public class Persistance_mysql {

    final private String CONN_URL = "jdbc:mysql://localhost:3306/mytelcodb?zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false";
    final private String CONN_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static Logger logger = Logger.getLogger(Persistance_mysql.class);

    private Connection con;

    public Persistance_mysql() {
        DOMConfigurator.configure("C:\\Users\\user\\NetBeansProjects\\mywebapplication\\log4j.xml");
logger.info("Started");
    }

//    private void createFileHandler() {
//        FileHandler fh = null;
//        try {
//            fh = new FileHandler("C:\\Users\\user\\NetBeansProjects\\mywebapplication\\mylog.log", false);
//            fh.setFormatter(new SimpleFormatter());
//            fh.setLevel(Level.SEVERE);
//            logger.addHandler(fh);
//        } catch (IOException | SecurityException ex) {
//            Logger.getLogger(Persistance_mysql.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            logger.info("Hello log!");
//            logger.log(Level.FINE, "Hello log!");
//            fh.close();
//        }
//    }
    public void connect(String root, String passw) throws RuntimeException {
        try {
            Class.forName(CONN_DRIVER);
            con = DriverManager.getConnection(CONN_URL, root, passw);

        } catch (ClassNotFoundException | SQLException e) {

            logger.error("error while creating connection: " + e.getMessage());
            throw new RuntimeException("failed to connect");
        }
    }

    public List<Users> executeQueryUsers(String query) {
        List<Users> result = new ArrayList<>();

        try {
            ResultSet set = con.createStatement().executeQuery(query);

            while (set.next()) {
                result.add(new Users(set.getString(1), set.getString(2),
                        set.getString(3), set.getString(4),
                        set.getInt(4), null));
            }
        } catch (SQLException e) {
            logger.error("error while executing query " + e.getMessage());
        }

        return result;
    }

    public boolean connectionCheck() {
        try {
            if (!con.isValid(1)) {
                logger.error("Connection failed testing");
                con.close();
            } else {
                return true;
            }
        } catch (SQLException e) {
            logger.error("error while closing connection" + e.getMessage());
        }

        return false;
    }
}
