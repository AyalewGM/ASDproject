package application.business;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
	
	private static final long serialVersionUID = 8309080721495266420L;
	public String title;
	public String ISBN;
	public int bookType;
	public boolean availability;
	
	public List<Author> authors;
	public List<LibraryMember> members;
	
}
