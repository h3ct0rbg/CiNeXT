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
        <title>CiNeXT</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:500&display=swap">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.1/css/all.css">
        <link rel="stylesheet" type="text/css" href="/Cine/css/style.css">
        <link rel="stylesheet" type="text/css" href="/Cine/css/navbar.css">
        <link rel="stylesheet" type="text/css" href="/Cine/css/peliculas.css">
        <script type="text/javascript" src="/Cine/js/fadeout.js"></script>
        <script type="text/javascript" src="/Cine/js/htmlElements.js"></script>
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

    <section>
        <!-- Mostrar el mensaje de error si está presente -->
        <div class="container">
            <% 
            String errorMensaje = (String) request.getSession().getAttribute("error"); 
            request.getSession().removeAttribute("error");
            %>
            <% if (errorMensaje != null && !errorMensaje.isEmpty()) { %>
            <div id="errorDiv" class="error">
                <%= errorMensaje %>
            </div>
            <% } %>
        </div>

        <!-- Mostrar el mensaje de acierto si está presente -->
        <div class="container">
            <% 
            String sucessMensaje = (String) request.getSession().getAttribute("sucess"); 
            request.getSession().removeAttribute("sucess");
            %>
            <% if (sucessMensaje != null && !sucessMensaje.isEmpty()) { %>
            <div id="sucessDiv" class="sucess">
                <%= sucessMensaje %>
            </div>
            <% } %>
        </div>
        
        <%
            // Obtener la lista de películas desde la base de datos
            PeliculaDAO peliculaDAO = new PeliculaDAO();
            List<Pelicula> peliculas = peliculaDAO.getAllPeliculas();
                
            // Iterar sobre la lista de películas y mostrar la información
            for (Pelicula pelicula : peliculas) {
        %>
        <a href="/Cine/pelicula?titulo=<%= pelicula.getTitulo() %>">
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