package application.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import application.business.Address;
import application.business.Author;
import application.business.Book;
import application.business.LibraryMember;
import application.business.Person;
import application.dataaccess.DataFacade;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminController extends Application implements Initializable {

	private List<Person> members;
	private ArrayList<Book> books = new ArrayList();

	@FXML
	private TextField fname;
	@FXML
	private TextField lname;
	@FXML
	private TextField phoneNum;
	@FXML
	private TextField streetNum;
	@FXML
	private TextField city;
	@FXML
	private TextField state;
	@FXML
	private TextField zip;

	@FXML
	private TextField title;
	@FXML
	private TextField isbn;

	@FXML
	private TextField auth;

	@FXML
	private ComboBox<String> cb;

	@FXML
	private ComboBox<String> cb2;

	@FXML
	private TextField fname2;
	@FXML
	private TextField lname2;
	@FXML
	private TextField phoneNum2;
	@FXML
	private TextField streetNum2;
	@FXML
	private TextField city2;
	@FXML
	private TextField state2;
	@FXML
	private TextField zip2;
	@FXML
	private ComboBox<String> cb3;
	@FXML
	private TextField cp;

	@FXML
	private TextField bio;

	public static final String OUTPUT_DIR = System.getProperty("user.dir")
			+ "\\src\\application\\dataaccess\\staffInfo.txt";
	public static final String OUTPUT_DIR2 = System.getProperty("user.dir")
			+ "\\src\\application\\dataaccess\\bookInfo.txt";

	@FXML
	private void handleSubmit() {

		String fn = fname.getText();
		String ln = lname.getText();
		String pn = phoneNum.getText();
		String sn = streetNum.getText();
		String ct = city.getText();
		String st = state.getText();
		int zp = Integer.parseInt(zip.getText());

		try {

			// Store Serialized User Object in File
			FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_DIR);
			Address ad = new Address(sn, ct, st, zp);
			Person p = new LibraryMember(fn, ln, pn, ad, LibraryMember.getMemberId());
			ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
			output.writeObject(p);

			output.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void addBookHandle() throws IOException {

		boolean ch, ch2;
		int ch3;
		FileOutputStream fileOutputStream2 = new FileOutputStream(OUTPUT_DIR2);
		ObjectOutputStream output2 = new ObjectOutputStream(fileOutputStream2);

		if (cb2.getValue().equals("Available"))
			ch = true;
		else
			ch = false;

		if (cb3.getValue().equals("Accredited"))
			ch2 = true;
		else
			ch2 = false;

		if (cb.getValue().equals("7 days"))
			ch3 = 7;
		else
			ch3 = 21;
		// Address a2= new
		// Address(streetNum2.getText(),city2.getText(),state2.getText(),Integer.parseInt(zip2.getText()));
		// Author a1= new Author
		// (fname2.getText(),lname2.getText(),phoneNum2.getText(),a2,ch2,bio.getText());

		Address a2 = new Address("343", "faf", "afa", 34);
		Author a1 = new Author("jkajf", "fdafa", "kjkaj", a2, true, "bio");

		int cpn = Integer.parseInt(cp.getText());
		

		for (int i = 1; i <= cpn; i++) {
			Book bk = new Book(title.getText(), isbn.getText(), ch3, ch, a1, i);

			books.add(bk);
		}
		// output2.writeObject(books);

		DataFacade d= new DataFacade();
		d.saveBook(books);

	}

	@Override

	public void start(Stage primaryStage) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/ui/Admin.fxml"));
			Scene scene = new Scene(root, 600, 500);
			// scene.getStylesheets().add(getClass().getResource("admin.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adminstrator");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		cb.getItems().removeAll(cb.getItems());
		cb.getItems().addAll("7 days", "21 days");
		cb.getSelectionModel().select("7 days");

		cb2.getItems().removeAll(cb2.getItems());
		cb2.getItems().addAll("Available", "Not Available");
		cb2.getSelectionModel().select("Available");

		cb3.getItems().removeAll(cb3.getItems());
		cb3.getItems().addAll("Accredited", "Not Accredited");
		cb3.getSelectionModel().select("Accredited");
	}

	class NoHeaderObjectOutputStream extends ObjectOutputStream {
		public NoHeaderObjectOutputStream(OutputStream os) throws IOException {
			super(os);
		}

		protected void writeStreamHeader() {
		}
	}

}
