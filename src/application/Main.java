package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

import application.business.*;
import application.dataaccess.*;
import application.ui.*;

public class Main extends Application{

	Stage thisStage;
	
	@FXML
	private TextField username;
	@FXML
	private TextField password;

	/**
	 * This method starts the window to display GUI.
	 * It uses template design pattern to excute differnt commandd and it is part of Application API
	 *
	 * @param primaryStage
	 */
	@Override
	public void start(Stage primaryStage) {
		System.out.println("Testing Action listener");
		try {
			this.thisStage = primaryStage;

// use of factory method to create Concrete UserDetail Objects
			UserDetailsFactory factory =new ConcreteUserDetailsFactory();

			UserDetails userAdmin= factory.createUserDetails("Ayu", "myName", "Admin");
			UserDetails userlib= factory.createUserDetails("Betka", "Betka", "librarian");
			UserDetails usersadmin= factory.createUserDetails("Eshak", "123", "sadmin");

//			UserDetails user = new UserDetails("admin", "123", "admin");
//			UserDetails user2 = new UserDetails("lib", "123", "librarian");
//			UserDetails user3 = new UserDetails("sadmin", "123", "sadmin");
			UserObjectInputOutputStream inputOutput = new UserObjectInputOutputStream();
			
			List<UserDetails> users = new ArrayList<UserDetails>();
			users.add(userAdmin);
			users.add(userlib);
			users.add(usersadmin);


//			users.add(user);
//			users.add(user2);
//			users.add(user3);
			inputOutput.addUser(users);

			Pane root = (Pane) FXMLLoader.load(Main.class.getResource("LoginForm.fxml"));
			Scene scene = new Scene(root, 530, 350);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * handleLoginButtonAction
	 * This is the main function corresponding to Login Button on the corresponding GUI
	 *
	 */

	@FXML
	private void handleLoginButtonAction() {
		
//		String upass = "123";
//		String uname = "admin";
//		String uname = "sadmin";
		String uname = username.getText();
		String upass = password.getText();

		Validator validator = new Validator(uname, upass);
		System.out.println(validator.getName());

 		ChainBuilder chain= new ChainBuilder();
 		chain.builChain();
 		chain.getAuthonticator().validateRequest(validator);


		// Authenticator htos = new AdminValidator();
//		MyAuthenticator  auth= new AdminValidator();
//		MyAuthenticator lib = new LibrarianValidator();
//		MyAuthenticator nullAuth= new NullChecker();
//
//		nullAuth.setNextAuthonticator(lib);
//		lib.setNextAuthonticator(auth);



//		if (uname.trim().length() == 0 || upass.trim().length() == 0) {
//			alertMessage("Fill all the required Fields");
//		} else {
//			UserObjectInputOutputStream inputOutput = new UserObjectInputOutputStream();
//			UserDetails user = inputOutput.getUsers(uname, upass);
//			try{
//				if (user.getUsername().equals(null)) {
//					alertMessage("Invalid Username or Password");
//				} else {
//					switch(user.getRole()){
//						case "admin":
//
//							AdminController sac = new AdminController();
//							sac.loadAdminWindow();
//							break;
//						case "librarian":
//							LibrarianController libc = new LibrarianController();
//							libc.loadAdminWindow();
//							break;
//						case "sadmin":
//							SuperAdminController spac = new SuperAdminController();
//							spac.loadSuperAdminWindow();
//							break;
//						default:
//							break;
//					}
//
//				}
//			}catch(Exception e){
//				e.printStackTrace();
//				alertMessage("======Invalid Username or Password");
//			}
//		}
	}

	private void alertMessage(String msg) {
		Alert alert = new Alert(AlertType.WARNING, msg, ButtonType.OK);
		alert.showAndWait();
	}

	@FXML
	private void handleCancelButtonAction() {
		System.out.println("Testing Action listener");
		Alert alert = new Alert(AlertType.CONFIRMATION,
				"You are about to close this Application. Would you like to close it?", ButtonType.YES, ButtonType.NO);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			Platform.exit();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
