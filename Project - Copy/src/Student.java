import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

public class Student extends Persoana{
	
	private String facultate;
	private int anStudiu;
	private String email;
	private String parola = "0000";
	private int grupa;
	private double medie;
	private Map<String,List<Pair<Integer,Pair<String,Integer>>>> orar= new HashMap<>();
	//private Map<String,List<Integer>> note = new HashMap<>();
	private List<Integer> note = new ArrayList<>();
	
	
	
	
	

	
/*public  double medieGenerala() {
		
		double medGen = 0;
		int nrMatCuNote = 0;
		 Iterator<Entry<String, List<Integer>>> iterator = this.getNote().entrySet().iterator();
		 while (iterator.hasNext()) {
		        Entry<String, List<Integer>> entry = iterator.next();
		         double med = 0;
		        	  for(int nota: entry.getValue())
		        	  {
		        		  med+=nota;
		        	  }
		        	  if(entry.getValue().size()==0)
		        	      {
		        		    continue;
		        	      }
		        	  else
		        	      {
		        	        med=med/entry.getValue().size();
		        	        medGen+=med;
		        	        nrMatCuNote++;
		        	      }
		        	 
		          }
		 if(medGen==0)
			 medGen=-1;
		 else
		 medGen = medGen/nrMatCuNote;
		 
		 return medGen;
		
	}*/


public double getMedie() {
	return medie;
}

public void setMedie(double medie) {
	this.medie = medie;
}

public void addNota(int nota) {
	this.getNote().add(nota);
 }

    public void makeEmail() {
    
	         this.email=this.getPrenume().toLowerCase()+"."+this.getNume().toLowerCase()+this.getDataNasterii().substring(this.getDataNasterii().
	        		 length() - 2)+"@"+"e-uvt.ro";
    }

	
	public Student(String nume, String prenume,String dataNasterii, String statut, int varsta, String facultate,
			 int anStudiu, int grupa) {
		super(nume, prenume,dataNasterii,statut,varsta);
		this.facultate = facultate;
		this.anStudiu = anStudiu;
		this.grupa = grupa;
		this.parola = "0000";
	}
		
	
	public String getFacultate() {
		return facultate;
	}
	
	public void setFacultate(String facultate) {
		this.facultate = facultate;
	}
	
	

	
	public int getAnStudiu() {
		return anStudiu;
	}


	public void setAnStudiu(int anStudiu) {
		this.anStudiu = anStudiu;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getGrupa() {
		return grupa;
	}


	public void setGrupa(int grupa) {
		this.grupa = grupa;
	}
	public void setParola(String parola) {
		this.parola = parola;
	}
	public void setParola(String vechie, String noua) {
		if(this.parola.equals(vechie))
		   this.parola = noua;
		else
		{
			System.out.println("Eroare!");
		}
	}
	
	public String getParola() {
		return parola;
	}
	
	


	public  Map<String,List<Pair<Integer,Pair<String,Integer>>>> getOrar() {
		return orar;
	}


	public void setOrar(Map<String,List<Pair<Integer,Pair<String,Integer>>>> orar) {
		this.orar = orar;
	}


	public List<Integer>  getNote() {
		return note;
	}
	public void setNote(List<Integer> note) {
		this.note = note;
	}


	
	
	
	
	
}
