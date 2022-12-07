import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafx.util.Pair;

public class Student extends Persoana{
	
	private String facultatea;
	private String specializarea;
	private int studentID;
	private int varsta;
	private String dataNasterii;
	private int anStudiu;
	private String email;
	private String parola = "0000";
	private int grupa;
	private Map<String,List<Pair<Integer,Pair<String,Integer>>>> orar= new HashMap<>();
	private Map<String,List<Integer>> note = new HashMap<>();
	
	
	
	
	public  double medie(String materie) {
		
       double med = 0;
		
		if(this.getNote().get(materie).isEmpty())
		{
			return -1 ;
		}
		
		for(int nota: this.getNote().get(materie))
  	  {
  		  med+=nota;
  	  }

       return med/this.getNote().get(materie).size();
		
	}
	
public  double medieGenerala() {
		
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
		
	}

 public void addNota(String materie, int nota) {
	this.getNote().get(materie).add(nota);
 }

    public void makeEmail() {
    
	         this.email=this.getPrenume()+"."+this.getNume()+this.getSpecializarea()+this.getDataNasterii().substring(this.getDataNasterii().
	        		 length() - 2)+"@"+"e-uvt.ro";
    }

	
	public Student(String nume, String prenume,String dataNasterii,String statut,int vartsa, String facultatea, String specializarea,
			 int anStudiu, int grupa) {
		super(nume, prenume,dataNasterii,statut,vartsa);
		this.facultatea = facultatea;
		this.specializarea = specializarea;
		this.anStudiu = anStudiu;
		this.grupa = grupa;
		this.parola = "0000";
		
	
	}
		
	
	public String getFacultatea() {
		return facultatea;
	}
	
	public void setFacultatea(String facultatea) {
		this.facultatea = facultatea;
	}
	
	public String getSpecializarea() {
		return specializarea;
	}
	
	public void setSpecializarea(String specializarea) {
		this.specializarea = specializarea;
	}
	
	public int getStudentID() {
		return studentID;
	}
	
	public void setStudentID(int studentID) {
		this.studentID = studentID;
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


	public Map<String, List<Integer>> getNote() {
		return note;
	}
	public void setNote(Map<String, List<Integer>> note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Student [studentID=" + studentID+", facultatea=" + facultatea + ", specializarea=" + specializarea 
				+ ", varsta=" + varsta + ", dataNasterii=" + dataNasterii + ", anStudiu=" + anStudiu + ", email="
				+ email + ", grupa=" + grupa + "]";
	}
	
	
	
	
	
}
