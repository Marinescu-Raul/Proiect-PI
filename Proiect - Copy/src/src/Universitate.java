import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Universitate {
	private String nume;
	private List<String> saliCurs = new ArrayList<>();
	private List<String> saliLab = new ArrayList<>();
	private Map<Facultate,List<Specializare>> facultati= new HashMap<>(); 
	
	
	
	
	public Universitate(String nume, Map<Facultate,List<Specializare>> facultati, List<String> saliCurs, List<String> saliLab) {
	
		this.nume = nume;
		this.facultati = facultati;
		this.saliCurs = saliCurs;
		this.saliLab = saliLab;
	}
	
	public Universitate(String nume, Map<Facultate,List<Specializare>> facultati) {
		
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
	
	public List<String> getSaliCurs() {
		return saliCurs;
	}
	
	public void setSaliCurs(List<String> saliCurs) {
		this.saliCurs = saliCurs;
	}
	
	public List<String> getSaliLab() {
		return saliLab;
	}
	
	public void setSaliLab(List<String> saliLab) {
		this.saliLab = saliLab;
	}
	
	public Map<Facultate,List<Specializare>> getFacultati() {
		return facultati;
	}
	
	public void setFacultati(Map<Facultate,List<Specializare>>facultati) {
		this.facultati = facultati;
	}
	
	
	public void addFacultate(Facultate facultate)
	{
		this.facultati.put(facultate, facultate.getSpecializari());
	}
	
	
	
	


}
