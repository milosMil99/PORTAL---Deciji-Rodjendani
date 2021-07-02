package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Korisnik;
import dbs.*;
/**
 * Servlet implementation class ServletUpdateKorisnik
 */
@WebServlet("/ServletUpdateKorisnik")
public class ServletUpdateKorisnik extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	KorisnikDBS korisnikDBS = new KorisnikDBS();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String korisnikIDString =request.getParameter("korisnikID");
		String tipKorisnikaIdString=request.getParameter("tipKorisnikaID");
		String ime= request.getParameter("ime");
		String prezime= request.getParameter("prezime");
		String email= request.getParameter("email");
		String lozinka= request.getParameter("lozinka");
		String bodovi= request.getParameter("bodovi");
		
		
		int korisnikID= Integer.parseInt(korisnikIDString);
		int tipKorisnikaID = Integer.parseInt(tipKorisnikaIdString);
		
		Korisnik korisnik = new Korisnik();
		korisnik.setKorisnikID(korisnikID);
		korisnik.setTipKorisnikaID(tipKorisnikaID);
		korisnik.setIme(ime);
		korisnik.setPrezime(prezime);
		korisnik.setEmail(email);
		korisnik.setLozinka(lozinka);
		korisnik.setBodovi(bodovi);
		
		
		korisnikDBS.UpdateKorisnik(korisnik);
		request.getRequestDispatcher("View/AdminPanel.jsp").forward(request, response);
	}

}
