<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <script type="text/javascript" src="js/jquery-3.7.1.js"></script>
        <script type="text/javascript" src="js/fadeout.js"></script>
        <script type="text/javascript" src="js/htmlElements.js"></script>
        <title>CiNeXT - Login</title>
    </head>
    <body>       
        <header>
            <div class="zoom">
                <a class="logo" href="/Cine"><img src="img/logo.svg" alt="logo"></a>
            </div>
        </header>

        <section>
            <h2>Inicia sesión</h2>

            <!-- Mostrar el mensaje de error si está presente -->
            <div class="container">
                <% String errorMensaje = (String) request.getAttribute("error"); %>
                <% if (errorMensaje != null && !errorMensaje.isEmpty()) { %>
                <div id="errorDiv" class="error">
                    <%= errorMensaje %>
                </div>
                <% } %>
            </div>

            <!-- Mostrar el mensaje de acierto si está presente -->
            <div class="container">
                <% String sucessMensaje = (String) request.getAttribute("sucess"); %>
                <% if (sucessMensaje != null && !sucessMensaje.isEmpty()) { %>
                <div id="sucessDiv" class="sucess">
                    <%= sucessMensaje %>
                </div>
                <% } %>
            </div>

            <form action="Login" method="post">
                <label for="email">Correo Electrónico:</label>
                <input type="email" id="email" name="email" required>

                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>

                <button type="submit">Iniciar Sesión</button>
            </form>
            <p>¿No tienes cuenta? <a href="register.jsp">Regístrate aquí</a></p>
        </section>
        
        <my-footer></my-footer>
    </body>
</html>