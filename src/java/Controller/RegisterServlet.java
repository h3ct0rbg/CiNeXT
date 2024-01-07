package Controller;

import DAO.UsuarioDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        
        switch (action) {
            case "register" -> register(request, response);
            default -> {
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
        }
    }
    
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros del formulario
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Crear un nuevo usuario
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int registro = usuarioDAO.registrarUsuario(nombre, email, password);

        switch (registro) {
            case 1 -> {
                // Redirigir a la página de inicio de sesión si el registro fue exitoso
                request.getSession().setAttribute("sucess", "Usuario registrado correctamente");
                response.sendRedirect(request.getContextPath() + "/login");

            }
            case 2 -> {
                // Manejar el error si el usuario ya se encuentra registrado
                request.getSession().setAttribute("error", "El usuario ya se encuentra registrado");
                response.sendRedirect(request.getContextPath() + "/login");
            }
            default -> {
                // Manejar el error
                request.getSession().setAttribute("errorMessage", "Error al registrar el usuario");
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
    }
}