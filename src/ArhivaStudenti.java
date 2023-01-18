import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ArhivaStudenti implements Initializable{
	
	@FXML
	private TableView<Student> tableView;
	
	
	
	@FXML
	private TableColumn<Student, String> nume;
	
	@FXML
	private TableColumn<Student, String> prenume;

	@FXML
	private TableColumn<Student, String> email;

	@FXML
	private TableColumn<Student, Double> medie;
	
	@FXML
	private TableColumn<Student, String> facultate;
	
	@FXML
	private TableColumn<Student, String> dataNasterii;
	
	
	
	@FXML
	void merginapoi(ActionEvent event) throws  IOException {
		Main m =new Main();
		 m.dimensiuni( 613.5,437);
		 m.changeScene("AdminMain.fxml");
  }
	
	
	
	
	
	
	
	public static Student creareStudent(String nume, String prenume, String dataNasterii, 
			int varsta, String facultate,int an, int grupa) {
		 nume = nume.substring(0,1).toUpperCase() + nume.substring(1).toLowerCase();
		 prenume = prenume.substring(0,1).toUpperCase() + prenume.substring(1).toLowerCase();
		 String statut = "student";
	
		 Student s1 = new Student(nume,prenume,dataNasterii,statut,varsta,facultate,an,grupa);
		 s1.makeEmail();
		
	     Map<String,List<Integer>> carnet = new HashMap<>();
	    // s1.setNote(carnet);
		 
		 
		return s1;
		
	}
	
	private void populateTableView() throws SQLException {
		 ObservableList<Student> list = FXCollections.observableArrayList();
		 Connection con  = DriverManager.getConnection("jdbc:mysql://localhost/db_proiect","root","");
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery("select * from arhivastud");
		 try {
	
			while(rs.next()) {
				
				Student s = creareStudent(rs.getString(2),rs.getString(3),rs.getString(4),
						Integer.parseInt(rs.getString(5)),rs.getString(6),
						Integer.parseInt(rs.getString(7)),Integer.parseInt(rs.getString(8)));
				s.setId(rs.getInt(1));
				s.setEmail(rs.getString(9));
				s.setMedie(Double.parseDouble(rs.getString(8)));
			    list.add(s);
			   
				
			}
			
			/*list.forEach(s->{
				System.out.println(s);
				System.out.println(s.getMedie());
				System.out.println(s.getGrupa());
				System.out.println(s.getEmail());
			});*/
	
			 
			
			 nume.setCellValueFactory(new PropertyValueFactory<>("nume"));
			 prenume.setCellValueFactory(new PropertyValueFactory<>("prenume"));
			 email.setCellValueFactory(new PropertyValueFactory<>("email"));
			 facultate.setCellValueFactory(new PropertyValueFactory<>("facultate"));
		     medie.setCellValueFactory(new PropertyValueFactory<>("medie"));
		     dataNasterii.setCellValueFactory(new PropertyValueFactory<>("dataNasterii"));
			
			
			tableView.setItems(list);  
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			populateTableView();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
