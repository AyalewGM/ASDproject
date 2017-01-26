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
import application.business.LibraryMember;
import application.business.UserDetails;




public class DataFacade {
	
	public static final String OUTPUT_DIR = System.getProperty("user.dir")
			+ "//src//application//dataaccess//staffInfo.txt";
	
	public static final String OUTPUT_DIR2 = System.getProperty("user.dir")
			+ "//src//application//dataaccess//bookInfo.txt";
	
	//private ArrayList<Book> books = new ArrayList();
	
	public void saveLibraryMember(LibraryMember memb){
		
	}
	
	public  void  saveBook(ArrayList<Book> books) throws IOException{
		
		FileOutputStream fileOutputStream2 = new FileOutputStream(OUTPUT_DIR2);
		ObjectOutputStream output2 = new ObjectOutputStream(fileOutputStream2);
		
		ArrayList booklis= new ArrayList();
		
		try {
			// Read from the stored file
			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR2));
			ObjectInputStream input = new ObjectInputStream(fileInputStream);

			if (input.available() > 0) {

				booklis = (ArrayList) input.readObject();
				input.close();
				System.out.println("working");
				books.addAll(booklis);

				output2.writeObject(books);
                 System.out.println("Added");
				output2.close();
			} else {
				output2.writeObject(books);
				output2.close();
			}

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void findMemberById(){
		
	}
	public Book findBookBySBN(String ISBN){
		try {
			//Read from the stored file
			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR2));
			ObjectInputStream input = new ObjectInputStream(fileInputStream);
			List<Book> books = (List<Book>) input.readObject();
			int copyCount = 0; Book details = null;
			for(Book book: books){
				if(book.getISBN().equals(ISBN)){
					details = book;
					return book;
				}else{
					System.out.println("ISBN provided is invalid");
					return null;
					
				}
			}
			input.close();
			return details;
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} 
	}

}
