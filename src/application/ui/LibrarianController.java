package application.ui;

import application.business.*;
import application.dataaccess.DataFacade;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LibrarianController extends Application {
	public static final String OUTPUT_DIR_Book = System.getProperty("user.dir")
			+ "//src//application//dataaccess//bookInfo.txt";

	public static final String OUTPUT_DIR_Member = System.getProperty("user.dir")
			+ "//src//application//dataaccess//staffInfo.txt";

	public static final String OUTPUT_DIR_CheckoutList = System.getProperty("user.dir")
			+ "//src//application//dataaccess//checkoutlist.txt";

	@FXML
	private Label lblMemberId;

	@FXML
	private Label lblISBN;

	@FXML
	private Label lblDueDate;

	@FXML
	private TextField txtdueDate;

	@FXML
	private Button btnCheckout;

	@FXML
	private Button btnCancel;

	@FXML
	private Librarian librarian;
	@FXML
	private TableColumn<CheckoutEntries, String> tcmemberId;
	@FXML
	private TableColumn<CheckoutEntries, String> tcbookISSBN;

	@FXML
	private TableColumn<CheckoutEntries, String> tccheckoutDate;
	@FXML
	private TableColumn<CheckoutEntries, String> tcdueDate;
	Tab tbView;
	@FXML
	private TextField txtMemberId;
	@FXML
	private TextField txtISBN;
	@FXML
	private Label coBookTitle;
	@FXML
	private Label coBookAuth;
	@FXML
	private Label coBookAvailability;
	@FXML
	private Label coBookCopyCount;

	private boolean memberValid = false;
	private boolean isbnValid = false;
	LibraryMember memberCheckout;
	
	public LibrarianController() {

	}

	@FXML
	public void checkMemberValidity(){
		String reqMemberId = txtMemberId.getText();
		LibraryMember member;
		try {
			member = DataFacade.findMemberByMemberId(reqMemberId);
			if (member.getMemberId().equals(reqMemberId)) {
				memberValid = true;
				System.out.println("Valid member Id");
			} else {
				memberValid = false;
				alertMessage("Invalid / Unregistered Member Id provided");
			}
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			e.printStackTrace();
		} catch(Exception e){
			this.alertMessage("Invalid ISBN and / or Member Id, Press the CLEAR button to reset the field");
			e.printStackTrace();
		}
		
	}

	@FXML
	public void searchBookByISBN() throws IOException, ClassNotFoundException {

		String ISBN = this.txtISBN.getText();
		Book bookDetails = DataFacade.findBookByISBN(ISBN);
		try {
			if (bookDetails.equals(null)) {
				isbnValid = false;
				alertMessage("ISBN entered is not registered!! Enter a valid one.");
			} else {
				isbnValid = true;
				String dueDate = dueDateCalculator(bookDetails);
				System.out.println(dueDate);
				txtdueDate.setText(dueDate);
				coBookTitle.setText(bookDetails.getTitle());
				coBookAuth.setText(bookDetails.getISBN());
				
				coBookCopyCount.setText(Integer.toString(getCopyBookCount(ISBN)));
				coBookAvailability.setText(Boolean.toString(bookDetails.isAvailability()));
			}
		} catch (Exception e) {
			alertMessage("Invalid ISBN entered!! Enter a valid one.");
		}
	}
	
	public static int getCopyBookCount(String ISBN) throws ClassNotFoundException, IOException{
		List<CopyBook> copies = DataFacade.getBookCopiesByISBN(ISBN);
		int count = 0;
		for(CopyBook copy: copies){
			if(copy.isAvailability()){
				count++;
			}
		}
		return count;
	}

	public String dueDateCalculator(Book book) {
		Calendar now = Calendar.getInstance();
		if (book.isAvailability() == true) {
			if (book.borrowDuration == 21) {
				now.add(Calendar.DATE, 21);
			} else if (book.borrowDuration == 7) {
				now.add(Calendar.DATE, 7);
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String date = sdf.format(now.getTime());
		System.out.println(date);
		sdf = new SimpleDateFormat("MM/dd/yyyy");
		date = sdf.format(now.getTime());
		return date;
	}

	private void alertMessage(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION, msg, ButtonType.OK);
		alert.showAndWait();
	}

	private void addCheckoutEntry() throws ClassNotFoundException {
		try {
			List<CheckoutRecord> entries = DataFacade.getAllCheckoutRecords();
			
			String dueDate = txtdueDate.getText();
			String memberId = txtMemberId.getText();
			String bookISBN = txtISBN.getText();
			String bookTittle = coBookTitle.getText();
			Date currentDate = new Date();
			
			CopyBook copyBook = updateCopyBookAvailability(bookISBN);
			
			CheckoutRecord chekoutentry = new CheckoutRecord(dueDate, memberId, bookISBN, bookTittle, currentDate, copyBook);
			FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_DIR_CheckoutList);
			ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
			
			entries.add(chekoutentry);
			output.writeObject(entries);
			output.close();
			
			this.alertMessage("Checkout Successfully processed!! Thanks");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("File empty");
			this.addSingleCheckoutEntry();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			clearCheckoutForm();
		}
	}
	
	public CopyBook updateCopyBookAvailability(String ISBN) throws ClassNotFoundException, IOException{
		List<CopyBook> copies = DataFacade.getBookCopiesByISBN(ISBN);
		
		for(CopyBook copy : copies){
			if(copy.isAvailability()){
				copy.setAvailability(false);
				return copy;
			}
		}
		return null;
	}
	
	private void addSingleCheckoutEntry() throws ClassNotFoundException{
		try {
			
			String dueDate = txtdueDate.getText();
			String memberId = txtMemberId.getText();
			String bookISBN = txtISBN.getText();
			String bookTittle = coBookTitle.getText();
			Date currentDate = new Date();
			
			CopyBook copyBook = updateCopyBookAvailability(bookISBN);
			
			CheckoutRecord chekoutentry = new CheckoutRecord(dueDate, memberId, bookISBN, bookTittle, currentDate, copyBook);

			FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_DIR_CheckoutList);
			ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
			List<CheckoutRecord> entries = new ArrayList<CheckoutRecord>();
			entries.add(chekoutentry);
			output.writeObject(entries);
			output.close();
			
			this.alertMessage("Checkout processed Successfuli, Thanks");
			
			////Clear the input fields for checkout

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void bookCheckoutHandler() throws ParseException, ClassNotFoundException {
		if (this.isbnValid && this.memberValid) {
			this.addCheckoutEntry();
		} else {
			this.alertMessage("Invalid Member Id and / or ISBN provided");
		}
	}
	
	@FXML
	public void cancelHandler() {
		clearCheckoutForm();
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			Pane root = (Pane) FXMLLoader.load(getClass().getResource("LibrarianTabbed.fxml"));
			Scene scene = new Scene(root, 705, 529);
			scene.getStylesheets().add(getClass().getResource("Librarian.css").toExternalForm());

			stage.setTitle("Librarian Window");

			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadAdminWindow() {
		Pane root;
		try {
			root = (Pane) FXMLLoader.load(SuperAdminController.class.getResource("LibrarianTabbed.fxml"));
			Scene adminScene = new Scene(root);
			Stage newStage = new Stage();
			newStage.setScene(adminScene);
			newStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clearCheckoutForm() {
		txtMemberId.setText("");
		txtISBN.setText("");
		txtdueDate.setText("");
		coBookTitle.setText("");
		coBookAuth.setText("");
		coBookAvailability.setText("");
		coBookCopyCount.setText("");
	}
}
