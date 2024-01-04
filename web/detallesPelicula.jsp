<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.Pelicula" %>
<%@ page import="DAO.PeliculaDAO" %>
<%@ page import="java.lang.Integer" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CineWeb - Pelicula</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/navbar.css">
        <link rel="stylesheet" type="text/css" href="css/peliculas.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:500&display=swap">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.1/css/all.css">
        <script type="text/javascript" src="js/htmlElements.js"></script>
    </head>
    <body>
        <my-header></my-header> <!-- Header personalizado -->
        <script type="text/javascript" src="js/mobile.js"></script>
        <%
        // Obtener el título de la película desde la URL
        String titulo_ = request.getParameter("titulo");

        // Verificar si se proporcionó un título válido
        if (titulo_ != null && !titulo_.isEmpty()) {
            // Escapar caracteres especiales del título (opcional, pero recomendado)
            String titulo = java.net.URLDecoder.decode(titulo_, "UTF-8");

            // Obtener la película por título desde la base de datos
            PeliculaDAO peliculaDAO = new PeliculaDAO();
            Pelicula pelicula = peliculaDAO.getPeliculaByTitulo(titulo);

            // Verificar si se encontró la película
            if (pelicula != null) {
        %>
        <!-- Mostrar la información de la película -->
        <div class="detalles-pelicula">
            <div class="poster">
                <img src="data:image/jpeg;base64,<%= pelicula.getImagenBase64() %>" alt="<%= pelicula.getTitulo() %>">
            </div>
            <h2><%= pelicula.getTitulo() %></h2>
            <p>Año: <%= pelicula.getAnio() %></p>
            <p>Sinopsis: <%= pelicula.getSinopsis() %></p>
            <!-- Agrega más detalles según sea necesario -->
        </div>
        <%
            } else {
                // Manejar el caso en el que la película no se encuentra
        %>
        <p>La película no se encuentra.</p>
        <%
            }
        } else {
            // Manejar el caso en el que no se proporciona un título válido
        %>
        <p>Se requiere un título válido para mostrar los detalles de la película.</p>
        <%
        }
        %>
    </body>
</html>
