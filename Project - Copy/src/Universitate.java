import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Universitate {
	private String nume;
	private List<Facultate> facultati= new ArrayList<>(); 
	
	
	
	
	public Universitate(String nume, List<Facultate> facultati, List<String> saliCurs, List<String> saliLab) {
	
		this.nume = nume;
		this.facultati = facultati;
	}
	
	public Universitate(String nume, List<Facultate> facultati) {
		
		this.nume = nume;
		this.facultati = facultati;
	}
	
	
	public Universitate(String nume) {
		this.nume = nume;
	}
	
	
	public String getNume() {
		return nume;
	}
	
	public void setNume(String nume) {
		this.nume = nume;
	}
		
	public List<Facultate>  getFacultati() {
		return facultati;
	}
	
	public void setFacultati(List<Facultate> facultati) {
		this.facultati = facultati;
	}
	
	
	public void addFacultate(Facultate facultate)
	{
		this.facultati.add(facultate);
	}
	
	
	
	


}
