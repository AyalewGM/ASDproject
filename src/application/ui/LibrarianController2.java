package application.ui;

import application.Main;
import application.business.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LibrarianController2 extends Application {
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
	private TextField txtMemberId;

	@FXML
	private TextField txtISBN;

	@FXML
	private TextField txtdueDate;

	@FXML
	private Button btnCheckout;

	@FXML
	private Button btnCancel;

	@FXML
	private Librarian librarian;

	// @FXML
	// private Button btnCancel;

	@FXML

	private TableView tableView;
	// @FXML
	// TableView tabView;
	@FXML
	private TableColumn<CheckoutEntries, String> tcmemberId;
	@FXML
	private TableColumn<CheckoutEntries, String> tcbookISSBN;

	@FXML
	private TableColumn<CheckoutEntries, String> tccheckoutDate;
	@FXML
	private TableColumn<CheckoutEntries, String> tcdueDate;

	@FXML
	TableColumn colMemberId;
	@FXML
	TableColumn colBookISBN;
	@FXML
	TableColumn colBookTitle;

	@FXML
	TableColumn colCheckoutDate;

	@FXML
	TableColumn colDueDate;

	@FXML
	Tab tbView;

	private ObservableList<CheckoutEntries> data = FXCollections.observableArrayList();

	public LibrarianController() {

	}

	public boolean bookIdValidator(List<Book> books, String s) {

		for (Book e : books) {
			if (e.getISBN().equals(s)) {
				return true;
			}
		}

		return false;
	}

	public boolean memberIdValidator(List<LibraryMember> lb, String s) {
		// int idInt = Integer.parseInt(s);
		for (LibraryMember e : lb) {
			if (e.getMemberId().equals(s)) {
				return true;
			}
		}

		return false;
	}

	public Book availabiltyChecker(List<Book> book) {

		for (Book e : book) {
			if (e.availability) {
				return e;
			}
		}

		return null;
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

		// Define another Format of date
		sdf = new SimpleDateFormat("MM/dd/yyyy");
		date = sdf.format(now.getTime());

		return date;

	}

	// Look for member
	public LibraryMember memberFinder(List<LibraryMember> lb) {

		// int idInt = Integer.parseInt(txtMemberId.getText());

		for (LibraryMember member : lb) {
			if (member.getMemberId().equals(txtMemberId.getText())) {
				return member;
			}

		}
		return null;
	}

	// Look for member
	public Book bookFinder(List<Book> bk) {
		for (Book book : bk) {
			if (book.getISBN().equals(txtISBN.getText())) {
				return book;
			}

		}
		return null;
	}

	@FXML

	public void checkoutHandler() throws ParseException {

		// BufferedReader br = null;
		// String[] characters = new String[1024];//just an example - you have
		// to initialize it to be big enough to hold all the lines!
		List<Book> books = new ArrayList<>();
		List<LibraryMember> libraryMembers = new ArrayList<>();

		try {

			FileInputStream fileInputStream = new FileInputStream(new File(OUTPUT_DIR_Book));
			ObjectInputStream input = new ObjectInputStream(fileInputStream);

			books = (List<Book>) input.readObject();

			System.out.println(books.get(0).ISBN);
			input.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {

			FileInputStream fileInputStream2 = new FileInputStream(new File(OUTPUT_DIR_Member));
			ObjectInputStream input2 = new ObjectInputStream(fileInputStream2);
			LibraryMember m = (LibraryMember) input2.readObject();
			libraryMembers.add(m);
			System.out.println(m.firstName);
			System.out.println(m.getMemberId());
			input2.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// System.out.println(txtISBN.getText());

		if (bookIdValidator(books, txtISBN.getText()) == false) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Invalid ISBN or the book does not exist in this library");
			alert.setContentText("Please check it and enter it again!");

			alert.showAndWait();
		}

		// System.out.println("The user ID is: " + txtMemberId.getText());
		if (memberIdValidator(libraryMembers, txtMemberId.getText()) == false) {

			Alert alert = new Alert(AlertType.ERROR);

			alert.setTitle("Error Dialog");
			alert.setHeaderText("Invalid Members ID");

			alert.setContentText("Please check it and enter it again!");

			alert.showAndWait();
		}

		if (memberIdValidator(libraryMembers, txtMemberId.getText()) && bookIdValidator(books, txtISBN.getText())
				&& availabiltyChecker(books) != null) {

			CheckoutEntries entries = null;
			try {

				FileInputStream fileInputStream4 = new FileInputStream(new File(OUTPUT_DIR_CheckoutList));
				ObjectInputStream input4 = new ObjectInputStream(fileInputStream4);

				// entries=(List<CheckoutEntries>)input4.readObject();

				entries = (CheckoutEntries) input4.readObject();
				input4.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			try {
				LibraryMember member;

				member = memberFinder(libraryMembers);
				CheckoutRecord checkout = new CheckoutRecord(member);
				// checkout.getRecordMember().getMemberId()
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				Date duedate = df.parse(dueDateCalculator(bookFinder(books)));
				Date currentDate = new Date();
				// System.out.println("I am here ");
				// set the dueDate to the text field
				SimpleDateFormat andf = new SimpleDateFormat("MM/dd/yyyy");

				txtdueDate = new TextField();
				// txtdueDate.setText("Me");
				// txtdueDate.setText(andf.format(duedate));
				txtdueDate.appendText(andf.format(duedate).toString());
				CheckoutEntries chkoutentry = new CheckoutEntries(currentDate, duedate, checkout);
				FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_DIR_CheckoutList);

				ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
				output.writeObject(chkoutentry);
				// output.writeObject(entries);
				output.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// read data for table view
			List<CheckoutEntries> anotherEntries = new ArrayList<CheckoutEntries>();

			try {

				FileInputStream fileInputStream3 = new FileInputStream(new File(OUTPUT_DIR_CheckoutList));
				ObjectInputStream input3 = new ObjectInputStream(fileInputStream3);

				CheckoutEntries chk = (CheckoutEntries) input3.readObject();
				System.out.println(chk.dateOfCheckout);
				System.out.println("Reading is good!");
				anotherEntries.add(chk);
				input3.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			ObservableList data = FXCollections.observableList(anotherEntries);

			colMemberId.setCellValueFactory(new PropertyValueFactory<CheckoutEntries, String>("memberId"));

			colBookISBN.setCellValueFactory(new PropertyValueFactory<CheckoutEntries, String>("ISBN"));
			colBookTitle.setCellValueFactory(new PropertyValueFactory<CheckoutEntries, String>("title"));
			colCheckoutDate.setCellValueFactory(new PropertyValueFactory<CheckoutEntries, String>("CheckoutDate"));
			colDueDate.setCellValueFactory(new PropertyValueFactory<CheckoutEntries, String>("DueDate"));

			tableView.setItems(data);

			tableView.getColumns().addAll(colMemberId, colBookISBN, colBookTitle, colCheckoutDate, colDueDate);
			tbView.setContent(tableView);

		}

	}

	@FXML
	public void cancelHandler() {

		Platform.exit();
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
	
	public void loadAdminWindow(){
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
//
//	public static void main(String[] args) {
//		launch(args);
//	}
}
