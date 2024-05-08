<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.Usuario" %>
<%@ page import="Model.Pelicula" %>
<%@ page import="Model.Sesion" %>
<%@ page import="Model.Comentario" %>
<%@ page import="DAO.PeliculaDAO" %>
<%@ page import="DAO.SesionDAO" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Time" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:500&display=swap">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.1/css/all.css">
        <link rel="stylesheet" type="text/css" href="/Cine/css/style.css">
        <link rel="stylesheet" type="text/css" href="/Cine/css/navbar.css">
        <link rel="stylesheet" type="text/css" href="/Cine/css/peliculas.css">
        <link rel="stylesheet" type="text/css" href="/Cine/css/detallesPelicula.css">
        <link rel="stylesheet" type="text/css" href="/Cine/css/carrusel.css">
        <link rel="stylesheet" type="text/css" href="/Cine/css/comentarios.css">
        <script type="text/javascript" src="/Cine/js/htmlElements.js"></script>
        <title>CiNeXT</title>
    </head>
    <body>

        <% 
        // Verificar si la sesión está iniciada
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
        %>
    <my-header></my-header> <!-- Header personalizado -->
        <%
            } else {
        %>
    <my-header-logged></my-header-logged> <!-- Header personalizado para sesión iniciada -->
        <%
            }
        %>

    <script type="text/javascript" src="/Cine/js/mobile.js"></script>


    <%
        Pelicula pelicula = (Pelicula) request.getAttribute("pelicula");
    %>
    <!-- Mostrar la información de la película -->
    <section>
        <div class="detalles-pelicula">
            <div class="poster">
                <img src="data:image/jpeg;base64,<%= pelicula.getImagenBase64() %>" alt="<%= pelicula.getTitulo() %>">
            </div>
            <div class="info">
                <h1><%= pelicula.getTitulo() %></h1>
                <div class="anio">
                    <h3>Año:</h3>
                    <p><%= pelicula.getAnio() %></p>
                </div>
                <div class="sinopsis">
                    <h3>Sinopsis:</h3>
                    <p><%= pelicula.getSinopsis() %></p>
                </div>
            </div>
        </div>

        <div class="carrusel">
            <button id="anterior">&lt;</button>
            <div class="dias-container">
                <!-- Días se generarán dinámicamente con JavaScript (carrusel.js) -->
            </div>
            <button id="siguiente">&gt;</button>
        </div>
        <script src="js/carrusel.js"></script>

        <div class="sesiones-container">
            <%
                List<Sesion> sesiones = (List) request.getAttribute("sesiones");
                for(Sesion sesion : sesiones){
            %>
            <a href="/Cine/pelicula/sesion?idSesion=<%= sesion.getId()%>">
                <div class="sesion" id="<%= sesion.getFecha() %>">
                    <%
                    // Obtén la hora de la sesión
                    Time horaSesion = sesion.getHora();

                    // Crea un objeto SimpleDateFormat para formatear la hora
                    SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");

                    // Formatea la hora y obtén la representación en formato "00:00"
                    String hora = formatoHora.format(horaSesion);
                    %>
                    <p><%= hora %></p>
                    <p><%= sesion.getNombreSala() %></p>
                </div>
            </a>
            <%    
                }
            %>
        </div>
        
        <div class="container-comments">
            <%
            List<Comentario> comentarios = (List) request.getAttribute("comentarios");
            for(Comentario comentario : comentarios){
            %>
            <div class="comments">
                
                <div class="info-comments">
                   
                    <div class="header">
                        <h4>Usuario Anónimo</h4>
                        <h5><%= comentario.getPuntuacion() %></h5>
                    </div>
                    
                    <p><%= comentario.getComentario() %></p>
                </div>
                
            </div>
            <%}%>
        </div>

    </section>
</body>
</html>