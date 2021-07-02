package dbs;

import java.sql.*;
import java.util.ArrayList;

import Beans.PonudeAgencija;

public class PonudeAgencijaDBS {

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
		}
		
		
		public ArrayList<Integer> getbyID(String ID) {
			PonudeAgencija ponuda = new PonudeAgencija();
			ArrayList<Integer> ponude=new ArrayList<Integer>();
			
			try {
				this.connect();
				rs=stm.executeQuery("SELECT * from ponudaagencija WHERE AgencijaID="+ID);
				while(rs.next()) {
					ponuda= new PonudeAgencija(
							rs.getInt("PonudaAgencijaID"),
							 rs.getInt("AgencijaID"),
							 rs.getInt("PonudaID"));
					ponude.add(ponuda.getPonudaID());
				}
				
				return ponude;
			} catch (Exception e) {
				e.printStackTrace();
				return ponude;
			}
			
		}
		
		public void InsertPonudaAgencije(String ponudaID, String agencijaID)
		{
			try {
				this.connect();
				String query = "INSERT INTO `ponudaagencija`(`AgencijaID`, `PonudaID`) "
						+ "VALUES (?,?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
			     
			      preparedStmt.setString (1, agencijaID);
			      preparedStmt.setString   (2, ponudaID);
			      
			  
			      
			      preparedStmt.execute();
			      
			     
			      conn.close();
				
				}
				catch (Exception e) {
					e.printStackTrace();
				}
	
		}
		
		public void DeletePonudaAgencije(String ponudaID, String agencijaID)
		{
			try {
				this.connect();
				String query = "DELETE FROM `ponudaagencija` WHERE `AgencijaID` = ? AND `PonudaID` = ? ";
						
				PreparedStatement preparedStmt = conn.prepareStatement(query);
			     
			      preparedStmt.setString (1, agencijaID);
			      preparedStmt.setString   (2, ponudaID);
			      
			  
			      
			      preparedStmt.execute();
			      
			     
			      conn.close();
				
				}
				catch (Exception e) {
					e.printStackTrace();
				}
		}
		
	
}
