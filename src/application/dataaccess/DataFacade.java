package application.dataaccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import application.business.Book;
import application.business.CheckoutRecord;
import application.business.CopyBook;
import application.business.LibraryMember;

public class DataFacade {

	// public static final String OUTPUT_DIR = System.getProperty("user.dir")
	// + "\\src\\application\\dataaccess\\staffInfo.txt";
	//
	// public static final String OUTPUT_DIR2 = System.getProperty("user.dir")
	// + "\\src\\application\\dataaccess\\bookInfo.txt";
	//
	// public static final String OUTPUT_DIR3 = System.getProperty("user.dir")
	// + "\\src\\application\\dataaccess\\copyBookInfo.txt";

	public static final String OUTPUT_DIR = System.getProperty("user.dir")
			+ "//src//application//dataaccess//staffInfo.txt";

	public static final String OUTPUT_DIR2 = System.getProperty("user.dir")
			+ "//src//application//dataaccess//bookInfo.txt";

	public static final String OUTPUT_DIR3 = System.getProperty("user.dir")
			+ "//src//application//dataaccess//copyBookInfo.txt";
	
	public static final String OUTPUT_DIR4 = System.getProperty("user.dir")
			+ "//src//application//dataaccess//checkoutlist.txt";

	public static void saveLibraryMember(LibraryMember p) throws IOException, ClassNotFoundException {

		List<LibraryMember> memb = new ArrayList<LibraryMember>();
		memb.add(p);
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR));
			ObjectInputStream input2 = new ObjectInputStream(fileInputStream);
			memb.addAll((ArrayList) input2.readObject());

			input2.close();
			System.out.println("read successfully!");

			FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_DIR);
			ObjectOutputStream output1 = new ObjectOutputStream(fileOutputStream);

			output1.writeObject(memb);
			System.out.println("Added");
			output1.close();

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void saveBook(Book bk) throws IOException {

		saveBookCopies(bk);
		
		ArrayList<Book> books = new ArrayList<Book>();
		books.add(bk);
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR2));
			ObjectInputStream input2 = new ObjectInputStream(fileInputStream);
			books.addAll((ArrayList) input2.readObject());
			input2.close();
			System.out.println("read successfully!");
			FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_DIR2);
			ObjectOutputStream output1 = new ObjectOutputStream(fileOutputStream);
			output1.writeObject(books);
			System.out.println("Added");
			output1.close();

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			addFirstBook(books);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void saveBookCopies(Book book) throws IOException{
		List<CopyBook> copies = new ArrayList<CopyBook>();
		for(int i = 1; i <= book.getnumCopies(); i++){
			CopyBook copy = new CopyBook(i, book);
			copies.add(copy);
		}
		saveCopyBook(copies);
	}
	
	public static void saveExtraBookCopies(Book book, int extraCopies) throws IOException{
		List<CopyBook> copies = new ArrayList<CopyBook>();
		for(int i = 1; i <= extraCopies; i++){
			CopyBook copy = new CopyBook(i, book);
			copies.add(copy);
		}
		saveCopyBook(copies);
	}
	
	public static void addFirstBook(List<Book> books) throws IOException{
		FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_DIR2);
		ObjectOutputStream output1 = new ObjectOutputStream(fileOutputStream);

		output1.writeObject(books);
		System.out.println("Added");
		output1.close();
	}
	public static void saveCopyBook(List<CopyBook> cps) throws IOException {
		FileOutputStream fileOutputStream2 = new FileOutputStream(OUTPUT_DIR3);
		ObjectOutputStream output2 = new ObjectOutputStream(fileOutputStream2);
		List<CopyBook> booklis = new ArrayList<CopyBook>();
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR3));
			ObjectInputStream input = new ObjectInputStream(fileInputStream);
			booklis = (ArrayList<CopyBook>) input.readObject();
			input.close();
			System.out.println("working");
			cps.addAll(booklis);
			output2.writeObject(cps);
			System.out.println("Added");
			output2.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			output2.writeObject(cps);
			output2.close();
			System.out.println("Something is wrong here!!");
//			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void writeBooksToFile(List<Book> newBooks) throws IOException {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_DIR2);
			ObjectOutputStream output1 = new ObjectOutputStream(fileOutputStream);

			output1.writeObject(newBooks);
			System.out.println("Added");
			output1.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static LibraryMember findMemberByName(String name) throws IOException, ClassNotFoundException {

		LibraryMember memberf = null;
		ArrayList<LibraryMember> memlis = new ArrayList<LibraryMember>();

		try {

			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR));

			ObjectInputStream input = new ObjectInputStream(fileInputStream);

			memlis = (ArrayList) input.readObject();

			input.close();

			for (int i = 0; i < memlis.size(); i++) {

				if (memlis.get(i).getFirstName().equals(name)) {

					memberf = (memlis.get(i));

				}

			}

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return memberf;

	}

	public static Book findBookByISBN(String title) throws IOException, ClassNotFoundException {
		Book bkf = null;
		List<Book> memlis = new ArrayList<Book>();

		try {
			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR2));
			ObjectInputStream input = new ObjectInputStream(fileInputStream);
			memlis = (ArrayList) input.readObject();
			input.close();
			
			for (int i = 0; i < memlis.size(); i++) {
				if (memlis.get(i).getISBN().equals(title)) {
					bkf = (memlis.get(i));
				}
			}

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return bkf;
	}
	
	public static LibraryMember findMemberByMemberId(String memberID) throws IOException, ClassNotFoundException {
		System.out.println("I am finished!!!");
		LibraryMember memberf = null;
		List<LibraryMember> memlis = new ArrayList<LibraryMember>();
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR));
			ObjectInputStream input = new ObjectInputStream(fileInputStream);
			memlis = (ArrayList) input.readObject();
			input.close();
			for (int i = 0; i < memlis.size(); i++) {
				if (memlis.get(i).getMemberId().equals(memberID)) {
					memberf = (memlis.get(i));
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("ffffffffffffffffffffffffffffff");
		} catch (IOException e) {
			System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("ccccccccccccccccccccccccccccccccccccc");
			e.printStackTrace();
		}
		return memberf;
	}
	
	public static List<CheckoutRecord> getAllCheckoutRecords() throws IOException, ClassNotFoundException {
		List<CheckoutRecord> records = new ArrayList<CheckoutRecord>();
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR4));
			ObjectInputStream input = new ObjectInputStream(fileInputStream);
			records = (ArrayList<CheckoutRecord>) input.readObject();
			
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return records;
	}
	public List<Book> getAllBooks(){
		List<Book> books = null;
		try {

			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR2));
			ObjectInputStream input = new ObjectInputStream(fileInputStream);
			books = (ArrayList<Book>) input.readObject();
			input.close();
			System.out.println("read successfully!");

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return books;
	}
	
	public static List<CopyBook> getBookCopiesByISBN(String ISBN) throws IOException, ClassNotFoundException {
		List<CopyBook> temp = new ArrayList<CopyBook>();
		List<CopyBook> copies = new ArrayList<CopyBook>();

		try {
			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR3));
			ObjectInputStream input = new ObjectInputStream(fileInputStream);
			copies = (ArrayList) input.readObject();
			input.close();
			
			for (int i = 0; i < copies.size(); i++) {
				if (copies.get(i).getBk().getISBN().equals(ISBN)) {
					temp.add(copies.get(i));
				}
			}

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return temp;
	}

}