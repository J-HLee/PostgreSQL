package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlVersion {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        String password = "1234";
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        	

        try {
        	con = DriverManager.getConnection(url, user, password);
        	stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT VERSION()"); 

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {
        
            Logger lgr = Logger.getLogger(JavaPostgreSqlVersion.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}