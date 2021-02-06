package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySystem {
	public static Connection conn = null;
	
	public MySystem() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mariadb://localhost:3307/sidibesa";
			conn = DriverManager.getConnection(url, "root", "");
			System.out.println("Connexion etablie!");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
