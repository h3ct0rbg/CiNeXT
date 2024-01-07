<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.Pelicula" %>
<%@ page import="DAO.PeliculaDAO" %>
<%@ page import="java.lang.Integer" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CiNeXT</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:500&display=swap">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.1/css/all.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/navbar.css">
        <link rel="stylesheet" type="text/css" href="css/peliculas.css">
        <link rel="stylesheet" type="text/css" href="css/detallesPelicula.css">
        <link rel="stylesheet" type="text/css" href="css/carrusel.css">
        <script type="text/javascript" src="js/htmlElements.js"></script>
    </head>
    <body>
    <my-header></my-header> <!-- Header personalizado -->
    <script type="text/javascript" src="js/mobile.js"></script>
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

    </section>
</body>
</html>