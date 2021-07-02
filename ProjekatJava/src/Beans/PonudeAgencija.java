package Beans;

public class PonudeAgencija {
	
	
	private int PonudeAgencijaID, AgencijaID, PonudaID;

	
	public PonudeAgencija() {}
	
	
	public PonudeAgencija(int ponudeAgencijaID, int agencijaID, int ponudaID) {
		
		PonudeAgencijaID = ponudeAgencijaID;
		AgencijaID = agencijaID;
		PonudaID = ponudaID;
	}

	public int getPonudeAgencijaID() {
		return PonudeAgencijaID;
	}

	public void setPonudeAgencijaID(int ponudeAgencijaID) {
		PonudeAgencijaID = ponudeAgencijaID;
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

	
	

}
