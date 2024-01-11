<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.Usuario" %>
<%@ page import="Model.Pelicula" %>
<%@ page import="Model.Sala" %>
<%@ page import="Model.Sesion" %>
<%@ page import="DAO.UsuarioDAO" %>
<%@ page import="DAO.PeliculaDAO" %>
<%@ page import="DAO.SalaDAO" %>
<%@ page import="DAO.SesionDAO" %>
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
                    <tr>
                    <!-- Bucador -->                               
                    <form action="sesiones" method="post">
                        <input type="hidden" name="accion" value="buscar">
                        <th scope="col">
                            <input type="search" name="busqueda" class="form-control mb-0" placeholder="Buscar sesión">
                        </th>
                        <th scope="col">
                            <button type="submit" class="btn btn-outline-info"> Buscar</button>
                        </th>
                    </form>

                    <th scope="col" colspan="2"></th>

                    <!-- Añadir película -->
                    <th scope="col" class="text-center align-middle">
                        <button type="button" class="btn btn-outline-primary" data-toggle="modal" data-target="#addModal"> Añadir </button>
                    </th>
                    </tr>
                    <tr>
                        <th scope="col" class="text-center">FECHA</th>
                        <th scope="col" class="text-center">HORA</th>
                        <th scope="col" class="text-center">SALA</th>
                        <th scope="col" class="text-center">PELICULA</th>
                        <th scope="col" class="text-center"></th>
                    </tr>
                    </thead>
                    <%
                        List<Sesion> sesiones = (List) request.getAttribute("sesiones");
                        for(Sesion sesion : sesiones){
                    %>
                    <tbody>
                        <tr>
                            <td class="fecha text-center align-middle"><p><%= sesion.getFecha()%></p></td>
                            <td class="hora text-center align-middle"><p><%= sesion.getHora()%></p></td>
                            <td class="nombreSala text-center align-middle"><p><%= sesion.getNombreSala()%></p></td>
                            <td class="nombrePelicula text-center align-middle"><p><%= sesion.getNombrePelicula()%></p></td>
                            <td class="text-center align-middle">
                                <!-- Simbolo editar -->
                                <button type="button" class="btn btn-outline-warning btnEditar" data-toggle="modal" data-target="#editModal<%=sesion.getId()%>">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16">
                                    <path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
                                    </svg>
                                </button>

                                <!-- Simbolo eliminar -->
                                <form action="sesiones" method="post">
                                    <input type="hidden" name="accion" value="eliminar">
                                    <input type="hidden" name="id" value="<%=sesion.getId()%>">
                                    <button type="submit" class="btn btn-outline-danger btnEliminar">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16">
                                        <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5m-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5M4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5"/>
                                        </svg >
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </tbody>
                    <!-- Modal Editar-->
                    <div class="modal fade" id="editModal<%=sesion.getId()%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content bg-dark">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLongTitle">Modificar Sesion</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <%
                                    SalaDAO salaDAO = new SalaDAO();
                                    PeliculaDAO peliculaDAO = new PeliculaDAO();
                                %>
                                <div class="modal-body">
                                    <div class="container-form-modal">
                                        <form action="sesiones" method="post">
                                            <input type="hidden" name="accion" value="modificar">
                                            <!-- ID -->
                                            <input type="hidden" id="id" name="id" value="<%= sesion.getId()%>">

                                            <!-- Fecha -->
                                            <label for="fecha">Fecha:</label>
                                            <input type="date" id="fecha" name="fecha" value="<%= sesion.getFecha()%>" required><br>
                                            <script>
                                                // Obtener la fecha de hoy
                                                const hoy = new Date();
                                                // Obtener la fecha dentro de un año
                                                const nextAnio = new Date();
                                                nextAnio.setFullYear(nextAnio.getFullYear() + 1);
                                                // Formatear las fechas en formato ISO (YYYY-MM-DD) para su uso en el atributo 'min' y 'max'
                                                const formattedHoy = hoy.toISOString().split('T')[0];
                                                const formattedNextAnio = nextAnio.toISOString().split('T')[0];
                                                // Establecer la fecha de hoy como el valor mínimo
                                                document.getElementById('fecha').setAttribute('min', formattedHoy);
                                                // Establecer la fecha dentro de un año como el valor máximo
                                                document.getElementById('fecha').setAttribute('max', formattedNextAnio);
                                            </script>


                                            <!-- Hora -->
                                            <label for="hora">Hora:</label>
                                            <input type="time" id="hora" name="hora" value="<%= sesion.getHora()%>" min="15:00" max="23:30" required><br>

                                            <!-- Sala -->
                                            <label for="salaId">Sala:</label>
                                            <select name="salaId" id="salaId" class="form-select">
                                                <option value="<%= sesion.getSalaId()%>" selected hidden><%= sesion.getNombreSala()%></option>
                                                <%
                                                    List<Sala> salas = salaDAO.mostrarSalas();
                                                    for (Sala sala : salas) {
                                                %>
                                                <option value="<%=sala.getId()%>"><%=sala.getNombre()%></option>
                                                <%
                                                    }
                                                %>
                                            </select>

                                            <!-- Pelicula -->
                                            <label for="peliculaId">Pelicula</label>
                                            <select name="peliculaId" id="peliculaId" value="<%= sesion.getNombrePelicula()%>" class="form-select">
                                                <option value="<%= sesion.getPeliculaId()%>" selected hidden><%= sesion.getNombrePelicula()%></option>
                                                <%
                                                    List<Pelicula> peliculas = peliculaDAO.mostrarPeliculas();
                                                    for (Pelicula pelicula : peliculas) {

                                                %>
                                                <option value="<%=pelicula.getId()%>"><%=pelicula.getTitulo()%></option>
                                                <%
                                                    }
                                                %>
                                            </select>

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
    <%
        SalaDAO salaDAO = new SalaDAO();
        PeliculaDAO peliculaDAO = new PeliculaDAO();
    %>
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
                        <form action="sesiones" method="post" class="w-100">
                            <input type="hidden" name="accion" value="crear">
                            <!-- ID -->
                            <input type="hidden" id="id" name="id">

                            <!-- Fecha -->
                            <label for="fecha">Fecha:</label>
                            <input type="date" id="fecha" name="fecha" required><br>
                            <script>
                                // Obtener la fecha de hoy
                                const hoy = new Date();
                                // Obtener la fecha dentro de un año
                                const nextAnio = new Date();
                                nextAnio.setFullYear(nextAnio.getFullYear() + 1);
                                // Formatear las fechas en formato ISO (YYYY-MM-DD) para su uso en el atributo 'min' y 'max'
                                const formattedHoy = hoy.toISOString().split('T')[0];
                                const formattedNextAnio = nextAnio.toISOString().split('T')[0];
                                // Establecer la fecha de hoy como el valor mínimo
                                document.getElementById('fecha').setAttribute('min', formattedHoy);
                                // Establecer la fecha dentro de un año como el valor máximo
                                document.getElementById('fecha').setAttribute('max', formattedNextAnio);
                            </script>

                            <!-- Hora -->
                            <label for="hora">Hora:</label>
                            <input type="time" id="hora" name="hora" min="15:00" max="23:30" required><br>

                            <!-- Sala -->
                            <label for="salaId">Sala:</label>
                            <select name="salaId" id="salaId" class="form-select" required>
                                <option value="">Selecciona una sala...</option>
                                <%
                                    List<Sala> salas = salaDAO.mostrarSalas();
                                    for (Sala sala : salas) {
                                %>
                                <option value="<%=sala.getId()%>"><%=sala.getNombre()%></option>
                                <%
                                    }
                                %>
                            </select>

                            <!-- Pelicula -->
                            <label for="peliculaId">Pelicula</label>
                            <select name="peliculaId" id="peliculaId" class="form-select" required>
                                <option value="">Selecciona una pelicula...</option>
                                <%
                                    List<Pelicula> peliculas = peliculaDAO.mostrarPeliculas();
                                    for (Pelicula pelicula : peliculas) {

                                %>
                                <option value="<%=pelicula.getId()%>"><%=pelicula.getTitulo()%></option>
                                <%
                                    }
                                %>
                            </select>

                            <input type="submit" value="Crear Sesión">
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