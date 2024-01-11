<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.Usuario" %>
<%@ page import="Model.Pelicula" %>
<%@ page import="Model.Sesion" %>
<%@ page import="Model.Sala" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="java.util.BitSet" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:500&display=swap">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.1/css/all.css">
        <link rel="stylesheet" type="text/css" href="/Cine/css/style.css">
        <link rel="stylesheet" type="text/css" href="/Cine/css/navbar.css">
        <link rel="stylesheet" type="text/css" href="/Cine/css/asientos.css">
        <script type="text/javascript" src="/Cine/js/htmlElements.js"></script>
        <script type="text/javascript" src="/Cine/js/asientos.js"></script>
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

    <section>
        <h2>Seleccione sus asientos</h2>

        <%
        // Obtén la sesión y su información
        Sesion sesion = (Sesion) request.getAttribute("sesion");

        // Obtén la sala y su información
        Sala sala = (Sala) request.getAttribute("sala");

        // Obtener los asientos reservados de la sesión
        byte[] asientosReservados = sesion.getAsientosReservados();
        BitSet bitSet = (asientosReservados == null) ? new BitSet() : BitSet.valueOf(asientosReservados);

        // Número de filas y columnas de la sala
        int filas = sala.getFilas();
        int columnas = sala.getColumnas();
        %>

        <form action="/Cine/compra/entradas" method="post">
            <table>
                <% for (int row = 1; row <= filas; row++) { %>
                <tr>
                    <td>
                        <label>
                            <h1><%= row %></h1>
                        </label>
                    </td>
                    <% for (int col = 1; col <= columnas; col++) { 
                        // Calcular el índice del asiento actual en el BitSet
                        int index = (row - 1) * columnas + col - 1;
                        
                        // Verificar si el asiento está reservado
                        boolean reservado = bitSet.get(index);
                    %>
                    <td>
                        <label>
                            <input type="checkbox" name="asiento" value="<%= row + "-" + col %>" <% if (reservado) { %>disabled<% } %>>
                            <span class="asiento-icon <% if (reservado) { %>reservado<% } %>">
                                <img src="/Cine/img/seat.svg" alt="Asiento">
                            </span>
                        </label>
                    </td>
                    <% } %>
                </tr>
                <% } %>
            </table>

            <div class="asientos-seleccionados">
                <label for="asientos"><h3>Asientos seleccionados: </h3></label>
                <input type="text" id="asientos" name="asientos" readonly>
                <input type="hidden" id="idSesion" name="idSesion" value="<%=sesion.getId()%>" readonly>
            </div>

            <button type="submit">Continuar</button>
        </form>
    </section>

    <my-footer></my-footer> <!-- Footer personalizado -->
</body>
</html>