import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ButtonBar;

public class ProfesorMain implements Initializable{
	
	
	

	@FXML
	private Button addTema;
	@FXML
	private Button addNota;
	@FXML
	private Button schimbaParola;
	@FXML
	private Button logOut;
	@FXML
	private Button cauta;
	
	@FXML
	private Button tpred;
	
	@FXML
	private Button trmmail;
	
	@FXML
	private TextField fil;


	@FXML
	private TableView<Student> tableView;
	
	
	@FXML
	private TableColumn<Student, Integer> ID;
	
	@FXML
	private TableColumn<Student, String> nume;
	
	@FXML
	private TableColumn<Student, String> prenume;
	@FXML
	private TableColumn<Student, Integer> anStudiu;
	@FXML
	private TableColumn<Student, String> email;
	@FXML
	private TableColumn<Student, Integer> grupa;
	@FXML
	private TableColumn<Student, Double> medie;
	@FXML
	private TableColumn<Student, String> facultate;
	
	int[] note = {2,3,4,5,6,7,8,9,10};
	
	 String fac = Global.pGlob.getFacultate();
	
	
	
	private final Integer[] arrayNote = {1,2,3,4,5,6,7,8,9,10};
	//private List<Integer> dialogNota;
	
	int ok=0; 
	int nota;
	
	
	

	

	@FXML
	void trmEmail (ActionEvent event) throws  IOException {
		ObservableList<Student> studenti = tableView.getSelectionModel().getSelectedItems();
		if(studenti.isEmpty())
		{
			    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	 	 		alert.setTitle("ERROR");
	 	 		alert.setContentText("Nu ai selectat nici un student!");
	 	 		alert.setHeaderText("ERROR");
	 	 		alert.showAndWait();	
		}
		else
		{
		Dialog dialog = new Dialog();
        dialog.setTitle("Trimite email");
      dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
       // dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().setContent(creareDialogM());
      
            dialog.showAndWait();
            
		}
		
		
	}
	
	@FXML
	private Node creareDialogM() {
		
		GridPane gp = new GridPane();
		Button ok = new Button("Trimite");
		 gp.setAlignment(Pos.BOTTOM_CENTER);
	        gp.setHgap(10);
	        gp.setVgap(10);
	    TextField titlu = new TextField();
	    TextArea continut = new TextArea();
	    continut.setPrefColumnCount(20);
	    gp.add(new Label("Titlul : "),0,0);
		gp.add(titlu, 1, 0);
		gp.add(new Label("Continut : "),0,1);
		gp.add(continut, 1, 1);
		gp.add(ok,0,5);
		ok.setOnAction(x->{
			ObservableList<Student> studenti = tableView.getSelectionModel().getSelectedItems();
			String tit, cont;
			tit = titlu.getText();
			tit = tit+"    (De la "+Global.pGlob.getNume()+" "+Global.pGlob.getPrenume()+")";
			cont = continut.getText();
			for(Student s: studenti) {
				new SendEmail(tit,cont,s.getEmail(),0);
				System.out.println(s.getEmail());
			}
			ok.getScene().getWindow().hide();
		});

	        return gp;
		
	}


	@FXML
	void logout(ActionEvent event) throws  IOException {
		 Main m =new Main();
		 m.dimensiuni( 613.5,437);
		 m.changeScene("Login.fxml");
	}
	
	
	

	@FXML
	void temePred (ActionEvent event) throws  IOException {
		Main m =new Main();
		 m.dimensiuni( 613.5,437);
		 m.changeScene("PrevTeme.fxml");
	}
	
	
	@FXML
	void adaugaT(ActionEvent event) throws SQLException {
		Dialog dialog = new Dialog();
        dialog.setTitle("Adauga tema");
      dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
       // dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().setContent(creareDialogT());
       
            dialog.showAndWait();
          
		
     
	}
	
	@FXML
	private Node creareDialogT() {
	    
		Button ok = new Button("Adauga");
		TextField numele = new TextField();
		TextField grupa = new TextField();
	    TextField an = new TextField();
	    TextArea area = new TextArea();
	    area.setPrefColumnCount(20);
		GridPane gp = new GridPane();
		 gp.setAlignment(Pos.BOTTOM_CENTER);
	        gp.setHgap(10);
	        gp.setVgap(10);
		gp.add(new Label("Numele : "),0,0);
		gp.add(numele, 1, 0);
		gp.add(new Label("Anul : "),0,1);
		gp.add(an, 1, 1);
		gp.add(new Label("Grupa : "),0,2);
		gp.add(grupa, 1, 2);
		gp.add(new Label("Tema : "),0,3);
		gp.add(area, 1, 3);
		gp.add(ok,0,5);
		ok.setOnAction(x->{
	          
			if(numele.getText().isEmpty() || an.getText().isEmpty() || grupa.getText().isEmpty() || area.getText().isEmpty())
			{
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
	 	 		alert.setTitle("ERROR");
	 	 		alert.setContentText("Nu ai introdus toate datele necesare!");
	 	 		alert.setHeaderText("ERROR");
	 	 		alert.showAndWait();	
	 	 		
			}
			else
			{
				String nume,facultate,enunt,rezolvare;
				int anT,grupaT;
				nume = numele.getText();
				rezolvare = "";
				facultate = Global.pGlob.getFacultate();
				enunt = area.getText();
				anT=Integer.parseInt(an.getText());
				grupaT = Integer.parseInt(grupa.getText());
	   
				 final String DB_URL = "jdbc:mysql://localhost/db_proiect?serverTimezone=UTC";
			        final String USERNAME = "root";
			        final String PASSWORD = "";
			        
			        
					 try {
						 ObservableList<Student> list = FXCollections.observableArrayList();
						 Connection con  = DriverManager.getConnection("jdbc:mysql://localhost/db_proiect","root","");
						 Statement stmt = con.createStatement();
		
						 ResultSet rs = stmt.executeQuery("select * from studenti");
							
							while(rs.next()) {
								Student s = creareStudent(rs.getString(2),rs.getString(3),rs.getString(5),
										Integer.parseInt(rs.getString(4)),rs.getString(10),
										Integer.parseInt(rs.getString(6)),Integer.parseInt(rs.getString(9)));
								s.setId(rs.getInt(1));
								
								if(s.getFacultate().equals(facultate) && s.getAnStudiu()==anT && s.getGrupa()==grupaT)
									{
									list.add(s);
									}
							}

				            
                      for(Student s: list)
                      {
                    	  String sql = "INSERT INTO teme (nume, facultate, an, grupa, enunt, rezolvare, predata, idp, ids)" +
				                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				            PreparedStatement preparedStatement = con.prepareStatement(sql);
			            preparedStatement.setString(1, nume);
			            preparedStatement.setString(2, facultate);
			            preparedStatement.setInt(3, anT);
			            preparedStatement.setInt(4, grupaT);
			            preparedStatement.setString(5, enunt);
			            preparedStatement.setString(6, rezolvare);
			            preparedStatement.setInt(7, 0);
			            preparedStatement.setInt(8, Global.pGlob.getId());
			            preparedStatement.setInt(9, s.getId());
			            int addedRows = preparedStatement.executeUpdate();
                      }
			    
			            
			            stmt.close();
			            con.close();
                      
			        }catch(Exception e){
			            e.printStackTrace();
			        }
			        
			        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		 	 		alert.setTitle("Succes");
		 	 		alert.setContentText("Tema a fost adaugata cu succes!");
		 	 		alert.setHeaderText("SUCCES");
		 	 		alert.showAndWait();	
			        
			        
			}
			ok.getScene().getWindow().hide();
			
		});
		
   
		return gp;
	}
	
	
	@FXML
	void adaugaN(ActionEvent event) throws SQLException {
		
		//DialogPane
          Dialog dialog = new Dialog();
          dialog.setTitle("Adauga nota");
          dialog.getDialogPane().setPrefWidth(200);
          dialog.getDialogPane().setPrefHeight(130);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        
          dialog.getDialogPane().setContent(creareDialog());
          if(creareDialog()!=null)
             dialog.showAndWait();
  
         
	}
	
	@FXML
	private Node creareDialog(){
		 Student stud = tableView.getSelectionModel().getSelectedItem(); 
		 if(stud == null)
		 {
		 	 Alert alert = new Alert(Alert.AlertType.INFORMATION);
 	 		alert.setTitle("ERROR");
 	 		alert.setContentText("Nu ai selectat nici un student!");
 	 		alert.setHeaderText("ERROR");
 	 		alert.showAndWait();	
		 }
		 else
		 {
			 
		ChoiceBox<Integer> cb = new ChoiceBox<>();
		 Button ok = new Button("Adauga");
		 Button cl = new Button("Close");
		cb.getItems().addAll(arrayNote);
		GridPane gp = new GridPane();
		 gp.setAlignment(Pos.BOTTOM_CENTER);
	        gp.setHgap(10);
	        gp.setVgap(10);
		gp.add(new Label("Nota : "),0,0);
		gp.add(cb ,1,0);
		gp.add(new Label(""),0,1);
		gp.add(new Label(""),0,2);
		gp.add(new Label(""),0,3);
		gp.add(new Label(""),0,4);
		gp.add(ok,0,5);
		//gp.add(ButtonType.CLOSE,1,5);
		
		cl.setOnAction(s->{
			
		});
		
         ok.setOnAction(s->{
        	     if(cb.getItems().isEmpty())
        	     {
        	       	 Alert alert = new Alert(Alert.AlertType.INFORMATION);
        	 		alert.setTitle("ERROR");
        	 		alert.setContentText("Nu ai selectat nici o nota!");
        	 		alert.setHeaderText("ERROR");
        	 		alert.showAndWait();	
        	 		tableView.getSelectionModel().clearSelection();
        	     }
        	     else
        	     {
			 nota=cb.getValue();
			 final String DB_URL = "jdbc:mysql://localhost/db_proiect?serverTimezone=UTC";
		        final String USERNAME = "root";
		        final String PASSWORD = "";

		        try{
		            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

		            Statement stmt = conn.createStatement();
		            String sql = "INSERT INTO note (idn, nota)" +
		                    "VALUES (?, ?)";
		            
		            PreparedStatement preparedStatement = conn.prepareStatement(sql);
		            preparedStatement.setInt(1, stud.getId());
		            preparedStatement.setInt(2, nota);
		            int addedRows = preparedStatement.executeUpdate();
		            
		            stmt.close();
		            conn.close();
		        }catch(Exception e){
		            e.printStackTrace();
		        }
		        
		        try {
					populateTableView();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	     }
        	     
        	     ok.getScene().getWindow().hide();
		           
		 });
		return gp;
		 }
		 return null;
		
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
		    if(Global.pGlob.getParola().equals(vechiaP))
		    {
		    	Global.pGlob.setParola(vechiaP,nouaP);
			try {
				final String DB_URL = "jdbc:mysql://localhost/db_proiect?serverTimezone=UTC";
		        final String USERNAME = "root";
		        final String PASSWORD = "";
	            Connection conn;
				conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                 Statement stmt = conn.createStatement();         
	            
	           
	            String sql = "UPDATE profesori set parola='"+nouaP+"' where id='"+Global.pGlob.getId()+"'";
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
	void search(ActionEvent event) throws SQLException {
		if(ok==0)
		{
		 ObservableList<Student> list = FXCollections.observableArrayList();
		 Connection con  = DriverManager.getConnection("jdbc:mysql://localhost/db_proiect","root","");
		 Statement stmt = con.createStatement();
		 String patern = fil.getText().toString();
		 ResultSet rs = stmt.executeQuery("select * from studenti ");
		 try {
				
				while(rs.next()) {
					Student s = creareStudent(rs.getString(2),rs.getString(3),rs.getString(5),
							Integer.parseInt(rs.getString(4)),rs.getString(10),
							Integer.parseInt(rs.getString(6)),Integer.parseInt(rs.getString(9)));
					s.setId(rs.getInt(1));
					
					if(s.getFacultate().contains(fac) && (s.getNume().contains(patern) || s.getPrenume().contains(patern)))
					list.add(s);
					
				}
				
				for(Student stud: list) {
					int idd;
					int nota;
					double finala=0;
					int nr=0;
					ResultSet rs1 = stmt.executeQuery("select * from note ");
					while(rs1.next()) {
					 idd=Integer.parseInt(rs1.getString(2));
					 nota = Integer.parseInt(rs1.getString(3));
					if(idd==stud.getId()) {
						finala+=nota;
					     nr++;
					}
					
				}
					if(nr!=0)
						
				     	stud.setMedie((double)finala/nr);
				}
				
				ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
				nume.setCellValueFactory(new PropertyValueFactory<>("nume"));
				prenume.setCellValueFactory(new PropertyValueFactory<>("prenume"));
				email.setCellValueFactory(new PropertyValueFactory<>("email"));
				anStudiu.setCellValueFactory(new PropertyValueFactory<>("anStudiu"));
				 grupa.setCellValueFactory(new PropertyValueFactory<>("grupa"));	
				 medie.setCellValueFactory(new PropertyValueFactory<>("medie"));
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
				if(s.getFacultate().contains(fac))
			     	list.add(s);
				
			}
	
			
			for(Student stud: list) {
				int idd;
				int nota;
				int finala=0;
				int nr=0;
				ResultSet rs1 = stmt.executeQuery("select * from note ");
				while(rs1.next()) {
				 idd=Integer.parseInt(rs1.getString(2));
				 nota = Integer.parseInt(rs1.getString(3));
				if(idd==stud.getId()) {
					finala+=nota;
				     nr++;
				}
				
			}
				if(nr!=0)
			     	stud.setMedie(finala/nr);
			}
			 
	
			 
			 ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
			 nume.setCellValueFactory(new PropertyValueFactory<>("nume"));
			 prenume.setCellValueFactory(new PropertyValueFactory<>("prenume"));
			 email.setCellValueFactory(new PropertyValueFactory<>("email"));
			 anStudiu.setCellValueFactory(new PropertyValueFactory<>("anStudiu"));
		     grupa.setCellValueFactory(new PropertyValueFactory<>("grupa"));	
		     medie.setCellValueFactory(new PropertyValueFactory<>("medie"));
			facultate.setCellValueFactory(new PropertyValueFactory<>("facultate"));
			
			tableView.setItems(list);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableView.setOnMouseClicked(event -> {
		    if (event.getClickCount() == 2) {
		        tableView.getSelectionModel().clearSelection();
		    }
		});
		try {
			ID.setVisible(false);
			populateTableView();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
}
	
}
