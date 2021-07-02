package Beans;

public class Rezervacija {

		private int RezervacijaID, AgencijaID, PonudaID,KorisnikID ;
		private String nazivAgencije, korisnikIme, korisnikPrezime, ukupnaCena, datum, vreme;
		private Boolean aktivan;
		
		
		public Rezervacija() { this.aktivan = true; }
		
		public Rezervacija(int rezervacijaID, int agencijaID, int ponudaID, int korisnikID, String nazivAgencije,
				String korisnikIme, String korisnikPrezime, String ukupnaCena, String datum, String vreme, Boolean aktivan) {
			
			RezervacijaID = rezervacijaID;
			AgencijaID = agencijaID;
			PonudaID = ponudaID;
			KorisnikID = korisnikID;
			this.nazivAgencije = nazivAgencije;
			this.korisnikIme = korisnikIme;
			this.korisnikPrezime = korisnikPrezime;
			this.ukupnaCena = ukupnaCena;
			this.datum = datum;
			this.vreme = vreme;
			this.aktivan = aktivan;
		}
		public int getRezervacijaID() {
			return RezervacijaID;
		}
		public void setRezervacijaID(int rezervacijaID) {
			RezervacijaID = rezervacijaID;
		}
		public int getAgencijaID() {
			return AgencijaID;
		}
		public void setAgencijaID(int agencijaID) {
			AgencijaID = agencijaID;
		}
		public int getPonudaID() {
			return PonudaID;
		}
		public void setPonudaID(int ponudaID) {
			PonudaID = ponudaID;
		}
		public int getKorisnikID() {
			return KorisnikID;
		}
		public void setKorisnikID(int korisnikID) {
			KorisnikID = korisnikID;
		}
		public String getNazivAgencije() {
			return nazivAgencije;
		}
		public void setNazivAgencije(String nazivAgencije) {
			this.nazivAgencije = nazivAgencije;
		}
		public String getKorisnikIme() {
			return korisnikIme;
		}
		public void setKorisnikIme(String korisnikIme) {
			this.korisnikIme = korisnikIme;
		}
		public String getKorisnikPrezime() {
			return korisnikPrezime;
		}
		public void setKorisnikPrezime(String korisnikPrezime) {
			this.korisnikPrezime = korisnikPrezime;
		}
		public String getUkupnaCena() {
			return ukupnaCena;
		}
		public void setUkupnaCena(String ukupnaCena) {
			this.ukupnaCena = ukupnaCena;
		}
		public String getDatum() {
			return datum;
		}
		public void setDatum(String datum) {
			this.datum = datum;
		}
		public String getVreme() {
			return vreme;
		}
		public void setVreme(String vreme) {
			this.vreme = vreme;
		}
		
		public void SetAktivan(boolean b)
		{
			this.aktivan = b;
		}
		
		public Boolean GetAktivan() {
			return this.aktivan;
		}
		
		
		
}
