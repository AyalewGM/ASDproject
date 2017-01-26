package application.business;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
	
	private static final long serialVersionUID = 721495266420L;
	public String title;
	public String ISBN;
	public int borrowDuration;
	public boolean availability;
    public Author authors;
    public int numCopies;
    
	public List<LibraryMember> members;

	public Book(String title, String iSBN, int borrowDuration, boolean availability, Author authors, int numCopies) {
		super();
		this.title = title;
		ISBN = iSBN;
		this.borrowDuration = borrowDuration;
		this.availability = availability;
		this.authors = authors;
		this.numCopies=numCopies;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public int getBorrowDuration() {
		return borrowDuration;
	}

	public void setBorrowDuration(int borrowDuration) {
		this.borrowDuration = borrowDuration;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public Author getAuthors() {
		return authors;
	}

	public void setAuthors(Author authors) {
		this.authors = authors;
	}

	public int getCopyNum() {
		return this.numCopies;
	}

	public void setCopyNum(int copyNum) {
		this.numCopies = copyNum;
	}
	
	
	
}
