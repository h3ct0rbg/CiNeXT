package Controller;

import DAO.EntradaDAO;
import DAO.SesionDAO;
import Model.Asiento;
import Model.Entrada;
import Model.Usuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/pago/entradas")
public class PagoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mostrarEntradas(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            request.getSession().setAttribute("error", "Inicia sesión para realizar la reserva");
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            String action = request.getParameter("accion");

            switch (action) {
                case "pagar" ->
                    crearEntrada(request, response);
                default ->
                    mostrarEntradas(request, response);
            }
        }
    }

    private void mostrarEntradas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void crearEntrada(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1- RESERVAR LOS ASIENTOS
        // Obtener los asientos
        List<Asiento> listaAsientos = preprocesarAsientos(request.getParameter("asientos"));
        // Obtener el id de la sesión
        int idSesion = Integer.parseInt(request.getParameter("idSesion"));

        // Reservar los asientos
        SesionDAO sesionDAO = new SesionDAO();        
        if(sesionDAO.addReservaAsientos(idSesion, listaAsientos)) {
            // 2- GENERAR LAS ENTRADAS
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            
            for (Asiento asiento : listaAsientos) {
                Entrada entrada = new Entrada(idSesion, asiento.getFilas(), asiento.getColumnas(), usuario.getEmail());
                EntradaDAO entradaDAO = new EntradaDAO();
                entradaDAO.insertarEntrada(entrada);
            }      
            
            // Redirigir a la página de salas si el registro fue exitoso
            request.getSession().setAttribute("sucess", "Compra realizada con éxito");
            response.sendRedirect(request.getContextPath());
        } else {
            // Manejar el error
            request.getSession().setAttribute("error", "Error al reservar los asientos");
            response.sendRedirect(request.getContextPath());
        }
    }

    private List<Asiento> preprocesarAsientos(String asientosParam) {
        // Crear una lista para almacenar los objetos de Asiento
        List<Asiento> listaAsientos = new ArrayList<>();

        // Separar los asientos usando el patrón "[fila-columna]"
        String[] asientosArray = asientosParam.split("\\]\\s*\\[");  // Modificado para quitar espacios en blanco
        asientosArray[0] = asientosArray[0].replace("[", "").trim();
        asientosArray[asientosArray.length - 1] = asientosArray[asientosArray.length - 1].replace("]", "").trim();

        // Iterar sobre los elementos y crear objetos Asiento
        for (String asiento : asientosArray) {
            String[] coordenadas = asiento.split("-");
            int fila = Integer.parseInt(coordenadas[0].trim());
            int columna = Integer.parseInt(coordenadas[1].trim());

            // Crear objeto Asiento y agregarlo a la lista
            Asiento nuevoAsiento = new Asiento(fila, columna);
            listaAsientos.add(nuevoAsiento);
        }

        return listaAsientos;
    }
}
