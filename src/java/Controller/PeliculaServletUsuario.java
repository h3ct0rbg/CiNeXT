package Controller;


import DAO.PeliculaDAO;
import Model.Pelicula;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pelicula")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class PeliculaServletUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mostrarPelicula(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mostrarPelicula(request, response);
    }

    protected void mostrarPelicula(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        titulo = java.net.URLDecoder.decode(titulo, "UTF-8");
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        Pelicula pelicula = peliculaDAO.getPeliculaByTitulo(titulo);
        request.setAttribute("pelicula", pelicula);
        request.getRequestDispatcher("/detallesPelicula.jsp").forward(request, response);
    }
}