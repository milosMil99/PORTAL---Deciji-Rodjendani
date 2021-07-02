<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Beans.*"%>
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
                        		else if(rola.equals("Korisnik"))
                        		{
                        			%>
                    	 			<li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded"  href="ServletLogIn">Profil</a></li>
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
        <div class="modal fade" id="myModal" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalCenterTitle"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLongTitle">Prijavi se</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form action="ServletLogIn" method="POST">
							  <div class="form-group">
							    <label for="exampleInputEmail1">Email:</label>
							    <input type="email" class="form-control" name="email" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Unesi email">
							  </div>
							  <div class="form-group">
							    <label for="exampleInputPassword1">Password:</label>
							    <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Unesi lozinku">
							  </div>
							  <br>
							  <% String msg = (String) request.getAttribute("msg");
							  		if(msg == null)
							  		{
							  			msg = "";
							  		}%>
								<p id="poruka">	<%= msg%> </p>
								<br><br>
							  <button type="submit" class="btn btn-info">Potvrdi</button>
							  <button type="button" class="btn btn-secondary" data-dismiss="modal">Zatvori</button>
							 
						</form>
					</div>
				</div>
			</div>
		</div>
        <header class="masthead bg-primary text-white text-center">
            <div class="container d-flex align-items-center flex-column">
               
                <h1 id="naslov" class="masthead-heading text-uppercase mb-0">Update rezervacije</h1>
                <!-- Icon Divider-->
                <div class="divider-custom divider-light">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <!-- Masthead Subheading-->
                
            </div>
        </header>
		<section class="page-section" id="contact">
            <div class="container">
                <!-- Contact Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">Rezervacija</h2>
                <!-- Icon Divider-->
                <div class="divider-custom">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <!-- Contact Section Form-->
                <div class="row justify-content-center">
                    <div class="col-lg-8 col-xl-7">
                        <% Rezervacija rezervacija = (Rezervacija) request.getAttribute("rezervacija");%>
                        
                       
                        	
                        <form  method="POST" action="ServletRezervacijaMenadzer">
                            <!-- Name input-->
                            
                           <div class="form-floating mb-3">
                                <input class="form-control" type="text" name="rezID" value="<%= rezervacija.getRezervacijaID() %>"  readonly/>
                                <label for="rezID">RezervacijaID</label>
                            </div>
                         
                          
                            <div class="form-floating mb-3">
                                <input class="form-control" type="text" name="korisnikID" value="<%= rezervacija.getKorisnikID() %>" />
                                <label for="korisnikID">KorisnikID</label>
                            </div>
                            
                            <div class="form-floating mb-3">
                                <input class="form-control" type="text" name="ponudaID" value="<%= rezervacija.getPonudaID() %>" />
                                <label for="ponudaID">ponudaID</label>
                            </div>
                            
                            <div class="form-floating mb-3">
                                <input class="form-control" name="agencijaID" type="text" value="<%= rezervacija.getAgencijaID() %>"  />
                                <label for="agencijaID">agencijaID</label>
                            </div>
                             
                            <div class="form-floating mb-3">
                                <input class="form-control" name="ime" type="text" value="<%= rezervacija.getKorisnikIme() %>"   />
                                <label for="ime">Ime korisnika</label>
                                
                            </div>
                            <!-- Email address input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" name="prezime" type="text" value="<%= rezervacija.getKorisnikPrezime() %>"  />
                                <label for="prezime">Prezime korisnika</label>
                                
                            </div>
                            
                            <div class="form-floating mb-3">
                                <input class="form-control" name="cena" type="text" value="<%=rezervacija.getUkupnaCena() %>"  />
                                <label for="cena">Ukupna Cena</label>
                            </div>
                            <!-- Password retype input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" name="datum" type="date" value="<%= rezervacija.getDatum() %>"  placeholder="Odaberite datum reodjendana"  />
                                <label for="datum">Datum</label>
                                <div class="invalid-feedback" data-sb-feedback="required">A date is required.</div>
                            </div>
                            <div class="form-floating mb-3">
                                <input class="form-control" name="vreme" type="text" value="<%= rezervacija.getVreme() %>"  placeholder="18"  />
                                <label for="vreme">Vreme</label>
                                <div class="invalid-feedback" data-sb-feedback="required">Time is required.</div>
                                
                            </div>
                          
                            <input class="btn btn-primary btn-xl " id="submitButton" value="Submit" type="submit"/>
                        </form>
                    </div>
                </div>
            </div>
        </section>
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
