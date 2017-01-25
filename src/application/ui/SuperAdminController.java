package application.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SuperAdminController extends Application{
	
	public SuperAdminController(){
	}
	
	public void loadSuperAdminWindow(){
		Pane root;
		try {
			root = (Pane) FXMLLoader.load(SuperAdminController.class.getResource("superAdmin.fxml"));
			Scene superAdminScene = new Scene(root);
			Stage newStage = new Stage();
			newStage.setScene(superAdminScene);
			newStage.show();
			loadUsersTable();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void loadUsersTable(){
		System.out.println("Loading users table");
	}

	@FXML
	private void submitUsersDetails() {
	}
	
	@Override
	public void start(Stage primaryStage){
	}

}
