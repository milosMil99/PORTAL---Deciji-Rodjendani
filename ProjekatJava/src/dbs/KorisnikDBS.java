package dbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Beans.Korisnik;
import Beans.Rezervacija;


public class KorisnikDBS {
	
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
		
		
		public ArrayList<Korisnik> getAll() {
			ArrayList<Korisnik>korisnici = new ArrayList<Korisnik>();
			
			try {
				this.connect();
				rs=stm.executeQuery("SELECT * from Korisnik");
				while (rs.next()) {
					korisnici.add(new Korisnik
								(rs.getInt("KorisnikID"),
								 rs.getInt("tipKorisnikaID"),
								 rs.getString("ime"),
								 rs.getString("prezime"),
								 rs.getString("email"),
								 rs.getString("lozinka"),
								 rs.getString("bodovi"),
								 rs.getBoolean("aktivan")));
								 
				}
				return korisnici;
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return korisnici;
		}
		
		
		public Korisnik getKorisnikbyID(String ID) {
			Korisnik korisnik=null;
			
			try {
				this.connect();
				rs=stm.executeQuery("SELECT * from Korisnik where KorisnikID="+ID);
				if(rs.next()) {
					korisnik= new Korisnik(
							rs.getInt("KorisnikID"),
							 rs.getInt("tipKorisnikaID"),
							 rs.getString("ime"),
							 rs.getString("prezime"),
							 rs.getString("email"),
							 rs.getString("lozinka"),
							 rs.getString("bodovi"),
							 rs.getBoolean("aktivan"));
				}
				return korisnik;
			} catch (Exception e) {
				e.printStackTrace();
				return korisnik;
			}
			
		}
		
		public Korisnik getTipbyID(String TipID) {
			Korisnik korisnik=null;
			
			try {
				this.connect();
				rs=stm.executeQuery("SELECT * from Korisnik where tipKorisnikaID="+TipID);
				if(rs.next()) {
					korisnik= new Korisnik(
							rs.getInt("KorisnikID"),
							 rs.getInt("tipKorisnikaID"),
							 rs.getString("ime"),
							 rs.getString("prezime"),
							 rs.getString("email"),
							 rs.getString("lozinka"),
							 rs.getString("bodovi"),
							 rs.getBoolean("aktivan"));
				}
				return korisnik;
			} catch (Exception e) {
				e.printStackTrace();
				return korisnik;
			}
			
		}
		
		public String getTipKorisnik(int id)
		{
			String rola = null;
			try 
			{
				this.connect();
				rs = stm.executeQuery("SELECT k.TipKorisnikaID, t.TipKorisnika  from Korisnik k JOIN tipkorisnika t ON k.tipKorisnikaID = t.tipKorisnikaID WHERE k.KorisnikID = "+id);
				if(rs.next()) {
					rola = rs.getString("TipKorisnika");
					
				}
				return rola;
			}
			catch (Exception e) {
				e.printStackTrace();
				return rola;
			}
			
		}
		
		public Korisnik validacijaKorisnika(String email,String lozinka) throws SQLException {
			try {
				this.connect();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Korisnik korisnik=null;
			rs=stm.executeQuery("SELECT * from Korisnik where email='"+email+"' and lozinka='"+lozinka+"'");
			if(rs.next()) {
				korisnik= new Korisnik(
						rs.getInt("KorisnikID"),
						 rs.getInt("tipKorisnikaID"),
						 rs.getString("ime"),
						 rs.getString("prezime"),
						 rs.getString("email"),
						 rs.getString("lozinka"),
						 rs.getString("bodovi"),
						 rs.getBoolean("aktivan"));
			}
			
				return korisnik;
			
			
		}
		
		public void registracijaKorisnika(Korisnik k)
		{
			try {
				this.connect();
				String query = "INSERT INTO `korisnik`(`ime`, `prezime`, `email`, `lozinka`) "
						+ "VALUES (?,?,?,?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
			     
			      preparedStmt.setString(1, k.getIme());
			      preparedStmt.setString (2, k.getPrezime());
			      preparedStmt.setString (3, k.getEmail());
			      preparedStmt.setString (4, k.getLozinka());
			      
			      preparedStmt.execute();
			      
			      conn.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		public void deleteKorisnik(String ID) {
			try {
				this.connect();

				stm.executeUpdate("UPDATE Korisnik SET aktivan = 0 WHERE KorisnikID = " + ID);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				
			}
		}
		
		
		public void UpdateKorisnik(Korisnik k) {
			try {
				this.connect();
				
				//conn.setAutoCommit(false);
				
					String query = "UPDATE `korisnik` SET `KorisnikID`=?, `tipKorisnikaID`=?,`ime`=?,`prezime`=?,`email`=?,`lozinka`=?,`bodovi`=?"
							+ "		 WHERE `KorisnikID` =  " + k.getKorisnikID();
					PreparedStatement preparedStmt = conn.prepareStatement(query);
				     
					preparedStmt.setInt(1,k.getKorisnikID());
				      preparedStmt.setInt (2, k.getTipKorisnikaID());
				      preparedStmt.setString   (3, k.getIme());
				      preparedStmt.setString   (4, k.getPrezime());
				      preparedStmt.setString (5, k.getEmail());
				      preparedStmt.setString (6, k.getLozinka());
				      preparedStmt.setString (7, k.getBodovi());
				     
				     
				     
				      
				       preparedStmt.executeUpdate();
				       preparedStmt.close();
				      
				      
				} catch (Exception e) {
					System.out.println(e);
				}
		}
		
		public void UpdateBodovi (Korisnik k, String cena, String iskoristeniBodovi)
		{
			try {
				this.connect();
				
				double cenaRez = Double.parseDouble(cena);
				double bodoviDoSad = Double.parseDouble(k.getBodovi());
				double bodoviOstvareni = cenaRez*0.001;
				
				double bodoviNovi = bodoviOstvareni + bodoviDoSad;
				
				
				
				if(iskoristeniBodovi != null)
				{
					double bodoviNoviIskoristeni = Double.parseDouble(iskoristeniBodovi);
					bodoviNovi -= bodoviNoviIskoristeni;
				}
				
				String bodoviNoviString = String.valueOf(bodoviNovi);
				
					String query = "UPDATE `korisnik` SET `bodovi`=?"
							+ "		 WHERE `KorisnikID` =  " + k.getKorisnikID();
					PreparedStatement preparedStmt = conn.prepareStatement(query);
				     
					preparedStmt.setString(1,bodoviNoviString);
				      
				     
				       preparedStmt.executeUpdate();
				       preparedStmt.close();
				      
				      
				} catch (Exception e) {
					System.out.println(e);
				}
		}
		
		
		
		
		
}
