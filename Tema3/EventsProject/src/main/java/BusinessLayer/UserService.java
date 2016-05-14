package BusinessLayer;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;

import DataLayer.UsersDAOMySQL;
import DataLayer.MySqlConnection;
import DomainModel.Employee;
import DomainModel.User;

public class UserService {
	
	private UsersDAOMySQL uD;
	
	public UserService () {
		Connection connection = MySqlConnection.openConnection("clujT", "root", "");
		this.uD = new UsersDAOMySQL(connection);
	}
	
	public User logIn (String username, String password) {
		
		User u = uD.getUser(username);
		if (u!=null) {
			if (md5(password).equals(u.getPassword())) {
				return u;
			}
		}
		
		return null;
	}
	
	public String updatePassword (String uN) {
		String p =randomString(12);
		if (uD.updateUserPassword(uN, md5(p)) !=0)
			return p;;
		return null;
	}
	
	public boolean insertUser (String uN, String uP, String fN) {
		

		User u = new  Employee (uN,md5(uP),fN);
		if (uD.getUser(uN) == null) {
			if (uD.insertUser(u) != -1)
				return true;
		}
		else {
			System.out.println("User already in system");
			uD.getUser(uN).toString();
			return false;
		}
		return false;
	}
	
	
	public boolean isAdmin (String uN) {
		return uD.isAdmin(uN);
	}
	
	public static String md5(String input) {
        
        String md5 = null;
         
        if(null == input) return null;
         
        try {
             
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
         
        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
 
        //Converts message digest value in base 16 (hex) 
        md5 = new BigInteger(1, digest.digest()).toString(16);
 
        } catch (NoSuchAlgorithmException e) {
 
            e.printStackTrace();
        }
        return md5;
    }
	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	String randomString( int len ){
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}
	


}
