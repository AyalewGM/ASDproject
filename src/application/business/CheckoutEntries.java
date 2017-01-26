package application.business;

import java.io.Serializable;
import java.util.Date;

public class CheckoutEntries implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Date dateOfCheckout;
	public Date dueDate;
	public CheckoutRecord checkoutRecord;
	public CheckoutEntries(Date dateOfCheckout, Date dueDate, CheckoutRecord checkoutRecord) {
		super();
		this.dateOfCheckout = dateOfCheckout;
		this.dueDate = dueDate;
		this.checkoutRecord = checkoutRecord;
	}
	
}
