<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CineWeb - Registro</title>
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>

        <header>
            <h1>¡Regístrate en CineWeb!</h1>
        </header>
        
        <section>
            <h2>Registro</h2>
            
            <!-- Mostrar el mensaje de error si está presente -->
            <div class="error-container">
            <% String errorMensaje = (String) request.getAttribute("errorRegister"); %>
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
            
            <form action="RegisterServlet" method="post">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" required>

                <label for="email">Correo Electrónico:</label>
                <input type="email" id="email" name="email" required>

                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>

                <button type="submit">Registrarse</button>
            </form>
            <p>¿Ya tienes una cuenta? <a href="index.jsp">Inicia sesión aquí</a></p>
        </section>

        <footer>
            <p>&copy; 2023 CineWeb</p>
        </footer>

    </body>
</html>