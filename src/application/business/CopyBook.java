package application.business;

import java.io.Serializable;

public class CopyBook implements Serializable {
	
	private int copyNum;
	private Book bk;
	private static final long serialVersionUID = 1495266420L;
	private boolean availability = true;
	
	public CopyBook(int copyNum, Book bk) {
		super();
		this.copyNum = copyNum;
		this.bk = bk;
		this.availability = true;
	}
	public int getCopyNum() {
		return copyNum;
	}

	public void setCopyNum(int copyNum) {
		this.copyNum = copyNum;
	}

	public Book getBk() {
		return bk;
	}

	public void setBk(Book bk) {
		this.bk = bk;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	
	
	

}
