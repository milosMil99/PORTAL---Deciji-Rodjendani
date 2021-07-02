package Beans;

public class Ponuda {

		private int PonudaID;
		private String naziv,opis,cena;
		private Boolean aktivan;
		
		
		public Ponuda() {}
		
		public Ponuda(int ponudaID, String naziv, String opis, String cena, Boolean aktivan) {
		
			PonudaID = ponudaID;
			this.naziv = naziv;
			this.opis = opis;
			this.cena = cena;
			this.aktivan = aktivan;
		}
		
		public int getPonudaID() {
			return PonudaID;
		}
		public void setPonudaID(int ponudaID) {
			PonudaID = ponudaID;
		}
		public String getNaziv() {
			return naziv;
		}
		public void setNaziv(String naziv) {
			this.naziv = naziv;
		}
		public String getOpis() {
			return opis;
		}
		public void setOpis(String opis) {
			this.opis = opis;
		}
		public String getCena() {
			return cena;
		}
		public void setCena(String cena) {
			this.cena = cena;
		}
		
		public void SetAktivan(boolean b)
		{
			this.aktivan = b;
		}
		
		public Boolean GetAktivan() {
			return aktivan;
		}
		
}
