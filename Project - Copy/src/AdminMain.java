


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class AdminMain {
	@FXML
	private Button logout;
	@FXML
	private Button addStud;
	@FXML
	private Button addProf;
	@FXML
	private Button deleteStud;
	@FXML
	private Button deteleProf;
	
	
	
	
	public void addStudent(ActionEvent event)throws IOException{
		Main m = new Main();
		m.changeScene("AddStudent.fxml");
	}
	
	public void addProfesor(ActionEvent event)throws IOException{
		Main m = new Main();
		m.changeScene("AddProfesor.fxml");
	}
	
	public void deleteStudent(ActionEvent event)throws IOException{
		Main m = new Main();
		m.changeScene("DeleteStudent.fxml");
	}
	
	public void  deleteProfesor(ActionEvent event)throws IOException{
		Main m = new Main();
		m.changeScene("DeleteProfesor.fxml");
	}
	
	
   public void userLogOut(ActionEvent event)throws IOException{
	   Main m =new Main();
	   m.changeScene("Login.fxml");
   }
   
   
}
