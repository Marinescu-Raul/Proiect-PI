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
	
	
	private final Integer[] arrayNote = {1,2,3,4,5,6,7,8,9,10};
	
	
	
	@FXML
	void inapoi() throws IOException {
		 Main m =new Main();
		 m.dimensiuni( 752.5,550);
		 m.changeScene("ProfesorMain.fxml");
	}
	
	void populateListView() throws SQLException {
		listViewP.getItems().clear();
		listViewNP.getItems().clear();
		ObservableList<Tema> nepredate = FXCollections.observableArrayList();
		ObservableList<Tema> predate = FXCollections.observableArrayList();
		Connection con =DbUtil.getConnection();
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
					if(t.getPredata()==1)
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
	
	@FXML
	private Node creareDialogTP(Tema t) {
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
	        area.setText(t.getRezolvare());
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
		            preparedStatement.setInt(1, t.getIds());
		            preparedStatement.setInt(2, cb.getValue());
		            int addedRows = preparedStatement.executeUpdate();
		            
		            
		            ResultSet rs = stmt.executeQuery("select email from studenti WHERE id='"+t.getIds()+"'");
		            while(rs.next()) {
						email= rs.getString(1);
					}
		            
		            ResultSet rs2 = stmt.executeQuery("select nume, prenume from profesori WHERE id='"+t.getIdp()+"'");
		            while(rs2.next()) {
						n = rs2.getString(1);
						pn = rs2.getString(2);
					}
		            
		            new SendEmail("Nota Tema "+t.getNume()+" ( "+n+" "+pn+" )","Ai primit nota "+cb.getValue(),email,0);
		            stmt.close();
		            conn.close();
		        }catch(Exception e){
		            e.printStackTrace();
		        }
				 				 
				 
				 try {
						
				        Connection conn =DbUtil.getConnection();
		                 Statement stmt = conn.createStatement();         
			 		      String sql = "UPDATE teme set rezolvare='"+t.getRezolvare()+"', predata='"+2+"' where enunt='"+t.getEnunt()+"'AND "
		                 + "ids='"+t.getIds()+"'";
			 	
			 	            PreparedStatement preparedStatement = conn.prepareStatement(sql);
			 	            int addedRows = preparedStatement.executeUpdate();
			 	            stmt.close();
			 	            conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 
				
				 
				 try {
						populateListView();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 
				 
				 ok.getScene().getWindow().hide();
			 });

		 return gp;
	}
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listViewP.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        if (event.getClickCount() == 2) {
                       Tema t = listViewP.getSelectionModel().getSelectedItem();
                       Dialog dialog = new Dialog();
                       dialog.setTitle("Verifica tema");
                       dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                       dialog.getDialogPane().setContent(creareDialogTP(t));
                           dialog.showAndWait();
		           
		        }
		    }
		});
		try {
			populateListView();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
