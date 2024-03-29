<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/Cine/css/style.css">
        <script type="text/javascript" src="/Cine/js/jquery-3.7.1.js"></script>
        <script type="text/javascript" src="/Cine/js/fadeout.js"></script>
        <script type="text/javascript" src="/Cine/js/htmlElements.js"></script>
        <title>CiNeXT</title>
    </head>
    <body>       
        <header>
            <div class="zoom">
                <a class="logo" href="/Cine"><img src="/Cine/img/logo.svg" alt="logo"></a>
            </div>
        </header>

        <section>
            <h2 class="login">Inicia sesión</h2>

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

            <form action="/Cine/login?accion=login" method="post">
                <label for="email">Correo Electrónico:</label>
                <input type="email" id="email" name="email" required>

                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>

                <button type="submit">Iniciar Sesión</button><br><br>
            </form>
            <p>¿No tienes cuenta? <a href="/Cine/register">Regístrate aquí</a></p><br><br>
        </section>
        
        <my-footer></my-footer>
    </body>
</html>