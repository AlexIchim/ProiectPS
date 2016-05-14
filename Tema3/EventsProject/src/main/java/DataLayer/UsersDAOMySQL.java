package DataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DomainModel.Admin;
import DomainModel.Employee;
import DomainModel.User;

public class UsersDAOMySQL implements UsersRepository {

	
	private Connection connection;
	   
	public UsersDAOMySQL (Connection connection) {
		this.connection = connection;
	}
	
	public User getUser(String username)   {
		
		User user = null;
		try {
			String sql = "SELECT * FROM users WHERE username = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,username);
			ResultSet results = statement.executeQuery();
			
			
			if (results.next()) {
				
				if (results.getBoolean("admin")) {
					user = new Admin(
							results.getString("username"),
							results.getString("userpassword"));
				} else {
					user = new Employee(
							results.getString("username"),
							results.getString("userpassword"),
							results.getString("firstName"));
					
				}
				
				results.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
		
	}
	
	public boolean isAdmin(String username)   {
		
		try {
			String sql = "SELECT * FROM users WHERE username = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,username);
			ResultSet results = statement.executeQuery();
			
			if (results.next()) 
				if (results.getBoolean("admin")) {
					return true;
				}
			results.close();
			return false;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public int updateUserPassword (String username,String pass ) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE users SET userpassword = ? WHERE username = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			
			statement.setString(1, pass);
			statement.setString(2, username);
			return statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int insertUser (User user) {
		try {
			String sql = "INSERT INTO users(username, userpassword, firstName, admin) VALUES(?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			
			statement.setString(1, ((Employee) user).getUser());
			statement.setString(2, ((Employee) user).getPassword());
			statement.setString(3, ((Employee) user).getEmpName());	
			statement.setBoolean(4, false);

			return statement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	


	public boolean insertUser() {
		// TODO Auto-generated method stub
		return false;
	}


}
