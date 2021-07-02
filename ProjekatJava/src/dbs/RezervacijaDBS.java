package dbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Beans.Rezervacija;

public class RezervacijaDBS {
	String url ="jdbc:mysql://localhost:3306/projekatjava";
	String username="root";
	String password="";

     Connection conn;
     Statement stm;
	 ResultSet rs;
	 
		public void connect() throws Exception {

			try {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					throw new ClassNotFoundException(e.getMessage());
				}
				conn = DriverManager.getConnection(url, username, password);
				stm = conn.createStatement();
			} catch (SQLException ex) {
				throw new SQLException(ex.getMessage());
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	
	
	public void unosRezervacije(Rezervacija r)
	{
		try {
			this.connect();
			String query = "INSERT INTO `rezervacija`( `AgencijaID`, `PonudaID`, `KorisnikID`, `nazivAgencije`, `korisnikIme`, `korisnikPrezime`, `ukupnaCena`, `datum`, `vreme`) "
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
		     
		      preparedStmt.setInt (1, r.getAgencijaID());
		      preparedStmt.setInt   (2, r.getPonudaID());
		      preparedStmt.setInt   (3, r.getKorisnikID());
		      preparedStmt.setString (4, r.getNazivAgencije());
		      preparedStmt.setString (5, r.getKorisnikIme());
		      preparedStmt.setString (6, r.getKorisnikPrezime());
		      preparedStmt.setString (7, r.getUkupnaCena());
		      preparedStmt.setString (8, r.getDatum());
		      preparedStmt.setString (9, r.getVreme());
		      
		      preparedStmt.execute();
		      
		      conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<Rezervacija> getAllRezForAgencija(String ID) throws SQLException{
		ArrayList<Rezervacija> rez = new ArrayList<Rezervacija>();
		try {
			this.connect();
			
			
			rs=stm.executeQuery("SELECT * from rezervacija where AgencijaID = " + ID);
			while (rs.next()) {
				rez.add(new Rezervacija 
						(rs.getInt("RezervacijaID"),
						 rs.getInt("AgencijaID"),
						 rs.getInt("PonudaID"),
						 rs.getInt("KorisnikID"),
						 rs.getString("nazivAgencije"),
						 rs.getString("korisnikIme"),
						 rs.getString("korisnikPrezime"),
						 rs.getString("ukupnaCena"),
						 rs.getString("datum"),
						 rs.getString("vreme"),
						 rs.getBoolean("aktivan")
								));

			}
			return rez;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			
		}
		
		return rez;
		
	}
	
	public void deleteRezByID(String ID) {
		try {
			this.connect();

			stm.executeUpdate("UPDATE Rezervacija SET aktivan = 0 WHERE RezervacijaID = " + ID);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			
		}
	}
	
	public void UpdateRez(Rezervacija r) {
		try {
			this.connect();
			
			//conn.setAutoCommit(false);
			
				String query = "UPDATE `rezervacija` SET `RezervacijaID`=?, `AgencijaID`=?,`PonudaID`=?,`KorisnikID`=?,`nazivAgencije`=?,`korisnikIme`=?,`korisnikPrezime`=?,`ukupnaCena`=?,`datum`=?,`vreme`=?,`aktivan`=?"
						+ "		 WHERE `RezervacijaID` =  " + r.getRezervacijaID();
				PreparedStatement preparedStmt = conn.prepareStatement(query);
			     
				preparedStmt.setInt(1, r.getRezervacijaID());
			      preparedStmt.setInt (2, r.getAgencijaID());
			      preparedStmt.setInt   (3, r.getPonudaID());
			      preparedStmt.setInt   (4, r.getKorisnikID());
			      preparedStmt.setString (5, r.getNazivAgencije());
			      preparedStmt.setString (6, r.getKorisnikIme());
			      preparedStmt.setString (7, r.getKorisnikPrezime());
			      preparedStmt.setString (8, r.getUkupnaCena());
			      preparedStmt.setString (9, r.getDatum());
			      preparedStmt.setString (10, r.getVreme());
			      preparedStmt.setBoolean(11, r.GetAktivan());
			     
			      
			       preparedStmt.executeUpdate();
			       preparedStmt.close();
			      
			      
			} catch (Exception e) {
				System.out.println(e);
			}
	}
	
	public Rezervacija getbyID(String ID) {
		Rezervacija rezervacija=null;
		
		try {
			this.connect();
			rs=stm.executeQuery("SELECT * from Rezervacija where RezervacijaID="+ID);
			if(rs.next()) {
				rezervacija= new Rezervacija(
						rs.getInt("RezervacijaID"),
						 rs.getInt("AgencijaID"),
						 rs.getInt("PonudaID"),
						 rs.getInt("KorisnikID"),
						 rs.getString("nazivAgencije"),
						 rs.getString("korisnikIme"),
						 rs.getString("korisnikPrezime"),
						 rs.getString("ukupnaCena"),
						 rs.getString("datum"),
						 rs.getString("vreme"),
						 rs.getBoolean("aktivan"));
			}
			return rezervacija;
		} catch (Exception e) {
			e.printStackTrace();
			return rezervacija;
		}
		
	}



}
