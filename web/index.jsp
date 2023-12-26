<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CineWeb - Inicio de Sesión</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>

        <header>
            <h1>Bienvenido a CiNeXT</h1>
        </header>

        <section>
            <h2>Inicia sesión</h2>

            <!-- Mostrar el mensaje de error si está presente -->
            <div class="container">
            <% String errorMensaje = (String) request.getAttribute("errorLogin"); %>
            <% if (errorMensaje != null && !errorMensaje.isEmpty()) { %>
            <div id="errorDiv" class="error">
                <%= errorMensaje %>
            </div>
            <script>
                // Desvanecer el div después de 5 segundos
                setTimeout(function () {
                    var errorDiv = document.getElementById("errorDiv");
                    if (errorDiv) {
                        errorDiv.classList.add("fadeOut");
                        setTimeout(function () {
                            errorDiv.style.display = "none";
                        }, 500);
                    }
                }, 5000);
            </script>
            <% } %>
            </div>
            
            <!-- Mostrar el mensaje de registro correcto si está presente -->
            <div class="container">
            <% String regMensaje = (String) request.getAttribute("registered"); %>
            <% if (regMensaje != null && !regMensaje.isEmpty()) { %>
            <div id="notifyDiv" class="notify">
                <%= regMensaje %>
            </div>
            <script>
                // Desvanecer el div después de 5 segundos
                setTimeout(function () {
                    var notifyDiv = document.getElementById("notifyDiv");
                    if (notifyDiv) {
                        notifyDiv.classList.add("fadeOut");
                        setTimeout(function () {
                            notifyDiv.style.display = "none";
                        }, 500);
                    }
                }, 5000);
            </script>
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

        <footer>
            <p>&copy; 2023 CiNeXT</p>
        </footer>

    </body>
</html>
