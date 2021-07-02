package Beans;



public class TipKorisnika {
	
	 private  int tipKorisnikaID;
	 private String tipKorisnika;
	 
	 
	 
	 
	public TipKorisnika(int tipKorisnikaID, String tipKorisnika) {
		
		this.tipKorisnikaID = tipKorisnikaID;
		this.tipKorisnika = tipKorisnika;
	}
	public int getTipKorisnikaID() {
		return tipKorisnikaID;
	}
	public void setTipKorisnikaID(int tipKorisnikaID) {
		this.tipKorisnikaID = tipKorisnikaID;
	}
	public String getTipKorisnika() {
		return tipKorisnika;
	}
	public void setTipKorisnika(String tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}
	 
	 
	 
}
