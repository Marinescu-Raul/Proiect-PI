import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DeleteStudent  implements Initializable{
	
	@FXML
	private TableView<Student> tableView;
	
	@FXML
	private TableColumn<Student, Integer> ID;
	
	@FXML
	private TableColumn<Student, String> nume;
	
	@FXML
	private TableColumn<Student, String> prenume;
	
	@FXML
	private TableColumn<Student, Integer> varsta;
	
	@FXML
	private TableColumn<Student, Integer> anStudiu;
	
	@FXML
	private TableColumn<Student, String> email;
	
	@FXML
	private TableColumn<Student, Integer> grupa;
	
	
	@FXML
	private TableColumn<Student, String> facultate;
	
	@FXML
	private Button stergere;
	
	@FXML
	private Button filtrare;
	
	@FXML
	private TextField fil;
	
	int ok=0;
	
	
	@FXML
	void back(ActionEvent event) throws IOException {
		
		Main m = new Main();
		m.changeScene("AdminMain.fxml");
		
	}
	
	@FXML
	void search(ActionEvent event) throws SQLException {
		if(ok==0)
		{
		 ObservableList<Student> list = FXCollections.observableArrayList();
		 Connection con  = DriverManager.getConnection("jdbc:mysql://localhost/db_proiect","root","");
		 Statement stmt = con.createStatement();
		 String patern = fil.getText().toString();
		 ResultSet rs = stmt.executeQuery("select * from studenti");
		 try {
				
				while(rs.next()) {
					Student s = creareStudent(rs.getString(2),rs.getString(3),rs.getString(5),
							Integer.parseInt(rs.getString(4)),rs.getString(10),
							Integer.parseInt(rs.getString(6)),Integer.parseInt(rs.getString(9)));
					s.setId(rs.getInt(1));
					if(s.getNume().contains(patern) || s.getPrenume().contains(patern))
				     	list.add(s);
					
				}
				
				ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
				nume.setCellValueFactory(new PropertyValueFactory<>("nume"));
				prenume.setCellValueFactory(new PropertyValueFactory<>("prenume"));
				varsta.setCellValueFactory(new PropertyValueFactory<>("varsta"));
				anStudiu.setCellValueFactory(new PropertyValueFactory<>("anStudiu"));
				email.setCellValueFactory(new PropertyValueFactory<>("email"));
			     grupa.setCellValueFactory(new PropertyValueFactory<>("grupa"));	
				facultate.setCellValueFactory(new PropertyValueFactory<>("facultate"));
				
				tableView.setItems(list);
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		 ok=1;
		}
		else
		{
			populateTableView();
			ok=0;
			fil.setText("");
		}
	}
	
	@FXML
	void removeStud(ActionEvent event) throws SQLException {
		Student s = tableView.getSelectionModel().getSelectedItem();
		 final String DB_URL = "jdbc:mysql://localhost/db_proiect?serverTimezone=UTC";
	        final String USERNAME = "root";
	        final String PASSWORD = "";
	        int ok=0;

	        try{
	            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
	            // Connected to database successfully...

	            Statement stmt = conn.createStatement();
	            String sql = "INSERT INTO arhivastud (nume, prenume, dataNasterii, varsta, facultate, an, grupa, email, medie) " +
	                    "VALUES (?, ?, ?, ?, ?, ? ,? ,?, ?)";
	            
	            PreparedStatement preparedStatement = conn.prepareStatement(sql);
	            preparedStatement.setString(1, s.getNume());
	            preparedStatement.setString(2, s.getPrenume());
	            preparedStatement.setString(3, s.getDataNasterii());
	            preparedStatement.setInt(4,  s.getVarsta());
	            preparedStatement.setString(5, s.getFacultate());
	            preparedStatement.setInt(6,  s.getAnStudiu());
	            preparedStatement.setInt(7,  s.getGrupa());
	            preparedStatement.setString(8,  s.getEmail());
	            System.out.println(s.getMedie());
	            preparedStatement.setDouble(9, s.getMedie());
	            
	        
	            int addedRows = preparedStatement.executeUpdate();
	            
	            stmt.close();
	            conn.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        
	        
	        
	        try{
	            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
	          
	            Statement stmt = conn.createStatement();
	            String sql = "delete from studenti where id = ?";
	            PreparedStatement preparedStatement = conn.prepareStatement(sql);
	            preparedStatement.setInt(1, s.getId());
	            preparedStatement.executeUpdate();
	            stmt.close();
	            conn.close();

	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        
	        
	        try{
	            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

	            Statement stmt = conn.createStatement();
	            String sql = "delete from teme where ids = ?";
	            PreparedStatement preparedStatement = conn.prepareStatement(sql);
	            preparedStatement.setInt(1, s.getId());
	            preparedStatement.executeUpdate();
	            stmt.close();
	            conn.close();

	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        
	        
	        try{
	            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

	            Statement stmt = conn.createStatement();
	            String sql = "delete from examene where ids = ?";
	            PreparedStatement preparedStatement = conn.prepareStatement(sql);
	            preparedStatement.setInt(1, s.getId());
	            preparedStatement.executeUpdate();
	            stmt.close();
	            conn.close();

	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        
	        try{
	            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

	            Statement stmt = conn.createStatement();
	            String sql = "delete from note where idn = ?";
	            PreparedStatement preparedStatement = conn.prepareStatement(sql);
	            preparedStatement.setInt(1, s.getId());
	            preparedStatement.executeUpdate();
	            stmt.close();
	            conn.close();

	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        
	        
	        populateTableView();
	        
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
		 ResultSet rs = stmt.executeQuery("select * from studenti");
		 try {
	
			while(rs.next()) {
				Student s = creareStudent(rs.getString(2),rs.getString(3),rs.getString(5),
						Integer.parseInt(rs.getString(4)),rs.getString(10),
						Integer.parseInt(rs.getString(6)),Integer.parseInt(rs.getString(9)));
				s.setId(rs.getInt(1));
				list.add(s);
				
			}
			
			ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
			nume.setCellValueFactory(new PropertyValueFactory<>("nume"));
			prenume.setCellValueFactory(new PropertyValueFactory<>("prenume"));
			varsta.setCellValueFactory(new PropertyValueFactory<>("varsta"));
			anStudiu.setCellValueFactory(new PropertyValueFactory<>("anStudiu"));
			email.setCellValueFactory(new PropertyValueFactory<>("email"));
		     grupa.setCellValueFactory(new PropertyValueFactory<>("grupa"));	
			facultate.setCellValueFactory(new PropertyValueFactory<>("facultate"));
			
			tableView.setItems(list);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			ID.setVisible(false);
			populateTableView();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}	
}



