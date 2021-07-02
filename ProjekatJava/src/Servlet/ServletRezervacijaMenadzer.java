package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Agencija;
import Beans.Rezervacija;
import dbs.AgencijaDBS;
import dbs.RezervacijaDBS;

/**
 * Servlet implementation class ServletRezervacijaMenadzer
 */
@WebServlet("/ServletRezervacijaMenadzer")
public class ServletRezervacijaMenadzer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RezervacijaDBS rezervacijaDBS = new RezervacijaDBS();
	private AgencijaDBS agencijaDBS = new AgencijaDBS();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String RezID = (String) request.getParameter("RezervacijaID");
		String AgencijaID = (String) request.getParameter("AgencijaID");
		String opcija = (String) request.getParameter("opcija");
		
		if(opcija.equals("UPDATE"))
		{
			Rezervacija rez = (Rezervacija) rezervacijaDBS.getbyID(RezID);
			
			request.setAttribute("rezervacija", rez);
			
			request.getRequestDispatcher("View/UpdateRezervacija.jsp").forward(request, response);
		}
		else if(opcija.equals("DELETE"))
		{
			rezervacijaDBS.deleteRezByID(RezID);
			
			
			try {
				ArrayList<Rezervacija> rezervacije = (ArrayList<Rezervacija>) rezervacijaDBS.getAllRezForAgencija(AgencijaID);
				
				request.setAttribute("rezervacije", rezervacije);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			request.getRequestDispatcher("View/MenadzerPanel.jsp").forward(request, response);
		}
		
		
			
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rezervacijaID = (String) request.getParameter("rezID");
		String agencijaID = (String) request.getParameter("agencijaID");
		String korisnikID = (String) request.getParameter("korisnikID");
		String ponudaID = (String) request.getParameter("ponudaID");
		
		Agencija agencija = (Agencija) agencijaDBS.getbyID(agencijaID);
		
		String nazivAgencije = agencija.getNaziv();
		
		String imeKorisnika = (String) request.getParameter("ime");
		String prezimeKorisnika = (String) request.getParameter("prezime");
		String cena = (String) request.getParameter("cena");
		String datum = (String) request.getParameter("datum");
		String vreme = (String) request.getParameter("vreme");
		
		int rezervacijaSifra = Integer.parseInt(rezervacijaID);
		int agencijaSifra = Integer.parseInt(agencijaID);
		int KorisnikSifra =  Integer.parseInt(korisnikID);
		int PonudaSifra =  Integer.parseInt(ponudaID);
		
		
		Rezervacija rezervacija = new Rezervacija();
		
		rezervacija.setRezervacijaID(rezervacijaSifra);
		rezervacija.setAgencijaID(agencijaSifra);
		rezervacija.setKorisnikID(KorisnikSifra);
		rezervacija.setPonudaID(PonudaSifra);
		
		rezervacija.setNazivAgencije(nazivAgencije);
		rezervacija.setKorisnikIme(imeKorisnika);
		rezervacija.setKorisnikPrezime(prezimeKorisnika);
		rezervacija.setUkupnaCena(cena);
		rezervacija.setDatum(datum);
		rezervacija.setVreme(vreme);
		
		rezervacijaDBS.UpdateRez(rezervacija);
		
		try {
			ArrayList<Rezervacija> rezervacije = (ArrayList<Rezervacija>) rezervacijaDBS.getAllRezForAgencija(agencijaID);
			
			request.setAttribute("rezervacije", rezervacije);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		request.getRequestDispatcher("View/MenadzerPanel.jsp").forward(request, response);
	}

}
