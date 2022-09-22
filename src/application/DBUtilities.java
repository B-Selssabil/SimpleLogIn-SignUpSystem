package application;


import java.io.IOException;


import java.sql.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DBUtilities {
	

	
	static Connection con  = null ;
	static EncryptingClass t = new EncryptingClass();
	FXMLLoader loader ;
	Parent root ;
	Stage window ;
	
	
	public void changeScene(String title, String file, Button Btn) throws IOException {
		
		loader = new FXMLLoader(getClass().getResource(file));
        root  = loader.load();
        window = (Stage) Btn.getScene().getWindow();
        window.setScene(new Scene (root));
        window.setTitle(title);
			
	}

	
	public void connect() throws ClassNotFoundException, SQLException {
		
		String url = "jdbc:mysql://localhost:3306/projectdatabase";
		String usname = "root";
		String pass = "helloworld19";
		
		try {
		
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, usname, pass);
		
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}catch(ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}	
		
	}
	
	public void register(String username , String txt , String email, Button btn) throws Exception {
		
		String Query = "INSERT INTO users (username, password , email) VALUES (?, ?, ?)";
		String Query2 = "Select password from users  where username = ? or email= ?";
		
		try {
			
			if ((username.isEmpty()) || (txt.isEmpty()) || (email.isEmpty())){

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Warning");
				alert.setContentText("Becarefull . You must fill all your informations ");
				alert.show();
				
			}else {
				
				PreparedStatement ps = con.prepareStatement(Query2);
				ps.setString(1, username);
				ps.setString(2, email);
				ResultSet rs = ps.executeQuery();
				if (rs.isBeforeFirst()) {
					
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Warning");
					alert.setContentText("User aleardy existe !! Change your username or email or both .");
					alert.show();
						
				}else {
					
					PreparedStatement pst =  con.prepareStatement(Query);
					t.initFromStrings("TIGxyU5i1lU/vsV360NdIg==", "MpmJtndhlIyEP5ck");
					String password = t.encrypt(txt);
					pst.setString(1, username);
					pst.setString(2, password);
					pst.setString(3, email);
					
					int r = pst.executeUpdate();
					System.out.println(r + " row/s affected !!");
					this.changeScene("Looged In", "LoggedIn.fxml", btn );
			        LoggedInController cont = loader.getController();
			        cont.LoggedIn(username);
			        pst.close();
				}
				ps.close();
				con.close();
	
				
			
			}
			
			
		}catch(SQLException e) {
			
			e.printStackTrace();
				
		}
		
		
		
	}
	
	public void login(String Usname, String pass, Button b) throws Exception {
		
		String Query = "select password from users where username = ?"  ;
			
		PreparedStatement st = con.prepareStatement(Query);
		st.setString(1, Usname);
  		ResultSet rs = st.executeQuery();
		
        if (!rs.isBeforeFirst()) {
			
			System.out.println("User does not exist in database");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Warning");
			alert.setContentText("User does not exist in database");
			alert.show();
						
		}else {
			    rs.next();
			    String txt = rs.getString(1);
			    System.out.println(txt);
				t.initFromStrings("TIGxyU5i1lU/vsV360NdIg==", "MpmJtndhlIyEP5ck");
			    String password = t.encrypt(pass);
				if (txt.equals(password)){
					
					System.out.println("You are now logged in succsuflly !!");
					this.changeScene("Looged In", "LoggedIn.fxml", b);
			        LoggedInController cont = loader.getController();
			        cont.LoggedIn(Usname);
			        st.close();
			        con.close();
					
				}else {
					
					System.out.println("Wrong Password !!");
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Warning");
					alert.setContentText("Wrong Password !!");
					alert.show();
				}
				
		}

		
		
	}

}
