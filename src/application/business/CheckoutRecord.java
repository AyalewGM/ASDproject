package application.business;

import java.io.Serializable;
import java.util.List;

public class CheckoutRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
