import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class AddStudent implements Initializable{
	
	@FXML
	private ChoiceBox<String> facultate; 
	@FXML
	private TextField numeStud;
	@FXML
	private TextField prenumeStud;
	@FXML
	private  DatePicker dataNasteriiStud;
	@FXML
	private TextField varstaStud;
	@FXML
	private TextField anStud;
	@FXML
	private TextField grupaStud;
	@FXML
	private Button addStud;
	
	

	

	
	private String[] facult = {"Facultatea de Drept","Facultatea de Fizică","Facultatea de Matematica și Informatică","Facultatea de Muzică și Teatru"};
	//private String[] spec = {"Informatica Romana","Informatica Aplicata","Informatica engleza"};
	
	
	
	
	@FXML
	void back(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("AdminMain.fxml");
	}
	
	
	
	public static Student creareStudent(String nume, String prenume, String dataNasterii, 
			int varsta, String facultate,int an, int grupa) {
		 nume = nume.substring(0,1).toUpperCase() + nume.substring(1).toLowerCase();
		 prenume = prenume.substring(0,1).toUpperCase() + prenume.substring(1).toLowerCase();
		 String statut = "student";
	
		 Student s1 = new Student(nume,prenume,dataNasterii,statut,varsta,facultate,an,grupa);
		 Persoana p = new Persoana(nume,prenume,dataNasterii,statut,varsta);
		 s1.makeEmail();
		
	     Map<String,List<Integer>> carnet = new HashMap<>();
	    // s1.setNote(carnet);
		 
		 
		return s1;
		
	}
	
	public void adaugS( ActionEvent event)throws IOException{
		Main m  =new Main();
		String numeS, prenumeS,dataNasteriiS,facultateS,specializareS;
		 int  varstaS, anS, grupaS;
		 
		 if(numeStud.getText().isEmpty() ||  prenumeStud.getText().isEmpty() || varstaStud.getText().isEmpty() || facultate.getValue().isEmpty()) {
				

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("ERROR");
				alert.setContentText("Nu ai completat toate campurile!");
				alert.setHeaderText("Ok");
				alert.showAndWait();	
				return;
			
			}
		 
		 LocalDate data = dataNasteriiStud.getValue();
		 
		 facultateS= facultate.getValue();
		// specializareS = specializare.getValue();

		 numeS  = numeStud.getText().toString();
		 prenumeS  = prenumeStud.getText().toString();
		 dataNasteriiS = data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString();
		 varstaS  = Integer.parseInt(varstaStud.getText());
		 anS = Integer.parseInt(anStud.getText());
		grupaS = Integer.parseInt(grupaStud.getText());
		
		addStudentToDatabase( numeS,prenumeS,dataNasteriiS, varstaS,facultateS,anS, grupaS);
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Adaugat cu succes");
		alert.setContentText("Studentul a fost adaugat cu succes!");
		alert.setHeaderText("Succes");
		alert.showAndWait();	
		m.changeScene("AdminMain.fxml");
	}
	
	 private void addStudentToDatabase(String numeS, String prenumeS, String dataNasteriiS , 
			 int varstaS, String facultateS, int anS, int grupaS) {
		
	        Student s = creareStudent(numeS,prenumeS,dataNasteriiS,varstaS,facultateS,anS,grupaS);
	        
	        Random random = new Random();
	        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	        StringBuilder randomString = new StringBuilder();
	        for (int i = 0; i < 5; i++) {
	            char randomChar = characters.charAt(random.nextInt(characters.length()));
	            randomString.append(randomChar);
	        }
	        s.setParola(randomString.toString());
	       
	        
	        new SendEmail(s.getPrenume(),"Bine ati venit la Universitate! Parola ta pentru autentificare este: "+randomString.toString()+"."
	        		+ " Aceasta se poate schimba in interiroul aplicatiei!",s.getEmail(),0);
	        
	        final String DB_URL = "jdbc:mysql://localhost/db_proiect?serverTimezone=UTC";
	        final String USERNAME = "root";
	        final String PASSWORD = "";

	        try{
	            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
	            // Connected to database successfully...

	            Statement stmt = conn.createStatement();
	            String sql = "INSERT INTO studenti (nume, prenume, varsta, dataNasterii, anStudiu, email, parola, grupa, facultate, statut) " +
	                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            
	            PreparedStatement preparedStatement = conn.prepareStatement(sql);
	            preparedStatement.setString(1, s.getNume());
	            preparedStatement.setString(2, s.getPrenume());
	            preparedStatement.setInt(3,  s.getVarsta());
	            preparedStatement.setString(4, s.getDataNasterii());
	            preparedStatement.setInt(5, s.getAnStudiu());
	            preparedStatement.setString(6, s.getEmail());
	            preparedStatement.setString(7, s.getParola());
	            preparedStatement.setInt(8, s.getGrupa());
	            preparedStatement.setString(9, s.getFacultate());
	            preparedStatement.setString(10, s.getStatut());
	            
	            
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
		
		 facultate.getItems().addAll(facult);
			dataNasteriiStud.setDayCellFactory(new Callback<DatePicker, DateCell>() {
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

	}







	

}
