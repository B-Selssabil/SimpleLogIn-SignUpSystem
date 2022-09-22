package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoggedInController {
	
	
	@FXML 
	 Label lb_welcome;
	
	@FXML 
	Button btn4;
	
	DBUtilities db = new DBUtilities();
	
	public void  LoggedIn(String Username) {
		
		lb_welcome.setText("Welcome There " + Username + " , Enjoy Your Time Here ");
				
	}
	
	public void logout(ActionEvent e) throws IOException {
		
		try {
			
			db.changeScene("Tim", "Scene.fxml", btn4);
		
		
		}catch(IOException ei) {
			
			ei.printStackTrace();
			
		}
		
	}
	

}
