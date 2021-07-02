package Beans;

public class Agencija {
	
	private int AgencijaID, menadzer;
	private String naziv,lokacija,opis,slika,pocetakRadnog, krajRadnog;
	private Boolean aktivan;
	
	
	public Agencija() {}
	
	
	public Agencija(int agencijaID, int menadzer, String naziv, String lokacija, String opis, String slika,
			String pocetakRadnog, String krajRadnog, Boolean aktivan) {
		
		AgencijaID = agencijaID;
		this.menadzer = menadzer;
		this.naziv = naziv;
		this.lokacija = lokacija;
		this.opis = opis;
		this.slika = slika;
		this.pocetakRadnog = pocetakRadnog;
		this.krajRadnog = krajRadnog;
		this.aktivan = aktivan;
	}
	
	public int getAgencijaID() {
		return AgencijaID;
	}
	public void setAgencijaID(int agencijaID) {
		AgencijaID = agencijaID;
	}
	public int getMenadzer() {
		return menadzer;
	}
	public void setMenadzer(int menadzer) {
		this.menadzer = menadzer;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getLokacija() {
		return lokacija;
	}
	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getSlika() {
		return slika;
	}
	public void setSlika(String slika) {
		this.slika = slika;
	}
	public String getPocetakRadnog() {
		return pocetakRadnog;
	}
	public void setPocetakRadnog(String pocetakRadnog) {
		this.pocetakRadnog = pocetakRadnog;
	}
	public String getKrajRadnog() {
		return krajRadnog;
	}
	public void setKrajRadnog(String krajRadnog) {
		this.krajRadnog = krajRadnog;
	}
	
	public void SetAktivan(boolean b)
	{
		this.aktivan = b;
	}
	
	public Boolean GetAktivan() {
		return aktivan;
	}
	
}
