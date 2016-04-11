package DataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

		public MySqlConnection () {
		}
		
		public static Connection openConnection (String database, String user, String password) {
			
			String drv = "jdbc:mysql://localhost:3306/" + database + "?useSSL=false";
			try {
				return DriverManager.getConnection(drv, user, password);
			
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public static boolean closeConnection (Connection connection) {
			try {
				connection.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();	
				return false;
			}
		}
}
