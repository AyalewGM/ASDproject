package application.dataaccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import application.business.Book;
import application.business.LibraryMember;




public class DataFacade {
	
	public static final String OUTPUT_DIR = System.getProperty("user.dir")
			+ "\\src\\application\\dataaccess\\staffInfo.txt";
	
	public static final String OUTPUT_DIR2 = System.getProperty("user.dir")
			+ "\\src\\application\\dataaccess\\bookInfo.txt";
	
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

}
