import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

public class PrevTeme implements Initializable{

	@FXML
	private ListView<Tema> listViewP;
	
	@FXML
	private ListView<Tema> listViewNP;
	
	@FXML
	private Label labelP;
	
	@FXML
	private Label labelNP;
	
	@FXML
	private Button back;
	
	@FXML
	void inapoi() throws IOException {
		 Main m =new Main();
		 m.dimensiuni( 752.5,550);
		 m.changeScene("ProfesorMain.fxml");
	}
	
	void populateListView() throws SQLException {
		ObservableList<Tema> nepredate = FXCollections.observableArrayList();
		ObservableList<Tema> predate = FXCollections.observableArrayList();
		ObservableList<String> numee = FXCollections.observableArrayList();
		ObservableList<Integer> IDS = FXCollections.observableArrayList();
		Connection con  = DriverManager.getConnection("jdbc:mysql://localhost/db_proiect","root","");
		 Statement stmt = con.createStatement();
		 
		 
		 
		 ResultSet rs = stmt.executeQuery("select * from teme WHERE idp='"+Global.pGlob.getId()+"'");
		
		 try {
		
	
			while(rs.next()) {
				Tema t = new Tema(rs.getString(2),rs.getString(3),Integer.parseInt(rs.getString(4)),
						Integer.parseInt(rs.getString(5)),rs.getString(6),rs.getString(7),Integer.parseInt(rs.getString(8))
						,Integer.parseInt(rs.getString(9)),Integer.parseInt(rs.getString(10)));
				t.setId(Integer.parseInt(rs.getString(1)));
				if(t.getPredata()==0)
					nepredate.add(t);
				else
					predate.add(t);
				
			}
		 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		 listViewP.setCellFactory(new Callback<ListView<Tema>, ListCell<Tema>>() {
		     @Override
		     public ListCell<Tema> call(ListView<Tema> param) {
		         return new ListCell<Tema>() {
		             @Override
		             protected void updateItem(Tema item, boolean empty) {
		                 super.updateItem(item, empty);
   
		                 if (item != null) {
							try {
								String nume = null;
							 	 Connection con;
								 con = DriverManager.getConnection("jdbc:mysql://localhost/db_proiect","root","");
								 Statement stmt = con.createStatement();
			                    	 ResultSet rs2 = stmt.executeQuery("select id, nume, prenume from studenti where id='"+item.getIds()+"'");
			                    	 while(rs2.next()) {
			                    		
			                    		 nume = rs2.getString(2)+" "+rs2.getString(3);
			                    	 }
			                    	
			                	         Label label1 = new Label(item.getNume());
				                     Label label2 = new Label(" - ");
				                     Label label3 = new Label(nume);
				                     HBox hBox = new HBox(label1, label2, label3);
				                     setGraphic(hBox);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		            
		                 } else {
		                     setGraphic(null);
		                 }
		             }
		         };
		     }
		 });
		 
		 listViewNP.setCellFactory(new Callback<ListView<Tema>, ListCell<Tema>>() {
		     @Override
		     public ListCell<Tema> call(ListView<Tema> param) {
		         return new ListCell<Tema>() {
		             @Override
		             protected void updateItem(Tema item, boolean empty) {
		                 super.updateItem(item, empty);
   
		                 if (item != null) {
							try {
								String nume = null;
							 	 Connection con;
								 con = DriverManager.getConnection("jdbc:mysql://localhost/db_proiect","root","");
								 Statement stmt = con.createStatement();
			                    	 ResultSet rs2 = stmt.executeQuery("select id, nume, prenume from studenti where id='"+item.getIds()+"'");
			                    	 while(rs2.next()) {
			                    		
			                    		 nume = rs2.getString(2)+" "+rs2.getString(3);
			                    	 }
			               
			                	         Label label1 = new Label(item.getNume());
				                     Label label2 = new Label(" - ");
				                     Label label3 = new Label(nume);
				                     HBox hBox = new HBox(label1, label2, label3);
				                     setGraphic(hBox);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		            
		                 } else {
		                     setGraphic(null);
		                 }
		             }
		         };
		     }
		 });


		 listViewP.getItems().addAll(predate);
		 listViewNP.getItems().addAll(nepredate);
		 
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
