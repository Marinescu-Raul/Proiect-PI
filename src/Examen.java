import java.time.LocalDate;
import java.util.Date;

public class Examen {
	@Override
	public String toString() {
		return "Examen [id=" + id + ", nume=" + nume + ", an=" + an + ", data=" + data + ", enunt=" + enunt
				+ ", rezolvare=" + rezolvare + ", predata=" + predata + ", idp=" + idp + ", ids=" + ids + "]";
	}

	private int id;
	private String nume;
	private int an;
	private Date data;
	private String enunt;
	private String rezolvare;
	private int predata;
	private int idp;
	private int ids;
	
	public Examen(String nume, int an, Date data, String enunt) {
		this.nume = nume;
		this.an = an;
		this.data = data;
		this.enunt = enunt;
		this.predata=0;
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

	public int getAn() {
		return an;
	}

	public void setAn(int an) {
		this.an = an;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getEnunt() {
		return enunt;
	}

	public void setEnunt(String enunt) {
		this.enunt = enunt;
	}

	public String getRezolvare() {
		return rezolvare;
	}

	public void setRezolvare(String rezolvare) {
		this.rezolvare = rezolvare;
	}

	public int getPredata() {
		return predata;
	}

	public void setPredata(int predata) {
		this.predata = predata;
	}

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
	
	
	
	

}
