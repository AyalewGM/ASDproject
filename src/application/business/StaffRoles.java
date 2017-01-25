package application.business;

public abstract class StaffRoles {
	public String userName;
	public String password;
	
	
	
	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	@Override
	
	public boolean equals(Object obj){
		
		return (this.userName.equals(((StaffRoles) obj).userName)
				&& this.password.equals(((StaffRoles) obj).password) );
		
	}
	
	
}
