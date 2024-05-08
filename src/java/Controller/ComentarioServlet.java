package Controller;

import DAO.ComentarioDAO;
import DAO.PeliculaDAO;
import Model.Comentario;
import Model.Pelicula;
import Model.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/mis-peliculas")
public class ComentarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mostrarMisPeliculas(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");

        switch (action) {
            case "añadir" -> añadirComentario(request, response);
            case "modificar" -> modificarComentario(request, response);
            case "eliminar" -> eliminarComentario(request, response);
            default -> mostrarMisPeliculas(request, response);
        }
    }

    protected void mostrarMisPeliculas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        List<Pelicula> peliculas = peliculaDAO.mostrarPeliculasVistas(usuario.getEmail());
        
        request.setAttribute("peliculas", peliculas);
        request.getRequestDispatcher("/misPeliculas.jsp").forward(request, response);
    }

    protected void añadirComentario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener datos del formulario
        int peliculaId = Integer.parseInt(request.getParameter("peliculaId"));
        String email = request.getParameter("email");
        float puntuacion = Float.parseFloat(request.getParameter("puntuacion"));
        String comentarioTexto = request.getParameter("comentario");
        Comentario comentario = new Comentario(peliculaId, email, comentarioTexto, puntuacion);

        ComentarioDAO comentarioDAO = new ComentarioDAO();
        int res = comentarioDAO.insertarComentario(comentario);

        switch (res) {
            case 1 -> {
                // Redirigir a la página de comentarios si el registro fue exitoso
                request.setAttribute("sucess", "Comentario insertado correctamente");
                RequestDispatcher dispatcher = request.getRequestDispatcher("mis-peliculas?accion=mostrar");
                dispatcher.forward(request, response);
            }
            case 2 -> {
                // Manejar el error si el comentario ya se encuentra registrado
                request.setAttribute("error", "El comentario ya está registrado");
                RequestDispatcher dispatcher = request.getRequestDispatcher("mis-peliculas?accion=mostrar");
                dispatcher.forward(request, response);
            }
            default -> {
                // Manejar el error
                request.setAttribute("error", "Error al registrar el comentario");
                RequestDispatcher dispatcher = request.getRequestDispatcher("mis-peliculas?accion=mostrar");
                dispatcher.forward(request, response);
            }
        }
    }

    protected void eliminarComentario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        ComentarioDAO comentarioDAO = new ComentarioDAO();
        int res = comentarioDAO.eliminarComentario(id);

        switch (res) {
            case 1 -> {
                // Redirigir a la página de comentarios si la eliminación fue exitosa
                request.setAttribute("sucess", "Comentario eliminado correctamente");
                RequestDispatcher dispatcher = request.getRequestDispatcher("mis-peliculas?accion=mostrar");
                dispatcher.forward(request, response);
            }
            default -> {
                // Manejar el error
                request.setAttribute("error", "Error al eliminar el comentario");
                RequestDispatcher dispatcher = request.getRequestDispatcher("mis-peliculas?accion=mostrar");
                dispatcher.forward(request, response);
            }
        }
    }

    protected void modificarComentario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener datos del formulario
        int id = Integer.parseInt(request.getParameter("id"));
        int peliculaId = Integer.parseInt(request.getParameter("peliculaId"));
        String email = request.getParameter("email");
        float puntuacion = Float.parseFloat(request.getParameter("puntuacion"));
        String comentarioTexto = request.getParameter("comentario");

        // Crear objeto Comentario
        Comentario comentario = new Comentario(id, peliculaId, email, comentarioTexto, puntuacion);

        ComentarioDAO comentarioDAO = new ComentarioDAO();
        int res = comentarioDAO.modificarComentario(comentario);

        switch (res) {
            case 1 -> {
                // Redirigir a la página de comentarios si la modificación fue exitosa
                request.setAttribute("sucess", "Comentario actualizado correctamente");
                RequestDispatcher dispatcher = request.getRequestDispatcher("mis-peliculas?accion=mostrar");
                dispatcher.forward(request, response);
            }
            case 0 -> {
                // Manejar el error
                request.setAttribute("error", "Error al actualizar el comentario");
                RequestDispatcher dispatcher = request.getRequestDispatcher("mis-peliculas?accion=mostrar");
                dispatcher.forward(request, response);
            }
        }
    }
}