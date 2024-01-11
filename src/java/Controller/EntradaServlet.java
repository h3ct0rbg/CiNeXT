package Controller;

import DAO.EntradaDAO;
import Model.Entrada;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/admin/entradas")
public class EntradaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mostrarSalas(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mostrarSalas(request, response);
    }

    protected void mostrarSalas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntradaDAO entradaDAO = new EntradaDAO();
        List<Entrada> entradas = entradaDAO.getAllEntradas();
        request.setAttribute("entradas", entradas);
        request.getRequestDispatcher("/adminEntradas.jsp").forward(request, response);
    }
}