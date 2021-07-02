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
import dbs.AgencijaDBS;
import dbs.PonudaDBS;

/**
 *  implementation class AgencijaServlet
 */
@WebServlet("/AgencijaServlet")
public class AgencijaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
		private AgencijaDBS agencijaDBS = new AgencijaDBS();
		private PonudaDBS ponudaDBS  = new PonudaDBS();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgencijaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Agencija agencija = new Agencija();
		
		String id = request.getParameter("AgencijaID");
		try {
			agencijaDBS.connect();
			agencija = agencijaDBS.getbyID(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Ponuda> ponude;
		try {
			ponude = ponudaDBS.getByIDs(id);
			request.setAttribute("ponude", ponude);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("agencija", agencija);
		
		request.getRequestDispatcher("View/Agencija.jsp").forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Agencija> listaAgencija = new ArrayList<Agencija>();

		try {
			agencijaDBS.connect();
			listaAgencija = agencijaDBS.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("listaAgencija", listaAgencija);
		request.getRequestDispatcher("View/prikazAgencija.jsp").forward(request, response);
	}

}
