package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {
	
	@FXML 
	Button btn2, btn3;
	
	@FXML 
	TextField tf_username , tf_email;
	
	@FXML
	PasswordField tf_password;
	
	DBUtilities db = new DBUtilities();
	
public void LogIn() throws IOException, ClassNotFoundException, SQLException {
	
		
	    db.changeScene("Log In","LogIn.fxml", btn3 );
	
        
	}
	
	public void SignUp()  throws Exception {
		
		String username = tf_username.getText();
		String password = tf_password.getText();
		String email = tf_email.getText();

		
		
        
        db.connect();
        db.register(username, password, email, btn2);
        
        
        		
	}

	


}
