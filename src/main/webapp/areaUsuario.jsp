<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.Usuario" %>
<%@ page import="DAO.UsuarioDAO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CineWeb - Área de Usuario</title>
        <link rel="stylesheet" href="areaUsuario.css">
    </head>
    <body>

        <header>
            <h1>Bienvenido a tu Área de Usuario</h1>
        </header>

        <section>
            <h2>Datos del Usuario</h2>
            <% 
                // Obtenemos el usuario de la sesión
                Usuario usuario = (Usuario) session.getAttribute("usuario");

                // Verificamos si el usuario está autenticado
                if (usuario != null) {
            %>
            <p><strong>Nombre:</strong> <%= usuario.getNombre() %></p>
            <p><strong>Email:</strong> <%= usuario.getEmail() %></p>
            <p><strong>Tipo de Usuario:</strong> <%= usuario.getTipoUsuario() %></p>

            <h2>Opciones</h2>
            <form action="CerrarSesionServlet" method="post">
                <button type="submit">Cerrar Sesión</button>
            </form>
            <% 
                } else {
            %>
            <p>No has iniciado sesión. <a href="index.jsp">Inicia sesión aquí</a></p>
            <% 
                }
            %>
        </section>

        <footer>
            <p>&copy; 2023 CineWeb</p>
        </footer>

    </body>
</html>