import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class PrevExam implements Initializable{
	
	@FXML
	private Button back;

	
	@FXML
	private ListView<Examen> listViewE;
	
	@FXML
	private ListView<Examen> listViewEN;
	
	private final Integer[] arrayNote = {1,2,3,4,5,6,7,8,9,10};
	
	
	@FXML
	void inapoi() throws IOException {
		 Main m =new Main();
		 m.dimensiuni( 752.5,550);
		 m.changeScene("ProfesorMain.fxml");
	}
	
	
	
	
	void populateListView() throws SQLException {
		listViewE.getItems().clear();
		listViewEN.getItems().clear();
		ObservableList<Examen> exameneP = FXCollections.observableArrayList();
		ObservableList<Examen> exameneNP = FXCollections.observableArrayList();
		Connection con =DbUtil.getConnection();
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery("select * from examene WHERE idp='"+Global.pGlob.getId()+"'");
		
		 try {

			while(rs.next()) {
				Examen e = new Examen(rs.getString(2),Integer.parseInt(rs.getString(3)),
						new java.util.Date(rs.getDate(4).getTime()),rs.getString(5));
				if(rs.getInt(7) == 1)
				       e.setPredata(1);
				e.setRezolvare(rs.getString(6));
				e.setId(rs.getInt(1));    //??? nu salveaza
				e.setIdp(rs.getInt(8));   //??? nu salveaza
				e.setIds(rs.getInt(9));   //??? nu salveaza
				LocalDate currentDate = LocalDate.now();
				
				 ZonedDateTime zonedDateTime = e.getData().toInstant().atZone(ZoneId.systemDefault());
				 LocalDate futureDate = zonedDateTime.toLocalDate(); 
				 Period period = Period.between(currentDate,futureDate);
				 int days = period.getDays();
				 
				if(e.getPredata()==1)
					exameneP.add(e);
				else
				   if(e.getPredata()==0 && days<0)
					exameneNP.add(e);
			}
		 } catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		
		
		 listViewE.setCellFactory(new Callback<ListView<Examen>, ListCell<Examen>>() {
		     @Override
		     public ListCell<Examen> call(ListView<Examen> param) {
		         return new ListCell<Examen>() {
		             @Override
		             protected void updateItem(Examen item, boolean empty) {
		                 super.updateItem(item, empty);
   
		                 if (item != null) {
							try {
								String nume = null;
								Connection con =DbUtil.getConnection();
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
							} catch (SQLException ex) {
								// TODO Auto-generated catch block
								ex.printStackTrace();
							}
		            
		                 } else {
		                     setGraphic(null);
		                 }
		             }
		         };
		     }
		 });
		 
		 listViewEN.setCellFactory(new Callback<ListView<Examen>, ListCell<Examen>>() {
		     @Override
		     public ListCell<Examen> call(ListView<Examen> param) {
		         return new ListCell<Examen>() {
		             @Override
		             protected void updateItem(Examen item, boolean empty) {
		                 super.updateItem(item, empty);
   
		                 if (item != null) {
							try {
								String nume = null;
								Connection con =DbUtil.getConnection();
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
							} catch (SQLException ex) {
								// TODO Auto-generated catch block
								ex.printStackTrace();
							}
		            
		                 } else {
		                     setGraphic(null);
		                 }
		             }
		         };
		     }
		 });


		 listViewE.getItems().addAll(exameneP);
		 listViewEN.getItems().addAll(exameneNP); 
		 
	}
	
	
	@FXML
	private Node creareDialogE(Examen e) {	
		GridPane gp = new GridPane();
		 Button ok = new Button("Adauga");
		ChoiceBox<Integer> cb = new ChoiceBox<>();
		cb.getItems().addAll(arrayNote);
		 gp.setAlignment(Pos.BOTTOM_CENTER);
	        gp.setHgap(10);
	        gp.setVgap(10);
		    TextArea area = new TextArea();
		    area.setEditable(false);
	        area.setPrefColumnCount(20);
	        area.setText(e.getRezolvare());
	        gp.add(new Label("Rezolvarea : "),0,0);
			gp.add(area, 1, 0);
			gp.add(new Label("Atribuie nota : "),0,1);
			gp.add(cb, 1, 1);
			gp.add(new Label(""),0,2);
			gp.add(new Label(""),0,3);
			gp.add(ok,0,4);
			 ok.setOnAction(s->{
				 try{
					 String email=null;
					 String n,pn;
					 pn=null;
					 n= null;
					 Connection conn =DbUtil.getConnection();
		            Statement stmt = conn.createStatement();
		            String sql = "INSERT INTO note (idn, nota)" +
		                    "VALUES (?, ?)";
		            
		            PreparedStatement preparedStatement = conn.prepareStatement(sql);
		            preparedStatement.setInt(1, e.getIds());
		            preparedStatement.setInt(2, cb.getValue());
		            int addedRows = preparedStatement.executeUpdate();
		            
		            
		            ResultSet rs = stmt.executeQuery("select email from studenti WHERE id='"+e.getIds()+"'");
		            while(rs.next()) {
						email= rs.getString(1);
					}
		            
		            ResultSet rs2 = stmt.executeQuery("select nume, prenume from profesori WHERE id='"+e.getIdp()+"'");
		            while(rs2.next()) {
						n = rs2.getString(1);
						pn = rs2.getString(2);
					}
		            
		            new SendEmail("Nota Examen "+e.getNume()+" ( "+n+" "+pn+" )","Ai primit nota "+cb.getValue(),email,0);
		            stmt.close();
		            conn.close();
		        }catch(Exception ex){
		            ex.printStackTrace();
		        }
				 				 
				 
				 try {
						
				        Connection conn =DbUtil.getConnection();
		                 Statement stmt = conn.createStatement();         
			 		      String sql = "UPDATE examene set rezolvare='"+e.getRezolvare()+"', predata='"+2+"' where enunt='"+e.getEnunt()+"'AND "
		                 + "ids='"+e.getIds()+"'";
			 	
			 	            PreparedStatement preparedStatement = conn.prepareStatement(sql);
			 	            int addedRows = preparedStatement.executeUpdate();
			 	            stmt.close();
			 	            conn.close();
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
			 
				
				 
				 try {
						populateListView();
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				 
				 
				 ok.getScene().getWindow().hide();
			 });

		 return gp;
	}
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listViewE.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        if (event.getClickCount() == 2) {
                       Examen e = listViewE.getSelectionModel().getSelectedItem();
                       Dialog dialog = new Dialog();
                       dialog.setTitle("Examen");
                       dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                       dialog.getDialogPane().setContent(creareDialogE(e));
                      
                           dialog.showAndWait();
		           
		        }
		    }
		});
		try {
			populateListView();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
	}

}



