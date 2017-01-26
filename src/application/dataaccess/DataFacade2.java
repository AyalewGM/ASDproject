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
import application.business.CopyBook;
import application.business.LibraryMember;
import application.business.UserDetails;
import application.business.Person;

public class DataFacade2 {

	public static final String OUTPUT_DIR = System.getProperty("user.dir")
			+ "//src//application//dataaccess//staffInfo.txt";

	public static final String OUTPUT_DIR2 = System.getProperty("user.dir")
			+ "//src//application//dataaccess//bookInfo.txt";

	public static final String OUTPUT_DIR3 = System.getProperty("user.dir")
			+ "//src//application//dataaccess//copyBookInfo.txt";
//	public static final String OUTPUT_DIR = System.getProperty("user.dir")
//			+ "\\src\\application\\dataaccess\\staffInfo.txt";
//	
//	public static final String OUTPUT_DIR2 = System.getProperty("user.dir")
//			+ "\\src\\application\\dataaccess\\bookInfo.txt";
//	
//	public static final String OUTPUT_DIR3 = System.getProperty("user.dir")
//			+ "\\src\\application\\dataaccess\\copyBookInfo.txt";
	// private ArrayList<Book> books = new ArrayList();

public static void saveLibraryMember(LibraryMember p) throws IOException, ClassNotFoundException{
		
		ArrayList<LibraryMember> memb=new ArrayList();
		memb.add(p);
//		
//   try {
//			
//			
//			FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_DIR);
//			ObjectOutputStream output1 = new ObjectOutputStream(fileOutputStream);
//			
//			output1.writeObject(memb);
//             System.out.println("Added");
//			output1.close();
//		
//		
//		} catch (FileNotFoundException e) {
//		} catch (IOException e) {
//			e.printStackTrace();
//		} 
		
		try{
			
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
			
		}catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		}	

	}
	

public  static void  saveBook(Book bk) throws IOException{
	
	ArrayList<Book> books=new ArrayList();
	books.add(bk);
	
//	   try {
//			
//			
//			FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_DIR2);
//			ObjectOutputStream output1 = new ObjectOutputStream(fileOutputStream);
//			
//			output1.writeObject(books);
//             System.out.println("Added");
//			output1.close();
//		
//		
//		} catch (FileNotFoundException e) {
//		} catch (IOException e) {
//			e.printStackTrace();
//		} 
//	
try{
		
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
		
	}catch (FileNotFoundException e) {
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
	e.printStackTrace();
	}	
	
}

	public void saveCopyBook(ArrayList<CopyBook> cps) throws IOException {

		FileOutputStream fileOutputStream2 = new FileOutputStream(OUTPUT_DIR3);
		ObjectOutputStream output2 = new ObjectOutputStream(fileOutputStream2);

		ArrayList<CopyBook> booklis = new ArrayList();

		try {

			// Read from the stored file
			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR3));
			ObjectInputStream input = new ObjectInputStream(fileInputStream);

			if (input.available() > 0) {

				booklis = (ArrayList) input.readObject();
				input.close();
				System.out.println("working");
				cps.addAll(booklis);

				output2.writeObject(cps);
				System.out.println("Added");
				output2.close();
			} else {
				output2.writeObject(cps);
				output2.close();
			}

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public LibraryMember findMemberById(int id) throws IOException, ClassNotFoundException {

		LibraryMember memberf = null;

		FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR));

		ObjectInputStream input = new ObjectInputStream(fileInputStream);
		ArrayList<LibraryMember> memlis = new ArrayList();

		if (input.available() > 0) {
			memlis = (ArrayList) input.readObject();

			for (int i = 0; i < memlis.size(); i++) {

				if (memlis.get(i).getMemberId() == id) {
					memberf = (memlis.get(i));
				}
			}
		}
		return memberf;
	}
	
public static LibraryMember findMemberByName(String name) throws IOException, ClassNotFoundException{
		
		LibraryMember memberf= null;
		ArrayList<LibraryMember> memlis= new ArrayList();
		
		try{
			
     FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR));
		
		ObjectInputStream input = new ObjectInputStream(fileInputStream);
		
		
			memlis = (ArrayList) input.readObject();
			
			input.close();
			
			for(int i=0;i<memlis.size();i++){
				
				if(memlis.get(i).getFirstName().equals(name)){
					
					
					memberf=(memlis.get(i));
					
				}
				
			}
			
		
		
		}catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return memberf;
		
		
	}

public static Book findBookByTitle(String title) throws IOException, ClassNotFoundException{
	
	Book bkf= null;
	ArrayList<Book> memlis= new ArrayList();
	
	try{
		
 FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR2));
	
	ObjectInputStream input = new ObjectInputStream(fileInputStream);
	
	
		memlis = (ArrayList) input.readObject();
		
		input.close();
		
		for(int i=0;i<memlis.size();i++){
			
			if(memlis.get(i).getTitle().equals(title)){
				
				
				bkf=(memlis.get(i));
				
			}
			
		}
		
	
	
	}catch (FileNotFoundException e) {
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	
	
	return bkf;
	
	
}

	public static Book findBookBySBN(String ISBN) {
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR2));
			ObjectInputStream input = new ObjectInputStream(fileInputStream);
			List<Book> books = (List<Book>) input.readObject();
			int copyCount = 0;
			Book details = null;
			for (Book book : books) {
				if (book.getISBN().equals(ISBN)) {
					details = book;
					return book;
				} else {
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

	public static LibraryMember findMemberByDetails(String detail) throws IOException, ClassNotFoundException {

		LibraryMember memberf = null;
		ArrayList<LibraryMember> memlis = new ArrayList();

		try {
			System.out.println("==================================" + detail);
			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR));
			ObjectInputStream input = new ObjectInputStream(fileInputStream);
			memlis = (ArrayList) input.readObject();
			input.close();
			for (int i = 0; i < memlis.size(); i++) {
				System.out.println(memlis.get(i).getFirstName());
				if (memlis.get(i).getFirstName().equals(detail)) {
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

}