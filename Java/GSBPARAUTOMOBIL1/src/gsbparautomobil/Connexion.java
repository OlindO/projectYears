/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsbparautomobil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Christophe G
 */
public class Connexion {
    
    private String url= "jdbc:mysql://localhost:3306/test2";
    private String user = "root";
    private String password = "";
    private static Connection conn;
    
    
    
    private Connexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver ok");
            conn=DriverManager.getConnection(url,user,password);
            System.out.println("Connexion effective");
            
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
    public static Connection getInstance(){
        if(conn==null){
            new Connexion();
        }
        return conn;
    }

    
}

//id 25013334
//mdp 444fab

//