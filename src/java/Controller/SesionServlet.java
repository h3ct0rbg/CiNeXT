package Controller;

import DAO.SesionDAO;
import Model.Sesion;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@WebServlet("/admin/sesiones")
public class SesionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mostrarSesiones(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");

        switch (action) {
            case "buscar" ->
                buscarSesion(request, response);
            case "crear" ->
                insertarSesion(request, response);
            case "modificar" ->
                modificarSesion(request, response);
            case "eliminar" ->
                eliminarSesion(request, response);
            default ->
                mostrarSesiones(request, response);
        }
    }

    protected void mostrarSesiones(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SesionDAO sesionDAO = new SesionDAO();
        List<Sesion> sesiones = sesionDAO.mostrarSesiones();
        request.setAttribute("sesiones", sesiones);
        request.getRequestDispatcher("/adminSesiones.jsp").forward(request, response);
    }

    protected void buscarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String texto = request.getParameter("busqueda");
        SesionDAO sesionDAO = new SesionDAO();
        List<Sesion> sesiones = sesionDAO.buscarSesion(texto);
        request.setAttribute("sesiones", sesiones);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminSesiones.jsp");
        dispatcher.forward(request, response);
    }

    protected void insertarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora") + ":00";
        int salaId = Integer.parseInt(request.getParameter("salaId"));
        int peliculaId = Integer.parseInt(request.getParameter("peliculaId"));

        Sesion sesion = new Sesion(Date.valueOf(fecha), Time.valueOf(hora), salaId, peliculaId);
        SesionDAO sesionDAO = new SesionDAO();
        int res = sesionDAO.insertarSesion(sesion);

        switch (res) {
            case 1 -> {
                // Redirigir a la página de sesiones si el registro fue exitoso
                request.setAttribute("sucess", "Sesión insertada correctamente");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/sesiones?accion=mostrar");
                dispatcher.forward(request, response);
            }

            case 2 -> {
                // Manejar el error si la sesión ya se encuentra registrada
                request.setAttribute("error", "La sesión coincide con otra");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/sesiones?accion=mostrar");
                dispatcher.forward(request, response);
            }

            default -> {
                // Manejar el error
                request.setAttribute("error", "Error al registrar la sesión");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/sesiones?accion=mostrar");
                dispatcher.forward(request, response);
            }
        }
    }

    protected void eliminarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        SesionDAO sesionDAO = new SesionDAO();
        int res = sesionDAO.eliminarSesion(id);

        switch (res) {
            case 1 -> {
                // Redirigir a la página de sesiones si la eliminación fue exitosa
                request.setAttribute("sucess", "Sesión eliminada correctamente");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/sesiones?accion=mostrar");
                dispatcher.forward(request, response);
            }

            case 0 -> {
                // Manejar el error si la sesión no existe
                request.setAttribute("error", "La sesión no existe");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/sesiones?accion=mostrar");
                dispatcher.forward(request, response);
            }

            default -> {
                // Manejar el error
                request.setAttribute("error", "Error al eliminar la sesión");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/sesiones?accion=mostrar");
                dispatcher.forward(request, response);
            }
        }
    }

    protected void modificarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener datos del formulario
        int id = Integer.parseInt(request.getParameter("id"));
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        int salaId = Integer.parseInt(request.getParameter("salaId"));
        int peliculaId = Integer.parseInt(request.getParameter("peliculaId"));

        // Verificar si la cadena ya tiene el formato completo
        if (!hora.matches("\\d{2}:\\d{2}:\\d{2}")) {
            // Si no tiene el formato completo, añadir ":00" al final
            hora = hora + ":00";
        }

        // Crear objeto Sesion
        Sesion sesion = new Sesion(id, Date.valueOf(fecha), Time.valueOf(hora), salaId, peliculaId);

        SesionDAO sesionDAO = new SesionDAO();
        int res = sesionDAO.modificarSesion(sesion);

        switch (res) {
            case 1 -> {
                // Redirigir a la página de sesiones si la actualización fue exitosa
                request.setAttribute("sucess", "Sesión actualizada correctamente");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/sesiones?accion=mostrar");
                dispatcher.forward(request, response);
            }

            case 2 -> {
                // Manejar el error si la sesión ya se encuentra registrada
                request.setAttribute("error", "La sesión coincide con otra");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/sesiones?accion=mostrar");
                dispatcher.forward(request, response);
            }
            
            case 3 -> {
                // Manejar el error si la sesión ya se encuentra registrada
                request.setAttribute("error", "Ya hay entradas reservadas para esta sesión");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/sesiones?accion=mostrar");
                dispatcher.forward(request, response);
            }

            default -> {
                // Manejar el error
                request.setAttribute("error", "Error al actualizar la sesión");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/sesiones?accion=mostrar");
                dispatcher.forward(request, response);
            }
        }
    }
}
