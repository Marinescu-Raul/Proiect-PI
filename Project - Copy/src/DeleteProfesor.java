import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

public class DeleteProfesor implements Initializable{
	
	@FXML
	private TableView<Profesor> tableView;
	
	@FXML
	private TableColumn<Profesor, Integer> ID;
	
	@FXML
	private TableColumn<Profesor, String> nume;
	
	@FXML
	private TableColumn<Profesor, String> prenume;
	
	@FXML
	private TableColumn<Profesor, String> dataNasterii;
	
	@FXML
	private TableColumn<Profesor, Integer> varsta;
	
	@FXML
	private TableColumn<Profesor, String> facultate;
	
	@FXML
	private Button stergere;
	
	@FXML
	private Button filtrare;
	
	@FXML
	private TextField fil;
	
	int ok=0;
	
	 
	// ObservableList<Profesor> listM;
	
	@FXML
	void back(ActionEvent event) throws IOException {
		
		Main m = new Main();
		m.changeScene("AdminMain.fxml");
		
	}
	
	@FXML
	void removeProf(ActionEvent event) throws SQLException {
		Profesor p = tableView.getSelectionModel().getSelectedItem();
		 final String DB_URL = "jdbc:mysql://localhost/db_proiect?serverTimezone=UTC";
	        final String USERNAME = "root";
	        final String PASSWORD = "";
	        int ok=0;
	        try{
	            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
	            // Connected to database successfully...
	            Statement stmt = conn.createStatement();
	            String sql = "delete from profesori where id = ?";
	            PreparedStatement preparedStatement = conn.prepareStatement(sql);
	            preparedStatement.setInt(1, p.getId());
	            preparedStatement.executeUpdate();
	            stmt.close();
	            conn.close();

	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        
	        
	        populateTableView();
	        
	    }
		
	
	public static Profesor creareProfesor(String nume, String prenume, String dataNasterii,int varsta,String facultate) {
		
		 nume = nume.substring(0,1).toUpperCase() + nume.substring(1).toLowerCase();
		 prenume = prenume.substring(0,1).toUpperCase() + prenume.substring(1).toLowerCase();
		 String statut = "profesor";
		 Profesor p = new Profesor(nume,prenume,dataNasterii,statut,varsta,facultate);
		 p.makeEmail();
		
		
		return p;
	}
	
	
	@FXML
	private void search(ActionEvent event)  throws SQLException{
		
		
		if(ok==0)
		{
		 ObservableList<Profesor> list = FXCollections.observableArrayList();
		 Connection con  = DriverManager.getConnection("jdbc:mysql://localhost/db_proiect","root","");
		 Statement stmt = con.createStatement();
		 String patern = fil.getText().toString();
		 ResultSet rs = stmt.executeQuery("select * from profesori ");
		 try {
	
			while(rs.next()) {
				Profesor p = creareProfesor(rs.getString(2),rs.getString(3),rs.getString(4),
						Integer.parseInt(rs.getString(5)),rs.getString(7));
				p.setId(rs.getInt(1));
				if(p.getNume().contains(patern) || p.getPrenume().contains(patern))
				list.add(p);
				
			}
			ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
			nume.setCellValueFactory(new PropertyValueFactory<>("nume"));
			prenume.setCellValueFactory(new PropertyValueFactory<>("prenume"));
			dataNasterii.setCellValueFactory(new PropertyValueFactory<>("dataNasterii"));
			varsta.setCellValueFactory(new PropertyValueFactory<>("varsta"));
			facultate.setCellValueFactory(new PropertyValueFactory<>("facultate"));
			
			tableView.setItems(list);
		
	    }	
	 catch (SQLException e) {
		// TODO Auto-generated catch block
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
	
	private void populateTableView() throws SQLException {
		
		 ObservableList<Profesor> list = FXCollections.observableArrayList();
		 Connection con  = DriverManager.getConnection("jdbc:mysql://localhost/db_proiect","root","");
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery("select * from profesori");
		 try {
	
			while(rs.next()) {
				Profesor p = creareProfesor(rs.getString(2),rs.getString(3),rs.getString(4),
						Integer.parseInt(rs.getString(5)),rs.getString(7));
				p.setId(rs.getInt(1));
				
				list.add(p);
				
			}
			
			ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
			nume.setCellValueFactory(new PropertyValueFactory<>("nume"));
			prenume.setCellValueFactory(new PropertyValueFactory<>("prenume"));
			dataNasterii.setCellValueFactory(new PropertyValueFactory<>("dataNasterii"));
			varsta.setCellValueFactory(new PropertyValueFactory<>("varsta"));
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

	
	
