package Controller;

import DAO.SalaDAO;
import DAO.SesionDAO;
import Model.Sala;
import Model.Sesion;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pelicula/sesion")
public class ButacasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mostrarSala(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mostrarSala(request, response);
    }

    protected void mostrarSala(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idSesion = Integer.parseInt(request.getParameter("idSesion"));
        SesionDAO sesionDAO = new SesionDAO();
        Sesion sesion = sesionDAO.getSesionById(idSesion);
        
        SalaDAO salaDAO = new SalaDAO();
        Sala sala = salaDAO.getSalaById(sesion.getSalaId());
        request.setAttribute("sala", sala);
        request.setAttribute("sesion", sesion);
        request.getRequestDispatcher("/elegirButacas.jsp").forward(request, response);
    }
}