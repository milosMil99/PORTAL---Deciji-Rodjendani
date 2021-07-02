package Servlet;

import java.awt.Point;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Beans.Agencija;
import Beans.Ponuda;
import Beans.Rezervacija;
import dbs.AgencijaDBS;
import dbs.PonudaDBS;
import dbs.RezervacijaDBS;

/**
 * Servlet implementation class ServletMenadzer
 */
@WebServlet("/ServletMenadzer")
public class ServletMenadzer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AgencijaDBS agencijaDBS = new AgencijaDBS();
	private RezervacijaDBS rezervacijaDBS = new RezervacijaDBS();
	private PonudaDBS ponudaDBS = new PonudaDBS();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ID = (String) request.getParameter("KorisnikID");
		String agencijaID;

		
		Agencija agencija = (Agencija) agencijaDBS.getbyMenadzerID(ID);
		
		agencijaID = String.valueOf(agencija.getAgencijaID());
		
		try {
			ArrayList<Rezervacija> rezervacije = (ArrayList<Rezervacija>) rezervacijaDBS.getAllRezForAgencija(agencijaID);
			
			request.setAttribute("rezervacije", rezervacije);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("KorisnikID", ID);
		request.setAttribute("Agencija", agencija);
		
		ArrayList<Ponuda> ponude = (ArrayList<Ponuda>) ponudaDBS.getByIDs(agencijaID);
		request.setAttribute("ponude", ponude);
		
		request.getRequestDispatcher("View/MenadzerPanel.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
			String naziv = request.getParameter("naziv"); 
			String lokacija = request.getParameter("lokacija"); 
			String opis = request.getParameter("opis"); 
			String slika = request.getParameter("slika"); 
			String pocetakRadnog = request.getParameter("pocetakRadnog"); 
			String krajRadnog = request.getParameter("krajRadnog"); 
			String agencijaIDStr =  request.getParameter("agencijaID") ;
			String menadzerStr = request.getParameter("mendazer");
			
			int agencijaID  = Integer.valueOf(agencijaIDStr);
			int menadzer = Integer.valueOf(menadzerStr);
		try {
			
			
			
			Agencija agencija = new Agencija(agencijaID,menadzer, naziv, lokacija, opis, slika, pocetakRadnog, krajRadnog, true);
			
			agencijaDBS.UpdateAgencija(agencija);
			
			Agencija agencija2 = agencijaDBS.getbyID(agencijaIDStr);
			
			request.setAttribute("Agencija", agencija2);
			
			
			ArrayList<Rezervacija> rezervacije = (ArrayList<Rezervacija>) rezervacijaDBS.getAllRezForAgencija(agencijaIDStr);
			ArrayList<Ponuda> ponude = (ArrayList<Ponuda>) ponudaDBS.getByIDs(agencijaIDStr);
			request.setAttribute("ponude", ponude);
			
			request.setAttribute("rezervacije", rezervacije);
			request.getRequestDispatcher("View/MenadzerPanel.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
		
		
		
		
		
		
		
	}

}
