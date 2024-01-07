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
        <% if (errorMensaje != null && !errorMensaje.isEmpty()) { %>
        <div id="errorDiv" class="error">
            <%= errorMensaje %>
        </div>
        <% } %>
    </div>

    <!-- Mostrar el mensaje de acierto si está presente -->
    <div class="container d-flex align-items-center justify-content-center mt-2">
        <% String sucessMensaje = (String) request.getAttribute("sucess"); %>
        <% if (sucessMensaje != null && !sucessMensaje.isEmpty()) { %>
        <div id="sucessDiv" class="sucess">
            <%= sucessMensaje %>
        </div>
        <% } %>
    </div>

    <div class="tabla">
        <div class="filas">
            <div class="columnas">
                <table class="table table-striped">
                    <thead>

                        <!-- Bucador -->                               
                    <form action="peliculas" method="post">
                        <input type="hidden" name="accion" value="buscar">
                        <th scope="col" colspan="2">
                            <input type="search" name="busqueda" class="form-control mb-0" placeholder="Buscar película">
                        </th>
                        <th scope="col">
                            <button type="submit" class="btn btn-outline-info"> Buscar</button>
                        </th>
                    </form>

                    <th scope="col" colspan="5"></th>

                    <!-- Añadir película -->
                    <th scope="col" class="text-center align-middle">
                        <button type="button" class="btn btn-outline-primary" data-toggle="modal" data-target="#addModal"> Añadir </button>
                    </th>

                    <tr>
                        <th scope="col" class="text-center"></th>
                        <th scope="col" class="text-center">TITULO</th>
                        <th scope="col" class="text-center">SINOPSIS</th>
                        <th scope="col" class="text-center">AÑO</th>
                        <th scope="col" class="text-center">GENERO</th>
                        <th scope="col" class="text-center">DURACION</th>
                        <th scope="col" class="text-center">DIRECTOR</th>
                        <th scope="col" class="text-center">EDAD</th>
                        <th scope="col" class="text-center"></th>
                    </tr>
                    </thead>
                    <%
                        List<Pelicula> peliculas = (List) request.getAttribute("peliculas");
                        for(Pelicula pelicula : peliculas){
                    %>
                    <tbody>
                        <tr>
                            <td class="portada text-center align-middle"><img src="data:image/jpeg;base64,<%= pelicula.getImagenBase64() %>" alt="<%= pelicula.getTitulo() %>"></td>
                            <td class="titulo text-center align-middle"><p><%= pelicula.getTitulo()%></p></td>
                            <td class="sinopsis text-center align-middle"><p><%= pelicula.getSinopsis()%></p></td>
                            <td class="anio text-center align-middle"><p><%= pelicula.getAnio()%></p></td>
                            <td class="genero text-center align-middle"><p><%= pelicula.getGenero()%></p></td>
                            <td class="duracion text-center align-middle"><p><%= pelicula.getDuracion()%></p></td>
                            <td class="director text-center align-middle"><p><%= pelicula.getDirector()%></p></td>
                            <td class="clasificacionedad text-center align-middle"><p><%= pelicula.getClasificacionEdad()%></p></td>
                            <td class="text-center align-middle">
                                <!-- Simbolo editar -->
                                <button type="button" class="btn btn-outline-warning btnEditar mb-2" data-toggle="modal" data-target="#editModal<%=pelicula.getId()%>">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16">
                                    <path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
                                    </svg>
                                </button>

                                <!-- Simbolo eliminar -->
                                <form action="peliculas" method="post">
                                    <input type="hidden" name="accion" value="eliminar">
                                    <input type="hidden" name="titulo" value="<%=pelicula.getTitulo()%>">
                                    <button type="submit" class="btn btn-outline-danger btnEliminar mb-2">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16">
                                        <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5m-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5M4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5"/>
                                        </svg >
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </tbody>
                    <!-- Modal Editar-->
                    <div class="modal fade" id="editModal<%=pelicula.getId()%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content bg-dark">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLongTitle">Modificar Pelicula</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="container-form-modal">
                                        <form action="peliculas" method="post" enctype="multipart/form-data">
                                            <input type="hidden" name="accion" value="modificar">
                                            <!-- ID -->
                                            <input type="hidden" id="id" name="id" value="<%= pelicula.getId()%>"
                                            
                                            <!-- Titulo -->
                                            <label for="titulo">Título:</label>
                                            <input type="text" id="titulo" name="titulo" value="<%= pelicula.getTitulo()%>" required><br>

                                            <!-- Sinopsis -->
                                            <label for="sinopsis">Sinopsis:</label>
                                            <textarea id="sinopsis" name="sinopsis" rows="4" required><%= pelicula.getSinopsis()%></textarea><br>

                                            <!-- Año -->
                                            <label for="anio">Año:</label>
                                            <input type="number" id="anio" name="anio" value="<%= pelicula.getAnio()%>" required min="1895" max="2100"><br>
                                            <script>
                                                // Obtener el año actual
                                                const anioActual = new Date().getFullYear();

                                                // Establecer el año actual como el valor máximo
                                                document.getElementById('anio').setAttribute('max', anioActual);
                                            </script>

                                            <!-- Genero -->
                                            <label for="genero">Género:</label>
                                            <select id="genero" name="genero" value="<%= pelicula.getGenero()%>" required>
                                                <option value="accion">Acción</option>
                                                <option value="comedia">Comedia</option>
                                                <!-- Agrega más opciones según tus necesidades -->
                                            </select><br>

                                            <!-- Duración -->
                                            <label for="duracion">Duración (minutos):</label>
                                            <input type="number" id="duracion" name="duracion" value="<%= pelicula.getDuracion()%>" required><br>

                                            <!-- Director -->
                                            <label for="director">Director:</label>
                                            <input type="text" id="director" name="director" value="<%= pelicula.getDirector()%>" required><br>

                                            <!-- Clasificacion de Edad -->
                                            <label for="edad">Clasificación de Edad:</label>
                                            <select id="edad" name="edad" required>
                                                <option value="<%= pelicula.getClasificacionEdad()%>" selected hidden><%= pelicula.getClasificacionEdad()%></option>
                                                <option value="A">A</option>
                                                <option value="Ai">Ai</option>
                                                <option value="7">7</option>
                                                <option value="7i">7i</option>
                                                <option value="12">12</option>
                                                <option value="16">16</option>
                                                <option value="18">18</option>
                                                <option value="X">X</option>
                                            </select><br>

                                            <!-- Imagen de Portada -->
                                            <label for="portada">Portada:</label>
                                            <input type="file" id="portada" name="portada" accept="image/*"><br>

                                            <input type="submit" value="Confirmar Cambios">
                                        </form>
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

    <!-- Modal Añadir-->
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content bg-dark">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Añadir Pelicula</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="container-form-modal">
                        <form action="peliculas" method="post" enctype="multipart/form-data" class="w-100">
                            <input type="hidden" name="accion" value="crear">
                            <!-- Titulo -->
                            <label for="titulo">Título:</label>
                            <input type="text" id="titulo" name="titulo" required><br>

                            <!-- Sinopsis -->
                            <label for="sinopsis">Sinopsis:</label>
                            <textarea id="sinopsis" name="sinopsis" rows="4" required></textarea><br>

                            <!-- Año -->
                            <label for="anio">Año:</label>
                            <input type="number" id="anio" name="anio" required min="1895"><br>
                            <script>
                                // Obtener el año actual
                                const anioActual = new Date().getFullYear();

                                // Establecer el año actual como el valor máximo
                                document.getElementById('anio').setAttribute('max', anioActual);
                            </script>

                            <!-- Genero -->
                            <label for="genero">Género:</label>
                            <select id="genero" name="genero" required>
                                <option value="accion">Acción</option>
                                <option value="comedia">Comedia</option>
                                <!-- Agrega más opciones según tus necesidades -->
                            </select><br>

                            <!-- Duración -->
                            <label for="duracion">Duración (minutos):</label>
                            <input type="number" id="duracion" name="duracion" required><br>

                            <!-- Director -->
                            <label for="director">Director:</label>
                            <input type="text" id="director" name="director" required><br>

                            <!-- Clasificacion de Edad -->
                            <label for="edad">Clasificación de Edad:</label>
                            <select id="edad" name="edad" required>
                                <option value="A">A</option>
                                <option value="Ai">Ai</option>
                                <option value="7">7</option>
                                <option value="7i">7i</option>
                                <option value="12">12</option>
                                <option value="16">16</option>
                                <option value="18">18</option>
                                <option value="X">X</option>
                            </select><br>

                            <!-- Imagen de Portada -->
                            <label for="portada">Portada:</label>
                            <input type="file" id="portada" name="portada" accept="image/*" required><br>

                            <input type="submit" value="Añadir Película">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <my-footer></my-footer> <!-- Footer personalizado -->

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>