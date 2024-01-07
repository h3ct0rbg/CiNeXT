<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.Usuario" %>
<%@ page import="Model.Sala" %>
<%@ page import="DAO.UsuarioDAO" %>
<%@ page import="DAO.SalaDAO" %>
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
                    <th scope="col" colspan="4"></th>

                    <!-- Añadir película -->
                    <th scope="col" class="text-center align-middle">
                        <button type="button" class="btn btn-outline-primary" data-toggle="modal" data-target="#addModal"> Añadir </button>
                    </th>

                    <tr>
                        <th scope="col" class="text-center">SALA</th>
                        <th scope="col" class="text-center">FILAS</th>
                        <th scope="col" class="text-center">COLUMNAS</th>
                        <th scope="col" class="text-center">TIPO</th>
                        <th scope="col" class="text-center"></th>
                    </tr>
                    </thead>
                    <%
                        List<Sala> salas = (List) request.getAttribute("salas");
                        for(Sala sala : salas){
                    %>
                    <tbody>
                        <tr>
                            <td class="id text-center align-middle"><p><%= sala.getNombre()%></p></td>
                            <td class="filas text-center align-middle"><p><%= sala.getFilas()%></p></td>
                            <td class="columnas text-center align-middle"><p><%= sala.getColumnas()%></p></td>
                            <td class="tipo text-center align-middle"><p><%= sala.getTipo()%></p></td>
                            <td class="text-center align-middle">
                                <!-- Simbolo editar -->
                                <button type="button" class="btn btn-outline-warning btnEditar mb-2" data-toggle="modal" data-target="#editModal<%=sala.getId()%>">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16">
                                    <path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
                                    </svg>
                                </button>

                                <!-- Simbolo eliminar -->
                                <form action="salas" method="post">
                                    <input type="hidden" name="accion" value="eliminar">
                                    <input type="hidden" name="id" value="<%=sala.getId()%>">
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
                    <div class="modal fade" id="editModal<%=sala.getId()%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content bg-dark">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLongTitle">Modificar Sala</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="container-form-modal">
                                        <form action="salas" method="post">
                                            <input type="hidden" name="accion" value="modificar">
                                            <!-- ID -->
                                            <input type="hidden" id="id" name="id" value="<%= sala.getId()%>">

                                            <!-- Sala -->
                                            <label for="sala">Sala:</label>
                                            <input type="text" id="sala" name="sala" value="<%= sala.getNombre()%>" readonly><br>

                                            <!-- Filas -->
                                            <label for="filas">Filas:</label>
                                            <input type="number" id="filas" name="filas" value="<%= sala.getFilas()%>" required min="5" max="20"><br>

                                            <!-- Columnas -->
                                            <label for="columnas">Columnas:</label>
                                            <input type="number" id="columnas" name="columnas" value="<%= sala.getColumnas()%>" required min="5" max="20"><br>

                                            <!-- Tipo -->
                                            <label for="tipo">Tipo:</label>
                                            <select id="tipo" name="tipo" value="<%= sala.getTipo()%>" required>
                                                <option value="dolby">Dolby</option>
                                                <option value="imax">IMAX</option>
                                                <option value="isense">iSense</option>
                                                <option value="screenX">ScreenX</option>
                                                <option value="DBOX">DBOX</option>
                                            </select><br>

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
                    <h5 class="modal-title" id="exampleModalLongTitle">Añadir Sala</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="container-form-modal">
                        <form action="salas" method="post" class="w-100">
                            <input type="hidden" name="accion" value="crear">
                            
                            <!<!-- Sala -->
                            <label for="nombre">Sala:</label>
                            <input type="text" id="nombre" name="nombre" required><br>
                            
                            <!-- Filas -->
                            <label for="filas">Filas:</label>
                            <input type="number" id="filas" name="filas" required min="5" max="20"><br>

                            <!-- Columnas -->
                            <label for="columnas">Columnas:</label>
                            <input type="number" id="columnas" name="columnas" required min="5" max="20"><br>

                            <!-- Tipo -->
                            <label for="tipo">Tipo:</label>
                            <select id="tipo" name="tipo" required>
                                <option value="dolby">Dolby</option>
                                <option value="imax">IMAX</option>
                                <option value="isense">iSense</option>
                                <option value="screenX">ScreenX</option>
                                <option value="DBOX">DBOX</option>
                            </select><br>

                            <input type="submit" value="Crear Sala">
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