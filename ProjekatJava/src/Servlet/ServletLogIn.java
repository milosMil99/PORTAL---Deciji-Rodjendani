package Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.Korisnik;
import dbs.KorisnikDBS;

/**
 * Servlet implementation class ServletLogIn
 */
@WebServlet("/ServletLogIn")
public class ServletLogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	KorisnikDBS korisnikDBS = new KorisnikDBS();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesija =  request.getSession();
		sesija.removeAttribute("koisnik");
		sesija.invalidate();
		
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		Korisnik korisnik = null;
		try {
			korisnik = korisnikDBS.validacijaKorisnika(email, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		if(korisnik != null)
		{
			int id = korisnik.getKorisnikID();
			
			HttpSession sesija =  request.getSession();
			
			String rola = korisnikDBS.getTipKorisnik(id);

			sesija.setAttribute("rola", rola);
			sesija.setAttribute("korisnik", korisnik);
			
			request.setAttribute("msg", "Uspesno");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else {
			request.setAttribute("msg", "Email ili sifra je pogresna!");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
	}

}
