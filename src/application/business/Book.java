package application.business;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
	
	private static final long serialVersionUID = 8309080721495266420L;
	public String title;
	public String ISBN;
	public int borrowDuration;
	public boolean availability;
    public Author authors;
    
	public List<LibraryMember> members;

	public Book(String title, String iSBN, int borrowDuration, boolean availability, Author authors) {
		super();
		this.title = title;
		ISBN = iSBN;
		this.borrowDuration = borrowDuration;
		this.availability = availability;
		this.authors = authors;
	}
	
	
	
}
