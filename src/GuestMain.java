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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class GuestMain implements Initializable{

	@FXML
	private ListView<Student> studlist;
	
	@FXML
	private ListView<Profesor> proflist;
	
	@FXML
	private Label studen;
	
	@FXML
	private Label profes;
	
	@FXML
	private Button bck;
	
	
	
	@FXML
	void inapoi() throws IOException {
		 Main m =new Main();
		 m.changeScene("Login.fxml");
	}
	
	
	public static Student creareStudent(String nume, String prenume, String dataNasterii, 
			int varsta, String facultate,int an, int grupa) {
		 nume = nume.substring(0,1).toUpperCase() + nume.substring(1).toLowerCase();
		 prenume = prenume.substring(0,1).toUpperCase() + prenume.substring(1).toLowerCase();
		 String statut = "student";
	
		 Student s1 = new Student(nume,prenume,dataNasterii,statut,varsta,facultate,an,grupa);
		 s1.makeEmail();
		
	     Map<String,List<Integer>> carnet = new HashMap<>();
	   
		 
		 
		return s1;
		
	}
	
	public static Profesor creareProfesor(String nume, String prenume, String dataNasterii,int varsta,String facultate) {
		
		 nume = nume.substring(0,1).toUpperCase() + nume.substring(1).toLowerCase();
		 prenume = prenume.substring(0,1).toUpperCase() + prenume.substring(1).toLowerCase();
		 String statut = "profesor";
		 Profesor p = new Profesor(nume,prenume,dataNasterii,statut,varsta,facultate);
		 p.makeEmail();
		
		
		return p;
	}
	
	void populateListView() throws SQLException {
		 ObservableList<Student> stud = FXCollections.observableArrayList();
		 ObservableList<Profesor> prof = FXCollections.observableArrayList();
		 try {
			 Connection con  = DriverManager.getConnection("jdbc:mysql://localhost/db_proiect","root","");
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery("select * from studenti");
	
			while(rs.next()) {
				
				Student s = creareStudent(rs.getString(2),rs.getString(3),rs.getString(5),
						Integer.parseInt(rs.getString(4)),rs.getString(10),
						Integer.parseInt(rs.getString(6)),Integer.parseInt(rs.getString(9)));
				 s.setId(rs.getInt(1));
			     stud.add(s);
				
			}
			 stmt.close();
	         con.close();
		 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 
		 try {
			 Connection con  = DriverManager.getConnection("jdbc:mysql://localhost/db_proiect","root","");
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery("select * from profesori");
	
			while(rs.next()) {
				
				Profesor p = creareProfesor(rs.getString(2),rs.getString(3),rs.getString(4),
						Integer.parseInt(rs.getString(5)),rs.getString(7));
				 p.setId(rs.getInt(1));
			     prof.add(p);
			}
			 stmt.close();
	         con.close();
			
		 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
         
           studlist.setCellFactory(new Callback<ListView<Student>, ListCell<Student>>() {
			     @Override
			     public ListCell<Student> call(ListView<Student> studlist) {
			         return new ListCell<Student>() {
			             protected void updateItem(Student item, boolean empty) {
			                 super.updateItem(item, empty);

			                 if (item != null) {
			                	   	
			                	 setText(item.getNume()+" "+item.getPrenume()+" - "+item.getFacultate());
			                 } else {
			                	 setText(null);
			                 }
			             }
			         };
			     }
			 });
			
					
			
           proflist.setCellFactory(new Callback<ListView<Profesor>, ListCell<Profesor>>() {
			     @Override
			     public ListCell<Profesor> call(ListView<Profesor> proflist) {
			         return new ListCell<Profesor>() {
			             @Override
			             protected void updateItem(Profesor item, boolean empty) {
			                 super.updateItem(item, empty);

			                 if (item != null) {
			                	   	
			                	 setText(item.getNume()+" "+item.getPrenume()+" - "+item.getFacultate());
			                 } else {
			                	 setText(null);
			                 }
			             }
			         };
			     }
			 });
        
         studlist.getItems().addAll(stud);
  		 proflist.getItems().addAll(prof);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			populateListView();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
