/**
 * User.java
 * @author Alyssa Reyes
 * 
 * Define basic functions that will be used by the Supplier and 
 * Customer classes.
 * 
 * CIS 22C, Course Project
 */
public abstract class User {
	private String userName; 
	private String password;
	
	public User() { 
		userName = "";
		password = ""; 
	}
	
	/**
	 * Mutators
	 */
	public void setUserName (String u) { 
		userName = u; 
	}
	
	public void setPassword (String p) { 
		password = p; 
	}
	
	/**
	 * Accessors
	 */
	public String getUserName() { 
		return userName;
	}
	
	public String getPassword() { 
		return password;
	}

}
