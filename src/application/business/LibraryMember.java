package application.business;

import java.util.List;

public class LibraryMember extends Person{
	private static int memberId=1;
	private List<Book> books;
	
	
	public LibraryMember(String firstName, String lastName, String phoneNumber, Address address, 
			int memberId) {
		super(firstName, lastName, phoneNumber, address);
		this.memberId = memberId++;
		
	}

	public static int getMemberId() {
		return memberId;
	}
	
	
}