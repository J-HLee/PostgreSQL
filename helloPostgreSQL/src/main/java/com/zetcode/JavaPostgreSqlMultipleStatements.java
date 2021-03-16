package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlMultipleStatements {
	public static void main(String[] args) {
		
		String url = "jdbc:postgresql://localhost:5432/testdb";
		String user = "postgres";
		String passwd = "1234";
		
		String query= "select id, name from authors where id=1;"
					+ "select id, name from authors where id=2;"
					+ "select id, name from authors where id=3";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, user, passwd);
			pstmt = con.prepareStatement(query);
			boolean isResult = pstmt.execute();
			do {
				try {
					rs = pstmt.getResultSet();
					while(rs.next()) {
						System.out.print(rs.getInt(1));
						System.out.print(": ");
						System.out.println(rs.getString(2));
					}
					isResult = pstmt.getMoreResults();
				}catch(SQLException ex1) {}
			}while(isResult);
		}catch(SQLException ex) {
			Logger lgr = Logger.getLogger(JavaPostgreSqlMultipleStatements.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
