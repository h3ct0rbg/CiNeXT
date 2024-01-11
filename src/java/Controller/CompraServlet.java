package Controller;

import Model.Usuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/compra/entradas")
public class CompraServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mostrarPago(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            // Si no se ha iniciado sesión
            request.getSession().setAttribute("error", "Inicia sesión para realizar la reserva");
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            mostrarPago(request, response);
        }
    }

    protected void mostrarPago(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String asientos = request.getParameter("asientos");
        request.setAttribute("asientos", asientos);
        
        int idSesion = Integer.parseInt(request.getParameter("idSesion"));
        request.setAttribute("idSesion", idSesion);
        request.getRequestDispatcher("/pago.jsp").forward(request, response);
    }
}
