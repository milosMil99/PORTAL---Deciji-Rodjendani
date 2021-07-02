package Beans;

public class Korisnik {
	
	private int KorisnikID, tipKorisnikaID;
	private String ime,prezime,email,lozinka,bodovi;
	private Boolean aktivan;
	
	
	
	public Korisnik() { };
	
	public Korisnik(int korisnikID, int tipKorisnikaID, String ime, String prezime, String email, String lozinka,
			String bodovi, Boolean aktivan) {
		KorisnikID = korisnikID;
		this.tipKorisnikaID = tipKorisnikaID;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.lozinka = lozinka;
		this.bodovi = bodovi;
		this.aktivan = aktivan;
	}
	public int getKorisnikID() {
		return KorisnikID;
	}
	public void setKorisnikID(int korisnikID) {
		KorisnikID = korisnikID;
	}
	public int getTipKorisnikaID() {
		return tipKorisnikaID;
	}
	public void setTipKorisnikaID(int tipKorisnikaID) {
		this.tipKorisnikaID = tipKorisnikaID;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public String getBodovi() {
		return bodovi;
	}
	public void setBodovi(String bodovi) {
		this.bodovi = bodovi;
	}
	public void SetAktivan(boolean b)
	{
		this.aktivan = b;
	}
	
	public Boolean GetAktivan() {
		return aktivan;
	}
	
}
