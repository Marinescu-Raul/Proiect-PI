import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class AddProfesor implements Initializable{
	
	@FXML
	private TextField numeProf;
	@FXML
	private TextField prenumeProf;
	@FXML
	private TextField vartsaProf;
	@FXML
	private  DatePicker dataNasteriiProf;
	@FXML
	private Button addProf;
	@FXML
	private Button back;
	@FXML
	private Label fil;
	@FXML
	ChoiceBox<String> facultate;
	
	
	private String[] facult = {"Facultatea de Drept","Facultatea de Fizică","Facultatea de Matematica și Informatică","Facultatea de Muzică și Teatru"};
	
	
	public static Profesor creareProfesor(String nume, String prenume, String dataNasterii,int varsta,String facultate) {
		
		 nume = nume.substring(0,1).toUpperCase() + nume.substring(1).toLowerCase();
		 prenume = prenume.substring(0,1).toUpperCase() + prenume.substring(1).toLowerCase();
		 String statut = "profesor";
		 Profesor p = new Profesor(nume,prenume,dataNasterii,statut,varsta,facultate);
		 p.makeEmail();
		
		
		return p;
	}
	
	@FXML
	void search(ActionEvent event) {
		
		
		
	}
	
	public void inapoiLaMain( ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeScene("AdminMain.fxml");
	}
	

	public void adaugP( ActionEvent event)throws IOException{
		Main m = new Main();
		String numeP, prenumeP,dataNasteriiP,facultateP;
		int varstaP;
		if(numeProf.getText().isEmpty() ||  prenumeProf.getText().isEmpty() || vartsaProf.getText().isEmpty() || facultate.getValue().isEmpty()) {
			

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setContentText("Nu ai completat toate campurile!");
			alert.setHeaderText("Ok");
			alert.showAndWait();	
			return;
		
		}
		
         LocalDate data = dataNasteriiProf.getValue();
		 
		 facultateP= facultate.getValue();
		 
		 numeP  = numeProf.getText().toString();
		 prenumeP  = prenumeProf.getText().toString();
		 dataNasteriiP = data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString();
		 varstaP  = Integer.parseInt(vartsaProf.getText());
		 
		 addProfesorToDatabase(numeP,prenumeP,dataNasteriiP,varstaP,facultateP);
		
		
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Adaugat cu succes");
		alert.setContentText("Profesorul a fost adaugat cu succes!");
		alert.setHeaderText("Succes");
		alert.showAndWait();	
		m.changeScene("AdminMain.fxml");
	
	}
	
	
	 private void addProfesorToDatabase(String numeS, String prenumeS, String dataNasteriiS , 
			 int varstaS, String facultateS) {
		 
	        Profesor p = creareProfesor(numeS,prenumeS,dataNasteriiS,varstaS,facultateS);
	        final String DB_URL = "jdbc:mysql://localhost/db_proiect?serverTimezone=UTC";
	        final String USERNAME = "root";
	        final String PASSWORD = "";

	        try{
	            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
	            // Connected to database successfully...

	            Statement stmt = conn.createStatement();
	            String sql = "INSERT INTO profesori (nume, prenume, dataNasterii, varsta, email, facultate, statut, parola)" 
	            +"VALUES (?, ?, ?, ?, ?, ?, ?,?)";
	            
	            PreparedStatement preparedStatement = conn.prepareStatement(sql);
	            preparedStatement.setString(1, p.getNume());
	            preparedStatement.setString(2, p.getPrenume());
	            preparedStatement.setString(3, p.getDataNasterii());
	            preparedStatement.setInt(4,  p.getVarsta());
	            preparedStatement.setString(5, p.getEmail());
	            preparedStatement.setString(6, p.getFacultate());
	            preparedStatement.setString(7, p.getStatut());
	            preparedStatement.setString(8, p.getParola());
	            
	            //Insert row into the table
	            int addedRows = preparedStatement.executeUpdate();
	            
	            stmt.close();
	            conn.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }

	       
	    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dataNasteriiProf.setDayCellFactory(new Callback<DatePicker, DateCell>() {
		      @Override
		      public DateCell call(DatePicker param) {
		        return new DateCell() {
		          @Override
		          public void updateItem(LocalDate item, boolean empty) {
		            super.updateItem(item, empty);
		            setDisable(empty || item.isAfter(LocalDate.now()));
		          }
		        };
		      }
		    });
		 facultate.getItems().addAll(facult);
		
	}
	
	
		

	
}
