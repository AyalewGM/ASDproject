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
import application.business.Person;

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
	// private ArrayList<Book> books = new ArrayList();

	public static void saveLibraryMember(LibraryMember p) throws IOException, ClassNotFoundException {

		ArrayList<LibraryMember> memb = new ArrayList();
		memb.add(p);

//		try {
//
//			FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_DIR);
//			ObjectOutputStream output1 = new ObjectOutputStream(fileOutputStream);
//
//			output1.writeObject(memb);
//			System.out.println("Added");
//			output1.close();
//
//		} catch (FileNotFoundException e) {
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

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

		ArrayList<Book> books = new ArrayList();
		books.add(bk);

		// try {
		//
		//
		// FileOutputStream fileOutputStream = new
		// FileOutputStream(OUTPUT_DIR2);
		// ObjectOutputStream output1 = new
		// ObjectOutputStream(fileOutputStream);
		//
		// output1.writeObject(books);
		// System.out.println("Added");
		// output1.close();
		//
		//
		// } catch (FileNotFoundException e) {
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		//
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

	public static LibraryMember findMemberByName(String name) throws IOException, ClassNotFoundException {

		LibraryMember memberf = null;
		ArrayList<LibraryMember> memlis = new ArrayList();

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
		ArrayList<Book> memlis = new ArrayList();

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

}