
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	    public static int StudId=0;
	    public static int ProfId=0;
	    
	    private static Stage stg;
	    
	    public void start(Stage login) throws Exception{
	    	try {
	    	stg = login;
	    	login.setResizable(false);
	  
	    	Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
	    	login.setTitle("Project");
	    	login.setScene(new Scene(root, 600,400));
	    	login.show();
	    }catch(Exception e) {
	        e.printStackTrace();
	  }
	}
	    
	    public void changeScene(String fxml) throws IOException{
	    	Parent pane = FXMLLoader.load(getClass().getResource(fxml));
	    	stg.getScene().setRoot(pane);
	    }
	    
	    
	    
	    
	
	public static Student creareStudent(String nume, String prenume, String dataNasterii, 
			int varsta, String facultate,String specializare,int an, int grupa) {
		 nume = nume.substring(0,1).toUpperCase() + nume.substring(1).toLowerCase();
		 prenume = prenume.substring(0,1).toUpperCase() + prenume.substring(1).toLowerCase();
		 String statut = "student";
	
		 Student s1 = new Student(nume,prenume,dataNasterii,statut,varsta,facultate,specializare,an,grupa);
		 s1.makeEmail();
		 s1.setStudentID(++StudId);
		 
		 //! Exista vreo metoda de a stii automate ce materii sa
		 //ii setez unui student daca stim facultatea specialitatea si anul?
		 
		 /// as vrea sa fac cu enum
		     Map<String,List<Integer>> carnet = new HashMap<>();
		      s1.setNote(carnet);
		 
		 
		return s1;
		
	}
	
	public static Profesor creareProfesor(String nume, String prenume, String dataNasterii,int varsta) {
		
		 nume = nume.substring(0,1).toUpperCase() + nume.substring(1).toLowerCase();
		 prenume = prenume.substring(0,1).toUpperCase() + prenume.substring(1).toLowerCase();
		 String statut = "profesor";
		 Profesor p = new Profesor(nume,prenume,dataNasterii,statut,varsta);
		 p.makeEmail();
		 p.setProfesorID(++ProfId);
		 

		return p;
		
	}
	
	public static void main(String[] args) {
		//List<Persoana> persoane= new ArrayList<>();
		 // Student s1 = creareStudent();
		  // Profesor p1 = creareProfesor();
		  // persoane.add(s1);
		  // persoane.add(p1);
		 launch(args);
	
		   
	
		  
		
		
		
	}



}
