package dbs;

import java.sql.*;
import java.util.ArrayList;

import Beans.Ponuda;
import Beans.PonudeAgencija;

public class PonudaDBS {

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
		
		
		/*public ArrayList<Ponuda> getAllPonuda() throws SQLException{
			ArrayList<Ponuda>ponuda = new ArrayList<Ponuda>();
			rs=stm.executeQuery("SELECT * from ponuda");
			while (rs.next()) {
				ponuda.add(new Ponuda
							(rs.getInt("PonudaID"),
							 rs.getString("naziv"),
							 rs.getString("opis"),
							 rs.getString("cena")));
											

			}
			return ponuda;
			
		}*/
		
		
		
		public PonudeAgencija ponudeAgencija = new PonudeAgencija();
		public PonudeAgencijaDBS ponudeAgencijaDBS = new PonudeAgencijaDBS();
		
		public ArrayList<Ponuda> getByIDs(String ID)  {
			
			ArrayList<Integer> listaPonuda= ponudeAgencijaDBS.getbyID(ID);
			ArrayList<Ponuda> ponudaAgencije= new ArrayList<Ponuda>();
			ArrayList<Ponuda> ponuda = new ArrayList<Ponuda>();
			try {
				this.connect();
				rs = stm.executeQuery("SELECT * from ponuda");
				while (rs.next()) {
					ponuda.add(new Ponuda
								(rs.getInt("PonudaID"),
								 rs.getString("naziv"),
								 rs.getString("opis"),
								 rs.getString("cena"),
								 rs.getBoolean("aktivan")));
												

				}
				for(Ponuda p:ponuda) {
					for(Integer i:listaPonuda) {
						if(p.getPonudaID() == i) {
							ponudaAgencije.add(p);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
				
			return ponudaAgencije;
		}
		
		public Ponuda getByID(String id)
		{
			Ponuda p = new Ponuda();
			try {
				this.connect();
				rs = stm.executeQuery("SELECT * from ponuda WHERE PonudaID = " + id);
				if (rs.next()) {
					p = new Ponuda
								(rs.getInt("PonudaID"),
								 rs.getString("naziv"),
								 rs.getString("opis"),
								 rs.getString("cena"),
								 rs.getBoolean("aktivan"));
												
				}
				return p;
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			
			return p;
		}
		
		public Ponuda getByName(String naziv)
		{
			Ponuda p = new Ponuda();
			try {
				this.connect();
				rs = stm.executeQuery("SELECT * from ponuda WHERE naziv = '" + naziv + "'");
				if (rs.next()) {
					p = new Ponuda
								(rs.getInt("PonudaID"),
								 rs.getString("naziv"),
								 rs.getString("opis"),
								 rs.getString("cena"),
								 rs.getBoolean("aktivan"));
												
				}
				return p;
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			
			return p;
		}
		
		public void InsertPonuda(Ponuda p)
		{
			try {
				this.connect();
				String query = "INSERT INTO `ponuda`(`naziv`, `opis`, `cena`) "
						+ "VALUES (?,?,?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
			     
			      preparedStmt.setString (1, p.getNaziv());
			      preparedStmt.setString   (2, p.getOpis());
			      preparedStmt.setString   (3,p.getCena());
			  
			      
			      preparedStmt.execute();
			      
			     
			      conn.close();
				
				}
				catch (Exception e) {
					e.printStackTrace();
				}
	
		}
		
		public void DeletePonuda(String ponudaID)
		{
			try {
				this.connect();

				stm.executeUpdate("UPDATE Ponuda SET aktivan = 0 WHERE PonudaID = " + ponudaID);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				
			}
		}
		
		
		
}
