<%@page import="DAO.InformeDAO"%>
<%@page import="Model.Informe"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.Usuario" %>
<%@ page import="Model.Pelicula" %>
<%@ page import="DAO.UsuarioDAO" %>
<%@ page import="DAO.PeliculaDAO" %>
<%@ page import="java.util.List" %>

<%
    // Verificar si el usuario tiene el rol de "Admin"
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null || !usuario.getTipoUsuario().equals("Admin")) {
        // Si el usuario no es un administrador, redirigir o mostrar un mensaje de error
        response.sendRedirect("/Cine/login");
    }
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Montserrat:500&display=swap">
        <link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.12.1/css/all.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="../css/style.css">
        <link rel="stylesheet" type="text/css" href="../css/navbar.css">
        <link rel="stylesheet" type="text/css" href="../css/peliculas.css">
        <link rel="stylesheet" type="text/css" href="../css/admin.css">
        <script type="text/javascript" src="../js/jquery-3.7.1.js"></script>
        <script type="text/javascript" src="../js/fadeout.js"></script>
        <script type="text/javascript" src="../js/htmlElements.js"></script>
        <title>CiNeXT</title>
    </head>
    <body>
    <my-header-admin></my-header-admin> <!-- Header personalizado -->
    <script type="text/javascript" src="../js/mobile.js"></script>

    <!-- Mostrar el mensaje de error si está presente -->
    <div class="container d-flex align-items-center justify-content-center mt-2">
        <% String errorMensaje = (String) request.getAttribute("error"); %>
        <% if (errorMensaje != null && !errorMensaje.isEmpty()) {%>
        <div id="errorDiv" class="error">
            <%= errorMensaje%>
        </div>
        <% } %>
    </div>

    <!-- Mostrar el mensaje de acierto si está presente -->
    <div class="container d-flex align-items-center justify-content-center mt-2">
        <% String sucessMensaje = (String) request.getAttribute("sucess"); %>
        <% if (sucessMensaje != null && !sucessMensaje.isEmpty()) {%>
        <div id="sucessDiv" class="sucess">
            <%= sucessMensaje%>
        </div>
        <% } %>
    </div>

    <div class="tabla">
        <div class="filas">
            <div class="columnas">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col" class="text-center"></th>
                            <th scope="col" class="text-center">TITULO</th>
                            <th scope="col" class="text-center">SINOPSIS</th>
                            <th scope="col" class="text-center">GENERO 
                                <!-- Boton para ordenar por genero -->
                                <form action="/Cine/admin/informes?accion=ordenarGenero" method="post">
                                    <button type="submit" class="filter"> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-down-square-fill" viewBox="0 0 16 16">
                                        <path d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm4 4a.5.5 0 0 0-.374.832l4 4.5a.5.5 0 0 0 .748 0l4-4.5A.5.5 0 0 0 12 6z"/>
                                        </svg></button>
                                </form>
                            </th>
                            <th scope="col" class="text-center">ASISTENTES</th>
                            <th scope="col" class="text-center">PUNTUACION
                                <form action="/Cine/admin/informes?accion=ordenarPuntuacion" method="post">
                                    <button type="submit" class="filter"> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-down-square-fill" viewBox="0 0 16 16">
                                        <path d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm4 4a.5.5 0 0 0-.374.832l4 4.5a.5.5 0 0 0 .748 0l4-4.5A.5.5 0 0 0 12 6z"/>
                                        </svg></button>
                                </form>
                            </th>
                            <th scope="col" class="text-center">COMENTARIOS</th>
                        </tr>
                    </thead>
                    <%
                        List<Informe> peliculas = (List) request.getAttribute("peliculas");
                        for (Informe informe : peliculas) {

                            float puntuacion = informe.getPuntuacionPelicula();
                            String puntuacionStr;
                            if (puntuacion == 0) {//una pelicula unicamente es cero cuando no es puntuada
                                puntuacionStr = "No hay datos registrados ";
                            } else {
                                puntuacionStr = puntuacion + "";
                            }
                    %>
                    <tbody>
                        <tr>
                            <td class="portada text-center align-middle"><img src="data:image/jpeg;base64,<%= informe.getImagenBase64()%>" alt="<%= informe.getTituloPelicula()%>"></td>
                            <td class="tituloPelicula text-center align-middle"><p><%= informe.getTituloPelicula()%></p></td>
                            <td class="sinopsis text-center align-middle"><p><%= informe.getSinopsisPelicula()%></p></td>
                            <td class="genero text-center align-middle"><p><%= informe.getGeneroPelicula()%></p></td>
                            <td class="anio text-center align-middle"><p><%= informe.getNumAsistentes()%></p></td>
                            <td class="genero text-center align-middle"><p><%= puntuacionStr%></p></td>
                            <td class="text-center align-middle">
                                <!-- Simbolo comentarios -->
                                <button type="button" class="btn btn-outline-primary btnEditar mb-2" data-toggle="modal" data-target="#editModal<%=informe.getIdPelicula()%>">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat-dots-fill" viewBox="0 0 16 16">
                                    <path d="M16 8c0 3.866-3.582 7-8 7a9 9 0 0 1-2.347-.306c-.584.296-1.925.864-4.181 1.234-.2.032-.352-.176-.273-.362.354-.836.674-1.95.77-2.966C.744 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7M5 8a1 1 0 1 0-2 0 1 1 0 0 0 2 0m4 0a1 1 0 1 0-2 0 1 1 0 0 0 2 0m3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2"/>
                                    </svg>
                                </button>
                            </td>
                        </tr>
                    </tbody>
                    <!-- Modal Editar-->
                    <div class="modal fade" id="editModal<%=informe.getIdPelicula()%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content bg-dark">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLongTitle">Comentarios</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="container-form-modal">
                                               <!-- Obtenemos el id de la pelicula -->
                                                <%
                                                   InformeDAO dao = new InformeDAO();
                                                   List<Informe> comentarios = dao.mostrarComentarios(informe.getTituloPelicula());

                                                   for (Informe inf : comentarios) {

                                                %>
                                                <div class="caja">
                                                    <h5><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
                                                        <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
                                                        </svg> <%=inf.getNombreUsuario()%></h5> <b><label><%=inf.getPuntuacionPelicula()%></label></b>
                                                        <%=inf.getComentarios()%>
                                                    <hr>
                                                </div>
                                                <%
                                            }
                                        %>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%}%>
                </table>
            </div>
        </div>
    </div>

    <my-footer></my-footer> <!-- Footer personalizado -->

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>