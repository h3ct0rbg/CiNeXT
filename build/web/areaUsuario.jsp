<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.Usuario" %>
<%@ page import="Model.Pelicula" %>
<%@ page import="DAO.UsuarioDAO" %>
<%@ page import="DAO.PeliculaDAO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CineWeb - Área de Usuario</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:500&display=swap">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.1/css/all.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/navbar.css">
        <link rel="stylesheet" type="text/css" href="css/peliculas.css">
        <script type="text/javascript" src="js/htmlElements.js"></script>
    </head>
    <body>
        <my-header></my-header> <!-- Header personalizado -->
        <script type="text/javascript" src="js/mobile.js"></script>
        <section>
            <%
                // Obtener la lista de películas desde la base de datos
                PeliculaDAO peliculaDAO = new PeliculaDAO();
                List<Pelicula> peliculas = peliculaDAO.getAllPeliculas();
                
                // Iterar sobre la lista de películas y mostrar la información
                for (Pelicula pelicula : peliculas) {
            %>
                <a href="detallesPelicula.jsp?titulo=<%= pelicula.getTitulo() %>">
                    <div class="pelicula">
                        <img src="data:image/jpeg;base64,<%= pelicula.getImagenBase64() %>" alt="<%= pelicula.getTitulo() %>">
                        <h3><%= pelicula.getTitulo() %></h3>
                        <p>Año: <%= pelicula.getAnio() %></p>
                    </div>
                </a>
            <%
                }
            %>
        </section>
        
        <my-footer></my-footer> <!-- Footer personalizado -->
    </body>
</html>