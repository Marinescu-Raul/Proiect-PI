
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {
	
	public Login() {
		
	}
	
	@FXML
	private Button loginB;
	@FXML
	private Button guestLogin;
	@FXML
	private Label wrongLogin;
	@FXML
	private TextField email;
	@FXML
	private PasswordField parola;
	
	
	public void userLogIn(ActionEvent event)throws IOException{
		checkLogin();
	}
	
	public void guestLogIn(ActionEvent event)throws IOException{
		Main m = new Main();
		m.changeScene("GuestMain.fxml");
	}
	
	private void checkLogin()throws IOException{
		Main m = new Main();
		
		if(email.getText().toString().equals("Admin") && parola.getText().toString().equals("0000")) {
			wrongLogin.setText("Success");
			
			m.changeScene("AdminMain.fxml");
		}
		else
			if(email.getText().isEmpty() || parola.getText().isEmpty()) {
				wrongLogin.setText("Inserati datele.");
			}
			else
				wrongLogin.setText(" Emailul sau parola este gresita!");
	}

	
	

}
