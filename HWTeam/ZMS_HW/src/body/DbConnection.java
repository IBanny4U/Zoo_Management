package body;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	private static final String URL="jdbc:mysql://localhost:3306/ZooManagementSystem";
	private static final String USER="root";
	private static final String PASSWORD="mintOfficial2BE4$";
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(URL,USER,PASSWORD);
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
