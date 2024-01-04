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

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                response.sendRedirect("areaUsuario.jsp"); // Cambia al nombre de tu página de área de usuario
            } 
            else if ("Admin".equals(usuario.getTipoUsuario())) {
                response.sendRedirect("areaAdmin.jsp"); // Cambia al nombre de tu página de área de administración
            }
        } else {
            // Manejar el error o mostrar un mensaje al usuario
            request.setAttribute("error", "Credenciales incorrectas, por favor, intenta nuevamente");

            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
}
