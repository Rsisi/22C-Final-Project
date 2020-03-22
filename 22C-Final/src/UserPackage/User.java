package UserPackage;

import DataStructurePackage.*;
import DataStructurePackage.Comparable;

public class User implements FileOutPutFormat, Contain, Comparable<User> {
	private String username;
	private String password;
	private String email;
	private String securityAnswer;

	public User(String username, String password, String email, String securityAnswer) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.securityAnswer = securityAnswer;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public User(String username, String email, String securityAnswer) {
		this.username = username;
		this.email = email;
		this.securityAnswer = securityAnswer;
	}

	public String getUserName() {
		return this.username;
	}

	public boolean check(String username, String email, String securityAnswer) {
		return this.username.contentEquals(username) && this.email.equals(email)
				&& this.securityAnswer.equals(securityAnswer);
	}

	public void setpassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (!(o instanceof User)) {
			return false;
		} else {
			User u = (User) o;
			return this.username.equals(u.username) && this.email.equals(u.email)
					&& this.securityAnswer.equals(securityAnswer);
		}
	}

	@Override
	public int hashCode() {
		int key = 0;
		for (int i = 0; i < username.length(); i++) {
			key += (int) username.charAt(i);
		}

		return key;
	}

	@Override
	public String toString() {
		return "Username: " + username + "\nPassword: " + password;
	}

	@Override
	public String fileOutPutFormat() {
		return username + "\n" + password + "\n"+email+"\n"+securityAnswer+"\n";
	}





	@Override
	public int compareTo(User u) {
		if (this.equals(u)) {
			return 0;
		} else {
			if (username.compareTo(u.username) < 0) {
				return -1;
			} else if (username.compareTo(u.username) > 0) {
				return 1;
			}

			else {
				if (password.compareTo(u.password) < 0) {
					return -1;
				}
				else if(password.compareTo(u.password)>0) {
					return 1;
				}
			}

			return 0;

		}
	}

	@Override
	public boolean contain(String s1) {
		return username.equals(s1);
		
	}

	@Override
	public int CompareSecondaryKey(User t) {
		// TODO Auto-generated method stub
		return 0;
	}
}
