package Controller;

import DAO.UsuarioDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            case 1:
                // Redirigir a la página de inicio de sesión si el registro fue exitoso
                request.setAttribute("registered", "Usuario registrado correctamente");
                RequestDispatcher dispatcher1 = request.getRequestDispatcher("index.jsp");
                dispatcher1.forward(request, response);
            
            case 2:
                // Manejar el error si el usuario ya se encuentra registrado
                request.setAttribute("errorRegister", "El usuario ya se encuentra registrado");
                RequestDispatcher dispatcher2 = request.getRequestDispatcher("register.jsp");
                dispatcher2.forward(request, response);
            
            default:
                // Manejar el error
                request.setAttribute("errorRegister", "Error al registrar el usuario");
                RequestDispatcher dispatcher3 = request.getRequestDispatcher("register.jsp");
                dispatcher3.forward(request, response);
            
        }
    }
}