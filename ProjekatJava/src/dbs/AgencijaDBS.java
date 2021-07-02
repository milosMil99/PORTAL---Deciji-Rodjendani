package dbs;
import java.sql.*;
import java.util.*;

import Beans.*;
public class AgencijaDBS {

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
		
		
		public ArrayList<Agencija> getAll(){
			
			
			ArrayList<Agencija>agencije = new ArrayList<Agencija>();
			try {
				this.connect();
				rs=stm.executeQuery("SELECT * from Agencija");
				while (rs.next()) {
					agencije.add(new Agencija 
								(rs.getInt("AgencijaID"),
								 rs.getInt("menadzer"),
								 rs.getString("naziv"),
								 rs.getString("lokacija"),
								 rs.getString("opis"),
								 rs.getString("slika"),
								 rs.getString("pocetakRadnog"),
								 rs.getString("krajRadnog"),
								 rs.getBoolean("aktivan")));

				}
				return agencije;
			} catch (Exception e) {
				// TODO: handle exception
			}
			return agencije;
			
		}
		
		
		public Agencija getbyID(String ID) {
			Agencija agencija=null;
			
			try {
				this.connect();
				rs=stm.executeQuery("SELECT * from Agencija where AgencijaID="+ID);
				if(rs.next()) {
					agencija= new Agencija(
							rs.getInt("AgencijaID"),
							 rs.getInt("menadzer"),
							 rs.getString("naziv"),
							 rs.getString("lokacija"),
							 rs.getString("opis"),
							 rs.getString("slika"),
							 rs.getString("pocetakRadnog"),
							 rs.getString("krajRadnog"),
							 rs.getBoolean("aktivan"));
				}
				return agencija;
			} catch (Exception e) {
				e.printStackTrace();
				return agencija;
			}
			
		}
		
		public Agencija getbyMenadzerID(String ID) {
			Agencija agencija=null;
			
			try {
				this.connect();
				rs=stm.executeQuery("SELECT * from Agencija where menadzer="+ID);
				if(rs.next()) {
					agencija= new Agencija(
							rs.getInt("AgencijaID"),
							 rs.getInt("menadzer"),
							 rs.getString("naziv"),
							 rs.getString("lokacija"),
							 rs.getString("opis"),
							 rs.getString("slika"),
							 rs.getString("pocetakRadnog"),
							 rs.getString("krajRadnog"),
							 rs.getBoolean("aktivan"));
				}
				return agencija;
			} catch (Exception e) {
				e.printStackTrace();
				return agencija;
			}
			
		}
		
		
		
		public void insertAgencije(Agencija a) {
			
			try {
				this.connect();
				String query = "INSERT INTO `agencija`( `menadzer`, `naziv`, `lokacija`, `opis`, `slika`, `pocetakRadnog`, `krajRadnog`) "
						+ "VALUES (?,?,?,?,?,?,?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
			     
			      preparedStmt.setInt (1, a.getMenadzer());
			      preparedStmt.setString(2, a.getNaziv()); 
			      preparedStmt.setString(3, a.getLokacija()); 
			      preparedStmt.setString(4,a.getOpis()); 
			      preparedStmt.setString(5, a.getSlika());
			      preparedStmt.setString(6,a.getPocetakRadnog());
			      preparedStmt.setString(7,a.getKrajRadnog()); 
			      
			      preparedStmt.execute();
			      
			      conn.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		public void UpdateAgencija(Agencija agencija)
		{
			try {
				this.connect();
				
				String query = "UPDATE `agencija` SET `AgencijaID`=?, `menadzer`=?,`naziv`=?,`lokacija`=?,`opis`=?,`slika`=?,`pocetakRadnog`=?,`krajRadnog`=?"
						+ "		 WHERE `AgencijaID` =  " + agencija.getAgencijaID();
				PreparedStatement preparedStmt = conn.prepareStatement(query);
			     
				preparedStmt.setInt(1, agencija.getAgencijaID());
			      preparedStmt.setInt (2, agencija.getMenadzer());
			      preparedStmt.setString   (3, agencija.getNaziv());
			      preparedStmt.setString   (4, agencija.getLokacija());
			      preparedStmt.setString (5, agencija.getOpis());
			      preparedStmt.setString (6, agencija.getSlika());
			      preparedStmt.setString (7, agencija.getPocetakRadnog());
			      preparedStmt.setString (8 ,agencija.getKrajRadnog());
			      
			     
			      
			       preparedStmt.executeUpdate();
			       preparedStmt.close();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		public void deleteAgencija(String ID) {
			try {
				this.connect();

				stm.executeUpdate("UPDATE Agencija SET aktivan = 0 WHERE AgencijaID = " + ID);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				
			}
		}
		
		
		
		
		
		


}