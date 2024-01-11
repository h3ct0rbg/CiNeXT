package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Sala;

public class SalaDAO {

    private Connection conexion;

    public SalaDAO() {
        // Conexión a la base de datos
        this.conexion = ConexionDB.obtenerConexion();
    }

    // Método para obtener una sala por su ID
    public Sala getSalaById(int idSala) {
        Sala sala = null;

        try {
            // Consulta SQL para obtener una sala por su ID
            String consulta = "SELECT id, filas, columnas, tipo, nombre FROM salas WHERE id=?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, idSala);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Crear objeto Sala con los datos obtenidos de la base de datos
                int id = resultSet.getInt("id");
                int filas = resultSet.getInt("filas");
                int columnas = resultSet.getInt("columnas");
                String tipo = resultSet.getString("tipo");
                String nombre = resultSet.getString("nombre");

                sala = new Sala(id, filas, columnas, tipo, nombre);
            }

        } catch (SQLException e) {
            // Manejar la excepción
        } finally {
            ConexionDB.cerrarConexion(conexion);
        }

        return sala;
    }

    // Método para mostrar todas las salas
    public List<Sala> mostrarSalas() {
        List<Sala> salas = new ArrayList<>();

        try {
            // Consulta SQL para obtener todas las salas
            String consulta = "SELECT id, filas, columnas, tipo, nombre FROM salas";
            PreparedStatement statement = conexion.prepareStatement(consulta);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Crear objeto Sala con los datos obtenidos de la base de datos
                int id = resultSet.getInt("id");
                int filas = resultSet.getInt("filas");
                int columnas = resultSet.getInt("columnas");
                String tipo = resultSet.getString("tipo");
                String nombre = resultSet.getString("nombre");

                Sala sala = new Sala(id, filas, columnas, tipo, nombre);
                salas.add(sala);
            }

        } catch (SQLException e) {
            // Manejar la excepción
        } finally {
            ConexionDB.cerrarConexion(conexion);
        }

        return salas;
    }

    // Método para insertar una nueva sala en la base de datos
    public int insertarSala(Sala sala) {
        try {
            // Verificar si la sala ya existe en la base de datos
            if (existeSala(sala.getNombre())) {
                // La sala ya existe, retorna un código de error
                return 2;
            }

            // Consulta SQL para insertar una nueva sala
            String consulta = "INSERT INTO salas (filas, columnas, tipo, nombre) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consulta);

            // Establecer los valores de los parámetros de la consulta
            statement.setInt(1, sala.getFilas());
            statement.setInt(2, sala.getColumnas());
            statement.setString(3, sala.getTipo());
            statement.setString(4, sala.getNombre());

            // Ejecutar la consulta
            int filasAfectadas = statement.executeUpdate();

            // Verificar si se insertó correctamente
            return (filasAfectadas > 0) ? 1 : 0;

        } catch (SQLException e) {
            // Manejar la excepción
            return 0;

        } finally {
            ConexionDB.cerrarConexion(conexion);
        }
    }

    // Método para actualizar una sala
    public int modificarSala(Sala sala) {
        try {
            // Consulta SQL para actualizar una sala
            String consulta = "UPDATE salas SET filas=?, columnas=?, tipo=?, nombre=? WHERE id=?";
            PreparedStatement statement = conexion.prepareStatement(consulta);

            // Establecer los valores de los parámetros de la consulta
            statement.setInt(1, sala.getFilas());
            statement.setInt(2, sala.getColumnas());
            statement.setString(3, sala.getTipo());
            statement.setString(4, sala.getNombre());
            statement.setInt(5, sala.getId());

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

    // Método para eliminar una sala por su ID
    public int eliminarSala(int id_) {
        try {
            // Consulta SQL para eliminar una sala por su ID
            String consulta = "DELETE FROM salas WHERE id=?";
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

    // Método para comprobar si una sala ya existe por su nombre
    public boolean existeSala(String nombre) {
        boolean salaExistente = false;

        try {
            // Consulta SQL para verificar si una sala ya existe por su nombre
            String consulta = "SELECT COUNT(*) FROM salas WHERE nombre=?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, nombre);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                salaExistente = true;
            }

        } catch (SQLException e) {
            // Manejar la excepción
        } finally {
            ConexionDB.cerrarConexion(conexion);
        }

        return salaExistente;
    }
}