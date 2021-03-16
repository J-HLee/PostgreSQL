package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlListTables {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        String password = "1234";

        String query = "SELECT table_name FROM information_schema.tables "
                + "WHERE table_schema = 'public'";
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
        	con = DriverManager.getConnection(url, user, password);
        	pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JavaPostgreSqlListTables.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
