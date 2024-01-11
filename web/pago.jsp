<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.Usuario" %>
<%@ page import="Model.Sesion" %>
<%@ page import="DAO.SesionDAO" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="/Cine/css/style.css">
        <link rel="stylesheet" type="text/css" href="/Cine/css/navbar.css">
        <link rel="stylesheet" type="text/css" href="/Cine/css/pago.css">
        <script type="text/javascript" src="/Cine/js/jquery-3.7.1.js"></script>
        <script type="text/javascript" src="/Cine/js/htmlElements.js"></script>
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

        String asientos = (String) request.getAttribute("asientos");
        int idSesion = (int) request.getAttribute("idSesion");

        int numeroEntradas = asientos.split("-").length - 1;
        
        double precioUnitario = 9.99;
        double precioTotal = numeroEntradas * precioUnitario;
        String precioTotalFormateado = String.format("%.2f", precioTotal);
        %>
    <section>
        <div class="container">
            <h2>Información de Pago</h2>

            <!-- Información de las entradas, precios, etc. -->
            <div>
                <p>Entradas seleccionadas: <%= numeroEntradas %></p>
                <p>Precio entrada: 9,99€</p>
                <p>Total: <%= precioTotalFormateado %>€</p>
                <!-- Agrega más información según tus necesidades -->
            </div>

            <!-- Formulario de pago -->
            <form action="/Cine/pago/entradas?accion=pagar" method="post">
                <!-- Input para el número de tarjeta de crédito -->
                <div class="form-group">
                    <input type="hidden" id="asientos" name="asientos" value="<%= asientos %>" readonly>
                    <input type="hidden" id="idSesion" name="idSesion" value="<%= idSesion %>" readonly>
                    <label for="tarjetaCredito">Número de Tarjeta de Crédito</label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="tarjetaCredito" name="tarjetaCredito" placeholder="0000-0000-0000-0000" pattern="\d{4}-\d{4}-\d{4}-\d{4}" maxlength="19" oninput="formatTarjeta(this)" required>
                        <div class="input-group-append">
                            <!-- Imagen SVG de la tarjeta de crédito -->
                            <span class="input-group-text" id="basic-addon2">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-credit-card" viewBox="0 0 16 16">
                                <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm2-1a1 1 0 0 0-1 1v1h14V4a1 1 0 0 0-1-1zm13 4H1v5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1z"/>
                                <path d="M2 10a1 1 0 0 1 1-1h1a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1z"/>
                                </svg>
                            </span>
                        </div>
                    </div>
                </div>
                <!-- Botón de pago -->
                <button type="submit" class="btn btn-primary">Pagar</button>
            </form>
        </div>
    </section>
    
    <my-footer></my-footer> <!-- Footer personalizado -->

    <script>
        // Función para formatear el número de tarjeta y gestionar el color del borde del div
        function formatTarjeta(input) {
            // Eliminar caracteres no numéricos
            var numero = input.value.replace(/\D/g, '');

            // Aplicar formato con guiones
            if (numero.length > 0) {
                numero = numero.match(new RegExp('.{1,4}', 'g')).join('-');
            }

            // Asignar el valor formateado de vuelta al input
            input.value = numero;

            // Obtener el div del grupo
            var divGroup = input.closest('.input-group');

            // Cambiar el color del borde del div basado en la longitud del número
            if (numero.length < 19) {
                divGroup.style.border = '4px solid red';
                divGroup.style.borderRadius = '8px';                
                divGroup.style.color = 'white';
            } else {
                divGroup.style.border = '4px solid green';
                divGroup.style.borderRadius = '8px';
                divGroup.style.color = 'white';
            }
        }
    </script>

</body>
</html>