package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbs.AgencijaDBS;
import Beans.Agencija;
import Beans.Ponuda;
import dbs.AgencijaDBS;
import dbs.PonudaDBS;

/**
 * Servlet implementation class ServletAdminAgencija
 */
@WebServlet("/ServletAdminAgencija")
public class ServletAdminAgencija extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAdminAgencija() {
        super();
        // TODO Auto-generated constructor stub
    }
    private AgencijaDBS agencijaDBS = new AgencijaDBS();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String agencijaID = request.getParameter("AgencijaID");
		
		agencijaDBS.deleteAgencija(agencijaID);
		
		request.getRequestDispatcher("View/AdminPanel.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String menadzerString =(String) request.getParameter("menadzer");
		String naziv =(String) request.getParameter("naziv");
		String lokacija =(String) request.getParameter("lokacija");
		String opis =(String) request.getParameter("opis");
		String slika =(String) request.getParameter("slika");
		String pocetakRadnog =(String) request.getParameter("pocetakRadnog");
		String krajRadnog =(String) request.getParameter("krajRadnog");
		
		int menadzer= Integer.parseInt(menadzerString);
		
		Agencija agencija = new Agencija();
		
		agencija.setMenadzer(menadzer);
		agencija.setNaziv(naziv);
		agencija.setLokacija(lokacija);
		agencija.setOpis(opis);
		agencija.setSlika(slika);
		agencija.setPocetakRadnog(pocetakRadnog);
		agencija.setKrajRadnog(krajRadnog);
		
		
		agencijaDBS.insertAgencije(agencija);
		request.getRequestDispatcher("View/AdminPanel.jsp").forward(request, response);
	}

}
