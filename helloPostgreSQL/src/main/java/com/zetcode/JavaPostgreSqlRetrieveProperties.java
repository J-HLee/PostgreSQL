package com.zetcode;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSqlRetrieveProperties {
	public static Properties readProperties() {
		Properties props = new Properties();
		Path myPath = Paths.get("src/main/resources/database.properties");
		System.out.println(myPath);
		
		try {
			BufferedReader bf = Files.newBufferedReader(myPath, StandardCharsets.UTF_8);
			props.load(bf);
		}catch(IOException ex) {
			Logger.getLogger(JavaPostgreSqlRetrieveProperties.class.getName()).log(Level.SEVERE, null, ex);
		}
		return props;
	}
	
	public static void main(String[] args) {
		Properties props = readProperties();
		String url = props.getProperty("db.url");
		String user = props.getProperty("db.user");
		String passwd = props.getProperty("db.passwd");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			while(rs.next()) {
			con = DriverManager.getConnection(url, user, passwd);
			pstmt = con.prepareStatement("select * from Authors");
			rs = pstmt.executeQuery(); 
			
			System.out.println(rs.getInt(1));
			System.out.println(": ");
			System.out.println(rs.getString(2));
			
			}
		}catch(SQLException ex) {
			Logger lgr = Logger.getLogger(JavaPostgreSqlRetrieveProperties.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	
				
	}
}
