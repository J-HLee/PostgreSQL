package com.zetcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlWriteImage {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost/testdb";
        String user = "postgres";
        String password = "1234";

        String query = "INSERT INTO images(data) VALUES(?)";
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
        	con = DriverManager.getConnection(url, user, password);
        	pstmt = con.prepareStatement(query);
            File img = new File("src/main/resources/sid.jpg");

            try {
            	FileInputStream fin = new FileInputStream(img);

                pstmt.setBinaryStream(1, fin, (int) img.length());
                pstmt.executeUpdate();
            } catch (IOException ex) {
                Logger.getLogger(JavaPostgreSqlWriteImage.class.getName()).log(
                Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JavaPostgreSqlWriteImage.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}