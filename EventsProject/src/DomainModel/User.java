package DomainModel;

public class User {

	private String userName;
	private String userPassword;
	//private boolean admin;
	
	public User(String name, String pass) {
		this.userName = name;
		this.userPassword = pass;
		
	}
	
	public String getUser()  {
		return this.userName;
	}
	
	public String getPassword() {
		return this.userPassword;
	}
	
	public void setUser( String usr) {
		this.userName = usr;
	}
	
	public void setPassword(String pass) {
		this.userPassword = pass;
	}
	
	@Override
	public String toString () {
		String str = "Username: " + userName + "\nPassword: " + userPassword;
		return str;
	}
}
