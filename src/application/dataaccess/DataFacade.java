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
import application.business.Person;




public class DataFacade {
	
	public static final String OUTPUT_DIR = System.getProperty("user.dir")
			+ "\\src\\application\\dataaccess\\staffInfo.txt";
	
	public static final String OUTPUT_DIR2 = System.getProperty("user.dir")
			+ "\\src\\application\\dataaccess\\bookInfo.txt";
	
	//private ArrayList<Book> books = new ArrayList();
	
	public void saveLibraryMember(Person p) throws IOException, ClassNotFoundException{
		
		ArrayList<LibraryMember> memb=new ArrayList();
		memb.add((LibraryMember)p);
		
		FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_DIR);
		ObjectOutputStream output1 = new ObjectOutputStream(fileOutputStream);
		
		try {
			
			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR));
			ObjectInputStream input2 = new ObjectInputStream(fileInputStream);
		
		if (input2.available() > 0) {

			memb.addAll((ArrayList) input2.readObject());
			input2.close();
			System.out.println("working");
			

			output1.writeObject(memb);
             System.out.println("Added");
			output1.close();
		} else {
			output1.writeObject(memb);
			output1.close();
		}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	
	
	public  void  saveBook(ArrayList<Book> books) throws IOException{
		
		
		FileOutputStream fileOutputStream2 = new FileOutputStream(OUTPUT_DIR2);
		ObjectOutputStream output2 = new ObjectOutputStream(fileOutputStream2);
		
		ArrayList<Book> booklis= new ArrayList();
		
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
	
	public LibraryMember findMemberById(int id) throws IOException, ClassNotFoundException{
		
		LibraryMember memberf= null;
		
     FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR));
		
		ObjectInputStream input = new ObjectInputStream(fileInputStream);
		ArrayList<LibraryMember> memlis= new ArrayList();
		
		if (input.available() > 0) {
			memlis = (ArrayList) input.readObject();
			
			for(int i=0;i<memlis.size();i++){
				
				if(memlis.get(i).getMemberId()==id){
					memberf=(memlis.get(i));
				}
				
			}
			
		}
		
		
		return memberf;
		
		
	}

}
