
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class Login {
	

	public Login() {
		
	}
	
	@FXML
	private Button loginB;
	@FXML
	private Button guestLogin;
	@FXML
	private Label wrongLogin;
	@FXML
	private TextField email;
	@FXML
	private PasswordField parola;
	
	
	public void userLogIn(ActionEvent event)throws IOException{
		checkLogin();
	}
	
	public void guestLogIn(ActionEvent event)throws IOException{
		Main m = new Main();
		m.changeScene("GuestMain.fxml");
	}
	
	
	public static Profesor creareProfesor(String nume, String prenume, String dataNasterii,int varsta,String facultate) {
		
		 nume = nume.substring(0,1).toUpperCase() + nume.substring(1).toLowerCase();
		 prenume = prenume.substring(0,1).toUpperCase() + prenume.substring(1).toLowerCase();
		 String statut = "profesor";
		 Profesor p = new Profesor(nume,prenume,dataNasterii,statut,varsta,facultate);
		 p.makeEmail();
	
			 
		 return p;
	}
	
	public static Student creareStudent(String nume, String prenume, String dataNasterii, 
			int varsta, String facultate,int an, int grupa) {
		 nume = nume.substring(0,1).toUpperCase() + nume.substring(1).toLowerCase();
		 prenume = prenume.substring(0,1).toUpperCase() + prenume.substring(1).toLowerCase();
		 String statut = "student";
	
		 Student s1 = new Student(nume,prenume,dataNasterii,statut,varsta,facultate,an,grupa);
		 s1.makeEmail();
		
	     //Map<String,List<Integer>> carnet = new HashMap<>();
		 
	     //s1.setNote();
		 
		 
		return s1;
		
	}
	
	
	
	private void checkLogin()throws IOException{
		Main m = new Main();
		  List<Profesor> prof = new ArrayList<>();
		  List<Student> stud = new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db_proiect","root","");
            Statement stmt = con.createStatement();
           
            ResultSet rs1;
            rs1 = stmt.executeQuery("select * from profesori");
            while(rs1.next()) {
            	    int ID1 = rs1.getInt(1);
                String num1 =  rs1.getString(2);
                String pnum1 =rs1.getString(3);
                String dn1 = rs1.getString(4);
                String parola1 = rs1.getString(9);
                int v1 = rs1.getInt(5);
                String f1 = rs1.getString(7);
                Profesor p  = creareProfesor(num1,pnum1,dn1,v1,f1);
                p.setId(ID1);
                p.setParola(parola1);
                prof.add(p);
            }
                
                
                
                ResultSet rs2;
                rs2 = stmt.executeQuery("select * from studenti");
                while(rs2.next()) {
                	    int ID2 = rs2.getInt(1);
                    String num =  rs2.getString(2);
                    String pnum =rs2.getString(3);
                    int v = rs2.getInt(4);
                    String dn = rs2.getString(5);
                    int a = rs2.getInt(6);
                    int g = rs2.getInt(9);
                    String f = rs2.getString(10);
                    String parola2 = rs2.getString(8);
                    Student s  = creareStudent(num,pnum,dn,v,f,a,g);
                    s.setId(ID2);
                    s.setParola(parola2);
                    stud.add(s);
                   
               
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
      
       
		
		if(email.getText().toString().equals("Admin") && parola.getText().toString().equals("0000")) {
			wrongLogin.setText("Success");
			
			m.changeScene("AdminMain.fxml");
		}
		else
			 {
				for (Profesor pers: prof) {
					if(email.getText().toString().equals(pers.getEmail()) && parola.getText().toString().equals(pers.getParola())) {
						Global.pGlob = pers;
						m.dimensiuni(754, 551);
						m.changeScene("ProfesorMain.fxml");
					}
				}
				for (Student pers: stud) {
					if(email.getText().toString().equals(pers.getEmail()) && parola.getText().toString().equals(pers.getParola())) {
						Global.sGlob = pers;
						m.changeScene("StudentMain.fxml");
					}
				}
			}
		
		if(email.getText().isEmpty() || parola.getText().isEmpty()) {
				wrongLogin.setText("Inserati datele.");
			}
			else
				wrongLogin.setText(" Emailul sau parola este gresita!");
	}

	
	

}
