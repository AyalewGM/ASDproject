package application.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckoutRecord {
	private LibraryMember recordMember;
	private Book bk;
	public Date dateOfCheckout;
	public Date dueDate;
	
	public List<Book> books=new ArrayList<Book>();

	public CheckoutRecord(LibraryMember recordMember, Book bk, Date dateOfCheckout, Date dueDate) {
		super();
		this.recordMember = recordMember;
		books.add(bk);
		this.dateOfCheckout = dateOfCheckout;
		this.dueDate = dueDate;
	}
	
	public void addBooks(Book b){
		this.books.add(b);
	}

	public LibraryMember getRecordMember() {
		return recordMember;
	}

	public void setRecordMember(LibraryMember recordMember) {
		this.recordMember = recordMember;
	}

	public Book getBk() {
		return bk;
	}

	public void setBk(Book bk) {
		this.bk = bk;
	}

	public Date getDateOfCheckout() {
		return dateOfCheckout;
	}

	public void setDateOfCheckout(Date dateOfCheckout) {
		this.dateOfCheckout = dateOfCheckout;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	
}
