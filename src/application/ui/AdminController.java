package application.ui;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.business.Address;
import application.business.Author;
import application.business.Book;
import application.business.CopyBook;
import application.business.LibraryMember;
import application.business.Person;
import application.dataaccess.DataFacade;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminController extends Application implements Initializable {

	private List<Person> members=new ArrayList();
	private ArrayList<Book> books = new ArrayList();
	private ArrayList<CopyBook> copies = new ArrayList();
	
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
	@FXML
	private Button sm;
	@FXML
	private TextField st;
	
	public static final String OUTPUT_DIR = System.getProperty("user.dir")
			+ "\\src\\application\\dataaccess\\staffInfo.txt";
	public static final String OUTPUT_DIR2 = System.getProperty("user.dir")
			+ "\\src\\application\\dataaccess\\bookInfo.txt";

	@FXML
	private void handleSubmit() throws ClassNotFoundException, IOException {

		String fn = fname.getText();
		String ln = lname.getText();
		String pn = phoneNum.getText();
		String sn = streetNum.getText();
		String ct = city.getText();
		String st = state.getText();
		int zp = Integer.parseInt(zip.getText());
		
			Address ad = new Address(sn, ct, st, zp);
			LibraryMember lm = new LibraryMember(fn, ln, pn, ad, LibraryMember.memberId);
			
       // DataFacade pd= new DataFacade();
       DataFacade.saveLibraryMember(lm);
		

	}

	@FXML
	public void addBookHandle() throws IOException {

		boolean ch, ch2;
		int ch3;
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
		 Address a2= new Address(streetNum2.getText(),city2.getText(),state2.getText(),Integer.parseInt(zip2.getText()));
		 Author a1= new Author (fname2.getText(),lname2.getText(),phoneNum2.getText(),a2,ch2,bio.getText());

		//Address a2 = new Address("343", "faf", "afa", 34);
		//Author a1 = new Author("jkajf", "fdafa", "kjkaj", a2, true, "bio");

		int cpn = Integer.parseInt(cp.getText());
		
		Book bk = new Book(title.getText(), isbn.getText(), ch3, ch, a1, cpn);
		
		for(int i=1;i<=cpn;i++){
			CopyBook cp= new CopyBook(i,bk);
			copies.add(cp);
		}


		DataFacade.saveBook(bk);
		clearNewBookForm();
		alertMessage("Book added successfully");

	}
	
	private void clearNewBookForm(){
		fname.setText("");
		lname.setText("");
		phoneNum.setText("");
		streetNum.setText("");
		city.setText("");
		state.setText("");
		zip.setText("");
		title.setText("");
		isbn.setText("");
		bio.setText("");
		cp.setText("");
	}
	
	@FXML
	public void memberSearchHandle() throws NumberFormatException, ClassNotFoundException, IOException{
		LibraryMember lb = DataFacade.findMemberByDetails(memberSearchTf.getText());
	}
	
	private void alertMessage(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION, msg, ButtonType.OK);
		alert.showAndWait();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/ui/Admin.fxml"));
			Scene scene = new Scene(root, 600, 500);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adminstrator");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	public void loadAdminWindow(){
		Pane root;
		try {
			root = (Pane) FXMLLoader.load(SuperAdminController.class.getResource("Admin.fxml"));
			Scene adminScene = new Scene(root);
			Stage newStage = new Stage();
			newStage.setScene(adminScene);
			newStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void clearSearchBookForm(){
		searchField.setText("");
		bookTitle.setText("");
		bookISBN.setText("");
		bookCopies.setText("");
		bDuration.setText("");
		bAvailability.setText("");
		fname21.setText("");
		lname21.setText("");
		phoneNum21.setText("");
		streetNum21.setText("");
		city21.setText("");
		state21.setText("");
		zip21.setText("");
		bio1.setText("");
		cr21.setText("");
	}
	
	@FXML
	private TextField searchField;
	@FXML
	private TextField bookTitle;
	@FXML
	private TextField bookISBN;
	@FXML
	private TextField bookCopies;
	@FXML
	private TextField bDuration;
	@FXML
	private TextField bAvailability;
	@FXML
	private TextField fname21;
	@FXML
	private TextField lname21;
	@FXML
	private TextField phoneNum21;
	@FXML
	private TextField streetNum21;
	@FXML
	private TextField city21;
	@FXML
	private TextField state21;
	@FXML
	private TextField zip21;
	@FXML
	private TextField cp1;
	@FXML
	private TextField bio1;
	@FXML
	private TextField cr21;
	
	
	@FXML
	private TextField memberSearchTf;
	
	@FXML
	public void searchBookHandle() throws IOException, ClassNotFoundException {
		
		String ISBN = this.searchField.getText();
		//DataFacade dataFacade = new DataFacade();
		
		Book bookDetails = DataFacade.findBookByTitle(ISBN);
		try{
			bookTitle.setText(bookDetails.getTitle());
			bookISBN.setText(bookDetails.getISBN());
			bookCopies.setText(Integer.toString(bookDetails.getnumCopies()));
			bDuration.setText(Integer.toString(bookDetails.getBorrowDuration()));
			bAvailability.setText(Boolean.toString(bookDetails.isAvailability()));
			
			fname21.setText(bookDetails.authors.getFirstName());
			lname21.setText(bookDetails.authors.getLastName());
			phoneNum21.setText(bookDetails.authors.getPhoneNumber());
			streetNum21.setText(bookDetails.authors.getAddress().getStreet());
			city21.setText(bookDetails.authors.getAddress().getCity());
			state21.setText(bookDetails.authors.getAddress().getState());
			zip21.setText(Integer.toString(bookDetails.authors.getAddress().getZip()));
			bio1.setText(bookDetails.authors.getBio());
			cr21.setText(Boolean.toString(bookDetails.authors.isCredentials()));
		}catch(Exception e){
			alertMessage("Invalid ISBN entered!! Enter a valid one.");
		}
	}
}
