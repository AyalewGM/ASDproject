package application.ui;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import application.business.Address;
import application.business.LibraryMember;
import application.business.Person;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminController extends Application {
	
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
	
	public static final String OUTPUT_DIR = System.getProperty("user.dir") 
			+ "\\src\\application\\dataaccess\\staffInfo.txt";
	
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

}
