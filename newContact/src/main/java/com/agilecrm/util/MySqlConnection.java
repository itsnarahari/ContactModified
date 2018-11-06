package com.agilecrm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

		public static Connection getConnection() throws ClassNotFoundException, SQLException{  
			 Class.forName("com.mysql.jdbc.Driver");  
	         return DriverManager.getConnection("jdbc:mysql://localhost:3306/training","root","root123");  
		}  

}
