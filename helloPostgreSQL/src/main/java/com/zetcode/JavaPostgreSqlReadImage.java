package com.zetcode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlReadImage {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        String password = "1234";

        String query = "SELECT data, LENGTH(data) FROM images WHERE id = 1";
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
        	con = DriverManager.getConnection(url, user, password);
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            rs.next();
            
            File myFile = new File("src/main/resources/sid.jpg");

            try {
            	FileOutputStream fos = new FileOutputStream(myFile);
                int len = rs.getInt(2);
                byte[] buf = rs.getBytes("data");
                fos.write(buf, 0, len);
            
            }catch(IOException ex) {
            	Logger lgr1 = Logger.getLogger(JavaPostgreSqlReadImage.class.getName());
                lgr1.log(Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException sqle) {

            Logger lgr = Logger.getLogger(JavaPostgreSqlReadImage.class.getName());
            lgr.log(Level.SEVERE, sqle.getMessage(), sqle);
        }
    }
}