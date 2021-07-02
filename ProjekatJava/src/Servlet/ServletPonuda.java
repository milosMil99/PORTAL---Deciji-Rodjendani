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
import Beans.Ponuda;
import Beans.Rezervacija;
import dbs.AgencijaDBS;
import dbs.PonudaDBS;
import dbs.PonudeAgencijaDBS;
import dbs.RezervacijaDBS;

/**
 *  implementation class ServletPonuda
 */
@WebServlet("/ServletPonuda")
public class ServletPonuda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PonudaDBS ponudaDBS = new PonudaDBS();
	private PonudeAgencijaDBS ponudeAgencijaDBS = new PonudeAgencijaDBS();
	private AgencijaDBS agencijaDBS = new AgencijaDBS();
	private RezervacijaDBS rezervacijaDBS = new RezervacijaDBS();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPonuda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ponudaID = request.getParameter("PonudaID");
		String agencijaID = request.getParameter("AgencijaID");
		if(request.getParameter("opcija").equals("DELETE"))
		{
			ponudaDBS.DeletePonuda(ponudaID);
			ponudeAgencijaDBS.DeletePonudaAgencije(ponudaID, agencijaID);
		}	
		try {
			ArrayList<Rezervacija> rezervacije = (ArrayList<Rezervacija>) rezervacijaDBS.getAllRezForAgencija(agencijaID);
			
			request.setAttribute("rezervacije", rezervacije);
			
			ArrayList<Ponuda> ponude = (ArrayList<Ponuda>) ponudaDBS.getByIDs(agencijaID);
			request.setAttribute("ponude", ponude);
			
			Agencija agencija2 = agencijaDBS.getbyID(agencijaID);
			request.setAttribute("Agencija", agencija2);
			
			request.getRequestDispatcher("View/MenadzerPanel.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Ponuda ponuda = new Ponuda();
		
		String agencijaID = request.getParameter("agencijaID");
		ponuda.setNaziv(request.getParameter("naziv"));
		ponuda.setOpis(request.getParameter("opis"));
		ponuda.setCena(request.getParameter("cena"));
		
		ponudaDBS.InsertPonuda(ponuda);
		
		Ponuda ponuda2 = new Ponuda();
		
		ponuda2 = ponudaDBS.getByName(ponuda.getNaziv());
		
		String ponuda2ID = String.valueOf(ponuda2.getPonudaID());
		
		ponudeAgencijaDBS.InsertPonudaAgencije(ponuda2ID, agencijaID);
		
		try {
			ArrayList<Rezervacija> rezervacije = (ArrayList<Rezervacija>) rezervacijaDBS.getAllRezForAgencija(agencijaID);
			
			request.setAttribute("rezervacije", rezervacije);
			
			ArrayList<Ponuda> ponude = (ArrayList<Ponuda>) ponudaDBS.getByIDs(agencijaID);
			request.setAttribute("ponude", ponude);
			
			Agencija agencija2 = agencijaDBS.getbyID(agencijaID);
			request.setAttribute("Agencija", agencija2);
			
			request.getRequestDispatcher("View/MenadzerPanel.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
