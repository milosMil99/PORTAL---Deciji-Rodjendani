<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Beans.*"%>
<%@page import= "java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Organizacija rodjendana</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.3/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body id="page-top">
        
       <!-- Navigation-->
        <nav class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top" id="mainNav">
            <div class="container">
                <a class="navbar-brand" href="index.jsp">Deciji rodjendani</a>
                <button class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item mx-0 mx-lg-1"> <form action="AgencijaServlet" method="POST"> <input type="submit" class="nav-link py-3 px-0 px-lg-3 rounded" value="Agencije" /></form></li>
                        
                        <%
                        Korisnik kor = (Korisnik) session.getAttribute("korisnik");
                        String rola = (String) session.getAttribute("rola");
                        String ime = "LogIn";
                        if(kor != null)
                    	{
                    		ime = kor.getIme();
                    		%>
                    		<li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="View/Korisnik.jsp?KorisnikID=<%= kor.getKorisnikID() %>"><%= ime  %></a></li>
                    		<%
                    	}
                        else
                        {
                        	%>
                        	<li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="#" data-toggle="modal" data-target="#myModal"
                         	><%= ime  %></a></li>
                        	<%
                        }%>
                        
                         <% if(kor != null)
                        	 {
                        	 	%>
                        	 		<li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded"  href="ServletLogIn">LogOut</a></li>
                        	 	<%
                        	 }%>
                        	 
                        <% if(kor != null)
                        	{
                        		if(rola.equals("Menadzer"))
                        		{
                        			%>
                    	 			<li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded"  href="ServletMenadzer?KorisnikID=<%= kor.getKorisnikID()%>">Menadzer panel</a></li>
                    	 			<%
                        		}    		
                        		else if(rola.equals("Admin"))
                        		{
                        			%>
                    	 			<li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded"  href="View/AdminPanel.jsp">Admin panel</a></li>
                    	 			<%
                        		}
                        	
                       		}
                        	
                        %>
                    </ul>
                </div>
            </div>
        </nav>
       

		
        <!-- Masthead-->
        <header class="masthead bg-primary text-white text-center">
            <div class="container d-flex align-items-center flex-column">
                
                <!-- Masthead Heading-->
                <h1 class="masthead-heading text-uppercase mb-0">Menadzer panel</h1>
                <!-- Icon Divider-->
                <div class="divider-custom divider-light">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <!-- Masthead Subheading-->
                
            </div>
        </header>
        
        <h2>Rezervacije</h2>
        
        <% ArrayList<Rezervacija> rezervacije = (ArrayList<Rezervacija>) request.getAttribute("rezervacije"); %>
        
		<div class="container">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<table class="table table-dark">
					  <thead>
					    <tr>
					      
					      <th scope="col">Rezervacija ID</th>
					      <th scope="col">Korisnik Ime</th>
					      <th scope="col">Korisnik Prezime</th>
					      <th scope="col">Datum</th>
					      <th scope="col">Vreme</th>
					      <th scope="col">Cena</th>
					      <th scope="col">Update</th>
					      <th scope="col">Delete</th>
					    </tr>
					  </thead>
					  <tbody>
					  <% 
					  	for(Rezervacija r : rezervacije)
					  	{
					  		if(r.GetAktivan() == true)
					  		{
				  	
					  	%>  
					  	
						    <tr>
						    
							      
							      <td><%= r.getRezervacijaID() %></td>
							      <td><%= r.getKorisnikIme() %></td>
							      <td><%= r.getKorisnikPrezime() %></td>
							      <td><%= r.getDatum() %></td>
							      <td><%= r.getVreme() %></td>
							      <td><%= r.getUkupnaCena() %></td>
							      <td><a href="ServletRezervacijaMenadzer?RezervacijaID=<%= r.getRezervacijaID()%>&AgencijaID=<%=r.getAgencijaID()%>&opcija=UPDATE">UPDATE</a></td>
							      <td><a href="ServletRezervacijaMenadzer?RezervacijaID=<%= r.getRezervacijaID()%>&AgencijaID=<%=r.getAgencijaID()%>&opcija=DELETE"">DELETE</a></td>	
						      			
						    </tr>
						 
					    <%
					  		}
					  	}
					    %> 
					  </tbody>
				</table>
				</div>
				<div class="col-md-2"></div>
			</div>
		</div>
       
       <h2>Agencija</h2>
       <% Agencija agencija = (Agencija) request.getAttribute("Agencija"); %>
       <div class="container">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<form action="ServletMenadzer" method="POST">
						<input type="hidden" name="agencijaID" value="<%= agencija.getAgencijaID() %>" />
						<input type="hidden" name="mendazer" value="<%= agencija.getMenadzer() %>" />
						 <div class="form-group">
						    <label for="naziv">Naziv</label>
						    <input type="text" class="form-control" name="naziv"  value="<%= agencija.getNaziv()%>">
						  </div>
						  <div class="form-group">
						    <label for="lokacija">Lokacija</label>
						    <input type="text" class="form-control" name="lokacija" value="<%= agencija.getLokacija()%>">
						  </div>
						  <div class="form-group">
						    <label for="opis">Opis</label>
						    <textarea  class="form-control" name="opis" ><%= agencija.getOpis()%> </textarea>
						  </div>
						  <div class="form-group">
						    <label for="slika">Slika</label>
						    <input type="text" class="form-control" name="slika" value="<%= agencija.getSlika()%>">
						  </div>
						  <div class="form-group">
						    <label for="pocetakRadnog">pocetakRadnog</label>
						    <input type="text" class="form-control" name="pocetakRadnog" value="<%= agencija.getPocetakRadnog()%>">
						  </div>
						  <div class="form-group">
						    <label for="krajRadnog">krajRadnog</label>
						    <input type="text" class="form-control" name="krajRadnog" value="<%= agencija.getKrajRadnog()%>">
						  </div>
						  
						  
						  <button type="submit" class="btn btn-primary">Update</button>
						
					</form>
				</div>
				<div class="col-md-2"></div>
			</div>
		</div>
		
		<h2>Ponude</h2>
      <% ArrayList<Ponuda> ponude = (ArrayList<Ponuda>) request.getAttribute("ponude"); %>
       <div class="container">
			<div class="row">
				<div class="col-md-2">
					
				</div>
				<div class="col-md-8">
					<table class="table table-dark">
					  <thead>
					    <tr>
					      
					      <th scope="col">Ponuda ID</th>
					      <th scope="col">Naziv</th>
					      <th scope="col">Opis</th>
					      <th scope="col">Cena</th>					       
					      <th scope="col">Delete</th>
					    </tr>
					  </thead>
					  <tbody>
					  <% 
					  	for(Ponuda p : ponude)
					  	{
					  		if(p.GetAktivan() == true)
					  		{
				  	
					  	%>  
					  	
						    <tr>
						    
							      
							      <td><%= p.getPonudaID() %></td>
							      <td><%= p.getNaziv() %></td>
							      <td><%= p.getOpis() %></td>
							      <td><%= p.getCena() %></td>
							      <td><a href="ServletPonuda?PonudaID=<%= p.getPonudaID()%>&AgencijaID=<%=agencija.getAgencijaID()%>&opcija=DELETE"">DELETE</a></td>	
						      			
						    </tr>
						 
					    <%
					  		}
					  	}
					    %> 
					  </tbody>
				</table>
				</div>
				<div class="col-md-2">
					<h4>Insert ponuda</h4>
					<a href="View/InsertPonuda.jsp?AgencijaID=<%= agencija.getAgencijaID()%>">Insert</a>
				</div>
			</div>
		</div>
        
        
        <!-- Footer-->
       <!-- Footer-->
        <footer class="footer text-center">
            <div class="container">
                <div class="row">
                    <!-- Footer Location-->
                    <div class="col-lg-4 mb-5 mb-lg-0">
                        <h4 class="text-uppercase mb-4">Lokacija</h4>
                        <p class="lead mb-0">
                            Savski Nasip 
                            <br />
                            Beograd 11000
                        </p>
                    </div>
                    <!-- Footer Social Icons-->
                    <div class="col-lg-4 mb-5 mb-lg-0">
                        <h4 class="text-uppercase mb-4">Drustvene mreze</h4>
                        <a class="btn btn-outline-light btn-social mx-1" href="#!"><i class="fab fa-fw fa-facebook-f"></i></a>
                        <a class="btn btn-outline-light btn-social mx-1" href="#!"><i class="fab fa-fw fa-twitter"></i></a>
                        <a class="btn btn-outline-light btn-social mx-1" href="#!"><i class="fab fa-fw fa-linkedin-in"></i></a>
                        <a class="btn btn-outline-light btn-social mx-1" href="#!"><i class="fab fa-fw fa-dribbble"></i></a>
                    </div>
                    <!-- Footer About Text-->
                    <div class="col-lg-4">
                        <h4 class="text-uppercase mb-4">About Freelancer</h4>
                        <p class="lead mb-0">
                            Freelance is a free to use, MIT licensed Bootstrap theme created by
                            <a href="http://startbootstrap.com">Start Bootstrap</a>
                            .
                        </p>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Copyright Section-->
        <div class="copyright py-4 text-center text-white">
            <div class="container"><small>Milos Milosavljevic &copy; Deciji Rodjendani 2021 ITS</small></div>
        </div>
       
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
        
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" ></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" ></script>
        
    </body>
    	
</html>
