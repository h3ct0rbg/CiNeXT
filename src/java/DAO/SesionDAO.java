package DAO;

import Model.Sesion;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SesionDAO {

    Connection conexion;

    public SesionDAO() {
        // Conexión a la base de datos
        this.conexion = ConexionDB.obtenerConexion();
    }

    // Método para insertar una sesión
    public int insertarSesion(Sesion sesion) {
        try {
            if (existeSesion(sesion)) {
                return 2;
            }

            // Consulta SQL para insertar una nueva sala
            String consulta = "INSERT INTO Sesiones (fecha, hora, salaId, peliculaId) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consulta);

            // Establecer los valores de los parámetros de la consulta
            statement.setDate(1, sesion.getFecha());
            statement.setTime(2, sesion.getHora());
            statement.setInt(3, sesion.getSalaId());
            statement.setInt(4, sesion.getPeliculaId());

            // Ejecutar la consulta
            int filasAfectadas = statement.executeUpdate();

            // Verificar si se insertó correctamente
            return (filasAfectadas > 0) ? 1 : 0;

        } catch (SQLException e) {
            return 0;

        } finally {
            ConexionDB.cerrarConexion(conexion);
        }
    }

    // Método para listar todas las sesiones
    public List<Sesion> mostrarSesiones() {
        List<Sesion> sesiones = new ArrayList<>();

        try {
            // Consulta SQL para mostrar todas las sesiones
            String consulta = "SELECT s.id, fecha, hora, salaId, nombre, peliculaId, titulo FROM sesiones s INNER JOIN peliculas p ON s.peliculaId = p.id INNER JOIN salas sa ON s.salaId = sa.id ORDER BY s.id";
            PreparedStatement statement = conexion.prepareStatement(consulta);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Guardar los datos en variables
                int id = resultSet.getInt("id");
                Date fecha = resultSet.getDate("fecha");
                Time hora = resultSet.getTime("hora");
                int salaId = resultSet.getInt("salaId");
                String nombreSala = resultSet.getString("nombre");
                int peliculaId = resultSet.getInt("peliculaId");
                String tituloPelicula = resultSet.getString("titulo");

                // Crear objeto Sesion con el constructor correspondiente
                Sesion sesion = new Sesion(id, fecha, hora, salaId, peliculaId);
                sesion.setNombreSala(nombreSala);
                sesion.setNombrePelicula(tituloPelicula);
                sesiones.add(sesion);
            }

        } catch (SQLException e) {
            // Manejar la excepción
        } finally {
            ConexionDB.cerrarConexion(conexion);
        }

        return sesiones;
    }

    // Método para modificar una sesión
    public int modificarSesion(Sesion sesion) {
        try {
            if (existeSesionActualizar(sesion)) {
                return 2;
            }

            // Consulta SQL para actualizar una sesión
            String consulta = "UPDATE sesiones SET fecha=?, hora=?, salaId=?, peliculaId=? WHERE id=?";
            PreparedStatement statement = conexion.prepareStatement(consulta);

            // Establecer los valores de los parámetros de la consulta
            statement.setDate(1, sesion.getFecha());
            statement.setTime(2, sesion.getHora());
            statement.setInt(3, sesion.getSalaId());
            statement.setInt(4, sesion.getPeliculaId());
            statement.setInt(5, sesion.getId());

            // Ejecutar la consulta
            int filasAfectadas = statement.executeUpdate();

            // Verificar si se actualizó correctamente
            return (filasAfectadas > 0) ? 1 : 0;

        } catch (SQLException e) {
            // Manejar la excepción
            return 0;

        } finally {
            ConexionDB.cerrarConexion(conexion);
        }
    }

    // Método para eliminar una sesión
    public int eliminarSesion(int id_) {
        try {
            // Consulta SQL para eliminar una sesión por su ID
            String consulta = "DELETE FROM sesiones WHERE id=?";
            PreparedStatement statement = conexion.prepareStatement(consulta);

            // Establecer el valor del parámetro de la consulta
            statement.setInt(1, id_);

            // Ejecutar la consulta
            int filasAfectadas = statement.executeUpdate();

            // Verificar si se eliminó correctamente
            return (filasAfectadas > 0) ? 1 : 0;

        } catch (SQLException e) {
            // Manejar la excepción
            return 0;

        } finally {
            ConexionDB.cerrarConexion(conexion);
        }
    }

    public List<Sesion> buscarSesion(String texto) {
        List<Sesion> sesiones = new ArrayList<>();

        try {
            // Consulta SQL para buscar sesiones que coincidan con el texto
            String consulta = "SELECT s.id, fecha, hora, salaId, nombre, peliculaId, titulo FROM sesiones s INNER JOIN peliculas p ON s.peliculaId = p.id INNER JOIN salas sa ON s.salaId = sa.id"
                    + " WHERE CAST(fecha AS VARCHAR) LIKE ? OR CAST(hora AS VARCHAR) LIKE ? OR "
                    + "CAST(salaId AS VARCHAR) LIKE ? OR CAST(peliculaId AS VARCHAR) LIKE ? OR "
                    + "nombre LIKE ? OR titulo LIKE ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);

            // Establecer los valores de los parámetros de la consulta
            for (int i = 1; i <= 6; i++) {
                statement.setString(i, "%" + texto + "%");
            }

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                // Guardar los datos en variables
                int id = rs.getInt("id");
                Date fecha = rs.getDate("fecha");
                Time hora = rs.getTime("hora");
                int salaId = rs.getInt("salaId");
                String nombreSala = rs.getString("nombre");
                int peliculaId = rs.getInt("peliculaId");
                String tituloPelicula = rs.getString("titulo");

                // Crear objeto Sesion con el constructor correspondiente
                Sesion sesion = new Sesion(id, fecha, hora, salaId, peliculaId);
                sesion.setNombreSala(nombreSala);
                sesion.setNombrePelicula(tituloPelicula);
                sesiones.add(sesion);
            }

        } catch (SQLException e) {
            // Manejar la excepción
        } finally {
            ConexionDB.cerrarConexion(conexion);
        }

        return sesiones;
    }

    // Método para verificar si existe alguna sesión en el rango de tiempo de la nueva sesión
    public boolean existeSesion(Sesion sesion) {
        try {
            // Calcular la hora de finalización de la nueva sesión
            int duracionPelicula = obtenerDuracionPelicula(sesion.getPeliculaId());
            Time horaFinNuevaSesion = sumarMinutos(sesion.getHora(), duracionPelicula);

            // Consulta SQL para verificar si hay alguna sesión en el rango de tiempo
            String consulta = "SELECT COUNT(*) FROM sesiones WHERE salaId = ? AND fecha = ? "
                    + "AND ((hora BETWEEN ? AND ?) OR (? BETWEEN hora AND ?))";
            PreparedStatement statement = conexion.prepareStatement(consulta);

            // Establecer los valores de los parámetros de la consulta
            statement.setInt(1, sesion.getSalaId());
            statement.setDate(2, sesion.getFecha());
            statement.setTime(3, sesion.getHora());
            statement.setTime(4, horaFinNuevaSesion);
            statement.setTime(5, sesion.getHora());
            statement.setTime(6, horaFinNuevaSesion);

            ResultSet resultSet = statement.executeQuery();

            // Verificar si hay alguna sesión en el rango de tiempo
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }

        } catch (SQLException e) {
            return false;
        }

        return false;
    }
    
    // Método para verificar si existe alguna sesión en el rango de tiempo de la nueva sesión
    public boolean existeSesionActualizar(Sesion sesion) {
        try {
            // Calcular la hora de finalización de la nueva sesión
            int duracionPelicula = obtenerDuracionPelicula(sesion.getPeliculaId());
            Time horaFinNuevaSesion = sumarMinutos(sesion.getHora(), duracionPelicula);

            // Consulta SQL para verificar si hay alguna sesión en el rango de tiempo
            String consulta = "SELECT COUNT(*) FROM sesiones WHERE salaId = ? AND fecha = ? "
                    + "AND ((hora BETWEEN ? AND ?) OR (? BETWEEN hora AND ?)) AND id != ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);

            // Establecer los valores de los parámetros de la consulta
            statement.setInt(1, sesion.getSalaId());
            statement.setDate(2, sesion.getFecha());
            statement.setTime(3, sesion.getHora());
            statement.setTime(4, horaFinNuevaSesion);
            statement.setTime(5, sesion.getHora());
            statement.setTime(6, horaFinNuevaSesion);
            statement.setInt(7, sesion.getId());
            
            ResultSet resultSet = statement.executeQuery();

            // Verificar si hay alguna sesión en el rango de tiempo
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }

        } catch (SQLException e) {
            return false;
        }

        return false;
    }

    // Método auxiliar para obtener la duración de una película
    private int obtenerDuracionPelicula(int peliculaId) {
        try {
            // Consulta SQL para obtener la duración de una película
            String consulta = "SELECT duracion FROM peliculas WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);

            // Establecer el valor del parámetro de la consulta
            statement.setInt(1, peliculaId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Obtener la duración de la película
                return resultSet.getInt("duracion");
            }

        } catch (SQLException e) {
            return 0;
        }

        return 0;
    }

    // Método auxiliar para sumar minutos a una hora
    private Time sumarMinutos(Time hora, int minutos) {
        LocalTime localTime = hora.toLocalTime().plusMinutes(minutos);
        return Time.valueOf(localTime);
    }
}
