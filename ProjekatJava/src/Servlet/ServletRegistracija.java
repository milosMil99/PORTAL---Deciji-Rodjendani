package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.*;
import dbs.*;
/**
 * Servlet implementation class ServletRegistracija
 */
@WebServlet("/ServletRegistracija")
public class ServletRegistracija extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KorisnikDBS korisnikDBS = new KorisnikDBS();
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
		// TODO Auto-generated method stub
		String ime= request.getParameter("ime");
		String prezime= request.getParameter("prezime");
		String email= request.getParameter("email");
		String lozinka1= request.getParameter("lozinka1");
		String lozinka2= request.getParameter("lozinka2");
		
		Korisnik korisnik = new Korisnik();
		korisnik.setIme(ime);
		korisnik.setPrezime(prezime);
		korisnik.setEmail(email);
		korisnik.setLozinka(lozinka1);
		
		if(lozinka2.equals(lozinka1)) {
			korisnikDBS.registracijaKorisnika(korisnik);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("http://localhost:8080/JavaProjekat/#contact").forward(request, response);
		}
	}

}
