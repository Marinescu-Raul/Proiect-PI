
public class Persoana {
	private int id;
	private String nume;
	private String prenume;
	private String dataNasterii;
	private String statut;
	private int varsta;
	
	
	
	
	public Persoana( String nume, String prenume, String dataNasterii, String statut, int varsta) {
		this.nume = nume;
		this.prenume = prenume;
		this.dataNasterii = dataNasterii;
		this.statut = statut;
		this.varsta = varsta;
	
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Persoana() {
	
	}
	
	
	public String getStatut() {
		return statut;
	}



	public void setStatut(String statut) {
		this.statut = statut;
	}



	public String getNume() {
		return nume;
	}
	
	public void setNume(String nume) {
		this.nume = nume;
	}
	
	public String getPrenume() {
		return prenume;
	}
	
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	
	public String getDataNasterii() {
		return dataNasterii;
	}
	
	public void setDataNasterii(String dataNasterii) {
		this.dataNasterii = dataNasterii;
	}
	


	public int getVarsta() {
		return varsta;
	}


	public void setVarsta(int varsta) {
		this.varsta = varsta;
	}



	@Override
	public String toString() {
		return "Persoana [nume=" + nume + ", prenume=" + prenume + ", dataNasterii=" + dataNasterii + "]";
	}
	
	
	
	
	
	
	

}
