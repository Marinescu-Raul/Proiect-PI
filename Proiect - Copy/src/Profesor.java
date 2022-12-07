import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

public class Profesor extends Persoana{
	private int profesorID;
	private List<String> materii = new ArrayList<>();
	private String email;
	private String parola;
	private List<Facultate> facultati = new ArrayList<>();
	private Map<String,List<Pair<Integer,Pair<String,Integer>>>> orar= new HashMap<>();
	
	
	
	
	

	
	public Profesor(String nume, String prenume, String dataNasterii, String statut, int vartsa) {
		super(nume, prenume,dataNasterii, statut,vartsa);
		this.parola = "0000";
	}
	
	
	public void makeEmail() {
	         this.email=this.getPrenume()+"."+this.getNume()+"@"+"e-uvt.ro";
    }
	
	public void addMaterie(String materie) {
		this.getMaterii().add(materie);
	}
	
	
	public int getProfesorID() {
		return profesorID;
	}
	@Override
	public String toString() {
		return "Profesor [profesorID=" + profesorID+", nume="+getNume()+", prenume="+getPrenume() + ", email=" + email + "]";
	}


	public void setProfesorID(int profesorID) {
		this.profesorID = profesorID;
	}
	public List<String> getMaterii() {
		return materii;
	}
	public void setMaterii(List<String> materii) {
		this.materii = materii;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getParola() {
		return parola;
	}
	
	public void setParola(String vechie, String noua) {
		if(this.parola.equals(vechie))
		   this.parola = noua;
		else
		{
			System.out.println("Eroare!");
		}
	}
	public Map<String,List<Pair<Integer,Pair<String,Integer>>>>getOrar() {
		return orar;
	}
	public void setOrar(Map<String,List<Pair<Integer,Pair<String,Integer>>>> orar) {
		this.orar = orar;
	}
	
	


}
