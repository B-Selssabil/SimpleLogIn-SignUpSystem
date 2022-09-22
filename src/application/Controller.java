package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
	
	@FXML
	Button btn1;
	
	DBUtilities db = new DBUtilities();
	
	public void handleBtn1() throws IOException {
		
		db.changeScene("Sign Up", "SingUp.fxml", btn1);
		
	}
	
	

}
