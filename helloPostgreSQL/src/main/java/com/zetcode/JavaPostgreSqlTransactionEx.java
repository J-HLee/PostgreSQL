package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlTransactionEx {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "postgres";
        String password = "1234";

        try (Connection con = DriverManager.getConnection(url, user, password)) {

            try (Statement st = con.createStatement()) {

                con.setAutoCommit(false);
                st.executeUpdate("UPDATE authors SET name = 'Leo Tolstoy' "
                        + "WHERE Id = 1");
                st.executeUpdate("UPDATE books SET title = 'War and Peace' "
                        + "WHERE Id = 1");
                st.executeUpdate("UPDATE books SET titl = 'Anna Karenina' "
                        + "WHERE Id = 2");

                con.commit();

            } catch (SQLException ex) {

                if (con != null) {
                    try {
                        con.rollback();
                    } catch (SQLException ex1) {
                        Logger lgr = Logger.getLogger(JavaPostgreSqlTransactionEx.class.getName());
                        lgr.log(Level.WARNING, ex1.getMessage(), ex1);
                    }
                }

                Logger lgr = Logger.getLogger(JavaPostgreSqlTransactionEx.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JavaPostgreSqlTransactionEx.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}