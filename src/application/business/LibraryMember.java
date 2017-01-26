package application.business;

import java.text.SimpleDateFormat;
import java.util.List;

public class LibraryMember extends Person{
	//private static int memberId=1;
	
	private String memberId;
	
	private List<Book> books;
	
	
	public LibraryMember(String firstName, String lastName, String phoneNumber, Address address) {
		super(firstName, lastName, phoneNumber, address);
		
				
		char s= firstName.charAt(0);
		char y= lastName.charAt(0);
		
		int digit = (int)(Math.random()*900) + 100;
		
		 
		
		StringBuilder myst= new StringBuilder();
		myst.append(s);
		myst.append(y);
		myst.append(Integer.toString(digit));
		 
		this.memberId= myst.toString();
		
		//this.memberId = memberId++;
		
	}

	
	
	/*public  static int getMemberId() {
		return memberId;
	}*/
	
	public  String getMemberId() {
		return memberId;
	}
}