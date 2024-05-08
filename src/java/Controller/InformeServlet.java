package Controller;

import DAO.InformeDAO;
import Model.Informe;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/informes")
public class InformeServlet extends HttpServlet {
    InformeDAO informeDAO = new InformeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mostrarInforme(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");

        switch (action) {
            case "ordenarGenero" -> ordenarGeneroInforme(request, response);
            case "ordenarPuntuacion" -> ordenarPuntuacionInforme(request, response);
            default -> {
                mostrarInforme(request, response);
            }
        }
    }
    
    protected void mostrarInforme(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        List<Informe> peliculas = informeDAO.mostarPeliculas();
        request.setAttribute("peliculas", peliculas);
        request.getRequestDispatcher("/adminInformes.jsp").forward(request, response);
    } 
        
    protected void ordenarGeneroInforme(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        List<Informe> peliculas = informeDAO.ordenarPeliculasGenero();
        request.setAttribute("peliculas", peliculas);
        request.getRequestDispatcher("/adminInformes.jsp").forward(request, response);
    }
    
    protected void ordenarPuntuacionInforme(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Informe> peliculas = informeDAO.ordenarPeliculasPuntuacion();
        request.setAttribute("peliculas", peliculas);
        request.getRequestDispatcher("/adminInformes.jsp").forward(request, response);
    }
}