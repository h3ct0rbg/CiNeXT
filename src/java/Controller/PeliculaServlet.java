package Controller;

import DAO.PeliculaDAO;
import Model.Pelicula;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.List;

@WebServlet("/peliculas")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class PeliculaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        
        switch (action) {
            case "buscar" -> buscarPelicula(request, response);
            case "crear" -> insertarPelicula(request, response);
            case "modificar" -> modificarPelicula(request, response);
            case "eliminar" -> eliminarPelicula(request, response);
            default -> {
            }
        }
    }

    protected void buscarPelicula(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        String titulo = request.getParameter("busqueda");
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        List<Pelicula> peliculas = peliculaDAO.buscarPelicula(titulo);
        request.setAttribute("peliculas", peliculas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminPeliculas.jsp");
        dispatcher.forward(request, response);
    }
    
    protected void insertarPelicula(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener datos del formulario
        String titulo = request.getParameter("titulo");
        String sinopsis = request.getParameter("sinopsis");
        int anio = Integer.parseInt(request.getParameter("anio"));
        String genero = request.getParameter("genero");
        int duracion = Integer.parseInt(request.getParameter("duracion"));
        String director = request.getParameter("director");
        String edad = request.getParameter("edad");
        Part filePart = request.getPart("portada");
        InputStream inputStream = filePart.getInputStream();

        // Convertir la imagen a un arreglo de bytes
        byte[] imagenBytes = inputStream.readAllBytes();

        // Crear objeto Pelicula
        Pelicula pelicula = new Pelicula(titulo, sinopsis, anio, genero, duracion, director, edad, imagenBytes);
        
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        int res = peliculaDAO.insertarPelicula(pelicula);
        switch (res) {
            case 1 -> {
                // Redirigir a la página de peliculas si el registro fue exitoso
                request.setAttribute("sucess", "Pelicula insertada correctamente");
                RequestDispatcher dispatcher1 = request.getRequestDispatcher("adminPeliculas.jsp");
                dispatcher1.forward(request, response);
            }

            case 2 -> {
                // Manejar el error si la pelicula ya se encuentra registrada
                request.setAttribute("error", "La pelicula ya esta registrada");
                RequestDispatcher dispatcher2 = request.getRequestDispatcher("adminPeliculas.jsp");
                dispatcher2.forward(request, response);
            }

            default -> {
                // Manejar el error
                request.setAttribute("error", "Error al registrar la pelicula");
                RequestDispatcher dispatcher3 = request.getRequestDispatcher("adminPeliculas.jsp");
                dispatcher3.forward(request, response);
            }
        }
    }

    protected void eliminarPelicula(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           
        String titulo=request.getParameter("titulo");
        
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        int res = peliculaDAO.eliminarPelicula(titulo);

        switch (res) {
            case 1 -> {
                // Redirigir a la página de peliculas si el registro fue exitoso
                request.setAttribute("sucess", "Pelicula eliminada correctamente");
                RequestDispatcher dispatcher1 = request.getRequestDispatcher("adminPeliculas.jsp");
                dispatcher1.forward(request, response);
            }
            default -> {
                // Manejar el error
                request.setAttribute("error", "Error al eliminar la pelicula");
                RequestDispatcher dispatcher3 = request.getRequestDispatcher("adminPeliculas.jsp");
                dispatcher3.forward(request, response);
            }
        }

    }
    
    protected void modificarPelicula(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtener datos del formulario
        int id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String sinopsis = request.getParameter("sinopsis");
        int anio = Integer.parseInt(request.getParameter("anio"));
        String genero = request.getParameter("genero");
        int duracion = Integer.parseInt(request.getParameter("duracion"));
        String director = request.getParameter("director");
        String edad = request.getParameter("edad");
        
        // Verificar si se proporcionó una nueva imagen
        Part filePart = request.getPart("portada");
        byte[] imagenBytes = null;

        if (filePart != null && filePart.getSize() > 0) {
            // Convertir la imagen a un arreglo de bytes
            InputStream inputStream = filePart.getInputStream();
            imagenBytes = inputStream.readAllBytes();
        }

        // Crear objeto Pelicula
        Pelicula pelicula = new Pelicula(id, titulo, sinopsis, anio, genero, duracion, director, edad, imagenBytes);
        
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        int res = peliculaDAO.modificarPelicula(pelicula);

        switch (res) {
            case 1 -> {
                // Redirigir a la página de peliculas si el update fue exitoso
                request.setAttribute("sucess", "Pelicula actualizada correctamente");
                RequestDispatcher dispatcher1 = request.getRequestDispatcher("adminPeliculas.jsp");
                dispatcher1.forward(request, response);
            }
            case 0 -> {
                // Manejar el error
                request.setAttribute("error", "Error al actualizar la pelicula");
                RequestDispatcher dispatcher3 = request.getRequestDispatcher("adminPeliculas.jsp");
                dispatcher3.forward(request, response);
            }
        }
    }
}