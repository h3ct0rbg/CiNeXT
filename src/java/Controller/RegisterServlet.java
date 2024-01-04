package Controller;

import DAO.UsuarioDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                request.setAttribute("sucess", "Usuario registrado correctamente");
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
            case 2 -> {
                // Manejar el error si el usuario ya se encuentra registrado
                request.setAttribute("error", "El usuario ya se encuentra registrado");
                RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                dispatcher.forward(request, response);
            }
            default -> {
                // Manejar el error
                request.setAttribute("error", "Error al registrar el usuario");
                RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}