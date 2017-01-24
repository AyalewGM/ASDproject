package application.business;

import java.util.List;

public class Book {
	public String title;
	public String ISBN;
	public String bookType;
	public boolean availability;
	
	public List<Author> authors;
	public List<LibraryMember> members;
	
}
