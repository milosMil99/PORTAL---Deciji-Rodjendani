package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbs.*;
import Beans.*;
/**
 * Servlet implementation class ServletAdminKorisnik
 */
@WebServlet("/ServletAdminKorisnik")
public class ServletAdminKorisnik extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	KorisnikDBS korisnikDBS = new KorisnikDBS();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAdminKorisnik() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String opcija = request.getParameter("opcija");
		String korisnikID = request.getParameter("KorisnikID");
		
		if(opcija.equals("DELETE"))
		{
			korisnikDBS.deleteKorisnik(korisnikID);
			request.getRequestDispatcher("View/AdminPanel.jsp").forward(request, response);
			
			
		}
		else if(opcija.equals("UPDATE"))
		{
			Korisnik korisnik = korisnikDBS.getKorisnikbyID(korisnikID);
			request.setAttribute("Korisnik", korisnik);
			
			request.getRequestDispatcher("View/UpdateKorisnik.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ime= request.getParameter("ime");
		String prezime= request.getParameter("prezime");
		String email= request.getParameter("email");
		String lozinka= request.getParameter("lozinka");
		
		
		Korisnik korisnik = new Korisnik();
		korisnik.setIme(ime);
		korisnik.setPrezime(prezime);
		korisnik.setEmail(email);
		korisnik.setLozinka(lozinka);
		
		
			korisnikDBS.registracijaKorisnika(korisnik);
			
		
			request.getRequestDispatcher("View/AdminPanel.jsp").forward(request, response);
		
	}

}
