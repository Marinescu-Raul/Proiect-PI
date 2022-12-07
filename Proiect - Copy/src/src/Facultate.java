import java.util.ArrayList;
import java.util.List;

public class Facultate {
	
	private String nume;
	private int nrLocuri;
	private double ultimaMedie;
	private String descriere;
	private int ani;
	private List<Specializare> specializari = new ArrayList<>();
	
	
	
	
	
	
	public Facultate(String nume, int nrLocuri, double ultimaMedie, String descriere, int ani) {
		this.nume = nume;
		this.nrLocuri = nrLocuri;
		this.ultimaMedie = ultimaMedie;
		this.descriere = descriere;
		this.ani = ani;
	}
	
	
	public void addSpecializare(Specializare s) {
		this.getSpecializari().add(s);
		
	}
	
	public List<Specializare> getSpecializari() {
		return specializari;
	}
	public void setSpecializari(List<Specializare> specializari) {
		this.specializari = specializari;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public int getNrLocuri() {
		return nrLocuri;
	}
	public void setNrLocuri(int nrLocuri) {
		this.nrLocuri = nrLocuri;
	}
	public double getUltimaMedie() {
		return ultimaMedie;
	}
	public void setUltimaMedie(double ultimaMedie) {
		this.ultimaMedie = ultimaMedie;
	}
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	public int getAni() {
		return ani;
	}
	public void setAni(int ani) {
		this.ani = ani;
	}
	
	
	

}
