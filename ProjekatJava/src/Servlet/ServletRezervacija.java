package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.Agencija;
import Beans.Korisnik;
import Beans.Ponuda;
import Beans.Rezervacija;
import dbs.AgencijaDBS;
import dbs.KorisnikDBS;
import dbs.PonudaDBS;
import dbs.RezervacijaDBS;

/**
 * Servlet implementation class ServletRezervacija
 */
@WebServlet("/ServletRezervacija")
public class ServletRezervacija extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PonudaDBS ponudaDBS = new PonudaDBS();
	private AgencijaDBS agencijaDBS = new AgencijaDBS();
	private RezervacijaDBS rezervacijaDBS = new RezervacijaDBS();
	private KorisnikDBS korisnikDBS = new KorisnikDBS();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesija =  request.getSession();
		Korisnik korisnik = (Korisnik)sesija.getAttribute("korisnik");
		
		if(korisnik != null)
		{
			String ponuda = request.getParameter("ponuda");
			String agencijaID = request.getParameter("AgencijaID");
			
			Ponuda ponuda2 = ponudaDBS.getByID(ponuda);
			Agencija agencija = agencijaDBS.getbyID(agencijaID); 
			
			request.setAttribute("agencija", agencija);
			request.setAttribute("ponuda", ponuda2);
			request.setAttribute("korisnika", korisnik);
			
			request.getRequestDispatcher("View/Rezervacija.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("http://localhost:8080/JavaProjekat/#contact");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String agencijaID = (String) request.getParameter("agencijaID");
		String korisnikID = (String) request.getParameter("korisnikID");
		String ponudaID = (String) request.getParameter("ponudaID");
		
		String nazivAgencije = (String)request.getParameter("agencija");
		String imeKorisnika = (String) request.getParameter("ime");
		String prezimeKorisnika = (String) request.getParameter("prezime");
		String cena = (String) request.getParameter("cena");
		String datum = (String) request.getParameter("datum");
		String vreme = (String) request.getParameter("vreme");
		
		int agencijaSifra = Integer.parseInt(agencijaID);
		int KorisnikSifra =  Integer.parseInt(korisnikID);
		int PonudaSifra =  Integer.parseInt(ponudaID);
		
		Korisnik korisnik = korisnikDBS.getKorisnikbyID(korisnikID);
		
		String iskosristeniBodovi =  request.getParameter("bodoviZaPopust");
		if(iskosristeniBodovi != null)
		{
			double iskosristeniBodoviINT = Double.parseDouble(iskosristeniBodovi);
			if(iskosristeniBodoviINT > 0)
			{
				
				
				double cenaINT = Double.parseDouble(cena);
				double krajnjaCena = cenaINT - (iskosristeniBodoviINT * 100);
				String kranjaCenaString = String.valueOf(krajnjaCena);
				
				korisnikDBS.UpdateBodovi(korisnik, kranjaCenaString, iskosristeniBodovi);
				
				cena = kranjaCenaString;
			}
		}
		else {
			korisnikDBS.UpdateBodovi(korisnik, cena, iskosristeniBodovi);
		}
		
		
		
		
		
		
		Rezervacija rezervacija = new Rezervacija();
		
		rezervacija.setAgencijaID(agencijaSifra);
		rezervacija.setKorisnikID(KorisnikSifra);
		rezervacija.setPonudaID(PonudaSifra);
		
		rezervacija.setNazivAgencije(nazivAgencije);
		rezervacija.setKorisnikIme(imeKorisnika);
		rezervacija.setKorisnikPrezime(prezimeKorisnika);
		rezervacija.setUkupnaCena(cena);
		rezervacija.setDatum(datum);
		rezervacija.setVreme(vreme);
		
		rezervacijaDBS.unosRezervacije(rezervacija);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

}
