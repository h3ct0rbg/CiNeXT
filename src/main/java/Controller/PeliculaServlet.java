package Controller;

import DAO.PeliculaDAO;
import Model.Pelicula;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PeliculaServlet", urlPatterns = {"/PeliculaServlet"})
public class PeliculaServlet extends HttpServlet {
    PeliculaDAO dao = new PeliculaDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = "";
        String action = request.getParameter("accion");

        if (action.equals("mostrar")) {
            mostrarPeliculas(request, response);
        }
        //Buscar una pelicula por el titulo
        else if (action.equals("buscar")) {
            buscarPelicula(request, response);
        }
        //Si el usuario pulsa el boton guardar
        else if (action.equals("guardar")) {
            insertarPelicula(request, response);
        }
        //Si el usuario pulsa el boton de modificar
        else if(action.equals("modificar")){
            modificarPelicula(request, response);
        }
        //Si el usuario pulsa el boton de eliminar
        else if (action.equals("eliminar")){
            eliminarPelicula(request, response);
        }
    }
    
    protected void mostrarPeliculas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        List<Pelicula> peliculas = dao.mostarPeliculas();
        request.setAttribute("peliculas", peliculas);
        request.getRequestDispatcher("adminPeliculas.jsp").forward(request, response);
    } 
        
    protected void buscarPelicula(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        String titulo = request.getParameter("txtBuscar");
        System.out.println(titulo);
        List<Pelicula> peliculas = dao.buscarTitulo(titulo);
        request.setAttribute("peliculas", peliculas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminPeliculas.jsp");
        dispatcher.forward(request, response);
    }

    protected void insertarPelicula(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String titulo = request.getParameter("titulo");
        String sinopsis = request.getParameter("sinopsis");
        String paginaOficial = request.getParameter("paginaoficial");
        String anio = request.getParameter("anio");
        String genero = request.getParameter("genero");
        String duracion = request.getParameter("duracion");
        String distribuidora = request.getParameter("distribuidora");
        String director = request.getParameter("director");
        String clasificacion = request.getParameter("clasificacion");
        String otrosDatos = request.getParameter("otrosdatos");

        int res = dao.introducirPelicula(titulo, sinopsis, paginaOficial, anio, genero, duracion, distribuidora, director, clasificacion, otrosDatos);
        switch (res) {
            case 1:
                // Redirigir a la página de peliculas si el registro fue exitoso
                request.setAttribute("registered", "Pelicula insertada correctamente");
                RequestDispatcher dispatcher1 = request.getRequestDispatcher("PeliculaServlet?accion=mostrar");
                dispatcher1.forward(request, response);
                break;

            case 2:
                // Manejar el error si la pelicula ya se encuentra registrada
                request.setAttribute("errorRegister", "La pelicula ya esta registrada");
                RequestDispatcher dispatcher2 = request.getRequestDispatcher("PeliculaServlet?accion=mostrar");
                dispatcher2.forward(request, response);
                break;

            default:
                // Manejar el error
                request.setAttribute("errorRegister", "Error al registrar la pelicula");
                RequestDispatcher dispatcher3 = request.getRequestDispatcher("PeliculaServlet?accion=mostrar");
                dispatcher3.forward(request, response);
                break;
        }
    }

    protected void eliminarPelicula(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           
        int id=Integer.parseInt(request.getParameter("id"));
        int res = dao.eliminarPelicula(id);

        switch (res) {
            case 1:
                // Redirigir a la página de peliculas si el registro fue exitoso
                request.setAttribute("registered", "Pelicula eliminada correctamente");
                RequestDispatcher dispatcher1 = request.getRequestDispatcher("PeliculaServlet?accion=mostrar");
                dispatcher1.forward(request, response);
                break;
            case 2:
                // Manejar el error si la pelicula no existe
                request.setAttribute("errorRegister", "La pelicula no existe");
                RequestDispatcher dispatcher2 = request.getRequestDispatcher("PeliculaServlet?accion=mostrar");
                dispatcher2.forward(request, response);
                break;
            default:
                // Manejar el error
                request.setAttribute("errorRegister", "Error al eliminar la pelicula");
                RequestDispatcher dispatcher3 = request.getRequestDispatcher("PeliculaServlet?accion=mostrar");
                dispatcher3.forward(request, response);
                break;
        }

    }

    protected void modificarPelicula(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("txtid"));
        String titulo = request.getParameter("txtTitulo");
        String sinopsis = request.getParameter("txtSinopsis");
        String paginaOficial = request.getParameter("txtPaginaoficial");
        String anio = request.getParameter("txtAnio");
        String genero = request.getParameter("txtGenero");
        String duracion = request.getParameter("txtDuracion");
        String distribuidora = request.getParameter("txtDistribuidora");
        String director = request.getParameter("txtDirector");
        String clasificacion = request.getParameter("txtClasificacion");
        String otrosDatos = request.getParameter("txtOtrosdatos");
        
        int res = dao.modificarPelicula(id, titulo, sinopsis, paginaOficial, anio, genero, duracion, distribuidora, director, clasificacion, otrosDatos);

        switch (res) {
            case 1:
                // Redirigir a la página de peliculas si el update fue exitoso
                request.setAttribute("registered", "Pelicula actualizada correctamente");
                RequestDispatcher dispatcher1 = request.getRequestDispatcher("PeliculaServlet?accion=mostrar");
                dispatcher1.forward(request, response);
                break;
            case 2:
                // Manejar el error si la pelicula no existe
                request.setAttribute("errorRegister", "La pelicula no existe");
                RequestDispatcher dispatcher2 = request.getRequestDispatcher("PeliculaServlet?accion=mostrar");
                dispatcher2.forward(request, response);
                break;
            default:
                // Manejar el error
                request.setAttribute("errorRegister", "Error al actualizar la pelicula");
                RequestDispatcher dispatcher3 = request.getRequestDispatcher("PeliculaServlet?accion=mostrar");
                dispatcher3.forward(request, response);
                break;
        }
    }

}
