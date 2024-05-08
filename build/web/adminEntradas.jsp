<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.Usuario" %>
<%@ page import="Model.Entrada" %>
<%@ page import="DAO.UsuarioDAO" %>
<%@ page import="DAO.EntradaDAO" %>
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
        <link rel="stylesheet" type="text/css" href="../css/admin.css">
        <script type="text/javascript" src="../js/jquery-3.7.1.js"></script>
        <script type="text/javascript" src="../js/fadeout.js"></script>
        <script type="text/javascript" src="../js/htmlElements.js"></script>
        <title>CiNeXT</title>
    </head>
    <body>
    <my-header-admin></my-header-admin> <!-- Header personalizado -->
    <script type="text/javascript" src="../js/mobile.js"></script>

    <div class="tabla">
        <div class="filas">
            <div class="columnas">
                <table class="table table-striped">
                    <thead>
                    <th scope="col" colspan="8"></th>

                    <tr>
                        <th scope="col" class="text-center">ID</th>
                        <th scope="col" class="text-center">FECHA</th>
                        <th scope="col" class="text-center">FILA</th>
                        <th scope="col" class="text-center">COLUMNA</th>
                        <th scope="col" class="text-center">CLIENTE</th>
                        <th scope="col" class="text-center">EMAIL</th>
                        <th scope="col" class="text-center">SALA</th>
                        <th scope="col" class="text-center">PELICULA</th>
                    </tr>
                    </thead>
                    <%
                        List<Entrada> entradas = (List) request.getAttribute("entradas");
                        for(Entrada entrada : entradas){
                    %>
                    <tbody>
                        <tr>
                            <td class="id text-center align-middle"><p><%= entrada.getId()%></p></td>
                            <td class="id text-center align-middle"><p><%= entrada.getFecha()%></p></td>
                            <td class="filas text-center align-middle"><p><%= entrada.getFilas()%></p></td>
                            <td class="columnas text-center align-middle"><p><%= entrada.getColumnas()%></p></td>
                            <td class="columnas text-center align-middle"><p><%= entrada.getNombreUsuario()%></p></td>
                            <td class="tipo text-center align-middle"><p><%= entrada.getEmail()%></p></td>
                            <td class="tipo text-center align-middle"><p><%= entrada.getSala()%></p></td>
                            <td class="tipo text-center align-middle"><p><%= entrada.getTituloPelicula()%></p></td>
                        </tr>
                    </tbody>
                    <%}%>
                </table>
            </div>
        </div>
    </div>

    <my-footer></my-footer> <!-- Footer personalizado -->

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>