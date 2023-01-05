
public class Tema {
	private int id;
	private String nume;
	private String facultate;
	private int an;
	private int grupa;
	private String enunt;
	private String rezolvare;
	private int predata;
	private int idp;
	private int ids;
	
	
	
	public int getIdp() {
		return idp;
	}



	public void setIdp(int idp) {
		this.idp = idp;
	}



	public int getIds() {
		return ids;
	}



	public void setIds(int ids) {
		this.ids = ids;
	}



	public int getPredata() {
		return predata;
	}



	public void setPredata(int predata) {
		this.predata = predata;
	}



	public Tema(String nume, String facultate, int an, int grupa, String enunt, String rezolvare, int predata, int idp, int ids) {
		this.nume = nume;
		this.facultate = facultate;
		this.an = an;
		this.grupa = grupa;
		this.enunt = enunt;
		this.rezolvare = rezolvare;
		this.predata = predata;
		this.idp = idp;
		this.ids = ids;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNume() {
		return nume;
	}



	public void setNume(String nume) {
		this.nume = nume;
	}



	public String getFacultate() {
		return facultate;
	}



	public void setFacultate(String facultate) {
		this.facultate = facultate;
	}



	public int getAn() {
		return an;
	}



	public void setAn(int an) {
		this.an = an;
	}



	public int getGrupa() {
		return grupa;
	}



	public void setGrupa(int grupa) {
		this.grupa = grupa;
	}



	public String getEnunt() {
		return enunt;
	}



	public String getRezolvare() {
		return rezolvare;
	}



	public void setRezolvare(String rezolvare) {
		this.rezolvare = rezolvare;
	}



	public void setEnunt(String enunt) {
		this.enunt = enunt;
	}



	@Override
	public String toString() {
		return nume+" - ";
	}
	
	
	
 
	 
}
