package Controller;

import DAO.UsuarioDAO;
import Model.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        
        switch (action) {
            case "login" -> login(request, response);
            default -> {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }
    
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros del formulario
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Crear un nuevo usuario
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.iniciarSesion(email, password);

        if (usuario != null) {
            // Iniciar sesión y redirigir al área de usuario
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            if ("Cliente".equals(usuario.getTipoUsuario())) {
                response.sendRedirect("/Cine"); // Cambia al nombre de tu página de área de usuario
            } 
            else if ("Admin".equals(usuario.getTipoUsuario())) {
                response.sendRedirect("/Cine/admin/peliculas"); // Cambia al nombre de tu página de área de administración
            }
        } else {
            request.getSession().setAttribute("error", "Credenciales incorrectas, por favor, intenta nuevamente");
            response.sendRedirect(request.getContextPath() + "/login");                
        }
    }
}
