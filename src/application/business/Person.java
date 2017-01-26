package application.business;

import java.io.Serializable;
import java.util.List;

public abstract class Person implements Serializable {
	
	public String firstName;
	public String lastName;
	public String phoneNumber;
	public Address address;
	public StaffRoles role;
	private static final long serialVersionUID = 80721495266420L;
	
	public Person(String firstName, String lastName, String phoneNumber, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public StaffRoles getRole() {
		return role;
	}
	public void setRole(StaffRoles role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return getFirstName() + " " + getLastName() + " " + getPhoneNumber()+""+
	getAddress().getStreet()+" "+getAddress().getCity()+" "+getAddress().getState()+" "+getAddress().getZip();
	}
	
	
}

