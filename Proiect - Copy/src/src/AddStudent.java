

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddStudent {
	
	@FXML
	private ChoiceBox<String> facultate; //FACULTATE
	@FXML
	private ChoiceBox<String> specializare;  //SPECIALIZARE
	@FXML
	private TextField nume;
	@FXML
	private TextField prenume;
	@FXML
	private  DatePicker dataNasterii;
	@FXML
	private TextField vartsa;
	@FXML
	private TextField an;
	@FXML
	private TextField grupa;
	@FXML
	private Button addStud;
	
	private String[] facult = {""};
	private String[] spec = {""};
	
	
	
	
		
	
	public void addStudent( ActionEvent event)throws IOException{
		Main m = new Main();
		String numeS, prenumeS,dataNasteriiS;
		 int  vartsaS, anS, grupaS;
		LocalDate data = dataNasterii.getValue(); 
		 numeS  = nume.getText().toString();
		 prenumeS  = prenume.getText().toString();
		 dataNasteriiS = data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString();
		 vartsaS  = Integer.parseInt(vartsa.getText());
		 anS = Integer.parseInt(an.getText());
		grupaS = Integer.parseInt(grupa.getText());
		
		
		m.changeScene("Login.fxml");
	}

	

}
