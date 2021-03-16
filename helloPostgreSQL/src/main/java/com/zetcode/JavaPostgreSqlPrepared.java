package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlPrepared {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        String password = "1234";

        int id = 6;
        String author = "Trygve Gulbranssen";
        String query = "INSERT INTO authors(id, name) VALUES(?, ?)";
        
        Connection con = null;

        try {con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query);
            
            pst.setInt(1, id);
            pst.setString(2, author);
            pst.executeUpdate();

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JavaPostgreSqlPrepared.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}