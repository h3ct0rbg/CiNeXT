<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CineWeb - Gestion Admin Peliculas Insertar</title>
        <link rel="stylesheet" href="css/areaPelicula.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
    <body>
        <h1><center>Insertar Peliculas</center></h1>
        <div class="error-container">
            <% String errorMensaje = (String) request.getAttribute("errorRegister"); %>
            <% if (errorMensaje != null && !errorMensaje.isEmpty()) {%>
            <div id="errorDiv" class="error">
                <%= errorMensaje%>
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
            <% }%>
        </div>
        <div class="container-form">
            <form action="PeliculaServlet?accion=guardar" method="post" >
                <div class="form-group">
                    <label for="exampleFormControlInput1">Titulo</label>
                    <input class="form-control" id="titulo" name="titulo" placeholder="Harry Potter y la Orden del Fénix" required="">
                </div>
                <div class="form-group">
                    <label for="exampleFormControlTextarea1">Sinopsis</label>
                    <textarea type="text" class="form-control" id="sinopsis" name="sinopsis" rows="3" required=""></textarea>
                </div>
                <div class="form-group">
                    <label for="exampleFormControlInput1">Página Oficial</label>
                    <input type="text" class="form-control" id="paginaoficial" name="paginaoficial" placeholder="https://www.wizardingworld.com/" required="">
                </div>
                <div class="form-group">
                    <label for="exampleFormControlInput1">Año</label>
                    <input type="date" class="form-control" id="anio" name="anio" placeholder="2007" required="">
                </div>
                <div class="form-group">
                    <label for="exampleFormControlInput1">Género</label>
                    <input type="text" class="form-control" id="genero" name="genero" placeholder="Ciencia Ficcion" required="">
                </div>
                <div class="form-group">
                    <label for="exampleFormControlInput1">Duración</label>
                    <input type="text" class="form-control" id="duracion" name="duracion" placeholder="2:18:45" required="">
                </div>
                <div class="form-group">
                    <label for="exampleFormControlInput1">Distribuidora</label>
                    <input type="text" class="form-control" id="distribuidora" name="distribuidora" placeholder="Electronic Arts" required="">
                </div>
                <div class="form-group">
                    <label for="exampleFormControlInput1">Director</label>
                    <input type="text" class="form-control" id="director" name="director" placeholder="David Yates" required="">
                </div>
                <div class="form-group">
                    <label for="exampleFormControlInput1">Clasificación</label>
                    <input class="form-control" id="clasificacion" name="clasificacion" placeholder="+12" required="">
                </div>
                <div class="form-group">
                    <label for="exampleFormControlTextarea1">Otros Datos</label>
                    <textarea type="text" class="form-control" id="otrosdatos" name="otrosdatos" rows="3" required=""></textarea>
                </div>
                <button type="submit" name="guardar" class="btn btn-primary">Guardar</button>
            </form>
        </div>
    </body>
</html>
