package application.business;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CheckoutRecord implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String dueDate;
	private String memberId;
	private String bookISBN;
	private String bookTittle;
	private Date checkoutDate;
	private CopyBook copyBook;
	 
	public CheckoutRecord(String dueDate, String memberId, String bookISBN, String bookTittle, Date checkoutDate, CopyBook copyBook) {
		this.dueDate = dueDate;
		this.memberId = memberId;
		this.bookISBN = bookISBN;
		this.bookTittle = bookTittle;
		this.checkoutDate = checkoutDate;
		this.copyBook = copyBook;
	}
	
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	public String getBookTittle() {
		return bookTittle;
	}
	public void setBookTittle(String bookTittle) {
		this.bookTittle = bookTittle;
	}
	public Date getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	public CopyBook getCopyBook() {
		return copyBook;
	}
	public void setCopyBook(CopyBook copyBook) {
		this.copyBook = copyBook;
	}





	private LibraryMember recordMember;
	public List<CheckoutEntries> checkoutEntries;
	public CheckoutRecord(LibraryMember recordMember) {
		this.recordMember = recordMember;
		//this.checkoutEntries = checkoutEntries;
	}
	public LibraryMember getRecordMember() {
		return recordMember;
	}
	public void setRecordMember(LibraryMember recordMember) {
		this.recordMember = recordMember;
	}
	public List<CheckoutEntries> getCheckoutEntries() {
		return checkoutEntries;
	}
	public void setCheckoutEntries(List<CheckoutEntries> checkoutEntries) {
		this.checkoutEntries = checkoutEntries;
	}
	
	
	
}
