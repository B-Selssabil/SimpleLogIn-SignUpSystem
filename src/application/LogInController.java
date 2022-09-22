package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController {
	
	@FXML 
	Button btn6, btn5;
	
	@FXML 
	TextField tf_username;
	
	@FXML
	PasswordField tf_password;
	
	DBUtilities db = new DBUtilities();
	
public void Sign() throws IOException, ClassNotFoundException, SQLException {
	
	
	 
	   db.changeScene("Sign Up", "SingUp.fxml", btn5);
		       		
	}

public void Log() throws Exception {
	
    
	String user = tf_username.getText();
	String password = tf_password.getText();
	
    System.out.println(user);
    System.out.println(password);


     try {
			db.connect();
			db.login(user, password, btn5);
			
     }catch(Exception e) {
    	 
    	 e.printStackTrace();
    	 
     }
	
  
    
    		
}

}
