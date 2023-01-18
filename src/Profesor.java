import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

public class Profesor extends Persoana{
	private String email;
	private String parola;
	private String facultate;
	private Map<String,List<Pair<Integer,Pair<String,Integer>>>> orar= new HashMap<>();
	
	
	
	
	
	public Profesor() {
		
	}
	
	
	public Profesor(String nume, String prenume, String dataNasterii, String statut, int vartsa,String facultate) {
		super(nume, prenume,dataNasterii, statut,vartsa);
		this.parola = "0000";
		this.facultate = facultate;
	}
	
	
	public void makeEmail() {
	         this.email=this.getPrenume().toLowerCase()+"."+this.getNume().toLowerCase()+"@"+"e-uvt.ro";
    }
	

	@Override
	public String toString() {
		return getNume()+" "+getPrenume();
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
	
	
	public void setParola(String parola) {
		this.parola = parola;
	}
	
	
	public String getFacultate() {
		return facultate;
	}


	public void setFacultate(String facultate) {
		this.facultate = facultate;
	}



	public Map<String,List<Pair<Integer,Pair<String,Integer>>>>getOrar() {
		return orar;
	}
	public void setOrar(Map<String,List<Pair<Integer,Pair<String,Integer>>>> orar) {
		this.orar = orar;
	}
	
	


}
