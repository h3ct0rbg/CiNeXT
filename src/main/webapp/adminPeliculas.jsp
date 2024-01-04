<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Model.Pelicula"%>
<%@page import="DAO.PeliculaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CineWeb - Gestion Admin Peliculas</title>
        <link rel="stylesheet" href="css/areaUsuario.css">
        <link rel="stylesheet" href="css/areaPelicula.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
    <body>
        <!-- Barra de navegacion para acceder a las distintas funcionalidades del administrador -->
        <nav class="navbar navbar-dark bg-dark">
            
            <!-- Link para direccionar al home  -->
            <a href="areaAdmin.jsp" style="color:white" class="navbar-toggler"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-house-fill" viewBox="0 0 16 16">
                <path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L8 2.207l6.646 6.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293z"/>
                <path d="m8 3.293 6 6V13.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5V9.293l6-6Z"/>
                </svg></span> Home</a>
            
            <!-- Link para direccionar a Peliculas  -->
            <form action="PeliculaServlet?accion=mostrar" method="post">
                <button type="submit" style="color:white" class="navbar-toggler"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-camera-reels-fill" viewBox="0 0 16 16">
                    <path d="M6 3a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                    <path d="M9 6a3 3 0 1 1 0-6 3 3 0 0 1 0 6"/>
                    <path d="M9 6h.5a2 2 0 0 1 1.983 1.738l3.11-1.382A1 1 0 0 1 16 7.269v7.462a1 1 0 0 1-1.406.913l-3.111-1.382A2 2 0 0 1 9.5 16H2a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2z"/>
                    </svg></span> Peliculas</button>
            </form>
            <!-- Link para direccionar a Salas  -->
            <a href="adminSalas.jsp" style="color:white" class="navbar-toggler"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-badge-4k-fill" viewBox="0 0 16 16">
                <path d="M3.577 8.9v.03h1.828V5.898h-.062a46.781 46.781 0 0 0-1.766 3.001z"/>
                <path d="M2 2a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2zm2.372 3.715.435-.714h1.71v3.93h.733v.957h-.733V11H5.405V9.888H2.5v-.971c.574-1.077 1.225-2.142 1.872-3.202m7.73-.714h1.306l-2.14 2.584L13.5 11h-1.428l-1.679-2.624-.615.7V11H8.59V5.001h1.187v2.686h.057L12.102 5z"/>
                </svg></span> Salas</a>
            
             <!-- Link para direccionar a Entradas  -->
            <a href="adminEntradas.jsp" href="adminEntradas.jsp" style="color:white" class="navbar-toggler"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-ticket-perforated-fill" viewBox="0 0 16 16">
                <path d="M0 4.5A1.5 1.5 0 0 1 1.5 3h13A1.5 1.5 0 0 1 16 4.5V6a.5.5 0 0 1-.5.5 1.5 1.5 0 0 0 0 3 .5.5 0 0 1 .5.5v1.5a1.5 1.5 0 0 1-1.5 1.5h-13A1.5 1.5 0 0 1 0 11.5V10a.5.5 0 0 1 .5-.5 1.5 1.5 0 1 0 0-3A.5.5 0 0 1 0 6zm4-1v1h1v-1zm1 3v-1H4v1zm7 0v-1h-1v1zm-1-2h1v-1h-1zm-6 3H4v1h1zm7 1v-1h-1v1zm-7 1H4v1h1zm7 1v-1h-1v1zm-8 1v1h1v-1zm7 1h1v-1h-1z"/>
                </svg></span> Entradas</a>
             
             <!-- Link para direccionar a Reservas  -->
            <a href="adminReservas.jsp" style="color:white" class="navbar-toggler"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-clipboard2-check-fill" viewBox="0 0 16 16">
                <path d="M10 .5a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5.5.5 0 0 1-.5.5.5.5 0 0 0-.5.5V2a.5.5 0 0 0 .5.5h5A.5.5 0 0 0 11 2v-.5a.5.5 0 0 0-.5-.5.5.5 0 0 1-.5-.5"/>
                <path d="M4.085 1H3.5A1.5 1.5 0 0 0 2 2.5v12A1.5 1.5 0 0 0 3.5 16h9a1.5 1.5 0 0 0 1.5-1.5v-12A1.5 1.5 0 0 0 12.5 1h-.585c.055.156.085.325.085.5V2a1.5 1.5 0 0 1-1.5 1.5h-5A1.5 1.5 0 0 1 4 2v-.5c0-.175.03-.344.085-.5m6.769 6.854-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 9.793l2.646-2.647a.5.5 0 0 1 .708.708Z"/>
                </svg></span> Reservas</a>
             
             <!-- Link para direccionar a Informes -->
            <a href="adminInformes.jsp" style="color:white" class="navbar-toggler"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-book-fill" viewBox="0 0 16 16">
                <path d="M8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783"/>
                </svg></span> Informes</a>
             
            <!-- Despliega un menu en cerrar sesion para confirmar el cierre de sesion -->
            <div class="dropdown">
                <a style="color:white" href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Cerrar Sesion</a>
                <div class="dropdown-menu text-center">
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item">Salir</a>
                </div>
            </div>
        </nav>

        <header>
            <h1>Gestión Peliculas</h1>
        </header>
        <div class="tabla">
            <div class="filas">
                <div class="columnas">
                    <table class="table table-striped">
                        <thead>

                            <!-- Insertamos el bucador -->                               
                        <form action="PeliculaServlet?accion=buscar" method="post">
                            <th scope="col" colspan="1"></th>
                            <th scope="col">
                                <input type="search" name="txtBuscar" class="form-control" placeholder="titulo">
                            <th scope="col">
                                <button type="submit" class="btn btn-outline-info"> Buscar</button>
                            </th>
                        </form>
                                <!-- Insetamos el simbolo plus con funcionalidad de añadir peliculas en la ultima columna -->
                                <th scope="col" colspan="8"></th>
                                <th scope="col">
                                    <a href="adminPeliculasInsertar.jsp"><button type="button" class="btn btn-outline-primary"> Añadir </button></a>
                                </th>
                            
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">TITULO</th>
                                <th scope="col">SINOPSIS</th>
                                <th scope="col">PAGINA OFICIAL</th>
                                <th scope="col">AÑO</th>
                                <th scope="col">GENERO</th>
                                <th scope="col">DURACION</th>
                                <th scope="col">DISTRIBUIDORA</th>
                                <th scope="col">DIRECTOR</th>
                                <th scope="col">EDAD</th>
                                <th scope="col">OTROS DATOS</th>
                                <th scope="col">ACCIONES</th>
                            </tr>
                        </thead>
                        <%
                            //PeliculaDAO dao = new PeliculaDAO();
                            
                            List<Pelicula> peliculas = (List) request.getAttribute("peliculas");
                            Iterator<Pelicula> iter = peliculas.iterator();
                            Pelicula pel = null;
                            
                            while (iter.hasNext()){
                                pel=iter.next();
                            
                        %>
                        <tbody>
                            <tr>
                                <td class="id"><b><%= pel.getId()%></b></td>
                                <td class="titulo"><%= pel.getTitulo()%></td>
                                <td class="sinopsis"><%= pel.getSinopsis()%></td>
                                <td class="paginaoficial"><%= pel.getPaginaOficial()%></td>
                                <td class="anio"><%= pel.getAnio()%></td>
                                <td class="genero"><%= pel.getGenero()%></td>
                                <td class="duracion"><%= pel.getDuracion()%></td>
                                <td class="distribuidora"><%= pel.getDistribuidora()%></td>
                                <td class="director"><%= pel.getDirector()%></td>
                                <td class="clasificacionedad"><%= pel.getClasificacionEdad()%></td>
                                <td class="otrosdatos"><%= pel.getOtrosDatos()%></td>
                                <td>

                                <!-- Simbolo editar -->
                                <button type="button" class="btn btn-outline-warning btnEditar" data-toggle="modal" data-target="#exampleModal"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16">
                                    <path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
                                    </svg>
                                </button>

                                    <!-- Simbolo eliminar -->
                                    <form action="PeliculaServlet?accion=eliminar&id=<%=pel.getId()%>" method="post">
                                    <button type="submit" class="btn btn-outline-danger btnEliminar"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16">
                                        <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5m-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5M4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5"/>
                                        </svg >
                                    </button>
                                    </form>
                                </td>                
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!-- Modal Editar-->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Modificar Pelicula</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-form-modal">
                            <form action="PeliculaServlet?accion=modificar" method="post">
                                <input class="form-control" id="txtid" name="txtid" hidden="">
                                <div class="form-group">
                                    <label for="exampleFormControlInput1">Titulo</label>
                                    <input type="text" class="form-control" id="txtTitulo" name="txtTitulo" required="">
                                </div>
                                <div class="form-group">
                                    <label for="exampleFormControlTextarea1">Sinopsis</label>
                                    <textarea type="text" class="form-control" id="txtSinopsis" name="txtSinopsis" rows="3" required=""></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="exampleFormControlInput1">Página Oficial</label>
                                    <input type="text" class="form-control" id="txtPaginaoficial" name="txtPaginaoficial" required="">
                                </div>
                                <div class="form-group">
                                    <label for="exampleFormControlInput1">Año</label>
                                    <input type="date" class="form-control" id="txtAnio" name="txtAnio" required="">
                                </div>
                                <div class="form-group">
                                    <label for="exampleFormControlInput1">Género</label>
                                    <input type="text" class="form-control" id="txtGenero" name="txtGenero" required="">
                                </div>
                                <div class="form-group">
                                    <label for="exampleFormControlInput1">Duración</label>
                                    <input type="text" class="form-control" id="txtDuracion" name="txtDuracion" required="">
                                </div>
                                <div class="form-group">
                                    <label for="exampleFormControlInput1">Distribuidora</label>
                                    <input type="text" class="form-control" id="txtDistribuidora" name="txtDistribuidora" required="">
                                </div>
                                <div class="form-group">
                                    <label for="exampleFormControlInput1">Director</label>
                                    <input type="text" class="form-control" id="txtDirector" name="txtDirector" required="">
                                </div>
                                <div class="form-group">
                                    <label for="exampleFormControlInput1">Clasificación</label>
                                    <input class="form-control" id="txtClasificacion" name="txtClasificacion" required="">
                                </div>
                                <div class="form-group">
                                    <label for="exampleFormControlTextarea1">Otros Datos</label>
                                    <textarea type="text" class="form-control" id="txtOtrosdatos" name="txtOtrosdatos" rows="3" required=""></textarea>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary">Guardar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                        
        <footer>
            <p>&copy; 2023 CineWeb</p>
        </footer>
        
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/pelicula.js"></script>
    </body>
</html>
