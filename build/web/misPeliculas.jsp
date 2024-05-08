<%@page import="Model.Comentario"%>
<%@page import="DAO.ComentarioDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.Usuario" %>
<%@ page import="Model.Pelicula" %>
<%@ page import="Model.Comentario" %>
<%@ page import="DAO.PeliculaDAO" %>
<%@ page import="DAO.ComentarioDAO" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%
    // Verificar si el usuario tiene el rol de "Admin"
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null || !usuario.getTipoUsuario().equals("Cliente")) {
        // Si el usuario no es un administrador, redirigir o mostrar un mensaje de error
        response.sendRedirect("/Cine");
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CineWeb - Info Usuario</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:500&display=swap">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.1/css/all.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/navbar.css">
        <link rel="stylesheet" type="text/css" href="css/peliculas.css">
        <link rel="stylesheet" type="text/css" href="css/comentarios.css">
        <script type="text/javascript" src="js/htmlElements.js"></script>

    </head>
    <body>
        <% 
            // Verificar si la sesión está iniciada
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

    <script type="text/javascript" src="js/mobile.js"></script>
    
    <section class="text-left">
    <%
        // Obtener el título de la película desde la URL
        List<Pelicula> peliculas = (List) request.getAttribute("peliculas");

        for (Pelicula pelicula : peliculas) {
            ComentarioDAO comentarioDAO = new ComentarioDAO();
            List<Comentario> comentarios = comentarioDAO.getComentariosByPelicula(pelicula.getId(), usuario.getEmail());
            // Si la lista de comentarios está vacía, añadir un comentario vacío
            if (comentarios == null || comentarios.isEmpty()) {
                comentarios = new ArrayList<>();
                comentarios.add(new Comentario()); // Añadir comentario vacío
                for (Comentario comentario : comentarios) {
            
    %>
    <!-- Mostrar la información de la película -->
    <div class="detalles-pelicula">
        <div class="container-comments">
            <div class="comments">
                <h2 class="title"><%= pelicula.getTitulo()%></h2>
                <div class="pelicula-container">
                    <div class="foto-portada">
                        <img class="poster" src="data:image/jpeg;base64,<%= pelicula.getImagenBase64()%>" alt="<%= pelicula.getTitulo()%>">
                    </div>
                
                    <div class="info-comments">

                        <div class="header">
                            <h4><%= usuario.getNombre() %></h4>
                            <h5>?</h5>
                        </div>

                        <p>Aún no has comentado esta película</p>
                    </div>
                </div>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addModal<%=pelicula.getId()%>"> Añadir Valoracion</button>
            </div>
        </div>
                
        <!-- Modal para añadir comentarios-->
        <div class="modal fade" id="addModal<%=pelicula.getId()%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content bg-dark">
                    <div class="modal-header">
                        <h5 class="modal-title text-dark" id="exampleModalLongTitle">Insertar Valoracion</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-form-modal">
                            <form action="/Cine/mis-peliculas?accion=añadir" method="post" class="w-100">
                                <%-- PeliculaID --%>
                                <input class="form-control" id="peliculaId" name="peliculaId" value="<%=pelicula.getId()%>" hidden="">
                                
                                <%-- Email --%>
                                <input class="form-control" id="email" name="email" value="<%=usuario.getEmail()%>" hidden="">

                                <%-- Puntuacion --%>
                                <div class="form-group">
                                    <label for="puntuacion">Puntuación:</label>
                                    <input type="number" class="form-control" id="puntuacion" name="puntuacion" min="1" max="10" step="0.25" required="">
                                </div>

                                <%-- Comentario --%>
                                <div class="form-group">
                                    <label for="comentario">Comentario:</label>
                                    <textarea type="text" class="form-control" id="comentario" name="comentario" rows="3" required=""></textarea>
                                </div>

                                <%-- Boton --%>
                                <div class="modal-footer">
                                    <button type="sumbit" class="btn btn-primary">Guardar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%
        }} else {
        for (Comentario comentario : comentarios) {
        %>
        <!-- Mostrar la información de la película -->
    <div class="detalles-pelicula">
        <div class="container-comments">
            <div class="comments">
                <h2 class="title"><%= pelicula.getTitulo()%></h2>
                <div class="pelicula-container">
                    <div class="foto-portada">
                        <img class="poster" src="data:image/jpeg;base64,<%= pelicula.getImagenBase64()%>" alt="<%= pelicula.getTitulo()%>">
                    </div>
                    <div class="info-comments">

                        <div class="header">
                            <h4><%= usuario.getNombre() %></h4>
                            <h5><%= comentario.getPuntuacion() %></h5>
                        </div>

                        <p><%= comentario.getComentario() %></p>
                    </div>
                </div>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modModal<%=pelicula.getId()%>"> Modificar Valoracion</button>
            </div>
        </div>
                
        <!-- Modal para modificar comentarios-->
        <div class="modal fade" id="modModal<%=pelicula.getId()%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content bg-dark">
                    <div class="modal-header">
                        <h5 class="modal-title text-dark" id="exampleModalLongTitle">Insertar Valoracion</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-form-modal">
                            <form action="/Cine/mis-peliculas?accion=modificar" method="post" class="w-100">
                                <%-- ID --%>
                                <input class="form-control" id="id" name="id" value="<%=comentario.getId()%>" hidden="">
                                
                                <%-- PeliculaID --%>
                                <input class="form-control" id="peliculaId" name="peliculaId" value="<%=pelicula.getId()%>" hidden="">
                                
                                <%-- Email --%>
                                <input class="form-control" id="email" name="email" value="<%=usuario.getEmail()%>" hidden="">
                                
                                <%-- Puntuacion --%>
                                <div class="form-group">
                                    <label for="puntuacion">Puntuación:</label>
                                    <input type="number" class="form-control" id="puntuacion" name="puntuacion" value="<%=comentario.getPuntuacion()%>" min="1" max="10" step="0.25" required="">
                                </div>

                                <%-- Comentario --%>
                                <div class="form-group">
                                    <label for="comentario">Comentario:</label>
                                    <textarea type="text" class="form-control" id="comentario" name="comentario" rows="3" required=""><%=comentario.getComentario()%></textarea>
                                </div>

                                <%-- Boton --%>
                                <div class="modal-footer">
                                    <button type="sumbit" class="btn btn-primary">Guardar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%
        }}}
        %>   
    </div>
    </section>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</body>
</html>