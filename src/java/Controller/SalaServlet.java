package Controller;

import DAO.SalaDAO;
import Model.Sala;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/admin/salas")
public class SalaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mostrarSalas(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");

        switch (action) {
            case "crear" ->
                insertarSala(request, response);
            case "modificar" ->
                modificarSala(request, response);
            case "eliminar" ->
                eliminarSala(request, response);
            default -> {
                mostrarSalas(request, response);
            }
        }
    }

    protected void mostrarSalas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SalaDAO salaDAO = new SalaDAO();
        List<Sala> salas = salaDAO.mostrarSalas();
        request.setAttribute("salas", salas);
        request.getRequestDispatcher("/adminSalas.jsp").forward(request, response);
    }
    
    protected void insertarSala(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int filas = Integer.parseInt(request.getParameter("filas"));
        int columnas = Integer.parseInt(request.getParameter("columnas"));
        String tipo = request.getParameter("tipo");
        String nombre = request.getParameter("nombre");

        Sala sala = new Sala(filas, columnas, tipo, nombre);

        SalaDAO salaDAO = new SalaDAO();
        int res = salaDAO.insertarSala(sala);
        switch (res) {
            case 1 -> {
                // Redirigir a la página de salas si el registro fue exitoso
                request.setAttribute("success", "Sala insertada correctamente");
                RequestDispatcher dispatcher = request.getRequestDispatcher("salas?accion=mostrar");
                dispatcher.forward(request, response);
            }
            case 2 -> {
                // Redirigir a la página de salas si el registro fue exitoso
                request.setAttribute("error", "La sala ya existe");
                RequestDispatcher dispatcher = request.getRequestDispatcher("salas?accion=mostrar");
                dispatcher.forward(request, response);
            }
            default -> {
                // Manejar el error
                request.setAttribute("error", "Error al registrar la sala");
                RequestDispatcher dispatcher = request.getRequestDispatcher("salas?accion=mostrar");
                dispatcher.forward(request, response);
            }
        }
    }

    protected void eliminarSala(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);

            SalaDAO salaDAO = new SalaDAO();
            int res = salaDAO.eliminarSala(id);

            switch (res) {
                case 1 -> {
                    // Redirigir a la página de salas si el registro fue exitoso
                    request.setAttribute("success", "Sala eliminada correctamente");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("salas?accion=mostrar");
                    dispatcher.forward(request, response);
                }
                default -> {
                    // Manejar el error
                    request.setAttribute("error", "Error al eliminar la sala");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("salas?accion=mostrar");
                    dispatcher.forward(request, response);
                }
            }
        } else {
            request.setAttribute("error", "Por favor, proporcione un ID de sala válido.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("salas?accion=mostrar");
            dispatcher.forward(request, response);
        }
    }

    protected void modificarSala(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener datos del formulario
        int id = Integer.parseInt(request.getParameter("id"));
        int filas = Integer.parseInt(request.getParameter("filas"));
        int columnas = Integer.parseInt(request.getParameter("columnas"));
        String tipo = request.getParameter("tipo");
        String nombre = request.getParameter("nombre");

        // Crear objeto Sala
        Sala sala = new Sala(id, filas, columnas, tipo, nombre);

        SalaDAO salaDAO = new SalaDAO();
        int res = salaDAO.modificarSala(sala);

        switch (res) {
            case 1 -> {
                // Redirigir a la página de salas si la actualización fue exitosa
                request.setAttribute("success", "Sala actualizada correctamente");
                RequestDispatcher dispatcher = request.getRequestDispatcher("salas?accion=mostrar");
                dispatcher.forward(request, response);
            }
            case 0 -> {
                // Manejar el error
                request.setAttribute("error", "Error al actualizar la sala");
                RequestDispatcher dispatcher = request.getRequestDispatcher("salas?accion=mostrar");
                dispatcher.forward(request, response);
            }
        }
    }
}