import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class StudentMain  implements Initializable{
	
	@FXML
	private Button logOut;
	
	@FXML
	private TableView<Tema> tableViewTema;
	
	@FXML
	private TableColumn<Tema, String> nume;
	
	
	@FXML
	private TableView<Examen> tableViewExamen;
	
	@FXML
	private TableColumn<Examen, String> numeE;
	
	@FXML
	private Button vizNote;
	
	
	@FXML
	private Button incarcareTema;	
	
	@FXML
	private Button schimbaParola;
	
	@FXML
	private Button trmmail;
	
	
	@FXML
	void trmEmail (ActionEvent event) throws  IOException {
		//!!!!
	}
	
	@FXML
	void incTema (ActionEvent event) throws SQLException {
		Dialog dialog = new Dialog();
        dialog.setTitle("Revolvare Tema");
      // dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
      dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
      dialog.getDialogPane().setContent(creareDialogVT());
      if(creareDialogVT()!=null)
         dialog.showAndWait();
          
	}
	
	@FXML
	private Node creareDialogVT() throws SQLException {
		 Tema t = tableViewTema.getSelectionModel().getSelectedItem();
		 if(t == null)
		 {

		 	Alert alert = new Alert(Alert.AlertType.INFORMATION);
 	 		alert.setTitle("ERROR");
 	 		alert.setContentText("Nu ai selectat nici o tema pentru incarcare!");
 	 		alert.setHeaderText("ERROR");
 	 		alert.showAndWait();	
 	 		return null;
		 }
		 else {
		GridPane gp = new GridPane();
		Button ok = new Button("Preda");
		 gp.setAlignment(Pos.BOTTOM_CENTER);
	        gp.setHgap(10);
	        gp.setVgap(10);
	        gp.add(new Label("Numele Temei :"), 0, 0);
	 
	        gp.add(new Label(t.getNume()), 1, 0);
	        gp.add(new Label("Tema :"), 0, 1);
	        TextArea area = new TextArea();
	        area.setEditable(false);
	        area.setPrefColumnCount(20);
	        TextArea area1 = new TextArea();
	        area1.setPrefColumnCount(20);
	        area.setText(t.getEnunt());
	        gp.add(area, 1, 1);
	        gp.add(new Label("Rezolvare :"), 0, 2);
	        gp.add(area1, 1, 2);
	        gp.add(new Label(""), 0, 3);
	        gp.add(ok, 0, 4);
	        
	        ok.setOnAction( x ->{
	        
				try {
					final String DB_URL = "jdbc:mysql://localhost/db_proiect?serverTimezone=UTC";
			        final String USERNAME = "root";
			        final String PASSWORD = "";
		            Connection conn;
					conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
	                 Statement stmt = conn.createStatement();         
		 		      String sql = "UPDATE teme set rezolvare='"+area1.getText()+"', predata='"+1+"' where enunt='"+area.getText()+"'AND "
	                 + "ids='"+Global.sGlob.getId()+"'";
		 	
		 	            PreparedStatement preparedStatement = conn.prepareStatement(sql);
		 	            int addedRows = preparedStatement.executeUpdate();
		 	            stmt.close();
		 	            conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ok.getScene().getWindow().hide();
				try {
					populateTableViewT();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	 			 
	        });
	 
			 return gp;
		 }
		 
		
	}
	
	
	@FXML
	void viznote(ActionEvent event) throws SQLException {
		Dialog dialog = new Dialog();
        dialog.setTitle("Vizualizare note");
     // dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
      dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
      dialog.getDialogPane().setContent(creareDialogN());
      dialog.setWidth(400);
      dialog.setHeight(300);
      
      dialog.showAndWait();
          
		
     
	}
	
	@FXML
	private Node creareDialogN() throws SQLException {
		GridPane gp = new GridPane();
		 gp.setAlignment(Pos.BOTTOM_CENTER);
	        gp.setHgap(10);
	        gp.setVgap(10);
		double suma=0;
		ObservableList<Integer> list = FXCollections.observableArrayList();
		 Connection con  = DriverManager.getConnection("jdbc:mysql://localhost/db_proiect","root","");
		 Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery("select idn, nota from note ");
		 try {
				
				while(rs.next()) {
					if(Integer.parseInt(rs.getString(1)) == Global.sGlob.getId())
						list.add(Integer.parseInt(rs.getString(2)));
				}
				
				for(int i: list) {
					suma+=i;
				}
				
				if(suma!=0)
				suma = suma/list.size();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		if(list.isEmpty()) {
			gp.add(new Label("NU AVETI NOTE!"),0,0);
		}
		else
		{
		gp.add(new Label("Notele tale sunt : "),0,0);
		
		String sl1 = list.stream().map(Object::toString).collect(Collectors.joining(", "));
		Label l1 = new Label(sl1);
		gp.add(l1, 1, 0);
		gp.add(new Label("Media ta este : "),0,1);
		String sl2 =  Double.toString(suma);
		Label l2 = new Label(sl2);
		gp.add(l2, 1, 1);
		
		}

		
		return gp;
	}
	
	@FXML
	void schimbaP(ActionEvent event) throws SQLException {
		
		Dialog dialog = new Dialog();
        dialog.setTitle("Schimba parola");
       // dialog.getDialogPane().setPrefWidth(200);
       // dialog.getDialogPane().setPrefHeight(130);
      dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        //dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().setContent(creareDialogP());
        
        dialog.showAndWait();
		
	}
	
	@FXML
	private Node creareDialogP(){
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.BOTTOM_CENTER);
        gp.setHgap(10);
        gp.setVgap(10);
		PasswordField noua = new PasswordField();
		PasswordField vechie =  new PasswordField();
		Button ok = new Button("Schimba");
		gp.add(new Label("Vechia parola : "),0,0);
		gp.add(vechie ,1,0);
		gp.add(new Label("Noua parola : "),0,1);
		gp.add(noua,1,1);
		gp.add(new Label(""),0,2);
		gp.add(new Label(""),0,3);
		gp.add(new Label(""),0,4);
		gp.add(ok,0,5);
		ok.setOnAction( x ->{
			String nouaP = noua.getText().toString();
		    String vechiaP = vechie.getText().toString();
		    if(Global.sGlob.getParola().equals(vechiaP))
		    {
		    	Global.sGlob.setParola(vechiaP,nouaP);
			try {
				final String DB_URL = "jdbc:mysql://localhost/db_proiect?serverTimezone=UTC";
		        final String USERNAME = "root";
		        final String PASSWORD = "";
	            Connection conn;
				conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                 Statement stmt = conn.createStatement();         
	            
	           
	            String sql = "UPDATE studenti set parola='"+nouaP+"' where id='"+Global.sGlob.getId()+"'";
	            PreparedStatement preparedStatement = conn.prepareStatement(sql);
	            int addedRows = preparedStatement.executeUpdate();
	            stmt.close();
	            conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			}
	     else	
	        {
	        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("ERROR");
				if(noua.getText().isEmpty() || vechie.getText().isEmpty())
				     alert.setContentText("Nu ai completat toate campurile!");
				else
					alert.setContentText("Parola nu este buna!");
				alert.setHeaderText("EROARE");
				alert.showAndWait();	
				return;
	        }
		    ok.getScene().getWindow().hide();
	        	
		});
		
		return gp;
		
		
	}

	
	
	
	
	@FXML
	void logout(ActionEvent event) throws  IOException {
		 Main m =new Main();
		 m.changeScene("Login.fxml");
	}
	
	
	
	
	private void populateTableViewT() throws SQLException {
		
		 ObservableList<Tema> list = FXCollections.observableArrayList();
		 Connection con  = DriverManager.getConnection("jdbc:mysql://localhost/db_proiect","root","");
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery("SELECT * from teme");
		 try {
				
				while(rs.next()) {
					Tema t = new Tema(rs.getString(2),rs.getString(3),Integer.parseInt(rs.getString(4)),
							Integer.parseInt(rs.getString(5)),rs.getString(6),rs.getString(7),Integer.parseInt(rs.getString(8))
							,Integer.parseInt(rs.getString(9)),Integer.parseInt(rs.getString(10)));
					t.setId(Integer.parseInt(rs.getString(1)));
					
					if(t.getPredata()==0 && t.getIds()==Global.sGlob.getId())
					     	{
					           list.add(t);
					     	}
				}
		
				 
				 nume.setCellValueFactory(new PropertyValueFactory<>("nume"));
				tableViewTema.setItems(list);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tableViewTema.setOnMouseClicked(event1 -> {
		    if (event1.getClickCount() == 2) {
		        tableViewTema.getSelectionModel().clearSelection();
		    }
		    
			tableViewExamen.setOnMouseClicked(event2 -> {
			    if (event2.getClickCount() == 2) {
			        tableViewExamen.getSelectionModel().clearSelection();
			    }
			});
		});
		try {
			populateTableViewT();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
