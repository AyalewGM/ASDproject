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

public class Main extends Application {
	
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	
	@Override
	public void start(Stage primaryStage) {

		System.out.println("Testing Action listener");
		try {
			Pane root = (Pane) FXMLLoader.load(Main.class.getResource("LoginForm.fxml"));
			Scene scene = new Scene(root, 530, 350);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleLoginButtonAction() {
		String uname = "test";
		String upass = "123";
		UserDetails userObject = new UserDetails(uname, upass, "admin");
		if (uname.trim().length() == 0 || upass.trim().length() == 0) {
			alertMessage("Fill all the required Fields");
		} else {
			UserObjectInputOutputStream inputOutput = new UserObjectInputOutputStream();
			UserDetails user = inputOutput.getUsers(uname, upass);
			try{
				if (user.getUsername().equals(null)) {
					alertMessage("Invalid Username or Password");
				} else {
					SuperAdminController sac = new SuperAdminController();
					sac.loadSuperAdminWindow();
				}
			}catch(Exception e){
				alertMessage("Invalid Username or Password");
			}
		}
	}
	
	private void alertMessage(String msg){
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
