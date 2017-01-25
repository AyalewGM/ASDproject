package application.ui;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import application.business.Address;
import application.business.Author;
import application.business.Book;
import application.business.LibraryMember;
import application.business.Person;

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
	private TextField bio;
	
	public static final String OUTPUT_DIR = System.getProperty("user.dir") 
			+ "\\src\\application\\dataaccess\\staffInfo.txt";
	public static final String OUTPUT_DIR2 = System.getProperty("user.dir") 
			+ "\\src\\application\\dataaccess\\bookInfo.txt";
	
	@FXML
	private void handleSubmit(){
		
		
		String fn= fname.getText();
		String ln= lname.getText();
		String pn= phoneNum.getText();
		String sn= streetNum.getText();
		String ct= city.getText();
		String st= state.getText();
		int zp= Integer.parseInt(zip.getText());
		
		try {

			// Store Serialized User Object in File
			FileOutputStream fileOutputStream = new FileOutputStream(
					OUTPUT_DIR);
			Address ad= new Address(sn,ct,st,zp);
			Person p= new LibraryMember(fn,ln,pn,ad,LibraryMember.getMemberId());
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
	public void addBookHandle(){
		
		
		try{
		
		boolean ch,ch2;
	FileOutputStream fileOutputStream = new FileOutputStream(
			OUTPUT_DIR2);
	
		
	if(cb2.getAccessibleText().equals("Available"))
		ch=true;
		else
		ch=false;
		
	if(cb3.getAccessibleText().equals("Accredited"))
		ch2=true;
	else
		ch2=false;
	
Address a2= new Address(streetNum2.getText(),city2.getText(),state2.getText(),Integer.parseInt(zip2.getText()));
	Author a1= new Author (fname2.getText(),lname2.getText(),phoneNum2.getText(),a2,ch2,bio.getText());
			
	    Book bk= new Book(title.getText(),isbn.getText(),Integer.parseInt(cb.getAccessibleText()),ch,a1);
	    
   ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
	output.writeObject(bk);
	output.close();
			} catch (FileNotFoundException e) {
			e.printStackTrace();
	} catch (IOException e) {
			e.printStackTrace();}
		
		System.out.println("handled");
	}
	
	
	
	
	@Override 
	
	public void start(Stage primaryStage){
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/ui/Admin.fxml"));
			Scene scene = new Scene(root,600,500);
			//scene.getStylesheets().add(getClass().getResource("admin.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adminstrator");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main (String[] args){
		
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
	
	

}
