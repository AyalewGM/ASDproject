package application.business;

import java.util.List;

public class Author extends Person{
	public boolean credentials;
	public String bio;
	public Book books;
	public Author(String firstName, String lastName, String phoneNumber, Address address, 
			boolean credentials, String bio, Book books) {
		super(firstName, lastName, phoneNumber, address);
		this.credentials = credentials;
		this.bio = bio;
		this.books = books;
	}
	
	
	
}
